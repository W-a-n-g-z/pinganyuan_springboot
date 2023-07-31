package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.PrInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrService {
    //新建PR
    int newPr(PrInfo prInfo);

    List<PrInfo> search(String repo_name, String member_name);
    //允许合并
    void agree(String repo_name, String member_name);
}
