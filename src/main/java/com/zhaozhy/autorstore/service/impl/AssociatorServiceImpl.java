package com.zhaozhy.autorstore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zhaozhy.autorstore.entity.AssRecharge;
import com.zhaozhy.autorstore.entity.Associator;
import com.zhaozhy.autorstore.form.ValidatorAssociatorForm;
import com.zhaozhy.autorstore.service.AssociatorService;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.SerialGenUtil;
/**
 * 
 * @Title				AssociatorServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午08:02:13
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class AssociatorServiceImpl extends BaseServiceImpl<Associator> implements AssociatorService {

	public List findAllByIdLike(String no) {
		return this.associatorDAO.findAllByIdLike(no);
	}

	public List findAllOrderByVal() {
		return this.associatorDAO.findAllOrderByVal();
	}

	public List findByAssAddr(Object assAddr) {
		return this.associatorDAO.findByAssAddr(assAddr);
	}

	public List findByAssBalance(Object assBalance) {
		return this.associatorDAO.findByAssBalance(assBalance);
	}

	public List findByAssCarno(Object assCarno) {
		return this.associatorDAO.findByAssCarno(assCarno);
	}

	public List findByAssGender(Object assGender) {
		return this.associatorDAO.findByAssGender(assGender);
	}

	public List findByAssLevel(Object assLevel) {
		return this.associatorDAO.findByAssLevel(assLevel);
	}

	public List findByAssName(Object assName) {
		return this.associatorDAO.findByAssName(assName);
	}

	public List findByAssPassword(Object assPassword) {
		return this.associatorDAO.findByAssPassword(assPassword);
	}

	public List findByAssPbalance(Object assPbalance) {
		return this.associatorDAO.findByAssPbalance(assPbalance);
	}

	public List findByAssPhone(Object assPhone) {
		return this.associatorDAO.findByAssPhone(assPhone);
	}

	public List findByAssPoint(Object assPoint) {
		return this.associatorDAO.findByAssPoint(assPoint);
	}

	public List findByAssStat(Object assStat) {
		return this.associatorDAO.findByAssStat(assStat);
	}

	public Associator findById(String id) {
		return this.associatorDAO.findById(id);
	}

	public List queryPerPage(String no, int intPage, int intPageSize) {
		return this.associatorDAO.queryPerPage(no, intPage, intPageSize);
	}

	public int addRecharge(ValidatorAssociatorForm vform,UserContext uc) {
		//Step 1:修改会员主表Associator，更新余额和赠送余额
		Associator ass=this.findById(vform.getA_id());
		BigDecimal balance=new BigDecimal(vform.getA_balance());
		BigDecimal pbalance=new BigDecimal(0.0);
		if(StringUtils.isNotBlank(vform.getA_pbalance())){
			pbalance=new BigDecimal(vform.getA_pbalance());
		}
		ass.setAssBalance(ass.getAssBalance().add(balance));
		ass.setAssPbalance(ass.getAssPbalance().add(pbalance));
		this.associatorDAO.update(ass);
		
		//Step 2:会员充值表ass_recharge 增加一条数据
		AssRecharge ar=new AssRecharge();
		//Step 2.2:取得主键值，更新serialgen表
		String sNo=this.serialGenDAO.genRechargeId();
		String dateStr=sNo.substring(0,8);
		String timeStr=sNo.substring(8,14);
		ar.setRecId(sNo);
		ar.setAssId(ass.getAssId());
		ar.setRecAmount(balance);
		ar.setRecDate(dateStr);
		ar.setRecTime(timeStr);
		ar.setRecPresent(pbalance);
		ar.setStaId(uc.getStafferId());
		this.assRechargeDAO.save(ar);
		
		return 1;
	}

	public String validateAssId(String assId) {
		StringBuffer rtnStr=new StringBuffer("");
		//判断会员号是否是默认，是 返回不存在
		if(assId!=null&&assId.equals(DicDataUtil.ASSOCIATOR_DEFAULT)){
			rtnStr.append("会员号【"+assId+"】无效，请确认！");
			return rtnStr.toString();
		}
		Associator ass=this.findById(assId);
		if(ass==null){
			rtnStr.append("会员号【"+assId+"】不存在，请确认！");
		}else if(ass.getAssStat().equals(DicDataUtil.DICDATA_000001)){
			//会员号无效，未激活
			rtnStr.append("会员号【"+assId+"】未激活，请确认！");
		}
		return rtnStr.toString();
	}

}
