<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
   	String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500页面</title>
    <script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/css/base.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/css/error.css"/>
</head>
<body>
    <div id="wrapper">
			<div class="header-container">
				<div class="header">
					<div class="logo">
						<img src="<%=rootPath%>/images/login_logo.png" />
					</div>
					<div class="top-right-info">
						<!-- <a href="">返回.net平台</a>
						<p>欢迎您，张三丰</p> -->
					</div>
				</div>
			</div><!--头部-->
			
			<div class="main-cotainer">
				<div class="main">
					
					<div class="errorBox">
						<div class="errorBox-w">
							<h2>Sorry!</h2>
							<p>猴子又来闹天宫啦，快去请佛祖 ~</p>
							<div class="errorBox-btn">
								<a  class="errorBtn-prev"  onclick="javascript:history.go(-1);">返回上一页</a>
							<!-- 	<a href="" class="errorBtn-home">返回首页</a> -->
							</div>
							<div class="errorBox-img">
								<img src="<%=rootPath%>/images/500.png" alt="" />
							</div>
						</div>
					</div><!--错误容器-->
					
				</div>
			</div><!--主体内容容器-->
			
			<div class="footer">
				<p>北京网视通联科技股份有限公司</p>
				<p>地址：北京市海淀区知春路京仪孵化器D座3层</p>
				<p>Copyright © 2004-2014 CCHVC云通-CDN全业务平台版权所有 京ICP备14002150号-1</p>
				<p>京ICP备14002150号-1 京公网安备1101080213237号</p>
			</div><!--底部-->
			
		</div><!--外部容器-->
		<script>
			$(function(){
				$('.column-list-view-item:last').css({'border-bottom':'none'});
			});
		</script>
</body>
</html>