<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- associatorPrintTime.jsp -->
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
		<br>
		<br>
		<br>
		<CENTER>
			<ec:table items="data" var="viewBean" title="${fromDate}——${endDate }<br><br><br>" editable="false"
				action="${pageContext.request.contextPath}/chart/time/associatorPrint.do"
				classic="true" showPrint="true" xlsFileName="会员列表.xls"
				csvFileName="会员列表.csv" pdfFileName="会员列表.pdf" sortable="false"
				filterable="true" width="99%" useAjax="true" rowsDisplayed="1000"
				pageSizeList="all" resizeColWidth="false"
				toolbarContent="save add del|export |extend">
				<ec:row>
					<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}"
						sortable="false"></ec:column>
					<ec:column property="data1" title="会员卡号" sortable="false"
						filterable="true"></ec:column>
					<ec:column property="data3" title="会员姓名"></ec:column>

					<ec:column property="data2" title="积分"></ec:column>
					<ec:column property="data6" title="等级"></ec:column>
				</ec:row>
			</ec:table>
		</CENTER>
	</body>
</html>