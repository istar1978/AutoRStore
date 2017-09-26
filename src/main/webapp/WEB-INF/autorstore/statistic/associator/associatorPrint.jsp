<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- associatorPrint.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>
			会员列表显示
		</h1>
		<hr>
<br /><br /><br />
<CENTER>
		<ec:table items="data" 
						var="viewBean" 
						title="" 
						editable="false"
						action="${store}/chart/associatorPrint.do"
						retrieveRowsCallback="process" 
						sortRowsCallback="limit" 
						filterRowsCallback="limit"  
						classic="true" 
						showPrint="true" 
						xlsFileName="会员列表.xls"
						csvFileName="会员列表.csv" 
						pdfFileName="会员列表.pdf" 
						sortable="false"
						filterable="true" 
						width="99%" 
						useAjax="true" 
						rowsDisplayed="10"
						pageSizeList="10,20,30,50,100,all" 
						resizeColWidth="false"
						toolbarContent="pagesize|pagejump|navigation|export|extend|status">
			<ec:row>
				<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}" sortable="false"></ec:column>
				<ec:column property="data1" title="会员卡号" sortable="false" filterable="true"></ec:column>
				<ec:column property="data7" title="电话"></ec:column>
				<ec:column property="data8" title="积分"></ec:column>
				<ec:column property="data20" title="余额"></ec:column>
				<ec:column property="data21" title="赠送余额"></ec:column>
				<ec:column property="data15" title="等级"></ec:column>
				<ec:column property="data10" title="激活日期"></ec:column>
				<ec:column property="data18" title="状态"></ec:column>
			</ec:row>
		</ec:table>
</CENTER>
	</body>
</html>