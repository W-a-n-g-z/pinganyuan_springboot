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
    UserInfo selectLogin(String username, String password);
    int Login(UserInfo userInfo);

    /**
     *
     * @mbg.generated 2020-02-14
     */
    int deleteByPrimaryKey(Integer uId);

    /**
     *
     * @mbg.generated 2020-02-14
     */
    int insert(UserInfo record);

    /**
     *
     * @mbg.generated 2020-02-14
     */
    int insertSelective(UserInfo record);

    /**
     *
     * @mbg.generated 2020-02-14
     */
    UserInfo selectByPrimaryKey(Integer uId);

    /**
     *
     * @mbg.generated 2020-02-14
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     *
     * @mbg.generated 2020-02-14
     */
    int updateByPrimaryKey(UserInfo record);
}
