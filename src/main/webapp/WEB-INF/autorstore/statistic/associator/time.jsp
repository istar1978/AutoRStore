<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- time.jsp 会员积分统计-->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function query(){
		with(document.forms[0]){
			submit();
		}
		return true;
	}
</script>
	</head>
	<body>
		<h1>
			会员积分统计(按时间段查询)
		</h1>
		<br>
		<br>
		<br>
		<br>
		<hr>
		<html:errors />
		<center>
			<html:form action="/chart/time/associatorPrint">
		数据起始日期：<html:text property="fromDate" size="8" maxlength="4"
					style="iform" onfocus="WdatePicker();" readonly="true" />
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		数据终止日期：<html:text property="endDate" size="8" maxlength="4"
					style="iform" onfocus="WdatePicker();" readonly="true"></html:text>
<br><br><br>
机构：<html:select property="b_id">
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