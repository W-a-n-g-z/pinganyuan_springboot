package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.service.IssueService;
import com.enss.ipfsgate.service.PrService;
import com.enss.ipfsgate.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workbench")
public class WorkbenchController {
    @Autowired
    private RepoService repoService;
    @Autowired
    private PrService prService;
    @Autowired
    private IssueService issueService;

    @RequestMapping("/ownerrepo")
    public List<Map<String,Object>> ownerRepo(String user_name){
        return repoService.onesRepo(user_name);
    }

    @RequestMapping("/someonerepo")
    public List<Map<String,Object>> someoneInRepo(String member_name){
        return repoService.someoneInRepo(member_name);
    }

    //某人提交的pr
    @RequestMapping("/onespr")
    public List<Map<String,Object>> onesPr(String member_name){
        return prService.onesPr(member_name);
    }

    //某人审核的pr
    @RequestMapping("/managepr")
    public List<Map<String,Object>> managePr(String member_name){
        return prService.managePr(member_name);
    }

    //某人发布的issue
    @RequestMapping("/onesissue")
    public List<Map<String,Object>> onesIssue(String member_name){
        return issueService.searchMem(member_name);
    }

}
