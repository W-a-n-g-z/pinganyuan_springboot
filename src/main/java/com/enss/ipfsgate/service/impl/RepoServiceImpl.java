package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.RepoMapper;
import com.enss.ipfsgate.model.RepoInfo;
import com.enss.ipfsgate.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepoServiceImpl implements RepoService {

    @Autowired
    private RepoMapper repoMapper;

    @Override
    public List<RepoInfo> search(String reponame){
        RepoInfo repoInfo = new RepoInfo(reponame);
        return repoMapper.search(repoInfo);
    }

    //注册账户
    @Override
    public int newRepo(RepoInfo repoInfo){
        return repoMapper.insert(repoInfo);
    }

    //查询所有用户
    @Override
    public List<Map<String,Object>> selectAll(){
        return repoMapper.selectAll();
    }

    @Override
    public List<Map<String, Object>> activeRepo() {
        return repoMapper.activeRepo();
    }
}
