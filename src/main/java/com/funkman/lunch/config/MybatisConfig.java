package com.funkman.lunch.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.funkman.lunch.myBatis.")
public class MybatisConfig {
}
