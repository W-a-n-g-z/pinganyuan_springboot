package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {

    //查询所有用户
    List<Map<String,Object>> selectAllUsers();

    //登录
    List<UserInfo> Login(UserInfo userInfo);

    int updateChainInfo(UserInfo userInfo);

    int insert(UserInfo record);

}
