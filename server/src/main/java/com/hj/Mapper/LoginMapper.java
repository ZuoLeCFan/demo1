package com.hj.Mapper;

import com.hj.bean.bo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

   //注册
    void enroll(User user);
    //登陆
    User selectUser(User user);
}
