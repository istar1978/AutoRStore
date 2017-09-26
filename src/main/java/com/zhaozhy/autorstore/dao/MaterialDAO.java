package com.zhaozhy.autorstore.dao;

import java.math.BigDecimal;
import java.util.List;

import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;

/**
 * @Title MaterialDAO.java
 * @Package com.zhaozhy.autorstore.dao
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-17 上午10:49:43
 * @Desc TODO
 * @Version V1.0
 * @Modified
 * @Date
 * @Desc
 */
public interface MaterialDAO extends BaseDAO<Material>{


	public abstract Material findById(MaterialId id);


	public abstract List findByMatName(Object matName);

	public abstract List findByRepId(Object repId);

	public abstract List findByMatClassify(Object matClassify);

	public abstract List findByMatPreprice(Object matPreprice);

	public abstract List findByMatRealprice(Object matRealprice);

	public abstract List findByMatFactory(Object matFactory);

	public abstract List findByMatNum(Object matNum);


	/**
	 * @param material
	 * @return
	 */
	public abstract List findAllByExample(Material material);

	/**
	 * @param material
	 * @param intPage
	 * @param intPageSize
	 * @return
	 */
	public abstract List findAllByExamplePerPage(Material material,
			int intPage, int intPageSize);

	/**
	 * 取出药品表中状态为*的所有数据
	 * 
	 * @return
	 */
	public abstract List findAllHas();
	/**
	 * 
	 * @CreateDate	2017-6-22  上午11:45:02
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据所在机构ID和状态查询维修材料list
	 * @param braId
	 * @param stat
	 * @return
	 */
	public abstract List findByBraidStat(String braId, String stat);
	/**
	 * 
	 * @CreateDate	2017-6-22  下午04:18:28
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据mat_id 查询维修材料销售单价
	 * @param matId
	 * @return
	 */
	public abstract BigDecimal getPerPriceByMatId(String matId);
}