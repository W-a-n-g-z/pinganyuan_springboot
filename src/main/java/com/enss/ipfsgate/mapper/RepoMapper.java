package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.RepoInfo;

import java.util.List;
import java.util.Map;

public interface RepoMapper {
    //查询
    List<RepoInfo> search(RepoInfo record);

    //登录
    List<Map<String,Object>> selectAll();

    //新建
    int insert(RepoInfo record);

    //活跃仓库
    List<Map<String,Object>> activeRepo();

}
