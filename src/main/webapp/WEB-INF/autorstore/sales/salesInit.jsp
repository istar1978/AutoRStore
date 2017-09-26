<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- salesInit.jsp -->
<html>
	<head>
 		<%@include file="/autorstore/public/head.jspf"%>
		<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function clearAll() {
		with (document.forms[0]) {
			ass_id.value = "";
			yorn.value="";
		}
		return true;
	}
	function validate(){
		with(document.forms[0]){
			if(yorn.value==""){
				alert("请确实是否使用套餐！");
				yorn.focus();
				return false;
			}
			//使用套餐时，会员号必须输入
			if(yorn.value=="0"&&ass_id.value==""){
				alert("使用套餐时，会员卡号必须输入！");
				ass_id.focus();
				return false;
			}
			if(ass_id.value.length>0&&ass_id.value.length!=10){
				alert("请确认会员号是否输入正确！");
				return false;
			}
			return true;
		}
	}
</script>
	</head>
	<body onload="clearAll();">
<br /><br />
		<center>
			<H1 align="center">
				请输入会员卡号(使用套餐时，会员卡号必须输入，否则可略过,直接点确定)
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form  action="/sale/salesInputInit" onsubmit="return validate();">
				<br>
				<br>
				<br>
				<br>
				<table cellpadding="2" cellspacing="2" class="bd" width="90%"
					border="1" >
					<tr>
						<th align="right" >会员卡号:</th>
						<td >
							<html:text  property="ass_id" size="20" maxlength="10" accesskey="ass_id"></html:text>
						</td>
					</tr>

					<tr>
						<th>是否使用套餐:</th>
						<td >
				<html:select property="yorn">
								<html:options collection="yornList" property="code" labelProperty="name"/>
							</html:select>
							<font color="red">*</font>
					</td>
				</table>
				<br>
				<br>
				<br>
				<center>
					<html:submit styleClass="button" value="确定"></html:submit>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="button" value="清空" onclick="clearAll()">
				</center>
			</html:form>
		</center>


	</body>
</html>