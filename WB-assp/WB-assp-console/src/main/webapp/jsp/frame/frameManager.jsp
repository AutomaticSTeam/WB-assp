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
   function navlFirst(thisLi){
	   $(thisLi).children("a").css("color","#2770b2");
	   $(".sidebar .sidebarList li a:eq(1)").css("color","#4e4e4e");
	   $(".sidebar .sidebarList li a:eq(2)").css("color","#4e4e4e");
   }
   function navlSecond(thisLi){
	   $(".sidebar .sidebarList li a:eq(0)").css("color","#4e4e4e");
	   $(thisLi).children("a").css("color","#2770b2");
	   $(".sidebar .sidebarList li a:eq(2)").css("color","#4e4e4e");
   }
   function navlThird(thisLi){
	   $(".sidebar .sidebarList li a:eq(0)").css("color","#4e4e4e");
	   $(".sidebar .sidebarList li a:eq(1)").css("color","#4e4e4e");
	   $(thisLi).children("a").css("color","#2770b2");
   }
</script>
</head>
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<jsp:include page="/jsp/common/commonJs.jsp" /> 
<script type="text/javascript" src="<%=rootPath%>/js/frame/managerFrames.js"></script> 
<body style="margin: 0px auto;">
	<div class="accordantCon" style="height:600px;">
		<div class="sidebar fl minH450"  style="height:100%;background:#efeded;">
		        <h2 class="sidebarName" id="navl_10011"><span class="sidebarPandectOrder">管理框架</span>管理框架<i>管理框架</i></h2>
				<ul class="sidebarList">
				     <li id="navl_10018" onclick="navlFirst(this)" class="leftLi"><a href="<%= rootPath%>/jsp/frame/managerFrame.jsp" target="module_center">编辑板式</a></li>
				     <li id="navl_10018" onclick="navlSecond(this)" class="leftLi"><a href="<%= rootPath%>/jsp/frame/managerFrames.jsp" target="module_center">框架管理</a></li>
				     <li id="navl_10018" onclick="navlThird(this)" class="leftLi"><a href="<%= rootPath%>/jsp/frame/framesJoinData.jsp" target="module_center">数据关联</a></li>
				</ul>
		</div>
		<div class="w800 fl " style="height:100%;margin-left:20px;">
			<iframe src="<%= rootPath%>/jsp/frame/managerFrame.jsp" name="module_center" id="module_center" title="module_center" noresize="noresize" style="display: block;height:100%;"></iframe>
	</div>
</div>
<!-- content begin -->
    
			
<!-- content end -->

<%--  <jsp:include page="/jsp/common/footer.jsp" /> --%>
</body>
<script type="text/javascript">
$(function(){
	var fram_width=$(document.body).width()-218;
	$("#module_center").width(fram_width);
	$(".sidebar .sidebarList li a:eq(0)").css("color","#2770b2");
})

</script>
</html>