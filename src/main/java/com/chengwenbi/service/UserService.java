package com.chengwenbi.service;

import com.chengwenbi.common.Result;
import com.chengwenbi.domain.dto.UserDTO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.service.base.IBaseInterfaceService;

public interface UserService extends IBaseInterfaceService<UserDO> {

    Result login(UserDTO userDTO)throws Exception;

    Result modifyPassword(UserDTO userDTO)throws Exception;

    Result modifyUserRole(UserDTO userDTO) throws Exception;

    UserDO findByEmail(UserDTO userDTO) throws Exception;
}
