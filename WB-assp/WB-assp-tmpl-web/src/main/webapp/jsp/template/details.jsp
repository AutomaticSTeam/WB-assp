<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@ page import="java.lang.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>延安精神，中华魂，党史人物，优良传统，中国精神</title>
<script type="text/javascript">
    var rootPath = '<%= rootPath%>';
</script>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/model.css" />
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>   
<script src="<%=rootPath%>/js/site.js" type="text/javascript"></script>
<script src="<%=rootPath%>/js/common/DateUtils.js" type="text/javascript"></script>      
<script src="<%=rootPath%>/js/site_frame_module.js" type="text/javascript"></script> 
<script src="<%= rootPath%>/static/plugins/jquery.tmpl.min.js" type="text/javascript"></script>
<!-- 建站操作css -->
	<link rel="stylesheet" href="<%=rootPath %>/css/pop.css" />
<!-- 建站操作css -->
<link rel="stylesheet" href="http://111.206.116.91/wms/template/10004/css/self.css" />

</head>
<body>
       <!--建站操作头部-->
      <%--   <jsp:include page="/jsp/template/topBarArea.jsp"></jsp:include> --%>
		<!--建站操作头部-->
		
		<!--网站主题内容-->
		<div id="wrapper">
			
			<jsp:include page="/jsp/template/header.jsp"></jsp:include>
			<div class="main">
				<div class="w" id="DynamicModules">
				</div>
			</div><!--main-->
			
			<jsp:include page="/jsp/template/footer.jsp"></jsp:include>
		</div><!--wrapper-->
		<!--网站主题内容-->
		
		<!--建站操作js-->
		<script src="<%=rootPath%>/js/template_manage.js" type="text/javascript"></script>   
		<!--建站操作js-->
</body>
</html>