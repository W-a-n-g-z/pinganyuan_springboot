package com.enss.ipfsgate.model;

public class RepoInfo {
    private Integer rId;
    private String rName;
    private String rUser;
    private String repo_lable;
    private String repo_lauguage;

    public RepoInfo(Integer rId,String rName,String rUser,String repo_lable,String repo_lauguage){
        this.rId=rId;
        this.rName=rName;
        this.rUser=rUser;
        this.repo_lable=repo_lable;
        this.repo_lauguage=repo_lauguage;
    }
    public RepoInfo(String rName,String rUser,String repo_lable,String repo_lauguage){
        this.rName=rName;
        this.rUser=rUser;
        this.repo_lable=repo_lable;
        this.repo_lauguage=repo_lauguage;
    }
    public RepoInfo(String rName){
        this.rName=rName;
    }
    public RepoInfo() {
        super();
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrUser() {
        return rUser;
    }

    public void setrUser(String rUser) {
        this.rUser = rUser;
    }

    public String getRepo_lable() {
        return repo_lable;
    }

    public void setRepo_lable(String repo_lable) {
        this.repo_lable = repo_lable;
    }

    public String getRepo_lauguage() {
        return repo_lauguage;
    }

    public void setRepo_lauguage(String repo_lauguage) {
        this.repo_lauguage = repo_lauguage;
    }
}
