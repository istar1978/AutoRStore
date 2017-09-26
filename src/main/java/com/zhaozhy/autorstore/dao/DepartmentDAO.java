package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.Department;
/**
 * 
 * @Title				DepartmentDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:35:21
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface DepartmentDAO extends BaseDAO<Department>{


	public abstract Department findById(java.lang.String id);

	public abstract List findByDepName(Object depName);

	public abstract List findByDepType(Object depType);

	public abstract List findByDepStat(Object depStat);

	/**
	 * 取出数据库中所有状态为正常的药店部门的数据
	 * 
	 * @return
	 */
	public abstract List findAllInUsing();

	/**
	 * 取出数据库中所有状态为正常的药店部门的数据(除去管理员组)
	 * 
	 * @return
	 */
	public abstract List findAllInUsingN();

	/**
	 * 取出一页数据，包括id模糊查询
	 * 
	 * @param depId
	 * @param intPage
	 * @param intPageSize
	 * @return
	 */
	public abstract List queryPerPageByIdLike(String depId, int intPage,
			int intPageSize);

	/**
	 * 根据id模糊查询
	 * 
	 * @param depId
	 * @return
	 */
	public abstract List findAllByIdLike(String depId);

}