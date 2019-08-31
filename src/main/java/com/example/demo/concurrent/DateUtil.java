package com.example.demo.concurrent;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 日期工具类,提供时间转换、比较、格式化等各种常用方法
 * 
 * @modificationHistory.
 * <li>kjl 2010-8-27下午04:06:10</li>
 */
@SuppressWarnings("deprecation")
@Slf4j
public class DateUtil {
	public final static int DATE_INTERVAL_DAY = 1; // 日
	public final static int DATE_INTERVAL_WEEK = 2; // 周
	public final static int DATE_INTERVAL_MONTH = 3; // 月
	public final static int DATE_INTERVAL_YEAR = 4; // 年
	public final static int DATE_INTERVAL_HOUR = 5; // 小时
	public final static int DATE_INTERVAL_MINUTE = 6; // 分钟
	public final static int DATE_INTERVAL_SECOND = 7; // 秒
	public final static String DATE_FORMAT_YMD = "yyyy-MM-dd";
	public final static String DATE_FORMAT_YMDHMS = "yyyy/MM/dd HH:mm:ss";
	public final static String DATE_FORMAT_DMY = "dd/MM/yyyy";
	public final static String DATE_FORMAT_MD = "MM/dd";
	public final static String DATE_FORMAT_YMD_SLASH = "yyyy/MM/dd";
	public final static String DATE_FORMAT_YMD_ZH = "yyyy年MM月dd日 ";
	public final static String DATE_FORMAT_DMY_EN = "dd-MMM-yyyy";
	public final static String DATE_FORMAT_YMD_DOT = "yyyy.MM.dd";
	public final static String DATE_FORMAT_YMD_NOSIGN = "yyyyMMdd";
	public final static String DATE_FORMAT_YM_NOSIGN = "yyyyMM";
	public final static String DATE_FORMAT_YMDHHMMSS_NOSIGN = "yyyyMMddHHmmss";
	public final static String DATE_FORMAT_DMY_EN_INFO = "dd.MMMM yyyy";
	public final static String DATE_FORMAT_YYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMAT_YYMMDDHHMM = "yyyy-MM-dd HH:mm";
	public final static String DATE_FORMAT_HMS = "HH:mm:ss";
	public final static String DATE_FORMAT_YMD_YMD = "yyyy年MM月dd日";
	public final static String DATE_FORMAT_YMD_YMD_DETAIL = "yyyy年MM月dd日 HH:mm:ss";
	public final static String DATE_FORMAT_YMD_YMD_MINUTE = "yyyy年MM月dd日 HH:mm";
	public final static String DATE_FORMAT_MINUTE = "HH:mm";
	public final static String DATE_FORMAT_YMD_YM = "yyyy年	M月";

	/**
	 * 获得当前日期(年月日)
	 * 
	 * @author kjl @creationDate. 2010-8-27 下午05:11:23
	 * @return 当前时间（yyyy-MM-dd）
	 */
	public static Date getNowDate() {
		return dateFormat(dateFormat(new Date(), "yyyy-MM-dd"), DATE_FORMAT_YMD);
	}

