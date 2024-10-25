package com.hj.service;

import com.hj.Mapper.CreateTables;
import com.hj.service.impl.CreateTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTableService implements CreateTableServiceImpl {
    @Autowired
    private CreateTables createTables;


    @Override
    public void createUserTable(String userTableName) {
        createTables.createTables(userTableName);
    }
}
