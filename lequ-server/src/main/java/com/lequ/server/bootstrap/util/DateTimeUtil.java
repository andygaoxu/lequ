package com.lequ.server.bootstrap.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author gaoxu
 *
 */
public class DateTimeUtil {
	
	// 一秒的毫秒数
	public static final long ONE_SECOND = 1000L;
	// 一分钟的的毫秒数
	public static final long ONE_MINUTE = 60 * 1000L;
	// 一小时的毫秒数
	public static final long ONE_HOUR = 60L * 60L * 1000L;
	//一天的毫秒数
	public static final long ONE_DAY = 24 * 60 * 60 * 1000L;
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdfF = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 格式化日期与时间
	 * @param postTime
	 * @return 返回 yyyy-MM-dd HH:mm:ss 格式的字符串
	 */
	public static String DateToString(Date postTime) {
		String dateString = "";
		if (postTime != null)
			dateString = sdf.format(postTime);
		return dateString;
	}
	/**
	 * 格式化日期与时间
	 * @param postTime
	 * @return 返回 yyyyMMddHHmmss 格式的字符串
	 */
	public static String DateToYYMMDDHHMMSS() {
		return sdfF.format(new Date());
	}
	
	public static void main(String[] prar){		
		long start =  System.currentTimeMillis();
		DateToYYMMDDHHMMSS();
		long end = System.currentTimeMillis();
		System.out.println("shi chang "+(end-start));
	}
	/**将字符串转化为时间
	 * @param dateStr 要转化的字符串
	 * @param pattern SimpleDateFormat模板
	 * @return 转化后的时间
	 * @throws Exception
	 */
	public static Date strToDate(String dateStr ,String pattern) throws Exception {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		return sdf.parse(dateStr);
	}
	/**将字符串转化为时间
	 * @param dateStr 要转化的字符串
	 * @param pattern SimpleDateFormat模板
	 * @return 转化后的时间
	 * @throws Exception
	 */
	public static Date strToDate(String dateStr) throws Exception {

		return sdf.parse(dateStr);
	}

	/**日期转化为字符串
	 * @param date 需要转换的日期
	 * @param sdf 格式化模板
	 * @return 返回传入模板的日期和时间
	 */
	public static String DateToString(Date date, String sdf) {
		DateFormat format = new SimpleDateFormat(sdf);
		String string = null;
		if (date == null) {
			return string;
		}
		return format.format(date);
	}
	/**日期增加或是减少整数天
	 * @param date 传入日期
	 * @param num  增加或是减少的天数，正数是增加，负数是减少
	 * @return 移动后的日期
	 */
	public static Date dateMoreOrLess(Date date, int num) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);// 把日期往后增加一天.正数往后增加,负数往前减少
		date = calendar.getTime();

		return date;
	}
	/**检查有效生命周期
	 * @author gaoxu
	 * @param modifyTime
	 * @param lifecycleTime
	 * @return true 有效，false 无效（说明过期）
	 */
	public static boolean isValidLifecycle(long modifyTime,long lifecycleTime){
		boolean result = false;
		long currentTime = System.currentTimeMillis();
		if(currentTime-modifyTime>lifecycleTime){
			result = false;
		}else{
			result =  true;
		}
		return result ;
	}
}
