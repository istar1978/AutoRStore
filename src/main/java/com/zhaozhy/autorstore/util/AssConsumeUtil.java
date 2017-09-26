package com.zhaozhy.autorstore.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.zhaozhy.autorstore.service.AssConsumeService;

/**
 * 
 * @author zhaozhy
 * 
 */
public class AssConsumeUtil {

	private AssConsumeService assConsumeService;

	private String footer;

	public String getFooter() {
		return footer;
	}

	public AssConsumeUtil(AssConsumeService assConsumeService) {
		super();
		this.assConsumeService = assConsumeService;
	}

	/**
	 * 按时间段统计会员积分
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param b_id
	 * @return
	 */
	public List queryAllTimeAssociator(String fromDate, String endDate,
			String b_id) {

		List dataList = this.assConsumeService.findAllTimeAssociator(fromDate,
				endDate, b_id);

		return dataList;

	}

	/**
	 * \\ 某机构时间段利润构成||按机构统计利润
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param b_id
	 * @return
	 */
	public List queryAllTimeByBId(String fromDate, String endDate, String b_id) {

		List dataList = this.assConsumeService.findAllTimeByBId(fromDate,
				endDate, b_id);

		return dataList;

	}

	/**
	 * 根据时间段、维修材料编号分页显示
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param d_id
	 * @return
	 * @throws ParseException 
	 */
	public List queryPerPage(String fromDate, String endDate, String d_id,
			PageState pageState) throws ParseException {
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
			intPageSize = 1000;
		} else {
			intPageSize = Integer.parseInt(nowPageSize);
			if (intPageSize < 0)
				intPageSize = 1000;
		}

		data = this.assConsumeService.findAllTimeMaterial(fromDate, endDate, d_id);

		int intCount = data.size();// 数据库中的总记录数

		List list = this.assConsumeService.findAllTimeMaterialPerPage(fromDate, endDate,
				d_id, intPage, intPageSize);

		int intPageCount = ((intCount + intPageSize) - 1) / intPageSize;// 总页数

		FooterUtil footerUtil = new FooterUtil();
		footer = footerUtil.getPageFooter(intPage, intPageCount, intPageSize,
				intCount);

		// System.out.println("list.size:"+list.size());
		return list;
	}
}
