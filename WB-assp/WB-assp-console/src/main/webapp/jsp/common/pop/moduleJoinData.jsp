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
								<div class="form-item" "margin-right: 20px;">
									<label>名称：</label>
									<div class="box-input">
										<input class="inputText w180" type="text" name="moduleName" id="moduleName" ></input>
									</div>
								</div>
								<div class="form-item">
									<label>类型：</label>
									<input class="" type="radio" name="moduleType" value="0" checked>单一模块</input>
									&nbsp
					    			<input class="" type="radio" name="moduleType" value="1">聚合模块</input>
								</div>
								<div class="form-item">
									<label>父节点：</label>
									<div class="box-input">
										<select class="inputText w180" name="modulePid" id="modulePid">
											<option value="0">当前为父节点</option>
						    			</select>
									</div>
								</div>
								<div class="form-item">
									<label>行数限制：</label>
									<input class="inputText w180" type="text" name="moduleItemLineNum"  id="moduleItemLineNum" ></input>
								</div>
								<div class="form-item">
									<label>列数限制：</label>
									<input class="inputText w180" type="text" name="moduleItemColumnNum" id="moduleItemColumnNum"></input>
								</div>
								<div class="form-item">
									<label>显示标题：</label>
									<div class="box-input">
										<input class="" type="radio" name="showTitile" value="1">是</input>
										&nbsp
					    				<input class="" type="radio" name="showTitile" value="0" checked>否</input>
									</div>
								</div>
								<div class="form-item">
									<label>显示分页：</label>
									<div class="box-input">
										<input class="" type="radio" name="pageShow" value="1" id="pageShow_yes">是</input>
										&nbsp
					    				<input class="" type="radio" name="pageShow" value="0" checked id="pageShow_no">否</input>
									</div>
								</div>
								<div class="form-item" style="display:none;" id="pageSize_item">
									<label>每页数量：</label>
									<input class="inputText w180" type="text" name="pageSize" value=""></input>
								</div>
								<div class="form-item">
									<label>组合方式：</label>
									<div class="box-input">
										<input class="" type="radio" name="moduleStyleType" value="1">tab</input>
										&nbsp
					    				<input class="" type="radio" name="moduleStyleType" value="2" checked>竖列</input>
									</div>
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
									<label>数据类型：</label>
									<input class="" value="2" type="hidden" name="contentDataType" id="contentDataType"></input>
									<input class=""  type="hidden" name="moduleId" id="moduleId"></input>
									<div class="box-input">
										<select class="inputText w180" name="dataTargetTable" id="dataTargetTable">
											<!-- <option id="0" value="wms_picture" class="2">图片</option> -->
											<option id="1" value="wms_picture_album" class="2">图册</option>
											<option id="2" value="wms_picture_album_type" class="2">图册类型</option>
											<option id="3" value="wms_article" class="1">文章</option>
											<option id="4" value="wms_article_type" class="1">文章类型</option>
											<option id="5" value="wms_media" class="3">视频</option>
											<option id="6" value="wms_media_type" class="3">视频类型</option>
											<option id="7" value="wms_template_logo" class="10">logo</option>
						    			</select>
									</div>
								</div>
								<div class="form-item">
									<label>选择数据：</label>
								</div>
								<div class="box-input" id="dataTable"><!-- 表格容器 -->
										<input class="" type="hidden" name="dataColumnId" id="dataColumnId"></input>
									</div>
							</div>
						</form>
					</div>
					<div class="pop-btn" style="margin-bottom:65px;">
						<a class="btn dib btn-blue01 cave-btn" id="saveBtn">提交</a>
						<!-- <a class="btn dib btn-green01 cancel-btn" id="cancleBtn" href="javascript:window.history.go(-1);">取消</a> -->
					</div>	
				</div>
			</div>
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script>
		<script src="<%=rootPath%>/jsp/common/pop/js/moduleJoinData.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>