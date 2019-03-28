package com.funkman.lunch.controller;

import com.funkman.lunch.entity.Error;
import com.funkman.lunch.entity.Result;
import com.funkman.lunch.entity.Success;
import com.funkman.lunch.entity.User;
import com.funkman.lunch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Result selectUser(User user) {
        User current = userService.selectUserById(user.getId());
        return Result.success(new Success(200,"currentUser"),current);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result updateUser(User user) {
        if(!StringUtils.isEmpty(user.getId())){
            User updateUser = userService.update(user);
            return Result.success(new Success(200, "updateUser"), updateUser);
        }else {
            return Result.error(new Error(100, "该用户为新用户"), null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result addUser(User user) {
        if (StringUtils.isEmpty(user.getId())) {
            User saveUser = userService.save(user);
            return Result.success(new Success(200, "saveUser"), saveUser);
        }else {
            return Result.error(new Error(150, "该用户已经存在"), null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result deleteUser(User user) {
        userService.delete(user);
        return Result.success(new Success(200, "删除成功"), null);
    }
}
