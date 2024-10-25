package com.hj.service.impl;

import com.hj.bean.bo.User;

import java.util.Map;

public interface LoginServiceImpl {
    Map<String, Object> loginService(User user);

    Map<String, Object> enrollService(User user);
}
