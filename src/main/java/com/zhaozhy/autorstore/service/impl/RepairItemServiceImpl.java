package com.zhaozhy.autorstore.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaozhy.autorstore.entity.RepairItem;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.form.ValidatorRepairItemAddForm;
import com.zhaozhy.autorstore.service.RepairItemService;
import com.zhaozhy.autorstore.util.DataUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;
/**
 * 
 * @Title				RepairItemServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午11:49:46
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class RepairItemServiceImpl extends BaseServiceImpl<RepairItem> implements RepairItemService {
	private static final Log log = LogFactory.getLog(RepairItemService.class);
	
	public RepairItem findById(String id) {
		return this.repairItemDAO.findById(id);
	}

	public List findByRepClassify(Object repClassify) {
		return this.repairItemDAO.findByRepClassify(repClassify);
	}

	public List findByRepMoney(Object repMoney) {
		return this.repairItemDAO.findByRepMoney(repMoney);
	}

	public List findByRepName(Object repName) {
		return this.repairItemDAO.findByRepName(repName);
	}

	public List findByRepStat(Object repStat) {
		return this.repairItemDAO.findByRepStat(repStat);
	}

	public List transformList2ViewList(List<RepairItem> dataList) {
		Map< String, String> dataMap=new HashMap<String, String>();
		for(RepairItem ri:dataList){
			dataMap.put(ri.getRepId(), ri.getRepName());
		}
		return DataUtil.getSelectList(dataMap);
	}

	public BigDecimal getPerPriceByRepId(String repId) {
		return this.repairItemDAO.getPerPriceByRepId(repId);
	}

	public int addRepairItem(RepairItem ri) {
//		String repId=vform.getRep_id();//不再从页面输入，系统自动生成十位ID
		String repId=this.serialGenDAO.genId10(DicDataUtil.DICDATA_1005);
		
		ri.setRepId(repId);
		
		RepairItem item2=this.findById(repId);
		if(item2!=null){
			log.error("RepairItem is already exist!");
			throw new DataAlreadyExistException("");
		}
		
		//ITEM_MATE 项目-材料关系表   操作暂时不考虑
		this.save(ri);
		return 1;
	}

	public List findAllByExample(RepairItem ri) {
		return this.repairItemDAO.findAllByExample(ri);
	}

}
