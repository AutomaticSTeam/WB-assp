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
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/template.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/page.css" />
<link rel="stylesheet" href="<%=rootPath%>/js/page/pager.css" />
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>   
<script src="<%=rootPath%>/js/dialog.js" type="text/javascript" charset="utf-8"></script>
<script src="<%= rootPath%>/static/plugins/jquery.tmpl.min.js" type="text/javascript"></script>
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/jsp/template/selectTemplate/js/selectTemplate.js" type="text/javascript" charset="utf-8"></script>
</head>
<script>
$(document).ready(function(){
	$("#templateFrame").height(document.documentElement.clientHeight-50);	
		  
});
</script>
<body>
<div id="topBarArea" class="topBarArea">
			<div class="topBarArea-fixed">
				<div class="w">
					<ul class="topBarArea-nav fl">
					</ul>
					<ul class="topBarArea-right fr">
						<li class="save btn-website-edit" style=""><button class="btn" onclick="$templateManage.toTemplate(${templateId});;">使用</button></li>
						<li class="cancel btn-website-edit" style=""><button class="btn"  onclick="window.location.href='/WB-assp-tmpl-web/jsp/template/selectTemplate/selectTemplate.jsp'" >返回</button></li>
						
					</ul>
				</div>
			</div>
</div><!-- topBarArea -->
<iframe src="<%=rootPath%>/${templateId}/${columnsId}/index.html" frameborder="0" width="100%" id="templateFrame" name="templateFrame"></iframe>
</body>
</html>