<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- settleAccounts.jsp -->

<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>
			购物清单
		</h1>
<html:errors />
		<br>
		<br>
		<br>
		<center>
			<ec:table items="data" 
							var="viewBean" 
							title="${date}${time}"
							showTitle="true"
							editable="false"
							action="${store}/sale/settleAccountsOut.do?conId=${conId}"
							classic="true" 
							showPrint="true" 
							xlsFileName="购物清单.xls"
							csvFileName="购物清单.csv" 
							pdfFileName="购物清单.pdf" 
							sortable="false"
							filterable="true" 
							width="99%" 
							useAjax="true" 
							rowsDisplayed="1000"
							pageSizeList="all" 
							resizeColWidth="false"
							toolbarContent="save add del|export |extend">
				<ec:row>
					<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}" sortable="false"></ec:column>
					<ec:column property="data7" title="维修项目" sortable="true" filterable="true"></ec:column>
					<ec:column property="data8" title="维修材料"></ec:column>

					<ec:column property="data5" title="单价"></ec:column>
					<ec:column property="data4" title="数量"></ec:column>
					<ec:column property="data6" title="小计" calc="total" calcTitle="总价" format="0.00"></ec:column>
				</ec:row>

<ec:extend>会员卡号：${assId }  总价：【${sumPrice}元】 本次积分：【${sumVal }】 总积分：【${aSumVal}】</ec:extend>
			</ec:table>
		</center>
	</body>
</html>