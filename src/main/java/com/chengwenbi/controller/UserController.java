package com.chengwenbi.controller;

import com.chengwenbi.domain.User;
import com.chengwenbi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @description:
 * @author: chengwenbi
 * @date:   2017/12/17 13:18
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String testUser(){
        log.debug("进入testUser的方法");
        List<User> users = userService.getUsers();
        log.debug("调用service的方法查出来的用户："+users.toString());

        return "index";
    }



}