	/**
	 * 获取系统当前日期，格式：yyyy-MM-dd 字符类型
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(DATE_FORMAT_YMD);
		return df.format(date);
	}

	/**
	 * 获取当前时间字符串(年月日)
	 * 
	 * @author kjl @creationDate. 2011-5-4 下午08:22:34
	 * @return 时间字符串
	 */
	public static String getNowStringDate() {
		return dateFormat(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取当前时间字符串(年月)
	 * 
	 * @author kjl @creationDate. 2011-5-4 下午08:22:34
	 * @return 时间字符串
	 */
	public static String getNowStringYMCDate(Date date) {
		return dateFormat(date, DATE_FORMAT_YMD_YM);
	}

	/**
	 * 获取当前时间字符串(年月日时分秒)
	 * 
	 * @author kjl @creationDate. 2011-5-4 下午08:22:34
	 * @return 时间字符串
	 */
	public static String getNowHMSDate() {
		return dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得当前时间(年月日时分秒)
	 * 
	 * @author kjl @creationDate. 2010-8-27 下午05:12:57
	 * @return 当前时间（yyyy-MM-dd HH:mm:ss）
	 */
	public static Date getNowTime() {
		return dateFormat(new Date().toLocaleString(), DATE_FORMAT_YMDHMS);
	}

	/**
	 * 获得明天的日期字符串(格式年月日)
	 * 
	 * @author kjl @creationDate. 2011-5-4 下午08:19:28
	 * @return 明天的日期
	 */
	public static String getTomorrowStringDate() {
		return dateFormat(getTomorrowTime(), "yyyy-MM-dd");
	}

	/**
	 * 获得明天的时间(年月日时分秒)
	 * 
	 * @author kjl @creationDate. 2011-5-4 下午08:20:19
	 * @return 明天的时间
	 */
	public static Date getTomorrowTime() {
		return dateAdd(1, getNowTime(), 1);
	}

	/**
	 * 取得当前星期几
	 * 
	 * @author kjl @creationDate. 2010-9-20 下午05:34:36
	 * @param date
	 *            时间
	 * @return 星期
	 */
	public static String getWeekOfDate(Date date) {
		if (date == null)
			return null;
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 时间类型转换返回字符串
	 * 
	 * @author kjl @creationDate. 2010-8-27 下午05:18:37
	 * @param date
	 *            时间
	 * @param dateFormat
	 *            转换的时间格式
	 * @return 转换后的时间字符串
	 */
	@SuppressWarnings("unused")
	public static String dateFormat(Date date, String dateFormat) {
		if (date == null)
			return null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			if (date != null) {
				return format.format(date);
			}
		} catch (Exception ex) {
			return null;
		}
		return "";
	}

	/**
	 * 英文日期
	 * 
	 * @author lxl
	 * @date 2016-09-23
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String dateFormatEn(Date date, String dateFormat) {
		if (date == null)
			return null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
			if (date != null) {
				return format.format(date);
			}
		} catch (Exception ex) {
		}
		return "";
	}

	/**
	 * 字符串时间类型转换返回Date类型
	 * 
	 * @author kjl @creationDate. 2010-8-27 下午05:23:35
	 * @param date
	 *            字符串时间
	 * @param dateFormat
	 *            转换的时间格式
	 * @return 转换后的时间
	 */
	public static Date dateFormat(String date, String dateFormat) {
		if (StringUtils.isBlank(date))
			return null;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		if (date != null) {
			try {
				return format.parse(date);
			} catch (Exception e) {
				e.printStackTrace();
				// log.error(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 加时间
	 * 
	 * @author kjl @creationDate. 2010-8-27 下午05:28:06
	 * @param interval
	 *            增加项，可以是天数、月份、年数、时间、分钟、秒
	 * @param date
	 *            时间
	 * @param num
	 *            加的数目
	 * @return 时间加后的时间
	 */
	public static Date dateAdd(int interval, Date date, int num) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (interval) {
		case DATE_INTERVAL_DAY:
			calendar.add(Calendar.DATE, num);
			break;
		case DATE_INTERVAL_WEEK:
			calendar.add(Calendar.WEEK_OF_MONTH, num);
			break;
		case DATE_INTERVAL_MONTH:
			calendar.add(Calendar.MONTH, num);
			break;
		case DATE_INTERVAL_YEAR:
			calendar.add(Calendar.YEAR, num);
			break;
		case DATE_INTERVAL_HOUR:
			calendar.add(Calendar.HOUR, num);
			break;
		case DATE_INTERVAL_MINUTE:
			calendar.add(Calendar.MINUTE, num);
			break;
		case DATE_INTERVAL_SECOND:
			calendar.add(Calendar.SECOND, num);
			break;
		default:
		}
		return calendar.getTime();
	}

	/**
	 * 两个时间时间差[前面时间和比较时间比,小于比较时间返回负数]
	 * 
	 * @author kjl @creationDate. 2010-8-27 下午05:26:13
	 * @param interval
	 *            比较项，可以是天数、月份、年数、时间、分钟、秒
	 * @param date
	 *            时间
	 * @param compare
	 *            比较的时间
	 * @return 时间差(保留两位小数点,小数点以后两位四舍五入)
	 */
	public static double getDateDiff(int interval, Date date, Date compare) {
		if (date == null || compare == null)
			return 0;
		double result = 0;
		double time = 0;
		Calendar calendar = null;
		switch (interval) {
		case DATE_INTERVAL_DAY:
			time = date.getTime() - compare.getTime();
			result = time / 1000 / 60 / 60 / 24;
			break;
		case DATE_INTERVAL_HOUR:
			time = date.getTime() - compare.getTime();
			result = time / 1000 / 60 / 60;
			break;
		case DATE_INTERVAL_MINUTE:
			time = date.getTime() / 1000 / 60;
			result = time - compare.getTime() / 1000 / 60;
			break;
		case DATE_INTERVAL_MONTH:
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			time = calendar.get(Calendar.YEAR) * 12;
			calendar.setTime(compare);
			time -= calendar.get(Calendar.YEAR) * 12;
			calendar.setTime(date);
			time += calendar.get(Calendar.MONTH);
			calendar.setTime(compare);
			result = time - calendar.get(Calendar.MONTH);
			break;
		case DATE_INTERVAL_SECOND:
			time = date.getTime() - compare.getTime();
			result = time / 1000;
			break;
		case DATE_INTERVAL_WEEK:
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			time = calendar.get(Calendar.YEAR) * 52;
			calendar.setTime(compare);
			time -= calendar.get(Calendar.YEAR) * 52;
			calendar.setTime(date);
			time += calendar.get(Calendar.WEEK_OF_YEAR);
			calendar.setTime(compare);
			result = time - calendar.get(Calendar.WEEK_OF_YEAR);
			break;
		case DATE_INTERVAL_YEAR:
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			time = calendar.get(Calendar.YEAR);
			calendar.setTime(compare);
			result = time - calendar.get(Calendar.YEAR);
			break;
		default:
			break;
		}
		return new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 获取时间差[前面时间和比较时间比,小于比较时间返回负数]
	 * 
	 * @author kjl @creationDate. 2010-9-1 下午04:36:07
	 * @param level
	 *            返回时间等级(1:返回天;2:返回天-小时;3:返回天-小时-分4:返回天-小时-分-秒;)
	 * @param date
	 *            时间
	 * @param compare
	 *            比较的时间
	 * @return 时间差(保留两位小数点,小数点以后两位四舍五入)
	 */
	public static String getDateBetween(Integer level, Date date, Date compare) {
		if (date == null || compare == null)
			return null;
		long s = new BigDecimal(getDateDiff(DATE_INTERVAL_SECOND, date, compare)).setScale(2, BigDecimal.ROUND_HALF_UP).longValue();
		int ss = 1;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = s / dd;
		long hour = (s - day * dd) / hh;
		long minute = (s - day * dd - hour * hh) / mi;
		long second = (s - day * dd - hour * hh - minute * mi) / ss;
		String flag = (day < 0 || hour < 0 || minute < 0 || second < 0) ? "-" : "";
		day = Math.abs(day);
		hour = Math.abs(hour);
		minute = Math.abs(minute);
		second = Math.abs(second);
		StringBuffer result = new StringBuffer(flag);
		switch (level) {
		case 1:
			if (day != 0)
				result.append(day).append("天");
			break;
		case 2:
			if (day != 0)
				result.append(day).append("天");
			if (hour != 0)
				result.append(hour).append("小时");
			break;
		case 3:
			if (day != 0)
				result.append(day).append("天");
			if (hour != 0)
				result.append(hour).append("小时");
			if (minute != 0)
				result.append(minute).append("分");
			break;
		case 4:
			if (day != 0)
				result.append(day).append("天");
			if (hour != 0)
				result.append(hour).append("小时");
			if (minute != 0)
				result.append(minute).append("分");
			if (second != 0)
				result.append(second).append("秒");
			break;
		default:
			break;
		}
		return result.toString();
	}

	/**
	 * 时间是否是今天
	 * 
	 * @author kjl @creationDate. 2011-5-4 下午08:24:58
	 * @param date
	 *            时间
	 * @return 布尔
	 */
	public static boolean isToday(Date date) {
		if (date == null)
			return false;
		return getNowStringDate().equals(dateFormat(date, "yyyy-MM-dd"));
	}

	/**
	 * 得到本周是一年中的第几周()
	 * 
	 * @author kjl @creationDate. 2012-9-26 下午6:45:42
	 * @return
	 * @throws Exception
	 */
	public static int getYearWeekNum() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int weekNum = calendar.get(Calendar.WEEK_OF_YEAR) + getYear() * 100;
		// 第一周的时候有可能是去年的
		if (calendar.get(Calendar.WEEK_OF_YEAR) == 1) {
			int day = calendar.get(Calendar.DAY_OF_YEAR);
			if (day > 100) {
				return weekNum + 100;
			}
		}
		return weekNum;
	}

	public static int getWeekNumByTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int weekNum = calendar.get(Calendar.WEEK_OF_YEAR) + calendar.get(Calendar.YEAR) * 100;
		// 第一周的时候有可能是去年的
		if (calendar.get(Calendar.WEEK_OF_YEAR) == 1) {
			int day = calendar.get(Calendar.DAY_OF_YEAR);
			if (day > 100) {
				return weekNum + 100;
			}
		}
		return weekNum;
	}

	/**
	 * 得到现在的年份
	 * 
	 * @author kjl @creationDate. 2012-9-26 下午6:45:42
	 * @return
	 * @throws Exception
	 */
	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 得到年月
	 * 
	 * @author kjl @creationDate. 2012-9-26 下午6:45:42
	 * @return
	 * @throws Exception
	 */
	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) * 100 + calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到上月的年月
	 * 
	 * @author kjl @creationDate. 2013-1-3 下午2:38:38
	 * @return
	 */
	public static int getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		return Integer.valueOf(dateFormat(calendar.getTime(), "yyyyMM"));
	}

	/**
	 * 得到某个时间的年月
	 * 
	 * @author kjl @creationDate. 2013-1-21 下午7:12:46
	 * @param date
	 * @return
	 */
	public static int getDateMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return Integer.valueOf(dateFormat(calendar.getTime(), "yyyyMM"));
	}

	/**
	 * 获取日期整型
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateInt(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return Integer.valueOf(dateFormat(calendar.getTime(), "yyyyMMdd"));
	}

	/**
	 * 得到某个时间的月数
	 * 
	 * @author kjl @creationDate. 2013-1-21 下午7:12:46
	 * @param date
	 * @return
	 */
	public static int getDateMonthMM(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		return Integer.valueOf(dateFormat(calendar.getTime(), "MM"));
	}

	/**
	 * 根据时间得到时间在一年中的第几周(它的上一周)
	 * 
	 * @author kjl @creationDate. 2013-1-21 下午1:27:16
	 * @param date
	 * @return
	 */
	public static int getLastWeekNumByTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.SATURDAY);
		cal.setTime(date);
		cal.add(Calendar.DATE, -7);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到某个时间点的年份
	 * 
	 * @author kjl @creationDate. 2013-4-2 上午9:17:05
	 * @param date
	 * @return
	 */
	public static int getYearNumByTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		cal.add(Calendar.DATE, -7);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 得到字符串时间的秒数的字符串
	 * 
	 * @author kjl
	 * @date 2015-12-12
	 * 
	 * @param date
	 * @return
	 */
	public static String getStrFromStringDate(String date) {
		Integer a = getIntFromStringDate(date);
		if (a != null) {
			return String.valueOf(a);
		}
		return null;
	}

