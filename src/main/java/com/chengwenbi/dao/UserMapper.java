package com.chengwenbi.dao;

import com.chengwenbi.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public List<User> getUsers();

    @Select("select * from user")
    public List<User> getUsers1();
}
