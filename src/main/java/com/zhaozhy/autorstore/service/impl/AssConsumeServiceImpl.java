package com.zhaozhy.autorstore.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.zhaozhy.autorstore.entity.AssConsume;
import com.zhaozhy.autorstore.entity.Associator;
import com.zhaozhy.autorstore.entity.ConsumeCart;
import com.zhaozhy.autorstore.entity.ConsumeList;
import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;
import com.zhaozhy.autorstore.service.AssConsumeService;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.util.DataUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.SerialGenUtil;
/**
 * 
 * @Title				AssConsumeServiceImpl.java
 * @Package		com.zhaozhy.autorstore.service.impl
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-6-17   上午07:25:08
 * @Desc				TODO
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
public class AssConsumeServiceImpl extends BaseServiceImpl<AssConsume> implements AssConsumeService {

	public List findAllTimeAssociator(String fromDate, String endDate,
			String b_id) {
		return this.assConsumeDAO.findAllTimeAssociator(fromDate, endDate, b_id);
	}

	public List findAllTimeByBId(String fromDate, String endDate, String b_id) {
		return this.assConsumeDAO.findAllTimeByBId(fromDate, endDate, b_id);
	}

	public List findAllTimeMaterial(String fromDate, String endDate, String d_id)
			throws ParseException {
		return this.assConsumeDAO.findAllTimeMaterial(fromDate, endDate, d_id);
	}

	public List findAllTimeMaterialPerPage(String fromDate, String endDate,
			String d_id, int intPage, int intPageSize) throws ParseException {
		return this.assConsumeDAO.findAllTimeMaterialPerPage(fromDate, endDate, d_id, intPage, intPageSize);
	}

	public List findByAssId(Object assId) {
		return this.assConsumeDAO.findByAssId(assId);
	}

	public List findByBraId(Object braId) {
		return this.assConsumeDAO.findByBraId(braId);
	}

	public List findByComId(Object comId) {
		return this.assConsumeDAO.findByComId(comId);
	}

	public List findByConAmount(Object conAmount) {
		return this.assConsumeDAO.findByConAmount(conAmount);
	}

	public List findByConCombo(Object conCombo) {
		return this.assConsumeDAO.findByConCombo(conCombo);
	}

	public List findByConDate(Object conDate) {
		return this.assConsumeDAO.findByConDate(conDate);
	}

	public List findByConDesc(Object conDesc) {
		return this.findByConDesc(conDesc);
	}

	public List findByConPoint(Object conPoint) {
		return this.assConsumeDAO.findByConPoint(conPoint);
	}

	public List findByConRamount(Object conRamount) {
		return this.assConsumeDAO.findByConRamount(conRamount);
	}

	public List findByConTime(Object conTime) {
		return this.assConsumeDAO.findByConTime(conTime);
	}

	public List findByConType(Object conType) {
		return this.assConsumeDAO.findByConType(conType);
	}

	public AssConsume findById(String id) {
		return this.assConsumeDAO.findById(id);
	}


	public List findByStaId(Object staId) {
		return this.assConsumeDAO.findByStaId(staId);
	}

	
	public String addCarConsume(UserContext uc, String assId) throws IOException {
		List<ConsumeCart> cartList=this.consumeCartDAO.findByAssId(assId);
		if(cartList.size()<1){
			//如果cart中没有数据，说明cart是空的
			return null;
		}
		AssConsume ac=new AssConsume();
		String con_id=this.serialGenDAO.genConsumeId();
		ac.setConId(con_id);
		ac.setAssId(assId);
		ac.setConDate(con_id.substring(0,8));
		ac.setConTime(con_id.substring(8,14));
		ac.setStaId(uc.getStafferId());
		ac.setBraId(uc.getStafferBranchId());
//		ac.setConCombo(DicDataUtil.DICDATA_00021);
		ac.setConMark(DicDataUtil.CONSUMEMARK_001001);
		
		BigDecimal con_amount=new BigDecimal(0);
		//封装consume_list对象
		
		for(ConsumeCart cart:cartList){
			ConsumeList cl=new ConsumeList();
			cl.setLisId(this.serialGenDAO.genConsumeListId());
			cl.setConId(con_id);
			cl.setRepId(cart.getRepId());
			cl.setMatId(cart.getMatId());
			cl.setLisNum(cart.getCartNum());
			cl.setLisPrice(cart.getCartPerprice());
			this.consumeListDAO.save(cl);
			con_amount=con_amount.add(cart.getCartPerprice().multiply(new BigDecimal(cart.getCartNum())));
			//修改维修材料库存
			if(cart.getMatId()!=null&&!cart.getMatId().equals("")){
				MaterialId materialId=new MaterialId();
				materialId.setBraId(uc.getStafferBranchId());
				materialId.setMatId(cart.getMatId());
				materialId.setMatStat(DicDataUtil.DICDATA_000000);
				Material material=this.materialDAO.findById(materialId);
				material.setMatNum(material.getMatNum()-cart.getCartNum());
				this.materialDAO.update(material);
			}
		}
		
		ac.setConAmount(con_amount);
		Integer point=DataUtil.calculatePoint(con_amount);
		//付款方式，暂定现金
		ac.setConType(DicDataUtil.PAYMENTMODE_000901);
		if(!assId.equals(DicDataUtil.ASSOCIATOR_DEFAULT)){
			//如果不是默认会员，则 实收金额根据会员级别计算
			Associator ass=this.associatorDAO.findById(assId);
			BigDecimal assBalance=ass.getAssBalance();
			BigDecimal assPbalance=ass.getAssPbalance();
			
			BigDecimal conRamount=DataUtil.getDiscountAmount(con_amount,ass.getAssLevel());
			ac.setConRamount(conRamount);
			//积分
			ac.setConPoint(point);
			//修改会员表余额、赠送余额
			//判断会员表中余额和赠送余额是否为0，没有充值
			boolean hasBal=assBalance.add(assPbalance).doubleValue()>0;
			if(assBalance.subtract(conRamount).doubleValue()>=0){
				//会员余额大于应付款，直接修改会员表 余额
				ass.setAssBalance(assBalance.subtract(conRamount));
				ac.setConType(DicDataUtil.PAYMENTMODE_000907);
			}else if((assBalance.add(assPbalance)).subtract(conRamount).doubleValue()>=0){
				//会员余额+赠款余额>=应付款，直接修改会员余额
				ass.setAssBalance(new BigDecimal(0));
				ass.setAssPbalance(ass.getAssPbalance().subtract(conRamount.subtract(assBalance)));
				ac.setConType(DicDataUtil.PAYMENTMODE_000907);
			}else if(conRamount.subtract(assBalance.add(assPbalance)).doubleValue()>0&&hasBal){
				//应付款>会员余额+赠款余额&&会员余额和赠款余额不为零
				//会员余额和赠款余额清零
				ass.setAssBalance(new BigDecimal(0));
				ass.setAssPbalance(new BigDecimal(0));
				String conDesc="会员余额使用:"+assBalance.add(assPbalance).toPlainString()+//
												";现金付款:"+conRamount.subtract(assBalance.add(assPbalance)).toPlainString();
				ac.setConDesc(conDesc);
				ac.setConType(DicDataUtil.PAYMENTMODE_000906);
			}else if(conRamount.subtract(assBalance.add(assPbalance)).doubleValue()>0&&!hasBal){
				//应付款>会员余额+赠款余额&&会员余额和赠款余额为零
				//全部使用现金,不做处理
				
			}
			//修改会员信息
			ass.setAssPoint(ass.getAssPoint()+point);
			ass.setAssLevel(DataUtil.getNewAssLevel(ass.getAssPoint()));
			this.associatorDAO.update(ass);
		}else{
			//如果是默认会员，则 消费金额==实收金额
			ac.setConRamount(con_amount);
			ac.setConPoint(0);
		}
		ac.setConCollect(new BigDecimal(0));
		ac.setConChange(new BigDecimal(0));
		this.assConsumeDAO.save(ac);
		this.consumeCartDAO.deleteAll();
		return con_id;
	}

	public List findUsingChartHisQry(Map<String, String> paramap) {
		return this.assConsumeDAO.findUsingChartHisQry(paramap);
	}

	public String addManualConsume(UserContext uc, String assId,String amountStr) throws IOException {
		Associator ass=this.associatorDAO.findById(assId);
		BigDecimal amount=new BigDecimal(amountStr);
	
		
		//step 1:会员消费表 ASS_CONSUME新增一条数据
		//step 2:修改会员表关联信息
		AssConsume ac=new AssConsume();
		String con_id=this.serialGenDAO.genConsumeId();
		ac.setConId(con_id);
		ac.setAssId(assId);
		ac.setConDate(con_id.substring(0,8));
		ac.setConTime(con_id.substring(8,14));
		ac.setStaId(uc.getStafferId());
		ac.setBraId(uc.getStafferBranchId());
		ac.setConMark(DicDataUtil.CONSUMEMARK_001003);//消费标志：手工录入消费金额
		ac.setConAmount(amount);
		//付款方式，暂定现金
		ac.setConType(DicDataUtil.PAYMENTMODE_000901);
		
		BigDecimal conRamount=DataUtil.getDiscountAmount(amount,ass.getAssLevel());
		ac.setConRamount(conRamount);
		Integer point=DataUtil.calculatePoint(conRamount);
		ac.setConPoint(point);
		
		BigDecimal assBalance=ass.getAssBalance();
		BigDecimal assPbalance=ass.getAssPbalance();
		//修改会员表余额、赠送余额
		//判断会员表中余额和赠送余额是否为0，没有充值
		boolean hasBal=assBalance.add(assPbalance).doubleValue()>0;
		if(assBalance.subtract(conRamount).doubleValue()>=0){
			//会员余额大于应付款，直接修改会员表 余额
			ass.setAssBalance(assBalance.subtract(conRamount));
			ac.setConType(DicDataUtil.PAYMENTMODE_000907);
		}else if((assBalance.add(assPbalance)).subtract(conRamount).doubleValue()>=0){
			//会员余额+赠款余额>=应付款，直接修改会员余额
			ass.setAssBalance(new BigDecimal(0));
			ass.setAssPbalance(ass.getAssPbalance().subtract(conRamount.subtract(assBalance)));
			ac.setConType(DicDataUtil.PAYMENTMODE_000907);
		}else if(conRamount.subtract(assBalance.add(assPbalance)).doubleValue()>0&&hasBal){
			//应付款>会员余额+赠款余额&&会员余额和赠款余额不为零
			//会员余额和赠款余额清零
			ass.setAssBalance(new BigDecimal(0));
			ass.setAssPbalance(new BigDecimal(0));
			String conDesc="会员余额使用:"+assBalance.add(assPbalance).toPlainString()+//
											";现金付款:"+conRamount.subtract(assBalance.add(assPbalance)).toPlainString();
			ac.setConDesc(conDesc);
			ac.setConType(DicDataUtil.PAYMENTMODE_000906);
		}else if(conRamount.subtract(assBalance.add(assPbalance)).doubleValue()>0&&!hasBal){
			//应付款>会员余额+赠款余额&&会员余额和赠款余额为零
			//全部使用现金
			ac.setConType(DicDataUtil.PAYMENTMODE_000901);//付款方式：现金
		}
		//修改会员信息
		ass.setAssPoint(ass.getAssPoint()+point);
		ass.setAssLevel(DataUtil.getNewAssLevel(ass.getAssPoint()));
		this.associatorDAO.update(ass);
		
		ac.setConCollect(new BigDecimal(0));
		ac.setConChange(new BigDecimal(0));
		this.assConsumeDAO.save(ac);
		return con_id;
	}

}
