<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/autorstore/public/headtop.jspf"%>
<html>
<head>
<%@include file="/autorstore/public/head.jspf"%>
<script type="text/javascript">
	function test(){
		alert("aaaa");
		 $j.ajax({
             type:"post",
             url:"${store}/sale/doAjaxValidateCart.do",
             data:{
            	assId:"1000000001"
             },
             dataType:"json",
             success:function(data){
                 alert("value returned from sever is : " + data["matNum"] );
             }
         });
	
	}
	function failure(){
		alert("failure");
	}
	function load(){
		alert("load");
	}
	function create(){
		alert("create");
	}
	function showResponse(){
		alert('123');
	}
</script>
</head>
<body>
<h1>test.jsp</h1>
<form action="${store }/sale/doAjaxValidateCart" id="qryForm">
<input type="button" value="cs" onclick="test();"  />
<input type="hidden" value="123" name="name">
</form>
<br /><br />
<a id=""></a>
</body>
</html>