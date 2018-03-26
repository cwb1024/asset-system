package com.chengwenbi.dao;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.UserDO;

public interface UserMapper extends BaseInterfaceMapper<UserDO> {

    UserDO findByEmail(String email);

    Integer verifyEmail(String email);

}
