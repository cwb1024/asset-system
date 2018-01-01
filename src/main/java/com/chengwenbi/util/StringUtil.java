package com.chengwenbi.util;

import java.util.UUID;

public class StringUtil {

    /**
     * 生成uuid
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
