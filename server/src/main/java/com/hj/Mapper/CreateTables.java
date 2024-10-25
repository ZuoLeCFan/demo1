package com.hj.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreateTables {
    void createTables(String userTableName);
    //创建表
}
