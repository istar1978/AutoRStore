package com.zhaozhy.autorstore.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaozhy.autorstore.service.StafferService;

/**
 * 
 * @author zhaozy
 * 
 */
public class StafferUtil {

	private StafferService stafferService;
	private String footer;

	public StafferUtil(StafferService stafferService) {
		super();
		this.stafferService = stafferService;
	}

	public String getFooter() {
		return footer;
	}

	public List queryPage(String s_id, PageState pageState) {
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

		data = this.stafferService.findAllByIdLike(s_id);
		int intCount = data.size();// 数据库中的总记录数

		List list = this.stafferService.queryPageByIdLike(s_id, intPage,
				intPageSize);

		int intPageCount = ((intCount + intPageSize) - 1) / intPageSize;// 总页数

		FooterUtil footerUtil = new FooterUtil();
		footer = footerUtil.getPageFooter(intPage, intPageCount, intPageSize,
				intCount);

		// System.out.println("list.size:"+list.size());
		return list;
	}
}
