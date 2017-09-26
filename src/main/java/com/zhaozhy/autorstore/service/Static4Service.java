package com.zhaozhy.autorstore.service;

import java.util.Date;
import java.util.List;

import com.zhaozhy.autorstore.entity.Static4;
import com.zhaozhy.autorstore.entity.Static4Id;

/**
 * 
 * @Title				Static4Service.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:55:49
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface Static4Service extends BaseService<Static4>{

	public abstract Static4 findById(Static4Id id);

	public abstract List findByBraName(Object braName);

	public abstract List findByS4Allprice(Object s4Allprice);

	public abstract List findByS4Realprice(Object s4Realprice);


	public abstract Date findStatic4Earlise();

	/**
	 * 找出数据库中日期最早的数据返回
	 * 
	 * @return
	 */
	public abstract Static4 findStatic4Earlist();

	/**
	 * 找出数据库中日期最晚的数据返回
	 * 
	 * @return
	 */
	public abstract Static4 findStatic4Last();
}
