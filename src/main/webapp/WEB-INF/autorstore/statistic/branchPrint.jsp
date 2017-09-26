<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- branchPrint.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
	</head>
	<body>
	<center>
		<h1>
			机构列表
		</h1>
		<hr />
<br /><br /><br />

		<ec:table items="data" 
							var="viewBean" 
							title="" 
							editable="false"
							action="${pageContext.request.contextPath}/chart/branchPrint.do"
							classic="true" 
							showPrint="true" 
							xlsFileName="机构列表.xls"
							csvFileName="机构列表.csv" 
							pdfFileName="机构列表.pdf" 
							sortable="false"
							filterable="true" 
							width="99%" 
							useAjax="true" 
							rowsDisplayed="1000"
							pageSizeList="all" 
							resizeColWidth="false"
							toolbarContent="save add del|export |extend|status">
			<ec:row>
				<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}" sortable="false"></ec:column>
				<ec:column property="data1" title="机构编号" sortable="false" filterable="true"></ec:column>
				<ec:column property="data2" title="机构名称"></ec:column>
				<ec:column property="data6" title="级别"></ec:column>
				<ec:column property="data7" title="上级机构名称"></ec:column>
				<ec:column property="data8" title="状态"></ec:column>
			</ec:row>
		</ec:table>
</center>
	</body>
</html>