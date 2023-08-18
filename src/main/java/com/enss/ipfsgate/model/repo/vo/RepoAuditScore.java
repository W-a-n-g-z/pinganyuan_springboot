package com.enss.ipfsgate.model.repo.vo;

//方便统计仓库的可信评价信息
public class RepoAuditScore {

    private Integer defaultNum;
    private Integer unAuditNum;
    private Integer auditNum;
    private Integer failAuditNum;
    private Integer score;

    public RepoAuditScore() {
    }

    public RepoAuditScore(Integer defaultNum, Integer unAuditNum, Integer auditNum, Integer failAuditNum, Integer score) {
        this.defaultNum = defaultNum;
        this.unAuditNum = unAuditNum;
        this.auditNum = auditNum;
        this.failAuditNum = failAuditNum;
        this.score = score;
    }

    public Integer getDefaultNum() {
        return defaultNum;
    }

    public void setDefaultNum(Integer defaultNum) {
        this.defaultNum = defaultNum;
    }

    public Integer getUnAuditNum() {
        return unAuditNum;
    }

    public void setUnAuditNum(Integer unAuditNum) {
        this.unAuditNum = unAuditNum;
    }

    public Integer getAuditNum() {
        return auditNum;
    }

    public void setAuditNum(Integer auditNum) {
        this.auditNum = auditNum;
    }

    public Integer getFailAuditNum() {
        return failAuditNum;
    }

    public void setFailAuditNum(Integer failAuditNum) {
        this.failAuditNum = failAuditNum;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
