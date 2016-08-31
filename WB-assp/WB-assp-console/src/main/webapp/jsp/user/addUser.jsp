<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>
<body id="d">
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<jsp:include page="/jsp/common/commonJs.jsp" />
<script type="text/javascript" src="<%=rootPath%>/jsp/user/js/addUser.js"></script> 
	<form id="userForm"   class="moduleTmplForm">
		<div style="margin-top:25px;">
			<div class="main-content" >
				<div class="form">
					<div class="form-item">
						<label>登录账号：</label>
						<div class="box-input">
							<input class="inputText" name="userName" id="userName" type="text"  datatype="m"  nullmsg="请填写手机号！"  errormsg="请填写手机号！" />
						</div>
					</div>
					<div class="form-item">
						<label>密码：&nbsp</label>
						<div class="box-input">
							<input class="inputText" name="password"  id="password" type="password"  datatype="*"  nullmsg="请填写密码！"  errormsg="" />
						</div>
					</div>
					<div class="form-item">
						<label>确认密码：&nbsp</label>
						<div class="box-input">
							<input class="inputText" name="qrpassword"  id="qrpassword" type="password"  datatype="*"  nullmsg="请填写密码！"  errormsg="" />
						</div>
					</div>
					<div class="pop-btn">
						<a class="btn dib btn-blue01 cave-btn" id="saveBtn">保存</a>
						<a class="btn dib btn-green01 cancel-btn" id="cancleBtn" href="javascript:window.history.go(-1);">取消</a>
					</div>
				</div><!--form-->
			</div><!--main-content-->
		</div><!--managementmoduleTmpls-->	
	</form>
</body>
</html>