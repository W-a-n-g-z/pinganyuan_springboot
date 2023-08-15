package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.model.PrInfo;
import com.enss.ipfsgate.model.repo.RepoInfo;
import com.enss.ipfsgate.model.repo.vo.RepoInfoVo;
import com.enss.ipfsgate.service.OperateService;
import com.enss.ipfsgate.service.PrService;
import com.enss.ipfsgate.service.RepoService;
import com.enss.ipfsgate.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

//    //搜索仓库
//    @RequestMapping("/search")
//    public List<RepoInfo> search(String repo_name){
//        return repoService.search(repo_name);
//    }

    /**
     * 条件查询仓库
     */
    @ResponseBody
    @RequestMapping("/search")
    public Resp search(@RequestBody RepoInfoVo repoInfoVo){
        if (null == repoInfoVo.getSort() || "".equals(repoInfoVo.getSort()) || "" == repoInfoVo.getSort()) {
            repoInfoVo.setSort("id desc");  //默认按id倒序
        }
        List<RepoInfoVo> repoInfoList = repoService.search(repoInfoVo);
        int repoInfoCount = repoService.searchCount(repoInfoVo);
        return Resp.success("查询成功!", repoInfoList, repoInfoCount);
    }

    //  查询数据详情
    @ResponseBody
    @RequestMapping(value = "searchDetail")
    public Resp searchDetail(@RequestBody RepoInfoVo repoInfoVo) {
        List<RepoInfoVo> repoInfoList = repoService.searchDetail(repoInfoVo);
        if(null!=repoInfoList && repoInfoList.size()<=0){
            return Resp.warning("未查询到此数据！");
        }
        return Resp.success("查询成功！",repoInfoList.get(0));
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
