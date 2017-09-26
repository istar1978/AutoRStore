<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- repairItemAdd.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function validate() {
		with (document.forms[0]) {
/*
			if (rep_id.value == "") {
				alert("维修项目编号必须输入！");
				rep_id.focus();
				return false;
			}
			
			if (rep_id.value.length != 10) {
				alert("维修项目编号必须输入10位数字！");
				rep_id.focus();
				return false;
			}
			*/
			if (rep_name.value == "") {
				alert("维修项目名称必须输入！");
				rep_name.focus();
				return false;
			}

			if (rep_stat.value == "") {
				alert("状态必须选择！");
				rep_stat.focus();
				return false;
			}
		}
		return true;
	}

	function clearAll(){
		with(document.forms[0]){
			//rep_id.value="";
			rep_name.value="";
			rep_classify.value="";
			rep_money.value="";
			//rep_stat.value="";
		}
		return true;
	}
	
</script>
	</head>
	<body onload="clearAll()">
		<center>

			<H1 align="center">
				维修项目定义
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/material/addRepairItem" onsubmit="return validate();">
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
							<html:text property="rep_name" size="40" maxlength="100"></html:text>
							<font color="red">*</font>
						</td>
						<th>
							分类
						</th>
						<td>
							<html:select property="rep_classify">
								<html:options collection="rClassifyList" property="code" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
					
					<tr>
						<th>
							价格
						</th>
						<td>
							<html:text property="rep_money" size="40" maxlength="11"></html:text>
						</td>
						<th>状态</th>
						<td>
							<html:select property="rep_stat">
								<html:options collection="rStateList" property="code" labelProperty="name"/>
							</html:select>
							<font color="red">*</font>
						</td>
					</tr>
				</table>

				<br />
				<br />
				<br />
				<br />
				<br />
				<html:submit value="提交" styleClass="button"></html:submit>
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空" onclick="clearAll()">
			</html:form>
		</center>

	</body>
</html>