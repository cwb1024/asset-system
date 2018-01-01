package com.chengwenbi.util;


import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	private DateUtil() {
	}

	/**
	 * 去掉微秒
	 * @return
	 */
	public static String deleteMicrosecond(String time) {
		try {
			if (StringUtils.isNotBlank(time)) {
				SimpleDateFormat smf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = smf1.parse(time);
				String dateString = smf1.format(date);
				return dateString;
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

	/**
	 * 返回给定日期的后N分钟的日期(精确到秒)
	 */
	public static Date getAddMinute(Date date, int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(format.parse(format.format(date)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}

	/**
	 * @description:  判断两个字符串类型日期的大小
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:26
	 */

	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() >= dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() <= dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * @description:  格式化日期
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:26
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * @description: 格式化时间
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:26
	 */

	public static Date stringToTime(String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(date);
	}

	/**
	 * @description: 格式化时间
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:26
	 */

	public static String dateToString(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * @description: 格式化时间
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:27
	 */

	public static String timeToString(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}

	/**
	 * @description: 格式化时间
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:27
	 */

	public static String dateToString1(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

	public static String dateToString2(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}

	/**
	 * @description: 时间转时间戳
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:27
	 */

	public static Long dateToTimeStamp(String date) throws Exception {
		long timeStamp = stringToTime(date).getTime() / 1000;
		System.out.println(timeStamp);
		return timeStamp;
	}

	/**
	 * @description: 时间转时间戳
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:27
	 */

	public static Date timeStampToDate(Long timeStamp) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(timeStamp);
		Date date = format.parse(d);
		return date;
	}

	/**
	 * @description: 时间戳转字符串时间
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:27
	 */

	public static String timeStampToStrDate(Long timeStamp) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(timeStamp);
		return date;
	}

	/**
	 * @description: 得到给定日期前n周的星期一
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:28
	 */

	public static Date getMonday(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		System.out.println("getFriday():" + date);
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - n);
		Date newdate = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = format.parse(format.format(newdate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * @description: <判断a是否比b大了c毫秒>
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:28
	 */

	public static boolean compareTwoDate(Date a, Date b, long c) {
		long d = a.getTime() - b.getTime();
		if (d > c) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @description: <毫秒装换成天时分秒>
	 * @author: chengwenbi
	 * @date:   2018/1/1 16:28
	 */

	public static String convertLongTime(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		// Long milliSecond = ms - day * dd - hour * hh - minute * mi - second *
		// ss;

		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day + "天 ");
		}
		sb.append(hour + ":");
		if (minute < 10 && minute >= 0) {
			sb.append("0");
		}
		sb.append(minute + ":");
		if (second < 10 && second >= 0) {
			sb.append("0");
		}
		sb.append(second);
		// if (milliSecond > 0) {
		// sb.append(milliSecond + "毫秒");
		// }
		return sb.toString();
	}

}
