<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String rootPath = request.getContextPath();%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>自动建站欢迎页</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<script type="text/javascript">
		       var rootPath = '<%= rootPath%>';
		</script>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<script type="text/javascript" src="<%=rootPath%>/js/json2.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/jsp/common/login/js/welcome.js"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/common.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/welcome.css" />
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
	</head>
	<body>
	<div id="wrapper">
			<div class="header-container">
				<div class="header">
					<div class="logo">
						<img src="<%=rootPath%>/images/login_logo.png" />
					</div>
					<div class="top-right-info">
						<p id="userName">欢迎您，</p>
						<a href="<%=rootPath%>//sys/login/logout.do" style="width:73px;">退出</a>
						<!-- <a href="">返回.net平台</a> -->
					</div>
				</div>
			</div><!--头部-->
			
			<div class="main-cotainer">
				<div class="main">
					<div class="cloumn-focus">
						<img src="<%=rootPath%>/images/focus_image.png" />
						<span class="label"></span>
					</div><!--左侧焦点图-->
					<div class="column-list">
						<h1 class="empt-flag" style="display: none;">亲你还没有创建网站</h1>
						<ul class="column-list-view" id="siteContent">
						
						</ul>
					</div><!--右侧网站列表-->
					<div class="column-bottom">
						<img src="<%=rootPath%>/images/flow_image.png" />
						<shiro:hasPermission name="wms:manage:createsite">
						<a class="create-btn" href="<%=rootPath%>/jsp/template/selectTemplate/selectTemplate.jsp">创建网站</a>
						</shiro:hasPermission>
					</div><!--下部流程图-->
				</div>
			</div><!--主体内容容器-->
			
			<div class="footer">
				<p>北京网视通联科技股份有限公司</p>
				<p>地址：北京市海淀区知春路京仪孵化器D座3层</p>
				<p>Copyright © 2004-2014 CCHVC云通-CDN全业务平台版权所有 京ICP备14002150号-1</p>
				<p>京ICP备14002150号-1 京公网安备1101080213237号</p>
			</div><!--底部-->
			
		</div><!--外部容器-->
	</body>
</html>