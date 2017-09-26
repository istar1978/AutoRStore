<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/autorstore/public/headtop.jspf"%>

<!-- error.jsp -->
<c:if test="${not empty userContext}">
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
	</HEAD>
	<BODY>
		<center>
			<table width="760">
				<tr>
					<td align="center">
						<H3 align="center">
							运行错误！
						</H3>
						<HR>
						
				</td>
				</tr>						
			</table>
			<html:errors/>
		</center>
	</BODY>
</HTML>
</c:if>
<c:if test="${empty userContext}">
	<center><font color="red" size="8">您无权访问此页面！！</font></center>
</c:if>