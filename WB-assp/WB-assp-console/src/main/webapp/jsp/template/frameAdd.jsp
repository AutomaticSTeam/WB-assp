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
<link rel="stylesheet" type="text/css" href="http://1.202.135.37/wms/template/10004/css/self.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/model.css" />
<link href="<%=rootPath%>/css/base.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=rootPath%>/css/base_console.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />

<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
<script src="<%=rootPath%>/jsp/template/js/frameInfo.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/static/artTemplate/artTemplate.js" type="text/javascript"></script> 
<style type="text/css">
.frameli {
	float: left;
	padding: 10px;
	font-size: 16px;
	cursor: pointer;
}
.colorli {
	color: blue;
}
</style>
<title>模板管理欢迎页</title>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>
<jsp:include page="/jsp/common/commonJs.jsp" />
<%-- <jsp:include page="/jsp/common/commonCssLink.jsp" /> --%>
<jsp:include page="/jsp/template/modules.jsp"></jsp:include>
</head>
<body>
<!-- content begin -->
<!--网站主题内容-->
<div class="main-content" id="ulDiv">
	<div id="tempName" style="font-size: 16px;font-weight: bold;margin: 10px;"></div>
	<ul id="addLi">
	</ul>
	<div style="padding-top: 9px;"><a href="#" id="columns-add-layer"><img src="<%=rootPath %>/images/addImg.png" alt="添加导航" width="22px;"/></a></div>
</div>
<div id="wrapper" style="padding-top: 47px;" class="w">
	
</div><!--wrapper-->
<div class="fl" style="width: 100%;clear: both;margin-left: auto;margin-right: auto;">
	<form id="frameForm" method="post">
		<input type="hidden" id="templateId" name="templateId"/>
		<input type="hidden" name="templateCode" id="templateCode"/>
		<div class="pop-btn">
			<input type="hidden" class="columnsId" name="columnsId" id="columnsId"/>
			<a class="btn dib btn-blue01 cave-btn" onclick="queryFrameList()">添加框架</a>
			<a class="btn dib btn-green01 cancel-btn" id="backStep">关闭</a>
		</div>	
	</form>
</div><!-- content end -->
</body>
</html>