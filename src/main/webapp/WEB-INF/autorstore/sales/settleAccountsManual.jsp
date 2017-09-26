<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- settleAccountsManual.jsp -->

<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>
			购物清单（手工录入消费金额）
		</h1>
		<br>
		<br>
		<br>
		<center>
			<ec:table items="data" 
							var="viewBean" 
							title="${date } ${time } " 
							editable="false"
							action="${store}/sale/settleAccountManualJsp.do?con_id=${con_id}"
							classic="true" 
							showPrint="true" 
							xlsFileName="消费清单.xls"
							csvFileName="消费清单.csv" 
							pdfFileName="消费清单.pdf" 
							sortable="false"
							filterable="true" 
							width="99%" 
							useAjax="true" 
							rowsDisplayed="1000"
							pageSizeList="all" 
							resizeColWidth="false"
							toolbarContent="extend">
				<ec:row>
					<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}" sortable="false"></ec:column>
					<ec:column property="data2" title="会员卡号"  ></ec:column>
					<ec:column property="data24" title="会员卡余额"></ec:column>
					<ec:column property="data25" title="会员卡赠送余额"></ec:column>
					<ec:column property="data5" title="消费金额"></ec:column>
					<ec:column property="data6" title="实收金额"></ec:column>
				</ec:row>

<ec:extend>总价：【${sumPrice}元】 本次积分：【${sumPoint }】 总积分：【${aSumPoint}】</ec:extend>
			</ec:table>
		</center>
	</body>
</html>