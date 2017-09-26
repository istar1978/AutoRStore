<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf" %>
<!-- materialList.jsp -->
<html>
	<head>
	<head>
<%@include file="/autorstore/public/head.jspf" %>
		<link href="${store }/theme/public.css"rel="stylesheet" type="text/css" />

		<title>维修材料清单</title>
		<script type="text/javascript">
function setColor(obj){
	setBgcolor(obj);
}
</script>
	</head>
	<body>
		<center>
			<h1>
				维修材料清单列表
			</h1>
			<hr>
			<html:form action="/material/query">
<html:hidden property="dr_id"/>
<html:hidden property="branch_id"/>
<html:hidden property="dr_stat"/>
<html:hidden property="dr_category"/>
<html:hidden property="dr_factory"/>
<html:hidden property="dr_name"/>
				<c:if test="${not empty footer}">
					<table width="90%" border="0">
						<tr>
							<%=request.getAttribute("footer")%>
						</tr>
					</table>
				</c:if>
<br><br><br>
				<table border="1" width="90%">
					<tr>
						<td class="top" colspan="12"></td>
					</tr>
					<tr>
						<td class="tab" colspan="12">
							<table class="tabs" cellpadding="0" cellspacing="0">
								<tr>
									<td width="9" class="tab_sf"></td>
									<td width="85" class="tab_f">
										显示结果
									</td>
									<td width="9" class="tab_ef">

									</td>
								</tr>

							</table>
						</td>
					</tr>
					<tr>
						<th>
							维修材料编号
						</th>
						<th>
							维修材料名称
						</th>
						<th>
							维修材料分类
						</th>
						<th>
							进货价格
						</th>
						<th>
							上架价格
						</th>

						<th>
							生产厂家
						</th>

						<th>
							生产日期
						</th>

						<th>
							进货日期
						</th>
						<th>
							数量
						</th>

						<th>
							所在机构
						</th>

						<th>
							状态
						</th>


					</tr>
					<c:if test="${not empty dataList}">

						<logic:present name="dataList">
							<logic:iterate id="material" name="dataList" scope="request">
								<tr onclick="setColor(this);">
									<td align="center">
										<bean:write name="material" property="data1" />
									</td>
									<td align="center">
										<bean:write name="material" property="data2" />
									</td>
									<td align="center">
										<bean:write name="material" property="data13" />
									</td>

									<td align="center">
										<bean:write name="material" property="data5" />
									</td>

									<td align="center">
										<bean:write name="material" property="data6" />
									</td>

									<td align="center">
										<bean:write name="material" property="data7" />
									</td>

									<td align="center">
										<bean:write name="material" property="data8" />
									</td>

									<td align="center">
										<bean:write name="material" property="data9" />
									</td>

									<td align="center">
										<bean:write name="material" property="data10" />
									</td>
									<td align="center">
										<bean:write name="material" property="data14" />
									</td>

									<td align="center">
										<bean:write name="material" property="data15" />
									</td>

								</tr>
							</logic:iterate>
						</logic:present>
					</c:if>

				</table>

			</html:form>
		</center>
	</body>
</html>