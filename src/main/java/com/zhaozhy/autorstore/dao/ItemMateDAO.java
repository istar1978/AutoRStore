package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.ItemMate;
import com.zhaozhy.autorstore.entity.ItemMateId;
/**
 * 
 * @Title				ItemMateDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午10:43:08
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface ItemMateDAO extends BaseDAO<ItemMate>{


	public abstract ItemMate findById(ItemMateId id);

	public abstract List findByRepId(Object repId);

}