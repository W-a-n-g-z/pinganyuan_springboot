package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.model.RepoInfo;
import com.enss.ipfsgate.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repo")
public class RepoController {
    @Autowired
    private RepoService repoService;

    @RequestMapping("/newrepo")
    public int newRepo(){
        RepoInfo repoInfo = new RepoInfo("test","1");
        return repoService.newRepo(repoInfo);
    }

    @RequestMapping("/search")
    public List<RepoInfo> search(){
        return repoService.search("test_repo");
    }

    @RequestMapping("/findall")
    public List<Map<String,Object>> fingall(){
        return repoService.selectAll();
    }
}
