package com.zhaozhy.autorstore.service.impl;

import java.util.List;

import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.service.DicDataService;
/**
 * 
 * @Title				DicDataServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午08:18:57
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class DicDataServiceImpl extends BaseServiceImpl<DicData> implements DicDataService {

	public List findAllByExample(String dicDataLarge) {
		return this.dicDataDAO.findAllByExample(dicDataLarge);
	}

	public List findByDicName(Object dicName) {
		return this.dicDataDAO.findByDicName(dicName);
	}

	public List findByDicText(Object dicText) {
		return this.dicDataDAO.findByDicText(dicText);
	}

	public DicData findById(DicDataId id) {
		return this.dicDataDAO.findById(id);
	}

	public List findByIdProperty(DicDataId id) {
		return this.dicDataDAO.findByIdProperty(id);
	}

	public List findPageByIdProperty(DicDataId id, int intPage, int intPageSize) {
		return this.dicDataDAO.findPageByIdProperty(id, intPage, intPageSize);
	}


}
