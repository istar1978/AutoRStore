package com.zhaozhy.autorstore.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.zhaozhy.autorstore.entity.AssCombo;
import com.zhaozhy.autorstore.entity.AssConsume;
import com.zhaozhy.autorstore.entity.Associator;
import com.zhaozhy.autorstore.entity.Branch;
import com.zhaozhy.autorstore.entity.ConsumeCart;
import com.zhaozhy.autorstore.entity.ConsumeList;
import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;
import com.zhaozhy.autorstore.entity.RepairItem;
import com.zhaozhy.autorstore.entity.Staffer;
import com.zhaozhy.autorstore.exception.DataNotEnoughException;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.OtherException;
import com.zhaozhy.autorstore.exception.SystemErrorException;
import com.zhaozhy.autorstore.form.ValidatorAssComboForm;
import com.zhaozhy.autorstore.form.ValidatorConsumeCartForm;
import com.zhaozhy.autorstore.form.ValidatorMaterialForm;
import com.zhaozhy.autorstore.form.ValidatorSalesForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.DataUtil;
import com.zhaozhy.autorstore.util.DateUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;

/**
 * 
 * @author zhaozy
 * 
 */
@SuppressWarnings("unchecked")
public class SalesAction extends BaseAction<AssConsume> {
	private static final Log log = LogFactory.getLog(SalesAction.class);

