package com.funkman.lunch;

import com.funkman.lunch.dao.CustomizedDao;
import com.funkman.lunch.dao.LunchDao;
import com.funkman.lunch.dao.UserDao;
import com.funkman.lunch.entity.Lunch;
import com.funkman.lunch.entity.Restaurant;
import com.funkman.lunch.entity.User;
import com.funkman.lunch.resultEnum.LevelEnum;
import com.funkman.lunch.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LunchApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CustomizedDao customizedDao;

    @Autowired
    private LunchDao lunchDao;

    @Autowired
    private RedisUtils redisUtils;

    //jpa
    @Test
    public void demo1() {
        Restaurant restaurant = new Restaurant();
        restaurant.setCheep(LevelEnum.ONE.getNumber());
        restaurant.setDistance(LevelEnum.TWO.getNumber());
        restaurant.setSweet(LevelEnum.THREE.getNumber());
        restaurant.setName("哈哈餐厅");
        User user = new User();
        user.setName("测试用户");
        Lunch lunch = new Lunch();
        lunch.setEatTime(new Timestamp(10000000L));
        lunch.setRestaurant(restaurant);
        lunch.setUser(user);
        lunchDao.save(lunch);
    }

    //jpa
    @Test
    @Transactional
    public void demo2() {
        Optional<User> obj = userDao.findById(1);
        User user = obj.get();
        System.out.println("testSelect():" + user.getName() + user.getLunches().get(0).getRestaurant());
    }

    // MyBatis Select
    @Test
    public void testSelectSQL() {
        List<LinkedHashMap<String, Object>> items = customizedDao.myBatisSelectSQL("SELECT * FROM lunch");
        for (LinkedHashMap<String, Object> hashMap : items) {
            for (String key : hashMap.keySet()) {
                System.out.println(key + ":" + hashMap.get(key));
            }
            System.out.println("--------------");
        }

    }


    public void addTask() {

    }
}
