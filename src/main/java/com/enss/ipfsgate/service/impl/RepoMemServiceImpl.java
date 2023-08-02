package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.RepoMemMapper;
import com.enss.ipfsgate.service.RepoMemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepoMemServiceImpl implements RepoMemService {
    @Autowired
    private RepoMemMapper repoMemMapper;

    public List<Map<String,Object>> selectAllMember(){
        return repoMemMapper.selectAllMember();
    }

    @Override
    public List<Map<String, Object>> selectRepoMember(String repo_name) {
        return repoMemMapper.selectRepoMember(repo_name);
    }

    public void addMember(String repo_name, String member_name){
        repoMemMapper.addMember(repo_name,member_name);
    }

    @Override
    public void deleteMember(String repo_name,String member_name){
        repoMemMapper.deleteMember(repo_name,member_name);
    }

    @Override
    public void deleteManager(String repo_name, String member_name) {
        repoMemMapper.deleteManager(repo_name,member_name);
    }

    @Override
    public void addManager(String repo_name, String member_name) {
        repoMemMapper.addManager(repo_name,member_name);
    }
}
