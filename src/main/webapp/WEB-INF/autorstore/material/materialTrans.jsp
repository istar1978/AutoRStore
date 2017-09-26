<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- drugTrans.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
		<script type="text/javascript">
	function validate() {
		with (document.forms[0]) {
			if (dr_id.value == "") {
				alert("药品编号必须输入！");
				dr_id.focus();
				return false;
			}
			if (dr_num.value == "") {
				alert("数量必须输入！");
				dr_num.focus();
				return false;
			}
			if (branch_id.value == "") {
				alert("机构必须选择！");
				branch_id.focus();
				return false;
			}
		}
		return true;
	}
	function clearAll() {
		with (document.forms[0]) {
			dr_id.value = "";
			dr_num.value = "";
			branch_id.value = "";
		}
		return true;
	}
</script>
	</head>
	<body>
		<center>
			<table width="760">
				<tr>
					<td>
						<H1 align="center">
							药品移库管理
						</H1>
						<html:errors />
						<br>
						<br>
						<br>
						<html:form action="/drug/transform" onsubmit="return validate();">
							<html:hidden property="dr_val" value="0" />
							<html:hidden property="pre_price" value="0.0" />
							<html:hidden property="real_price" value="0.0" />
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
														注意：编号不能为空
													</td>
													<td width="9" class="tab_ef"></td>

												</tr>
											</table>
										</td>

									</tr>
									<tr>
										<th>
											药品编号

											<font color="red">*</font>
										</th>

										<td>
											<html:text property="dr_id" size="12" maxlength="10"></html:text>

										</td>
									</tr>

									<tr>
										<th>
											数量
										</th>
										<td>
											<html:text property="dr_num" size="10"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											转移机构
										</th>
										<td>
											<html:select property="branch_id">
												<html:options collection="branchList" property="code"
													labelProperty="name" />
											</html:select>
										</td>
									</tr>

								</TBODY>
							</TABLE>
							<br>
							<br>
							<br>
							<center>
								<input type="submit" style="" class="button" value="确定" />
								&nbsp;&nbsp;&nbsp;
								<input type="button" class="button" value="清空"
									onclick="clearAll()">
							</center>
						</html:form>
					</td>
				</tr>
			</table>

		</center>
	</body>
</html>