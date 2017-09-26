<%@ page language="java" contentType="text/html; charset=UTF-8"%>

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
<!-- lrByDrugInit.jsp -->
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META http-equiv="Content-Style-Type" content="text/css">
		<LINK href="<%=request.getContextPath()%>/theme/public.css"
			rel="stylesheet" type="text/css">
		<script language="javascript"
			src="${pageContext.request.contextPath }/js/date/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<title>机构利润构成</title>
<script type="text/javascript">
function query(){
		with(document.forms[0]){
			if(b_id.value==""){
				alert("机构必须选择！");
				b_id.focus();
				return false;
			}
			submit();
		}
		return true;
	}
</script>
</head>
<body>
<h1>
			机构利润构成(按时间段查询)
		</h1>
		<br>
		<br>
		<br>
		<br>
		<hr>
		<html:errors />
		<center>
			<html:form action="/chart/bybranch/lrbydrug">
		数据起始日期：<html:text property="fromDate" size="8" maxlength="4"
					style="iform" onfocus="WdatePicker();" readonly="true" />
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		数据终止日期：<html:text property="endDate" size="8" maxlength="4"
					style="iform" onfocus="WdatePicker();" readonly="true"></html:text>
<br><br><br>
机构名称：<html:select property="b_id">
		<html:options collection="branchList" property="code" labelProperty="name"/>
		</html:select>
				&emsp;&emsp;
				<br><br><br><br>
				<html:button property="1" styleClass="button" value="查询"
					onclick="return query();" />
				<hr>

			</html:form>
		</center>
</body>
</html>