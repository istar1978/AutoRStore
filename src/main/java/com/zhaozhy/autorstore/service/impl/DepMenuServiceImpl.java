package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.entity.DepMenu;
import com.zhaozhy.autorstore.entity.DepMenuId;
import com.zhaozhy.autorstore.service.DepMenuService;
/**
 * 
 * @Title				DepMenuServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:44:59
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class DepMenuServiceImpl extends BaseServiceImpl<DepMenu> implements DepMenuService {

	public List findByDId(String id) {
		return this.depMenuDAO.findByDId(id);
	}

	public List findByDmStat(Object dmStat) {
		return this.depMenuDAO.findByDmStat(dmStat);
	}

	public DepMenu findById(DepMenuId id) {
		return this.depMenuDAO.findById(id);
	}
}
