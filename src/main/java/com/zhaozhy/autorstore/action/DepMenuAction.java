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

import com.zhaozhy.autorstore.entity.DepMenu;
import com.zhaozhy.autorstore.entity.DepMenuId;
import com.zhaozhy.autorstore.entity.Menu;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.form.ValidatorDepMenuForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryView;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.util.DicDataUtil;

/**
 * 部门权限管理
 * 
 * @author zhaozy
 * 
 */
public class DepMenuAction extends BaseAction<DepMenu> {

	private static final Log log = LogFactory.getLog(DepMenu.class);
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

		this.addToRequest(request);
		ValidatorDepMenuForm vdrf = (ValidatorDepMenuForm) form;
		//this.clearForm(vdrf);

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

		ValidatorDepMenuForm deptMenuForm = (ValidatorDepMenuForm) form;
		String action = deptMenuForm.getAction();
		String busi = deptMenuForm.getBusi();
		int num = 0;

		List menuDList = this.menuService.findAllD();// 查询出所有的动作菜单
		List menuList = this.getMenuList(menuDList);

		if (action == null || "".equals(action)) {
			request.setAttribute("list", "false");
			this.addToRequest(request);
			return mapping.findForward("success");
		}

		if ("update".equals(action)) {
			String depId = deptMenuForm.getBusi();
			List list1 = this.depMenuService.findByDId(depId);

			// 先删后加
			Iterator it = list1.iterator();
			while (it.hasNext()) {
				DepMenu deptMenu = (DepMenu) it.next();
				this.depMenuService.delete(deptMenu);
			}

			String[] code = deptMenuForm.getCode();
			for (int i = 0; i < code.length; i++) {
				DepMenu deptMenu = new DepMenu();

				deptMenu.setId(new DepMenuId(deptMenuForm.getBusi().trim(),
						code[i]));
				deptMenu.setDmStat(DicDataUtil.DICDATA_000000);
				this.depMenuService.save(deptMenu);

			}
			num = 1;
		} else if ("query".equals(action)) {
			List list = depMenuService.findByDId(deptMenuForm.getBusi());
			Iterator it2 = list.iterator();
			String[] rightStrs = new String[list.size()];

			for (int i = 0; it2.hasNext(); i++) {
				DepMenu deptMenu = (DepMenu) it2.next();
				rightStrs[i] = deptMenu.getId().getMenId();
			}
			deptMenuForm.setCode(rightStrs);
		}

		request.setAttribute("list", "true");
		request.setAttribute("menuList", menuList);

		if (num > 0) {
			this.addToRequest(request);
			throw new MannulSuccessException("");
		}

		this.addToRequest(request);

		return mapping.findForward("success");
	}

	/**
	 * 
	 * @param menuDList
	 * @return
	 */
	public List getMenuList(List menuDList) {
		List menuList = new ArrayList();
		Iterator it = menuDList.iterator();

		DictionaryView dictionaryView = null;
		while (it.hasNext()) {
			Menu menu = (Menu) it.next();
			dictionaryView = new DictionaryView();
			dictionaryView.setCode(menu.getMenId());
			dictionaryView.setName(menu.getMenName());
			menuList.add(dictionaryView);
		}
		return menuList;
	}

	/**
	 * 
	 * @param request
	 */
	public void addToRequest(HttpServletRequest request) {
		DictionaryViews dictionaryViews = new DictionaryViews(
				this.departmentService);
		List departmentList = dictionaryViews.getDepartmentList();

		request.setAttribute("departmentList", departmentList);
	}

	/**
	 * 
	 * @param vdrf
	 */
	public void clearForm(ValidatorDepMenuForm vdrf) {
		vdrf.setAction("");
		vdrf.setBusi("");
	}
}
