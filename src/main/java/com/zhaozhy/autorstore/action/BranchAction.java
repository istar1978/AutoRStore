package com.zhaozhy.autorstore.action;

import java.util.ArrayList;
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
import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.form.ValidatorBranchForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.BranchUtil;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.PageState;

/**
 * 
 * @author zhaozy
 * 
 */
public class BranchAction extends BaseAction<Branch> {
	private static final Log log = LogFactory.getLog(Branch.class);
	/**
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
		ValidatorBranchForm vbf = (ValidatorBranchForm) form;
		this.clearForm(vbf);
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

		ValidatorBranchForm vbf = (ValidatorBranchForm) form;
		String action = vbf.getAction();
		String b_id = vbf.getB_id();

		int num = 0;

		Branch branch = this.branchService.findById(b_id);

		if (action != null) {
			if ("insert".equals(action)) {
				if (branch != null) {
					log.error("branch is not null");
					throw new DataAlreadyExistException("");
				}

				String b_name = vbf.getB_name();
				String b_level = vbf.getB_level();
				String up_branch = vbf.getUp_branch();
				String b_stat = vbf.getB_stat();

				Branch model = new Branch();

				model.setBraId(b_id);
				model.setBraName(b_name);
				model.setBraLevel(b_level);
				model.setBraUpid(up_branch);
				model.setBraStat(b_stat);

				this.branchService.save(model);

				num = 1;
			} else if ("delete".equals(action)) {
				if (branch == null) {
					log.error("branch is not null");
					throw new DataNotFoundException("");
				}

				this.branchService.delete(branch);
				num = 1;
				this.clearForm(vbf);

			} else if ("update".equals(action)) {
				String b_name = vbf.getB_name();
				String b_level = vbf.getB_level();
				String up_branch = vbf.getUp_branch();
				String b_stat = vbf.getB_stat();

				if (b_name != null) {
					if (!"".equals(b_name)) {
						branch.setBraName(b_name);
					}
				}
				if (b_level != null) {
					if (!"".equals(b_level)) {
						branch.setBraLevel(b_level);
					}
				}
				if (up_branch != null) {
					if (!"".equals(up_branch)) {
						branch.setBraUpid(up_branch);
					}
				}
				if (b_stat != null) {
					if (!"".equals(b_stat)) {
						branch.setBraStat(b_stat);
					}
				}

				this.branchService.update(branch);

				num = 1;
			} else if ("query".equals(action)) {
				this.addToRequest(request, b_id);
			}
		}

		if (num > 0) {
			log.error("Mannul OK");
			this.addToRequest(request, "*");
			throw new MannulSuccessException("");
		}

		return mapping.findForward("success");
	}

	public void addToRequest(HttpServletRequest request, String b_id) {

		DicDataId did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0005);
		List<DicData> dList=this.dicDataService.findByIdProperty(did);
		List levelList = DictionaryViews.getSelectList(dList);
		
		List branchList = DictionaryViews.getBranchList(this.branchService.findAll());
		
		did=new DicDataId();
		did.setDicLarge(DicDataUtil.DICDATA_0000);
		List sList=this.dicDataService.findByIdProperty(did);
		List statList = DictionaryViews.getStatList(sList);

		BranchUtil branchUtil = new BranchUtil(this.branchService);

		String footer = "";

		List dataList = branchUtil.queryPerPage(b_id, new PageState(request));

		List list = new ArrayList();
		Iterator it = dataList.iterator();
		while (it.hasNext()) {
			Branch branch = (Branch) it.next();

			list.add(this.transformBranch(branch));
		}

		footer = branchUtil.getFooter();

		request.setAttribute("dataList", list);
		request.setAttribute("footer", footer);
		request.setAttribute("levelList", levelList);
		request.setAttribute("branchList", branchList);
		request.setAttribute("statList", statList);

	}

	/**
	 * 格式化机构类
	 * 
	 * @param branch
	 * @return
	 */
	public ViewBean4 transformBranch(Branch branch) {
		// data1:机构编号;data2:机构名称;data3:级别;data4:上级机构号;

		// data5:状态;

		// data6:级别(总店);data7:上级机构名称;data8:状态(正常);
		DictionaryViews dirViews = new DictionaryViews(this.branchService);

		String data1 = branch.getBraId();
		String data2 = branch.getBraName();
		String data3 = branch.getBraLevel();
		String data4 = branch.getBraUpid();
		String data5 = branch.getBraStat();

		String data6 = this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0005,data3)).getDicName().trim();

		Branch b = this.branchService.findById(branch.getBraUpid());

		String data7 = "";

		if (b != null) {
			data7 = b.getBraName();
		}
		String data8 = this.dicDataService.findById(new DicDataId(DicDataUtil.DICDATA_0000,data5)).getDicName().trim();

		return new ViewBean4(data1, data2, data3, data4, data5, data6, data7,
				data8);

	}

	/**
	 * 清空form
	 * 
	 * @param vbf
	 */
	public void clearForm(ValidatorBranchForm vbf) {

		vbf.setB_id("");
		vbf.setB_name("");
		vbf.setB_level("");
		vbf.setUp_branch("");
		vbf.setS_stat("");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward branchPrint(ActionMapping mapping, ActionForm form,
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
		allRecorders = this.branchService.findAll().size();

		int[] rowStartEnd = RequestUtils.getRowStartEnd(request, allRecorders,
				lineSize);
		if (lineSize > allRecorders) {
			currentPage = 1;
		}

		List branchList = this.branchService.findAll();
		List dataList = new ArrayList();
		Iterator it = branchList.iterator();
		while (it.hasNext()) {
			Branch branch = (Branch) it.next();
			dataList.add(this.transformBranch(branch));
		}

		request.setAttribute("data", dataList);

		return mapping.findForward("success");
	}

}