	/**
	 * 转向前询问页面(是否是会员)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward salesInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DicDataId did = new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0002);
		List<DicData> ddList = this.dicDataService.findByIdProperty(did);
		Map yornMap = DictionaryViews.getSelectMap(ddList);
		
		request.getSession().setAttribute("yornMap", yornMap);
		
		return mapping.findForward("success");
	}

	/**
	 * 输入会员卡号后初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward inputInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorSalesForm vsf = (ValidatorSalesForm) form;
		boolean has = false;

		String ass_id = vsf.getAss_id();
		String yorn=vsf.getYorn();
		//如果使用套餐，判断该会员是否有套餐，没有提示
		if(yorn!=null&&yorn.equals(DicDataUtil.DICDATA_00020)){
			//查询出ass_id对应的会员卡有哪些已购套餐
			//有效性限制：1.ass_id,2.状态con_stat正常，3.剩余次数con_tim>0，4.当前日期在起始日期和结束日期之间
			List<AssCombo> assComboList=this.assComboService.queryAssComboInUsing(ass_id, new Date());
			
			this.clearSessionByPro(request, "comboList");
			request.getSession().setAttribute("comboList", this.assComboService.transformList2ViewList(assComboList));
			request.setAttribute("ass_id", ass_id);
			return mapping.findForward("combo");
		}
		
		Associator associator = null;
		if (ass_id != null&&!ass_id.equals("")) {
				associator = this.associatorService.findById(ass_id);
				if (associator == null) {
					log.error("associator is null");
					throw new DataNotFoundException("");
				}
				has = true;
		}
//销售页面可以没有会员卡号，做如下处理
		if (!has) {
			associator = new Associator();
			associator.setAssId(DicDataUtil.ASSOCIATOR_DEFAULT);

		}

		this.clearSessionByPro(request, "associator");
		request.getSession().setAttribute("associator", associator);

		List<ConsumeCart> cartList = new ArrayList();

		this.clearSessionByPro(request, "cartList");
		request.getSession().setAttribute("cartList", cartList);
		//为页面准备数据，维修项目下拉列表repList；维修材料下拉列表matList
		this.clearSessionByPro(request, "repList");
		this.clearSessionByPro(request, "matList");
		List<RepairItem> repListSrc=(List<RepairItem>)this.repairItemService.findByRepStat(DicDataUtil.DICDATA_000000);//只查询有效的数据
		request.getSession().setAttribute("repList", this.repairItemService.transformList2ViewList(repListSrc));
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		List<Material> matListSrc=this.materialService.findByBraidStat(uc.getStafferBranchId(), DicDataUtil.DICDATA_000000);
		request.getSession().setAttribute("matList", DataUtil.getSelectListMat(matListSrc));
		
		this.fillEcsideCart(request, this.consumeCartService.findByAssId(associator.getAssId()), (UserContext)request.getSession().getAttribute("userContext"));
		this.clearSalesForm(vsf);
		return mapping.findForward("success");
	}
	/**
	 * 
	 * @CreateDate	2017年8月9日  下午8:31:56
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					输入会员卡号后初始化-V1
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward inputInitV1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean has = false;
		List list=ServletUtils.getParameterMaps(request);
		Map paramap=(Map)list.get(0);
		String ass_id=(String)paramap.get("ass_id");

		String yorn=(String)paramap.get("yorn");
		
		if(yorn!=null&&yorn.equals(DicDataUtil.DICDATA_00020)){
			//手工录入消费金额
			//会员号暂定 不能为空
			Associator ass=this.associatorService.findById(ass_id);
			request.setAttribute("ass", ass);
			return mapping.findForward("manual");
		}
		//非 手工录入消费金额
		//会员号可以为空
		Associator associator = null;
		if (ass_id != null&&!ass_id.equals("")) {
				associator = this.associatorService.findById(ass_id);
				if (associator == null) {
					log.error("associator is null");
					throw new DataNotFoundException("");
				}
				has = true;
		}
//销售页面可以没有会员卡号，做如下处理
		if (!has) {
			associator = new Associator();
			associator.setAssId(DicDataUtil.ASSOCIATOR_DEFAULT);

		}

		this.clearSessionByPro(request, "associator");
		request.getSession().setAttribute("associator", associator);
		List<ConsumeCart> cartList = new ArrayList();

		this.clearSessionByPro(request, "cartList");
		request.getSession().setAttribute("cartList", cartList);
		//为页面准备数据，维修项目下拉列表repList；维修材料下拉列表matList
		this.clearSessionByPro(request, "repList");
		this.clearSessionByPro(request, "matList");
		List<RepairItem> repListSrc=(List<RepairItem>)this.repairItemService.findByRepStat(DicDataUtil.DICDATA_000000);//只查询有效的数据
		request.getSession().setAttribute("repList", this.repairItemService.transformList2ViewList(repListSrc));
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		List<Material> matListSrc=this.materialService.findByBraidStat(uc.getStafferBranchId(), DicDataUtil.DICDATA_000000);
		request.getSession().setAttribute("matList", DataUtil.getSelectListMat(matListSrc));
		
		this.fillEcsideCart(request, this.consumeCartService.findByAssId(associator.getAssId()), (UserContext)request.getSession().getAttribute("userContext"));
		return mapping.findForward("success");
	}
	/**
	 * 
	 * @CreateDate	2017-6-19  下午01:01:58
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					选择完套餐后，点击确定进入此方法，直接进行表操作，不加入购物车
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cartComboAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ValidatorAssComboForm vform=(ValidatorAssComboForm)form;
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		//返回值为 新生成的AssConsume表主键
		 String numStr = this.assComboService.addCartCombo(vform, uc);

		if (numStr !=null) {
			log.info("cartComboAdd OK!");
//			this.clearAssComboForm(vform);
//			throw new MannulSuccessException("");
		}
		AssCombo ac=this.assComboService.findById(vform.getCom_id());
		//转向 购物清单（套餐） 页面，准备数据
		List dataList=new ArrayList();
		String data1=DateUtil.format8To10(numStr.substring(0,8));//日期
		String data2=DateUtil.format6To8(numStr.substring(8,14));//时间
		String data3=uc.getStafferBranchId();	//机构编号
		String data4=uc.getStafferId();//操作员编号
		String data5=vform.getAss_id();//会员号
		String data6=vform.getCom_id();//套餐编号
		String data7=ac.getRepId();//套餐所属 项目编号
		RepairItem ri=this.repairItemService.findById(data7);
		String data8=ri.getRepMoney().toString(); //维修项目 价格
		String data9=data3+":"+uc.getStafferBranchName();//机构编号:机构名称
		String data10=data4+":"+uc.getStafferName();//操作员编号:操作员姓名
		String data11=data6+":"+ac.getComName();//套餐编号:套餐名称
		String data12=data7+":"+ri.getRepName();//项目编号:项目名称
		dataList.add(new ViewBean4(data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12));
		
		request.setAttribute("data", dataList);
		request.setAttribute("sumPrice", data8);// 本次购物总价
		request.setAttribute("time", data2);// 本次购物具体时间(20:20:20)
		request.setAttribute("date", data1);// 本次购物日期
		request.setAttribute("branchName", data9);// 机构名称
		request.setAttribute("stafferId",data10);// 操作职员编号
		request.setAttribute("sumPoint", 0);//本次消费积分
		request.setAttribute("aSumPoint", this.associatorService.findById(vform.getAss_id()).getAssPoint());//会员总积分
		request.setAttribute("con_id",numStr);
		request.setAttribute("com_id", data6);
		
		return mapping.findForward("success");
	}
	/**
	 * 
	 * @CreateDate	2017年8月9日  下午8:45:17
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					选择手工录入消费金额后，点击确定进入此方法，直接进行表操作，不加入购物车
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cartManualAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		List list=ServletUtils.getParameterMaps(request);
		Map paramap=(Map)list.get(0);
		String ass_id=(String)paramap.get("ass_id");
		Associator associator=this.associatorService.findById(ass_id);
		String amount=(String)paramap.get("amount");
		//返回值为 新生成的AssConsume表主键
		String conId=this.assConsumeService.addManualConsume(uc, ass_id, amount);
		if(conId!=null){
			log.info("cartManualAdd ok!");
		}
		List<AssConsume> conList=new ArrayList<AssConsume>();
		AssConsume ass=this.assConsumeService.findById(conId);
		conList.add(ass);
		List<ViewBean4> dataList=this.transformAssConsume(conList);
		
		request.setAttribute("data", dataList);
		request.setAttribute("sumPrice", ass.getConAmount().toPlainString());// 本次购物总价
		request.setAttribute("time", DateUtil.format6To8(ass.getConTime()));// 本次购物具体时间(20:20:20)
		request.setAttribute("date", DateUtil.format8To10(ass.getConDate()));// 本次购物日期
		request.setAttribute("branchName", uc.getStafferBranchName());// 机构名称
		request.setAttribute("stafferId",uc.getStafferId());// 操作职员编号
		request.setAttribute("sumPoint", ass.getConPoint());//本次消费积分
		request.setAttribute("aSumPoint", associator.getAssPoint());//会员总积分
		request.setAttribute("con_id",conId);
//		request.setAttribute("assBalance", associator.getAssBalance().toPlainString());
//		request.setAttribute("assPbalance", associator.getAssPbalance().toPlainString());
		
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @CreateDate	2017-6-21  下午01:49:40
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward settleAccountComJsp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String con_id=(String)request.getAttribute("con_id");//消费流水号
		String com_id=(String)request.getAttribute("com_id");//套餐编号
		this.settleComJsp(request, 
											this.assConsumeService.findById(con_id), 
											(UserContext)request.getSession().getAttribute("userContext"), 
											this.assComboService.findById(com_id));
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @CreateDate	2017年8月9日  下午8:47:04
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					手工录入消费金额页面，提交结账
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward settleAccountManualJsp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017-6-21  下午01:38:56
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					为ECside展现准备数据
	 * @param request
	 * @param assConsume
	 * @param uc
	 * @param ac
	 */
	public void settleComJsp(HttpServletRequest request,AssConsume assConsume,UserContext uc,AssCombo ac){
		List dataList=new ArrayList();
		String data1=DateUtil.format8To10(assConsume.getAssId().substring(0,8));//日期
		String data2=DateUtil.format6To8(assConsume.getAssId().substring(8, 14));//时间
		String data3=uc.getStafferBranchId();	//机构编号
		String data4=uc.getStafferId();//操作员编号
		String data5=assConsume.getAssId();//会员号
		String data6=assConsume.getComId();//套餐编号
		String data7=ac.getRepId();//套餐所属 项目编号
		RepairItem ri=this.repairItemService.findById(data7);
		String data8=ri.getRepMoney().toString(); //维修项目 价格
		String data9=data3+":"+uc.getStafferBranchName();//机构编号:机构名称
		String data10=data4+":"+uc.getStafferName();//操作员编号:操作员姓名
		String data11=data6+":"+ac.getComName();//套餐编号:套餐名称
		String data12=data7+":"+ri.getRepName();//项目编号:项目名称
		dataList.add(new ViewBean4(data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12));
		
		request.setAttribute("data", dataList);
		request.setAttribute("sumPrice", data8);// 本次购物总价
		request.setAttribute("time", data2);// 本次购物具体时间(20:20:20)
		request.setAttribute("date", data1);// 本次购物日期
		request.setAttribute("branchName", data9);// 机构名称
		request.setAttribute("stafferId", data10);// 操作职员编号
		request.setAttribute("sumPoint", 0);//本次消费积分
		
		request.setAttribute("aSumPoint", this.associatorService.findById(data5).getAssPoint());//会员总积分
	}

