package com.zhaozhy.autorstore.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;
import com.zhaozhy.autorstore.form.ValidatorMaterialForm;
import com.zhaozhy.autorstore.sysadmin.UserContext;

/**
 * 
 * @Title				MaterialService.java
 * @Package		com.zhaozhy.autorstore.service
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午10:51:46
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public interface MaterialService extends BaseService<Material>{

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
	
	public abstract List transformList2ViewList(List<Material> dataList);
	/**
	 * 
	 * @CreateDate	2017-6-22  上午08:23:30
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据所在机构号+状态 查询所有维修材料
	 * @param braId
	 * @param stat
	 * @return
	 */
	public abstract List findByBraidStat(String braId,String stat);
	/**
	 * 
	 * @CreateDate	2017-6-22  下午04:19:38
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					根据rep_id查询单价
	 * @param matId
	 * @return
	 */
	public abstract BigDecimal getPerPriceByMatId(String matId);
	/**
	 * 
	 * @CreateDate	2017-7-5  上午08:50:18
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					新增维修材料，涉及到主键从serial_gen表中取得，放到service层，控制事务
	 * @param vform
	 * @return
	 */
	public abstract int addMaterial(Material material) ;
}
