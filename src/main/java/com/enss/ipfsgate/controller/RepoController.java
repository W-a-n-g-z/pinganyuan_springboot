package com.enss.ipfsgate.controller;

import com.alibaba.fastjson.JSON;
import com.enss.ipfsgate.model.PrInfo;
import com.enss.ipfsgate.model.repo.RepoInfo;
import com.enss.ipfsgate.model.repo.vo.RepoInfoVo;
import com.enss.ipfsgate.service.ContractService;
import com.enss.ipfsgate.service.OperateService;
import com.enss.ipfsgate.service.PrService;
import com.enss.ipfsgate.service.RepoService;
import com.enss.ipfsgate.utils.Resp;
import com.enss.ipfsgate.utils.fisco.web3j.FiscoUtil;
import com.enss.ipfsgate.utils.zero.fiatshamir.FiatShamirUtil;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    private ContractService contractService;
    @Autowired
    private FiscoUtil fiscoUtil;

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


    /**
     * 注册依赖库的可信信息，即通过检查后的依赖库特征信息，这个信息由平台生成
     * @param keyword 注册信息
     * @return
     */
    @RequestMapping(value = "registerLibraries")
    public Resp registerLibraries(@RequestParam String keyword,@RequestParam String priKey){
        String contractAddress = contractService.selectContractAddress(1);

        //1.注册验证信息
        Long x1 = FiatShamirUtil.contractStep0(keyword);
        //2.发起验证申请
        int y = FiatShamirUtil.contractStep1(x1);
        List<Object> paramList1 = new ArrayList<>();
        paramList1.add(y);
        try {
            TransactionResponse tr = fiscoUtil.execFiatShamirContract(priKey,paramList1,contractAddress,"FiatShamir","Step1_register");
            String txJsonStr = JSON.toJSONString(tr);
            System.out.println("Step1_register成功，返回值为："+txJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Resp.success("依赖库信息注册成功！");
    }


    /**
     * 验证可信依赖库，即需要提供的依赖库特征信息，这个信息由用户即时上传所用的依赖库时由平台生成
     * @param cKeyword 发起验证信息
     * @return
     */
    @RequestMapping(value = "checkLibraries")
    public Resp checkLibraries(@RequestParam String cKeyword,@RequestParam String priKey){
        String contractAddress = contractService.selectContractAddress(1);

        //3.计算并发送挑战信息
        int v = FiatShamirUtil.getRandomWithN();
        int t = FiatShamirUtil.contractStep2(v);
        List<Object> paramList2 = new ArrayList<>();
        paramList2.add(t);
        try {
            TransactionResponse tr = fiscoUtil.execFiatShamirContract(priKey,paramList2,contractAddress,"FiatShamir","Step2_login");
            String txJsonStr = JSON.toJSONString(tr);
            System.out.println("Step2_login成功，返回值为："+txJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //4.接收链返回的随机数c
        int c=0;
        List<Object> paramList3 = new ArrayList<>();
        try {
            TransactionResponse tr = fiscoUtil.execFiatShamirContract(priKey,paramList3,contractAddress,"FiatShamir","Step3_randomchallenge");
            c = Integer.parseInt(tr.getValues().replace("[","").replace("]",""));
            System.out.println("Step3_randomchallenge成功，返回值为："+c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //5.发送挑战信息r
        Long x2 = FiatShamirUtil.contractStep0(cKeyword);
        Long r = FiatShamirUtil.contractStep45(x2,c,v);
        List<Object> paramList4 = new ArrayList<>();
        paramList4.add(r);
        String result = "";
        try {
            TransactionResponse tr = fiscoUtil.execFiatShamirContract(priKey,paramList4,contractAddress,"FiatShamir","Step45_verify");
            result = JSON.toJSONString(tr);
            System.out.println("Step45_verify，返回值为："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Resp.success(result);
    }




}
