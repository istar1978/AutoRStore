package com.zhaozhy.autorstore.service.impl;

import java.util.List;
import java.util.Map;

import com.zhaozhy.autorstore.entity.AssRecharge;
import com.zhaozhy.autorstore.service.AssRechargeService;
/**
 * 
 * @Title				AssRechargeServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午03:42:05
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class AssRechargeServiceImpl extends BaseServiceImpl<AssRecharge> implements AssRechargeService {

	public List findByAssId(Object assId) {
		return this.assRechargeDAO.findByAssId(assId);
	}

	public AssRecharge findById(String id) {
		return this.assRechargeDAO.findById(id);
	}

	public List findByRecAmount(Object recAmount) {
		return this.assRechargeDAO.findByRecAmount(recAmount);
	}

	public List findByRecDate(Object recDate) {
		return this.assRechargeDAO.findByRecDate(recDate);
	}

	public List findByRecPresent(Object recPresent) {
		return this.assRechargeDAO.findByRecPresent(recPresent);
	}

	public List findByRecText(Object recText) {
		return this.assRechargeDAO.findByRecText(recText);
	}

	public List findByRecTime(Object recTime) {
		return this.assRechargeDAO.findByRecTime(recTime);
	}

	public List findUsingChartHisQry(Map<String, String> paramap) {
		return this.assRechargeDAO.findUsingChartHisQry(paramap);
	}

}
