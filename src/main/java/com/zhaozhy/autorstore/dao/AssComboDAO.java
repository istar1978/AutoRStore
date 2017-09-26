package com.zhaozhy.autorstore.dao;

import java.util.Date;
import java.util.List;

import com.zhaozhy.autorstore.entity.AssCombo;
/**
 * 
 * @Title				AssComboDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午08:00:03
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface AssComboDAO extends BaseDAO<AssCombo>{

	public abstract AssCombo findById(java.lang.String id);

	public abstract List findByRepId(Object repId);

	public abstract List findByRepName(Object repName);

	public abstract List findByAssId(Object assId);

	public abstract List findByComTime(Object comTime);

	public abstract List findByComDesc(Object comDesc);

	public abstract List findByComItem(Object comItem);

	public abstract List findByComPrice(Object comPrice);

	public abstract List findByComStat(Object comStat);


	/**
	 * 
	 * @CreateDate	2017-6-19  下午12:16:58
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					查询出ass_id对应的会员卡有哪些已购套餐
	 *									有效性限制：1.ass_id,2.状态con_stat正常，3.剩余次数con_tim>0，4.当前日期在起始日期和结束日期之间
	 * @param assId
	 * @param currDate
	 * @return
	 */
	public List<AssCombo> queryAssComboInUsing(String assId,Date currDate);

}