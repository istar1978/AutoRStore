package com.zhaozhy.autorstore.service;

import java.util.List;

import com.zhaozhy.autorstore.entity.Branch;

/**
 * 
 * @Title				BranchService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午08:39:35
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface BranchService extends BaseService<Branch>{

	public abstract Branch findById(java.lang.String id);

	public abstract List findByBraName(Object braName);

	public abstract List findByBraLevel(Object braLevel);

	public abstract List findByBraUpid(Object braUpid);

	public abstract List findByBraStat(Object braStat);

	/**
	 * 取出数据库中所有状态为正常的Branch
	 * 
	 * @return
	 */
	public abstract List findAllInUsing();

	/**
	 * 查询所有机构(除去当前登录职员所在的机构)
	 * 
	 * @param branchId
	 * @return
	 */
	public abstract List findAllOthers(String branchId);

	/**
	 * 
	 * @param b_id
	 * @return
	 */
	public abstract List findAllByIdLike(String b_id);

	/**
	 * 
	 * @param b_id
	 * @param intPage
	 * @param intPageSize
	 * @return
	 */
	public abstract List queryPageByIdLike(String b_id, int intPage,
			int intPageSize);

}
