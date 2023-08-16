package com.enss.ipfsgate.model.repo;

public class RepoInfo {
    private Integer rId;
    private String rName;
    private String rUser;
    private String repo_label;
    private String repo_language;
    private String desc;

    public RepoInfo() {
        super();
    }

    public RepoInfo(String rName){
        this.rName=rName;
    }

    public RepoInfo(Integer rId,String rName,String rUser,String repo_label,String repo_language){
        this.rId=rId;
        this.rName=rName;
        this.rUser=rUser;
        this.repo_label=repo_label;
        this.repo_language=repo_language;
    }
    public RepoInfo(String rName,String rUser,String repo_label,String repo_language){
        this.rName=rName;
        this.rUser=rUser;
        this.repo_label=repo_label;
        this.repo_language=repo_language;
    }

    public RepoInfo(Integer rId, String rName, String rUser, String repo_label, String repo_language, String desc) {
        this.rId = rId;
        this.rName = rName;
        this.rUser = rUser;
        this.repo_label = repo_label;
        this.repo_language = repo_language;
        this.desc = desc;
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

    public String getRepo_label() {
        return repo_label;
    }

    public void setRepo_label(String repo_label) {
        this.repo_label = repo_label;
    }

    public String getRepo_language() {
        return repo_language;
    }

    public void setRepo_language(String repo_language) {
        this.repo_language = repo_language;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
