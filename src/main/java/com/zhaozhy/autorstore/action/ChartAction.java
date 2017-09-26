package com.zhaozhy.autorstore.action;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ecside.util.ServletUtils;

import com.zhaozhy.autorstore.entity.AssConsume;
import com.zhaozhy.autorstore.entity.AssRecharge;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;
import com.zhaozhy.autorstore.entity.RepairItem;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.DataUtil;
import com.zhaozhy.autorstore.util.DateUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;

/**
 * 
 * @Title				ChartAction.java
 * @Package		com.zhaozhy.autorstore.action
 * @Created		zhaozhy  (zhongyong@qq.com)
 * @Date				2017-7-7   下午02:01:51
 * @Desc				报表查询相关action处理类
 * @Version 		V1.0
 *
 * @Modified
 * @Date
 * @Desc
 */
@SuppressWarnings("unchecked")
public class ChartAction extends BaseAction {

	private static final Log log=LogFactory.getLog(ChartAction.class);
	UserContext uc;
	
	/**
	 * 
	 * @CreateDate	2017年9月25日  下午10:04:03
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					会员充值流水查询   转向实际页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rechargeHisQryInit(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		return mapping.findForward("success");
	}
	/**
	 * 
	 * @CreateDate	2017-7-14  下午12:45:12
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					会员充值流水查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rechargeHisQry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("ChartAction-->rechargeHisQry()");
		List list=ServletUtils.getParameterMaps(request);
		Map paramap=(Map)list.get(0);
		String assId=(String)paramap.get("assId");
		String beginDate=(String)paramap.get("beginDate");
		String endDate=(String)paramap.get("endDate");
		
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("assId", assId);
		valueMap.put("beginDate", beginDate);
		valueMap.put("endDate", endDate);
		
		List srcList=this.assRechargeService.findUsingChartHisQry(valueMap);
		List dataList=this.transformRecharge2Jsp(srcList);
		
		this.clearAllByPro(request, "dataList");
		request.setAttribute("dataList", dataList);
		this.clearAllByPro(request, "beginDate");
		request.setAttribute("beginDate", beginDate);
		this.clearAllByPro(request, "endDate");
		request.setAttribute("endDate", endDate);
		
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017-7-8  下午05:11:34
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					消费明细查询，查询页面展现数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward consumeListHisQry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("ChartAction-->consumeListHisQry()");
		uc=(UserContext)request.getSession().getAttribute("userContext");
		List list=ServletUtils.getParameterMaps(request);
		Map paramap=(Map)list.get(0);
//		String assId=(String)paramap.get("assId");
		String beginDate=(String)paramap.get("beginDate");
		String endDate=(String)paramap.get("endDate");
		String repId=(String)paramap.get("repId");
		String matId=(String)paramap.get("matId");
		
		Map<String,String> valueMap=new HashMap<String,String>();
//		valueMap.put("assId", assId);
		valueMap.put("beginDate", beginDate);
		valueMap.put("endDate", endDate);
		valueMap.put("repId", repId);
		valueMap.put("matId", matId);
		
		@SuppressWarnings("unused")
		List<Map> rtnList=this.consumeListService.findUsingChartHisQry(valueMap);
		List<ViewBean4> dataList=this.transform2Jsp4List(rtnList);
		this.clearAllByPro(request, "dataList");
		request.setAttribute("dataList", dataList);
		this.clearAllByPro(request, "beginDate");
		request.setAttribute("beginDate", beginDate);
		this.clearAllByPro(request, "endDate");
		request.setAttribute("endDate", endDate);
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017-7-8  下午04:40:46
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					消费明细查询 初始化页面，准备下拉列表数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward consumeListHisQryInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("ChatAction-->consumeListHisQryInit()");
		//维修项目map
		List<RepairItem> repList=this.repairItemService.findAll();
		Map repMap=DataUtil.getOptionsEc4Rep(repList);
		//维修材料map
		List<Material> matList=this.materialService.findAll();
		Map matMap=DataUtil.getOptionsEc4Mat(matList);
		
		request.setAttribute("repMap", repMap);
		request.setAttribute("matMap", matMap);
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @CreateDate	2017年9月25日  下午10:00:12
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					消费历史查询   转向实际页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward consumeHisQryInit(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017-7-7  下午02:09:51
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					消费历史查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward consumeHisQry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List list=ServletUtils.getParameterMaps(request);
		Map paramap=(Map)list.get(0);
		String assId=(String)paramap.get("assId");
		String beginDate=(String)paramap.get("beginDate");
		String endDate=(String)paramap.get("endDate");
		
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("assId", assId);
		valueMap.put("beginDate", beginDate);
		valueMap.put("endDate", endDate);
		
		List listSrc=this.assConsumeService.findUsingChartHisQry(valueMap);
		List dataList=this.transformConsume2Jsp(listSrc);
		
		this.clearAllByPro(request, "dataList");
		request.setAttribute("dataList", dataList);
		this.clearAllByPro(request, "beginDate");
		request.setAttribute("beginDate", beginDate);
		this.clearAllByPro(request, "endDate");
		request.setAttribute("endDate", endDate);
		
//		ServletUtils.defaultAjaxResopnse(list, new int[1], request, response);
		return mapping.findForward("success");
	}
	/**
	 * 
	 * @CreateDate	2017-7-8  下午09:04:07
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					销售明细查询 组装数据供页面展现
	 * @param listMap
	 * @return
	 */
	public List<ViewBean4> transform2Jsp4List(List<Map> listMap){
		//data1:repId		data2:matId		data3:总数			data4:维修项目名称
		//data5:维修材料名称
		List<ViewBean4> rtnList=new ArrayList<ViewBean4>();
		for(Map map:listMap){
			String data1=null;
			String data2=null;
			String data3=null;
			String data4=null;
			String data5=null;

			data1=(String)map.get("REP_ID");
			data2=(String)map.get("MAT_ID");
			data3=(map.get("NSUM")).toString();
			
			if(data1!=null&&!data1.equals("")){
				data4=data1+":"+this.repairItemService.findById(data1).getRepName();
			}
			if(data2!=null&&!data2.equals("")){
				MaterialId mi=new MaterialId();
				mi.setBraId(uc.getStafferBranchId());
				mi.setMatId(data2);
				mi.setMatStat(DicDataUtil.DICDATA_000000);
				data5=data2+":"+this.materialService.findById(mi).getMatName();
			}
			rtnList.add(new ViewBean4(data1,data2,data3,data4,data5));
		}
		return rtnList;
	}
	/**
	 * 
	 * @CreateDate	2017-7-14  下午12:54:06
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					封装充值流水对象，供页面使用
	 * @param listSrc
	 * @return
	 */
	public List<ViewBean4> transformRecharge2Jsp(List<AssRecharge> listSrc){
		List<ViewBean4> rtnList=new ArrayList<ViewBean4>();
		for(AssRecharge ar:listSrc){
			//data1:充值流水号			data2:会员卡号			data3:充值金额
			//data4:充值日期				data5:充值时间			data6:赠送金额
			//data7:操作员编号			data8:备注
			//data9:会员电话号码		data10:操作员姓名
			//data11:充值日期 10位	data12:充值时间 8位
			String data1=ar.getRecId();
			String data2=ar.getAssId();
			String data3=ar.getRecAmount().toPlainString();
			String data4=ar.getRecDate();
			String data5=ar.getRecTime();
			String data6=ar.getRecPresent().toPlainString();
			String data7=ar.getStaId();
			String data8=ar.getRecText();
			String data9=this.associatorService.findById(data2).getAssPhone();
			String data10=new String("");
			if(data7!=null){
				data10=data7+":"+this.stafferService.findById(data7).getStaName();
			}
			String data11=DateUtil.format8To10(data4);
			String data12=DateUtil.format6To8(data5);
			
			rtnList.add(new ViewBean4(data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12));
		}
		return rtnList;
	}
	/**
	 * 
	 * @CreateDate	2017-7-8  上午07:37:26
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					封装销售流水对象，供页面使用
	 * @param listSrc
	 * @return
	 */
	public List<ViewBean4> transformConsume2Jsp(List<AssConsume> listSrc){
		List<ViewBean4> rtnList=new ArrayList<ViewBean4>();
		for(AssConsume ac:listSrc){
			//data1:消费流水号     data2:会员卡号			data3:消费日期			data4:消费时间
			//data5:消费金额		data6:实收金额			data7:付款方式			data8:本次积分
			//data9:操作员编号	data10:交易机构			data11:消费标志			data12:套餐明细编号
			//data13:付款描述		data14:收款金额		data15:找零金额
			//data16:会员卡电话	data17:消费日期(2017-01-01)	data18:消费时间(18:18:18)
			//data19:付款方式		data20:操作员姓名		data21:交易机构名称		data22:套餐名称
			//data23:消费标志(手工录入消费金额)
			String data1=ac.getConId();
			String data2=ac.getAssId();
			String data3=ac.getConDate();
			String data4=ac.getConTime();
			String data5=ac.getConAmount().toPlainString();
			String data6=ac.getConRamount().toPlainString();
			String data7=ac.getConType();
			String data8=ac.getConPoint().toString();
			String data9=ac.getStaId();
			String data10=ac.getBraId();
			String data11=ac.getConMark();
			String data12=ac.getComId();
			String data13=ac.getConDesc();
			String data14=ac.getConCollect().toPlainString();
			String data15=ac.getConChange().toPlainString();
			String data16=new String("");
			if(!data2.equals(DicDataUtil.ASSOCIATOR_DEFAULT)){
				data16=this.associatorService.findById(data2).getAssPhone();
			}
			String data17=DateUtil.format8To10(data3);
			String data18=DateUtil.format6To8(data4);
			String data19=data7+":"+dicDataService.findById(new DicDataId(DicDataUtil.PAYMENTMODE_0009,data7)).getDicName();
			String data20=data9+":"+this.stafferService.findById(data9).getStaName();
			String data21=data10+":"+this.branchService.findById(data10).getBraName();
			String data22=new String("");
			if(data12!=null&&!data12.equals("")){
				data22=this.assComboService.findById(data12).getComName();
			}
			String data23=data11+":"+this.dicDataService.findById(new DicDataId(DicDataUtil.CONSUMEMARK_0010,data11)).getDicName();
			
			rtnList.add(new ViewBean4(data1,data2,data3,data4,data5,data6,data7,
																		data8,data9,data10,data11,data12,data13,data14,data15,
																		data16,data17,data18,data19,data20,data21,data22,data23));
		}
		return rtnList;
	}

	
}
