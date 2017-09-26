<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include  file="autorstore/public/headtop.jspf" %>
<html>
	<head>
	
		<TITLE>主界面topFrame</TITLE>
		<%@include file="autorstore/public/head.jspf" %>
	<LINK REL="stylesheet" TYPE="text/css" HREF="<%=request.getContextPath() %>/theme/top.css"
			TITLE="default">
		<SCRIPT language=Javascript>
	
function Init()
{
	startclock();			
}


</SCRIPT>

	</head>
	<BODY id=Bdy text=#ffffff bgColor=#38497f onload=Init(); >

		<TABLE width="100%" border=0 align="center" cellPadding=0
			cellSpacing=0>
			<TBODY>
				<TR>
					<TD align="center" vAlign=top nowrap>
						<IMG src="<%=request.getContextPath() %>/images/tree/logo1.jpg" width="114" height="23"
							align=top>
						&nbsp;
					</TD>

					<TD align=left vAlign=middle nowrap>
						<IMG src="<%=request.getContextPath() %>/images/tree/dot.jpg">
					</TD>

					<TD align=left vAlign=middle nowrap>
						&nbsp;所属机构&nbsp;
						【${userContext.stafferBranchName},${userContext.stafferBranchId }】
					</TD>
					<TD align=left vAlign=middle nowrap>
						<IMG src="<%=request.getContextPath() %>/images/tree/dot.jpg">
					</TD>
					<TD align=left vAlign=middle nowrap>
						&nbsp;所属部门【${userContext.stafferDepName }】
					</TD>
					<TD align=left vAlign=middle nowrap>
						<IMG src="<%=request.getContextPath() %>/images/tree/dot.jpg">
					</TD>
					<TD align=left vAlign=middle nowrap>
						&nbsp;姓名【${sessionScope.userContext.stafferName}】
					</TD>
					<TD align=left vAlign=middle nowrap>
						<IMG src="<%=request.getContextPath() %>/images/tree/dot.jpg">
					</TD>
					<TD align=left vAlign=middle nowrap>
						&nbsp;编号 &nbsp;【${sessionScope.userContext.stafferId }】
					</TD>
					<TD align=left vAlign=middle nowrap>
						<IMG src="<%=request.getContextPath() %>/images/tree/dot.jpg">
					</TD>
					<TD align=left vAlign=middle nowrap id=clock></TD>

				</TR>
			</TBODY>
		</TABLE>
	</BODY>
</HTML>