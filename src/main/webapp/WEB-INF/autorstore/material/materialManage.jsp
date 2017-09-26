<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- materialManage.jsp 维修材料管理 -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function validate() {
		with (document.forms[0]) {
			/*
			if (dr_id.value == "") {
				alert("维修材料编号必须输入！");
				dr_id.focus();
				return false;
			}
			*/
			if (dr_id.value.length != 10) {
				alert("维修材料编号必须输入10位数字！");
				dr_id.focus();
				return false;
			}
			if (dr_name.value == "") {
				alert("维修材料名称必须输入！");
				dr_name.focus();
				return false;
			}

			if (dr_category.value == "") {
				alert("维修材料分类必须输入！");
				dr_category.focus();
				return false;
			}

			if (pre_price.value == "") {
				alert("进货价格必须输入！");
				pre_price.focus();
				return false;
			}

			if (real_price.value == "") {
				alert("上架价格必须输入！");
				real_price.focus();
				return false;
			}

			if (dr_factory.value == "") {
				alert("生产厂家必须输入！");
				dr_factory.focus();
				return false;
			}

			if (pro_date.value == "") {
				alert("生产日期必须输入！");
				pro_date.focus();
				return false;
			}

			if (in_date.value == "") {
				alert("进货日期必须输入！");
				in_date.focus();
				return false;
			}

			if (dr_num.value == "") {
				alert("进货数量必须输入！");
				dr_num.focus();
				return false;
			}
		}
		return true;
	}

	function clearAll(){
		with(document.forms[0]){
			//dr_id.value="";
			dr_name.value="";
			dr_category.value="";
			pre_price.value="";
			real_price.value="";
			dr_factory.value="";
			pro_date.value="";
			in_date.value="";
			dr_num.value="";
		}
		return true;
	}
	
	function doAction(action){
		var manageForm=$("manageForm");
		var urlStr="${store}/material/materialManage.do?action="+action;
		var dr_name=manageForm["dr_name"].value;
		var dr_category=manageForm["dr_category"].value;
		var pre_price=manageForm["pre_price"].value;
		var real_price=manageForm["real_price"].value;
		var dr_factory=manageForm["dr_factory"].value;
		var pro_date=manageForm["pro_date"].value;
		var in_date=manageForm["in_date"].value;
		var dr_num=manageForm["dr_num"].value;
		var params={
				"dr_name":dr_name
				,"dr_category":dr_category
				,"pre_price":pre_price
				,"real_price":real_price
				,"dr_factory":dr_factory
				,"pro_date":pro_date
				,"in_date":in_date
				,"dr_num":dr_num
				,"action":action
		};
		//ECSideUtil.queryECForm("ec", params, true);
		//数据判断
		if(action=="insert"||action=="update"){
			if(!validateIfBlank(manageForm["dr_name"],"维修材料名称必须输入！")){
				return false;
			}
		
			if(!validateIfBlank(manageForm["dr_category"],"分类必须选择！")){
				return false;
			}
			if(!validateIfBlank(manageForm["pre_price"],"进货价格必须输入！")){
				return false;
			}else{
				if(!validMoney(manageForm["pre_price"])){
					return false;	
				}
			}
			
			if(!validateIfBlank(manageForm["real_price"],"上架价格必须输入！")){
				return false;
			}else{
				if(!validMoney(manageForm["real_price"])){
					return false;
				}
			}
			
			if(!validateIfBlank(manageForm["dr_factory"],"生产厂家必须输入！")){
				return false;
			}
			
			if(!validateIfBlank(manageForm["pro_date"],"生产日期必须选择！")){
				return false;
			}
			if(!validateIfBlank(manageForm["in_date"],"进货日期必须选择！")){
				return false;
			}
			
			if(!validateIfBlank(manageForm["dr_num"],"数量必须输入！")){
				return false;
			}else{
				if(!checkdec(manageForm["dr_num"],0,"001")){
					return false;
				}
			}
		}
		//ECSideUtil.operateECForm(urlStr,resFunc,params,true,"ec");
		//ECSideUtil.queryECForm("ec", params, true);
		manageForm["action"].value=action;
		manageForm.submit();
	}
	function resFunc(){
		ECSideUtil.reload("ec", 1);
		ECSideUtil.getGridObj("ec").hideWaitingBar();
		clearAll();
	}
	function setValue(bg, data1, data2, data3, data4,data5,data6,data7,data8,data9) {
		var manageForm=$("manageForm");
			manageForm["matId"].value=data1;
			manageForm["dr_name"].value = data2;
			manageForm["dr_category"].value = data3;
			manageForm["pre_price"].value=data4;
			manageForm["real_price"].value=data5;
			manageForm["dr_factory"].value=data6;
			manageForm["pro_date"].value=data7;
			manageForm["in_date"].value=data8;
			manageForm["dr_num"].value=data9;

		setBgcolor(bg);
	}
