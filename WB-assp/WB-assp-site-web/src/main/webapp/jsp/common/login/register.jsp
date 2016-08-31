<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String rootPath = request.getContextPath();%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>用户注册</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	      <script type="text/javascript">
		    	var rootPath = '<%= rootPath%>';
		</script>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<script type="text/javascript" src="<%=rootPath%>/js/common/ValidateUtils.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/js/common/sendSms.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/plugins/json2.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/jsp/common/login/js/register.js"></script>
 		
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/common.css" />
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=rootPath%>/css/login.css" />

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
						<h1>用户注册</h1>
						<form class="register-form" method="post" action="" id="add">
							<div class="form-input-item">
								<span class="user-name-icon"></span>
								<input class="user-input" type="text" placeholder="请输入手机号" name="userName" id="userName"  datatype="m" errormsg="请输入11位正确手机号！" nullmsg="" />
							</div>
							
							
							<!-- 获取短信验证 -->
							<div class="form-input-item-group">
								<div class="form-input-item form-input-item1">
									<span class="message-icon"></span>
									<input class="user-input" name="verifyCode" type="text" placeholder="短信动态码" id="verifyCode"/>
								</div>
								<span class="obtain-code" id="getSmsInfoBtn">获取动态码</span>
								<span id="sendSmsResult"></span>
							</div> 
							
							
							<!--请输入密码  -->
							<div class="form-input-item">
								<span class="password-icon"></span>
								<input class="password-input" type="password" placeholder="请输入6-18位密码"  name="password" id="password1"   datatype="s6-18" nullmsg=" " style="width: 243px;float: right;height: 42px;line-height: 42px;border: none;"/>
							</div>
							<!--请再次输入密码  -->
							<div class="form-input-item">
								<span class="password-icon"></span>
								<input class="password-input" type="password" placeholder="请再次输入6-18位密码"  name="password2" id="password2" datatype="s6-18"   nullmsg=" " style="width: 243px;float: right;height: 42px;line-height: 42px;border: none;"/>
							</div>
							<button type="button" class="login-btn" id="register">注册</button>
							<p align="center" style="margin-top: 9px;">已有账号，<a href="<%=rootPath%>/jsp/common/login/login.jsp" style="color:#5E9CDC;">请登录</a></p>
						</form>
					</div>
				</div>
			</div><!--主体注册框-->
			
			<div class="footer">
				<p>北京网视通联科技股份有限公司</p>
				<p>地址：北京市海淀区知春路京仪孵化器D座3层</p>
				<p>Copyright © 2004-2014 CCHVC云通-CDN全业务平台版权所有 京ICP备14002150号-1</p>
				<p>京ICP备14002150号-1 京公网安备1101080213237号</p>
			</div><!--底部-->
		</div><!--外部容器-->
	</body>
	<script>
	var form1=$("#add").Validform({
		tiptype:3
	});
	</script>
</html>