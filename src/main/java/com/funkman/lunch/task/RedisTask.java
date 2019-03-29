package com.funkman.lunch.task;

import com.funkman.lunch.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RedisTask {

    @Autowired
    RedisUtils redisUtils;

    @Scheduled(fixedRate = 10000)
    public void queryTask() {
        System.out.println("1111");
    }

}
