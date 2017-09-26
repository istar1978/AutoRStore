<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/autorstore/public/headtop.jspf"%>
<c:if test="${not empty userContext}">
<html>
	<HEAD>
		<title>会员管理系统</title>
		<%@include file="/autorstore/public/head.jspf"%>
	</head>

	<script>
	function init() {
		var appVar = new String(navigator.appVersion);
		var st = appVar.substr(appVar.indexOf("MSIE") + 5, 3);
		st = parseInt(st);
		if (st * 10 < 60) {
			alert("对不起,此后台程序最低要求为 IE 6.0 ,你的IE版本低于 IE5.5,请升级浏览器!");
			document.all("frame_left").src = "${store}/IEerror.htm"
		} else
			document.all("frame_left").src = "${store}/sysadm/dynaMenu.do"
		document.all("frame_top").src = "${store}/sysadm/topFrame.do"
	}
	function closeWindow() {
		if (frame_left.normalout == false) {
			wurl = "${store}/sysadm/logout.do?action=reset";
			window.parent.location.href = wurl;
		}
		return true;
	}
</script>
	<!-- frames -->
	<frameset rows="25px,*" cols="*" frameborder="no" onload="init()"
		onunload="closeWindow()" id="fa">
		<frame name="frame_top" src="${store }/sysadm/topFrame.do" marginwidth="0"
			marginheight="0" scrolling="no" frameborder="0">

		<frameset cols="200px,10px,*" id="fs">
			<frame name="frame_left" src="${store }/sysadm/dynaMenu.do"
				marginwidth="0" marginheight="0" scrolling="auto" frameborder="0">
			<frame name="cen" src="${store }/fs.htm" marginwidth="0" marginheight="0"
				scrolling="no" frameborder="0" noresize="noresize">

			<frame name="frame_right" src="${store }/admin.htm"
				marginwidth="10" marginheight="10" scrolling="auto" frameborder="0">
		</frameset>
	</frameset>
	<noframes>
		<body>
			您的浏览器不支持框架，因此不能正常显示网页的效果，请升级您的浏览器。 联系方式：zhongyong@qq.com
		</body>
	</noframes>
</html>
</c:if>
<c:if test="${empty userContext}">
	<center><font color="red" size="8">您无权访问此页面！！</font></center>
</c:if>