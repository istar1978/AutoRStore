<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- branchInput.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function validate() {
		with (document.forms[0]) {
			if (b_id.value == "") {
				alert("机构编号必须输入！");
				b_id.focus();
				return false;
			}
			if (b_id.value.length != 10) {
				alert("机构编号长度必须为10位！");
				b_id.focus();
				return false;
			}

			if (b_name.value == "") {
				alert("机构名称必须输入！");
				b_name.focus();
				return false;
			}

			if (b_level.value == "") {
				alert("机构级别必须选择！");
				b_level.focus();
				return false;
			}

			if (up_branch.value == "") {
				alert("上级机构必须选择！");
				up_branch.focus();
				return false;
			}

			if (b_stat.value == "") {
				alert("状态必须选择！");
				b_stat.focus();
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
				if (b_id.value == "") {
					alert("机构编号必须输入！");
					b_id.focus();
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

	function setValue(bg, data1, data2, data3, data4, data5) {
		with (document.forms[0]) {
			b_id.value = data1;
			b_name.value = data2;
			b_level.value = data3;
			up_branch.value = data4;
			b_stat.value = data5;
		}
		setBgcolor(bg);

	}
</script>
	</head>
	<body>
		<center>

			<H1 align="center">
				连锁机构管理
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/sysadm/branchManage">
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
										机构属性
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>
							机构编号
						</th>
						<td>
							<html:text property="b_id" size="10" maxlength="10"></html:text>
						</td>
						<th>
							机构名称
						</th>
						<td>
							<html:text property="b_name" size="30" maxlength="100"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							级别
						</th>
						<td>
							<html:select property="b_level">
								<html:options collection="levelList" property="code"
									labelProperty="name" />
							</html:select>
						</td>
						<th>
							上级机构
						</th>
						<td>
							<html:select property="up_branch">
								<html:options collection="branchList" property="code"
									labelProperty="name" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							状态
						</th>
						<td colspan="3">
							<html:select property="b_stat">
								<html:options collection="statList" property="code"
									labelProperty="name" />
							</html:select>
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
					<td class="top" colspan="5"></td>
				</tr>
				<tr>
					<td class="tab" colspan="5">
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
						机构编号
					</th>
					<th>
						机构名称
					</th>
					<th>
						级别
					</th>
					<th>
						上级机构
					</th>
					<th>
						状态
					</th>
				</tr>
				<c:if test="${not empty dataList}">
					<logic:iterate id="viewBean" name="dataList" scope="request">
						<tr
							onclick="setValue(this,'<bean:write name="viewBean" property="data1" filter="true" />',
												'<bean:write name="viewBean" property="data2" filter="true" />',
												'<bean:write name="viewBean" property="data3" filter="true"/>',
												'<bean:write name="viewBean" property="data4" filter="true"/>',
												'<bean:write name="viewBean" property="data5" filter="true" />')">
							<td>
								<bean:write name="viewBean" property="data1" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data2" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data6" filter="true" />
							</td>

							<td>
								<bean:write name="viewBean" property="data7" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data8" filter="true" />
							</td>

						</tr>
					</logic:iterate>
				</c:if>
			</table>

		</center>

	</body>
</html>