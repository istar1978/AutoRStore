package com.zhaozhy.autorstore.service;

import java.util.List;

import com.zhaozhy.autorstore.entity.Static2;

/**
 * 
 * @Title				Static2Service.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:54:46
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface Static2Service extends BaseService<Static2>{

	public abstract Static2 findById(String id);

	public abstract List findByS2Point(Object s2Point);

	/**
	 * @return
	 */
	public abstract List findAllOrderByVal();
}
