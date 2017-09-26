<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.zhaozhy.autorstore.sysadmin.DictionaryView"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- deprightInput.jsp -->
<c:if test="${not empty userContext}">
	<HTML>
		<HEAD>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
			<script language="javascript">
function update(){
	with(document.forms[0]){
		if(busi.value==""){
			alert("您未选择部门!");
			return false;
		}
		action.value='update';
		submit();
	}
	return true;
}
function query(){
	with(document.forms[0]){
		if(busi.value==""){
			alert("您未选择部门!");
			return false;
		}
		action.value='query';
		submit();
	}
	return true;
}

</script>

		</head>
		<body>
			<center>
				<h1>
					部门权限定义
				</h1>
				<html:errors />
<br><br>
				<html:form action="/sysadm/deprightManage">
					<html:hidden property="action" />
					<html:button property="0" styleClass="button" value="修改"
						onclick="return update();" />
					&nbsp;&nbsp;&nbsp;
<html:reset value="取消" styleClass="button"></html:reset>
&nbsp;&nbsp;&nbsp;

				<html:select property="busi" styleClass="iform">
						<html:options collection="departmentList" property="code"
							labelProperty="name" />
					</html:select>

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<html:button property="1" styleClass="button" value="查询"
						onclick="return query();" />

					<table border="1" width="90%">
						<tr>
							<td class="top" colspan="4"></td>
						</tr>
						<tr>
							<td class="tab" colspan="4">
								<table class="tabs" cellpadding="0" cellspacing="0">
									<tr>
										<td width="9" class="tab_sf"></td>
										<td width="85" class="tab_f">
											显示结果
										</td>
										<td width="9" class="tab_ef"></td>
									</tr>
								</table>
							</td>

						</tr>

						<tr>
							<th bgcolor="#EFEFEF" width="400">
								业务菜单名称
							</th>
							<th bgcolor="#EFEFEF" width="200">
								权限
							</th>
							<c:if test="${not empty menuList}">
								<logic:equal name="list" value="true" scope="request">
									<logic:iterate id="view" name="menuList" scope="request">
										<TR>
											<TD>
												<bean:write name="view" property="name" filter="true" />
											</TD>
											<TD align="center">
												<html:multibox property="code"
													value="<%=((DictionaryView) view)
															.getCode()%>" />
											</TD>
										</TR>
									</logic:iterate>
								</logic:equal>
							</c:if>
					</table>
				</html:form>
			</center>
		</body>
	</html>
</c:if>
<c:if test="${empty userContext}">
	<center><font color="red" size="9">您无权访问此页面！！</font></center>
</c:if>