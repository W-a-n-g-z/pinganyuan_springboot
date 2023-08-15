package com.enss.ipfsgate.utils.ipfs;

import com.enss.ipfsgate.config.AppConfigSchedule;
import com.enss.ipfsgate.utils.Constant;
import com.enss.ipfsgate.utils.JsonUtil;
import com.enss.ipfsgate.utils.Resp;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class IPFSClusterUtils {

    //获取文件
    //Multihash filePointer = Multihash.fromBase58("QmPZ9gcCEpqKTo6aq61g2nXGUhM4iCL3ewB6LDXZCtioEB");
    //byte[] fileContents = ipfs.cat(filePointer);

    //5001为常规api接口，9095是cluster对常规api的proxy，通过9095发送的add，会在cluster里都添加进pin
    public IPFS ipfs = new IPFS(AppConfigSchedule.clusterMainUrl);

    private static final Logger logger = LoggerFactory.getLogger(IPFSClusterUtils.class);

    //IPFS上传文件
    public Resp IPFSUpload(String fileSavePath){
        //保存上传文件
        NamedStreamable.FileWrapper savefile = new NamedStreamable.FileWrapper(new File(fileSavePath));
        MerkleNode result = null;
        try {
            result = ipfs.add(savefile).get(0);
        } catch (IOException e) {
            logger.error("", e);
            return new Resp(1, "文件上传IPFS Cluster失败");
        }
        return new Resp(0, "文件上传IPFS Cluster成功",result.toJSONString());//返回结果里面获取保存文件的唯一hash，基于文件内容的 hash
    }

    //从上传文件的json字符串中获取文件hash
    public String getHashFromResp(Resp resp){
        HashMap<String, Object> jsonRetInfo = JsonUtil.jsonToMap(resp.getData().toString());
        String ipfsHash = jsonRetInfo.get("Hash").toString();
        return ipfsHash;
    }

    //从上传文件的json字符串中获取文件大小
    public Integer getSizeFromResp(Resp resp){
        HashMap<String, Object> jsonRetInfo = JsonUtil.jsonToMap(resp.getData().toString());
        int logSize = Integer.parseInt(jsonRetInfo.get("Hash").toString());
        return logSize;
    }

    //IPFS下载文件
    public Resp IPFSDownload(String filehash,String filename) {
        //下载文件
        // Multihash filePointer = Multihash.fromBase58("Qmc51yHGyaxdRsUwBozP9tQuTRQuXLkZv3sPEcpSgb8BxP");//参数为文件 hash
        Multihash filePointer = Multihash.fromBase58(filehash);//参数为文件 hash
        byte[] fileContents = new byte[0];
        try {
            fileContents = ipfs.cat(filePointer);
        } catch (IOException e) {
            return new Resp(1, "IPFS Cluster下载文件失败");
        }

        //保存文件
        File downloadfile = new File(Constant.ROOT + File.separator + "downloadCache" + filename);

        if (!downloadfile.exists()) {
            try {
                downloadfile.createNewFile();
            } catch (IOException e) {
                return new Resp(1, "IPFS Cluster保存文件失败，1001");
            }
        }

        FileOutputStream fop = null;
        try {
            fop = new FileOutputStream(downloadfile);
        } catch (FileNotFoundException e) {
            return new Resp(1, "IPFS Cluster保存文件失败，1002");
        }
        try {
            fop.write(fileContents);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            return new Resp(1, "IPFS Cluster保存文件失败，1003");
        }

        return new Resp(0, "IPFS Cluster下载保存文件成功");
    }
}
