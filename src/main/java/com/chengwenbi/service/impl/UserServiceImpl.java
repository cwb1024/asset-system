package com.chengwenbi.service.impl;

import com.chengwenbi.dao.UserMapper;
import com.chengwenbi.domain.User;
import com.chengwenbi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers() {
        return userMapper.getUsers1();
    }
}
