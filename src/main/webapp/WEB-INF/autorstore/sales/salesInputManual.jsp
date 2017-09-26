<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- salsesInputManual.jsp -->
<html>
	<head>
	<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function clearAll() {
		$("manualForm").amount.value="";
		return true;
	}

	function validate(){
		var manualForm=$("manualForm");
		if(!validateIfBlank(manualForm["amount"],"消费金额必须输入！")){
			return false;
		}else{
			if(!validMoney(manualForm["amount"])){
				return false;	
			}
		}
		//提交之前，判断消费金额是否大于 会员卡余额
		var sum=Number("${ass.assBalance}")+Number("${ass.assPbalance}");
		if(sum<Number(manualForm["amount"].value)){
			if (!confirm("消费金额大于会员卡余额，是否继续？")) {
				return false;
			}
		}
		manualForm.submit();	
	}
</script>
	</head>
	<body onload="clearAll()">
		<center>
			<H1 align="center">
				请输入消费金额
			</H1>
			<html:errors />
			<br>
			<br>
			<form action="${store }/sale/cartManualAdd.do" method="post" id="manualForm" name="manualForm">
				<input type="hidden" id="ass_id" name="ass_id" value="${ass.assId }" />
				<table border="1" width="50%">
				<tr>
					<th>会员卡号</th>
					<td>${ass.assId}</td>
				</tr>
					<tr>
						<th>余额</th>
						<td>
							 ${ass.assBalance }
						</td>
					</tr>
					<tr>
						<th>赠送余额</th>
						<td>${ass.assPbalance }</td>
					</tr>
				</table>
				<br>
				<br>
				<table border="1" width="50%">
					<tr>
						<th>消费金额</th>
						<td>
							<input id="amount" name="amount"  size="20" maxlength="40"/>
						</td>
					</tr>
				</table>
				<br />
				<br />
				<center>
					<input type="button" class="button" value="提交" onclick="validate()" />
				</center>
			</form>
		</center>

	</body>
</html>