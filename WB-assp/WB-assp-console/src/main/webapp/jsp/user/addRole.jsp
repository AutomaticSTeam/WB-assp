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
<script type="text/javascript" src="<%=rootPath%>/jsp/user/js/addRole.js"></script> 
	<form id="roleForm"   class="moduleTmplForm">
		<div style="margin-top:25px;">
			<div class="main-content" >
				<div class="form">
					<div class="form-item">
						<label>角色名称：</label>
						<div class="box-input">
							<input class="inputText" name="roleName" id="roleName" type="text"  datatype="*"  nullmsg="请填写角色名称！"  errormsg="请填写角色名称！" />
						</div>
					</div>
					<div class="form-item">
						<label>角色编码：</label>
						<div class="box-input">
							<input class="inputText" name="roleCode"  id="roleCode" type="text"  datatype="*"  nullmsg="请填写角色编码！"  errormsg="" />
						</div>
					</div>
					<div class="form-item">
						<label>角色描述：</label>
						<div class="box-input">
							<input class="inputText" name="roleDesc"  id="roleDesc" type="text"  datatype="*"  nullmsg="请填写角色描述！"  errormsg="" />
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