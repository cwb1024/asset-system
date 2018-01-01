package com.chengwenbi.util;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件工具类
 * <p>Description:   </p>
 */
public class PropertyUtil {
	private static String propUrl = "prop.properties";
	private static Properties prop = new Properties();
	
	static {
		try {
			InputStream in = new BufferedInputStream
				(new ClassPathResource(propUrl).getInputStream());
			prop.load(in);
			in.close();
		} catch (Exception e) {
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
