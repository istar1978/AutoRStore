package com.zhaozhy.autorstore.dao;

import java.math.BigDecimal;
import java.util.List;

import com.zhaozhy.autorstore.entity.RepairItem;
/**
 * 
 * @Title				RepairItemDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:48:04
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface RepairItemDAO extends BaseDAO<RepairItem>{


	public abstract RepairItem findById(String id);

	public abstract List findByRepName(Object repName);

	public abstract List findByRepClassify(Object repClassify);

	public abstract List findByRepMoney(Object repMoney);

	public abstract List findByRepStat(Object repStat);

	/**
	 * 
	 * @CreateDate	2017-6-22  下午03:54:54
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据rep_id查询单价
	 * @param repId
	 * @return
	 */
	public abstract BigDecimal getPerPriceByRepId(String repId);
	/**
	 * 
	 * @CreateDate	2017年8月7日  下午10:04:53
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					模糊查询
	 * @param ri
	 * @return
	 */
	public abstract List findAllByExample(RepairItem ri);

}