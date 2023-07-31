package com.enss.ipfsgate.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RepoMemService {
//    增，删（状态修改为不可见），改，查
    List<Map<String,Object>> selectAllMember();

    void addMember(String repo_name, String member_name);

    void deleteMember(String member_name);

}
