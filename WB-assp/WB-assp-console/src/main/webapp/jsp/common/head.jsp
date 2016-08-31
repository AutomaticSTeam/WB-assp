<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);   
   String rootPath = request.getContextPath();
%>
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<jsp:include page="/jsp/common/commonJs.jsp" /> 
<script language="javascript"> 
javascript:window.history.forward(1); 
</script>
<script language="javascript"> 
//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
function banBackSpace(e){     
  var ev = e || window.event;//获取event对象     
  var obj = ev.target || ev.srcElement;//获取事件源     
    
  var t = obj.type || obj.getAttribute('type');//获取事件源类型    
    
  //获取作为判断条件的事件类型  
  var vReadOnly = obj.getAttribute('readonly');  
  var vEnabled = obj.getAttribute('enabled');  
  //处理null值情况  
  vReadOnly = (vReadOnly == null) ? false : vReadOnly;  
  vEnabled = (vEnabled == null) ? true : vEnabled;  
    
  //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
  //并且readonly属性为true或enabled属性为false的，则退格键失效  
  var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")   
              && (vReadOnly==true || vEnabled!=true))?true:false;  
   
  //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
  var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")  
              ?true:false;          
    
  //判断  
  if(flag2){  
      return false;  
  }  
  if(flag1){     
      return false;     
  }     
}  

//禁止后退键 作用于Firefox、Opera  
document.onkeypress=banBackSpace;  
//禁止后退键  作用于IE、Chrome  
document.onkeydown=banBackSpace; 
</script>
</head>
<body  class="bigBg" style="text-align:center;">
<div class="header" >
	<h1><a href="#" title="动力威视-WMS管理系统"></a><span>动力威视-WMS管理系统</span></h1>
	<p class="headerUser">
		您好！<b>管理员</b>
		<a href="<%= rootPath%>/jsp/common/updatePassWord.jsp" target="mainFrame_center"><span style="color:blue;">[修改密码]</span></a>
		,欢迎您来到WMS管理系统
		<span id="sysTime"></span>
		[<a href="javascript:void(0)" title="退出" class="eTxtLink2" onclick="cansolCheck()">退出</a>]</p>
</div>
<form id="loginOutForm" action="<%=rootPath %>/sys/user/loginOut.do" style="display:none;"></form>
<div class="nav" style="margin:0 auto;width:100%;">
    <ul>
       		<li  class="headLi"><a href="<%= rootPath%>/jsp/template/templateWelcome.jsp" target="mainFrame_center">管理模板</a></li>
       		<li  class="headLi"><a href="<%= rootPath%>/jsp/frame/frameManager.jsp" target="mainFrame_center">管理框架</a></li>
       		<li  class="headLi"><a href="<%= rootPath%>/jsp/module/moduleManager.jsp" target="mainFrame_center">管理模块</a></li>
       		<li id="navt_10011" class="headLi"><a href="<%= rootPath%>/jsp/webconsole/siteConsole.jsp" target="mainFrame_center">基础数据</a></li>
       		<li id="navt_10011" class="headLi"><a href="<%= rootPath%>/jsp/user/frameList.jsp" target="mainFrame_center">权限管理</a></li>
           <li id="navt_10011" class="headLi"><a href="<%= rootPath%>/jsp/report/reportLeft.jsp" target="mainFrame_center">报表统计</a></li>
       	   <li id="navt_10011" class="headLi"><a href="<%= rootPath%>/jsp/config/configLeft.jsp" target="mainFrame_center">配置管理</a></li>
	   	   <li id="navt_10011" class="headLi"><a href="<%= rootPath%>/jsp/sysSettings/settingLeft.jsp" target="mainFrame_center">系统设置</a></li>
       	   <li id="navt_10011" class="headLi"><a href="<%= rootPath%>/jsp/knowledge/left.jsp" target="mainFrame_center">知识库</a></li>
  </ul>
</div>
<script type="text/javascript">
var rootPath = "<%= rootPath%>"; 
 $(function(){
	$('.nav  li ').bind('click', function(){ 
		$('.headLi').removeClass('current'); 
		$(this).addClass('current'); 
	}); 
});
</script>
