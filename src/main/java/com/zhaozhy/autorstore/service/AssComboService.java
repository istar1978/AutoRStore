package com.zhaozhy.autorstore.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.zhaozhy.autorstore.entity.AssCombo;
import com.zhaozhy.autorstore.form.ValidatorAssComboForm;
import com.zhaozhy.autorstore.sysadmin.UserContext;

/**
 * @Title				AssComboService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-10   下午03:35:59
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface AssComboService extends BaseService<AssCombo>{
	/**
	 * 
	 * @CreateDate	2017-6-10  下午03:42:03
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 * @param assId
	 * @return
	 */
	public List<AssCombo> getAllComboByAssid(String assId);
	
	/**
	 * 
	 * @CreateDate	2017-7-1  下午03:51:44
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					会员购买套餐
	 * @param vform
	 * @return
	 */
	public abstract int addAssCombo(ValidatorAssComboForm vform)  throws ParseException,IOException;
	
	/**
	 * 
	 * @CreateDate	2017-7-2  上午08:22:21
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					选择完套餐后，点击确定进入此方法，直接进行插入表操作，不加入购物车
	 * @param vform
	 * @param uc
	 * @return String 返回生成的AssConsume表主键
	 */
	public abstract String addCartCombo(ValidatorAssComboForm vform,UserContext uc);

	public abstract AssCombo findById(java.lang.String id);

	public abstract List findByRepId(Object repId);

	public abstract List findByRepName(Object repName);

	public abstract List findByAssId(Object assId);

	public abstract List findByComTime(Object comTime);

	public abstract List findByComDesc(Object comDesc);

	public abstract List findByComItem(Object comItem);

	public abstract List findByComPrice(Object comPrice);

	public abstract List findByComStat(Object comStat);

	public List<AssCombo> queryAssComboInUsing(String assId,Date currDate);
	public List transformList2ViewList(List<AssCombo> dataList);
}
