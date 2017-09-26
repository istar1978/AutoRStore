<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- chartprint.jsp -->
<HTML>
	<HEAD>
	<%@include file="/autorstore/public/head.jspf"%>
	</HEAD>
	<BODY>
<br><br>
		<center>
<!-- 
			<img src="<%=request.getAttribute("chart")%>"/>
 -->
			<img src="${chart }">
		</center>


	</BODY>
</HTML>
