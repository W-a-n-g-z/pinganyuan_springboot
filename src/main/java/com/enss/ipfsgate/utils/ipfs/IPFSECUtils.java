package com.enss.ipfsgate.utils.ipfs;

import com.enss.ipfsgate.config.AppConfigSchedule;
import com.enss.ipfsgate.model.ipfs.ECModel;
import com.enss.ipfsgate.model.ipfs.IPFSECModel;
import com.enss.ipfsgate.model.ipfs.IPFSModel;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class IPFSECUtils {

    public int ipfsNum = AppConfigSchedule.ecNums;     //ipfs节点数量

    public List<IPFS> ipfsList = new ArrayList<IPFS>();
    public String ecAllStr = AppConfigSchedule.ecAllUrlStr;

    private static final Logger logger = LoggerFactory.getLogger(IPFSUtils.class);

    private final static Executor executor = Executors.newCachedThreadPool();

    public IPFSECUtils(){
        String[] ecStrArray = this.ecAllStr.split(",");
        for( String ecStr : ecStrArray ){
            this.ipfsList.add(new IPFS(ecStr));
        }
        //this.ipfsList.add(new IPFS("/ip4/39.98.67.202/tcp/5001"));
        //this.ipfsList.add(new IPFS("/ip4/39.98.70.227/tcp/5001"));
    }

    //IPFS EC上传文件列表
    public IPFSECModel IPFSECUpload(ECModel ecModel){
        IPFSECModel ipfsEcModel = new IPFSECModel();
        Map<String,String> kMap = ecModel.getkPartPathMap();
        Map<String,String> mMap = ecModel.getmPartPathMap();

        int index = 0;

        //保存上传文件列表
        for (int i = 1; i <=kMap.size() ; i++) {

            NamedStreamable.FileWrapper savefile = new NamedStreamable.FileWrapper(new File(kMap.get(Integer.toString(i))));
            MerkleNode result = null;
            IPFSModel ipfsModel = null;
            try {
                int saveNo = index%ipfsNum;     //文件保存在哪个节点上
                result = this.ipfsList.get(saveNo).add(savefile).get(0);

                String ipfsSaveHash = result.toJSONString();

                ipfsModel = new IPFSModel(ipfsSaveHash);
                ipfsModel.setfPeerIP(this.ipfsList.get(saveNo).host);
                ipfsEcModel.getIpfsKModelMap().put(Integer.toString(i),ipfsModel);
            } catch (IOException e) {
                logger.error("出现一个k文件上传失败", e);
                ipfsEcModel.setAllSuccess(false);
            }finally {
                index++;
            }
        }

        for (int i = 1; i <=mMap.size() ; i++) {
            NamedStreamable.FileWrapper savefile = new NamedStreamable.FileWrapper(new File(mMap.get(Integer.toString(i))));
            MerkleNode result = null;
            IPFSModel ipfsModel = null;
            try {
                int saveNo = index % ipfsNum;     //文件保存在哪个节点上
                result = this.ipfsList.get(saveNo).add(savefile).get(0);

                String ipfsSaveHash = result.toJSONString();

                ipfsModel = new IPFSModel(ipfsSaveHash);
                ipfsModel.setfPeerIP(this.ipfsList.get(saveNo).host);
                ipfsEcModel.getIpfsMModelMap().put(Integer.toString(i),ipfsModel);
            } catch (IOException e) {
                logger.error("出现一个m文件上传失败", e);
                ipfsEcModel.setAllSuccess(false);
            }finally {
                index++;
            }
        }


        //打印上传的文件信息
        logger.info("------>>>>>>ipfs ec upload result+"+ipfsEcModel.isAllSuccess()+"<<<<<<------");

        logger.info("------>>>>>>ipfs ec k-parts upload hash list<<<<<<------");
        for (int i = 1; i <=ipfsEcModel.getIpfsKModelMap().size() ; i++) {
            logger.info("第"+i+"个文件哈希："+ipfsEcModel.getIpfsKModelMap().get(Integer.toString(i)).getfHash());
            logger.info("第"+i+"个文件所在节点："+ipfsEcModel.getIpfsKModelMap().get(Integer.toString(i)).getfPeerIP());
        }
        logger.info("------>>>>>>ipfs ec k-parts upload hash list end<<<<<<------");

        if(ipfsEcModel.getIpfsMModelMap().size()>0){
            logger.info("------>>>>>>ipfs ec m-parts upload hash list<<<<<<------");
            for (int i = 1; i <=ipfsEcModel.getIpfsMModelMap().size() ; i++) {
                logger.info("第"+i+"个文件哈希："+ipfsEcModel.getIpfsMModelMap().get(Integer.toString(i)).getfHash());
                logger.info("第"+i+"个文件所在节点："+ipfsEcModel.getIpfsMModelMap().get(Integer.toString(i)).getfPeerIP());
            }
            logger.info("------>>>>>>ipfs ec m-parts upload hash list end<<<<<<------");
        }


        return ipfsEcModel;
    }

}
