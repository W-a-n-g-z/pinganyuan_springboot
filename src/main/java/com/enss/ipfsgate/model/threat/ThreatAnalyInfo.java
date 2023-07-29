package com.enss.ipfsgate.model.threat;

/**
 * 包含威胁分析所需条件的实体类，需要与威胁处理服务器保持一致
 */
public class ThreatAnalyInfo {

    private String analyType;   //威胁分析类型，目前包含ip、zeek
    private String threatIp;    //威胁来源的ip
    private String traceIpfsHash;
    private String threatDesc;
    private String nodeIp;
    private String traceFileName;

    public ThreatAnalyInfo() {
    }

    public String getAnalyType() {
        return analyType;
    }

    public void setAnalyType(String analyType) {
        this.analyType = analyType;
    }

    public String getThreatIp() {
        return threatIp;
    }

    public void setThreatIp(String threatIp) {
        this.threatIp = threatIp;
    }

    public String getTraceIpfsHash() {
        return traceIpfsHash;
    }

    public void setTraceIpfsHash(String traceIpfsHash) {
        this.traceIpfsHash = traceIpfsHash;
    }

    public String getThreatDesc() {
        return threatDesc;
    }

    public void setThreatDesc(String threatDesc) {
        this.threatDesc = threatDesc;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public String getTraceFileName() {
        return traceFileName;
    }

    public void setTraceFileName(String traceFileName) {
        this.traceFileName = traceFileName;
    }

}