	/**
	 * 
	 * @CreateDate	2017-7-2  上午11:52:28
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					会员不使用套餐，下拉列表选择维修项目/维修材料，点击添加后的操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cartAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ValidatorConsumeCartForm vform=(ValidatorConsumeCartForm)form;
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		String repId=vform.getRep_id();
		String matId=vform.getMat_id();
		String cartNum=vform.getCart_num();
		String assId=vform.getAss_id();
		List<ConsumeCart> ccList=this.consumeCartService.findByAssId(assId);//取出当前ass_id购物车中的数据
		
		ConsumeCart cc=new ConsumeCart();
		cc.setAssId(assId);
		cc.setCartNum(Integer.parseInt(cartNum));
		if(repId!=null&&!repId.equals("")){
			if(matId!=null&&!matId.equals("")){
				log.info("维修项目和维修材料只能选择一项！");
				throw new OtherException("");
			}
			//选择了维修项目，没有选择维修材料
			cc.setRepId(repId);//维修项目编号
			RepairItem ri=this.repairItemService.findById(repId);
			cc.setCartPerprice(ri.getRepMoney());//单价
			cc.setCartMoney(cc.getCartPerprice().multiply(new BigDecimal(cartNum)));
		}else if(matId!=null&&!matId.equals("")){
			//选择了维修材料，没有选择维修项目
			cc.setMatId(matId);
			MaterialId mi=new MaterialId();
			mi.setMatId(matId);
			mi.setBraId(uc.getStafferBranchId());
			mi.setMatStat(DicDataUtil.DICDATA_000000);
			Material m=this.materialService.findById(mi);
			if(m.getMatNum()<Integer.parseInt(cartNum)){
				log.info("维修材料库存不足！");
				throw new DataNotEnoughException("");
			}
			cc.setCartPerprice(this.materialService.getPerPriceByMatId(matId));//单价
			cc.setCartMoney(cc.getCartPerprice().multiply(new BigDecimal(cartNum)));
		}
		//新增前，判断cart里是否有rep_id/mat_id，若有，只是数量增加
		ConsumeCart cartTmp=this.consumeCartService.ifHasSameItem(cc);
		if(cartTmp==null){
			this.consumeCartService.save(cc);
			ccList.add(cc);
		}else{
			ccList.remove(cartTmp);
			cartTmp.setCartNum(cartTmp.getCartNum()+Integer.parseInt(cartNum));
			cartTmp.setCartMoney(cartTmp.getCartPerprice().multiply(new BigDecimal(cartTmp.getCartNum())));
			this.consumeCartService.update(cartTmp);
			
			ccList.add(cartTmp);
		}
		//在页面下方ecside展现
//		List<ConsumeCart> ccList=this.consumeCartService.findAll();
//		if(ccList.size()<1){
//			ccList.add(cc);
//		}
		this.fillEcsideCart(request, ccList, uc);
		
		return mapping.findForward("success");
	}
	/**
	 * 
	 * @CreateDate	2017-7-2  上午11:50:00
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					使用场景：会员选购完成后，结账页面；从表ConsumeList中取数据
	 * @param assId
	 * @param cList
	 * @param uc
	 */
	private List<ViewBean4> fillEcsideSettleAccount(String assId,List<ConsumeList> cList,UserContext uc){
		BigDecimal sumPrice=new BigDecimal(0);
		List<ViewBean4> dataList=new ArrayList();
		for(ConsumeList con:cList){
			String data1=assId;
			String data2=con.getRepId();
			String data3=con.getMatId();
			String data4=con.getLisNum().toString();
			String data5=con.getLisPrice().toPlainString();
			String data6=con.getLisPrice().multiply(new BigDecimal(con.getLisNum())).toPlainString();
			String data7=new String("");
			if(data2!=null&&!data2.equals("")){
				RepairItem rep=this.repairItemService.findById(data2);
				data7=rep.getRepId()+":"+rep.getRepName();//维修项目编号:维修项目名称
				sumPrice=sumPrice.add(rep.getRepMoney().multiply(new BigDecimal(Integer.parseInt(data4))));
			}
			String data8=new String("");
			if(data3!=null&&!data3.equals("")){
				MaterialId mi=new MaterialId();
				mi.setBraId(uc.getStafferBranchId());
				mi.setMatId(data3);
				mi.setMatStat(DicDataUtil.DICDATA_000000);
				Material mat=this.materialService.findById(mi);
				data8=mat.getId().getMatId()+":"+mat.getMatName();//维修材料编号:维修材料名称
				sumPrice=sumPrice.add(mat.getMatRealprice().multiply(new BigDecimal(Integer.parseInt(data4))));
			}
			dataList.add(new ViewBean4(data1,data2,data3,data4,data5,data6,data7,data8));
		}
		return dataList;
	}
	/**
	 * 
	 * @CreateDate	2017-6-23  上午11:14:28
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					场景：会员不使用套餐，添加购物车后，下方显示列表，从表ConsumeCart中取数据
	 * @param request
	 * @param ccList
	 * @param uc
	 */
	private void fillEcsideCart(HttpServletRequest request,List<ConsumeCart> ccList,UserContext uc){
		BigDecimal sumPrice=new BigDecimal(0);
		List dataList=new ArrayList();
		String assId=null;
		for(ConsumeCart cart:ccList){
			String data9=cart.getCartNo().toString();//主键编号
			String data1=cart.getAssId();//会员卡号
			assId=data1;
			String data2=cart.getRepId();//维修项目编号
			String data3=cart.getMatId();//维修材料编号
			String data4=cart.getCartNum().toString();//数量
			String data5=cart.getCartPerprice().toString();//单价
			String data6=cart.getCartMoney().toString();//总价
			
			String data7=new String("");
			if(data2!=null&&!data2.equals("")){
				RepairItem rep=this.repairItemService.findById(data2);
				data7=rep.getRepId()+":"+rep.getRepName();//维修项目编号:维修项目名称
				sumPrice=sumPrice.add(rep.getRepMoney().multiply(new BigDecimal(Integer.parseInt(data4))));
			}
			String data8=new String("");
			if(data3!=null&&!data3.equals("")){
				MaterialId mi=new MaterialId();
				mi.setBraId(uc.getStafferBranchId());
				mi.setMatId(data3);
				mi.setMatStat(DicDataUtil.DICDATA_000000);
				Material mat=this.materialService.findById(mi);
				data8=mat.getId().getMatId()+":"+mat.getMatName();//维修材料编号:维修材料名称
				sumPrice=sumPrice.add(mat.getMatRealprice().multiply(new BigDecimal(Integer.parseInt(data4))));
			}
			dataList.add(new ViewBean4(data1,data2,data3,data4,data5,data6,data7,data8,data9));
		}
		this.clearAllByPro(request, "data");
		this.clearAllByPro(request, "sumPrice");
		request.setAttribute("data", dataList);
		request.setAttribute("sumPrice", sumPrice.toString());
		
		this.clearAllByPro(request, "assId");
		request.setAttribute("assId", assId);
	}

