<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- salseInput.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf" %>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		$(function(){
			//alert("test");
			//ECSideUtil.refresh('ec');
		});
	function clearAll() {
		with (document.forms[0]) {
			rep_id.value = "";
			mat_id.value = "";
			cart_num.value="";
		}
		return true;
	}
	function vali(){
		var urlstr="${store}/sale/doAjaxValidateCart.do";
		var dataReq={assId:'${associator.assId}'};
		var val=0;
		$j.ajax({
			type:"post",
			url:urlstr,
			data:dataReq,
			dataType:"text",
			success:function (dataRes){
				if(dataRes.length>0){
					alert(dataRes);
				}
				$j("#settleImgForm").submit();
			}
		});
	}

	function settleAccounts(){
		//alert(ECSideUtil.getTotalRows("ec"));
		var urlstr="${store}/sale/doAjaxValidateCart.do";
		var dataReq={assId:'${associator.assId}'};
		$j.ajax({
			type:"post",
			url:urlstr,
			data:dataReq,
			dataType:"text",
			success:function (dataRes){
				if(dataRes.length>0){
					alert(dataRes);
					return false;
				}else{
					$j("#settleImgForm").submit();
				}
			}
		});
	}
	function validate(){
		with(document.forms[0]){
			if(rep_id.value!=""&&mat_id.value!=""){
				alert("维修项目和维修材料只能选择一项！");
				return false;
			}
			if(rep_id.value==""&&mat_id.value==""){
				alert("维修项目和维修材料必须选择一项！");
				return false;
			}
			if(cart_num.value==""){
				alert("数量必须输入！");
				return false;
			}
			if(!checkdec(cart_num,0,"001")){
				return false;
			}
//判断数量是否超过库存量
			var index=mat_id.selectedIndex;
			var text=mat_id.options[index].text;
			var value=mat_id.options[index].value;
			if(value!=''){
				var strs=text.split(":");
				var num=strs[strs.length-1];
				if(Number(num)<Number(cart_num.value)){
					alert("库存量["+num+"],不足，请确认！");
					return false;
				}
			}
			return true;
		}
	}
	
	function addCart(){
		with(document.forms[0]){
			if(rep_id.value!=""&&mat_id.value!=""){
				alert("维修项目和维修材料只能选择一项！");
				return false;
			}
			if(rep_id.value==""&&mat_id.value==""){
				alert("维修项目和维修材料必须选择一项！");
				return false;
			}
			if(cart_num.value==""){
				alert("数量必须输入！");
				return false;
			}
			if(!checkdec(cart_num,0,"001")){
				return false;
			}
//判断数量是否超过库存量
			var index=mat_id.selectedIndex;
			var text=mat_id.options[index].text;
			var value=mat_id.options[index].value;
			if(value!=''){
				var strs=text.split(":");
				var num=strs[strs.length-1];
				if(Number(num)<Number(cart_num.value)){
					alert("库存量["+num+"],不足，请确认！");
					return false;
				}
			}
			/*
			var urlstr="${store}/sale/doAjaxAddCart.do";
			var dataReq={assId:'${associator.assId}',repId:rep_id.value,matId:mat_id.value,cartNum:cart_num.value};
			$j.ajax({
				type:"post",
				url:urlstr,
				data:dataReq,
				dataType:"text",
				success:function (dataRes){
					alert(dataRes);
					ECSideUtil.reload("ec", 1);
					ECSideUtil.refresh("ec");
				}
			});
			*/
			return true;
		}
	}
	
</script>
	</head>
	<body onload="clearAll();">
		<center>

			<H1 align="center">
				请输入编号以及数量
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/sale/cartAdd" onsubmit="return validate();" >
				<html:hidden property="ass_id" value="${associator.assId}" />
				<table border="1" width="50%">
					<tr>
						<td colspan="2"><font color="red">维修项目和维修材料只能选择一项！</font></td>	
					</tr>
					<tr>
						<th>维修项目选择</th>
						<td>
							<html:select property="rep_id" >
								<html:options collection="repList" property="code" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>维修材料选择</th>
						<td>
							<html:select property="mat_id">
								<html:options collection="matList" property="code" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							数量
						</th>
						<td>
							<html:text property="cart_num"></html:text>
							<font color="red">*</font>
						</td>
					</tr>
				</table>
				<br>
				<br>
				<center>
					<html:submit styleClass="button" value="加入清单" ></html:submit>
					<!-- <input class="button" type="button" value="加入购物车"  onclick="addCart()"/> -->
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="button" value="清空"
						onclick="clearAll();">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					
				</center>
			</html:form>
		</center>
<br><br><br><br>
<CENTER>
		<ec:table items="data" var="viewBean" title="" 
						action="${store}/sale/cartAdd.do"
						deleteAction="${store}/sale/doAjaxDeleteCartItem.do"
						batchUpdate="true" 
						editable="true" 
						retrieveRowsCallback="process" 
						sortRowsCallback="limit" 
						filterRowsCallback="limit"  
						classic="true" 
						showPrint="true" 
						xlsFileName="购物车.xls"
						csvFileName="购物车.csv" 
						pdfFileName="购物车.pdf" 
						sortable="false"
						filterable="true" width="99%" 
						useAjax="true" 
						rowsDisplayed="10"
						pageSizeList="10,20,30,50,100,all" 
						resizeColWidth="false"
						toolbarContent="save del|extend|status" >
			<ec:row recordKey="${viewBean.data9}">
				<ec:column property="_0" title="序号" value="${GLOBALROWCOUNT}"
					sortable="false"></ec:column>
				<ec:column property="data1" title="会员卡号"  ></ec:column>
				<ec:column property="data7" title="维修项目" sortable="false" ></ec:column>
				<ec:column property="data8" title="维修材料"></ec:column>
				<ec:column property="data4" title="数量"></ec:column>
				<ec:column property="data5" title="单价"></ec:column>
				<ec:column property="data6" title="小计" calc="total" calcTitle="总价" format="0.00"></ec:column>
			</ec:row>
		</ec:table>
		<br /><br />
		<form id="settleImgForm" action="${store}/sale/settleAccounts.do">
			<a id="settleImg" onclick="settleAccounts()" >
						<img alt="" border="0" src="${store }/images/jiesuan.gif">
			</a>
		</form>
		<!-- href="${store}/sale/settleAccounts.do?ass_id=${associator.assId}" -->
</CENTER>
	</body>
</html>