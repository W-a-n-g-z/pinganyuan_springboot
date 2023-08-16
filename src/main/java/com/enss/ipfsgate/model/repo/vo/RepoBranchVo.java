package com.enss.ipfsgate.model.repo.vo;

import com.enss.ipfsgate.model.repo.RepoBranch;
import com.enss.ipfsgate.model.repo.RepoFile;

import java.util.List;

public class RepoBranchVo extends RepoBranch {

    private List<RepoFile> repoFileList;

    public List<RepoFile> getRepoFileList() {
        return repoFileList;
    }

    public void setRepoFileList(List<RepoFile> repoFileList) {
        this.repoFileList = repoFileList;
    }
}
