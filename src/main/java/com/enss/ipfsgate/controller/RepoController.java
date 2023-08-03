package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.model.PrInfo;
import com.enss.ipfsgate.model.RepoInfo;
import com.enss.ipfsgate.service.OperateService;
import com.enss.ipfsgate.service.PrService;
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
    @Autowired
    private OperateService operateService;
    @Autowired
    private PrService prService;

    //新建仓库
    @RequestMapping("/newrepo")
    public int newRepo(String repo_name, String member_name, String repo_label, String repo_language){
//        RepoInfo repoInfo = new RepoInfo("test","twf","ceshi","java");
        RepoInfo repoInfo = new RepoInfo(repo_name,member_name,repo_label,repo_language);
        return repoService.newRepo(repoInfo);
    }

    //搜索仓库
    @RequestMapping("/search")
    public List<RepoInfo> search(String repo_name){
        return repoService.search(repo_name);
    }

    //查看所有仓库
    @RequestMapping("/findall")
    public List<Map<String,Object>> findall(){
        return repoService.selectAll();
    }

    //活跃仓库
    @RequestMapping("/activerepo")
    public List<Map<String,Object>> active(){
        return repoService.activeRepo();
    }

    //最近动态
    @RequestMapping("/recentmoment")
    public List<Map<String,Object>> recentMoment(String repo_name){
        return operateService.search(repo_name);
    }

    //PR统计
    @RequestMapping("prstatistics")
    public List<PrInfo> prStatistics(String repo_name){
        return prService.searchRepo(repo_name);
    }
}
