<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="autorstore/public/headtop.jspf" %>
<html>
	<head>
	<%@include file="autorstore/public/head.jspf" %>
		<link rel="stylesheet" type="text/css" href="${store}/theme/menu.css" title="default" />
		<title>系统菜单展示</title>

		<script language="javascript">
	normalout=false;
	function act(a){ 
		normalout=true;
		if(a=="reset"){
			bb=confirm("确定要重新登录吗？");
		}else if(a=="logout"){
		bb=confirm("确定要退出登录吗？");
	}
	if(bb){
		wurl="${store}/sysadm/logout.do";
		window.parent.location.href=wurl;
		return true;
	}else{
		return false;
	}
	return true;
}		
			function noAct(){
				return ;
			}
			function onClickHome(){
				document.home.submit();
			}

			function onClickFolder(arg){
				document.folder_or_page.type.value = "FOLDER";
				document.folder_or_page.argument.value = arg;
				document.folder_or_page.submit();
			}

			function onClickItem(arg){
				if(arg.indexOf("://") > 0){
    		 // Outside URL
					if((typeof window.popupWindow) == "function"){
						popupWindow(arg);
						return;
					}
				}
				document.folder_or_page.type.value = "PAGE";
				document.folder_or_page.argument.value = arg;
				document.folder_or_page.submit();
			}

			function logout(){
				document.logout.submit();
			}

		</script>
		<style type="text/css" title="tree_basic_style">
a {
	text-decoration: none;
}

.tree_cell {
	position: relative;
	display: block;
	visibility: visible;
}

dev.tree_line {
	position: relative;
	display: inline;
	visibility: visible;
}
</style>
		<style type="text/javascript" title="tree_extend_style">
	if(navigator.appName.indexOf("Netscape") != -1 && parseInt(navigator.appVersion.charAt(0)) < 5){
		classes.tree_cell.DIV.position = "absolute";
		classes.tree_cell.DIV.visibility = "hidden";
		classes.tree_line.DIV.position = "absolute";
		classes.tree_line.DIV.visibility = "visible";
	}
	</style>
</head>

<body>
<br>
<br>
<div class="tree_line" id="tree_top"></div> 
<table border="0" cellpadding="0" cellspacing="0"> 
	<tr>
		<td valign="top" nowrap> 
	  		<div class="tree_cell" id="tree_root"> 
	      		<a href="JavaScript:tree_switch_home();">
	      			<img src="${store}/images/tree/home.gif" border="0" width="22" height="20" alt="Top Page" />
	      		</a> 
      		</div>
      
      		<div class="tree_cell" id="root"> 
	      		<a href="${store}/admin.htm" target="frame_right">【首页】</a>
      		</div>                                                               
	      
     		 <div class="tree_cell" id="tree_root/0:T"> 
				<a href="JavaScript:tree_switch_folder('tree_root/0:T');"> 
       			 <img src="${store}/images/tree/conct_T.gif" border="0" width="19" height="16"> 
       			 <img src="${store}/images/tree/folder.gif" border="0" width="14" height="14" >
				</a> 
       			 <a href="JavaScript:void(0);" onclick="tree_highlight('tree_root/0:T');">  登录管理</a>
				<br>
      		</div>                                                                  
	      
     	    <div class="tree_cell" id="tree_root/0:T/0"> 
				<img src="${store}/images/tree/conct_I.gif" border="0" width="19" height="16" /> 
        		<img src="${store}/images/tree/conct_T.gif" border="0" width="19" height="16" /> 
        		<a onclick="tree_highlight('tree_root/0:T/0');act('reset');" class="overpic" > 
        			<img src="${store}/images/tree/twistcb.gif" border="0"  />
					重新登录
				</a>
			<br>
      		</div>                                                                 
	      
      		<div class="tree_cell" id="tree_root/0:T/1">
				<img src="${store}/images/tree/conct_I.gif" border="0" width="19" height="16" /> 
        		<img src="${store}/images/tree/conct_T.gif" border="0" width="19" height="16" /> 
        		<a onclick="tree_highlight('tree_root/0:T/1');act('logout');" class="overpic" > 
        		<img src="${store}/images/tree/twistcb.gif" border="0" /> 退出登录
				</a>
				<br>
      		</div>                                                                 
	      
      		<div class="tree_cell" id="tree_root/0:T/2"> 
				<img src="${store}/images/tree/conct_I.gif" border="0" width="19" height="16" /> 
        		<img src="${store}/images/tree/conct_L.gif" border="0" width="19" height="16" /> 
        		<a href="${store}/sysadm/editpwdInit.do" target="frame_right" onclick="tree_highlight('tree_root/0:T/2');"> 
        		<img src="${store}/images/tree/twistcb.gif" border="0" >修改密码
				</a>
				<br>
      		</div>                                                                 
	   
 <!--start-->

<%=request.getAttribute("menuStr")%>

	<!--菜单全部展开-->	      
      <div class="tree_cell" id="tree_root/full_open"> 
		<a href="JavaScript:tree_switch_open();"> 
        <img src="${store}/images/tree/arrow_down.gif" border="0" width="19" height="18" />
		</a>
		<br>
      </div>                                                   
	                                                                 
	    </td>                                                           
	  </tr>                                                                    
</table>                                                                    
                                                         
<div class="tree_line" id="tree_bottom">
<br><br>                                                         
</div>                                                                    
                                                         
<form name="tree_event">                                                                    
	<input type="hidden" name="item_action" value="onClickItem">                                                         
	<input type="hidden" name="home_action" value="onClickHome">                                                        
	<input type="hidden" name="highlight_color" value="skyblue">                                                    
	<input type="hidden" name="folder_action" value="onClickFolder">                                                                 
</form>                                                                    
<!--输出下面信息项目-->                                                                    
<script language="javascript" src="${store}/js/Menu.js"></script>             
</body>   
</html>                                                                    
                                                                    
                                                                    
<!-- End of HTML -->