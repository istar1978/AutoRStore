<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- salsesComboInput.jsp -->
<html>
	<head>
	<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function clearAll() {
		with (document.forms[0]) {
			com_id.value = "";
		}
		return true;
	}

	function validate(){
		with(document.forms[0]){
			if(com_id.value==""){
				alert("已购套餐必须选择！");
				return false;
			}
			return true;
		}
	}
</script>
	</head>
	<body>
		<center>
			<H1 align="center">
				请选择已购套餐
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/sale/cartComboAdd" onsubmit="return validate();">
				<html:hidden property="ass_id" value="${ass_id}" />
				<table border="1" width="50%">
					<tr>
						<th>已购套餐选择</th>
						<td>
							<html:select property="com_id" style="width=200">
								<html:options collection="comboList" property="code" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
					
				</table>
				<br>
				<br>
				<center>
					<html:submit styleClass="button" value="提交"></html:submit>
				</center>
			</html:form>
		</center>

	</body>
</html>