	/**
	 * 清空form
	 * 
	 * @param vdf
	 */
	public void clearForm(ValidatorMaterialForm vdf) {
		vdf.setDr_id("");
		vdf.setDr_num("");
	}

	/**
	 * 结账
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward settleAccounts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		Associator asstmp=(Associator)request.getSession().getAttribute("associator");
		String ass_id=asstmp.getAssId();
		
		String conId=this.assConsumeService.addCarConsume(uc, ass_id);//返回的conId可能为null
		if(conId==null){
			log.error("购物车为空！不能提交！");
			throw new OtherException("");
		}
		
		//重新查一遍数据库，得到最新的数据
		AssConsume assConsume=this.assConsumeService.findById(conId);
		if(!ass_id.equals(DicDataUtil.ASSOCIATOR_DEFAULT)){
			//如果不是默认会员，则从数据库中取值
			asstmp=this.associatorService.findById(ass_id);	
		}else{
			//若是默认会员，则不从数据库中取值
			asstmp.setAssPoint(0);
		}
		
		List<ConsumeList> cList=this.consumeListService.findByConId(conId);
//		this.fillEcsideCart(request, cartList, uc);
	List<ViewBean4> dataList=this.fillEcsideSettleAccount(ass_id, cList, uc);
		
	this.clearAllByPro(request, "data");
	this.clearAllByPro(request, "sumPrice");
	request.setAttribute("data", dataList);
	request.setAttribute("sumPrice", assConsume.getConAmount().toPlainString());
	
	
	this.clearAllByPro(request, "sumVal");//本次积分
	this.clearAllByPro(request, "aSumVal");//会员总积分
	this.clearAllByPro(request, "conId");//购物流水号
	this.clearAllByPro(request, "assId");//会员卡号
	request.setAttribute("sumVal", assConsume.getConPoint().toString());
	request.setAttribute("aSumVal", asstmp.getAssPoint());
	request.getSession().setAttribute("conId", conId);
	request.setAttribute("assId", assConsume.getAssId());
		
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017年8月29日  下午9:22:48
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					格式化 会员消费表数据，用于页面展现
	 * @param list
	 * @return
	 */
	public List<ViewBean4> transformAssConsume(List<AssConsume> list){
		List<ViewBean4> rtnList=new ArrayList<ViewBean4>();
		for(AssConsume ac:list){
			//data1:消费流水号     data2:会员卡号			data3:消费日期			data4:消费时间
			//data5:消费金额		data6:实收金额			data7:付款方式			data8:本次积分
			//data9:操作员编号	data10:交易机构			data11:消费标志			data12:套餐明细编号
			//data13:付款描述		data14:收款金额		data15:找零金额
			//data16:会员卡电话	data17:消费日期(2017-01-01)	data18:消费时间(18:18:18)
			//data19:付款方式		data20:操作员姓名		data21:交易机构名称		data22:套餐名称
			//data23:消费标志(手工录入消费金额)
			//data24:会员卡余额		data25:会员卡赠送余额
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
			String data24=new String("");
			String data25=new String("");
			if(!data2.equals(DicDataUtil.ASSOCIATOR_DEFAULT)){
				Associator associator=this.associatorService.findById(data2);
				data24=associator.getAssBalance().toPlainString();
				data25=associator.getAssPbalance().toPlainString();
			}
			
			rtnList.add(new ViewBean4(data1,data2,data3,data4,data5,data6,data7,
																		data8,data9,data10,data11,data12,data13,data14,data15,
																		data16,data17,data18,data19,data20,data21,data22,data23,data24,data25));
		}
		return rtnList;
	}
	/**
	 * 格式化数据
	 * 
	 * @param statisticList
	 * @return
	 */
	public List transfromStatistic(List statisticList) {

		List dataList = new ArrayList();
		Iterator it = statisticList.iterator();
		while (it.hasNext()) {
			AssConsume assConsume = (AssConsume) it.next();
			// data1:交易日期;data2:交易时间;data3:机构编号;data4:职员编号;

			// data5:会员编号;data6:药品编号;data7:药品数量;data8:单价;

			// data9:会员单价;data10:总价;data11:机构名称;data12:职员姓名;

			// data13:会员姓名;data14:药品名称;

			DateFormat dfdate = DateFormat.getDateInstance();
			DateFormat dftime = DateFormat.getTimeInstance();

			String data1 = dfdate.format(assConsume.getConDate());

			String data2 = assConsume.getConTime();

			String data3 = assConsume.getBraId();
			String data4 = assConsume.getStaId();
			String data5 = assConsume.getAssId();
//			String data6 = assConsume.getMatId();
//			String data7 = assConsume.getDNum().toString();
//			String data8 = assConsume.getPerPrice().toString();
//			String data9 = assConsume.getAPrice().toString();
			String data10 = assConsume.getConAmount().toString();

			Branch branch = this.branchService.findById(data3);

			if (branch == null) {
				System.out.println("salesAction:branch is null");
				throw new SystemErrorException("");
			}

			String data11 = branch.getBraName();

			Staffer staffer = this.stafferService.findById(data4);
			if (staffer == null) {
				System.out.println("salesAction: staffer is null");
				throw new SystemErrorException("");
			}

			String data12 = staffer.getStaName();

			Associator associator = this.associatorService.findById(data5);
			String associatorName = "";
			if (!"0000000000".equals(data5)) {
				if (associator == null) {
					System.out
							.println("salesAction.settleAccounts: associator is null");
					throw new SystemErrorException("");
				}

				associatorName = associator.getAssName();
			} else {
				associatorName = "顾客";
			}
			String data13 = associatorName;

			dataList.add(new ViewBean4(data1, data2, data3, data4, data5,
					 data10, data11, data12, data13));
		}
		return dataList;
	}

