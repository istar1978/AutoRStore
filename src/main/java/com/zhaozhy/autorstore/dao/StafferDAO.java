package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.Staffer;
/**
 * 
 * @Title				StafferDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:36:15
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface StafferDAO extends BaseDAO<Staffer>{


	public abstract Staffer findById(String id);

	public abstract List findByStaName(Object staName);

	public abstract List findByStaPwd(Object staPwd);

	public abstract List findByStaPosition(Object staPosition);

	public abstract List findByStaLevel(Object staLevel);

	public abstract List findByDepId(Object depId);

	public abstract List findByBraId(Object braId);

	public abstract List findByStaStat(Object staStat);

	/**
	 * @param id
	 * @return
	 */
	public abstract List findAllByIdLike(String id);

	/**
	 * @param id
	 * @param intPage
	 * @param intPageSize
	 * @return
	 */
	public abstract List queryPageByIdLike(String id, int intPage, int intPageSize);

}