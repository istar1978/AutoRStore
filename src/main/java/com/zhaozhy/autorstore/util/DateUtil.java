package com.zhaozhy.autorstore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @Title:			DateUtil.java
 * @Package:		com.zhaozhy.autorstore.util
 * @Created：	zhaozhy
 * @Date：			2017-5-26   下午01:29:39
 * @Desc:			TODO 日期工具类
 * @Version: 		V1.0
 *
 * @Modified：
 * @Date：
 * @Desc：
 * 
 * @Email : 		zhongyong@qq.com
 */
public class DateUtil {

	private static SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmssS");
	private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf3=new SimpleDateFormat("yyyyMMdd");
	/**
	 * 
	 * 创建时间：2017-5-26  下午01:37:37
	 * 	创建者：zhaozhy
	 *	方法说明：取得当前时间的17位显示，实例：20170526133712540
	 * @return
	 */
	public static String getDateString17(){
		String dateStr=sdf1.format(new Date());
		return StringUtils.rightPad(dateStr,17,"0");
	}
	
	public static Date parseString2Date(String dateStr){
//		DateUtils.parseDate("", "");
		return null;
	}
	/**
	 * 
	 * @CreateDate	2017-6-21  上午11:08:08
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					格式化年月份字符串 如：20171201-->2017-12-01
	 * @param date
	 * @return
	 */
	public static String format8To10(String date){
		return date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
	}
	/**
	 * 
	 * @CreateDate	2017-7-8  上午08:23:55
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					格式化年月日字符串 形如：2017-01-01-->20170101
	 * @param date
	 * @return
	 */
	public static String format10To8(String date){
		return date.replaceAll("-", "");
	}
	/**
	 * 
	 * @CreateDate	2017-6-21  上午11:10:50
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					格式化时间字符串 如：131132-->13:11:32
	 * @param time
	 * @return
	 */
	public static String format6To8(String time){
		return time.substring(0,2)+":"+time.substring(2,4)+":"+time.substring(4,6);
	}
	
}
