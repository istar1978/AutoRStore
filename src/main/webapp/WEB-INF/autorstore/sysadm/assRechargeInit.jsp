<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- assRechargeInit.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		function reset() {
			with (document.forms[0]) {
				a_id.value="";
			}
		}

		function validate(){
			with(document.forms[0]){
				if(a_id.value==""){
					alert("会员卡号必须输入！");
					a_id.focus();
					return false;
				}
				if(a_id.value.length!=10){
					alert("请输入10位会员卡号！");
					a_id.focus();
					return false;
				}
				submit();
			}
			return true;
		}
		</script>
  </head>
  
  <body onload="reset();">
   		<center>
			<table width="760">
				<tr>
					<td>
						<H1 align="center">
							会员充值
						</H1>
						<html:errors />
						<html:form action="/sysadm/assRechargeInit">
							<TABLE align="center" border="1" width="480" cellpadding="0"
								cellspacing="0" class="bd">
								<TBODY>
									<tr>
										<td class="top" colspan="4"></td>
									</tr>
									<tr>
										<td class="tab" colspan="4">
											<table class="tabs" cellpadding="0" cellspacing="0">
												<tr>
													<td width="9" class="tab_sf"></td>
													<td width="400" class="tab_f">
														请输入10位会员卡号
													</td>
													<td width="9" class="tab_ef"></td>

												</tr>
											</table>
										</td>

									</tr>

									<TR>
										<TH width="180">
											会员卡号
										</TH>
										<TD width="300">
											<html:text property="a_id" size="12" maxlength="10" style="iform"></html:text>
											<FONT color="red">*</FONT>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</html:form>
					</td>
				</tr>
			</table>

		</center>
		<P align="center">
			<INPUT type="button" class="button" name="ok" value="确定"
				onclick="validate()">
			<INPUT type="button" name="reset" class="button" value="重置"
				onclick="reset()">
		</P>
  </body>
</html>
