package com.chengwenbi.util;

import com.chengwenbi.common.BaseEntity;
import com.chengwenbi.domain.entity.UserDO;

import java.util.Date;

public class PutUserInfoUtil {

    public static void userInfo(BaseEntity entity,UserDO userDO){
        Date now = new Date();
        entity.setCreateId(userDO.getId());
        entity.setCreateName(userDO.getName());
        entity.setCreateTime(now);
        entity.setModifyId(userDO.getId());
        entity.setModifyName(userDO.getName());
        entity.setModifyTime(now);
    }
}
