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
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
<script src="<%=rootPath%>/jsp/template/js/templateInfo.js" type="text/javascript" charset="utf-8"></script>
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
	<form id="columnsForm" method="post" class="columnsForm">
		<input type="hidden" id="templateId" name="templateId"/>
		<input type="hidden" name="templateCode" id="templateCode"/>
		<div id="columnsDiv" style="height: 450px; overflow-y: scroll;padding-top: 20px;">
			<div class="main-content" >
				<div class="form">
					<div class="form-item">
						<label>导航名称：</label>
						<div class="box-input">
							<input class="inputText" name="columnsName" id="columnsName" type="text" datatype="*1-50"  nullmsg="请填写导航名称"  errormsg="导航名称不能多于20个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>导航类型：</label>
						<div class="box-input">
							<select id="columnsTypeId" name="columnsTypeId" class="select"></select>
						</div>
					</div>
					<div class="form-item">
						<label>父导航：</label>
						<div class="box-input">
							<select name="columnsPid" id="columnsPid" class="select"></select>
						</div>
					</div>
					<div class="form-item">
						<label>是否使用框架 ：</label>
						<div class="box-input">
							<select name="isUseFrames" id="isUseFrames" class="select">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</div>
					</div>
					<div class="form-item">
						<label>选择框架：</label>
						<div class="box-input">
							<select name="framesId" id="framesId" class="select"></select>
						</div>
					</div>
					<div class="form-item">
						<label>打开方式：</label>
						<div class="box-input">
							<select name="openType" id="openType" class="select">
								<option value="0">当前页</option>
								<option value="1">新页面</option>
							</select>
						</div>
					</div>
					<div class="form-item">
						<label>自定义链接：</label>
						<div class="box-input">
							<input type="text" name="customUrl" id="customUrl"/>
						</div>
					</div>
					<div class="form-item">
						<label>默认图标：</label>
						<div class="box-input">
							<input type="hidden" class="columnsIcon" name="columnsIcon"/>
							<img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px " onclick="uploadcpImg('columnsIcon',this)"/>
						</div>
					</div>
					<div class="form-item">
						<label>激活图标：</label>
						<div class="box-input">
							<input type="hidden" class="columnsIconActive" name="columnsIconActive"/>
							<img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px " onclick="uploadcpImg('columnsIconActive',this)"/>
						</div>
					</div>
				</div>
			</div><!--main-content-->
		</div><!--columnsDiv-->
	</form>
</div><!-- content end -->
</body>
</html>