package com.enss.ipfsgate.model.repo;

public class RepoBranch {

    private Integer id;
    private Integer repoId;
    private String branchName;
    private String desc;

    public RepoBranch() {
    }

    public RepoBranch(Integer id, Integer repoId, String branchName, String desc) {
        this.id = id;
        this.repoId = repoId;
        this.branchName = branchName;
        this.desc = desc;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
