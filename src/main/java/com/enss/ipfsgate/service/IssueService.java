package com.enss.ipfsgate.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IssueService {
    //新建
    Integer insert(String repo_name, String member_name,String issue_content, String issue_label);
    //查询仓库的所有issue
    List<Map<String,Object>> searchRepo(String repo_name);
    //查询用户的所有issue
    List<Map<String,Object>> searchMem(String member_name);
}
