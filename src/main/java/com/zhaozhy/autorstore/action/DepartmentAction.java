package com.zhaozhy.autorstore.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhaozhy.autorstore.entity.Department;
import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.form.ValidatorDepartmentForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.DepartmentUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.PageState;

/**
 * 部门管理
 * 
 * @author zhaozy
 * 
 */
public class DepartmentAction extends BaseAction<Department> {
	private static final Log log = LogFactory.getLog(Department.class);
	
	/**
	 * 仅起转向的作用
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

		this.addToRequestInit(request, "*");
		ValidatorDepartmentForm vdf=(ValidatorDepartmentForm)form;
		//this.clearForm(vdf);
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

		ValidatorDepartmentForm departmentForm = (ValidatorDepartmentForm) form;
		String action = departmentForm.getAction();
		String depId = departmentForm.getDepId();
		log.info("action:【" + action + "】");
		int num = 0; 

		if (action != null) {
			if ("insert".equals(action.trim())) {// 增

				Department department = this.departmentService.findById(depId
						.trim());

				if (department != null) {
					log.error("department already exist!");
					this.addToRequestInit(request, "*");
					throw new DataAlreadyExistException("");
				}

				String depName = departmentForm.getDepName().trim();
				// String branchId = departmentForm.getBranchId().trim();
				String depType = departmentForm.getDepType().trim();
				String depStat = departmentForm.getStat().trim();

				Department instance = new Department();
				instance.setDepId(depId.trim());
				instance.setDepName(depName);
				// instance.setBranchId(branchId);
				instance.setDepType(depType);
				instance.setDepStat(depStat);

				this.departmentService.save(instance);
				num = 1;

			} else if ("delete".equals(action.trim())) {// 删
				Department department = this.departmentService.findById(depId
						.trim());
				if (department == null) {
					log.error("department is null");
					this.addToRequestInit(request, "*");
					throw new DataNotFoundException("");
				}

				this.departmentService.delete(department);
				num = 1;
				this.clearForm(departmentForm);

			} else if ("update".equals(action.trim())) {// 改
				Department department = this.departmentService.findById(depId
						.trim());

				String depName = departmentForm.getDepName().trim();
				// String branchId = departmentForm.getBranchId().trim();
				String depType = departmentForm.getDepType().trim();
				String depStat = departmentForm.getStat().trim();

				department.setDepName(depName);
				// department.setBranchId(branchId);
				department.setDepType(depType);
				department.setDepStat(depStat);

				if (department == null) {
					log.error("department is null");
					this.addToRequestInit(request, "*");
					throw new DataNotFoundException("");
				}

				this.departmentService.update(department);
				num = 1;
			} else {
				this.addToRequestInit(request, "*");// action=""
			}
		}

		if (num > 0) {
			this.addToRequestInit(request, "*");
			throw new MannulSuccessException("");
		}
		this.addToRequestInit(request, "*");
		return mapping.findForward("success");
	}

	/**
	 * 把数据都放到request中用于页面显示
	 * 
	 * @param request
	 */
	public void addToRequestInit(HttpServletRequest request, String depId) {
		DictionaryViews dictionaryViews = new DictionaryViews(this.branchService,
				this.departmentService);

		DepartmentUtil departmentUtil = new DepartmentUtil(this.departmentService);
		String footer = "";

		DicDataId did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0000);
		List<DicData> sList=this.dicDataService.findByIdProperty(did);
		List statList = dictionaryViews.getStatList(sList);
		
		/** 取部门类型 */
		did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0001);
		List<DicData> tList=this.dicDataService.findByIdProperty(did);
		List dTypeList = dictionaryViews.getDTypeList(tList);
		
		
		//List branchInUsingList = dictionaryViews.getBranchListInUsing();

		List departmentList = this.transformDepartmentList(departmentUtil
				.queryPage(new PageState(request), depId));

		footer = departmentUtil.getFooter();

		request.setAttribute("dataList", departmentList);
		request.setAttribute("statList", statList);
		request.setAttribute("typeList", dTypeList);
		request.setAttribute("footer", footer);
		//request.setAttribute("branchList", branchInUsingList);
	}

	/**
	 * 封装部门类
	 * 
	 * @param departmentList
	 * @return
	 */
	public List transformDepartmentList(List departmentList) {

		DictionaryViews dictionaryViews = new DictionaryViews(
				this.departmentService);

		List dataList = new ArrayList();
		Iterator it = departmentList.iterator();
		while (it.hasNext()) {
			Department department = (Department) it.next();
			// data1:部门编号;data2:部门名称;data3:部门类型;data4:状态;

			// data5:部门类型(0:销售部);data6:状态(0:正常);

			String data1 = department.getDepId().trim();
			String data2 = department.getDepName().trim();
			// String data3 = department.getBranchId().trim();
			String data3 = department.getDepType().trim();
			String data4 = department.getDepStat().trim();

			// Branch branch = this.branchDAO.findById(data3);

			// String data6 = branch.getName().trim();

			DicDataId ddId=new DicDataId();
			ddId.setDicLarge(DicDataUtil.DICDATA_0001);
			ddId.setDicValue(data3);
			String data5 = this.dicDataService.findById(ddId).getDicName();
			
			ddId.setDicLarge(DicDataUtil.DICDATA_0000);
			ddId.setDicValue(data4);
			String data6 = this.dicDataService.findById(ddId).getDicName();

			dataList
					.add(new ViewBean4(data1, data2, data3, data4, data5, data6));

		}
		return dataList;
	}

	/**
	 * 清空form
	 * 
	 * @param departmentForm
	 */
	public void clearForm(ValidatorDepartmentForm departmentForm) {
		departmentForm.setAction("");
		departmentForm.setDepId("");
		departmentForm.setDepName("");
		//departmentForm.setBranchId("");
		departmentForm.setDepType("");
		departmentForm.setStat("");
	}
}
