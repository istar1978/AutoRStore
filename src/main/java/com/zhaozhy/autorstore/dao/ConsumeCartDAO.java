package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.ConsumeCart;
/**
 * 
 * @Title				ConsumeCartDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-23   上午09:12:41
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface ConsumeCartDAO extends BaseDAO<ConsumeCart>{


	public abstract ConsumeCart findById(java.lang.Integer id);

	public abstract List findByAssId(Object assId);

	public abstract List findByRepId(Object repId);

	public abstract List findByMatId(Object matId);

	public abstract List findByCartNum(Object cartNum);

	public abstract List findByCartPerprice(Object cartPerprice);

	public abstract List findByCartMoney(Object cartMoney);

	/**
	 * 
	 * @CreateDate	2017-6-23  上午10:00:26
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					判断数据库中是否有和传入的cart相同的项，ass_id,rep_id/mat_id,
	 * @param cart
	 * @return
	 */
	public abstract ConsumeCart ifHasSameItem(ConsumeCart cart);
	/**
	 * 
	 * @CreateDate	2017-7-2  下午10:07:26
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					销售页面，无套餐，下方ec列表删除ConsumeCart项
	 * @param keyList
	 * @return
	 */
	public abstract int[] deleteCartItems(List keyList);
	
	/**
	 * 
	 * @CreateDate	2017年7月23日  上午9:39:27
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据assId查找所有数据，并且维修材料不为空
	 *									(供 提交购物车时，判断输入的数量是否大于维修材料库存量)
	 * @param assId
	 * @return
	 */
	public abstract List<ConsumeCart> findByAssIdWithMatIdNotNull(String assId);

}