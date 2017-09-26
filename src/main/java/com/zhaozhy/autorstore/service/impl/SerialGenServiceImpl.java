package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.entity.SerialGen;
import com.zhaozhy.autorstore.entity.SerialGenId;
import com.zhaozhy.autorstore.service.SerialGenService;
/**
 * 
 * @Title				SerialGenServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:58:54
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class SerialGenServiceImpl extends BaseServiceImpl<SerialGen> implements SerialGenService {

	public SerialGen findById(SerialGenId id) {
		return this.serialGenDAO.findById(id);
	}

	public List findBySerNo(Object serNo) {
		return this.serialGenDAO.findBySerNo(serNo);
	}

	public List findBySerRule(Object serRule) {
		return this.serialGenDAO.findBySerRule(serRule);
	}
}
