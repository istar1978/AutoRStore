<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- assComboInput.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">

		function validation(){
			with (document.forms[0]) {
				if(rep_id.value==""){
					alert("维修项目编号 必须输入!");
					rep_id.focus();
					return false;
				}
				if(com_item.value==""){
					alert("类别 必须输入!");
					com_item.focus();
					return false;
				}
				if(com_sdate.value==""){
					alert("起始日期 必须输入!");
					com_sdate.focus();
					return false;
				}
				if(com_edate.value==""){
					alert("终止日期 必须输入!");
					com_edate.focus();
					return false;
				}
				if(com_time.value==""){
					alert("优惠套餐次数不能为空!");
					com_time.focus();
					return false;
				}
				if(com_price.value==""){
					alert("套餐金额不能为空!");
					com_price.focus();
					return false;
				}
			}
			return true;
		}
		
		function clearAll(){
			with (document.forms[0]) {
				rep_id.value="";
				com_sdate.value="";
				com_edate.value="";
				com_time.value="";
				com_desc.value="";
				com_price.value="";
				com_item.value="";
			}
		}
		$j(function(){
			clearAll();
		});
</script>
	</head>
	<body>
		<center>

			<H1 align="center">
				会员购买套餐
			</H1>
			<html:errors />
			<br>
			<br>
			<html:form action="/associator/assComboAdd" onsubmit="return validation();">
				<table cellpadding="2" cellspacing="2" class="bd" width="90%"
					border="1">
					<tr>
						<td class="top" colspan="4"></td>
					</tr>
					<tr>
						<td class="tab" colspan="4">
							<table class="tabs" cellpadding="0" cellspacing="0">
								<tr>
									<td width="9" class="tab_sf"></td>
									<td width="85" class="tab_f">
										套餐明细
									</td>
									<td width="9" class="tab_ef"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>
							会员卡号
						</th>
							<td width="100" align="left">${ ass.assId}
								<html:hidden property="ass_id" value="${ass.assId}"/>
							</td>
						<th>
							会员手机号
						</th>
						<td width="100" align="left">
							${ ass.assPhone}
						</td>
						
					</tr>
<!-- 
					<tr>
						<th>
							优惠套餐编号
						</th>
						<td width="100" align="left">
							<html:text property="com_id" size="30" maxlength="10"></html:text>
							<font color="red">*</font>
						</td>
						<th>
							优惠套餐名称
						</th>
						<td width="100" align="left">
							<html:text property="com_name" size="30" maxlength="100"></html:text>
							<font color="red">*</font>
						</td>
					</tr>
 -->
					<tr>
						<th>
							所属项目
						</th>
						<td width="100" align="left">
							<html:select property="rep_id">
								<html:options collection="cRepIdList" property="code" labelProperty="name"/>
							</html:select>
							<font color="red">*</font>
						</td>
							<th>套餐类别</th>
						<td width="100" align="left">
						<html:select property="com_item">
							<html:options collection="cClassifyList" property="code" labelProperty="name"/>
						</html:select>
						<font color="red">*</font>
						</td>
					</tr>
					
					
					<tr>
						<th>开始日期</th>
						<td width="100" align="left">
							<html:text property="com_sdate" size="8" maxlength="4"
								style="iform" onfocus="WdatePicker();" readonly="true" />
							<font color="red">*</font>
						</td>
						<th>
							结束日期
						</th>
						<td width="100" align="left">
							<html:text property="com_edate" size="8" maxlength="4"
								style="iform" onfocus="WdatePicker();" readonly="true" />
							<font color="red">*</font>
						</td>
						
					</tr>
					<tr>
						<th width="100" align="left">
							优惠次数
						</th>
						<td width="100" align="left">
							<html:text property="com_time" size="30" maxlength="10"></html:text>
							<font color="red">*</font>
						</td>
						
							<th width="100" align="left">金额</th>
						<td width="100" align="left">
							<html:text property="com_price" size="30" maxlength="10"></html:text>
							<font color="red">*</font>
						</td>
						
					</tr>
					<tr>
						<th width="100" align="left">套餐说明</th>
						<td width="100" align="left">
						<html:text property="com_desc" size="40" maxlength="20"></html:text>
						</td>
					</tr>
				</table>

				<br>
				<br>
				<br>
				<br>
				<br>
				<html:submit value="提交" styleClass="button" ></html:submit>
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空" onclick="clearAll()">

			</html:form>
			</center>

	</body>
</html>