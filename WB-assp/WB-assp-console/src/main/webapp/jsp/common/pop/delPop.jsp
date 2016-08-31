<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>弹窗框</title>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>   
		<script src="<%=rootPath%>/js/template_manage.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
	</head>
	<body>
		<div class="deleteMsgPop">
	   		<div class="deleteMsgPop-tit">确定删除该文件？</div>
	    </div><!--deleteMsgPop-->
	</body>
</html>