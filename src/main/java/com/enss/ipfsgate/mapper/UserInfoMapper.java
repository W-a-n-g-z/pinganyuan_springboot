package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserInfoMapper {


    //登录
    UserInfo selectLogin(String username, String password);

    //用户名查询是否重复
    int isUserNameRepeat(String username);

    //查询所有用户
    List<Map<String,Object>> selectAllUsers(String keyword);

    //验证手机号是否重复
    int isPhoneRepeat(String phone);




    int deleteByPrimaryKey(Integer uId);




    int insert(UserInfo record);


    int insertSelective(UserInfo record);


    UserInfo selectByPrimaryKey(Integer uId);


    int updateByPrimaryKeySelective(UserInfo record);


    int updateByPrimaryKey(UserInfo record);
}
