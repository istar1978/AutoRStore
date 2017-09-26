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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ecside.table.limit.FilterSet;
import org.ecside.table.limit.Limit;
import org.ecside.table.limit.Sort;
import org.ecside.util.RequestUtils;
import org.ecside.util.ServletUtils;

import com.zhaozhy.autorstore.entity.AssConsume;
import com.zhaozhy.autorstore.entity.Associator;
import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.Static2;
import com.zhaozhy.autorstore.exception.AssociatorNotInUseException;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.DateErrorException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.exception.PasswordErrorException;
import com.zhaozhy.autorstore.exception.PasswordNotSameException;
import com.zhaozhy.autorstore.exception.RepeatActiveException;
import com.zhaozhy.autorstore.exception.SystemErrorException;
import com.zhaozhy.autorstore.form.ValidatorAssociatorForm;
import com.zhaozhy.autorstore.form.ValidatorTimeQueryForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.AssConsumeUtil;
import com.zhaozhy.autorstore.util.AssociatorUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.PageState;

/**
 * 
 * @Title:			AssociatorAction.java
 * @Package:		com.zhaozhy.autorstore.action
 * @Created：	zhaozhy
 * @Date：			2017-5-25   上午09:01:52
 * @Desc:			
 * @Version: 		V1.0
 *
 * @Modified：
 * @Date：
 * @Desc：
 * 
 * @Email : 		zhongyong@qq.com
 */
