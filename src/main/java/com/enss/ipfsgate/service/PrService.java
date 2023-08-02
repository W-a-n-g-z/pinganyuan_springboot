package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.PrInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PrService {
    //新建PR
    int newPr(PrInfo prInfo);
    //查询所有pr
    List<PrInfo> search(String repo_name, String member_name);
    //查询某一仓库的所有pr
    List<PrInfo> searchRepo(String repo_name);
    //允许合并
    void agree(String repo_name, String member_name);

    //某人提交的pr
    List<Map<String,Object>> onesPr(String member_name);

    //某人审核的pr
    List<Map<String,Object>> managePr(String member_name);
}
