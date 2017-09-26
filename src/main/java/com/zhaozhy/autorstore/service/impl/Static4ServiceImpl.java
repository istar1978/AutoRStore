package com.zhaozhy.autorstore.service.impl;

import java.util.Date;
import java.util.List;

import com.zhaozhy.autorstore.entity.Static4;
import com.zhaozhy.autorstore.entity.Static4Id;
import com.zhaozhy.autorstore.service.Static4Service;
/**
 * 
 * @Title				Static4ServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午03:20:57
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class Static4ServiceImpl extends BaseServiceImpl<Static4> implements Static4Service {

	public List findByBraName(Object braName) {
		return this.static4DAO.findByBraName(braName);
	}

	public Static4 findById(Static4Id id) {
		return this.static4DAO.findById(id);
	}

	public List findByS4Allprice(Object allprice) {
		return this.static4DAO.findByS4Allprice(allprice);
	}

	public List findByS4Realprice(Object realprice) {
		return this.static4DAO.findByS4Realprice(realprice);
	}

	public Date findStatic4Earlise() {
		return this.static4DAO.findStatic4Earlise();
	}

	public Static4 findStatic4Earlist() {
		return this.static4DAO.findStatic4Earlist();
	}

	public Static4 findStatic4Last() {
		return this.static4DAO.findStatic4Last();
	}

}
