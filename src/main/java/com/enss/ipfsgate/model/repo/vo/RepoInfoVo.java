package com.enss.ipfsgate.model.repo.vo;

import com.enss.ipfsgate.model.repo.RepoInfo;

public class RepoInfoVo extends RepoInfo {

    private String keyword;
    private String sort;
    private Integer pageNum;
    private Integer pageSize;

    public RepoInfoVo(Integer rId, String rName, String rUser, String repo_label, String repo_language, String keyword, String sort, Integer pageNum, Integer pageSize) {
        super(rId, rName, rUser, repo_label, repo_language);
        this.keyword = keyword;
        this.sort = sort;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
