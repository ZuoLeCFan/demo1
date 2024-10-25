package com.hj.service;

import com.hj.Mapper.LoginMapper;
import com.hj.bean.bo.User;
import com.hj.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService implements LoginServiceImpl {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Map<String, Object> loginService(User user) {
        Map<String, Object> map = new HashMap<>();
        try{
            //想查询看是否有这个用户
            User user1 = loginMapper.selectUser(user);
            System.out.println(user1);

            if(user1 == null){
                map.put("status", false);
                map.put("message", "请先注册");
                return map;
            }

           if(!user1.getPassword().equals(user.getPassword())){
               map.put("status", false);
               map.put("message", "密码不正确");
               return map;
           }

            map.put("status", true);
            map.put("message", "登陆成功");
            return map;

        }catch(Exception e){
            e.printStackTrace();
            map.put("status", false);
            map.put("message", "请重新输入");
            return map;
        }
    }

    @Override
    public Map<String, Object> enrollService(User user) {
        Map<String, Object> map = new HashMap<>();
        try{
            //想查询看是否有这个用户
            User user1 = loginMapper.selectUser(user);
            System.out.println(user1);

            if(user1 == null){
                loginMapper.enroll(user);
                map.put("status", true);
                map.put("message", "注册成功");
                return map;
            }

            if(user1.getId()!=0){
                map.put("status", false);
                map.put("message", "用户已存在");
                return map;
            }

            loginMapper.enroll(user);
            map.put("status", true);
            map.put("message", "注册成功");
            return map;
        }catch(Exception e){
            e.printStackTrace();
            map.put("status", false);
            map.put("message", "注册失败");
            return map;
        }
    }
}
