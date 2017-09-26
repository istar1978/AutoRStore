package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.DepMenu;
/**
 * 
 * @Title				DepMenuDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:44:12
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface DepMenuDAO extends BaseDAO<DepMenu>{


	public abstract DepMenu findById(com.zhaozhy.autorstore.entity.DepMenuId id);


	public abstract List findByDmStat(Object dmStat);

	/**
	 * 根据dId从部门权限表中查询出所有的符合条件的DepMenu类
	 * 
	 * @param dId
	 * @return
	 */
	public abstract List findByDId(String dId);

}