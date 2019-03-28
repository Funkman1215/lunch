package com.funkman.lunch.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface CustomizedDao {

    List<LinkedHashMap<String, Object>> myBatisSelectSQL(@Param("sql") String sql);

    int myBatisUpdateSQL(@Param("sql") String sql);

}
