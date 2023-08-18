package com.enss.ipfsgate.service.impl;


import com.enss.ipfsgate.mapper.UserMapper;
import com.enss.ipfsgate.model.UserInfo;
import com.enss.ipfsgate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @auth Auth :zhangbo
 * @date Date : 2020年02月14日 21:50
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserInfo> Login(String username, String password) {
        UserInfo userInfo = new UserInfo(username,password);
        return userMapper.Login(userInfo);
    }


    @Override
    public int regUser(UserInfo userInfo) {
        userMapper.insert(userInfo);
        return userInfo.getId();
    }

    @Override
    public int updateChainInfo(UserInfo userInfo) {
        return userMapper.updateChainInfo(userInfo);
    }


    @Override
    public List<Map<String,Object>> selectAllUsers() {

        return userMapper.selectAllUsers();
    }

}
