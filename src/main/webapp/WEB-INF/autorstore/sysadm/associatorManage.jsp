<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/autorstore/public/headtop.jspf"%>
<!-- associatorManage.jsp -->
<html>
	<head>
		<%@include file="/autorstore/public/head.jspf"%>
<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function validate() {
		with (document.forms[0]) {
			if (a_id.value == "") {
				alert("会员卡号必须填写！");
				a_id.focus();
				return false;
			}
			if (a_tel.value == "") {
				alert("手机号码 必须填写！");
				a_tel.focus();
				return false;
			}
			//判断手机号码是否合规
			if(!chmobile(a_tel,11)){
				return false;
			}
			if (a_level.value == "") {
				alert("等级必须选择！");
				a_level.focus();
				return false;
			}

		}
		return true;
	}
	function setAction(a) {
		with (document.forms[0]) {
			if (a == "insert") {
				if (!validate()) {
					return false;
				}
			}
			if (a == "delete") {
				if (a_id.value == "") {
					alert("会员卡号必须填写！");
					a_id.focus();
					return false;
				}
				if (!confirm("真的要删除？")) {
					return false;
				}
			}
			if(a=="update"){
				if(!validate()){
					return false;
				}
				if(!confirm("确认修改？")){
						return false;
				}
			}
			action.value = a;
			submit();
		}
		return true;
	}

	function clearAll() {
		with (document.forms[0]) {
			a_id.value = "";
			//a_password.value = "";
			a_name.value = "";
			a_gender.value = "";
			a_birthday.value = "";
			a_addr.value = "";
			a_tel.value = "";
			a_level.value = "";
			//a_stat.value = "";
			a_carno.value = "";
		}
	}

	function setValue(bg, data1, data2, data3, data4, data5, data6, data7,
			data8, data9) {
		with (document.forms[0]) {
			//alert(a_no);
			a_id.value = data1;
			a_name.value = data2;
			a_gender.value = data3;
			a_birthday.value = data4;
			a_addr.value = data5;
			a_tel.value = data6;
			a_level.value = data7;
			//a_stat.value = data8;
			a_carno.value = data9;

			setBgcolor(bg);
		}
	}
</script>
	</head>
	<body onload="clearAll();">
		<center>
			<H1 align="center">
				会员管理维护
			</H1>
			<html:errors />
			<html:form action="/sysadm/associatorManage">
				<html:hidden property="action" />
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
						<th>
							会员卡号
						</th>
						<td>
							<html:text property="a_id" size="12" maxlength="10" style="iform"></html:text>
							<font color="red">*</font>
						</td>

						<th>
							手机号码
						</th>
						<td>
							<html:text property="a_tel" size="12" maxlength="11"
								style="iform"></html:text>
							<font color="red">*</font>
						</td>
						<th>
							会员级别
						</th>
						<td >
							<html:select property="a_level">
								<html:options collection="levelList" property="code"
									labelProperty="name" />
							</html:select>
							<font color="red">*</font>
						</td>

					</tr>
					<tr>
						<th>
							性别
						</th>
						<td>
							<html:select property="a_gender">
								<html:options collection="sexList" property="code"
									labelProperty="name" />
							</html:select>
						</td>
						<th>
							出生日期
						</th>
						<td>
							<html:text property="a_birthday" size="8" maxlength="4"
								style="iform" onfocus="WdatePicker();" readonly="true" />
						</td>
						<th>
							车牌号码
						</th>
						<td>
							<html:text property="a_carno" size="10" maxlength="10"
								style="iform"></html:text>
						</td>
					</tr>

					<tr>
						<th>
							会员姓名
						</th>
						<td>
							<html:text property="a_name" size="30" maxlength="30"
								style="iform"></html:text>
						</td>
						<th>
							住址
						</th>
						<td>
							<html:text property="a_addr" size="40" maxlength="100"
								style="iform"></html:text>
						</td>

					</tr>
				</table>
				<br>
				<input type="button" class="button" name="add" value="新增"
					onclick="setAction('insert')">
&nbsp;&nbsp;&nbsp;

				<input type="button" class="button" name="update" value="修改"
					onclick="setAction('update')">
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" name="del" value="删除"
					onclick="setAction('delete')">
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" name="query" value="查询"
					onclick="setAction('query')">
&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" value="清空" onclick="clearAll()">

				<BR>

				<table width="90%" border="0">

					<tr>
						<c:if test="${not empty footer}">
							<%=request.getAttribute("footer")%>
						</c:if>
					</tr>
				</table>

			</html:form>

			<table border="1" width="90%" cellpadding="0" cellspacing="1">
				<tr>
					<td class="top" colspan="9"></td>
				</tr>
				<tr>
					<td class="tab" colspan="9">
						<table class="tabs" cellpadding="0" cellspacing="0">
							<tr>
								<td width="9" class="tab_sf"></td>
								<td width="85" class="tab_f">
									显示列表
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
					<th>
						手机号码
					</th>
					<th>
						会员级别
					</th>
<!-- 
					<th>
						激活日期
					</th>
 -->
					<th>
						创建日期
					</th>
					<th>
						积分
					</th>
					<th>
						余额
					</th>
					<th>
						赠送余额
					</th>
					<th>
						状态
					</th>
				</tr>

				<c:if test="${not empty dataList}">
					<logic:iterate id="viewBean" name="dataList" scope="request">
						<tr
							onclick="setValue(this,'<bean:write name="viewBean" property="data1" filter="true" />',
												'<bean:write name="viewBean" property="data2" filter="true" />',
												'<bean:write name="viewBean" property="data4" filter="true"/>',
												'<bean:write name="viewBean" property="data5" filter="true"/>',
												'<bean:write name="viewBean" property="data6" filter="true"/>',
												'<bean:write name="viewBean" property="data7" filter="true"/>',
												'<bean:write name="viewBean" property="data9" filter="true"/>',
												'<bean:write name="viewBean" property="data12" filter="true" />',
												'<bean:write name="viewBean" property="data19" filter="true" />')">
							<td>
								<bean:write name="viewBean" property="data1" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data7" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data15" filter="true" />
							</td>
<!-- 激活日期不再显示20170919 modified by zhaozhy
							<td>
								<bean:write name="viewBean" property="data16" filter="true" />
							</td>
 -->
							<td>
								<bean:write name="viewBean" property="data17" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data8" filter="true" />
							</td>

							<td>
								<bean:write name="viewBean" property="data20" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data21" filter="true" />
							</td>
							<td>
								<bean:write name="viewBean" property="data18" filter="true" />
							</td>
						</tr>
					</logic:iterate>
				</c:if>
			</table>

		</center>

	</body>
</html>