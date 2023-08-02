package com.enss.ipfsgate.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RepoMemService {
    //查询所有成员
    List<Map<String,Object>> selectAllMember();

    //查询某仓库的所有成员
    List<Map<String,Object>> selectRepoMember(String repo_name);

    //添加成员
    void addMember(String repo_name, String member_name);

    //删除成员
    void deleteMember(String repo_name,String member_name);

    //删除管理员
    void deleteManager(String repo_name,String member_name);

    //添加管理员
    void addManager(String repo_name,String member_name);

}
