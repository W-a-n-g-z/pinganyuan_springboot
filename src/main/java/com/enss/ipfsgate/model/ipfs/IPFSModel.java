package com.enss.ipfsgate.model.ipfs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class IPFSModel {

    private String fHash;
    private List<String> fLinks;
    private String fName;
    private Integer fSize;
    private String fPeerIP;

    public IPFSModel(String resultJSON){

        JSONObject ipfsModel= JSON.parseObject(resultJSON);
        this.fHash = ipfsModel.getString("Hash");
        this.fName = ipfsModel.getString("Name");
        this.fSize = Integer.parseInt(ipfsModel.getString("Size"));

    }

    public IPFSModel(String fHash,String fName,Integer fSize){
        this.fHash = fHash;
        this.fName = fName;
        this.fSize = fSize;
    }

    public IPFSModel(String fHash,String fName,Integer fSize,List<String> fLinks){
        this.fHash = fHash;
        this.fName = fName;
        this.fSize = fSize;
        this.fLinks = fLinks;
    }



    /**
     * 文件名
     * @return f_hash 文件名
     */
    public String getfHash() {
        return fHash;
    }

    /**
     * 文件名
     * @param fHash 文件名
     */
    public void setfHash(String fHash) {
        this.fHash = fHash == null ? null : fHash.trim();
    }

    /**
     * 文件名
     * @return f_links 文件名
     */
    public List<String> getfLinks() {
        return fLinks;
    }

    /**
     * 文件名
     * @param fLinks 文件名
     */
    public void setfLinks(List<String> fLinks) {
        this.fLinks = fLinks == null ? null : fLinks;
    }

    /**
     * 文件名
     * @return f_name 文件名
     */
    public String getfName() {
        return fName;
    }

    /**
     * 文件名
     * @param fName 文件名
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    /**
     * 文件名
     * @return f_size 文件名
     */
    public Integer getfSize() {
        return fSize;
    }

    /**
     * 文件名
     * @param fSize 文件名
     */
    public void setfSize(Integer fSize) {
        this.fSize = fSize == null ? null : fSize;
    }

    /**
     * 文件名
     * @return fPeerIP 该文件上传在哪个节点上
     */
    public String getfPeerIP() {
        return fPeerIP;
    }

    /**
     * 文件名
     * @param fPeerIP 该文件上传在哪个节点上
     */
    public void setfPeerIP(String fPeerIP) {
        this.fPeerIP = fPeerIP;
    }
}
