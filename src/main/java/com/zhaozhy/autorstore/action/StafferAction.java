package com.zhaozhy.autorstore.action;

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
import org.ecside.table.limit.FilterSet;
import org.ecside.table.limit.Limit;
import org.ecside.table.limit.Sort;
import org.ecside.util.RequestUtils;

import com.zhaozhy.autorstore.entity.Branch;
import com.zhaozhy.autorstore.entity.Department;
import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.entity.Staffer;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.exception.SystemErrorException;
import com.zhaozhy.autorstore.form.ValidatorStafferForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.MD5Util;
import com.zhaozhy.autorstore.util.PageState;
import com.zhaozhy.autorstore.util.StafferUtil;

/**
 * 
 * @author zhaozy
 * 
 */
public class StafferAction extends BaseAction<Staffer> {
	private static final Log log = LogFactory.getLog(Staffer.class);
	
	/**
	 * 页面初始化
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

		this.addToRequest(request, "*");

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
	public ActionForward manage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ValidatorStafferForm vsf = (ValidatorStafferForm) form;
		int num = 0;

		String action = vsf.getAction();
		String s_id = vsf.getS_id();

		Staffer staffer = this.stafferService.findById(s_id);

		if (action != null) {
			if ("insert".equals(action)) {
				if (staffer != null) {
//					System.out.println("staffer is already exist!");
					log.error("staffer is already exist!");
					this.addToRequest(request, "*");
					throw new DataAlreadyExistException("");
				}

				//String s_password = vsf.getS_password();
				
//				String s_password="123";//登录密码采用默认123
				//20170721 密码使用MD5加密
				String password=MD5Util.getEncryptedPwd(DicDataUtil.STAFFER_DEFAULT_PASSWORD);
				
				String s_name = vsf.getS_name();
				String s_position = vsf.getS_position();
				String s_level = vsf.getS_level();
				String dep_id = vsf.getDep_id();
				String branch_id = vsf.getBranch_id();
				String s_stat = vsf.getS_stat();

				Staffer model = new Staffer();

				model.setStaId(s_id);
				model.setStaPwd(password);
				model.setStaName(s_name);
				model.setStaPosition(s_position);
				model.setStaLevel(s_level);
				model.setDepId(dep_id);
				model.setBraId(branch_id);
				model.setStaStat(s_stat);

				Date date = new Date();
				model.setStaCdate(date);

				this.stafferService.save(model);

				num = 1;

			} else if ("delete".equals(action)) {
				if (staffer == null) {
//					System.out.println("staffer is null!");
					log.error("staffer is null!");
					this.addToRequest(request, "*");
					throw new DataNotFoundException("");
				}

				this.stafferService.delete(staffer);
				num = 1;
				this.clearForm(vsf);
			} else if ("update".equals(action)) {
				if (staffer == null) {
//					System.out.println("staffer is null!");
					log.error("staffer is null!");
					this.addToRequest(request, "*");
					throw new DataNotFoundException("");
				}
				String s_password = vsf.getS_password();
				String password=MD5Util.getEncryptedPwd(s_password);
				String s_name = vsf.getS_name();
				String s_position = vsf.getS_position();
				String s_level = vsf.getS_level();
				String dep_id = vsf.getDep_id();
				String branch_id = vsf.getBranch_id();
				String s_stat = vsf.getS_stat();

				/*
				 * if (s_password != null) {
				 * 
				 * if (!"".equals(s_password)) {
				 * staffer.setPassword(s_password); } }
				 */

				if (s_name != null) {

					if (!"".equals(s_name)) {
						staffer.setStaName(s_name);
					}
				}
				if (s_position != null) {
					if (!"".equals(s_position)) {
						staffer.setStaPosition(s_position);
					}
				}
				if (s_level != null) {
					if (!"".equals(s_level)) {
						staffer.setStaLevel(s_level);
					}
				}
				if (dep_id != null) {
					if (!"".equals(dep_id)) {
						staffer.setDepId(dep_id);
					}
				}
				if (branch_id != null) {
					if (!"".equals(branch_id)) {
						staffer.setBraId(branch_id);
					}
				}

				if (s_stat != null) {
					if (!"".equals(s_stat)) {
						staffer.setStaStat(s_stat);
					}
				}

				this.stafferService.update(staffer);

