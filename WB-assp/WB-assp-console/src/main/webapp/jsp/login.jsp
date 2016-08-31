<%@page import="java.util.Date"%>
<%@page pageEncoding="UTF-8"%>
<%
   	String rootPath = request.getContextPath();
   	long timestamp= new Date().getTime();
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>WMS后台管理登录</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/console/css/login.css" />


</head>
<body class="bigBg1" >
	<div class="logWrap">
		<!-- header begin -->
		<div class="header">
			<h1><a href="#" title="wms后台管理系统"><img src="<%=rootPath %>/css/console/img/logo.png" alt="wms后台管理系统" /></a></h1>
		</div>
		<!-- header end -->
		<!-- content begin -->
		<div class="login">
			<form action="<%=rootPath %>/sys/user/login.do" method="post" id="loginForm">
					<p class="loginItem">
						<label for="username" class="label">账   户：</label>
						<input type="text" class="userName" id="userName" name="userAccount" value=""/>
						<span class="error undis" id="userMsg">对不起，请输入用户名！</span>
					</p>
					<p class="loginItem">
						<label for="password" class="label">密　码：</label>
						<input  type="password" class="password" id="passwd" name="userPassword"/>
						<span class="error undis"  style="width:300px;"  id="passMsg">对不起，请输入密码！</span>
					</p>
					
						<p class="loginItem">
							<label for="code" class="label">验证码：</label>
							<input type="text" id="verifyCode" name="verifyCode" class="codeText"/>
							<span class="codeImg"><img id="imgObj" alt="验证码" class="image" src="<%=rootPath %>/sys/login/getVerificationCode.do?timestamp=<%=timestamp %>"/></span> 
							<span class="changeCode"><a title="刷新验证码" class="refreshCode" href="javascript:changeImage()">刷新验证码</a></span>
							<span class="error undis" id="verifyCodeMsg">请输入验证码</span>
							<span class="error undis" id="returnMsg"></span>
						</p>
					<p class="submitBtn"><button type="button" id="loginBtn"></button>
					<a title="忘记密码"  href=" <%=rootPath %>/jsp/common/fogetPassWord.jsp">忘记密码</a> 
					</p>
				</ul>
			</form>
		</div>
		<!-- content end-->
		<!--footer begin-->
		<div class="footer mt10">
			<p><em>&copy;</em>  动力威视　版权所有</p>
		</div>
		<!-- footer end -->
	</div>
</body>
<script type="text/javascript">
    var rootPath = "<%=rootPath %>" ;
</script>
</html>
