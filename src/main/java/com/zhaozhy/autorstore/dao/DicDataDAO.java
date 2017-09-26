package com.zhaozhy.autorstore.dao;

import java.util.List;

import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
/**
 * 
 * @Title				DicDataDAO.java
 * @Package		com.zhaozhy.autorstore.dao
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午08:17:34
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface DicDataDAO extends BaseDAO<DicData>{


	public abstract DicData findById(com.zhaozhy.autorstore.entity.DicDataId id);


	public abstract List findByDicName(Object dicName);

	public abstract List findByDicText(Object dicText);

	/**
	 *  根据MenId中的列查询数据
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public abstract List findByIdProperty(DicDataId id);

	/**
	 * 
	 * 创建时间：2017-6-2  下午01:26:17
	 * 	创建者：zhaozhy
	 *	方法说明：
	 * @param propertyName
	 * @param value
	 * @param intPage
	 * @param intPageSize
	 * @return
	 */
	public abstract List findPageByIdProperty(DicDataId id, int intPage,
			int intPageSize);

	/**
	 * 
	 * 创建时间：2017-5-27  上午09:13:03
	 * 	创建者：zhaozhy
	 *	方法说明：
	 * @param dicDataLarge
	 * @return
	 */
	public abstract List findAllByExample(String dicDataLarge);

}