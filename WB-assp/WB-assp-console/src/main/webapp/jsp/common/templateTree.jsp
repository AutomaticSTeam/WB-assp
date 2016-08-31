<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
   String rootPath = request.getContextPath();
	//设置缓存为空   
	response.setHeader("Pragma","No-cache");   
	response.setHeader("Cache-Control","no-cache");   
	response.setDateHeader("Expires",   0);   
%>
<body class="bigBg">
<div id="div_content" class="content accordant mt10">
	<div class="accordantCon" style="margin-left: 0px;">
		<div class="sidebar fl minH450">
		        <h2 class="sidebarName" id="navl_10011"><span class="sidebarPandectOrder">管理模板</span>管理模板<i>管理模板</i></h2>
				<ul class="sidebarList">
				     <li id="navl_10018" class="leftLi"><a href="<%= rootPath%>/jsp/template/templateWelcome.jsp">模板管理</a></li>
				     <li id="navl_10018" class="leftLi"><a href="<%= rootPath%>/jsp/template/templateAdd.jsp">添加模板</a></li>
				     <%-- <li id="navl_10018" class="leftLi"><a href="<%= rootPath%>/jsp/template/templateEdit.jsp">编辑板式</a></li> --%>
				</ul>
		</div>

		<!-- left end -->
		
<script type="text/javascript">
	
$(function(){
	$('.sidebarList  li ').bind('click', function(){ 
		$('.leftLi').removeClass('current'); 
		$(this).addClass('current'); 
	}); 
})
function loadPage(url){
	alert("in loadPage");
	$.ajax({
	 	async:true,
	    cache : false,
        url: url,
      	type:'GET',		
      	success:function(data){
      		$("#center").html(data);
      	},
      	error: function(){
      		$.messager.alert('提示消息','系统异常，请联系管理员','error');
      	}
    }); 
}
function addClass(id){
	$('.leftLi').removeClass('current'); 
	$("#"+id).addClass('current'); 
}
</script>
