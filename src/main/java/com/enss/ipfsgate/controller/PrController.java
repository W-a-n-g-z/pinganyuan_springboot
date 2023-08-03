package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.model.PrInfo;
import com.enss.ipfsgate.service.PrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pr")
public class PrController {
    @Autowired
    private PrService prService;

    @RequestMapping("/newpr")
    public int newRepo(String member_name, String repo_name, String apply_describe, String notes){
//        PrInfo prInfo = new PrInfo("twf","test_1","无","未通过","无");
        PrInfo prInfo = new PrInfo(member_name,repo_name,apply_describe,"未通过",notes);
        return prService.newPr(prInfo);
    }
    @RequestMapping("/search")
    public List<PrInfo> search(String repo_name, String member_name){
        //PrInfo prInfo = new PrInfo("twf","test","无","未通过","无");
/*        String repo_name="test";
        String member_name="twf";*/
        return prService.search(repo_name,member_name);
    }
    @RequestMapping("/agree")
    public void agree(String repo_name, String member_name){
        //PrInfo prInfo = new PrInfo("twf","test","无","未通过","无");
/*        String repo_name="test";
        String member_name="twf";*/
        prService.agree(repo_name,member_name);
    }

}
