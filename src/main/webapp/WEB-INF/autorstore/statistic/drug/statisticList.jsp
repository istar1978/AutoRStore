<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<!-- statisticList.jsp -->
<html>
	<head>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/ecside/css/ecside_style.css" />

		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ecside/js/prototype_mini.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ecside/js/ecside_msg_utf8_cn.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ecside/js/ecside.js"></script>

		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%=request.getContextPath()%>/theme/public.css"
			rel="stylesheet" type="text/css">

		<title>药品统计列表</title>
		<script type="text/javascript">
	function setColor(obj) {
		setBgcolor(obj);
	}
</script>
	</head>
	<body>
		<h1>
			药品统计列表
		</h1>
		<hr>
		<br>
		<br>
		<br>
		<CENTER>
			<ec:table items="dataList" var="viewBean" title="药品销售统计<br></br>${fromDate }——${endDate }"
				editable="false"
				action="${pageContext.request.contextPath}/chart/drugSale.do"
				classic="true" showPrint="true" xlsFileName="药品统计列表.xls"
				csvFileName="药品统计列表.csv" pdfFileName="药品统计列表.pdf" sortable="false"
				filterable="true" width="99%" useAjax="true" rowsDisplayed="1000"
				pageSizeList="all" resizeColWidth="false"
				toolbarContent="save add del|export |extend">
				<ec:row>
					<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}"
						sortable="false"></ec:column>
					<ec:column property="data1" title="药品编号" sortable="false"
						filterable="true"></ec:column>
					<ec:column property="data2" title="药品名称"></ec:column>
					
					<ec:column property="data3" title="单价(单位：元)"></ec:column>

<ec:column property="data4" title="进货价(单位：元)"></ec:column>
					<ec:column property="data5" title="销量"></ec:column>
					<ec:column property="data6" title="毛利润(单位：元)"></ec:column>
					<ec:column property="data7" title="净利润(单位：元)"></ec:column>
				</ec:row>
<ec:extend>总净利润：${sumJing}元</ec:extend>
			</ec:table>
		</CENTER>
	</body>
</html>