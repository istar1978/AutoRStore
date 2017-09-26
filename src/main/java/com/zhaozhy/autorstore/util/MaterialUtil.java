package com.zhaozhy.autorstore.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaozhy.autorstore.entity.Material;
import com.zhaozhy.autorstore.service.MaterialService;

/**
 * 
 * @author zhaozy
 *
 */
public class MaterialUtil {

	private MaterialService materialService;

	public MaterialUtil(MaterialService materialService) {
		super();
		this.materialService = materialService;
	}
	private String footer;

	public String getFooter() {
		return footer;
	}
	
	public List queryPage(PageState pageState,Material material){
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
		
		data=this.materialService.findAllByExample(material);
		
		int intCount = data.size();// 数据库中的总记录数

		List list = this.materialService.findAllByExamplePerPage(material, intPage, intPageSize);

		int intPageCount = ((intCount + intPageSize) - 1) / intPageSize;// 总页数

		FooterUtil footerUtil = new FooterUtil();
		footer = footerUtil.getPageFooter(intPage, intPageCount, intPageSize,
				intCount);

		// System.out.println("list.size:"+list.size());
		return list;
	}
}
