package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.FileInfo;
import com.enss.ipfsgate.model.repo.RepoFile;
import com.enss.ipfsgate.utils.Resp;
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

    //新增文件信息
    int insertRepoFile(RepoFile repoFile);

    //更新链哈希
    int updateChainHash(RepoFile repoFile);

    //更新临时存储路径
    int updateTempSavePath(RepoFile repoFile);

    //更新对外提供访问的临时url，暂时可能没用
    int updateTempUrl(RepoFile repoFile);

    /***
     * 文件上传ipfs、信息上链，并更新数据库
     * @param repoFile
     */
    public Resp uploadIpfsAndChain(RepoFile repoFile);


    public int updateFileInfo(RepoFile repoFile);
}
