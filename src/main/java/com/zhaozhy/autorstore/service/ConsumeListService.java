package com.zhaozhy.autorstore.service;

import java.util.List;
import java.util.Map;

import com.zhaozhy.autorstore.entity.ConsumeList;

/**
 * 
 * @Title				ConsumeListService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午09:21:20
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface ConsumeListService extends BaseService<ConsumeList>{

	public abstract ConsumeList findById(java.lang.String id);

	public abstract List findByConId(Object conId);

	public abstract List findByRepId(Object repId);

	public abstract List findByMatId(Object matId);

	public abstract List findByLisNum(Object lisNum);

	public abstract List findByLisMoney(Object lisMoney);
	/**
	 * 
	 * @CreateDate	2017-7-8  下午05:55:14
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					销售明细查询
	 * @param valueMap
	 * @return
	 */
	public abstract List findUsingChartHisQry(Map<String,String> valueMap);

}