	/**
	 * 得到字符串时间的秒数
	 * 
	 * @author kjl
	 * @date 2015-12-12
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getIntFromStringDate(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		try {
			Date time = dateFormat(date, DATE_FORMAT_YMD);
			if (time != null) {
				return (int) (time.getTime() / 1000);
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static Integer getIntFromStringDateYMS(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		try {
			Date time = dateFormat(date, DATE_FORMAT_YYMMDDHHMMSS);
			if (time != null) {
				return (int) (time.getTime() / 1000);
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static Integer getIntNowTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 返回日期的开始时间 传入2016-10-25 ，返回 2016-10-25 00:00:00,
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayStart(String date) {

		Date start = getStart(date);

		return dateFormat(start, DATE_FORMAT_YYMMDDHHMMSS);
	}

	/**
	 * 返回日期的开始时间 传入2016-10-25 ，返回 2016-10-25 00:00:00,
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStart(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD);

		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(format.parse(date));
		} catch (ParseException e) {
			// log.error("date parse eeror.", e);
			return null;
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	public static int getStart(Integer time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date((long) time * 1000));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return (int) (calendar.getTime().getTime() / 1000);
	}

	public static int getEnd(Integer time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date((long) time * 1000));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return (int) (calendar.getTime().getTime() / 1000);
	}

	public static String getToday() {
		return dateFormat(new Date(), DATE_FORMAT_YMD);
	}

	/**
	 * 返回当天0:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStart(Date date) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 返回日期的结束时间 传入2016-10-25 ，返回 2016-10-25 23:59:59,
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayEnd(String date) {

		Date start = getEnd(date);

		return dateFormat(start, DATE_FORMAT_YYMMDDHHMMSS);
	}

	/**
	 * 返回日期的结束时间 传入2016-10-25 ，返回 2016-10-25 23:59:59,
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEnd(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD);

		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(format.parse(date));
		} catch (ParseException e) {
			// log.error("date parse eeror.", e);
			return null;
		}
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	/**
	 * 比较两个天数
	 */
	public static int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	/**
	 * 获取当年的最后一天
	 * 
	 * @return
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @return
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 返回日期的结束时间 传入2016-10-25 ，返回 2016-10-25 23:59:59,
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEnd(Date date) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	public static Integer getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return (int) (calendar.getTime().getTime() / 1000);
	}

	public static Date getPerFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 特殊的时间格式化
	 *
	 * @param date
	 *            原时间
	 * @return 格式化后的时间
	 */
	public static String specialFormatTime(Date date) {
		if (DateUtil.isToday(date)) {
			return DateUtil.dateFormat(date, "HH:mm");
		}
		return DateUtil.dateFormat(date, "MM-dd HH:mm");
	}

