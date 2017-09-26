<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%-- <%@ include file="/common/tiglib.jsp" %> --%>
<%@include file="/autorstore/public/headtop.jspf"%>

<!-- login.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="/autorstore/public/head.jspf"%>
	<%-- <script src="${store}/js/utils.js" type="text/javascript" language="javascript"></script> --%>
	<link rel="stylesheet" type="text/css" href="${store}/theme/pingTai.css">
	<script type="text/javascript">
		function resetFun(){
			document.forms[0].reset();
		}
		function doload() {
			with (document.forms[0]) {
				username.focus();
			}
		}
		function check() {
			with (document.forms[0]) {
					if (username.value == "") {
						alert("用户名不能为空，请填写");
						username.focus();
						return false;
					}
					if (password.value == "") {
						alert("密码不能为空，请填写");
						password.focus();
						return false;
					}
					submit();
			}
		}
		function checkkey() {
			if (event.keyCode == 13) {//Enter 回车
				check();
				return true;
			} else if (event.keyCode == 82) {
				check();
				return true;
			} else {
				return false;
			}
		}
	</script>
</head>
<body onload="doload()">
	
	<html:form   action="/sysadm/login" method="post" >
		
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td bgcolor="#1075b1">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="608" background="${store}/images/pingTai/login/login_03.gif">
					<table width="847" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="318" background="${store}/images/pingTai/login/login_04.gif">
								&nbsp;
									
							</td>
						</tr>
						<tr>
							<td height="84">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="381" height="84" background="${store}/images/pingTai/login/login_06.gif">
											&nbsp;
											<center>
										<html:errors />
									</center>
										</td>
										<td width="162" valign="middle"
											background="${store}/images/pingTai/login/login_07.gif">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="44" height="24" valign="bottom">
														<div align="right">
															<span class="STYLE3">用户</span>
														</div>
													</td>
													<td width="10" valign="bottom">
														&nbsp;
													</td>
													<td height="24" colspan="2" valign="bottom">
														<div align="left">
														<html:text property="username" maxlength="10" title="请输入登录名" style="width: 100px; height: 17px; background-color: #87adbf; border: solid 1px #153966; font-size: 12px; color: #283439;"></html:text>
														</div>
													</td>
												</tr>
												
												<tr>
													<td height="24" valign="bottom">
														<div align="right">
															<span class="STYLE3">密码</span>
														</div>
													</td>
													<td width="10" valign="bottom">
														&nbsp;
													</td>
													<td height="24" colspan="2" valign="bottom">
													<html:password property="password" maxlength="8" title="请输入登录密码" value="" onkeypress="checkkey()" style="width: 100px; height: 17px; background-color: #87adbf; border: solid 1px #153966; font-size: 12px; color: #283439;"  />
													</td>
												</tr>
												<tr>
												</tr>
											</table>
										</td>
										<td width="26">
											<img src="${store}/images/pingTai/login/login_08.gif" width="26" height="84">
										</td>
										<td width="67" background="${store}/images/pingTai/login/login_09.gif">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="25">
														<div align="center">
															<a href="#" title="点击登录">
																<img border="0" src="${store}/images/pingTai/login/dl.gif" width="57" height="20" onclick="check()">
															</a>
														</div>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td height="25">
														<div align="center" title="点击清空">
															<a href="#">
																<img border="0" src="${store}/images/pingTai/login/cz.gif" width="57" height="20" onclick="resetFun()">
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="211" background="${store}/images/pingTai/login/login_10.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="206" background="${store}/images/pingTai/login/login_11.gif">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#152753">
					&nbsp;
				</td>
			</tr>
		</table>
	</html:form>
		
</body>
</html>
