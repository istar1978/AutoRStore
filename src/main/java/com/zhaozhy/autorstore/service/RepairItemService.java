package com.zhaozhy.autorstore.service;

import java.math.BigDecimal;
import java.util.List;

import com.zhaozhy.autorstore.entity.RepairItem;
import com.zhaozhy.autorstore.form.ValidatorRepairItemAddForm;

/**
 * 
 * @Title				RepairItemService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:49:07
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface RepairItemService extends BaseService<RepairItem>{

	public abstract RepairItem findById(String id);

	public abstract List findByRepName(Object repName);

	public abstract List findByRepClassify(Object repClassify);

	public abstract List findByRepMoney(Object repMoney);

	public abstract List findByRepStat(Object repStat);

	/**
	 * 
	 * @CreateDate	2017-6-22  上午08:16:02
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 * @param dataList
	 * @return
	 */
	public abstract List transformList2ViewList(List<RepairItem> dataList);
	/**
	 * 
	 * @CreateDate	2017-6-22  下午03:54:02
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据rep_id查询单价
	 * @param repId
	 * @return
	 */
	public abstract BigDecimal getPerPriceByRepId(String repId);
	/**
	 * 
	 * @CreateDate	2017-7-5  上午11:33:04
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					新增维修项目
	 * @param ri
	 * @return
	 */
	public abstract int addRepairItem(RepairItem ri);
	/**
	 * 
	 * @CreateDate	2017年8月7日  下午10:15:27
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					模糊查询
	 * @param ri
	 * @return
	 */
	public abstract List findAllByExample(RepairItem ri);
}