	public static String formatToMinutes(Integer time) {
		Long longTime = (long) time * 1000;
		Date date = new Date(longTime);
		return getTitleDay(date) + DateUtil.dateFormat(date, "HH:mm");
	}

	public static String formatToDay(Integer time) {
		Long longTime = (long) time * 1000;
		Date date = new Date(longTime);
		return dateFormat(date, "MM月dd日");
	}

	public static String formatToDayNew(Integer time) {
		Long longTime = (long) time * 1000;
		Date date = new Date(longTime);
		return dateFormat(date, "MM-dd");
	}

	public static String formatToYMDHSDay(Integer time) {
		Long longTime = (long) time * 1000;
		Date date = new Date(longTime);
		return dateFormat(date, "yyyy-MM-dd HH:mm");
	}

	public static String formatToMonth(Integer time) {
		Long longTime = (long) time * 1000;
		Date date = new Date(longTime);
		return dateFormat(date, "MM月dd日 HH:mm");
	}

	public static String formatToSimgl(Integer time) {
		Long longTime = (long) time * 1000;
		Date date = new Date(longTime);
		return dateFormat(date, DATE_FORMAT_YMD);
	}

	public static String formatToSimglDetail(Integer time) {
		Long longTime = (long) time * 1000;
		Date date = new Date(longTime);
		return dateFormat(date, DATE_FORMAT_YYMMDDHHMM);
	}

