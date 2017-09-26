<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- repairItemList.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>
			维修项目查询
		</h1>
		<hr>
<br><br><br>
<CENTER>
		<ec:table items="dataList" 
						var="viewBean" 
						title="维修项目查询" 
						showTitle="false"
						editable="false"
						action="${store}/material/repairItemList.do"
						classic="true" 
						showPrint="true" 
						xlsFileName="维修项目查询.xls"
						csvFileName="维修项目查询.csv" 
						pdfFileName="维修项目查询.pdf" 
						sortable="false"
						width="99%" 
						useAjax="true" 
						pageSizeList="10,20,50,100,all" 
						toolbarContent="navigation|pagejump |pagesize|export|extend|status"
						resizeColWidth="false" >
			<ec:row>
				<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}"
					sortable="false"></ec:column>
				<ec:column property="data1" title="维修项目编号"></ec:column>
				<ec:column property="data2" title="维修项目名称" ></ec:column>
				<ec:column property="data6" title="分类"></ec:column>
				<ec:column property="data4" title="价格"></ec:column>
				<ec:column property="data7" title="状态"></ec:column>
			</ec:row>
		</ec:table>
</CENTER>
	</body>
</html>