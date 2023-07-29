package com.enss.ipfsgate.service.impl;


import com.enss.ipfsgate.mapper.UserMapper;
import com.enss.ipfsgate.model.UserInfo;
import com.enss.ipfsgate.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auth Auth :zhangbo
 * @date Date : 2020年02月14日 21:50
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo selectLogin(String username, String password) {
        UserInfo userInfo = userMapper.selectLogin(username.trim(),password.trim());
        if(null != userInfo){
            userInfo.setuLastLogin(new Date());
            try {
                userMapper.updateByPrimaryKeySelective(userInfo);
            }catch (Exception e){
                logger.error("登录修改最后登录时间失败");
                e.printStackTrace();
            }finally {
                return userInfo;
            }
        }else{
            return null;
        }

    }


    @Override
    public int regUser(UserInfo userInfo) {
        userInfo.setuCreateTime(new Date());
        userInfo.setuState(1);
        userInfo.setuType(1);
        userInfo.setuContractAddress("");

        return userMapper.insert(userInfo);
    }

    @Override
    public List<Map<String,Object>> selectAllUsers(String keyword) {

        return userMapper.selectAllUsers();
    }

}
