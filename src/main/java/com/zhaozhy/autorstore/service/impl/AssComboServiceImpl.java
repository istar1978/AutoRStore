package com.zhaozhy.autorstore.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import com.zhaozhy.autorstore.entity.AssCombo;
import com.zhaozhy.autorstore.entity.AssConsume;
import com.zhaozhy.autorstore.entity.Associator;
import com.zhaozhy.autorstore.form.ValidatorAssComboForm;
import com.zhaozhy.autorstore.service.AssComboService;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.util.DataUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;

/**
 * @Title AssComboServiceImpl.java
 * @Package com.zhaozhy.autorstore.service.impl
 * @Created zhaozhy (zhongyong@qq.com)
 * @Date 2017-6-10 下午03:41:27
 * @Desc TODO
 * @Version V1.0
 * @Modified
 * @Date
 * @Desc
 */
public class AssComboServiceImpl extends BaseServiceImpl<AssCombo> implements AssComboService {

	public List<AssCombo> getAllComboByAssid(String assId) {
		return (List<AssCombo>) this.assComboDAO.findByAssId(assId);
	}

	public List findByAssId(Object assId) {
		return this.assComboDAO.findByAssId(assId);
	}

	public List findByComDesc(Object comDesc) {
		return this.assComboDAO.findByComDesc(comDesc);
	}

	public List findByComItem(Object comItem) {
		return this.assComboDAO.findByComItem(comItem);
	}

	public List findByComPrice(Object comPrice) {
		return this.assComboDAO.findByComPrice(comPrice);
	}

	public List findByComStat(Object comStat) {
		return this.assComboDAO.findByComStat(comStat);
	}

	public List findByComTime(Object comTime) {
		return this.assComboDAO.findByComTime(comTime);
	}

	public AssCombo findById(String id) {
		return this.assComboDAO.findById(id);
	}

	public List findByRepId(Object repId) {
		return this.assComboDAO.findByRepId(repId);
	}

	public List findByRepName(Object repName) {
		return this.assComboDAO.findByRepName(repName);
	}

	public List<AssCombo> queryAssComboInUsing(String assId, Date currDate) {
		return this.assComboDAO.queryAssComboInUsing(assId, currDate);
	}
	
	/**
	 * 
	 * @CreateDate	2017-6-19  下午09:15:19
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					传入存储asscombo对象的list，返回转换成DictionaryViews的list
	 * @param dataList
	 * @return
	 */
	public List transformList2ViewList(List<AssCombo> dataList){
		Map< String, String> dataMap=new HashMap<String, String>();
		for(AssCombo combo:dataList){
//			dataMap.put(combo.getComId(), this.repairItemDAO.get(combo.getRepId()).getRepName());
			dataMap.put(combo.getComId(), combo.getComName());
		}
		return DataUtil.getSelectListDefault(dataMap);
	}

	public int addAssCombo(ValidatorAssComboForm vform) throws ParseException, IOException {
		String com_id=this.serialGenDAO.genAssComboId();
		String rep_id=vform.getRep_id();
		String com_name=this.repairItemDAO.findById(rep_id).getRepName();
		String ass_id=vform.getAss_id();
		String com_sdate=vform.getCom_sdate();
		Date sdate=DateUtils.parseDate(com_sdate, new String[]{"yyyy-mm-dd"});
		String com_edate=vform.getCom_edate();
		Date edate=DateUtils.parseDate(com_edate, new String[]{"yyyy-mm-dd"});
		Date com_date=new Date();//默认当前日期
		String com_time=vform.getCom_time();
		String com_item=vform.getCom_item();
		String com_desc=vform.getCom_desc();
		String com_price=vform.getCom_price();
		String com_stat=DicDataUtil.DICDATA_000000;//默认有效
		
		AssCombo assCombo=new AssCombo();
		assCombo.setComId(com_id);
		assCombo.setComName(com_name);
		assCombo.setRepId(rep_id);
		assCombo.setAssId(ass_id);
		assCombo.setComSdate(sdate);
		assCombo.setComEdate(edate);
		assCombo.setComDate(com_date);
		assCombo.setComTime(Integer.parseInt(com_time));
		assCombo.setComDesc(com_desc);
		assCombo.setComItem(com_item);
		assCombo.setComPrice(new BigDecimal(com_price));
		assCombo.setComStat(com_stat);
//		this.assComboDAO.save(assCombo);
		this.assComboDAO.save(assCombo);
//		Integer.parseInt("sdfds");
		//购买套餐时根据系统设置，判断是否计入积分
		String pc=DataUtil.getValueByKeyPro(DicDataUtil.POINT_COMBO);
		if(pc.equals(DicDataUtil.DICDATA_00020)){
//			String rule=DataUtil.getValueByKeyPro(DicDataUtil.POINT_RULE);
//			int ruleInt=Integer.parseInt(rule);
			Integer point=DataUtil.calculatePoint(assCombo.getComPrice());
			Associator ass=this.associatorDAO.findById(ass_id);
			Integer nPoint=ass.getAssPoint()+point;//积分
			String aLevel=DataUtil.getNewAssLevel(nPoint);//会员级别
			ass.setAssPoint(nPoint);
			ass.setAssLevel(aLevel);
			this.associatorDAO.update(ass);
		}
		
		return 1;
	}

	public String addCartCombo(ValidatorAssComboForm vform,UserContext uc) {
		String com_id=vform.getCom_id();
		String ass_id=vform.getAss_id();
		//1.ASS_COMBO修改剩余优惠次数
		AssCombo ac=this.findById(com_id);
		ac.setComTime(ac.getComTime()-1);
		this.update(ac);
		//2.ASS_CONSUME增加一条数据
		AssConsume assConsume=new AssConsume();
		//2.1 生成AssConsume表主键conId
		String con_id=this.serialGenDAO.genConsumeId();
		
		String con_date=con_id.substring(0, 8);
		String con_time=con_id.substring(8, 14);
		BigDecimal con_amount=new BigDecimal(0);
		BigDecimal con_ramount=new BigDecimal(0);
		String con_type=DicDataUtil.PAYMENTMODE_000902;
		//使用套餐，积分在购买套餐时确定，此处不计算
		Integer con_point=0;
		String sta_id=uc.getStafferId();
		String sta_name=uc.getStafferName();
		String bra_id=uc.getStafferBranchId();
		String bra_name=uc.getStafferBranchName();
//		String con_combo=DicDataUtil.DICDATA_00020;
		BigDecimal con_collect=new BigDecimal(0);
		BigDecimal con_change=new BigDecimal(0);
		
		assConsume.setConId(con_id);
		assConsume.setAssId(ass_id);
		assConsume.setConDate(con_date);
		assConsume.setConTime(con_time);
		assConsume.setConAmount(con_amount);
		assConsume.setConRamount(con_ramount);
		assConsume.setConType(con_type);
		assConsume.setConPoint(con_point);
		assConsume.setStaId(sta_id);
		assConsume.setBraId(bra_id);
//		assConsume.setConCombo(con_combo);
		assConsume.setComId(com_id);
		assConsume.setConCollect(con_collect);
		assConsume.setConChange(con_change);
		assConsume.setConMark(DicDataUtil.CONSUMEMARK_001002);
		
		this.assConsumeDAO.save(assConsume);
		
		return con_id;
	}

}
