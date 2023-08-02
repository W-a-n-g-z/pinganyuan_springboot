package com.enss.ipfsgate.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OperateService {
    //新建
    int insert(String repo_name, String member_name, String operate);
    //查询最近十条
    List<Map<String,Object>> search(String repo_name);
}