public class AssociatorAction extends BaseAction<Associator> {
	private static final Log log = LogFactory.getLog(AssociatorAction.class);
	/**
	 * 初始化页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ValidatorAssociatorForm vaf = (ValidatorAssociatorForm) form;
		// this.clearForm(vaf);
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @CreateDate	2017年9月25日  下午10:10:13
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					会员充值页面初始化   转向实际页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rechargeInitPage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		return mapping.findForward("success");
	}
	/**
	 * 
	 * 创建时间：2017-5-26  上午08:43:13
	 * 	创建者：zhaozhy
	 *	方法说明：会员充值页面初始化
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rechargeInit(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		ValidatorAssociatorForm vaf=(ValidatorAssociatorForm)form;
		String assId=vaf.getA_id();
		Associator ass=this.associatorService.findById(assId);
		//20170925 modified by zhaozhy 如果输入的会员号时系统默认会员号，报错
		if (ass == null||assId.equals(DicDataUtil.ASSOCIATOR_DEFAULT)) {
//			this.addToRequest(null, null, request, "*");
			log.error("associator is not exist!");
			throw new DataNotFoundException("");
		}
		if(ass.getAssStat().equals(DicDataUtil.DICDATA_000001)){
			//如果会员未激活，不能充值
			log.error("associator is not jihuo");
			throw new AssociatorNotInUseException("");
		}
		
		request.setAttribute("ass", this.transformAssociator(ass));
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * 创建时间：2017-5-26  上午09:52:16
	 * 	创建者：zhaozhy
	 *	方法说明：会员充值
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward recharge(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		ValidatorAssociatorForm vaf=(ValidatorAssociatorForm)form;
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		int num=this.associatorService.addRecharge(vaf,uc);
		
		Associator ass=this.associatorService.findById(vaf.getA_id());
		
//		this.addToRequest(null, null, request, "*");
		request.setAttribute("ass", this.transformAssociator(ass));
		if(num==1){
			log.info("AssociatorAction recharge mannu OK!");
			throw new MannulSuccessException("");
		}
		
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					TODO
	 *
	 *	 20170919 modified by zhaozhy:会员卡新增时，默认激活，取消系统激活会员卡功能
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward manage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ValidatorAssociatorForm vaf = (ValidatorAssociatorForm) form;
		AssociatorUtil associatorUtil = new AssociatorUtil(this.associatorService);
		String footer = "";
		String action = vaf.getAction();
		log.debug("action:"+action);
		String a_no = vaf.getA_id();

		// String a_password = vaf.getA_password();
		String a_level = vaf.getA_level();
		String a_stat = vaf.getA_stat();

		String a_name = vaf.getA_name();
		String a_gender = vaf.getA_gender();
		String a_birthday = vaf.getA_birthday();
		String a_addr = vaf.getA_addr();
		String a_tel = vaf.getA_tel();
		String a_carno=vaf.getA_carno();

		int num = 0;
		Associator associator = this.associatorService.findById(a_no);

		if (action != null) {
			if ("insert".equals(action.trim())) {
				if (associator != null) {
					log.error("associator is already exist");
					this.addToRequest(null, null, request, "*");
					throw new DataAlreadyExistException("");
				}

				Associator model = new Associator();
				model.setAssId(a_no);
				model.setAssPassword(DicDataUtil.ASSOCIATOR_DEFAULT_PASSWORD);
				model.setAssLevel(a_level);// 会员等级
			
				Date create_date = new Date();
				model.setAssCreate(create_date);
				if (StringUtils.isNotBlank(a_name)) {
						model.setAssName(a_name.trim());
				}
				if (StringUtils.isNotBlank(a_gender)) {
						model.setAssGender(a_gender.trim());
				}
				if (StringUtils.isNotBlank(a_birthday)) {
						DateFormat df = DateFormat.getDateInstance();
						Date birthday = df.parse(a_birthday.trim());
						model.setAssBirthday(birthday);
				}
				if(StringUtils.isNotBlank(a_addr)){
						model.setAssAddr(a_addr.trim());
					}
				if (StringUtils.isNotBlank(a_tel)) {
						model.setAssPhone(a_tel.trim());
				}

				//会员创建时，状态为无效，激活后有效
				//20170919 modified by zhaozhy 默认会员卡已激活
//				model.setAssStat(DicDataUtil.DICDATA_000001);
				model.setAssStat(DicDataUtil.DICDATA_000000);
				//20170919modified by zhaozhy 激活日期默认创建日期
				model.setAssActive(create_date);
				//会员积分初始值0
				model.setAssPoint(0);
				//余额、赠送余额初始值=0
				model.setAssBalance(new BigDecimal(0.0));
				model.setAssPbalance(new BigDecimal(0.0));
				this.associatorService.save(model);
				num = 1;
			} else if ("delete".equals(action.trim())) {
				if (associator == null) {
					log.error("associator is not exist!");
					this.addToRequest(null, null, request, "*");
					throw new DataNotFoundException("");
				}
				this.associatorService.delete(associator);
				num = 1;
				this.clearForm(vaf);
			} else if ("update".equals(action.trim())) {
				if (associator == null) {
					log.error("associator is not exist!");
					this.addToRequest(null, null, request, "*");
					throw new DataNotFoundException("");
				}
				associator.setAssLevel(a_level);
				if (StringUtils.isNotBlank(a_name)) {
						associator.setAssName(a_name.trim());
					}
				if (StringUtils.isNotBlank(a_gender)) {
						associator.setAssGender(a_gender.trim());
					}
				if (StringUtils.isNotBlank(a_birthday)) {
						DateFormat df = DateFormat.getDateInstance();
						Date birthday = df.parse(a_birthday.trim());
						associator.setAssBirthday(birthday);
				}
				if (StringUtils.isNotBlank(a_addr)) {
						associator.setAssAddr(a_addr.trim());
				}
				if (StringUtils.isNotBlank(a_tel)) {
						associator.setAssPhone(a_tel.trim());
				}
				if(StringUtils.isNotBlank(a_carno)){
						associator.setAssCarno(a_carno.trim());
				}
				this.associatorService.update(associator);
				num = 1;

			} else if ("query".equals(action.trim())) {
				List list = associatorUtil.queryPerPage(a_no, new PageState(
						request));
				footer = associatorUtil.getFooter();
				List dataList = new ArrayList();
				Iterator it = list.iterator();
				while (it.hasNext()) {
					Associator ator = (Associator) it.next();
					dataList.add(this.transformAssociator(ator));
				}
				this.addToRequest(footer, dataList, request, null);
			}
		}
		if (num > 0) {
			log.error("Mannul OK!");
			this.addToRequest(null, null, request, "*");
			throw new MannulSuccessException("");
		}
		return mapping.findForward("success");
	}

	/**
	 * 处理会员管理的转向请求
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward manageInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorAssociatorForm vaf = (ValidatorAssociatorForm) form;

		DictionaryViews dirView = new DictionaryViews();
		DicDataId dId=new DicDataId();
		dId.setDicLarge(DicDataUtil.DICDATA_0007);
		List sexList = dirView.getSelectList(this.dicDataService.findByIdProperty(dId));

		dId=new DicDataId();
		dId.setDicLarge(DicDataUtil.DICDATA_0000);
		List<DicData> sList=this.dicDataService.findByIdProperty(dId);
		
		List statList = dirView.getStatList(sList);
		
		dId=new DicDataId();
		dId.setDicLarge(DicDataUtil.DICDATA_0006);
		List<DicData> dList=this.dicDataService.findByIdProperty(dId);
		List levelList = dirView.getSelectList(dList);

		request.setAttribute("sexList", sexList);
		
		request.getSession().setAttribute("sexList", sexList);
		request.setAttribute("statList", statList);
		request.getSession().setAttribute("statList", statList);
		request.setAttribute("levelList", levelList);
		request.getSession().setAttribute("levelList", levelList);

		// this.clearForm(vaf);

		return mapping.findForward("success");
	}

	/**
	 * 激活会员卡操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jihuo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ValidatorAssociatorForm vaf = (ValidatorAssociatorForm) form;
		String a_id = vaf.getA_id();
		Associator associator = this.associatorService.findById(a_id.trim());

		if (associator == null) {
			log.error("associator is not exist!");
			throw new DataNotFoundException("");
		} else if (associator != null) {
			// 先判断状态是否已经是激活？
			String stat = associator.getAssStat();
			if (stat != null) {
				if (DicDataUtil.DICDATA_000000.equals(stat.trim())) {
					log.error("associator is already active");
					throw new RepeatActiveException("");
				}
			}

			// 将状态设为正常
			associator.setAssStat(DicDataUtil.DICDATA_000000);
			// 插入激活日期
			Date date = new Date();
			associator.setAssActive(date);
			this.associatorService.update(associator);
			// 清空form
			vaf.setA_id("");
			throw new MannulSuccessException("");
		}
		return mapping.findForward("success");
	}

	/**
	 * 取得格式化好的会员类
	 * 
	 * @return
	 */
	public List getAssociatorList() {
		List allList = this.associatorService.findAll();
		List dataList = new ArrayList();

		Iterator it = allList.iterator();
		while (it.hasNext()) {
			Associator associator = (Associator) it.next();

			dataList.add(this.transformAssociator(associator));
		}

		return dataList;
	}

