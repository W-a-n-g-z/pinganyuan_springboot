package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.RepoInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RepoService {
    //登录
    List<RepoInfo> search(String reponame);

    //注册账户
    int newRepo(RepoInfo repoInfo);

    //查询所有用户
    List<Map<String,Object>> selectAll();

    //活跃仓库
    List<Map<String,Object>> activeRepo();

    //某人拥有的仓库
    List<Map<String,Object>> onesRepo(String user_name);

    //某人参与的仓库
    List<Map<String,Object>> someoneInRepo(String member_name);
}
