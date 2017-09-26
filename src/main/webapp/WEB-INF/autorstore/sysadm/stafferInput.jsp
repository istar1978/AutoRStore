<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- stafferInput.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function validate() {
		with (document.forms[0]) {
			if (s_id.value == "") {
				alert("职员编号必须输入！");
				s_id.focus();
				return false;
			}

			if (s_id.value.length != 10) {
				alert("职员编号长度必须为10位！");
				return false;
			}
			

			if (s_name.value == "") {
				alert("职员姓名必须输入！");
				s_name.focus();
				return false;
			}
			if (s_position.value == "") {
				alert("职位必须输入!");
				s_position.focus();
				return false;
			}
			if (s_level.value == "") {
				alert("级别必须选择！");
				s_level.focus();
				return false;
			}
			if (dep_id.value == "") {
				alert("部门必须选择！");
				dep_id.focus();
				return false;
			}
			if (branch_id.value == "") {
				alert("机构必须选择！");
				branch_id.focus();
				return false;
			}

			if (s_stat.value == "") {

				alert("状态必须选择！");
				s_stat.focus();
				return false;
			}
		}
		return true;
	}
	function setAction(a) {
		with (document.forms[0]) {

			if (a == "insert" || a == "update") {
				if (!validate) {
					return false;
				}
			}
			if (a == "delete") {
				if (!confirm("真的要删除？")) {
					return false;
				}
			}
			if (a == "query") {
				if (s_id.value == "") {
					alert("职员编号必须输入！");
					s_id.focus();
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
			s_id.value = "";
			//s_password.value = "";
			s_name.value = "";
			s_position.value = "";
			s_level.value = "";
			dep_id.value = "";
			branch_id.value = "";
			s_stat.value = "";
		}
		return true;
	}

	function setValue(bg, data1, data2, data3, data4, data5, data6,data7) {
		with (document.forms[0]) {
			s_id.value = data1;
			s_name.value = data2;
			s_position.value = data3;
			s_level.value = data4;
			dep_id.value = data5;
			branch_id.value = data6;
			s_stat.value = data7;
		}
		setBgcolor(bg);
	}
</script>
	</head>
	<body>
		<center>

			<H1 align="center">
				职员管理
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/sysadm/stafferManage">
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
										职员属性
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>
							职员编号
						</th>
						<td>
							<html:text property="s_id" size="12" maxlength="10" style="iform" />
						</td>
						
						<td colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							职员姓名
						</th>
						<td>
							<html:text property="s_name" size="21" maxlength="30"></html:text>
						</td>
						<th>
							职位
						</th>
						<td>
							<!--<html:text property="s_position" size="21" maxlength="20"></html:text>-->
							<html:select property="s_position">
								<html:options collection="positionList" property="code" labelProperty="name"/>
							</html:select>
						</td>
					</tr>

					<tr>
						<th>
							级别
						</th>
						<td>
							<html:select property="s_level">
								<html:options collection="levelList" property="code"
									labelProperty="name" />
							</html:select>
						</td>
						<th>
							所属部门
						</th>
						<td>
							<html:select property="dep_id">
								<html:options collection="depList" property="code"
									labelProperty="name" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							所属机构
						</th>
						<td>
							<html:select property="branch_id">
								<html:options collection="branchList" property="code"
									labelProperty="name" />
							</html:select>
						</td>

						<th>
							状态
						</th>
						<td>
							<html:select property="s_stat">
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
					<td class="top" colspan="8"></td>
				</tr>
				<tr>
					<td class="tab" colspan="8">
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
						职员编号
					</th>
					<th>
						职员姓名
					</th>
					<th>
						职位
					</th>
					<th>
						级别
					</th>
					<th>
						所属部门
					</th>
					<th>
						所属机构
					</th>
					<th>
						创建日期
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
												'<bean:write name="viewBean" property="data4" filter="true"/>',
												'<bean:write name="viewBean" property="data5" filter="true"/>',
												'<bean:write name="viewBean" property="data6" filter="true"/>',
												'<bean:write name="viewBean" property="data7" filter="true"/>',
												'<bean:write name="viewBean" property="data9" filter="true" />')">
							<td>
								<bean:write name="viewBean" property="data1" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data2" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data14" filter="true" />
							</td>

							<td>
								<bean:write name="viewBean" property="data10" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data11" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data12" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data8" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data13" filter="true" />
							</td>
						</tr>
					</logic:iterate>
				</c:if>
			</table>

		</center>

	</body>
</html>