<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- menuInput.jsp -->
<c:if test="${not empty userContext}">
<HTML>
	<HEAD>
	<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script language="javascript">
	function fInsert() {
		with (document.forms[0]) {
			if (menuId.value == "" || menuName == "" ||(menuAtt[0].checked==false&&menuAtt[1].checked==false)) {
				alert("您还有数据需要录入!");
				return false;
			}
			
			
			if (menuAtt[1].checked==true && urlAddr.value == "") {
				alert("菜单处理文件未录入!");
				return false;
			}
			menuForm.action.value = "insert";
			menuForm.submit();
		}
		return true;
	}
	function fDelete() {
		if (document.forms[0].menuId.value == "") {
			alert("必须录入菜单ID!");
			return false;
		}
		if (!confirm("真的要删除？")) {
			return false;
		}
		document.forms[0].action.value = "delete";
		document.forms[0].submit();
		return true;
	}
	function fQuery() {
		with (document.forms[0]) {
			if (menuId.value == "") {
				alert("必须录入菜单ID!");
				return false;
			}
			action.value = "query";
			//	lastMenuId.value = menuId.value;
			submit();
		}
		return true;
	}
	function fUpdate() {
		with (document.forms[0]) {
			if (menuId.value == "") {
				alert("必须录入菜单ID!");
				return false;
			}
			if (menuAtt[1].checked==true && urlAddr.value == "") {
				alert("菜单处理文件未录入!");
				return false;
			}
			action.value = "update";
			submit();
		}
		return true;
	}
	function setValue(tr, menuId, menuName, menuAtt, urlAddr,menStat) {

		document.forms[0].menuId.value = menuId;
		document.forms[0].menuName.value = menuName;
		if (menuAtt == '0')
			document.forms[0].menuAtt[0].checked = true;
		else
			document.forms[0].menuAtt[1].checked = true;
		document.forms[0].urlAddr.value = urlAddr;

		document.forms[0].menStat.value=menStat;

		setBgcolor(tr);
	}
	function clearAll(){
		with(document.forms[0]){
			menuId.value="";
			menuName.value="";

			menuAtt[0].checked=false;
			menuAtt[1].checked=false;
			
			//menuAtt.value="";
			urlAddr.value="";
			menStat.value="00";
		}
		return true;
	}
</script>
		<TITLE>系统菜单管理</TITLE>
	</HEAD>
	<BODY>
		<center>
			<h1>
				系统菜单管理
			</h1>

			<html:form action="/sysadm/menuManage">
				<html:hidden property="action" />
				<html:errors />
<br><br>
				<table border="1" width="90%">
					<tr>
						<td class="top" colspan="5"></td>
					</tr>
					<tr>
						<td class="tab" colspan="5">
							<table class="tabs" cellpadding="0" cellspacing="0">
								<tr>
									<td width="9" class="tab_sf"></td>
									<td width="85" class="tab_f">
										菜单定义条件
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>

					</tr>

					<tr>
						<th width="150">
							菜单ID
						</th>
						<th width="200">
							显示名称
						</th>
						<th width="250">
							路径菜单
						</th>
						<th width="150">
							处理文件URL
						</th>
						<th width="150">
							是否有效
						</th>
					<tr>
						<td>
							<html:text property="menuId" size="10" maxlength="10" style="iform"
								onkeypress="javascript:if(event.keyCode==13)fQuery();return true;" />
							<font color="red">*</font>
						</td>
						<td>
							<html:text property="menuName" size="25" maxlength="30"
								style="iform" />
						</td>
						<td align="center">
							<html:radio property="menuAtt" value="0" />
							是
							<html:radio property="menuAtt" value="1" />
							否
						</td>
						<td>
							<html:text property="urlAddr" size="50" maxlength="80"
								style="iform" />
						</td>
						<td>
							<html:select property="menStat"  size="1">
								<html:options collection="statList" property="code" labelProperty="name" />
							</html:select>
						</td>
					</tr>
				</table>
				<br>
				<br>
				<table>
					<tr>
						<td>
							<html:button property="0" styleClass="button" value="增加"
								onclick="return fInsert();" />
							&nbsp;&nbsp;&nbsp;
						<td>
							<html:button property="1" styleClass="button" value="删除"
								onclick="return fDelete();" />
							&nbsp;&nbsp;&nbsp;
						<td>
							<html:button property="2" styleClass="button" value="修改"
								onclick="return fUpdate();" />
							&nbsp;&nbsp;&nbsp;
						<td>
							<html:button property="3" styleClass="button" value="查询"
								onclick="return fQuery();" />
							&nbsp;&nbsp;&nbsp;
							<input type="button" class="button" value="清空"
									onclick="clearAll()">
				</table>
				<c:if test="${not empty footer}">
					<table width="90%" border="0">
						<tr>
							<%=request.getAttribute("footer")%>
						</tr>
					</table>
				</c:if>
			</html:form>
			<br>

			<table border="1" width="90%">
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
					<th bgcolor="#EFEFEF">
						菜单ID
					</th>
					<th bgcolor="#EFEFEF">
						菜单名称
					</th>
					<th bgcolor="#EFEFEF">
						URL地址
					</th>
					<th>菜单性质</th>
					<th>是否有效</th>
					<c:if test="${not empty menuList}">
						<logic:present name="menuList">
							<logic:equal name="list" value="true" scope="request">

								<logic:iterate id="viewBean" name="menuList" scope="request">
									<TR onclick="setValue(this
										,'<bean:write name="viewBean" property="data1" filter="true" />'
										,'<bean:write name="viewBean" property="data2" filter="true" />'
										,'<bean:write name="viewBean" property="data3" filter="true" />'
										,'<bean:write name="viewBean" property="data4" filter="true" />'
										,'<bean:write name="viewBean" property="data6" filter="true" />')">


										<TD>
											<bean:write name="viewBean" property="data1" filter="true" />
										</TD>
										<TD>
											<bean:write name="viewBean" property="data2" filter="true" />
										</TD>
										<TD>
											<bean:write name="viewBean" property="data4" filter="true" />
										</TD>
										<td>
											<bean:write name="viewBean" property="data5" filter="true"/>
										</td>
										<td>
											<bean:write name="viewBean" property="data7" filter="true"/>
										</td>
									</TR>
								</logic:iterate>

							</logic:equal>
						</logic:present>
					</c:if>
			</table>

		</center>
	</body>
</html>
</c:if>