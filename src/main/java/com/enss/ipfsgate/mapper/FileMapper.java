package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.FileInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FileMapper {
    //查询特定
    List<FileInfo> search(@Param("repo_name") String repo_name,
                          @Param("file_name") String file_name, @Param("file_path") String file_path);

    //查询某仓库的所有文件
    List<FileInfo> searchrepo(@Param("repo_name") String repo_name);

    //查询所有
    List<Map<String,Object>> selectAll();

    //新建
    int insert(FileInfo record);
}
