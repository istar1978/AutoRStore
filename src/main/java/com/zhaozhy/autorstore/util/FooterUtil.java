package com.zhaozhy.autorstore.util;

/**
 * 生成分页条
 * 
 * 
 */
public class FooterUtil {
	/**
	 * /**
	 * 
	 * @param intPage
	 *            ((当前在第几页))
	 * @param intPageCount
	 *            (总的页数)
	 * @param intPageSize
	 *            (一页的记录数)
	 * @param intSumCount
	 *            (总记录数)
	 * @return
	 */
	public String getPageFooter(int intPage, int intPageCount, int intPageSize,
			int intSumCount) {
		String str = "";
		int prev = intPage - 1;
		int next = intPage + 1;
		if (intPage > 1)
			str = str
					+ "<INPUT type=submit class=button value=首页 name=firs onclick='this.form.pages.value=1'>";
		else
			str = str
					+ "<INPUT type=submit class=button value=首页 name=firs disabled>";
		if (intPage > 1)
			str = str
					+ "<INPUT type=submit class=button value=上页 name=prev onclick='this.form.pages.value="
					+ prev + "'>";
		else
			str = str
					+ "<INPUT type=submit class=button value=上页 name=prev disabled>";
		if (intPage < intPageCount)
			str = str
					+ "<INPUT type=submit class=button value=下页 name=next onclick='this.form.pages.value="
					+ next + "'>";
		else
			str = str
					+ "<INPUT type=submit class=button value=下页 name=next disabled>";
		if (intPageCount > 1 && intPage != intPageCount)
			str = str
					+ "<INPUT type=submit class=button value=末页 name=last onclick='this.form.pages.value="
					+ intPageCount + "'>";
		else
			str = str
					+ "<INPUT type=submit class=button value=末页 name=last disabled>";
		str = str + " 共" + intSumCount + "条记录";
		str = str
				+ "  每页<SELECT size=1 name=pagesize onchange='this.form.pages.value=1;this.form.submit();'>";
		if (intPageSize == 10)
			str = str + "<OPTION value=10 selected>10</OPTION>";
		else
			str = str + "<OPTION value=10>10</OPTION>";
		if (intPageSize == 20)
			str = str + "<OPTION value=20 selected>20</OPTION>";
		else
			str = str + "<OPTION value=20>20</OPTION>";
		if (intPageSize == 50)
			str = str + "<OPTION value=50 selected>50</OPTION>";
		else
			str = str + "<OPTION value=50>50</OPTION>";
		if (intPageSize == 100)
			str = str + "<OPTION value=100 selected>100</OPTION>";
		else
			str = str + "<OPTION value=100>100</OPTION>";
		if (intPageSize == 200)
			str = str + "<OPTION value=200 selected>200</OPTION>";
		else
			str = str + "<OPTION value=200>200</OPTION>";
		if (intPageSize == 500)
			str = str + "<OPTION value=500 selected>500</OPTION>";
		else
			str = str + "<OPTION value=500>500</OPTION>";
		str = str + "</SELECT>";
		str = str + "条 分" + intPageCount + "页显示 转到";
		str = str
				+ "<SELECT size=1 name=Pagelist onchange='this.form.pages.value=this.value;this.form.submit();'>";
		for (int i = 1; i < intPageCount + 1; i++)
			if (i == intPage)
				str = str + "<OPTION value=" + i + " selected>" + i
						+ "</OPTION>";
			else
				str = str + "<OPTION value=" + i + ">" + i + "</OPTION>";

		str = str + "</SELECT>页";
		str = str + "<INPUT type=hidden value=" + intPage + " name=pages>";
		return str;
	}
}
