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

import com.zhaozhy.autorstore.entity.DicData;
import com.zhaozhy.autorstore.entity.DicDataId;
import com.zhaozhy.autorstore.entity.Menu;
import com.zhaozhy.autorstore.exception.DataAlreadyExistException;
import com.zhaozhy.autorstore.exception.DataNotFoundException;
import com.zhaozhy.autorstore.exception.MannulSuccessException;
import com.zhaozhy.autorstore.form.ValidatorMenuForm;
import com.zhaozhy.autorstore.sysadmin.DictionaryViews;
import com.zhaozhy.autorstore.sysadmin.ViewBean4;
import com.zhaozhy.autorstore.util.DicDataUtil;
import com.zhaozhy.autorstore.util.MenuUtil;
import com.zhaozhy.autorstore.util.PageState;

/**
 * 处理对系统菜单表的操作
 * 
 * @author zhaozy
 * 
 */
public class MenuAction extends BaseAction<Menu> {
	private static final Log log = LogFactory.getLog(Menu.class);
	/**
	 * 仅起转向作用
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

		DictionaryViews dirView = new DictionaryViews();
		DicDataId dId=new DicDataId();
		dId=new DicDataId();
		dId.setDicLarge(DicDataUtil.DICDATA_0000);
		List<DicData> sList=this.dicDataService.findByIdProperty(dId);
		List statList = dirView.getSelectListDefault(sList);
		request.getSession().setAttribute("statList", statList);
		
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

		ValidatorMenuForm menuForm = (ValidatorMenuForm) form;
		String action = menuForm.getAction();
		String menuId = menuForm.getMenuId().trim();
		int num = 0;

		MenuUtil menuUtil = new MenuUtil(this.menuService);

		if ("insert".equals(action)) { // 增加新数据
			// String menuId = menuForm.getMenuId();
			Menu menu = this.menuService.findById(menuId);
			if (menu != null) {
				System.out.println("menu is not null");
				this.addListToRequest(menuUtil, request, "*", menuForm);
				throw new DataAlreadyExistException("");
			}

			Menu insertMenu = new Menu();
			insertMenu.setMenId(menuForm.getMenuId().trim());
			insertMenu.setMenName(menuForm.getMenuName().trim());

			// System.out.println(menuForm.getMenuAtt());
			insertMenu.setMenAtt(menuForm.getMenuAtt().trim());

			insertMenu.setMenUrl(menuForm.getUrlAddr().trim());
			insertMenu.setMenStat(DicDataUtil.DICDATA_000000);//默认有效

			this.menuService.save(insertMenu);

			num = 1;

		} else if ("delete".equals(action)) { // 删除操作
			// String menuId = menuForm.getMenuId().trim();
			Menu menu = this.menuService.findById(menuId);
			if (menu == null) {
				System.out.println("===menu is null====");
				this.addListToRequest(menuUtil, request, "*", menuForm);
				throw new DataNotFoundException("");
			}
			this.menuService.delete(menu);
//			menu.setMenStat(DicDataUtil.DICDATA_000001);
//			this.menuDAO.merge(menu);
			num = 1;

			this.clearForm(menuForm);

		} else if ("update".equals(action)) { // 更新操作
			// String menuId = menuForm.getMenuId().trim();
			Menu menu = this.menuService.findById(menuId);
			if (menu == null) {
				System.out.println("====menu is null=====");
				this.addListToRequest(menuUtil, request, "*", menuForm);
				throw new DataNotFoundException("");
			}

			String menuName = menuForm.getMenuName().trim();
			String menuAtt = menuForm.getMenuAtt().trim();
			String menuUrl = menuForm.getUrlAddr().trim();
			String menStat=menuForm.getMenStat().trim();

			menu.setMenName(menuName);
			menu.setMenAtt(menuAtt);
			menu.setMenUrl(menuUrl);
			menu.setMenStat(menStat);

			this.menuService.update(menu);
			num = 1;

		} else if ("query".equals(action)) { // 查询操作
			// String menuId = menuForm.getMenuId().trim();
			this.addListToRequest(menuUtil, request, menuId, menuForm);

		}

		if (num > 0) {
			this.addListToRequest(menuUtil, request, "*", menuForm);
			throw new MannulSuccessException("");
		}

		return mapping.findForward("success");
	}

	/**
	 * 将所需要的数据放到Request中，用于显示
	 * 
	 * @param menuUtil
	 * @param request
	 * @param menuId
	 * @param menuForm
	 */
	public void addListToRequest(MenuUtil menuUtil, HttpServletRequest request,
			String menuId, ValidatorMenuForm menuForm) {

		List menuList = menuUtil.queryPage(new PageState(request), menuForm,
				menuId);
		// System.out.println("menuId="+menuId);
		List dataList = new ArrayList();

		Iterator it = menuList.iterator();
		while (it.hasNext()) {
			Menu menu = (Menu) it.next();
			dataList.add(this.transformMenu(menu));
		}
		String footer = menuUtil.getFooter();
		// System.out.println("dataList.size"+dataList.size());

		request.setAttribute("list", "true");
		request.setAttribute("menuList", dataList);
		request.setAttribute("footer", footer);

	}

	/**
	 * 对Menu进行重新封装
	 * 
	 * @param menu
	 * @return
	 */
	public ViewBean4 transformMenu(Menu menu) {

		// data1:菜单编号;data2:菜单显示名称;data3:是否为路径菜单;data4:URL地址;
		// data5:菜单性质(路径菜单或动作菜单);
		//data6:是否有效
		//data7:有效或无效
		String data1 = menu.getMenId().trim();
		String data2 = menu.getMenName().trim();
		String data3 = menu.getMenAtt().trim();
		String data4 = menu.getMenUrl().trim();
		String data6=menu.getMenStat().trim();
		
		String data5 = "";
		String data7="";

		if (DicDataUtil.DICDATA_00020.equals(data3)) {
			data5 = "路径菜单";
		} else {
			data5 = "动作菜单";
		}
		
		if(DicDataUtil.DICDATA_000000.equals(data6)){
			data7="有效";
		}else{
			data7="无效";
		}

		ViewBean4 viewBean4 = new ViewBean4(data1, data2, data3, data4, data5,data6,data7);
		return viewBean4;
	}

	/**
	 * 清空MenuForm
	 * 
	 * @param menuForm
	 */
	public void clearForm(ValidatorMenuForm menuForm) {
		menuForm.setMenuId("");
		menuForm.setMenuAtt("");
		menuForm.setMenuName("");
		menuForm.setUrlAddr("");
	}

}
