package com.zhaozhy.autorstore.service;

import java.util.List;

import com.zhaozhy.autorstore.entity.ItemMate;
import com.zhaozhy.autorstore.entity.ItemMateId;

/**
 * 
 * @Title				ItemMateService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午10:43:35
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface ItemMateService extends BaseService<ItemMate>{

	public abstract ItemMate findById(ItemMateId id);

	public abstract List findByRepId(Object repId);

}