	/**
	 * 
	 * @CreateDate	2017-7-2  下午10:19:53
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					销售页面，无套餐，下方ec列表删除ConsumeCart项
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAjaxDeleteCartItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List list=ServletUtils.getParameterMaps(request);
		int[] results=this.consumeCartService.deleteCartItems(list);
		Map keys=(Map)list.get(0);
		String assId=(String)keys.get("data1");
		
		this.clearAllByPro(request, "sumPrice");
		request.setAttribute("sumPrice", this.consumeCartService.getSumPriceByAssId(assId));
		ServletUtils.defaultAjaxResopnse(list, results, request, response);
		return null;
	}

	/**
	 * 
	 * @CreateDate	2017-7-7  上午08:26:38
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					结账页面导出功能
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward settleAccountsOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map> parasList=ServletUtils.getParameterMaps(request);
		String conId=null;
		for(Map map:parasList){
			conId=(String)map.get("conId");
		}
		if(conId==null||conId.equals("")){
			log.error("导出出错，请确认！");
			throw new OtherException("");
		}
		AssConsume ac=this.assConsumeService.findById(conId);
		Associator associator=this.associatorService.findById(ac.getAssId());
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		List cList=this.consumeListService.findByConId(conId);
		List<ViewBean4> dataList=this.fillEcsideSettleAccount(associator.getAssId(), cList, uc);
		
		this.clearAllByPro(request, "data");
		this.clearAllByPro(request, "sumPrice");
		request.setAttribute("data", dataList);
		request.setAttribute("sumPrice", ac.getConAmount().toPlainString());
		
		
		this.clearAllByPro(request, "sumVal");//本次积分
		this.clearAllByPro(request, "aSumVal");//会员总积分
		this.clearAllByPro(request, "conId");//购物流水号
		this.clearAllByPro(request, "assId");//会员卡号
		request.setAttribute("sumVal", ac.getConPoint().toString());
		request.setAttribute("aSumVal", associator.getAssPoint());
		request.getSession().setAttribute("conId", conId);
		request.setAttribute("assId", ac.getAssId());
		
		return mapping.findForward("success");
	}
	/**
	 * 
	 * @CreateDate	2017年7月23日  上午10:18:12
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					验证cart中数量是否合规
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAjaxValidateCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List list=ServletUtils.getParameterMaps(request);
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		Map keys=(Map)list.get(0);
		String assId=(String)keys.get("assId");
		String resStr=this.consumeCartService.validateCartNum(assId, uc);
		
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");  
		PrintWriter pw = response.getWriter();  
        pw.write(resStr);  
        pw.flush();  
		return null;
	}
	/**
	 * 
	 * @CreateDate	2017年8月25日  下午8:14:27
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					判断会员卡号是否存在
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAjaxValidateAssId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List list=ServletUtils.getParameterMaps(request);
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		Map keys=(Map)list.get(0);
		String assId=(String)keys.get("assId");
		String resStr=this.associatorService.validateAssId(assId);
		
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");  
		PrintWriter pw = response.getWriter();  
        pw.write(resStr);  
        pw.flush();  
		return null;
	}
	
	
	public ActionForward doAjaxAddCart(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List list=ServletUtils.getParameterMaps(request);
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		Map keys=(Map)list.get(0);
		String assId=(String)keys.get("assId");
		String repId=(String)keys.get("repId");
		String matId=(String)keys.get("matId");
		String cartNum=(String)keys.get("cartNum");
		
List<ConsumeCart> ccList=this.consumeCartService.findByAssId(assId);//取出当前ass_id购物车中的数据
		
		ConsumeCart cc=new ConsumeCart();
		cc.setAssId(assId);
		cc.setCartNum(Integer.parseInt(cartNum));
		if(repId!=null&&!repId.equals("")){
			if(matId!=null&&!matId.equals("")){
				log.info("维修项目和维修材料只能选择一项！");
				throw new OtherException("");
			}
			//选择了维修项目，没有选择维修材料
			cc.setRepId(repId);//维修项目编号
			RepairItem ri=this.repairItemService.findById(repId);
			cc.setCartPerprice(ri.getRepMoney());//单价
			cc.setCartMoney(cc.getCartPerprice().multiply(new BigDecimal(cartNum)));
		}else if(matId!=null&&!matId.equals("")){
			//选择了维修材料，没有选择维修项目
			cc.setMatId(matId);
			MaterialId mi=new MaterialId();
			mi.setMatId(matId);
			mi.setBraId(uc.getStafferBranchId());
			mi.setMatStat(DicDataUtil.DICDATA_000000);
			Material m=this.materialService.findById(mi);
			if(m.getMatNum()<Integer.parseInt(cartNum)){
				log.info("维修材料库存不足！");
				throw new DataNotEnoughException("");
			}
			cc.setCartPerprice(this.materialService.getPerPriceByMatId(matId));//单价
			cc.setCartMoney(cc.getCartPerprice().multiply(new BigDecimal(cartNum)));
		}
		//新增前，判断cart里是否有rep_id/mat_id，若有，只是数量增加
		ConsumeCart cartTmp=this.consumeCartService.ifHasSameItem(cc);
		if(cartTmp==null){
			this.consumeCartService.save(cc);
			ccList.add(cc);
		}else{
			ccList.remove(cartTmp);
			cartTmp.setCartNum(cartTmp.getCartNum()+Integer.parseInt(cartNum));
			cartTmp.setCartMoney(cartTmp.getCartPerprice().multiply(new BigDecimal(cartTmp.getCartNum())));
			this.consumeCartService.update(cartTmp);
			
			ccList.add(cartTmp);
		}
		//在页面下方ecside展现
		this.fillEcsideCart(request, ccList, uc);
		
		String resStr="success!";
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");  
		PrintWriter pw = response.getWriter();  
        pw.write(resStr);  
        pw.flush();  
        ServletUtils.defaultAjaxResopnse(list, new int[1], request, response);
		return null;
	}
	
}
