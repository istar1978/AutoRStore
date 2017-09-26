package com.zhaozhy.autorstore.util;

import org.apache.commons.logging.Log;

import com.zhaozhy.autorstore.exception.DataNotFoundException;

/**
 * @Title				ExceptionUtil.java
 * @Package		com.zhaozhy.autorstore.util
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-10   下午08:12:56
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ExceptionUtil {

	/**
	 * 
	 * @CreateDate	2017-6-10  下午08:16:31
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					判断取出的对象是否null，若是，抛出DataNotFoundException
	 * @param o
	 * @param msg
	 * @param log
	 */
	public static void throwDataNotFoundException(Object o,String msg,Log log){
		if(o==null){
			DataNotFoundException ex=new DataNotFoundException("");
			System.out.println(msg);
			log.error(msg, ex);
			throw ex;
		}
	}
}
