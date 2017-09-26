<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- associatorInput.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function jihuo(){
		with(document.forms[0]){
			if(a_id.value==""){
				alert("会员卡号必须输入！");
				a_id.focus();
				return false;
			}
			if(a_id.value.length!=10){
				alert("会员卡号不存在！");
				
				return false;
			}
			var urlstr="${store}/associator/doAjaxValidateJihuo.do";
			var dataReq={assId:a_id.value};
			$j.ajax({
				type:"post",
				url:urlstr,
				data:dataReq,
				dataType:"text",
				success:function (dataRes){
					if(dataRes.length>0){
						alert(dataRes);
						return false;
					}else{
						submit();
					}
				}
			});
			
			//submit();
		}
		return true;
	}
</script>
	</head>
	<body>
		<center>

			<table width="760">
				<tr>
					<td>
						<H1 align="center">
							会员卡激活
						</H1>
						<br>
						<br>
						<html:errors />
						<br>
						<br>
						<html:form action="/sysadm/associatorJihuo">
							<TABLE align="center" border="1" width="480" cellpadding="0"
								cellspacing="0" class="bd">
								<TBODY>
									<tr>
										<td class="top" colspan="2"></td>
									</tr>
									<tr>
										<td class="tab" colspan="2">
											<table class="tabs" cellpadding="0" cellspacing="0">
												<tr>
													<td width="9" class="tab_sf"></td>
													<td width="400" class="tab_f">
														注意：仅限管理员操作！
													</td>
													<td width="9" class="tab_ef"></td>
												</tr>

											</table>
										</td>

									</tr>

									<TR>
										<TH width="180">
											会员卡号
											<FONT color="red">*</FONT>
										</TH>
										<TD width="300" align="center">
											<html:text property="a_id" size="12" maxlength="10"></html:text>
										</TD>
									</TR>
									<tr>
										<td align="center" colspan="2">
											<html:button property="b1" styleClass="button"
												onclick="jihuo();">激活</html:button>
										</td>
									</tr>

								</TBODY>
							</TABLE>
						</html:form>
					</td>
				</tr>
			</table>
		</center>

	</body>
</html>