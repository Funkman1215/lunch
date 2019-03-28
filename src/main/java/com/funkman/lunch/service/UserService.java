package com.funkman.lunch.service;

import com.funkman.lunch.dao.UserDao;
import com.funkman.lunch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public User selectUserById(Integer id) {
        return userDao.getOne(id);
    }

    public User update(User user) {
        return userDao.save(user);
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}
