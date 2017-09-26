package com.zhaozhy.autorstore.service;

import java.util.List;

import com.zhaozhy.autorstore.entity.Associator;
import com.zhaozhy.autorstore.form.ValidatorAssociatorForm;
import com.zhaozhy.autorstore.sysadmin.UserContext;

/**
 * 
 * @Title				AssociatorService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-10   下午03:13:19
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface AssociatorService extends BaseService<Associator>{


	public abstract Associator findById(java.lang.String id);

	public abstract List findByAssPhone(Object assPhone);

	public abstract List findByAssName(Object assName);

	public abstract List findByAssPassword(Object assPassword);

	public abstract List findByAssGender(Object assGender);

	public abstract List findByAssAddr(Object assAddr);

	public abstract List findByAssPoint(Object assPoint);

	public abstract List findByAssLevel(Object assLevel);

	public abstract List findByAssBalance(Object assBalance);

	public abstract List findByAssPbalance(Object assPbalance);

	public abstract List findByAssCarno(Object assCarno);

	public abstract List findByAssStat(Object assStat);

	/**
	 * 根据NO模糊查询出所有数据
	 * 
	 * @param no
	 * @return
	 */
	public abstract List findAllByIdLike(String no);

	/**
	 * 
	 * @param no
	 * @param intPage
	 * @param intPageSize
	 * @return
	 */
	public abstract List queryPerPage(String no, int intPage, int intPageSize);

	/**
	 * 
	 * @return
	 */
	public abstract List findAllOrderByVal();
	/**
	 * 
	 * @CreateDate	2017-7-1  下午05:56:53
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					会员充值操作
	 * @param vform
	 * @return
	 */
	public abstract int addRecharge(ValidatorAssociatorForm vform,UserContext uc);
	/**
	 * 
	 * @CreateDate	2017年8月27日  上午9:34:24
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					判断会员号是否存在
	 * @param assId
	 * @return
	 */
	public abstract String validateAssId(String assId);
}
