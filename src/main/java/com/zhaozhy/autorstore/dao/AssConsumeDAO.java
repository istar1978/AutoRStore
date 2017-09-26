package com.zhaozhy.autorstore.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.zhaozhy.autorstore.entity.AssConsume;
/**
 * 
 * @Title				AssConsumeDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午07:59:58
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface AssConsumeDAO extends BaseDAO<AssConsume>{

	public abstract AssConsume findById(java.lang.String id);

	public abstract List findByAssId(Object assId);

	public abstract List findByConDate(Object conDate);

	public abstract List findByConTime(Object conTime);

	public abstract List findByConAmount(Object conAmount);

	public abstract List findByConRamount(Object conRamount);

	public abstract List findByConType(Object conType);

	public abstract List findByConPoint(Object conPoint);

	public abstract List findByStaId(Object staId);

	public abstract List findByBraId(Object braId);

	public abstract List findByConCombo(Object conCombo);

	public abstract List findByComId(Object comId);

	public abstract List findByConDesc(Object conDesc);


	/**
	 * 根据时间段、维修材料编号取出所有数据
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param d_id
	 * @return
	 */
	public abstract List findAllTimeMaterial(String fromDate, String endDate,
			String d_id) throws ParseException;

	/**
	 * 根据时间段、维修材料编号取出所有数据,并分页显示
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param d_id
	 * @param intPage
	 * @param intPageSize
	 * @return
	 */
	public abstract List findAllTimeMaterialPerPage(String fromDate, String endDate, String d_id, int intPage, int intPageSize) throws  ParseException;

	/**
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param b_id
	 * @return
	 */
	public abstract List findAllTimeAssociator(String fromDate, String endDate, String b_id);

	/**
	 * @param fromDate
	 * @param endDate
	 * @param b_id
	 * @return
	 */
	public abstract List findAllTimeByBId(String fromDate, String endDate, String b_id);
	/**
	 * 
	 * @CreateDate	2017-7-7  下午09:12:05
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					销售记录查询
	 * @param paramap
	 * @return
	 */
	public abstract List findUsingChartHisQry(Map<String,String> paramap);

}