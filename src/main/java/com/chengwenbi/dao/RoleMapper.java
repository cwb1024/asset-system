package com.chengwenbi.dao;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.dto.RoleDTO;
import com.chengwenbi.domain.dto.UserRoleDTO;
import com.chengwenbi.domain.entity.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseInterfaceMapper<RoleDO> {

    void authRole(@Param("authorityList") List<UserRoleDTO> authorityList);

}
