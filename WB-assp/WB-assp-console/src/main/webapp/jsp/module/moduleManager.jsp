<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
   String rootPath = request.getContextPath();
//设置缓存为空   
	response.setHeader("Pragma","No-cache");   
	response.setHeader("Cache-Control","no-cache");   
	response.setDateHeader("Expires",   0); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理模块</title>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>

</head>
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<jsp:include page="/jsp/common/commonJs.jsp" /> 
<style type="text/css">
</style>
<body style="margin: 0px auto;">
	<div class="accordantCon" style="height:600px;">
		<div class="sidebar fl minH450"  style="height:100%;background:#efeded;">
		        <h2 class="sidebarName" id="navl_10011"><span class="sidebarPandectOrder">管理模块</span>管理模块<i>管理模块</i></h2>
				<ul class="sidebarList">
				     <li  class="leftLi"><a href="<%= rootPath%>/jsp/module/addModule.jsp" target="module_center">添加模块</a></li>
				     <li  class="leftLi"><a href="<%= rootPath%>/jsp/module/managerModule.jsp" target="module_center">模块管理</a></li>
				     <li  class="leftLi"><a href="<%= rootPath%>/jsp/common/pop/moduleJoinData.jsp" target="module_center">数据关联</a></li>
				</ul>
		</div>
		<div class="w800 fl " style="height:100%;margin-left:20px;">
			<iframe name="module_center" id="module_center" title="module_center" seamless noresize="noresize" style="display: block;height:100%;"></iframe>
	</div>
</div>
<!-- content begin -->
    
			
<!-- content end -->

<%--  <jsp:include page="/jsp/common/footer.jsp" /> --%>
</body>
<script type="text/javascript">
$(function(){
	var fram_width=$(document.body).width()-218;
	$("#module_center").width(fram_width)
	
	var sHeight=screen.height-145;
	$(".accordantCon").height(sHeight);
	
	$(".sidebarList .leftLi a").click(function(){
		$(".sidebarList .leftLi a").removeClass("active");
		$(this).addClass("active");
		
	})
})

</script>
</html>