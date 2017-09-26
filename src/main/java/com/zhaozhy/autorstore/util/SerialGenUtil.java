package com.zhaozhy.autorstore.util;

import org.apache.commons.lang.StringUtils;

import com.zhaozhy.autorstore.entity.SerialGen;
import com.zhaozhy.autorstore.entity.SerialGenId;
import com.zhaozhy.autorstore.service.SerialGenService;

/**
 * 
 * @Title:			SerialGenUtil.java
 * @Package:		com.zhaozhy.autorstore.util
 * @Created：	zhaozhy
 * @Date：			2017-5-26   下午12:55:12
 * @Desc:			TODO 流水号生成工具类
 * @Version: 		V1.0
 *
 * @Modified：
 * @Date：
 * @Desc：
 * 
 * @Email : 		zhongyong@qq.com
 */
public class SerialGenUtil {
	private SerialGenService serialGenService;
	

	public SerialGenUtil(SerialGenService serialGenService) {
		super();
		this.serialGenService=serialGenService;
	}

}
