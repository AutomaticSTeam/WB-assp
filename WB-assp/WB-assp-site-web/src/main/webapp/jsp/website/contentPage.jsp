<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@ page import="java.lang.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${website.siteName }</title>
<script type="text/javascript">
    var rootPath = '<%= rootPath%>';
</script>
 <jsp:include page="/jsp/common/commonCssLink.jsp"></jsp:include> 
 <jsp:include page="/jsp/common/commonWebsiteJs.jsp"></jsp:include> 
<c:if test="${contentDataId != null }">
       <script type="text/javascript">
       $(document).ready(function(){
    	   Template.drawFrames({showType:2});
       });
       var  contentDataId = '${contentDataId}';
       var  dataType = '${dataType}';
      </script>
</c:if>
<link rel="stylesheet" href="http://1.202.135.37/wms/site/${website.siteCode}/css/self.css" />
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
</head>

<body>
       <!--建站操作头部-->
       <c:if test="${loginUser != null }">
             <jsp:include page="/jsp/website/topBarArea.jsp"></jsp:include> 
       </c:if>
		<!--建站操作头部-->
		
		<!--网站主题内容-->
		<div id="wrapper">
		</div><!--wrapper-->
		<!--网站主题内容-->
</body>
 <jsp:include page="/jsp/website/modules.jsp"></jsp:include>
 <c:if test="${loginUser != null }">
 <!--建站操作js-->
	<script src="<%=rootPath%>/js/site_manage.js" type="text/javascript"></script>   
	 <script src="<%=rootPath%>/static/jwplayer6.6/jwplayer.js" type="text/javascript"></script>
	<script src="<%=rootPath%>/js/common/vedio.js" type="text/javascript"></script>
</c:if>
</html>
