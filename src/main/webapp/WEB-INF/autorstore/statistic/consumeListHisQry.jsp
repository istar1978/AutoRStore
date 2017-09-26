<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>

<!-- consumeListHis.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function doQuery(queryFormName, listFormName) {
		var queryForm = $(queryFormName);
		var queryPara = {
			"beginDate" : queryForm["beginDate"].value,
			"endDate" : queryForm["endDate"].value,
			"repId" : queryForm["repId"].value,
			"matId" : queryForm["matId"].value
		};
	
		ECSideUtil.queryECForm(listFormName, queryPara, true);
}
</script>
	</head>
	<body>
		<h1>
			消费明细查询
		</h1>
			<form name="queryForm"  action="/chart/consumeListHisQry.do" id="queryForm" >
			<table border="0" cellpadding="0" cellspacing="0" class="simpleFormTable" width="100%" >
			  <tr id="queryUserZone"> 
				<td class="formTableC"> 
			<table align="center" border="0" cellpadding="0" cellspacing="3" class="formTableCore">
<tr>
	<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
				<tr>
					<td width="15%" align="right">起始日期：</td>
					<td width="35%">
						<input name="beginDate" size="8" maxlength="4"  style="iform" onfocus="WdatePicker();" readonly="true" />
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
	<td width="15%" align="right">维修项目：</td>
	<td width="35%">
		<select style="width: 150px" name="repId">
			<ec:options items="repMap"></ec:options>
		</select>
	</td>
	<td width="15%" align="right">维修材料编号：</td>
	<td width="35%">
		<select style="width: 150px" name="matId">
				<ec:options items="matMap" />
		</select>
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
							title="销售明细<br></br>${beginDate}——${endDate }"
							editable="false"
							action="${store}/chart/consumeListHisQry.do"
							classic="true" 
							showPrint="true" 
							xlsFileName="(${beginDate})至(${endDate})销售明细列表.xls"
							csvFileName="销售明细列表.csv" 
							pdfFileName="销售明细列表.pdf" 
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
					<ec:column property="data4" title="维修项目"></ec:column>
					<ec:column property="data5" title="维修材料"></ec:column>
					<ec:column property="data3" title="数量"></ec:column>
				</ec:row>
			</ec:table>
		</CENTER>
	</body>
</html>