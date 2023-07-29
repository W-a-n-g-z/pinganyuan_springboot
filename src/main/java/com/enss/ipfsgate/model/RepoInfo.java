package com.enss.ipfsgate.model;

public class RepoInfo {
    private Integer rId;
    private String rName;
    private String rUser;

    public RepoInfo(Integer rId,String rName,String rUser){
        this.rId=rId;
        this.rName=rName;
        this.rUser=rUser;
    }
    public RepoInfo(String rName,String rUser){
        this.rName=rName;
        this.rUser=rUser;
    }
    public RepoInfo(String rName){
        this.rName=rName;
    }
    public RepoInfo() {
        super();
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public void setrUser(String rUser) {
        this.rUser = rUser;
    }
}
