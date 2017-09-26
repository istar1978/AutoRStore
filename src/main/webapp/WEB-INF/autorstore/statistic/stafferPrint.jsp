<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- stafferPrint.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
	</head>
	<body>
	<center>
		<h1>
			职员列表
		</h1>
		<hr>
		
<br /><br /><br />
		<ec:table items="data" 
							var="viewBean" 
							title="" 
							editable="false"
							action="${pageContext.request.contextPath}/chart/stafferPrint.do"
							classic="true" 
							showPrint="true" 
							xlsFileName="职员列表.xls"
							csvFileName="职员列表.csv" 
							pdfFileName="职员列表.pdf" 
							sortable="false"
							width="99%" 
							useAjax="true" 
							rowsDisplayed="1000"
							pageSizeList="all" 
							resizeColWidth="false"
							toolbarContent="save add del|export |extend|status">
			<ec:row>
				<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}" sortable="false"></ec:column>
				<ec:column property="data1" title="职员编号" sortable="false" filterable="true"></ec:column>
				<ec:column property="data2" title="职员姓名"></ec:column>
				<ec:column property="data14" title="职位"></ec:column>
				<ec:column property="data10" title="级别"></ec:column>
				<ec:column property="data11" title="所属部门名称"></ec:column>
				<ec:column property="data12" title="所属机构名称"></ec:column>
				<ec:column property="data8" title="创建日期"></ec:column>
				<ec:column property="data13" title="状态"></ec:column>
			</ec:row>
		</ec:table>
</CENTER>
	</body>
</html>