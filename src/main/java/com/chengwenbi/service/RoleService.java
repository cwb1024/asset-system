package com.chengwenbi.service;

import com.chengwenbi.domain.dto.RoleDTO;
import com.chengwenbi.domain.dto.UserRoleDTO;
import com.chengwenbi.domain.entity.RoleDO;
import com.chengwenbi.service.base.IBaseInterfaceService;

import java.util.List;

public interface RoleService extends IBaseInterfaceService<RoleDO> {

    void authRole(List<UserRoleDTO> authorityList);
}
