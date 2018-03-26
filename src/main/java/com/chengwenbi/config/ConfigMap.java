package com.chengwenbi.config;

import java.util.List;

public class ConfigMap {

    public static List<BaseItemMap> roleList;


    /**
     * 通过id 获取 角色名称
     * @return
     */
    public static String getRoleNameById(Integer id){
        for (BaseItemMap map : roleList) {
            if (id != null && id == map.getId()) {
                return map.getName();
            }
        }
        return null;
    }

    public static List<BaseItemMap> getRoleList() {
        return roleList;
    }

    public static void setRoleList(List<BaseItemMap> roleList) {
        ConfigMap.roleList = roleList;
    }
}
