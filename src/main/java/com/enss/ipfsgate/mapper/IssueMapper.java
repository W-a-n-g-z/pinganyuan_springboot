package com.enss.ipfsgate.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IssueMapper {
    //新建
    int insert(@Param("repo_name") String repo_name, @Param("member_name") String member_name,
               @Param("issue_content") String issue_content, @Param("issue_label") String issue_label);
    //查询仓库的所有issue
    List<Map<String,Object>> searchRepo(@Param("repo_name") String repo_name);
    //查询用户的所有issue
    List<Map<String,Object>> searchMem(@Param("member_name") String member_name);
}
