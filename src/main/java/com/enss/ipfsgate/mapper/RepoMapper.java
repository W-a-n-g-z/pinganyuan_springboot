package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.RepoInfo;
import org.apache.ibatis.annotations.Param;

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

    //某人拥有的仓库
    List<Map<String,Object>> onesRepo(@Param("user_name") String user_name);

    //某人参与的仓库
    List<Map<String,Object>> someoneInRepo(@Param("member_name") String member_name);

}
