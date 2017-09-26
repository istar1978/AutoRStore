package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.Menu;
/**
 * 
 * @Title				MenuDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:10:02
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface MenuDAO extends BaseDAO<Menu>{


	public abstract Menu findById(String id);

	public abstract List findByMenName(Object menName);

	public abstract List findByMenAtt(Object menAtt);

	public abstract List findByMenUrl(Object menUrl);

	public abstract List findByMenStat(Object menStat);


	/**
	 * 根据id模糊查询出所有数据
	 * 
	 * @param id
	 * @return
	 */
	public abstract List findAllByIdLike(String id);

	/**
	 * 根据ID 模糊查询出一页的数据返回
	 * 
	 * @param menuId
	 * @param intPage
	 * @param intPageSize
	 * @return
	 */
	public abstract List queryPerPageIdLike(String menuId, int intPage,
			int intPageSize);

	/**
	 * 查询出所有的动作菜单
	 * 
	 * @return
	 */
	public abstract List findAllD();

}