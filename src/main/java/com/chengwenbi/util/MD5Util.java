package com.chengwenbi.util;

import org.apache.commons.lang3.StringUtils;
import java.security.MessageDigest;

public class MD5Util {

	/**
	 * 获取md5 32位码
	 * @param s
	 * @return
	 */
	public final static String getMD5(String s){
		
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd','e', 'f'};
		try {
			byte[] strTemp = s.getBytes("utf-8");
			
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>>4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e){
			return null;
		}
	}

	public static String get16MD5(String str){
		if(StringUtils.isBlank(str)){
			return "";
		}
	    return getMD5(str).substring(8, 24);
	}

}
