package com.chengwenbi.util;

import com.chengwenbi.common.exception.ValidateParamsException;
import org.apache.commons.lang3.StringUtils;

/**
 * @description:
 * @author: chengwenbi
 * @date:   2018/1/1 21:24
 */
public class ValidParamUtil {

	public static void validNotNull(Object... params) throws Exception {
		for (Object o : params) {
			if (o == null) {
				throw new ValidateParamsException("必传参数不能为空！");
			}
			if (o instanceof String) {
				if (StringUtils.isBlank((String) o)) {
					throw new ValidateParamsException("必传参数不能为空！");
				}
			}
		}
	}

}
