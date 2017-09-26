package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.Static3;
/**
 * 
 * @Title				Static3DAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:54:20
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface Static3DAO extends BaseDAO<Static3>{


	public abstract Static3 findById(String id);

	public abstract List findByMatName(Object matName);

	public abstract List findByMatRealprice(Object matRealprice);

	public abstract List findByMatPreprice(Object matPreprice);

	public abstract List findByS3Num(Object s3Num);

	public abstract List findByS3Sumprice(Object s3Sumprice);

	public abstract List findByS3Rsumprice(Object s3Rsumprice);

}