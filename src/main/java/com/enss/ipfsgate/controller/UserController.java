package com.enss.ipfsgate.controller;

import com.enss.ipfsgate.model.UserInfo;
import com.enss.ipfsgate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findall")
    public List<Map<String,Object>> findtest(){
        return userService.selectAllUsers();
    }

    @RequestMapping("/login")
    public int login(){
        return userService.Login("wzy","222");
    }

    @RequestMapping("/register")
    public int register(){
        UserInfo userInfo = new UserInfo("xxx","12119","123456789", "111@qq.com");
        return userService.regUser(userInfo);
    }

}
