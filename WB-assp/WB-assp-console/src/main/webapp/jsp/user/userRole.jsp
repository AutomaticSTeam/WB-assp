<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>模块管理</title>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
   var userId= '${userId}';
</script>
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<jsp:include page="/jsp/common/commonJs.jsp" /> 
<link rel="stylesheet" href="<%=rootPath%>/js/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=rootPath%>/js/ztree/jquery.ztree.core-3.5.min.js"></script> 
<script type="text/javascript" src="<%=rootPath%>/js/ztree/jquery.ztree.excheck-3.5.min.js"></script> 
<script type="text/javascript" src="<%=rootPath%>/jsp/user/js/userRole.js"></script> 
<div class="content_wrap" style="width:400px;">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
		<form id="tree">
			<input type="hidden" id="userId" value="${userId}" />
			<input type="button" id="roleForm" onclick="tjForm()" value="提交" />
		</form>
	</div>
</div>
</body>
</html>