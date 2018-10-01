package com.chengwenbi.dao;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.MenuDO;
import com.chengwenbi.domain.entity.UserDO;

import java.util.List;

public interface UserMapper extends BaseInterfaceMapper<UserDO> {

    UserDO findByEmail(String email);

    Integer verifyEmail(String email);

    List<MenuDO> findMenu(String identityId);

}
