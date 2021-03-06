package com.chengwenbi.controller;

import com.chengwenbi.common.PageBean;
import com.chengwenbi.common.Result;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.common.exception.ValidateParamsException;
import com.chengwenbi.config.BaseItemMap;
import com.chengwenbi.config.ConfigMap;
import com.chengwenbi.constant.SessionConstants;
import com.chengwenbi.constant.StateConstants;
import com.chengwenbi.controller.base.BaseController;
import com.chengwenbi.domain.dto.UserDTO;
import com.chengwenbi.domain.entity.MenuDO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.domain.vo.MenuVO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/api/user")
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
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userVO, data);
            result.modifyResult(true, userVO,"登录成功");
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
            qo.setCreateId(userDO.getId());
            qo.setCreateName(userDO.getName());
            qo.setCreateTime(new Date());
            //状态默认未删除
            BeanUtils.copyProperties(qo, userDTO);
            qo.setId(StringUtil.uuid());
            //设置默认密码
            qo.setPassword(MD5Util.getMD5("111111"));
            qo.setIdentityId("1");
            qo.setIdentityName("普通用户");
            qo.setState(StateConstants.NORMAL);
            //验证邮箱是否已经存在
            userService.verifyEmail(userDTO.getEmail());
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
            qo.setModifyId(userDO.getId());
            qo.setModifyName(userDO.getName());
            qo.setModifyTime(new Date());
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
            qo.setState(StateConstants.BLOCKED);
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
            PageBean<UserDO> pageBean = new PageBean<>(userList);
            //do 转 vo
            List<UserVO> voList = new ArrayList<>();
            for (UserDO user : userList) {
                UserVO vo = new UserVO();
                BeanUtils.copyProperties(vo,user);
                vo.setState(StateConstants.getNameByState(user.getState()));
                voList.add(vo);
            }
            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("data", voList);
            map.put("total", pageBean.getTotal());
            map.put("pageSize", pageBean.getPageSize());
            map.put("pageNo", pageBean.getPageNum());
            map.put("page", pageBean.getPages());
            result.modifyResult(true, map, "用户列表查询成功");
        } catch (ServiceException | ValidateParamsException e) {
            log.warn("查询用户异常  ", e);
            result.modifyResult(false, e.getMessage());
        } catch (Exception e) {
            log.error("查询失败  ", e);
            result.modifyResult(false, "查询失败");
        }
        return result;
    }

    /**
     * 参数：identifyId，email,设置用户角色
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setUserRole")
    public Result setUserRole(UserDTO userDTO) {
        String email = userDTO.getEmail();
        try {
            ValidParamUtil.validNotNull(email, userDTO.getIdentityId());
            UserDO userDO = userService.findByEmail(userDTO);
            BeanUtils.copyProperties(userDO, userDTO);
            userDO.setIdentityName(ConfigMap.getRoleNameById(Integer.valueOf(userDTO.getIdentityId())));
            userService.modify(userDO);
            result.modifyResult(true, "设置用户角色成功");
        } catch (Exception e) {
            log.error("设置用户角色失败",e);
            result.modifyResult(false, "设置用户角色失败");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/findRoleList")
    public Result findRoleList(){
        List<BaseItemMap> roleList = ConfigMap.getRoleList();
        result.modifyResult(true, roleList, "获取角色列表成功");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/findMenu")
    public Result findMenu(String identityId) {
        try {
            List<MenuDO> menu = userService.findMenu(identityId);
            result.modifyResult(true, menu, "获取菜单列表成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
