package com.chengwenbi.service;

import com.chengwenbi.common.Result;
import com.chengwenbi.domain.dto.UserDTO;
import com.chengwenbi.domain.entity.MenuDO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.service.base.IBaseInterfaceService;

import java.util.List;

public interface UserService extends IBaseInterfaceService<UserDO> {

    Result login(UserDTO userDTO)throws Exception;

    Result modifyPassword(UserDTO userDTO)throws Exception;

    Result modifyUserRole(UserDTO userDTO) throws Exception;

    UserDO findByEmail(UserDTO userDTO) throws Exception;

    boolean verifyEmail(String email) throws Exception;

    List<MenuDO> findMenu(String identityId) throws Exception;
}
