package com.chengwenbi.constant;

import com.chengwenbi.util.ValidParamUtil;

public class StateConstants {

    /*
     * normal 正常状态
     */
    public static final int NORMAL = 0;

    /*
     * blocked 冻结状态
     */
    public static final int BLOCKED = 1;

    /*
     * remove 移除状态
     *//*
    public static final int REMOVE =2;*/

    public static String getNameByState(Integer state) throws Exception {
        ValidParamUtil.validNotNull(state);
        String name = "";
        switch (state) {
            case 0:
                name = "正常";
                break;
            case 1:
                name = "冻结";
                break;
/*            case 2:
                name = "移除";
                break;*/
        }
        return name;
    }
}
