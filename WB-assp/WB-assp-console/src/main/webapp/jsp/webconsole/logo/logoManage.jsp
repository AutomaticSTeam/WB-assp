<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
		<script src="<%=rootPath%>/jsp/webconsole/logo/js/logoManage.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		</script>
		<jsp:include page="/jsp/common/commonCssLink.jsp" />
		<jsp:include page="/jsp/common/commonJs.jsp" /> 
	</head>
<body>
		<div class="w800 fl " style="height:450px;margin-left:20px;">
			<div class="main-content" style="margin: 0 39px 0; margin-top:25px;" >
				<div class="main-body">
				 <form action="" method="post" id="moduleTmplForm">
					<input type="hidden" id="currentPage" name="page" value="1"/>
				    <input type="hidden" name="rows" value="9"/>
					<div class="search">
						<div class="" style="float: right;">
							<div class="btn-item">
								<a href="<%= rootPath%>/jsp/webconsole/logo/createOrEditLogo.jsp" class="btn btn-border-gray border-gray-new dib batchModifyBtn">新增</a>
								<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="logo-edit">编辑</a>
								<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="logo-del">删除</a>
								<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="logo-preview">预览</a>
							</div>
						</div>
					</div><!--search-->
					</form>
					<div class="table" id="logo-table"></div><!--table-->
				</div><!--main-body-->
			</div><!--main-content-->
		</div><!--managementArticles-->
	</body>
</html>