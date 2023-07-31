package com.enss.ipfsgate.model;

import java.util.Date;

public class FileInfo {
    private Integer fileId;
    private String repo_name;
    private String file_name;
    private String file_path;
    private Date update_date;
    private String ipfs_hash;

    public FileInfo(Integer fileId, String repo_name, String file_name, String file_path, Date update_date, String ipfs_hash) {
        this.fileId = fileId;
        this.repo_name = repo_name;
        this.file_name = file_name;
        this.file_path = file_path;
        this.update_date = update_date;
        this.ipfs_hash = ipfs_hash;
    }

    public FileInfo(String repo_name, String file_name, String file_path) {
        this.repo_name = repo_name;
        this.file_name = file_name;
        this.file_path = file_path;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getRepo_name() {
        return repo_name;
    }

    public void setRepo_name(String repo_name) {
        this.repo_name = repo_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getIpfs_hash() {
        return ipfs_hash;
    }

    public void setIpfs_hash(String ipfs_hash) {
        this.ipfs_hash = ipfs_hash;
    }
}
