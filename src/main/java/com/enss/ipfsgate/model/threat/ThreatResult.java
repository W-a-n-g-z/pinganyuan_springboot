package com.enss.ipfsgate.model.threat;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ThreatResult {

    private Integer id;

    private Integer nodeId;

    private String nodeIp;

    private String threatIp;

    private Integer threatType; //与库里对应，目前1代表ssh登录异常，2代表网络流量异常

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatetime;

    private String threatDesc;

    private String analyResult;

    private String analyJson;

    private Integer cmdUserId;

    private Integer cmdState;

    private String cmdDesc;

    private Integer chainState;

    private String chainHash;

    private String chainKey;

    private String traceFileName;

    private String traceIpfsHash;

    private String remark;

    /**
     * 当前所使用的构造
     * @param id 主键id
     * @param nodeId 在node表中的id
     * @param nodeIp 日志来源的节点ip
     * @param threatIp 威胁来源的ip
     * @param threatType 威胁类型表id
     * @param createtime 威胁结论创建时间
     * @param updatetime 威胁结论更新时间
     * @param threatDesc 威胁描述，例如 从xx发起了xx次xx攻击
     * @param analyResult 在威胁库中分析后的结论，例如 该威胁源曾被发现xx行为
     * @param analyJson 如果在威胁库中匹配到信息，则将匹配到的信息保存为json
     * @param cmdUserId 操作用户id
     * @param cmdState 操作状态 0.未处理 1.处理中 2.已归档
     * @param cmdDesc 操作描述
     * @param chainState 上链状态 0.未上链 1.已上链
     * @param chainHash 上链哈希
     * @param chainKey 上链时所用的key，id+trace_file_name+trace_storage_path拼接后进行MD5计算
     * @param traceFileName 供溯源的文件名称
     * @param traceIpfsHash 供溯源的文件地址
     * @param remark 备注
     */
    public ThreatResult(Integer id, Integer nodeId, String nodeIp, String threatIp, Integer threatType, Date createtime, Date updatetime, String threatDesc, String analyResult, String analyJson, Integer cmdUserId, Integer cmdState, String cmdDesc, Integer chainState, String chainHash, String chainKey, String traceFileName, String traceIpfsHash, String remark) {
        this.id = id;
        this.nodeId = nodeId;
        this.nodeIp = nodeIp;
        this.threatIp = threatIp;
        this.threatType = threatType;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.threatDesc = threatDesc;
        this.analyResult = analyResult;
        this.analyJson = analyJson;
        this.cmdUserId = cmdUserId;
        this.cmdState = cmdState;
        this.cmdDesc = cmdDesc;
        this.chainState = chainState;
        this.chainHash = chainHash;
        this.chainKey = chainKey;
        this.traceFileName = traceFileName;
        this.traceIpfsHash = traceIpfsHash;
        this.remark = remark;
    }

    /**
     * 原构造，调研时期使用，参数顺序可能不一致
     */
    public ThreatResult(Integer id, Date createtime, Date updatetime, Integer threatType, String analyResult, String analyJson, Integer cmdUserId, Integer cmdState, String cmdDesc, String traceIpfsHash, String threatDesc, Integer chainState, String traceFileName) {
        this.id = id;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.threatType = threatType;
        this.analyResult = analyResult;
        this.analyJson = analyJson;
        this.cmdUserId = cmdUserId;
        this.cmdState = cmdState;
        this.cmdDesc = cmdDesc;
        this.traceIpfsHash = traceIpfsHash;
        this.threatDesc = threatDesc;
        this.chainState = chainState;
        this.traceFileName = traceFileName;
    }

    public ThreatResult() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public String getThreatIp() {
        return threatIp;
    }

    public void setThreatIp(String threatIp) {
        this.threatIp = threatIp;
    }

    public Integer getThreatType() {
        return threatType;
    }

    public void setThreatType(Integer threatType) {
        this.threatType = threatType;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getThreatDesc() {
        return threatDesc;
    }

    public void setThreatDesc(String threatDesc) {
        this.threatDesc = threatDesc;
    }

    public String getAnalyResult() {
        return analyResult;
    }

    public void setAnalyResult(String analyResult) {
        this.analyResult = analyResult;
    }

    public String getAnalyJson() {
        return analyJson;
    }

    public void setAnalyJson(String analyJson) {
        this.analyJson = analyJson;
    }

    public Integer getCmdUserId() {
        return cmdUserId;
    }

    public void setCmdUserId(Integer cmdUserId) {
        this.cmdUserId = cmdUserId;
    }

    public Integer getCmdState() {
        return cmdState;
    }

    public void setCmdState(Integer cmdState) {
        this.cmdState = cmdState;
    }

    public String getCmdDesc() {
        return cmdDesc;
    }

    public void setCmdDesc(String cmdDesc) {
        this.cmdDesc = cmdDesc;
    }

    public Integer getChainState() {
        return chainState;
    }

    public void setChainState(Integer chainState) {
        this.chainState = chainState;
    }

    public String getChainHash() {
        return chainHash;
    }

    public void setChainHash(String chainHash) {
        this.chainHash = chainHash;
    }

    public String getChainKey() {
        return chainKey;
    }

    public void setChainKey(String chainKey) {
        this.chainKey = chainKey;
    }

    public String getTraceFileName() {
        return traceFileName;
    }

    public void setTraceFileName(String traceFileName) {
        this.traceFileName = traceFileName;
    }

    public String getTraceIpfsHash() {
        return traceIpfsHash;
    }

    public void setTraceIpfsHash(String traceIpfsHash) {
        this.traceIpfsHash = traceIpfsHash;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
