<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
<script src="<%=rootPath%>/jsp/template/js/frameInfo.js" type="text/javascript" charset="utf-8"></script>
<title>模板管理欢迎页</title>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<jsp:include page="/jsp/common/commonJs.jsp" />
</head>
<body>
<!-- content begin -->
<div class="w800 fl "> 
	<form id="frameForm2" method="post">
		<div class="main-content">
			<form action="" method="post">
				<input type="hidden" id="currentPage" name="page" value="1"/>
			    <input type="hidden" name="rows" value="9"/>
			    <input type="hidden" name="columnsId" id="columnsId"/>
			    <div class="search" style="width: 80%;">
					<div class="" style="float: right;">
						<div class="btn-item">
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="frame-submit">提交</a>
						</div>
					</div>
				</div><!--search-->
			</form>
			<div class="table" id="frame-table"></div><!--table-->
		</div><!--main-content-->
	</form>
</div><!-- content end -->
</body>
</html>