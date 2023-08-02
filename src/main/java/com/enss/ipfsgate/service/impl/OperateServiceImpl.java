package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.OperateMapper;
import com.enss.ipfsgate.service.OperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OperateServiceImpl implements OperateService {
    @Autowired
    private OperateMapper operateMapper;

    @Override
    public int insert(String repo_name, String member_name, String operate) {
        return operateMapper.insert(repo_name,member_name,operate);
    }

    @Override
    public List<Map<String, Object>> search(String repo_name) {
        return operateMapper.search(repo_name);
    }
}
