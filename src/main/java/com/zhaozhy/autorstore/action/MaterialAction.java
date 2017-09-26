package com.zhaozhy.autorstore.action;

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
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ecside.util.ServletUtils;

import com.zhaozhy.autorstore.entity.Branch;
import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.entity.MaterialId;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.DateErrorException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.exception.OtherException;
import com.zhaozhy.autorstore.exception.SystemErrorException;
import com.zhaozhy.autorstore.form.ValidatorDrugForm1;
import com.zhaozhy.autorstore.form.ValidatorMaterialForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.UserContext;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.DateUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.MaterialUtil;
import com.zhaozhy.autorstore.util.PageState;

/**
 * 
 * @Title: MaterialAction.java
 * @Package: com.zhaozhy.autorstore.action
 * @Created： zhaozhy
 * @Date： 2017-5-26 下午03:47:43
 * @Desc: TODO
 * @Version: V1.0
 * 
 * @Modified：
 * @Date：
 * @Desc：
 * 
 * @Email : zhongyong@qq.com
 */
public class MaterialAction extends BaseAction<Material> {
	private static final Log log = LogFactory.getLog(Material.class);
	/**
	 * 维修材料入库
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addMaterial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserContext userContext = (UserContext) request.getSession()
				.getAttribute("userContext");

		ValidatorMaterialForm vdf = (ValidatorMaterialForm) form;
		Material material=new Material();
		
		MaterialId materialId=new MaterialId();
		materialId.setBraId(userContext.getStafferBranchId());
		materialId.setMatStat(DicDataUtil.DICDATA_000000);// 维修材料上架时 状态设置有效
		
		material.setId(materialId);
		
		BigDecimal pre_priceDou = new BigDecimal(0.0);
		BigDecimal real_priceDou = new BigDecimal(0.0);
		Integer dr_numInt = 0;
		try {
			pre_priceDou = new BigDecimal(vdf.getPre_price());
			real_priceDou = new BigDecimal(vdf.getReal_price());
			dr_numInt = Integer.parseInt(vdf.getDr_num());
		} catch (NumberFormatException e) {
			log.error("NumberFormatException");
			throw new NumberFormatException();
		}

		DateFormat df = DateFormat.getDateInstance();
		Date proDate = df.parse(vdf.getPro_date());// 生产日期
		Date inDate = df.parse(vdf.getIn_date());// 进货日期
		if (proDate.after(inDate)) {// 生产日期晚于进货日期，出错
			log.error("生产日期晚于进货日期，出错！");
			throw new DateErrorException("");
		}

		material.setMatPreprice(pre_priceDou);
		material.setMatRealprice(real_priceDou);
		material.setMatFactory(vdf.getDr_factory());
		material.setMatProdate(proDate);
		material.setMatIndate(inDate);
		material.setMatNum(dr_numInt);
		material.setMatClassify(vdf.getDr_category());
		material.setMatName(vdf.getDr_name());
		
		int num = this.materialService.addMaterial(material);
		if (num > 0) {
			this.addToRequest(request);
			throw new MannulSuccessException("");
		}
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addMaterialInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// this.addToRequest(request);
		DictionaryViews dirViews = new DictionaryViews();
		/** 维修材料分类列表 */
		DicDataId did = new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0008);
		List<DicData> ddList = this.dicDataService.findByIdProperty(did);
		List dCategoryList = dirViews.getSelectList(ddList);

		this.clearAllByPro(request, "dCategoryList");
		request.getSession().setAttribute("dCategoryList", dCategoryList);

		return mapping.findForward("success");
	}

	/**
	 * 将初始类数据放到request中
	 * 
	 * @param request
	 */
	public void addToRequest(HttpServletRequest request) {

		DicDataId did = new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0008);
		List<DicData> ddList = this.dicDataService.findByIdProperty(did);
		List dCategoryList = DictionaryViews.getSelectList(ddList);
		List branchList = DictionaryViews.getBranchList(this.branchService.findAll());

		request.setAttribute("dCategoryList", dCategoryList);
		// 加入状态list
		did = new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0000);
		List<DicData> statList = this.dicDataService.findByIdProperty(did);
		request.setAttribute("statList", DictionaryViews.getSelectList(statList));

		request.setAttribute("branchList", branchList);
	}

	/**
	 * 上架管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shangjia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorMaterialForm vdf = (ValidatorMaterialForm) form;
		int num = 0;

		UserContext userContext = (UserContext) request.getSession()
				.getAttribute("userContext");
		String dr_id = vdf.getDr_id();
		String dr_num = vdf.getDr_num();
		String dr_stat = vdf.getDr_stat();
		if (!"0".equals(dr_stat)) {// 非 药品回库
			Integer dr_numInt = 0;

			MaterialId mid = new MaterialId();
			mid.setBraId(userContext.getStafferBranchId());
			mid.setMatId(dr_id);
			mid.setMatStat("00");

			Material material = this.materialService.findById(mid);

			if (material == null) {// 库中无此药品
				log.error("drug is null");
				throw new DataNotFoundException("");
			}

			try {
				dr_numInt = Integer.parseInt(dr_num);
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}

			Integer dr_numInt1 = material.getMatNum();

			if (dr_numInt > dr_numInt1) {// 上架药品数量 >库中药品数量(即库中无足够的药品)
				log.error("dr_numInt>dr_numInt1");
				throw new OtherException("");
			}

			Material material1 = this.materialService.findById(mid);// 从数据库中找出已经上架的此种药品

			if (material1 != null) {// 架上已有此药品，则在此基础上进行操作
				int i1 = material.getMatNum();
				material1.setMatNum(material1.getMatNum() + dr_numInt);
				material.setMatNum(material.getMatNum() - dr_numInt);
				if (i1 != dr_numInt.intValue()) {
					this.materialService.update(material);
				} else {
					this.materialService.delete(material);
				}
				this.materialService.update(material1);
				num = 1;

			} else {
				Material material2 = new Material();
				MaterialId midtmp = new MaterialId();
				midtmp.setMatId(dr_id);
				material2.setId(midtmp);
				material2.setMatNum(dr_numInt);

				material2.setMatName(material.getMatName());
				material2.setMatClassify(material.getMatClassify());
				material2.setMatPreprice(material.getMatPreprice());
				material2.setMatRealprice(material.getMatRealprice());
				material2.setMatFactory(material.getMatFactory());
				material2.setMatProdate(material.getMatProdate());
				material2.setMatIndate(material.getMatIndate());

				this.materialService.save(material2);
				material.setMatNum(material.getMatNum() - dr_numInt);
				if (material.getMatNum() != 0) {
					this.materialService.update(material);
				} else {
					this.materialService.delete(material);
				}
				num = 1;
			}

		} else {// 药品回库

			MaterialId mid = new MaterialId();
			mid.setMatId(dr_id);
			Material material = this.materialService.findById(mid);
			if (material == null) {// 架上无此药品
				System.out.println("drug is null");
				throw new DataNotFoundException("");
			}
			Material material1 = this.materialService.findById(mid);
			if (material1 == null) {// 库中原先没有此药品了
				material.setId(mid);
				this.materialService.update(material);
				num = 1;
			} else {// 库中还有此药品，则在此基础上进行操作
				material1.setMatNum(material1.getMatNum()
						+ material.getMatNum());
				this.materialService.update(material1);
				this.materialService.delete(material);
				num = 1;
			}
			// this.materialDAO.merge(drug);
			num = 1;
		}
		if (num > 0) {
			System.out.println("Mannul ok");
			throw new MannulSuccessException("");
		}
		return mapping.findForward("success");
	}

	/**
	 * 上架管理初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shangjiaInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// this.clearForm((ValidatorDrugForm) form);
		return mapping.findForward("success");
	}

	/**
	 * 查询页面的初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.addToRequest(request);
		// this.clearForm((ValidatorDrugForm) form);
		return mapping.findForward("success");
	}

	/**
	 * 查询操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorDrugForm1 vdf1 = (ValidatorDrugForm1) form;
		String dr_id = vdf1.getDr_id();
		String branch_id = vdf1.getBranch_id();
		String dr_stat = vdf1.getDr_stat();
		String dr_category = vdf1.getDr_category();
		String dr_factory = vdf1.getDr_factory();
		String dr_name = vdf1.getDr_name();

		Material material = new Material();
		MaterialId mid = new MaterialId();
		mid.setMatId(dr_id);
		mid.setBraId(branch_id);
		mid.setMatStat(dr_stat);

		material.setId(mid);
		material.setMatClassify(dr_category);
		material.setMatFactory(dr_factory);
		material.setMatName(dr_name);

		MaterialUtil drugUtil = new MaterialUtil(this.materialService);
		String footer = "";
		List drugList = drugUtil.queryPage(new PageState(request), material);
		footer = drugUtil.getFooter();
		List dataList = this.transformMaterialList(drugList);
		request.setAttribute("dataList", dataList);
		request.setAttribute("footer", footer);

		return mapping.findForward("success");
	}

	/**
	 * 格式化药品列表
	 * 
	 * @param materialList
	 * @return
	 */
	public List transformMaterialList(List materialList) {
		List dataList = new ArrayList();

		Iterator it = materialList.iterator();
		while (it.hasNext()) {
			Material material = (Material) it.next();
			DateFormat df = DateFormat.getDateInstance();
			DictionaryViews dirViews = new DictionaryViews();
			// data1:维修材料编号;data2:维修材料名称;data3:分类;data4:所属维修项目编号
			// data5:进货价格;data6:上架价格;data7:生产厂家;data8:生产日期;
			// data9:进货日期;data10:库存量;data11:所在机构;data12:状态;
			// data13:分类(维修类);data14:所在机构名称;data15:状态(有效);data16:;

			String data1 = material.getId().getMatId();
			String data2 = material.getMatName();
			String data3 = material.getMatClassify();
			String data4 = "";
			String data5 = material.getMatPreprice().toString();
			String data6 = material.getMatRealprice().toString();
			String data7 = material.getMatFactory();
			String data8 = df.format(material.getMatProdate());
			String data9 = df.format(material.getMatIndate());
			String data10 = material.getMatNum().toString();
			String data11 = material.getId().getBraId();
			String data12 = material.getId().getMatStat();
			String data13 = new String();
			if (null != data3 && !data3.equals("")) {
				data13 = data3+":"+this.dicDataService.findById( new DicDataId(DicDataUtil.DICDATA_0008, data3)) .getDicName().trim();
			}
			Branch branch = this.branchService.findById(data11);
			if (branch == null) {
				log.error("branch is null");
				throw new SystemErrorException("");
			}
			String data14 = data11+":"+branch.getBraName();
			String data15 = data12+":"+this.dicDataService.findById( new DicDataId(DicDataUtil.DICDATA_0000, data12)).getDicName().trim();

			dataList.add(new ViewBean4(data1, data2, data3, data4, data5,
					data6, data7, data8, data9, data10, data11, data12, data13,
					data14, data15));
		}
		return dataList;
	}

	/**
	 * 
	 * @param vdf
	 */
	public void clearForm(ValidatorMaterialForm vdf) {
		// vdf.setAction("");
		vdf.setBranch_id("");
		// vdf.setDr_category("");
		vdf.setDr_factory("");
		vdf.setDr_id("");
		vdf.setDr_name("");
		vdf.setDr_num("");
		vdf.setDr_stat("");
		vdf.setIn_date("");
		vdf.setPre_price("");
		vdf.setPro_date("");
		vdf.setReal_price("");
	}

	/**
	 * 药品移库初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward transInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserContext userContext = (UserContext) request.getSession()
				.getAttribute("userContext");
		DictionaryViews dirViews = new DictionaryViews(this.branchService);

		List branchList = dirViews.getBranchListOthers(userContext
				.getStafferBranchId());
		if (request.getSession().getAttribute("branchList") != null) {
			request.getSession().removeAttribute("branchList");
		}
		request.getSession().setAttribute("branchList", branchList);
		return mapping.findForward("success");
	}

	/**
	 * 药品移库管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward transform(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int num = 0;
		UserContext userContext = (UserContext) request.getSession()
				.getAttribute("userContext");
		ValidatorMaterialForm vdf = (ValidatorMaterialForm) form;

		String dr_id = vdf.getDr_id();
		String dr_num = vdf.getDr_num();
		String branchTo = vdf.getBranch_id();

		MaterialId mid = new MaterialId();
		mid.setMatId(dr_id);
		Material material = this.materialService.findById(mid);
		if (material == null) {
			log.error("material is null");
			this.transformAddRequest(request);
			throw new DataNotFoundException("");
		}
		Integer dr_numInt = 0;
		try {
			dr_numInt = Integer.parseInt(dr_num);
		} catch (NumberFormatException e) {
			this.transformAddRequest(request);
			throw new NumberFormatException();
		}

		if (material.getMatNum().intValue() < dr_numInt.intValue()) {
			System.out.println("drug.getNum()<dr_numInt");
			this.transformAddRequest(request);
			throw new OtherException("");
		}
		Material material1 = this.materialService.findById(mid);
		if (material1 == null) {
			Material newMaterial1 = new Material();
			MaterialId mmid = new MaterialId();
			mmid.setMatId(dr_id);

			newMaterial1.setId(mmid);
			newMaterial1.setMatClassify(material.getMatClassify());
			newMaterial1.setMatFactory(material.getMatFactory());
			newMaterial1.setMatIndate(material.getMatIndate());
			newMaterial1.setMatName(material.getMatName());
			newMaterial1.setMatNum(dr_numInt);

			newMaterial1.setMatPreprice(material.getMatPreprice());
			newMaterial1.setMatProdate(material.getMatProdate());
			newMaterial1.setMatRealprice(material.getMatRealprice());
			this.materialService.save(newMaterial1);
			Integer oldNum = material.getMatNum();
			if (oldNum == dr_numInt) {
				this.materialService.delete(material);
			} else {
				material.setMatNum(oldNum - dr_numInt);
				this.materialService.update(material);
			}
			num = 1;
		} else {
			Integer oldNum = material.getMatNum();
			material1.setMatNum(material1.getMatNum() + dr_numInt);
			this.materialService.update(material1);
			if (oldNum == dr_numInt) {
				this.materialService.delete(material);

			} else {
				material.setMatNum(oldNum - dr_numInt);
				this.materialService.update(material);
			}
			num = 1;
		}

		if (num > 0) {
			System.out.println("mannul Ok");
			this.transformAddRequest(request);
			throw new MannulSuccessException("");
		}

		return mapping.findForward("success");
	}

	/**
	 * 药品移库管理初始化request
	 * 
	 * @param request
	 */
	public void transformAddRequest(HttpServletRequest request) {
		UserContext userContext = (UserContext) request.getSession()
				.getAttribute("userContext");

		DictionaryViews dirViews = new DictionaryViews(this.branchService);

		List branchList = dirViews.getBranchListOthers(userContext
				.getStafferBranchId());
		request.setAttribute("branchList", branchList);
	}

	/**
	 * 
	 * @CreateDate	2017年8月6日  下午8:57:13
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					维修材料管理页面 初始化时加入分类下拉列表数据
	 * @param request
	 */
	public void addToRequestECSide(HttpServletRequest request){
		DicDataId did = new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0008);
		List<DicData> ddList = this.dicDataService.findByIdProperty(did);
		Map dCategoryMap = DictionaryViews.getSelectMap(ddList);

		request.setAttribute("dCategoryMap", dCategoryMap);

	}
	/**
	 * 
	 * @CreateDate	2017年7月30日  下午2:14:05
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					维修材料管理页面 页面初始化，准备数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward materialManageInit(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		this.addToRequestECSide(request);
		
		return mapping.findForward("success");
	}
	/**
	 * 
	 * @CreateDate	2017年7月30日  上午8:07:19
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					维修材料管理页面action 包含CUI操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward materialManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("MaterialAction-->materialManage()");
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		List list=ServletUtils.getParameterMaps(request);
		Map paramap=(Map)list.get(0);
		String action=(String)paramap.get("action");
		String dr_name=(String)paramap.get("dr_name");
		String dr_category=(String)paramap.get("dr_category");
		String pre_price=(String)paramap.get("pre_price");
		String real_price=(String)paramap.get("real_price");
		String dr_factory=(String)paramap.get("dr_factory");
		String pro_date=(String)paramap.get("pro_date");
		String in_date=(String)paramap.get("in_date");
		String dr_num=(String)paramap.get("dr_num");
		
		Date proDate=null;
		Date inDate=null;
		if(StringUtils.isNotBlank(pro_date)){
			proDate=DateUtils.parseDate(pro_date, new String[]{"yyyy-MM-dd"});
		}
		if(StringUtils.isNotBlank(in_date)){
			inDate=DateUtils.parseDate(in_date, new String[]{"yyyy-MM-dd"});
		}
		Integer matNum=null;
		if(StringUtils.isNotBlank(dr_num)){
			matNum=Integer.parseInt(dr_num);
		}
		BigDecimal prePrice=null;
		if(StringUtils.isNotBlank(pre_price)){
			prePrice=new BigDecimal(pre_price);
		}
		BigDecimal realPrice=null;
		if(StringUtils.isNotBlank(real_price)){
			realPrice=new BigDecimal(real_price);
		}
		
		Material model=new Material();
		MaterialId mid=new MaterialId();
		mid.setBraId(uc.getStafferBranchId());
		mid.setMatStat(DicDataUtil.DICDATA_000000);
		model.setId(mid);
		
		model.setMatClassify(dr_category);
		model.setMatFactory(dr_factory);
		model.setMatIndate(inDate);
		model.setMatName(dr_name);
		model.setMatNum(matNum);
		model.setMatPreprice(prePrice);
		model.setMatProdate(proDate);
		model.setMatRealprice(realPrice);
		
		List materialList=null;
		int num=0;
		if(action.equals("insert")){
			num=this.materialService.addMaterial(model);
			materialList=this.materialService.findAll();
		}else if(action.equals("query")){
			materialList=this.materialService.findAllByExample(model);
		}else if(action.equals("update")){
			String matId=(String)paramap.get("matId");
			mid.setMatId(matId);
			model.setId(mid);
			this.materialService.update(model);
			materialList=this.materialService.findAll();
			num=1;
		}
		
		List dataList=this.transformMaterialList(materialList);
		request.setAttribute("dataList", dataList);
		this.addToRequestECSide(request);
		request.setAttribute("totalRows", dataList.size());
		if(num>0){
			log.error("mannul ok!");
			throw new MannulSuccessException("");
		}
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @CreateDate	2017年8月7日  下午9:02:33
	 * @Author				zhaozhy  (zhongyong@qq.com)
	 *	@Desc					ECSide 列表删除数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAjaxMaterialDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserContext uc=(UserContext)request.getSession().getAttribute("userContext");
		List list=ServletUtils.getParameterMaps(request);
		Map paramap=(Map)list.get(0);
		String recordKey=(String)paramap.get("recordKey");
		
		MaterialId mid=new MaterialId();
		mid.setBraId(uc.getStafferBranchId());
		mid.setMatId(recordKey);
		mid.setMatStat(DicDataUtil.DICDATA_000000);
		Material model=this.materialService.findById(mid);
		this.materialService.delete(model);
		
		ServletUtils.defaultAjaxResopnse(list, new int[]{1}, request, response);
		return null;
	}

	
	
}
