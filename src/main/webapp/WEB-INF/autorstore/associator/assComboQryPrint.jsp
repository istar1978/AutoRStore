<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- assComboQryPrint.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
	</head>
	<body>
<h1>
			会员已购买套餐查询
		</h1>
		<hr>
<br><br><br>
<CENTER>
		<ec:table items="data" 
						var="viewBean" 
						title="会员已购买套餐明细" 
						showTitle="false"
						editable="false"
						action="${pageContext.request.contextPath}/assCombo/assComboQry.do"
						classic="true" 
						showPrint="true" 
						xlsFileName="会员已购套餐明细.xls"
						csvFileName="会员已购套餐明细.csv" 
						pdfFileName="会员已购套餐明细.pdf" 
						sortable="false"
						filterable="true" 
						width="99%" 
						useAjax="true" 
						pageSizeList="10,20,30,50,100,all" 
						resizeColWidth="false"
						toolbarContent="pagesize|pagejump|navigation|export|extend|status">
			<ec:row>
				<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}"
					sortable="false"></ec:column>
				<ec:column property="data1" title="套餐编号"></ec:column>
				<ec:column property="data4" title="会员卡号"></ec:column>
				<ec:column property="data13" title="维修项目名称" ></ec:column>
				<ec:column property="data15" title="起始日期"></ec:column>
				<ec:column property="data16" title="结束日期"></ec:column>
				<ec:column property="data17" title="办理日期"></ec:column>
				<ec:column property="data8" title="剩余次数"></ec:column>
				<ec:column property="data11" title="购买金额"></ec:column>
				<ec:column property="data19" title="状态"></ec:column>
			</ec:row>
		</ec:table>
</CENTER>
	</body>
</html>