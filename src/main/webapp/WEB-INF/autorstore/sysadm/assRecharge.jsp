<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- assRecharge.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function recharge(){
				with (document.forms[0]) {
					if(a_balance.value==""){
						alert("充值金额不能为空！");
						a_balance.focus();
						return false;
					}else{
						if(!validMoney(a_balance.value)){
							return false;	
						}
						submit();
						return true;
				}
			}
			}
			$j(function(){
				with(document.forms[0]){
					a_balance.value="";
					a_pbalance.value="";
				}
			});
		</script>
  </head>
  
  <body>
    <center>
			<H1 align="center">
				会员充值
			</H1>
				<table width="90%" border="1" class="bd">
					<tr>
						<td class="top" colspan="6"></td>
					</tr>
					<tr>
						<td class="tab" colspan="6">
							<table class="tabs" cellpadding="0" cellspacing="0">
								<tr>
									<td width="9" class="tab_sf"></td>
									<td width="85" class="tab_f">
										会员信息
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>

				<tr>
					<th width="60" >会员卡号</th>
					<td width="100" align="center">${ ass.data1}</td>

					<th width="60">手机号码</th>
					<td width="100" align="center">${ass.data7 }</td>
				</tr>

				<tr>
					<th width="60">会员姓名</th>
					<td width="100" align="center">${ass.data2 }</td>
					<th width="60">车牌号码</th>
					<td width="100" align="center">${ass.data19 }</td>
				</tr>

				<tr>
					<th width="60" >积分</th>
					<td width="100" align="center">${ass.data8 }</td>
					<th width="60" >激活日期</th>
					<td width="100" align="center">${ass.data16 }</td>
				</tr>

				<tr>
					<th width="60">余额</th>
					<td width="100" align="center">${ass.data20 }</td>
					<th width="60">赠送余额</th>
					<td width="100" align="center">${ass.data21 }</td>
				</tr>
</table>
</center>

			<h1></h1>
			<center>
				<html:errors />
						<br>
						<br>
				<html:form action="/sysadm/assRecharge"  onsubmit="return recharge();">	
					<html:hidden property="a_id" value="${ass.data1}"/>
				<table  width="90%" border="1" class="bd">
					<tr>
						<td class="top" colspan="4"></td>
					</tr>
					<tr>
						<td class="tab" colspan="4">
							<table class="tabs" cellpadding="0" cellspacing="0">
								<tr>
									<td width="9" class="tab_sf"></td>
									<td width="85" class="tab_f">
										充值
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<th width="60">充值金额</th>
						<td width="100" align="center">
							<html:text property="a_balance" ></html:text>
						</td>
						<th width="60">赠送金额</th>
						<td width="100" align="center">
							<html:text property="a_pbalance"></html:text>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<html:submit value="确定" styleClass="button"></html:submit>
						</td>
					</tr>
				</table>
			</html:form>
			</center>
  </body>
</html>
