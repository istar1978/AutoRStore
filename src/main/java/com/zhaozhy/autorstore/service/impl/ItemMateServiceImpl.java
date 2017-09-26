package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.entity.ItemMate;
import com.zhaozhy.autorstore.entity.ItemMateId;
import com.zhaozhy.autorstore.service.ItemMateService;
/**
 * 
 * @Title				ItemMateServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午10:44:05
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ItemMateServiceImpl extends BaseServiceImpl<ItemMate> implements ItemMateService {

	public ItemMate findById(ItemMateId id) {
		return this.itemMateDAO.findById(id);
	}

	public List findByRepId(Object repId) {
		return this.itemMateDAO.findByRepId(repId);
	}

}