</script>
	</head>
	
	<body onload="clearAll()">
<center>
			<H1 align="center">
				维修材料管理
			</H1>
			<html:errors />
			<br />
			<br />
			<form name="manageForm" id="manageForm" action="${store }/material/materialManage.do" method="post">
				<input type="hidden" name="action" id="action">
				<input type="hidden" name="matId" id="matId">
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
										维修材料属性
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>
							维修材料名称
						</th>
						<td>
							<input type="text" name="dr_name" size="40" maxlength="100" id="dr_name">
						</td>
						<th>
							分类
						</th>
						<td>
						<select style="width: 270px" name="dr_category">
									<ec:options items="dCategoryMap" />
							</select>
						</td>
					</tr>
					<tr>
						<th>
							进货价格
						</th>
						<td>
							<input type="text" name="pre_price" size="40" maxlength="11" />
						</td>
						<th>
							上架价格
						</th>
						<td>
							<input type="text" name="real_price" size="40" maxlength="11">
						</td>
					</tr>
					<tr>
						<th>
							生产厂家
						</th>
						<td>
							<input type="text" name="dr_factory" size="40" maxlength="100">
						</td>
						<th>
							生产日期
						</th>
						<td>
								<input name="pro_date" size="8" maxlength="4"  style="iform" onfocus="WdatePicker();" readonly="true"  />
						</td>
					</tr>
					<tr>
						<th>
							进货日期
						</th>
						<td>
								<input name="in_date" size="8" maxlength="4"  style="iform" onfocus="WdatePicker();" readonly="true"  />
						</td>
						<th>
							数量
						</th>
						<td>
							<input type="text" size="40" maxlength="20" name="dr_num">
						</td>
					</tr>

				</table>

				<br />
				<br />
				<input type="button" class="button" value="新增" onclick="doAction('insert')" />
				&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="修改" onclick="doAction('update')" />
				&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="查询" onclick="doAction('query')" />
				&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空" onclick="clearAll()" />
			</form>
		</center>
		<br />
		<br />
		<br />
		<br />
		<ec:table items="dataList" 
						var="viewBean" 
						title="维修材料管理" 
						showTitle="false"
						editable="true"
						action="${store}/material/materialManage.do?action=query"
						deleteAction="${store}/material/doAjaxMaterialDel.do"
						classic="true" 
						showPrint="true" 
						xlsFileName="维修材料查询.xls"
						csvFileName="维修材料查询.csv" 
						pdfFileName="维修材料查询.pdf" 
						sortable="false"
						width="99%" 
						useAjax="true" 
						pageSizeList="10,20,50,100,all" 
						toolbarContent="navigation|save del|export|status"
						resizeColWidth="false" >
			<ec:row recordKey="${viewBean.data1 }" onclick="setValue(this,'${viewBean.data1}','${viewBean.data2}','${viewBean.data3}','${viewBean.data5}','${viewBean.data6}','${viewBean.data7}','${viewBean.data8}','${viewBean.data9}','${viewBean.data10}')">
				<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}"
					sortable="false"></ec:column>
				<ec:column property="data1" title="维修材料编号"></ec:column>
				<ec:column property="data2" title="维修材料名称" ></ec:column>
				<ec:column property="data13" title="分类"></ec:column>
				<ec:column property="data5" title="进货价格"></ec:column>
				<ec:column property="data6" title="上架价格"></ec:column>
				<ec:column property="data7" title="生产厂家"></ec:column>
				<ec:column property="data8" title="生产日期"></ec:column>
				<ec:column property="data9" title="进货日期"></ec:column>
				<ec:column property="data10" title="数量"></ec:column>
				<ec:column property="data14" title="所在机构"></ec:column>
				<ec:column property="data15" title="状态"></ec:column>
			</ec:row>
		</ec:table>
	</body>
</html>