package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.IssueMapper;
import com.enss.ipfsgate.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueMapper issueMapper;

    @Override
    public int insert(String repo_name, String member_name, String issue_content, String issue_label) {
        return issueMapper.insert(repo_name, member_name, issue_content, issue_label);
    }

    @Override
    public List<Map<String, Object>> searchRepo(String repo_name) {
        return issueMapper.searchRepo(repo_name);
    }

    @Override
    public List<Map<String, Object>> searchMem(String member_name) {
        return issueMapper.searchMem(member_name);
    }
}
