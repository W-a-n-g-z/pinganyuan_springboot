package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    //登录
    List<UserInfo> Login(String username, String password);

    //注册账户
    int regUser(UserInfo userInfo);
    //更新区块链信息
    int updateChainInfo(UserInfo userInfo);

    //查询所有用户
    List<Map<String,Object>> selectAllUsers();

}
