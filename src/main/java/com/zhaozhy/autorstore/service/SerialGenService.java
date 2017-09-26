package com.zhaozhy.autorstore.service;

import java.util.List;

import com.zhaozhy.autorstore.entity.SerialGen;
import com.zhaozhy.autorstore.entity.SerialGenId;

/**
 * 
 * @Title				SerialGenService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:58:22
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface SerialGenService extends BaseService<SerialGen>{

	public abstract SerialGen findById(SerialGenId id);

	public abstract List findBySerNo(Object serNo);

	public abstract List findBySerRule(Object serRule);

}
