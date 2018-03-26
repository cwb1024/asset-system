package com.chengwenbi.service.impl;

import com.chengwenbi.common.Result;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.dao.UserMapper;
import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.dto.UserDTO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.service.UserService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import com.chengwenbi.util.MD5Util;
import com.chengwenbi.util.ValidParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new ServiceException("用户不存在，登录失败");
        }
        //登录密码进行MD5加密比对
        if (!userDO.getPassword().equals(MD5Util.getMD5(userDTO.getPassword()))) {
            throw new ServiceException("用户名密码不匹配,登录失败");
        }
        return new Result(true,"登录成功",userDO);
    }

    @Override
    public Result modifyPassword(UserDTO userDTO) throws Exception{
        ValidParamUtil.validNotNull(userDTO.getPassword(),userDTO.getNewPassword(),userDTO.getEmail());

        UserDO userDO = userMapper.findByEmail(userDTO.getEmail());
        if (userDO == null) {
            throw new ServiceException("用户不存在，不能修改密码");
        }
        //验证原密码是否正确
        if (!userDO.getPassword().equals(MD5Util.getMD5(userDTO.getPassword()))) {
            throw new ServiceException("原密码错误，请输入正确的原密码");
        }
        //覆盖原密码，进行更新
        UserDO qo = new UserDO();
        //对密码进行加密
        String newPassword = MD5Util.getMD5(userDTO.getNewPassword());
        qo.setPassword(newPassword);
        qo.setEmail(userDO.getEmail());
        int res = userMapper.update(qo);
        Result result = new Result();
        if (res != 0) {
            result.modifyResult(true, "密码修改成功");
        } else {
            result.modifyResult(false,"密码修改失败");
        }
        return result;
    }

    @Override
    public Result modifyUserRole(UserDTO userDTO) throws Exception {

        return null;
    }

    @Override
    public UserDO findByEmail(UserDTO userDTO) throws Exception {
        String email = userDTO.getEmail();
        ValidParamUtil.validNotNull(email);
        UserDO userDO = userMapper.findByEmail(email);
        if (userDO == null) {
            throw new ServiceException("用户不存在");
        }
        return userDO;
    }

    @Override
    public boolean verifyEmail(String email) throws Exception {
        ValidParamUtil.validNotNull(email);
        Integer count = userMapper.verifyEmail(email);
        if (count != null && count > 0) {
            throw new ServiceException("y邮箱已存在");
        }
        return true;
    }

}
