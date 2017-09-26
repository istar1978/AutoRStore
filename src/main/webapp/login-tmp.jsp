<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<%@include file="/autorstore/public/headtop.jspf"%>
<!-- login.jsp -->
<html>
	<HEAD>
	<%@include file="/autorstore/public/head.jspf"%>
	<script language="javascript">
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
				return true;
		}
	}

	function cancel(){
		window.close();
	}

	function clearAll() {
		with (document.forms[0]) {
			username.value = "";
			password.value = "";
		}
		return true;
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
	function doload() {
		with (document.forms[0]) {
			username.focus();
		}
	}
</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="doload()" background="${store }/images/dise2.jpg">
		<br>
		<center>
			<h1>
				汽修厂会员管理信息系统
			</h1>
		</center>
		<hr>
		<html:form action="/sysadm/login" onsubmit="return check();">
			<html:hidden property="action" />
			<br />
			<center>
				<html:errors />
			</center>
			<br />
			<div align="center">
				<table width="730" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table width="730" border="0" cellpadding="4" cellspacing="0">
								<tr>
									<td align="left" colspan="4">
										<h3>
											管理员登录
										</h3>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="right" >
				
											<h5>登录ID：</h5>
									</td>
									<td colspan="2" valign="top">
										<html:text property="username" maxlength="10" value="" style="width:80px;height:20px"  />
									</td>
								</tr>
								<tr>
									<td colspan="2" align="right">
										<h5 >
											认证密码：
										</h5>
									</td>
									<td colspan="2" valign="top">
										<html:password property="password" maxlength="8" value="" style="width:80px;height:20px" onkeypress="checkkey()" />
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<div align="center">
											<html:submit value="登 录" styleClass="button"></html:submit>
											&nbsp;
											<html:button property="button" styleClass="button" value="复位"
												onclick="clearAll()"></html:button>
											&nbsp;
											<html:button property="button" styleClass="button" value="取消" onclick="cancel()"></html:button>
										</div>
									</td>
								</tr>

							</table>
						</td>
					</tr>
				</table>
			</div>
		</html:form>
	</body>
</html>
