package com.zhaozhy.autorstore.service.impl;

import java.util.List;
import java.util.Map;

import com.zhaozhy.autorstore.dao.ConsumeListDAO;
import com.zhaozhy.autorstore.entity.ConsumeList;
import com.zhaozhy.autorstore.service.ConsumeListService;
/**
 * 
 * @Title				ConsumeListServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:21:54
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class ConsumeListServiceImpl extends BaseServiceImpl<ConsumeList> implements ConsumeListService {

	public List findByConId(Object conId) {
		return this.consumeListDAO.findByConId(conId);
	}

	public List findByExample(ConsumeList instance) {
		return this.consumeListDAO.findByExample(instance);
	}

	public ConsumeList findById(String id) {
		return this.consumeListDAO.findById(id);
	}

	public List findByLisMoney(Object lisMoney) {
		return this.consumeListDAO.findByLisMoney(lisMoney);
	}

	public List findByLisNum(Object lisNum) {
		return this.consumeListDAO.findByLisNum(lisNum);
	}

	public List findByMatId(Object matId) {
		return this.consumeListDAO.findByMatId(matId);
	}

	public List findByRepId(Object repId) {
		return this.consumeListDAO.findByRepId(repId);
	}

	public List findUsingChartHisQry(Map<String, String> valueMap) {
		
		return this.consumeListDAO.findUsingChartHisQry(valueMap);
	}

}
