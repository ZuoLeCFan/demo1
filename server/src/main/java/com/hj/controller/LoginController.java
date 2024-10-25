package com.hj.controller;

import com.hj.bean.bo.User;
import com.hj.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/LOGIN")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;


    //登陆
    @PostMapping("/LOGIN")
    public Map<String, Object> login(@RequestBody User user) {
        return loginService.loginService(user);
    }

    //注册
    @PostMapping("/ENROLL")
    public Map<String, Object> enroll(@RequestBody User user) {
        return loginService.enrollService(user);
    }

}
