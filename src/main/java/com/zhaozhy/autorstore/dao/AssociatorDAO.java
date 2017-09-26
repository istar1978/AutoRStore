package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.Associator;
/**
 * 
 * @Title				AssociatorDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午07:59:46
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface AssociatorDAO extends BaseDAO<Associator>{


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

}