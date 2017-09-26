<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- assComboQryInit.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		function validate(){
			with(document.forms[0]){
				if(ass_id.value==""){
					alert("会员卡号必须输入！");
					ass_id.focus();
					return false;
				}
				if(ass_id.value.length!=10){
					alert("会员卡号有误，请确认！");
					ass_id.focus();
					return false;
				}
				return true;
			}
		}
	function clearAll() {
		with (document.forms[0]) {
			ass_id.value = "";
		}
	}
	$j(function(){
		clearAll();
	});
</script>
	<body>
		<center>

			<H1 align="center">
				请输入会员卡号
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/assCombo/assComboQry" onsubmit="return validate();">

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
										请输入：
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>

				</table>
				<br>
				<br>
				<br>
				<br>
				<html:text property="ass_id" size="20" maxlength="10"></html:text>

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