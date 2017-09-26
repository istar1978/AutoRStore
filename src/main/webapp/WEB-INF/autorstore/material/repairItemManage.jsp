<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- repairItemManage.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function clearAll(){
		var manageForm=$("manageForm");
		manageForm["rep_name"].value="";
		manageForm["rep_classify"].value="";
		manageForm["rep_money"].value="";
		
		return true;
	}
	
	function doAction(action){
		var manageForm=$("manageForm");
		if(action=="insert"||action=="update"){
			if(!validateIfBlank(manageForm["rep_name"],"维修项目名称必须输入！")){
				return false;
			}
			if(!validateIfBlank(manageForm["rep_classify"],"分类必须选择！")){
				return false;
			}
			if(!validateIfBlank(manageForm["rep_money"],"维修项目价格必须输入！")){
				return false;
			}else{
				if(!validMoney(manageForm["rep_money"])){
					return false;	
				}
			}
		}
		manageForm["action"].value=action;
		manageForm.submit();
		
	}
	function setValue(bg, data1, data2, data3, data4) {
		var manageForm=$("manageForm");
			manageForm["repId"].value=data1;
			manageForm["rep_name"].value = data2;
			manageForm["rep_classify"].value = data3;
			manageForm["rep_money"].value=data4;

		setBgcolor(bg);
	}
</script>
	</head>
	<body onload="clearAll()">
	
		<h1>
			维修项目管理
		</h1>
		<hr />
		<html:errors />
<br /><br /><br />
<CENTER>
	<form name="manageForm" id="manageForm" action="${store }/material/repairItemManage.do"  method="post">
				<input type="hidden" name="action" id="action"/>
				<input type="hidden" name="repId" id="repId" />
				<table cellpadding="2" cellspacing="2" class="bd" width="90%"
					border="1">
					<tr>
						<td class="top" colspan="4"></td>
					</tr>
					<tr>
						<td class="tab" colspan="4">
							<table class="tabs" cellpadding="0" cellspacing="0">
								<tr>
									<td width="9" class="tab_sf"></td>
									<td width="85" class="tab_f">
										维修项目属性
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>
							维修项目名称
						</th>
						<td>
							<input type="text" id="rep_name" name="rep_name" size="40" maxlength="100" />
						</td>
						<th>
							分类
						</th>
						<td>
							<select style="width: 270px" name="rep_classify">
									<ec:options items="repClassifyMap" />
							</select>
						</td>
					</tr>
					
					<tr>
						<th>
							价格
						</th>
						<td>
							<input type="text" id="rep_money" name="rep_money" size="40" maxlength="11">
						</td>
					</tr>
				</table>

				<br />
				<br />
				<br />
				<br />
				<br />
				<input type="button" class="button" value="新增" onclick="doAction('insert')" />
				&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="修改" onclick="doAction('update')" />
				&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="查询" onclick="doAction('query')" />
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空" onclick="clearAll()">
			</form>
<br /><br /><br />
		<ec:table items="dataList" 
						var="viewBean" 
						title="维修项目管理" 
						showTitle="false"
						editable="true"
						action="${store}/material/repairItemManage.do?action=query"
						deleteAction="${store}/material/doAjaxRepairItemDel.do"
						classic="true" 
						showPrint="true" 
						xlsFileName="维修项目查询.xls"
						csvFileName="维修项目查询.csv" 
						pdfFileName="维修项目查询.pdf" 
						sortable="false"
						width="99%" 
						useAjax="true" 
						pageSizeList="10,20,50,100,all" 
						toolbarContent="navigation|save del|export|extend|status"
						resizeColWidth="false" >
			<ec:row recordKey="${viewBean.data1}" onclick="setValue(this,'${viewBean.data1}','${viewBean.data2}','${viewBean.data3}','${viewBean.data4}')">
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