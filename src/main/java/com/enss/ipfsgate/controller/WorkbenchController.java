package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.model.repo.RepoBranch;
import com.enss.ipfsgate.model.repo.RepoInfo;
import com.enss.ipfsgate.service.IssueService;
import com.enss.ipfsgate.service.PrService;
import com.enss.ipfsgate.service.RepoService;
import com.enss.ipfsgate.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Resp ownerRepo(String user_name){
        return Resp.success(repoService.onesRepo(user_name));
    }

    @RequestMapping("/addDepo")
    public Resp addDepo(@RequestBody RepoInfo repoInfo){
        return Resp.success(repoService.addDepo(repoInfo));
    }

    @RequestMapping("/someonerepo")
    public Resp someoneInRepo(String member_name){
        return Resp.success(repoService.someoneInRepo(member_name));
    }

    @RequestMapping("/selectBranchList")
    public Resp selectBranchList(int repoId){
        return Resp.success(repoService.selectBranchList(repoId));
    }

    @RequestMapping("/addBranch")
    public Resp addBranch(@RequestBody RepoBranch repoBranch){
        int count = repoService.addBranch(repoBranch);
        if(count>0){
            return Resp.success("创建成功！");
        }else{
            return Resp.error("创建失败！");
        }
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
