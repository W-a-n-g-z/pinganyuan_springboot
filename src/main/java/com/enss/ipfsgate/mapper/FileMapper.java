package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.FileInfo;
import com.enss.ipfsgate.model.threat.RepoFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
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

    //新增文件信息
    int insertRepoFile(RepoFile repoFile);

    //更新链哈希
    int updateChainHash(RepoFile repoFile);

    //更新临时存储路径
    int updateTempSavePath(RepoFile repoFile);

    //更新对外提供访问的临时url，暂时可能没用
    int updateTempUrl(RepoFile repoFile);
}
