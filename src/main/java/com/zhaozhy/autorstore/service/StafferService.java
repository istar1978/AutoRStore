package com.zhaozhy.autorstore.service;

import java.util.List;

import com.zhaozhy.autorstore.entity.Staffer;

/**
 * 
 * @Title				StafferService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:37:02
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface StafferService extends BaseService<Staffer>{

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
