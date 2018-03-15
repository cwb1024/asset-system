package com.chengwenbi.dao;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.UserDO;

public interface UserMapper extends BaseInterfaceMapper<UserDO> {

    public UserDO findByEmail(String email);

}
