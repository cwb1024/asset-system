package com.chengwenbi.controller;

import com.alibaba.fastjson.JSONObject;
import com.chengwenbi.common.Result;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.constant.SessionConstants;
import com.chengwenbi.constant.StateConstants;
import com.chengwenbi.controller.base.BaseController;
import com.chengwenbi.domain.dto.RoleDTO;
import com.chengwenbi.domain.dto.UserRoleDTO;
import com.chengwenbi.domain.entity.RoleDO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.domain.vo.RoleVO;
import com.chengwenbi.service.RoleService;
import com.chengwenbi.service.UserService;
import com.chengwenbi.util.StringUtil;
import com.chengwenbi.util.ValidParamUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/role")
public class RoleController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    /*
     name,remark
     */
    @ResponseBody
    @RequestMapping("add")
    public Result addRole(HttpSession session, RoleDTO roleDTO) {
        try {
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            //DTO 转 DO
            RoleDO roleDO = new RoleDO();
            BeanUtils.copyProperties(roleDO, roleDTO);
            String id = StringUtil.uuid();
            roleDO.setId(id);
            roleDO.setCreateId(userDO.getId());
            roleDO.setCreateName(userDO.getName());
            roleDO.setCreateTime(new Date());
            roleDO.setModifyId(userDO.getId());
            roleDO.setModifyName(userDO.getName());
            roleDO.setModifyTime(new Date());
            roleDO.setState(StateConstants.NORMAL);
            roleService.add(roleDO);
            result.modifyResult(true,roleDTO,"添加成功");
        } catch (Exception e) {
            log.error("添加失败",e);
            result.modifyResult(false,e.getMessage());
        }
        return result;
    }


    @RequestMapping("/modify")
    @ResponseBody
    public Result modifyRole(HttpSession session, RoleDTO roleDTO) {

        return result;
    }

    @RequestMapping("/drop")
    @ResponseBody
    public Result dropRole(HttpSession session, RoleDTO roleDTO) {
        try {
            ValidParamUtil.validNotNull(roleDTO.getName());
            //DTO 转 DO
            RoleDO roleDO = new RoleDO();
            BeanUtils.copyProperties(roleDO, roleDTO);
            roleDO.setState(StateConstants.BLOCKED);
            roleService.modify(roleDO);
        } catch (ServiceException e) {
            log.warn("删除警告失败", e);
            result.modifyResult(false, "删除异常");
        } catch (Exception e) {
            log.error("删除失败", e);
            result.modifyResult(false, "删除失败");
        }
        return result;
    }

    /**
     * 用户同时只拥有一种身份，一种身份对应于多种权限，一种权限可能对应于一种或者多种菜单展示
     * @param session
     * @return
     */
    @RequestMapping("/auth")
    @ResponseBody
    public Result authRole(HttpSession session, String userId,String jsonStr) {
        try {
            ValidParamUtil.validNotNull(userId, jsonStr);
            //利用fastJson 把权限id取出来
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            String jsonArr = jsonObject.getJSONArray("jsonStr").toJSONString();
            List<String> list = JSONObject.parseArray(jsonArr, String.class);
            List<UserRoleDTO> authorityList = new ArrayList<>();
            for (String roleId : list) {
                UserRoleDTO userRoleDTO = new UserRoleDTO();
                userRoleDTO.setId(StringUtil.uuid());
                userRoleDTO.setRoleId(roleId);
                userRoleDTO.setUserId(userId);
                authorityList.add(userRoleDTO);
            }
            //维护用户权限之间的对应关系

        } catch (Exception e) {

        }

        return result;
    }

    @RequestMapping("/findRole")
    @ResponseBody
    public Result findRole(RoleDTO roleDTO) {
        try {
            List<RoleDO> roleDOList = roleService.findByParams(roleDTO);
            //DO 转 VO
            RoleVO roleVO = new RoleVO();
            List<RoleVO> roleVOS = new ArrayList<>();
            for (RoleDO role :roleDOList ) {
                BeanUtils.copyProperties(roleVO, role);
                roleVOS.add(roleVO);
            }
            result.modifyResult(true, roleVOS, "获取权限列表成功");
        } catch (ServiceException e) {
            log.warn("获取权限列表警告异常", e);
            result.modifyResult(false, "获取权限列表警告异常");
        } catch (Exception e) {
            log.error("获取权限列表失败", e);
            result.modifyResult(false, "获取权限列表失败");
        }
        return result;
    }

}
