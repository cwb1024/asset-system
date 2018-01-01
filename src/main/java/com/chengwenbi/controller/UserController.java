package com.chengwenbi.controller;

import com.chengwenbi.common.Result;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.common.exception.ValidateParamsException;
import com.chengwenbi.constant.SessionConstants;
import com.chengwenbi.controller.base.BaseController;
import com.chengwenbi.domain.UserDTO;
import com.chengwenbi.service.UserService;
import com.chengwenbi.util.ValidParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @description:
 * @author: chengwenbi
 * @date:   2017/12/17 13:18
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login")
    public Result login(UserDTO userDTO){
        try {
            ValidParamUtil.validNotNull(userDTO.getEmail(),userDTO.getPassword());
            //验证登录

            //正确存入session
            this.set(SessionConstants.USER_KEY,userDTO);
            result.modifyResult(true,userDTO,"登录成功");
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
    @RequestMapping("/logout")
    public Result logout(){
        try {
            this.clear();
            result.setMessage("退出成功");
        } catch (Exception e) {
            log.error("退出异常", e);
            result.modifyResult(false, "退出失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/modifyPassword")
    public Result modifyPassword(UserDTO userDTO){
        try {
            String newPassword = userDTO.getNewPassword();
            ValidParamUtil.validNotNull(userDTO.getPassword(), newPassword);
            result= userService.modifyPassword(userDTO);
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
    @RequestMapping("/add")
    public Result addUser(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/modify")
    public Result modifyUser(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/drop")
    public Result dropUser(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/find")
    public Result findUser(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/setUserRole")
    public Result setUserRole(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/modifyUserRole")
    public Result modifyUserRole(){
        return result;
    }
}