	public static String formatToDetail(Integer time) {
		Long longTime = (long) time * 1000;
		Date date = new Date(longTime);
		return dateFormat(date, DATE_FORMAT_YYMMDDHHMMSS);
	}

	public static String formatToFormat(Integer time, String format) {
		Long longTime = (long) time * 1000;
		Date date = new Date(longTime);
		return dateFormat(date, format);
	}

	private static final int YESTERDY = -1;
	private static final int TODAY = 0;
	private static final int TOMORROWDAT = 1;
	private static final int OTHER_DAY = 10000;

	/**
	 * 调用显示日期
	 */
	public static String getTitleDay(Date date) {
		try {
			switch (JudgmentDay(date)) {
			case YESTERDY: {
				return "昨天";
			}
			case TODAY: {
				return "";
			}
			case TOMORROWDAT: {
				return "明天";
			}
			default:
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				return cal.get(Calendar.DAY_OF_MONTH) + "日";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getTitleDayA(Date date) {
		try {
			switch (JudgmentDay(date)) {
			case YESTERDY: {
				return "昨天";
			}
			case TODAY: {
				return "今天";
			}
			case TOMORROWDAT: {
				return "明天";
			}
			default:
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				return cal.get(Calendar.DAY_OF_MONTH) + "日";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 判断日期(效率比较高)
	 */
	public static int JudgmentDay(Date date) throws ParseException {
		Calendar pre = Calendar.getInstance();
		Date predate = new Date(System.currentTimeMillis());
		pre.setTime(predate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
			int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);

			switch (diffDay) {
			case YESTERDY: {
				return YESTERDY;
			}
			case TODAY: {
				return TODAY;
			}
			case TOMORROWDAT: {
				return TOMORROWDAT;
			}
			}
		}
		return OTHER_DAY;
	}

	/**
	 * 获取今天星期几 星期天 day=1 星期1 day=2
	 * 
	 * @return
	 */
	public static Integer getNowWeek() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return day;
	}

	/**
	 * 获取月份最后日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMaxMonthDate(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YMD);
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * jia
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date switchDateByInt(Long date) throws ParseException {
		long nowTimeLong = new Long(date).longValue() * 1000;
		DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTimeStr = ymdhmsFormat.format(nowTimeLong);
		Date nowTimeDate = ymdhmsFormat.parse(nowTimeStr);
		return nowTimeDate;
	}

	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前时间的月份
	 * 
	 * @return
	 */
	public static int getNowMonthOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	public static Integer getTodayZeroIntTime() {
		long current = System.currentTimeMillis();// 当前时间毫秒数
		long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();// 今天零点零分零秒的毫秒数
		return (int) (zero / 1000);
	}

	public static Integer getTodayEndIntTime() {
		long current = System.currentTimeMillis();// 当前时间毫秒数
		long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();// 今天零点零分零秒的毫秒数
		long twelve = zero + 24 * 60 * 60 * 1000 - 1;// 今天23点59分59秒的毫秒数
		return (int) (twelve / 1000);
	}

	public static int getTodayInt() {
		return Integer.valueOf(DateUtil.dateFormat(new Date(), DateUtil.DATE_FORMAT_YMD_NOSIGN));
	}
	
	public static int getTodayMonthInt() {
		return Integer.valueOf(DateUtil.dateFormat(new Date(), DateUtil.DATE_FORMAT_YM_NOSIGN));
	}
	
	public static Long getTodayDetailInt() {
		return Long.valueOf(DateUtil.dateFormat(new Date(), DateUtil.DATE_FORMAT_YMDHHMMSS_NOSIGN));
	}
	
	public static int getYesterTodayInt() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return Integer.valueOf(DateUtil.dateFormat(calendar.getTime(), DateUtil.DATE_FORMAT_YMD_NOSIGN));
	}

	/**
	 * 获取系统昨天日期，格式：yyyyMMdd 字符类型
	 * 
	 * @return
	 */
	public static String getYesterDateFormatter() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		return new SimpleDateFormat(DATE_FORMAT_YMD_NOSIGN).format(time);
	}

	/**
	 * 获取系统昨天日期，格式：yyyy-MM-dd 字符类型
	 * 
	 * @return
	 */
	public static String getYesterDateNewFormatter() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		return new SimpleDateFormat(DATE_FORMAT_YMD).format(time);
	}

	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getIntDayFromTime(Integer time) {
		return Integer.valueOf(DateUtil.dateFormat(new Date(((long) time) * 1000), DateUtil.DATE_FORMAT_YMD_NOSIGN));
	}

	public static Date getWeekStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	public static String formatSSMM(String videoTime) {
		try {
			Integer time =Integer.valueOf(videoTime);
			Integer minute=time/60;
			Integer second=time%60;
			return minute+":"+second;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println(DateUtil.getEnd(DateUtil.getIntNowTime())-DateUtil.getIntNowTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}