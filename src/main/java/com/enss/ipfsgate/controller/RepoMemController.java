package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.service.RepoMemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repomem")
public class RepoMemController {
    @Autowired
    private RepoMemService repoMemService;

    //查询所有成员
    @RequestMapping("/findall")
    public List<Map<String,Object>> findtest(){
        return repoMemService.selectAllMember();
    }

    //查询某仓库的所有成员
    @RequestMapping("/findrepomem")
    public List<Map<String,Object>> selectRepoMember(String repo_name){
        return repoMemService.selectRepoMember(repo_name);
    }

    //添加成员
    @RequestMapping("/addmem")
    public void add(String repo_name,String member_name){
        repoMemService.addMember(repo_name,member_name);
    }

    //删除成员
    @RequestMapping("/deletemem")
    public void delete(String repo_name,String member_name){
        repoMemService.deleteMember(repo_name,member_name);
    }

    //删除管理员
    @RequestMapping("/deletemanager")
    void deleteManager(String repo_name,String member_name){
        repoMemService.deleteManager(repo_name,member_name);
    }

    //添加管理员
    @RequestMapping("/addmanager")
    void addManager(String repo_name,String member_name){
        repoMemService.addManager(repo_name,member_name);
    }

}
