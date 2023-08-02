package com.enss.ipfsgate.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RepoMemMapper {
    //查询所有成员
    List<Map<String,Object>> selectAllMember();

    //查询某仓库的所有成员
    List<Map<String,Object>> selectRepoMember(@Param("repo_name") String repo_name);

    //添加成员
    void addMember(@Param("repo_name") String repo_name, @Param("member_name") String member_name);

    //删除成员
    void deleteMember(@Param("repo_name") String repo_name, @Param("member_name") String member_name);

    //添加管理员
    void addManager(@Param("repo_name") String repo_name,@Param("member_name") String member_name);

    //删除管理员
    void deleteManager(@Param("repo_name") String repo_name,@Param("member_name") String member_name);
}
