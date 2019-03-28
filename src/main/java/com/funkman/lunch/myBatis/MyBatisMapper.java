package com.funkman.lunch.myBatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface MyBatisMapper {

    // 执行任意SELECT语句（利用LinkedHashMap接收返回的结果）
    @Select("${sql}")
    List<LinkedHashMap<String,Object>> myBatisSelectSQL(@Param("sql")String sql);


    // 执行任意INSERT、UPDATE、DELETE语句
    @Update("${sql}")
    int myBatisUpdateSQL(@Param("sql")String sql);
}
