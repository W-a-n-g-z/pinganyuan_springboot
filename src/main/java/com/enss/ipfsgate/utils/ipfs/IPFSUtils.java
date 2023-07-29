package com.enss.ipfsgate.utils.ipfs;

import com.enss.ipfsgate.utils.Constant;
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

public class IPFSUtils {
    public IPFS ipfs = new IPFS("/ip4/39.98.67.202/tcp/5001");

    private static final Logger logger = LoggerFactory.getLogger(IPFSUtils.class);

    //IPFS上传文件
    public Resp IPFSUpload(String fileSavePath){
        //保存上传文件
        NamedStreamable.FileWrapper savefile = new NamedStreamable.FileWrapper(new File(fileSavePath));
        MerkleNode result = null;
        try {
            result = ipfs.add(savefile).get(0);
        } catch (IOException e) {
            logger.error("", e);
            return new Resp(1, "文件上传IPFS EC失败");
        }
        return new Resp(0, "文件上传IPFS EC成功",result.toJSONString());//返回结果里面获取保存文件的唯一hash，基于文件内容的 hash
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
            return new Resp(1, "IPFS下载EC文件失败");
        }

        //保存文件
        File downloadfile = new File(Constant.ROOT + File.separator + "downloadCache" + filename);

        if (!downloadfile.exists()) {
            try {
                downloadfile.createNewFile();
            } catch (IOException e) {
                return new Resp(1, "IPFS保存EC文件失败，1001");
            }
        }

        FileOutputStream fop = null;
        try {
            fop = new FileOutputStream(downloadfile);
        } catch (FileNotFoundException e) {
            return new Resp(1, "IPFS保存EC文件失败，1002");
        }
        try {
            fop.write(fileContents);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            return new Resp(1, "IPFS保存EC文件失败，1003");
        }

        return new Resp(0, "IPFS下载保存EC文件成功");
    }

}