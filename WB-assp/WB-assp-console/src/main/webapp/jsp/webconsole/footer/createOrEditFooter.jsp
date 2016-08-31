<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
        <script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
        <link rel="stylesheet" type="text/css" href="<%=rootPath%>/static/Huploadify/Huploadify.css"/>
		<script type="text/javascript" src="<%=rootPath%>/static/Huploadify/jquery.Huploadify.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/plugins/json2.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.config.js"></script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.all.min.js"> </script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
		<script src="<%=rootPath%>/jsp/webconsole/footer/js/footerManage.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		</script>
		<jsp:include page="/jsp/common/commonCssLink.jsp" />
		<jsp:include page="/jsp/common/commonJs.jsp" /> 
	</head>
	<body>
	<div class="w800 fl " style="height:450px;margin-left:20px;"> 
		<div class="column">
			<h2 class="column-tit"><span class="tit">创建footer</span><span class="line"></span></h2>
		</div><!--column-->
		<div id="footerDiv">
		<form id="footerForm" method="post" class="footerForm">
			<input type="hidden" name="templateFooterId" id="templateFooterId" value="${footer.templateFooterId }" />
			<div class="main-content">
				<div class="form">
					<div class="popForm-item">
						<label>版权信息：</label>
						<div class="label-con">
							<div class="editor-box" style="height:auto;">
								<textarea id="editor" name="copyrightInfo" style="width:100%;height:100%;">${footer.copyrightInfo}</textarea>
								<span class="Validform_checktip Validform_right" id="ueditor_Valid" style="display:none;">通过信息验证！</span>
							</div>
						</div>
					</div>
					<!-- <div class="form-item">
						<label>选择模板：</label>
						<div class="box-input">
							<select id="templateId" name="templateId" class="select"></select>
						</div>
					</div> -->
					<div class="form-item">
						<label>选择columns：</label>
						<div class="box-input">
							<div class="checkbox-list" id="queryColumnsCKBoxType">
							</div>
						</div>
					</div>
					<div class="form-item">
						<label>是否支持技术支持：</label>
						<div class="box-input">
							<select id="isShowTechnicalSupport" name="isShowTechnicalSupport" class="select">
								<option value="0">支持</option>
								<option value="1">不支持</option>
							</select>
						</div>
					</div>
					<div class="form-item">
						<label>是否支持手机版：</label>
						<div class="box-input">
							<select id="isShowMobileEdit" name="isShowMobileEdit" class="select">
								<option value="0">支持</option>
								<option value="1">不支持</option>
							</select>
						</div>
					</div>
					<div class="form-item">
						<label>是否支持管理登录：</label>
						<div class="box-input">
							<select id="isShowAdminLogin" name="isShowAdminLogin" class="select">
								<option value="0">支持</option>
								<option value="1">不支持</option>
							</select>
						</div>
					</div>
					<div class="pop-btn">
						<a href="<%=rootPath %>/jsp/webconsole/footer/footerManage.jsp" class="btn dib btn-green01 cancel-btn">返回</a>
						<a class="btn dib btn-blue01 cave-btn" id="submitFooter">提交</a>
					</div>
				</div>
			</div><!--main-content-->
		</form>	
		</div><!--footerDiv-->
</div><!-- content end -->	
</body>
</html>