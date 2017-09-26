package com.zhaozhy.autorstore.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaozhy.autorstore.dao.BranchDAO;
import com.zhaozhy.autorstore.service.BranchService;

/**
 * 
 * @author zhaozy
 * 
 */
public class BranchUtil {

	private BranchService branchService;
	private String footer;

	public BranchUtil(BranchService branchService) {
		super();
		this.branchService = branchService;
	}

	public String getFooter() {
		return footer;
	}

	public List queryPerPage(String b_id, PageState pageState) {
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

		data = this.branchService.findAllByIdLike(b_id);
		int intCount = data.size();// 数据库中的总记录数

		List list = this.branchService
				.queryPageByIdLike(b_id, intPage, intPageSize);

		int intPageCount = ((intCount + intPageSize) - 1) / intPageSize;// 总页数

		FooterUtil footerUtil = new FooterUtil();
		footer = footerUtil.getPageFooter(intPage, intPageCount, intPageSize,
				intCount);

		// System.out.println("list.size:"+list.size());
		return list;
	}
}
