<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf" %>
<html>
	<head>
<%@include file="/autorstore/public/head.jspf" %>
		<link rel="stylesheet" type="text/css" href="${store }/theme/top.css" title="default" />
		<title>主界面topFrame</title>

		<script language=Javascript>
			function Init() {
				startclock();			
			}
		</script>

	</head>
	<BODY id="Bdy" bgcolor="#38497f" onload="Init();" >

		<table width="100%" border=0 align="center" cellPadding="0" cellSpacing="0">
				<tr>
					<td align="center" valign="top" >
						<img src="${store}/images/tree/logo1.jpg" width="114" height="23" align="top" />
						&nbsp;
					</td>

					<td align="left" valign="middle" >
						<img src="${store }/images/tree/dot.jpg" />
					</td>

					<td align="left" valign="middle" >
						&nbsp;所属机构&nbsp;
						【${userContext.stafferBranchName},${userContext.stafferBranchId }】
					</td>
					<td align="left" valign="middle" >
						<img src="${store }/images/tree/dot.jpg" />
					</td>
					<td align="left" valign="middle" >
						&nbsp;所属部门【${userContext.stafferDepName }】
					</td>
					<td align="left" valign="middle" >
						<img src="${store }/images/tree/dot.jpg" />
					</td>
					<td align="left" valign="middle" >
						&nbsp;姓名【${sessionScope.userContext.stafferName}】
					</td>
					<td align="left" valign="middle" nowrap>
						<img src="${store }/images/tree/dot.jpg" />
					</td>
					<td align="left" valign="middle" >
						&nbsp;编号 &nbsp;【${sessionScope.userContext.stafferId }】
					</td>
					<td align="left" valign="middle" >
						<img src="${store }/images/tree/dot.jpg" />
					</td>
					<td align="left" valign="middle"  id="clock"></td>

				</tr>
		</table>
	</BODY>
</html>