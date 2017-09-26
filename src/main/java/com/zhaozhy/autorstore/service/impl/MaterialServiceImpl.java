package com.zhaozhy.autorstore.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.exception.DateErrorException;
import com.zhaozhy.autorstore.form.ValidatorMaterialForm;
import com.zhaozhy.autorstore.service.MaterialService;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.util.DataUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;
/**
 * 
 * @Title				MaterialServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午10:52:16
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class MaterialServiceImpl extends BaseServiceImpl<Material> implements MaterialService {
	private static final Log log = LogFactory.getLog(MaterialServiceImpl.class);
	
	public List findAllByExample(Material material) {
		return this.materialDAO.findAllByExample(material);
	}

	public List findAllByExamplePerPage(Material material, int intPage,
			int intPageSize) {
		return this.materialDAO.findAllByExamplePerPage(material, intPage, intPageSize);
	}

	public List findAllHas() {
		return this.materialDAO.findAllHas();
	}

	public Material findById(MaterialId id) {
		return this.materialDAO.findById(id);
	}

	public List findByMatClassify(Object matClassify) {
		return this.materialDAO.findByMatClassify(matClassify);
	}

	public List findByMatFactory(Object matFactory) {
		return this.materialDAO.findByMatFactory(matFactory);
	}

	public List findByMatName(Object matName) {
		return this.materialDAO.findByMatName(matName);
	}

	public List findByMatNum(Object matNum) {
		return this.materialDAO.findByMatNum(matNum);
	}

	public List findByMatPreprice(Object matPreprice) {
		return this.materialDAO.findByMatPreprice(matPreprice);
	}

	public List findByMatRealprice(Object matRealprice) {
		return this.materialDAO.findByMatRealprice(matRealprice);
	}


	public List findByRepId(Object repId) {
		return this.materialDAO.findByRepId(repId);
	}

	public List findByBraidStat(String braId, String stat) {
		return this.materialDAO.findByBraidStat(braId, stat);
	}

	public List transformList2ViewList(List<Material> dataList) {
		Map< String, String> dataMap=new HashMap<String, String>();
		for(Material mat:dataList){
			dataMap.put(mat.getId().getMatId(), mat.getMatName());
		}
		return DataUtil.getSelectList(dataMap);
	}

	public BigDecimal getPerPriceByMatId(String matId) {
		return this.materialDAO.getPerPriceByMatId(matId);
	}

	public int addMaterial(Material model)  {
//		String dr_id = vform.getDr_id();//matId从serial_gen表中生成，不再由页面输入
		String dr_id=this.serialGenDAO.genMaterialMatId();

		MaterialId materialId=model.getId();
		materialId.setMatId(dr_id);
		
		Material materialtmp = this.findById(materialId);
		if (materialtmp != null) {
			log.error("主键编号重复！！");
			throw new DataAlreadyExistException("");
		}

		model.setId(materialId);
		
		this.save(model);
		
		return 1;
	}

}
