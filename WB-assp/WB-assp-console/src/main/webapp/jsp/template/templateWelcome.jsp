<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
<title>模板管理欢迎页</title>
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
<script src="<%=rootPath%>/jsp/template/js/templateInfo.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>
<link href="<%=rootPath%>/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/css/page.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/static/jquery-easyui-1.3.4/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/static/jquery-easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=rootPath%>/css/base_console.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/static/Huploadify/Huploadify.css"/>
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
<script src="<%=rootPath%>/js/jquery.form.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/static/jquery-easyui-1.3.4/jquery.easyui.min.js" type="text/javascript"></script>  
<script type="text/javascript" src="<%=rootPath%>/static/Huploadify/jquery.Huploadify.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/static/artTemplate/artTemplate.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/js/template_v1.1.js" type="text/javascript"></script>
<script src="<%=rootPath%>/js/site_manage.js" type="text/javascript"></script> 


<jsp:include page="/jsp/common/templateTree.jsp" /> 
</head>
<body>
<!-- content begin -->
    <div class="w800 fl " style="height:450px;margin-left:20px;">
		<div class="main-content">
			<form action="" method="post">
				<input type="hidden" id="currentPage" name="page" value="1"/>
			    <input type="hidden" name="rows" value="9"/>
				<div class="search">
					<div class="" style="float: right;">
						<div class="btn-item">
							<!-- <a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="columns-add">添加导航</a> -->
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="frame-add">完善模板信息</a>
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="template-release">发布模块</a>
							<!-- <a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="template-preview">预览</a> -->
						</div>
					</div>
				</div><!--search-->
			</form>
			<div class="table" id="template-table"></div><!--table-->
		</div><!--main-content-->
	</div>	
<!-- content end -->

<%--  <jsp:include page="/jsp/common/footer.jsp" /> --%>
</body>
</html>