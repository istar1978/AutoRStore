<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- materialAdd.jsp 维修材料入库 -->
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
	
</script>
	</head>
	<body onload="clearAll()">
		<center>

			<H1 align="center">
				维修材料入库管理
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/material/addMaterial" onsubmit="return validate();">
				<html:hidden property="action" />
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
							<html:text property="dr_name" size="40" maxlength="100"></html:text>
						</td>
						<th>
							分类
						</th>
						<td>
							<html:select property="dr_category">
								<html:options collection="dCategoryList" property="code" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							进货价格
						</th>
						<td>
							<html:text property="pre_price" size="40" maxlength="11"></html:text>
						</td>
						<th>
							上架价格
						</th>
						<td>
							<html:text property="real_price" size="40" maxlength="11"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							生产厂家
						</th>
						<td>
							<html:text property="dr_factory" size="40" maxlength="100"></html:text>
						</td>
						<th>
							生产日期
						</th>
						<td>
							<html:text property="pro_date" size="8" maxlength="4"
								style="iform" onfocus="WdatePicker();" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							进货日期
						</th>
						<td>
							<html:text property="in_date" size="8" maxlength="4"
								style="iform" onfocus="WdatePicker();" readonly="true" />
						</td>
						<th>
							数量
						</th>
						<td>
							<html:text property="dr_num" size="40" maxlength="20"></html:text>
						</td>
					</tr>

				</table>

				<br>
				<br>
				<br>
				<br>
				<br>
				<html:submit value="提交" styleClass="button"></html:submit>
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空" onclick="clearAll()">
			</html:form>
		</center>

	</body>
</html>