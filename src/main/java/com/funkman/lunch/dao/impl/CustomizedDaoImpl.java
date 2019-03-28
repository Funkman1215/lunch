package com.funkman.lunch.dao.impl;

import com.funkman.lunch.dao.CustomizedDao;
import com.funkman.lunch.myBatis.MyBatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public class CustomizedDaoImpl implements CustomizedDao {

    @Autowired
    MyBatisMapper myBatisMapper;

    @Override
    public List<LinkedHashMap<String, Object>> myBatisSelectSQL(String sql) {
        return myBatisMapper.myBatisSelectSQL(sql);
    }

    @Override
    public int myBatisUpdateSQL(String sql) {
        return myBatisMapper.myBatisUpdateSQL(sql);
    }
}