	/**
	 * 格式化会员类
	 * 
	 * @param associator
	 * @return
	 */
	public ViewBean4 transformAssociator(Associator associator) {
		// data1:会员卡号;data2:会员姓名;data3:密码;data4:性别;
		// data5:出生日期;data6:住址;data7:联系电话;data8:积分;
		// data9:等级;data10:激活日期;data11:创建日期;data12:状态;
		//data13:性别(男);data14:出生日期(2010-01-01);data15:等级(一级会员);data16:激活日期(2010-
		// 11-11);
		// data17:创建日期(2010-01-01);data18:状态(正常);data19:车牌号码;data20:余额，data21:赠送余额;

		DateFormat df = DateFormat.getDateInstance();
		String data1 = associator.getAssId();
		String data2 = associator.getAssName();
		String data3 = associator.getAssPassword();
		String data4 = associator.getAssGender();

		Date birthdayDate = associator.getAssBirthday();

		String data5 = "";
		if (birthdayDate != null) {
			data5 = df.format(birthdayDate);
		}

		String data6 = associator.getAssAddr();
		String data7 = associator.getAssPhone();
		String data8 = associator.getAssPoint().toString();
		String data9 = associator.getAssLevel();
		Date activeDate = associator.getAssActive();

		String data10 = "";
		if (activeDate != null) {
			data10 = df.format(activeDate);
		}

		Date createDate = associator.getAssCreate();
		String data11 = df.format(createDate);
		String data12 = associator.getAssStat();
		//判断性别字段是否有值
		String data13=new String();
		if(StringUtils.isNotEmpty(data4)){
			data13=data4+":"+this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0007,data4)).getDicName().trim();
		}

		String data14 = data5;
		String data15 = data9+":"+this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0006,data9)).getDicName().trim();
		String data16 = data10;
		String data17 = data11;
		String data18 = data12+":"+this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0000,data12)).getDicName().trim();

		String data19=associator.getAssCarno();
		String data20=associator.getAssBalance().toPlainString();
		String data21=associator.getAssPbalance().toPlainString();
		return new ViewBean4(data1, data2, data3, data4, data5, data6, data7,
				data8, data9, data10, data11, data12, data13, data14, data15,
				data16, data17, data18,data19,data20,data21);
	}

	/**
	 * 修改密码
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorAssociatorForm vaf = (ValidatorAssociatorForm) form;
		int num = 0;

		String a_no = vaf.getA_id();

		Associator associator = this.associatorService.findById(a_no);
		if (associator == null) {
			log.error("associator is null");
			throw new DataNotFoundException("");
		}

		String oldPassword = vaf.getOldPassword();

		if (!associator.getAssPassword().trim().equals(oldPassword)) {
			log.error("oldpassword is error");
			throw new PasswordErrorException("");
		}
		String newPassword1 = vaf.getNewPassword1();
		String newPassword2 = vaf.getNewPassword2();

		if (!newPassword1.equals(newPassword2)) {
			log.error("the two passwords are not same!");
			throw new PasswordNotSameException("");
		}

		associator.setAssPassword(newPassword1);
		this.associatorService.update(associator);
		num = 1;

		if (num > 0) {
			log.error("mannul ok!");
			throw new MannulSuccessException("");
		}

		return mapping.findForward("success");
	}

	/**
	 * 
	 * @param footer
	 * @param dataList
	 * @param request
	 * @param no
	 */
	@SuppressWarnings("unchecked")
	public void addToRequest(String footer, List dataList,
			HttpServletRequest request, String no) {
		if (no == null) {
			if (footer != null) {
				if (!"".equals(footer.trim())) {
					request.setAttribute("footer", footer);
				}
			}
			if (dataList != null) {
				request.setAttribute("dataList", dataList);
			}
		}
		if (no != null) {
			AssociatorUtil associatorUtil = new AssociatorUtil(
					this.associatorService);
			String footer1 = "";
			List dataList1 = associatorUtil.queryPerPage("*", new PageState(
					request));
			List dataList2 = new ArrayList();
			Iterator it1 = dataList1.iterator();
			while (it1.hasNext()) {
				Associator ar = (Associator) it1.next();
				dataList2.add(this.transformAssociator(ar));
			}
			footer = associatorUtil.getFooter();

			request.setAttribute("dataList", dataList2);
			request.setAttribute("footer", footer1);
		}
		
		DictionaryViews dirView = new DictionaryViews();
		
		DicDataId did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0007);
		List sexList =dirView.getSelectList(this.dicDataService.findByIdProperty(did));

		did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0000);
		List statList = dirView.getSelectList(this.dicDataService.findByIdProperty(did));
		
		did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0006);
		List levelList = dirView.getSelectList(this.dicDataService.findByIdProperty(did));

		request.setAttribute("sexList", sexList);
		request.setAttribute("statList", statList);
		request.setAttribute("levelList", levelList);
	}

	/**
	 * 清空form
	 * 
	 * @param form
	 */
	public void clearForm(ValidatorAssociatorForm form) {
		form.setA_no("");
		form.setA_name("");
		form.setA_birthday("");
		form.setA_gender("1");
		form.setA_addr("");
		form.setA_level("");
		form.setA_stat("");
		form.setA_tel("");
		form.setAction("");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward associatorPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int currentPage = 1;
		int lineSize = 10;// 每页显示的记录数
		int allRecorders = 0;// 数据库中总的记录数

		Limit limit = RequestUtils.getLimit(request);
		Sort sort = limit.getSort();

		Map sortValueMap = sort.getSortValueMap();
		FilterSet filterSet = limit.getFilterSet();

		Map filterPropertyMap = filterSet.getPropertyValueMap();

		// allRecorders = sysOptDAO.getAllCount(sortValueMap,
		// filterPropertyMap);
		allRecorders = this.associatorService.findAll().size();

		int[] rowStartEnd = RequestUtils.getRowStartEnd(request, allRecorders,
				lineSize);
		if (lineSize > allRecorders) {
			currentPage = 1;
		}

		List associatorList = this.associatorService.findAllOrderByVal();
		List dataList = new ArrayList();
		Iterator it = associatorList.iterator();
		while (it.hasNext()) {
			Associator associator = (Associator) it.next();
			dataList.add(this.transformAssociator(associator));
		}

		request.setAttribute("data", dataList);

		return mapping.findForward("success");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward timeInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DictionaryViews dirViews = new DictionaryViews(this.branchService);

		List branchList = dirViews.getBranchListInUsing();// 取出数据库中状态为正常的机构

		request.setAttribute("branchList", branchList);

		this.clearForm((ValidatorTimeQueryForm) form);

		return mapping.findForward("success");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward timeQueryPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int currentPage = 1;
		int lineSize = 10;// 每页显示的记录数
		int allRecorders = 0;// 数据库中总的记录数

		Limit limit = RequestUtils.getLimit(request);
		Sort sort = limit.getSort();

		Map sortValueMap = sort.getSortValueMap();
		FilterSet filterSet = limit.getFilterSet();

		Map filterPropertyMap = filterSet.getPropertyValueMap();

		// allRecorders = sysOptDAO.getAllCount(sortValueMap,
		// filterPropertyMap);
		allRecorders = this.associatorService.findAll().size();

		int[] rowStartEnd = RequestUtils.getRowStartEnd(request, allRecorders,
				lineSize);
		if (lineSize > allRecorders) {
			currentPage = 1;
		}

		DictionaryViews dirViews = new DictionaryViews();

		ValidatorTimeQueryForm vtqf = (ValidatorTimeQueryForm) form;

		String fromDate = vtqf.getFromDate();
		String endDate = vtqf.getEndDate();
		String b_id = vtqf.getB_id();

		if (!"".equals(fromDate.trim()) && !"".equals(endDate.trim())) {
			String fromDateStr = fromDate.replace("-", "");
			Integer fromDateInt = Integer.parseInt(fromDateStr);

			String endDateStr = endDate.replace("-", "");
			Integer endDateInt = Integer.parseInt(endDateStr);

			if (fromDateInt.intValue() > endDateInt.intValue()) {
				log.error("fromDate>endDate");
				DictionaryViews dirViews1 = new DictionaryViews(this.branchService);

				List branchList = dirViews1.getBranchListInUsing();// 取出数据库中状态为正常的机构

				request.setAttribute("branchList", branchList);

				throw new DateErrorException("");
			}
		}

		if ("".equals(fromDate.trim())) {
			fromDate = null;
		}
		if ("".equals(endDate.trim())) {
			endDate = null;
		}

		if ("".equals(b_id.trim())) {
			b_id = null;
		}

		AssConsumeUtil assConsumeUtil = new AssConsumeUtil(this.assConsumeService);

		List assConsumeList = assConsumeUtil.queryAllTimeAssociator(fromDate,
				endDate, b_id);

		List staticList = new ArrayList();// 里面存放Static_2

		Iterator it = assConsumeList.iterator();
		while (it.hasNext()) {
			boolean has = false;
			AssConsume assConsume = (AssConsume) it.next();
			// 计算出这条交易总的会员积分
			String assConsumeId = assConsume.getAssId();

			Material material = this.materialService.findById(null);
			if (material == null) {
				log.error("drug is null");
				this.addToRequest(null, null, request, "*");
				throw new SystemErrorException("");
			}

//			Integer sumVal = material.getmatpo() * assConsume.getId().getDNum();
Integer sumVal=0;
			for (int i = 0; i < staticList.size(); i++) {
				// Statistic st=(Statistic)statisticList.get(i);
				Static2 st = (Static2) staticList.get(i);
				if (st.getAssId().equals(assConsume.getAssId())) {
					st.setS2Point(st.getS2Point() + sumVal);
					has = true;
				}
				// staticList.set(i, st);
				this.static2Service.update(st);
			}
			if (!has) {
				Static2 static2 = new Static2();
				static2.setAssId(assConsume.getAssId());
				static2.setS2Point(sumVal);

				this.static2Service.save(static2);
				staticList.add(static2);
			}
		}

		List stList = this.static2Service.findAllOrderByVal();// 按积分排序
		// data1:会员号;data2:积分;data3:会员姓名;data4:会员等级;
		// data5:机构名称(废);data6:会员等级;
		List lastList = new ArrayList();
		Iterator it2 = stList.iterator();
		while (it2.hasNext()) {
			Static2 st2 = (Static2) it2.next();

			String data1 = st2.getAssId();
			String data2 = st2.getS2Point().toString();

			Associator associator = this.associatorService.findById(data1);

			if (associator == null) {
				log.error("associator is null");
				this.addToRequest(null, null, request, "*");
				throw new SystemErrorException("");
			}

			String data3 = associator.getAssName();
			String data4 = associator.getAssLevel();
			String data6=this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0006,data4)).getDicName().trim();
			// String data5 = this.branchDAO.findById(b_id).getName();
			String data5 = "";
			lastList.add(new ViewBean4(data1, data2, data3, data4, data5, data6));

		}

		// 清空数据库
		this.static2Service.deleteAll();

		request.setAttribute("data", lastList);
		request.setAttribute("fromDate", fromDate);
		request.setAttribute("endDate", endDate);

		return mapping.findForward("success");
	}

	/**
	 * 清空form
	 * 
	 * @param form
	 */
	public void clearForm(ValidatorTimeQueryForm form) {
		form.setB_id("");
		form.setD_id("");
		form.setEndDate("");
		form.setFromDate("");
	}

	public ActionForward doAjaxValidateJihuo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List list=ServletUtils.getParameterMaps(request);
		Map keys=(Map)list.get(0);
		String assId=(String)keys.get("assId");
		String resStr="";
		if(StringUtils.isNotEmpty(assId)){
			Associator ass=this.associatorService.findById(assId);
			if(ass==null){
				resStr="会员卡号["+assId+"]不存在!";
			}else if(ass.getAssStat().equals(DicDataUtil.DICDATA_000000)){
				resStr="会员卡号["+assId+"]已激活,请勿重复操作!";
			}
		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");  
		PrintWriter pw = response.getWriter();  
        pw.write(resStr);  
        pw.flush();  
		
		
		return null;
	}
	
	

}
