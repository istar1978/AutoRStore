<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- dicDataInput.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function validate() {
		with (document.forms[0]) {
			if (dic_large.value == "") {
				alert("参数大类编号必须输入！");
				dic_large.focus();
				return false;
			}
			if (dic_large.value.length != 4) {
				alert("参数大类编号长度必须为4位！");
				dic_large.focus();
				return false;
			}

			if (dic_value.value == "") {
				alert("参数值必须输入！");
				dic_value.focus();
				return false;
			}
			if(dic_value.value.length!=2){
				alert("参数值长度必须为2位");
				dic_value.focus();
				return false;
			}

		}
		return true;
	}

	function setAction(a) {
		with (document.forms[0]) {
			if (a == "insert" || a == "update") {
				if (!validate()) {
					return false;
				}
			}
			if (a == "delete") {
				if (!confirm("真的要删除？")) {
					return false;
				}
			}

			if (a == "query") {
				if (dic_large.value == "") {
					alert("参数大类编号必须输入！");
					dic_large.focus();
					return false;
				}
			}
			action.value = a;
			submit();
		}
		return true;
	}

	function clearAll() {
		with (document.forms[0]) {
			b_id.value = "";
			b_name.value = "";
			b_level.value = "";
			up_branch.value = "";
			b_stat.value = "";
		}
		return true;
	}

	function setValue(bg, data1, data2, data3, data4) {
		with (document.forms[0]) {
			dic_large.value = data1;
			dic_value.value = data2;
			dic_name.value = data3;
			dic_text.value = data4;
		}
		setBgcolor(bg);

	}
</script>
	</head>
	<body>
		<center>

			<H1 align="center">
				参数管理
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/sysadm/dicDataManage">
				<html:hidden property="action" value="query"/>
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
										参数属性
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>
							参数大类编号
						</th>
						<td>
							<html:text property="dic_large" size="10" maxlength="4"></html:text>
							<font color="red">*</font>
						</td>
						<th>
							参数值
						</th>
						<td>
							<html:text property="dic_value" size="10" maxlength="2"></html:text>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<th>
							参数显示名
						</th>
						<td>
							<html:text property="dic_name" size="20" maxlength="100"></html:text>
						</td>
						<th>
							参数说明
						</th>
						<td>
							<html:text property="dic_text" size="20" maxlength="200"></html:text>
						</td>
					</tr>
				</table>
				<br>
				<input type="button" class="button" name="add" value="新增"
					onclick="setAction('insert')">
&nbsp;&nbsp;&nbsp;

				<input type="button" class="button" name="update" value="修改"
					onclick="setAction('update')">
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" name="del" value="删除"
					onclick="setAction('delete')">
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" name="query" value="查询"
					onclick="setAction('query')">
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空" onclick="clearAll()">

				<BR>
				<table width="90%" border="0">

					<tr>
						<c:if test="${not empty footer}">
							<%=request.getAttribute("footer")%>
						</c:if>
					</tr>
				</table>
			</html:form>
			<table border="1" width="90%" cellpadding="0" cellspacing="1">
				<tr>
					<td class="top" colspan="4"></td>
				</tr>
				<tr>
					<td class="tab" colspan="4">
						<table class="tabs" cellpadding="0" cellspacing="0">
							<tr>
								<td width="9" class="tab_sf"></td>
								<td width="85" class="tab_f">
									显示列表
								</td>
								<td width="9" class="tab_ef"></td>
							</tr>
						</table>
					</td>

				</tr>
				<tr>
					<th>
						参数大类编号
					</th>
					<th>
						参数值
					</th>
					<th>
						参数显示名
					</th>
					<th>
						参数说明
					</th>
				</tr>
				<c:if test="${not empty dataList}">
					<logic:iterate id="viewBean" name="dataList" scope="request">
						<tr
							onclick="setValue(this,'<bean:write name="viewBean" property="data1" filter="true" />',
												'<bean:write name="viewBean" property="data2" filter="true" />',
												'<bean:write name="viewBean" property="data3" filter="true"/>',
												'<bean:write name="viewBean" property="data4" filter="true"/>')">
							<td>
								<bean:write name="viewBean" property="data1" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data2" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data3" filter="true" />
							</td>

							<td>
								<bean:write name="viewBean" property="data4" filter="true" />
							</td>
							
						</tr>
					</logic:iterate>
				</c:if>
			</table>

		</center>

	</body>
</html>