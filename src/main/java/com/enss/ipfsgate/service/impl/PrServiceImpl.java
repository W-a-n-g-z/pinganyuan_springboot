package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.PrMapper;
import com.enss.ipfsgate.model.PrInfo;
import com.enss.ipfsgate.service.PrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrServiceImpl implements PrService {
    @Autowired
    private PrMapper prMapper;

    @Override
    public int newPr(PrInfo prInfo) {
        return prMapper.insert(prInfo);
    }

    @Override
    public List<PrInfo> search(String repo_name, String member_name) {
        return prMapper.search(repo_name,member_name);
    }

    @Override
    public void agree(String repo_name, String member_name) {
        prMapper.agree(repo_name,member_name);
    }
}
