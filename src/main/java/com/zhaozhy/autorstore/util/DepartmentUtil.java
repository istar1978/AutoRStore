package com.zhaozhy.autorstore.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaozhy.autorstore.service.DepartmentService;

/**
 * 
 * @author zhaozy
 *
 */
public class DepartmentUtil {

	private DepartmentService departmentService;
	
	private String footer;

	public DepartmentUtil(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	public String getFooter() {
		return footer;
	}
	
	/**
	 * 
	 * @param pageState
	 * @param depId
	 * @return
	 */
	public List queryPage(PageState pageState,String depId){
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
		
		data=this.departmentService.findAllByIdLike(depId);
		int intCount = data.size();// 数据库中的总记录数
		
		List list=this.departmentService.queryPerPageByIdLike(depId, intPage, intPageSize);
		int intPageCount = ((intCount + intPageSize) - 1) / intPageSize;// 总页数

		FooterUtil footerUtil = new FooterUtil();
		footer = footerUtil.getPageFooter(intPage, intPageCount, intPageSize,
				intCount);
		return list;
	}
}
