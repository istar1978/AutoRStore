package com.zhaozhy.autorstore.service;

import java.util.List;
import java.util.Map;

import com.zhaozhy.autorstore.entity.AssRecharge;

/**
 * 
 * @Title				AssRechargeService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   下午03:41:41
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface AssRechargeService extends BaseService<AssRecharge>{

	public abstract AssRecharge findById(String id);

	public abstract List findByAssId(Object assId);

	public abstract List findByRecAmount(Object recAmount);

	public abstract List findByRecDate(Object recDate);

	public abstract List findByRecTime(Object recTime);

	public abstract List findByRecPresent(Object recPresent);

	public abstract List findByRecText(Object recText);
	/**
	 * 
	 * @CreateDate	2017-7-14  下午12:51:08
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					会员充值流水查询
	 * @param paramap
	 * @return
	 */
	public abstract List findUsingChartHisQry(Map<String,String> paramap);
}
