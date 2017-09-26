<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- departmentInput.jsp -->
<c:if test="${not empty userContext}">
	<HTML>
		<HEAD>
			<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
			<script language="javascript">
	function validate() {
		with (document.forms[0]) {
			if (depId.value == "") {
				alert("部门代码必须输入！");
				depId.focus();
				return false;
			} else if (depName.value == "") {
				alert("部门名称必须输入！");
				depName.focus();
				return false;
			} else if (depType.value == "") {
				alert("部门类型必须选择！");
				depType.focus();
				return false;
			} else if (stat.value == "") {
				alert("部门状态必须选择！");
				stat.focus();
				return false;
			}
		}
		return true;
	}
	function setValue(bg, data1, data2, data3, data4) {
		with (document.forms[0]) {
			depId.value = data1;
			depName.value = data2;
			//branchId.value = data2;
			depType.value = data3;
			stat.value = data4;

		}
		setBgcolor(bg);
	}
	function setAction(a) {
		with (document.forms[0]) {
			if (a == "insert") {
				if (!validate())
					return false;
			}
			if (a == "update") {
				if (!validate()) {
					return false;
				}
			}
			if (a == "delete") {
				if (depId.value == "") {
					alert("部门代码必须输入！");
					depId.focus();
					return false;
				}
				if (!confirm("真的要删除？")) {
					return false;
				}
			}
			action.value = a;
			submit();
		}
		return;
	}

	function clearAll() {
		with (document.forms[0]) {
			depId.value = "";
			depName.value = "";
			//branchId.value="";
			depType.value = "";
			stat.value = "";
		}
	}
</script>
			<TITLE>部门维护</TITLE>
		</HEAD>
		<BODY>
			<center>

				<H1 align="center">
					部门维护
				</H1>
				<html:errors />
				<br>
				<br>
				<html:form action="/sysadm/departmentManage">
					<html:hidden property="action" />
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
											部门属性
										</td>
										<td width="9" class="tab_ef"></td>
									</tr>
								</table>
							</td>

						</tr>

						<tr>
							<th>
								部门代码
							</th>
							<td>
								<html:text property="depId" size="10" maxlength="10" style="iform" />
							</td>
						</tr>

						<tr>
							<th>
								部门名称
							</th>
							<td>
								<html:text property="depName" size="30" maxlength="30"
									style="iform" />
							</td>
						</tr>

						<tr>
							<th>
								部门类型
							</th>
							<td>

								<html:select property="depType" style="iform">
									<html:options collection="typeList" property="code"
										labelProperty="name" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								部门状态
							</th>
							<td>


								<html:select property="stat" style="iform">
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
							部门代码
						</th>
						<th>
							部门名称
						</th>
						<!--<th>操作员组类型</th>-->
						<th>
							部门类型
						</th>
						<th>
							部门状态
						</th>
					</tr>
					<c:if test="${not empty dataList}">
						<logic:iterate id="viewBean" name="dataList" scope="request">
							<tr onclick="setValue(this
												,'<bean:write name="viewBean" property="data1" filter="true" />'
												,'<bean:write name="viewBean" property="data2" filter="true" />'
												,'<bean:write name="viewBean" property="data3" filter="true"/>'
												,'<bean:write name="viewBean" property="data4" filter="true" />')">
								<td>
									<bean:write name="viewBean" property="data1" filter="true" />
								</td>
								<td>
									<bean:write name="viewBean" property="data2" filter="true" />
								</td>
								<td>
									<bean:write name="viewBean" property="data5" filter="true" />
								</td>

								<td>
									<bean:write name="viewBean" property="data6" filter="true" />
								</td>
							</tr>
						</logic:iterate>
					</c:if>
				</table>

			</center>
		</BODY>
	</HTML>
</c:if>
