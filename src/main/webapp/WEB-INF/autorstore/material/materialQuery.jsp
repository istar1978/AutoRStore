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
<!-- queryInitQuery.jsp -->
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
		<title>维修材料查询</title>
		<script type="text/javascript">
	function validate() {
		with (document.forms[0]) {
			
		}
		return true;
	}

	function clearAll(){
		with(document.forms[0]){
			dr_id.value="";
			dr_name.value="";
			dr_category.value="";
			branch_id.value="";
			dr_stat.value="";
		//	dr_val.value="";
		//	pre_price.value="";
		//real_price.value="";
			dr_factory.value="";
		//	pro_date.value="";
		//	in_date.value="";
		//	dr_num.value="";
		}
		return true;
	}
	
</script>
	</head>
	<body>
		<center>

			<H1 align="center">
				维修材料查询
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/material/query" onsubmit="return validate();">
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
										查询条件
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>
							维修材料编号
						</th>
						<td>
							<html:text property="dr_id" size="12" maxlength="10"></html:text>
						</td>
						<th>机构编号</th>
						<td>
							<html:select property="branch_id">
								<html:options collection="branchList" property="code" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							状态
						</th>
						<td>
							<html:select property="dr_stat">
								<html:options collection="statList" property="code" labelProperty="name"/>
							</html:select>
						</td>
						
						<th>
							分类
						</th>
						<td>
<html:select property="dr_category">
	<html:options collection="dCategoryList" property="code" labelProperty="name"/>
</html:select>
						</td>
						
					</tr>
					
					<tr>
						<th>
							生产厂家
						</th>
						<td>
							<html:text property="dr_factory" size="40" maxlength="100"></html:text>
						</td>
						<th>
							维修材料名称
						</th>
						<td>
							<html:text property="dr_name" size="40" maxlength="100"></html:text>
						</td>
					</tr>
					

				</table>

				<br>
				<br>
				<br>
				<br>
				<br>
				<html:submit value="查询" styleClass="button"></html:submit>
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空" onclick="clearAll()">
			</html:form>
		</center>

	</body>
</html>