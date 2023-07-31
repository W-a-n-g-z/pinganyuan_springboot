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

    @RequestMapping("/findall")
    public List<Map<String,Object>> findtest(){
        return repoMemService.selectAllMember();
    }

    @RequestMapping("/add")
    public void add(String repo_name,String member_name){
        repoMemService.addMember(repo_name,member_name);
    }

    @RequestMapping("/delete")
    public void delete(String member_name){
        repoMemService.deleteMember(member_name);
    }

}
