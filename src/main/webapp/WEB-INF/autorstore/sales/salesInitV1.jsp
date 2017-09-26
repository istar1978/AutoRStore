<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- salesInitV1.jsp -->
<html>
	<head>
 		<%@include file="/autorstore/public/head.jspf"%>
		<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function clearAll() {
		with (document.forms[0]) {
			ass_id.value = "";
			yorn.value="";
		}
		return true;
	}
	function validate(){
		with(document.forms[0]){
			if(yorn.value==""){
				alert("请确实是否手工录入消费金额！");
				yorn.focus();
				return false;
			}
			//手工录入消费金额时，会员号必须输入
			if(yorn.value=="0"&&ass_id.value==""){
				alert("手工录入消费金额时，会员卡号必须输入！");
				ass_id.focus();
				return false;
			}
			if(ass_id.value.length>0&&ass_id.value.length!=10){
				alert("请确认会员号是否输入正确！");
				return false;
			}
			if(yorn.value=="0"&&ass_id.value.length>0){
				settleAccounts(ass_id.value);	
			}
			if(yorn.value=="1"){
				$("initForm").submit();
			}
			
		return true;
			
		}
	}
	function settleAccounts(ass_id){
		var urlstr="${store}/sale/doAjaxValidateAssId.do";
		var dataReq={assId:ass_id};
		$j.ajax({
			type:"post",
			url:urlstr,
			data:dataReq,
			dataType:"text",
			success:function (dataRes){
				if(dataRes.length>0){
					alert(dataRes);
					$("ass_id").focus();
					return false;
				}else{
					$("initForm").submit();
					return true;
				}
			}
		});
	}
</script>
	</head>
	<body onload="clearAll();">
<br /><br />
		<center>
			<H1 align="center">
				请输入会员卡号(手工录入消费金额时，会员卡号必须输入，否则可略过,直接点确定)
			</H1>
			<html:errors />
			<br>
			<br>
			<form action="${store }/sale/salesInputInitV1.do" id="initForm" name="initForm" method="post">
				<br>
				<br>
				<br>
				<br>
				<table cellpadding="2" cellspacing="2" class="bd" width="90%"
					border="1" >
					<tr>
						<th align="right" >会员卡号:</th>
						<td >
							<input size="20" maxlength="10" id="ass_id" name="ass_id" />
						</td>
					</tr>

					<tr>
						<th>是否手工录入消费金额:</th>
						<td >
							<select style="width: 150px" name="yorn">
									<ec:options items="yornMap" />
							</select>
							<font color="red">*</font>
					</td>
				</table>
				<br>
				<br>
				<br>
				<center>
					<input type="button" class="button" value="确定" onclick="validate()" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="button" value="清空" onclick="clearAll()">
				</center>
			</form>
		</center>


	</body>
</html>