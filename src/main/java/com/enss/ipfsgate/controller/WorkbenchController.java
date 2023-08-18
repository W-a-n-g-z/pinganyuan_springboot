package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.model.repo.RepoBranch;
import com.enss.ipfsgate.model.repo.RepoInfo;
import com.enss.ipfsgate.model.repo.vo.RepoAuditScore;
import com.enss.ipfsgate.model.repo.vo.RepoBranchVo;
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

    //获取仓库的可信评价状况
    @RequestMapping("/getRepoAuditScore")
    public Resp getRepoAuditScore(int repoId){
        List<RepoBranchVo> repoBranchVoList = repoService.selectBranchList(repoId);
        int defaultNum = 0;     //未申请审核可信，auditState=-1，扣1分
        int unAuditNum = 0;     //申请未审核，auditState=0，加1分
        int auditNum = 0;       //可信，auditState=1，加2分
        int failAuditNum = 0;   //不可信，auditState=2，扣2分
        if(repoBranchVoList!=null){
            for (int i=0;i<repoBranchVoList.size();i++)
            {
                for (int j=0;j<repoBranchVoList.get(i).getRepoFileList().size();j++)
                {
                    int auditState = repoBranchVoList.get(i).getRepoFileList().get(j).getAuditState();
                    if(-1==auditState){
                        defaultNum++;
                    }else if(0==auditState){
                        unAuditNum++;
                    }else if(1==auditState){
                        auditNum++;
                    }else if(2==auditState){
                        failAuditNum++;
                    }
                }
            }
        }else{
            return Resp.error("未查询到该仓库！");
        }
        int score = 60-defaultNum*1+unAuditNum*1+auditNum*2-failAuditNum*2;
        return Resp.success(new RepoAuditScore(defaultNum,unAuditNum,auditNum,failAuditNum,score));
    }

    //申请可信依赖库，重新将未审核通过的可信库文件提交申请
    @RequestMapping("/applyForAudit")
    public Resp applyForAudit(int repoId,String priKey){
        repoService.applyForAudit(repoId);
        return Resp.success("申请成功！");
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
