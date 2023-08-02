package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.OperateMapper;
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
    @Autowired
    private OperateMapper operateMapper;

    //查找仓库
    @Override
    public List<RepoInfo> search(String reponame){
        RepoInfo repoInfo = new RepoInfo(reponame);
        return repoMapper.search(repoInfo);
    }

    //新建仓库
    @Override
    public int newRepo(RepoInfo repoInfo){
        return repoMapper.insert(repoInfo);
    }

    //查询所有仓库
    @Override
    public List<Map<String,Object>> selectAll(){
        return repoMapper.selectAll();
    }

    //活跃仓库
    @Override
    public List<Map<String, Object>> activeRepo() {
        return repoMapper.activeRepo();
    }

    //某人拥有的仓库
    @Override
    public List<Map<String, Object>> onesRepo(String user_name) {
        return repoMapper.onesRepo(user_name);
    }

    //某人参与的仓库
    @Override
    public List<Map<String, Object>> someoneInRepo(String member_name) {
        return repoMapper.someoneInRepo(member_name);
    }
}
