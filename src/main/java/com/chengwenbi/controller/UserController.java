package com.chengwenbi.controller;

import com.chengwenbi.common.Result;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.common.exception.ValidateParamsException;
import com.chengwenbi.constant.SessionConstants;
import com.chengwenbi.constant.UserState;
import com.chengwenbi.controller.base.BaseController;
import com.chengwenbi.domain.dto.UserDTO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.domain.vo.UserVO;
import com.chengwenbi.service.UserService;
import com.chengwenbi.util.MD5Util;
import com.chengwenbi.util.StringUtil;
import com.chengwenbi.util.ValidParamUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/login")
    public Result login(UserDTO userDTO, HttpSession session) {
        try {
            ValidParamUtil.validNotNull(userDTO.getEmail(), userDTO.getPassword());
            //验证登录
            result = userService.login(userDTO);
            //正确存入session
            UserDO data = (UserDO) result.getData();
            session.setAttribute(SessionConstants.USER_KEY, data);
            result = new Result();
            result.modifyResult(true, "登录成功");
        } catch (ServiceException | ValidateParamsException e) {
            log.warn("登录警告异常  " + userDTO.toString(), e);
            result.modifyResult(false, e.getMessage());
        } catch (Exception e) {
            log.error("登录异常  " + userDTO.toString(), e);
            result.modifyResult(false, "登录失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/logout")
    public Result logout(HttpSession session) {
        try {
            session.removeAttribute(SessionConstants.USER_KEY);//清除用户信息
            result.setMessage("退出成功");
        } catch (Exception e) {
            log.error("退出异常", e);
            result.modifyResult(false, "退出失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyPassword")
    public Result modifyPassword(UserDTO userDTO) {
        try {
            result = userService.modifyPassword(userDTO);
            result.modifyResult(true, userDTO, "修改密码成功");
        } catch (ServiceException | ValidateParamsException e) {
            log.warn("修改密码警告异常  " + userDTO.toString(), e);
            result.modifyResult(false, e.getMessage());
        } catch (Exception e) {
            log.error("修改密码异常  " + userDTO.toString(), e);
            result.modifyResult(false, "修改密码失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public Result addUser(HttpSession session, UserDTO userDTO) {
        UserDO qo = new UserDO();
        try {
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            ValidParamUtil.validNotNull(userDO);
            userDTO.setCreateId(userDO.getId());
            userDTO.setCreateName(userDO.getName());
            userDTO.setCreateTime(new Date());
            userDTO.setModifyId(userDO.getId());
            userDTO.setModifyName(userDO.getName());
            userDTO.setModifyTime(new Date());            //状态默认未删除
            BeanUtils.copyProperties(qo, userDTO);
            qo.setId(StringUtil.uuid());
            //设置默认密码
            qo.setPassword(MD5Util.getMD5("111111"));
            userService.add(qo);
            //DO 转 VO
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(vo,qo);
            result.modifyResult(true, vo, "添加成功");
        } catch (ServiceException | ValidateParamsException e) {
            log.warn("添加用户异常  " + qo.toString(), e);
            result.modifyResult(false, e.getMessage());
        } catch (Exception e) {
            log.error("添加失败  " + qo.toString(), e);
            result.modifyResult(false, "添加失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/modify")
    public Result modifyUser(HttpSession session, UserDTO userDTO) {
        UserDO qo = new UserDO();
        try {
            ValidParamUtil.validNotNull(userDTO.getEmail());
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            userDTO.setModifyId(userDO.getId());
            userDTO.setModifyName(userDO.getName());
            userDTO.setCreateTime(new Date());
            BeanUtils.copyProperties(qo, userDTO);
            userService.modify(qo);
            result.modifyResult(true, qo, "修改成功");
        } catch (ServiceException | ValidateParamsException e) {
            log.warn("修改用户异常  " + qo.toString(), e);
            result.modifyResult(false, e.getMessage());
        } catch (Exception e) {
            log.error("修改失败  " + qo.toString(), e);
            result.modifyResult(false, "修改失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/drop")
    public Result dropUser(UserDTO userDTO) {
        UserDO qo = null;
        try {
            qo=userService.findByEmail(userDTO);
            BeanUtils.copyProperties(qo, userDTO);
            qo.setState(UserState.BLOCKED);
            userService.modify(qo);
            result.modifyResult(true,"用户删除成功");
        } catch (ServiceException | ValidateParamsException e) {
            log.warn("删除用户异常  " + qo.toString(), e);
            result.modifyResult(false, e.getMessage());
        } catch (Exception e) {
            log.error("删除失败  " + qo.toString(), e);
            result.modifyResult(false, "删除失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/find")
    public Result findUser(UserDTO userDTO, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Result result = new Result();
        try {
            //dto 转 do
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(userDO, userDTO);
            PageHelper.startPage(pageNo, pageSize);
            List<UserDO> userList = userService.findByParams(userDO);
            //do 转 vo
            List<UserVO> voList = new ArrayList<>();
            for (UserDO user : userList) {
                UserVO vo = new UserVO();
                BeanUtils.copyProperties(vo,user);
                vo.setState(UserState.getNameByState(user.getState()));
                voList.add(vo);
            }
            result.modifyResult(true, voList, "用户列表查询成功");
        } catch (ServiceException | ValidateParamsException e) {
            log.warn("查询用户异常  ", e);
            result.modifyResult(false, e.getMessage());
        } catch (Exception e) {
            log.error("查询失败  ", e);
            result.modifyResult(false, "查询失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/setUserRole")
    public Result setUserRole() {

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyUserRole")
    public Result modifyUserRole() {
        return result;
    }
}
