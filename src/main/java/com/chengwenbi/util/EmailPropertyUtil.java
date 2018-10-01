package com.chengwenbi.util;


import java.io.*;
import java.util.Properties;


/**
 * 读取邮箱配置文件
 */
public class EmailPropertyUtil {
	private static String propUrl = "mailConfig.properties";
	static Properties prop = new Properties();
	static {
		try {
			InputStream in = EmailPropertyUtil.class.getResourceAsStream("/"+propUrl);
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据name获得配置文件中的值
	 * @param name
	 * @return
	 */
	public static String getString(String name) {
		return prop.getProperty(name);
	}

}
