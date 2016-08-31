<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>弹窗框</title>
		<jsp:include page="/jsp/common/commonCssLink.jsp" />
		<jsp:include page="/jsp/common/commonJs.jsp" />
		<link rel="stylesheet" href="<%=rootPath%>/css/base_console.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		</script>
	</head>
	<body>
		<div >
			<div style="margin: 30px auto;">
					<div id="templateDiv" style="height: 400px; overflow-y: scroll;">
						<div class="main-content" >
							<form id="moduleForm" method="post" action="<%= rootPath%>">
							<div class="form">
								<div class="form-item">
									<label>类型：</label>
									<input class="" type="radio" onclick="getFramesByType(this.value)" name="moduleType" value="" checked>所有</input>
									&nbsp
									<input class="" type="radio" onclick="getFramesByType(this.value)" name="moduleType" value="0">预置框架</input>
									&nbsp
					    			<input class="" type="radio" onclick="getFramesByType(this.value)" name="moduleType" value="1">自定义框架</input>
								</div>
								<div class="form-item">
									<label>选择模板：</label>
									<div class="box-input">
										<div class="thumb">
											<img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px "  id="thumbImgId"/>
										</div>
										<select class="inputText w180" name="moduleTmpl" id="moduleTmpl">
						   				</select>
									</div>
								</div>
							</form>
						</div>
						<form id="moduleTmplForm" method="post" action="<%= rootPath%>">
							<div class="form" style="margin-right: 20px;">
								<div class="form-item">
									<label>选择板式：</label>
								</div>
								<div class="box-input" id="dataTable"><!-- 表格容器 -->
										<input class="" type="hidden" name="dataColumnId" id="dataColumnId"></input>
									</div>
							</div>
						</form>
					</div>
					<div class="pop-btn">
						<a class="btn dib btn-blue01 cave-btn" id="saveBtn">提交</a>
						<!-- <a class="btn dib btn-green01 cancel-btn" id="cancleBtn" href="javascript:window.history.go(-1);">取消</a> -->
					</div>	
				</div>
			</div>
		<script src="<%=rootPath%>/js/frame/framesJoinData.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>