				num = 1;
			} else if ("query".equals(action)) {

				this.addToRequest(request, s_id);
			}
		}

		if (num > 0) {
//			System.out.println("Mannul Ok");
			log.debug("Mannul OK!");
			this.addToRequest(request, "*");
			throw new MannulSuccessException("");
		}

		return mapping.findForward("success");
	}

	/**
	 * 将所需数据加入到request中
	 * 
	 * @param request
	 */
	public void addToRequest(HttpServletRequest request, String s_id) {

		StafferUtil stafferUtil = new StafferUtil(this.stafferService);
		String footer = "";
		DictionaryViews dirViews = new DictionaryViews(this.branchService,
				this.departmentService);

		List dataList = stafferUtil.queryPage(s_id, new PageState(request));
		footer = stafferUtil.getFooter();
		List list = new ArrayList();

		Iterator it = dataList.iterator();
		while (it.hasNext()) {
			Staffer staffer = (Staffer) it.next();
			list.add(this.transformStaffer(staffer));

		}

		request.setAttribute("dataList", list);
		request.setAttribute("footer", footer);

		DicDataId did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0004);
		List<DicData> dList=this.dicDataService.findByIdProperty(did);
		List levelList = dirViews.getSLevelList(dList);
		
		List depList = dirViews.getDepartmentListInUsing();
		List branchList = dirViews.getBranchListInUsing();

		did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0000);
		dList=this.dicDataService.findByIdProperty(did);
		List statList = dirViews.getStatList(dList);
		
		/** 员工岗位列表 */
		did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0003);
		dList=this.dicDataService.findByIdProperty(did);
		List positionList=dirViews.getPositionList(dList);

		request.setAttribute("levelList", levelList);
		request.setAttribute("depList", depList);
		request.setAttribute("branchList", branchList);
		request.setAttribute("statList", statList);
		request.setAttribute("positionList", positionList);
	}

	/**
	 * 清理form
	 * 
	 * @param vsf
	 */
	public void clearForm(ValidatorStafferForm vsf) {
		vsf.setS_id("");
		vsf.setAction("");
		vsf.setS_name("");
		vsf.setS_password("");
		vsf.setS_position("");
		vsf.setS_level("");
		vsf.setDep_id("");
		vsf.setBranch_id("");
		vsf.setS_stat("");
	}

	/**
	 * 格式化职员类
	 * 
	 * @param staffer
	 * @return
	 */
	public ViewBean4 transformStaffer(Staffer staffer) {
		// data1:职员编号;data2:职员姓名;data3:系统登录密码;data4:岗位;
		// data5:级别;data6:部门编号;data7:所属机构编号;data8:创建日期;
		// data9:状态;
		// data10:级别(普通员工);data11:部门名称;data12:机构名称;
		// data13:状态(正常);
		//data14:岗位（销售岗）

		DateFormat df = DateFormat.getDateInstance();
		String data1 = staffer.getStaId();
		String data2 = staffer.getStaName();
		String data3 = staffer.getStaPwd();
		String data4 = staffer.getStaPosition();
		String data5 = staffer.getStaLevel();
		String data6 = staffer.getDepId();
		String data7 = staffer.getBraId();

		Date date = staffer.getStaCdate();
		String data8 = df.format(date);
		String data9 = staffer.getStaStat();
		
		DicDataId dId=new DicDataId();
		dId.setDicLarge(DicDataUtil.DICDATA_0004);
		dId.setDicValue(data5);
		String data10 = data5+":"+this.dicDataService.findById(dId).getDicName().trim();

		Department department = this.departmentService.findById(data6);
		if (department == null) {
			log.error("department is null!");
			throw new SystemErrorException("");
		}

		String data11 = data6+":"+department.getDepName();

		Branch branch = this.branchService.findById(data7);

		if (branch == null) {
			log.error("branch is null!");
			throw new SystemErrorException("");
		}

		String data12 = data7+":"+branch.getBraName();

		dId=new DicDataId();
		dId.setDicLarge(DicDataUtil.DICDATA_0000);
		dId.setDicValue(data9);
		String data13 = data9+":"+this.dicDataService.findById(dId).getDicName().trim();
		
		dId=new DicDataId();
		dId.setDicLarge(DicDataUtil.DICDATA_0003);
		dId.setDicValue(data4);
		String data14=data4+":"+this.dicDataService.findById(dId).getDicName().trim();

		return new ViewBean4(data1, data2, data3, data4, data5, data6, data7,
				data8, data9, data10, data11, data12, data13,data14);

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
	public ActionForward stafferPrint(ActionMapping mapping, ActionForm form,
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
		allRecorders = this.stafferService.findAll().size();

		int[] rowStartEnd = RequestUtils.getRowStartEnd(request, allRecorders,
				lineSize);
		if (lineSize > allRecorders) {
			currentPage = 1;
		}

		List stafferList = this.stafferService.findAll();

		List dataList = new ArrayList();

		Iterator it = stafferList.iterator();
		while (it.hasNext()) {
			Staffer staffer = (Staffer) it.next();
			dataList.add(this.transformStaffer(staffer));
		}

		request.setAttribute("data", dataList);

		return mapping.findForward("success");
	}

}
