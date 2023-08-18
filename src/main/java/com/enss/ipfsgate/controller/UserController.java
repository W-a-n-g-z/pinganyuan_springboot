package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.model.UserInfo;
import com.enss.ipfsgate.model.UserSign;
import com.enss.ipfsgate.service.UserService;
import com.enss.ipfsgate.utils.Resp;
import com.enss.ipfsgate.utils.fisco.web3j.FiscoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FiscoUtil fiscoUtil;

    @RequestMapping("/findall")
    public List<Map<String,Object>> findtest(){
        return userService.selectAllUsers();
    }

    @RequestMapping("/login")
    public Resp login(String username,String password){
        List<UserInfo> loginUser = userService.Login(username,password);
        if(loginUser.size()>0){
            return Resp.success("登录成功！",loginUser.get(0),loginUser.size());
        }else{
            return Resp.success("登录失败！","",loginUser.size());
        }
    }

    @RequestMapping("/register")
    public Resp register(@RequestBody UserInfo userInfo){
        int userId = userService.regUser(userInfo);
        UserSign us = fiscoUtil.buildUserSignForRegister(userId);
        userInfo.setPriKey(us.getPriKey());
        userInfo.setPubKey(us.getPubKey());
        userInfo.setChainAddress(us.getAddress());
        int count = userService.updateChainInfo(userInfo);
        if(userId>0 && count>0){
            return Resp.success("注册成功！",userInfo.getPriKey());
        }else{
            return Resp.error("注册失败！");
        }
    }

}
