<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>

<!-- rechargeHisQry.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function doQuery(queryFormName, listFormName) {
		var queryForm = $(queryFormName);
		var queryPara = {
			"assId" : queryForm["assId"].value,
			"beginDate" : queryForm["beginDate"].value,
			"endDate" : queryForm["endDate"].value
		};
	
		ECSideUtil.queryECForm(listFormName, queryPara, true);
}
</script>
	</head>
	<body>
		<h1>
			会员充值流水查询
		</h1>
			<form name="queryForm"  action="/chart/rechargeHisQry.do" id="queryForm" >
			<table border="0" cellpadding="0" cellspacing="0" class="simpleFormTable" width="100%" >
			  <tr id="queryUserZone"> 
				<td class="formTableC"> 
			<table align="center" border="0" cellpadding="0" cellspacing="3" class="formTableCore">
				<tr>
					<td width="15%" align="right">会员卡号：</td>
					<td width="35%"><input type="text" name="assId"></td>
				</tr>
<tr>
	<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
				<tr>
					<td width="15%" align="right">起始日期：</td>
					<td width="35%">
						<input name="beginDate" size="8" maxlength="4"  style="iform" onfocus="WdatePicker();" readonly="true"  />
					</td>
					<td width="15%" align="right">终止日期：</td>
					<td width="35%">
						<input name="endDate" size="8" maxlength="4"  style="iform" onfocus="WdatePicker();" readonly="true"  />
					</td>
				</tr>
<tr>
	<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
				<tr>
					<td colspan="4" class="formButtonTD" align="center">
						<input type="button" class="formButton" value="查询" onclick="doQuery('queryForm','ec')" />&#160;&#160;&#160;&#160;
						<input class="formButton" type="reset" />
					</td>
				</tr>
			</table>
			</td>
			</tr>
			</table>
			</form>
		<hr>
		<br>
		<br>
		<br>
		<CENTER>
			<ec:table items="dataList" 
							var="viewBean" 
							title="会员充值流水查询<br></br>${beginDate}——${endDate }"
							editable="false"
							action="${store}/chart/rechargeHisQry.do"
							classic="true" 
							showPrint="true" 
							xlsFileName="(${beginDate})至(${endDate})会员充值流水列表.xls"
							csvFileName="会员充值流水列表.csv" 
							pdfFileName="会员充值流水列表.pdf" 
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
					<ec:column property="data1" title="充值流水号" sortable="false" filterable="true"></ec:column>
					<ec:column property="data2" title="会员卡号"></ec:column>
					<ec:column property="data3" title="充值金额"  calc="total" calcTitle="合计" format="0.00"></ec:column>
					<ec:column property="data6" title="赠送金额"  ></ec:column>
					<ec:column property="data12" title="充值时间"></ec:column>
					<ec:column property="data10" title="操作员"></ec:column>
				</ec:row>
			</ec:table>
		</CENTER>
	</body>
</html>