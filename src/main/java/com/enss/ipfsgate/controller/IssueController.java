package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueService issueService;

    //新建issue
    @RequestMapping("/newissue")
    public int newIssue(String repo_name, String member_name,String issue_content, String issue_label){
        return issueService.insert(repo_name, member_name, issue_content, issue_label);
    }

    //查询仓库的所有issue
    @RequestMapping("/searchrepo")
    public List<Map<String,Object>> searchRepo(String repo_name){
        return issueService.searchRepo(repo_name);
    }
    //查询用户的所有issue
    @RequestMapping("/searchmem")
    public List<Map<String,Object>> searchMem(String member_name){
        return issueService.searchMem(member_name);
    }
}
