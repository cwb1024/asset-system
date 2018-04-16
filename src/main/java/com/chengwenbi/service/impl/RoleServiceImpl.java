package com.chengwenbi.service.impl;

import com.chengwenbi.dao.RoleMapper;
import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.dto.RoleDTO;
import com.chengwenbi.domain.dto.UserRoleDTO;
import com.chengwenbi.domain.entity.RoleDO;
import com.chengwenbi.service.RoleService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseInterfaceServiceImpl<RoleDO> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseInterfaceMapper<RoleDO> getBaseInterfaceMapper() {
        return roleMapper;
    }


    @Override
    public void authRole(List<UserRoleDTO> authorityList){

    }
}
