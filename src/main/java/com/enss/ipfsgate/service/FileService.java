package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.FileInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FileService {
    List<FileInfo> search(String repo_name, String file_name, String file_path);

    //查询某仓库的所有文件
    List<FileInfo> searchrepo(String repo_name);

    //查询所有
    List<Map<String,Object>> selectAll();

    //新建
    int insert(FileInfo record);
}
