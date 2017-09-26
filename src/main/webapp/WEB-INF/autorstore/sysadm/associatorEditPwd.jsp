<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- associatorEditPwd.jsp -->
<c:if test="${not empty userContext}">
	<HTML>
		<HEAD>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
			<script language="javascript">
	function validate() {
		with (document.forms[0]) {
			if(a_id.value==""){
				alert("请输入会员卡号！");
				a_id.focus();
				return false;
			}
			if (oldPassword.value == "") {
				alert("请输入原始密码!");
				oldPassword.focus();
				return false;
			}
			if (newPassword1.value == "") {
				alert("请输入修改后的密码!");
				newPassword1.focus();
				return false;
			}
			if (newPassword2.value == "") {
				alert("请确认修改后的密码!");
				newPassword2.focus();
				return false;
			}
			if (newPassword2.value != newPassword1.value) {
				alert("两次输入不一致，请重新输入修改后的密码!");
				newPassword1.focus();
				return false;
			}
			action.value = "pwd";
			submit();
		}
		//alert("密码修改！");
		return true;
	}
	function reply(a) {

		if (a == 100) {
			return true;
		}
		if (a > 0) {
			alert("密码修改成功！");
			return true;
		}

		if (a == -1) {
			alert("原始密码输入错误，请重新输入！");
			return true;
		}

		alert("密码修改失败！请重试");

		return true;
	}

	function reset() {
		with (document.forms[0]) {
			a_id.value="";
			oldPassword.value = "";
			newPassword1.value = "";
			newPassword2.value = "";
		}
	}
</script>
			<TITLE>修改密码</TITLE>
		</HEAD>
		<BODY onload="reset();">
			<center>
				<table width="760">
					<tr>
						<td>
							<H1 align="center">
								修改密码
							</H1>
							<html:errors />
							<html:form action="/sysadm/associatorEditPwd">
								<TABLE align="center" border="1" width="480" cellpadding="0"
									cellspacing="0" class="bd">
									<TBODY>
										<tr>
											<td class="top" colspan="4"></td>
										</tr>
										<tr>
											<td class="tab" colspan="10">
												<table class="tabs" cellpadding="0" cellspacing="0">
													<tr>
														<td width="9" class="tab_sf"></td>
														<td width="400" class="tab_f">
															注意：密码不能为空，最长6位
														</td>
														<td width="9" class="tab_ef"></td>

													</tr>
												</table>
											</td>

										</tr>
										<tr>
											<th>
												会员号
<font color="red">*</font>
											</th>
											
											<td>
												<html:text property="a_id" size="12" maxlength="10" style="iform"></html:text>

											</td>
										</tr>

										<TR>
											<TH width="180">
												原密码
												<FONT color="red">*</FONT>
											</TH>
											<TD width="300">
												<html:password size="12" maxlength="6"
													property="oldPassword" style="iform" />
											</TD>
										</TR>
										<TR>
											<TH width="180">
												新密码
												<FONT color="red">*</FONT>
											</TH>
											<TD width="300">
												<html:password size="12" maxlength="6"
													property="newPassword1" style="iform" />
											</TD>
										</TR>
										<TR>
											<TH width="180">
												密码确认
												<FONT color="red">*</FONT>
											</TH>
											<TD width="300">
												<html:password size="12" maxlength="6"
													property="newPassword2" style="iform" />
											</TD>
										</TR>
									</TBODY>
								</TABLE>
<center>
<html:submit value="确定" styleClass="button"></html:submit>
&nbsp;&nbsp;&nbsp;

<html:button property="button" styleClass="button" value="重置"
												onclick="reset()"></html:button>
</center>
							</html:form>
						</td>
					</tr>
				</table>

			</center>

		</BODY>
	</HTML>
</c:if>