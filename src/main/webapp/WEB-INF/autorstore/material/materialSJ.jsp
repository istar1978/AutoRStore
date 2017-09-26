<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!-- drugSJ.jsp -->
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<LINK href="<%=request.getContextPath()%>/theme/public.css"
			rel="stylesheet" type="text/css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/common.js"></script>
		<script language="javascript"
			src="${pageContext.request.contextPath }/js/date/WdatePicker.js"></script>
		<title>药品上架管理</title>
		<script type="text/javascript">
	function validate() {
		with (document.forms[0]) {
			if (dr_id.value == "") {
				alert("药品编号必须输入！");
				dr_id.focus();
				return false;
			}
			if (dr_stat.value!="0"&&dr_num.value == "") {
				alert("数量不能为空！");
				dr_num.focus();
				return false;
			}
			if(dr_stat.value=="0"){
				dr_num.value=1;
			}

			if (dr_stat.value == "") {
				alert("请选择要进行的操作！");
				dr_stat.focus();
				return false;
			}

		}
		return true;
	}

	function clearAll() {
		with (document.forms[0]) {
			dr_id.value = "";
			dr_num.value = "";
			dr_stat.value = "";
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
							药品上架管理
						</H1>
						<html:errors />
						<br>
						<br>
						<br>
						<html:form action="/drug/shangjia" onsubmit="return validate();">
<html:hidden property="dr_val" value="0"/>
<html:hidden property="pre_price" value="0.0"/>
<html:hidden property="real_price" value="0.0"/>
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
											操作
										</th>
										<td>
											<html:select property="dr_stat">
												<html:option value="">------请选择-----</html:option>
												<html:option value="0">药品回库</html:option>
												<html:option value="1">出库上架</html:option>
												<html:option value="2">暂停销售</html:option>
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