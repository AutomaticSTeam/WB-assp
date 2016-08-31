<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>弹窗框</title>
		
		<script src="<%=rootPath%>/static/html5shiv-master/dist/html5shiv.js"></script>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>   
		<script src="<%=rootPath%>/js/template_manage.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/plupload-2.1.9/js/plupload.full.min.js"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		</script>
		
	</head>
	<body>
		<div class="popUpload">
			<div class="pop-head pop-tab-title">
				<a data-tab="01" class="active">上传</a>
			</div><!--pop-head-->
			<div class="pop-body">
				<div class="popTabContent-item popTabContent-01">
					<div class="upload-img fl">
						<div class="popContent-head" id="popContent-head">
							<a href="javascript:void(0)" class="btn dib btn-border-gray uploadFile" id="uploadFile">文件上传</a>
							<input type="file"  style="display:none" id="image_url" name="files[]" accept="image/gif,image/png,image/jpeg,image/jpg" multiple="multiple">
							<%-- <input type="hidden" id="templateCode" value="${siteTempalte.templateCode}"> --%>
							<input type="hidden" id="siteCode" value="${website.siteCode}">
						</div>
						<div class="popContent-body" style="overflow: auto;">
							<ul class="popImgList" id="_popImgList">
							</ul>
							<div class="msg" style="display:block">点击“文件上传”按钮开始上传</div>
						</div>
						<!-- <div class="popPage">
							<span class="prev">上一页</span><a href="javascript:;">上一页</a><span class="page">1\1页</span><a href="next">下一页</a><span class="next">下一页</span>
						</div> -->
					</div>
					<div class="select-img fr">
						<div class="popContent-head">
							待添加的文件（0）
						</div>
						<div class="popContent-body" >
							<ul class="popImgList" id="popImgList">
								
							</ul>
							<input type="hidden" id="pictureUrls">
							<input type="hidden" id="pictureNames">
						</div>
					</div>
				</div>
			</div><!--pop-body-->
		</div><!--popUpload-body-->
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/plugins/json2.js"></script>
		<script src="<%=rootPath%>/jsp/common/pop/js/uploadImg.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>