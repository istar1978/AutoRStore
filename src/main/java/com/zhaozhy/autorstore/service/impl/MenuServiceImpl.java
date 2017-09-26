package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.dao.MenuDAO;
import com.zhaozhy.autorstore.entity.Menu;
import com.zhaozhy.autorstore.service.MenuService;
/**
 * 
 * @Title				MenuServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:10:58
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

	public List findAllByIdLike(String id) {
		return this.menuDAO.findAllByIdLike(id);
	}

	public List findAllD() {
		return this.menuDAO.findAllD();
	}

	public Menu findById(String id) {
		return this.menuDAO.findById(id);
	}

	public List findByMenAtt(Object menAtt) {
		return this.menuDAO.findByMenAtt(menAtt);
	}

	public List findByMenName(Object menName) {
		return this.menuDAO.findByMenName(menName);
	}

	public List findByMenStat(Object menStat) {
		return this.menuDAO.findByMenStat(menStat);
	}

	public List findByMenUrl(Object menUrl) {
		return this.menuDAO.findByMenUrl(menUrl);
	}

	public List queryPerPageIdLike(String menuId, int intPage, int intPageSize) {
		return this.menuDAO.queryPerPageIdLike(menuId, intPage, intPageSize);
	}

}
