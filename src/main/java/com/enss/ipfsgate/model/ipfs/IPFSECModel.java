package com.enss.ipfsgate.model.ipfs;

import java.util.HashMap;
import java.util.Map;

public class IPFSECModel {
    private boolean allSuccess = true;
    private Map<String,IPFSModel> ipfsKModelMap;   //K文件列表
    private Map<String,IPFSModel> ipfsMModelMap;  //M文件列表

    public boolean isAllSuccess() {
        return allSuccess;
    }

    public void setAllSuccess(boolean allSuccess) {
        this.allSuccess = allSuccess;
    }

    public Map<String, IPFSModel> getIpfsKModelMap() {
        if(null==this.ipfsKModelMap){
            this.ipfsKModelMap = new HashMap<String,IPFSModel>();
        }
        return ipfsKModelMap;
    }

    public void setIpfsKModelMap(Map<String, IPFSModel> ipfsKModelMap) {
        this.ipfsKModelMap = ipfsKModelMap;
    }

    public Map<String, IPFSModel> getIpfsMModelMap() {
        if(null==this.ipfsMModelMap){
            this.ipfsMModelMap = new HashMap<String,IPFSModel>();
        }
        return ipfsMModelMap;
    }

    public void setIpfsMModelMap(Map<String, IPFSModel> ipfsMModelMap) {
        this.ipfsMModelMap = ipfsMModelMap;
    }
}
