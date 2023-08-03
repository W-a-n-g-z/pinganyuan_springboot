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
    public int login(String username,String password){
        return userService.Login(username,password);
    }

    @RequestMapping("/register")
    public int register(String username, String password, String phone, String email){
        UserInfo userInfo = new UserInfo(username,password,phone,email);
        return userService.regUser(userInfo);
    }

}
