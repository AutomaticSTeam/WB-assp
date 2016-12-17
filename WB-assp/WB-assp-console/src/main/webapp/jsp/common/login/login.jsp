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
		<link href="<%=rootPath%>/css/style.css" rel="stylesheet">
	    <link href="<%=rootPath%>/css/style-responsive.css" rel="stylesheet">
	
	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	    <script src="<%=rootPath%>/js/html5shiv.js"></script>
	    <script src="<%=rootPath%>/js/respond.min.js"></script>
	    <![endif]-->
	</head>
	<body class="login-body">
	 <form class="form-signin" action="<%= rootPath%>/jsp/asspConsole.jsp">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">Sign In</h1>
            <img src="<%=rootPath%>/images/login-logo.png" alt=""/>
        </div>
        <div class="login-wrap">
            <input type="text" class="form-control" placeholder="账号" autofocus>
            <input type="password" class="form-control" placeholder="密码">

            <button class="btn btn-lg btn-login btn-block" type="submit">
                <i class="fa fa-check"></i>
            </button>

            <div class="registration">
                                                              还没有账号?
                <a class="" href="registration.html">
                    Signup
                </a>
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
                <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> Forgot Password?</a>

                </span>
            </label>

        </div>

        <!-- Modal -->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Forgot Password ?</h4>
                    </div>
                    <div class="modal-body">
                        <p>Enter your e-mail address below to reset your password.</p>
                        <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">

                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                        <button class="btn btn-primary" type="button">Submit</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal -->

    </form>

</div>



<!-- Placed js at the end of the document so the pages load faster -->

<!-- Placed js at the end of the document so the pages load faster -->
<script src="<%=rootPath%>/js/jquery-1.10.2.min.js"></script>
<script src="<%=rootPath%>/js/bootstrap.min.js"></script>
<script src="<%=rootPath%>/js/modernizr.min.js"></script>

	
	</body>
</html>