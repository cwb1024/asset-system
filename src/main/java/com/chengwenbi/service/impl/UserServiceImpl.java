package com.chengwenbi.service.impl;

import com.chengwenbi.common.Result;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.dao.UserMapper;
import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.UserDO;
import com.chengwenbi.domain.UserDTO;
import com.chengwenbi.service.UserService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import com.chengwenbi.util.ValidParamUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseInterfaceServiceImpl<UserDO> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseInterfaceMapper<UserDO> getBaseInterfaceMapper() {
        return userMapper;
    }

    @Override
    public Result login(UserDTO userDTO)throws Exception {
        UserDO userDO = userMapper.findByEmail(userDTO.getEmail());
        if (userDO == null) {
            throw new ServiceException("用户不存在");
        }
        if (!userDTO.getPassword().equals(userDTO.getPassword())){
            throw new ServiceException("用户名密码不匹配");
        }
        Result result = new Result();
        result.modifyResult(true,"登陆成功");
        return result;
    }

    @Override
    public Result modifyPassword(UserDTO userDTO) throws Exception{
        ValidParamUtil.validNotNull(userDTO.getEmail());

        UserDO userDO = userMapper.findByEmail(userDTO.getEmail());
        if (userDO == null) {
            throw new ServiceException("用户不存在，不能修改密码");
        }

        //DTO to DO
        BeanUtils.copyProperties(userDO,userDTO);
        int res = userMapper.update(userDO);
        Result result = new Result();
        if (res != 0) {
            result.modifyResult(true,"密码修改成功");
        }
        return result;
    }
}
