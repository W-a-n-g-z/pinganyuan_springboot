package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.service.IssueService;
import com.enss.ipfsgate.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueService issueService;

    //新建issue
    @RequestMapping("/newissue")
    public Resp newIssue(String repo_name, String member_name,String issue_content, String issue_label){
        int insertId = issueService.insert(repo_name, member_name, issue_content, issue_label);
        if(insertId == 1)
            return Resp.success("插入成功",insertId);
        else
            return Resp.error("插入失败",insertId);
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
