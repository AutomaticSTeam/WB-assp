<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置模板页</title>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>
<link rel="stylesheet" type="text/css" href="http://1.202.135.37/wms/template/10002/css/self.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/model.css" />
<link href="<%=rootPath%>/css/base.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=rootPath%>/css/base_console.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />


<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
<script src="<%=rootPath%>/static/artTemplate/artTemplate.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/js/site.js" type="text/javascript"></script>
<script src="<%=rootPath%>/js/common/DateUtils.js" type="text/javascript"></script>      
<script src="<%=rootPath%>/js/website_site.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/js/template_v1.1.js" type="text/javascript"></script>
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script>  
<script src="<%=rootPath%>/js/site_manage.js" type="text/javascript"></script> 
<script type="text/javascript">
   $(function(){
	   Template.initTemplatePage(5);
   });
</script>


<jsp:include page="/jsp/template/modules.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/jsp/template/topBarArea.jsp"></jsp:include> 
    <div id="wrapper"  class="w">
	
    </div>
    <input type="hidden" id="templateId" name="templateId"/>
    <input type="hidden" name="templateCode" id="templateCode"/>
</body>
</html>
<jsp:include page="/jsp/template/modules.jsp"></jsp:include>