package com.enss.ipfsgate.model.repo;

import com.enss.ipfsgate.utils.JsonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 全量日志的实体类，由于netlog目前结构一致，也适用与netlog
 */
public class RepoFile {

    private Integer id;                 //主键，自增
    private Integer repoId;             //文件所属仓库id，过时字段
    private Integer branchId;           //文件所属分支id
    private String fileName;            //文件名
    private String relativePath;        //在仓库中相对根目录路径
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date uploadDate;            //上传日期
    private Integer fileType;           //文件类型,备用
    private String fileTypeName;        //文件类型字符串,备用
    private BigInteger fileSize;        //文件大小
    private String fileSizeStr;         //文件大小带单位的字符串显示
    private String tempSavePath;        //在服务器上的临时路径
    private String tempUrl;             //对外访问的临时url
    private String ipfsHash;            //在ipfs中存储的位置
    private Integer ipfsState;          //ipfs存储状态，1.成功 2.失败 3.未知
    private String chainHash;           //文件上链哈希,同时作为上链的key
    private Integer chainState;         //上链状态，1.成功 2.失败 3.未知
    private String chainHeight;         //区块高度
    private String remark;              //备注

    public RepoFile(){
    }

    public RepoFile(Integer id, Integer repoId, Integer branchId, String fileName, String relativePath, Date uploadDate, Integer fileType, String fileTypeName, BigInteger fileSize, String fileSizeStr, String tempSavePath, String tempUrl, String ipfsHash, Integer ipfsState, String chainHash, Integer chainState, String chainHeight, String remark) {
        this.id = id;
        this.repoId = repoId;
        this.branchId = branchId;
        this.fileName = fileName;
        this.relativePath = relativePath;
        this.uploadDate = uploadDate;
        this.fileType = fileType;
        this.fileTypeName = fileTypeName;
        this.fileSize = fileSize;
        this.fileSizeStr = fileSizeStr;
        this.tempSavePath = tempSavePath;
        this.tempUrl = tempUrl;
        this.ipfsHash = ipfsHash;
        this.ipfsState = ipfsState;
        this.chainHash = chainHash;
        this.chainState = chainState;
        this.chainHeight = chainHeight;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepoId() {
        return repoId;
    }

    public void setRepoId(Integer repoId) {
        this.repoId = repoId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public BigInteger getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigInteger fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSizeStr() {
        return fileSizeStr;
    }

    public void setFileSizeStr(String fileSizeStr) {
        this.fileSizeStr = fileSizeStr;
    }

    public String getTempSavePath() {
        return tempSavePath;
    }

    public void setTempSavePath(String tempSavePath) {
        this.tempSavePath = tempSavePath;
    }

    public String getTempUrl() {
        return tempUrl;
    }

    public void setTempUrl(String tempUrl) {
        this.tempUrl = tempUrl;
    }

    public String getIpfsHash() {
        return ipfsHash;
    }

    public void setIpfsHash(String ipfsHash) {
        this.ipfsHash = ipfsHash;
    }

    public Integer getIpfsState() {
        return ipfsState;
    }

    public void setIpfsState(Integer ipfsState) {
        this.ipfsState = ipfsState;
    }

    public String getChainHash() {
        return chainHash;
    }

    public void setChainHash(String chainHash) {
        this.chainHash = chainHash;
    }

    public Integer getChainState() {
        return chainState;
    }

    public void setChainState(Integer chainState) {
        this.chainState = chainState;
    }

    public String getChainHeight() {
        return chainHeight;
    }

    public void setChainHeight(String chainHeight) {
        this.chainHeight = chainHeight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
