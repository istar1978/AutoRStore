package com.zhaozhy.autorstore.service;

import java.math.BigDecimal;
import java.util.List;

import com.zhaozhy.autorstore.entity.ConsumeCart;
import com.zhaozhy.autorstore.sysadmin.UserContext;

/**
 * 
 * @Title				ConsumeCartService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-19   上午10:13:04
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface ConsumeCartService  extends BaseService<ConsumeCart>{

	public abstract ConsumeCart findById(java.lang.Integer id);


	public abstract List findByAssId(Object assId);

	public abstract List findByRepId(Object repId);

	public abstract List findByMatId(Object matId);

	public abstract List findByCartNum(Object cartNum);

	public abstract List findByCartPerprice(Object cartPerprice);

	public abstract List findByCartMoney(Object cartMoney);

	/**
	 * 
	 * @CreateDate	2017-6-23  上午10:15:25
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					判断数据库中是否有和传入的cart相同的项，ass_id,rep_id/mat_id,
	 * @param cart
	 * @return
	 */
	public abstract ConsumeCart ifHasSameItem(ConsumeCart cart);
	/**
	 * 
	 * @CreateDate	2017-7-2  下午10:18:38
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					销售页面，无套餐，下方ec列表删除ConsumeCart项
	 * @param keyList
	 * @return
	 */
	public abstract int[] deleteCartItems(List keyList);
	/**
	 * 
	 * @CreateDate	2017-7-3  上午11:12:08
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据assId会员号查找ConsumeCart中所有商品总价
	 * @param assId
	 * @return
	 */
	public abstract BigDecimal getSumPriceByAssId(String assId);
	
	/**
	 * 
	 * @CreateDate	2017年7月23日  上午9:51:32
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					验证购物车中，数量是否合适，判断是否存在大于库存的情况
	 * @param assId
	 * @return
	 */
	public abstract String validateCartNum(String assId,UserContext uc);
}
