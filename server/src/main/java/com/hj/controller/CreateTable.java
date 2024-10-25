package com.hj.controller;

import com.hj.bean.bo.User;
import com.hj.service.impl.CreateTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CREATE")
public class CreateTable {
    @Autowired
    private CreateTableServiceImpl createTableService;
    /*
     * 根据表头创建数据表
     * 创建用户
     */
    @PostMapping("/USER-TABLE")
    public void create_table(@RequestBody User user) {
        createTableService.createUserTable(user.getName());
        System.out.println(user);
    }
}
