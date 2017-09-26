package com.zhaozhy.autorstore.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaozhy.autorstore.form.ValidatorMenuForm;
import com.zhaozhy.autorstore.service.MenuService;

/**
 * 
 * @author zhaozy
 *
 */
public class MenuUtil {

	private MenuService menuService;
	private String footer;
	public MenuUtil(MenuService menuService) {
		super();
		this.menuService = menuService;
	}
	public String getFooter() {
		return footer;
	}
	
	public List queryPage(PageState pageState,ValidatorMenuForm menuForm,String menuId){
		List data = new ArrayList();

		String nowPage = pageState.getPages();
		String nowPageSize = pageState.getPageSize();

		int intPage = 0;
		int intPageSize = 0;

		if (nowPage == null) {
			intPage = 1;
		} else {
			intPage = Integer.parseInt(nowPage);
			if (intPage < 0)
				intPage = 1;
		}
		if (nowPageSize == null) {
			intPageSize = 20;
		} else {
			intPageSize = Integer.parseInt(nowPageSize);
			if (intPageSize < 0)
				intPageSize = 20;
		}
		
		data=this.menuService.findAllByIdLike(menuId);
		int intCount = data.size();// 数据库中的总记录数
		
		List list =menuService.queryPerPageIdLike(menuId, intPage, intPageSize);// 查询一页的记录

		int intPageCount = ((intCount + intPageSize) - 1) / intPageSize;// 总页数

		FooterUtil footerUtil = new FooterUtil();
		footer = footerUtil.getPageFooter(intPage, intPageCount, intPageSize,
				intCount);
		return list;
		
	}
}
