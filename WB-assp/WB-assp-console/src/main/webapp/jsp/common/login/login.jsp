<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String rootPath = request.getContextPath();%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>用户登录</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	      <script type="text/javascript">
		    	var rootPath = '<%= rootPath%>';
		</script>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<script type="text/javascript" src="<%=rootPath%>/static/plugins/json2.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/jsp/common/login/js/login.js"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/common.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/login.css" />
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />

	</head>
	<body>
	<div id="wrapper">
			<div class="header-container">
				<div class="header">
					<div class="logo">
						<img src="<%=rootPath%>/images/login_logo.png" />
					</div>
				</div>
			</div><!--头部-->
			<div class="login-main-container">
				<div class="login-main">
					<div class="login-image">
						<div class="loginImage-item">
							<img src="<%=rootPath%>/images/login_iamge_01.png">
						</div>
						<div class="loginImage-item">
							<img src="<%=rootPath%>/images/login_iamge_02.png">
						</div>
					</div>
					<div class="login-border">
						<h1>用户登录</h1>
						<form class="login-form" method="post" action="">
							<div class="form-input-item">
								<span class="user-name-icon"></span>
								<input class="user-input" type="text" placeholder="请输入用户名" name="userName" id="userName"    nullmsg=" " />
							</div>
							<div class="form-input-item">
								<span class="password-icon"></span>
								<input class="password-input" type="password" placeholder="请输入密码"  name="password" id="password"    nullmsg=" "style="width: 243px;float: right;height: 42px;line-height: 42px;border: none;"/>
							</div>
							<div class="form-input-item-group">
								<div class="form-input-item">
									<span class="password-icon"></span>
									<input type="text" placeholder="验证码" name="verifyCode" id="verifyCode"/>
									<input type="hidden" name="vistSource" value="1">
								</div>
								<span class="text-code-image" style="line-height: 72px;"><img src="" id="verificationCode" style="width: 92px;height: 38px;"></span>
								<a class="text-code-btn" id="changeVerificationCode">&nbsp</a>
							</div>
							<div class="memory">
								<!-- <span class="radio"><input type="radio" value="" style="display: none;"></span>
								<label>记住密码</label> -->
							</div>
						<button type="button" class="login-btn" id="login">登录</button>
						</form>
					</div>
				</div>
			</div><!--主体登录框-->
			
			<div class="footer">
				<p>北京网视通联科技股份有限公司</p>
				<p>地址：北京市海淀区知春路京仪孵化器D座3层</p>
				<p>Copyright © 2004-2014 CCHVC云通-CDN全业务平台版权所有 京ICP备14002150号-1</p>
				<p>京ICP备14002150号-1 京公网安备1101080213237号</p>
			</div><!--底部-->
		</div><!--外部容器-->
	</body>
</html>