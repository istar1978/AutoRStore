package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.SerialGen;
import com.zhaozhy.autorstore.entity.SerialGenId;
/**
 * 
 * @Title				SerialGenDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:57:48
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface SerialGenDAO extends BaseDAO<SerialGen>{


	public abstract SerialGen findById(SerialGenId id);

	public abstract List findBySerNo(Object serNo);

	public abstract List findBySerRule(Object serRule);
	/**
	 * 
	 * @CreateDate	2017-7-1  下午03:48:36
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					会员购买优惠套餐 生成主键ID 10位
	 * @return
	 */
	public abstract String genAssComboId();
	/**
	 * 
	 * @CreateDate	2017-7-1  下午05:43:24
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					消费清单 主键lis_id    10位
	 * @return
	 */
	public abstract String genConsumeListId();
	/**
	 * 
	 * @CreateDate	2017-7-1  下午05:45:35
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					ASS_CONSUME 会员消费表 流水号
	 * @return
	 */
	public abstract String genConsumeId();
	/**
	 * 
	 * @CreateDate	2017-7-1  下午05:46:56
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					生成 充值流水号
	 * @return
	 */
	public abstract String genRechargeId();
	/**
	 * 
	 * @CreateDate	2017-7-5  上午09:21:29
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					生成维修材料编号
	 * @return
	 */
	public abstract String genMaterialMatId();
	/**
	 * 
	 * @CreateDate	2017-7-5  上午11:25:00
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					生成主键id通用方法，id长度为10
	 * @param serSmall
	 * @return
	 */
	public abstract String genId10(String serSmall);
	/**
	 * 
	 * @CreateDate	2017-7-5  上午11:28:23
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					生成主键id通用方法，id长度为20
	 * @param serSmall
	 * @return
	 */
	public String genId20(String serSmall);

}