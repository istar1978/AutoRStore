package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.entity.Static2;
import com.zhaozhy.autorstore.service.Static2Service;
/**
 * 
 * @Title				Static2ServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午01:56:11
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class Static2ServiceImpl extends BaseServiceImpl<Static2> implements Static2Service {

	public List findAllOrderByVal() {
		return this.static2DAO.findAllOrderByVal();
	}

	public Static2 findById(String id) {
		return this.static2DAO.findById(id);
	}

	public List findByProperty(String propertyName, Object value) {
		return this.static2DAO.findByProperty(propertyName, value);
	}

	public List findByS2Point(Object point) {
		return this.static2DAO.findByS2Point(point);
	}

}
