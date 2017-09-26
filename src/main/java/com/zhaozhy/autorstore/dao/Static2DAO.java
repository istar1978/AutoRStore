package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.Static2;
/**
 * 
 * @Title				Static2DAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:51:47
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface Static2DAO extends BaseDAO<Static2>{


	public abstract Static2 findById(String id);

	public abstract List findByS2Point(Object s2Point);

	/**
	 * @return
	 */
	public abstract List findAllOrderByVal();

}