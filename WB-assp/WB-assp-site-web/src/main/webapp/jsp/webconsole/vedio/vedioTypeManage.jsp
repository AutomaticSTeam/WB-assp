<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<script type="text/javascript"> var rootPath = '<%= rootPath%>';</script>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.core-3.5.min.js"></script>
		<script src="<%=rootPath%>/js/bootstrap.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/treeTable/jquery.treeTable.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/treeTable/tree.table.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/dialog.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/site_contents_manage.js" type="text/javascript" charset="utf-8"></script>
	   <script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/jsp/webconsole/vedio/js/vedioTypeManage.js" type="text/javascript" charset="utf-8"></script>
		<link href="<%=rootPath%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/ztree/zTreeStyle/zTreeStyle.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
	</head>
	<body>
	<div  id="main-content" class="mainCont">
		<div id="managementArticlesClass">
			<div class="column">
				<h2 class="column-tit"><span class="tit">管理视频分类</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
				<div class="main-head">
					<a href="javascript:;" class="btn btn-border-gray border-gray-new dib addArticleClassBtn" data-name="添加视频分类" id="addMediaTypeBtn">添加视频分类</a>
				</div><!--main-head-->
				<div class="main-body">
					<div class="table">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th class="tl pl40">视频分类</th>
								<th class="w180">操作</th>
							</tr>
							<tbody id="mediaGrid">
							
							</tbody>
						</table>
					</div><!--table-->
					<!-- <div class="pop-btn">
						<a class="btn dib btn-blue01 cave-btn">保存</a>
						<a class="btn dib btn-green01 cancel-btn">取消</a>
					</div> -->
				</div><!--main-body-->
			</div><!--main-content-->
		</div><!--managementArticles-->
		</div>
	</body>
	
	<div id="mediaTypeDiv"  class="oneEditPop" style="display:none">
	     	<div class="pop-form m-a">
					<div class="popForm-item">
						<label>分类名称：</label>
						<div class="label-con">
							<input class="inputText" type="text" name="mediaTypeName"  id="mediaTypeName" />
						</div>
					</div>
					<div class="popForm-item">
						<label>所属类别：</label>
						<div class="label-con">
						<input class="inputText" type="hidden" name="mediaTypePid"  id="mediaTypePid" value="0"/>
						<input class="inputText" type="text" name="mediaTypePid_0"  id="mediaTypePid_0" readonly="readonly" onclick="$formatToZtree.selecteTree('${pageContext.request.contextPath }','00',0,null,null,null,ContentsManage.mediaTypeToZtree,false)"/>
						</div>
					</div>
				</div>
	</div>
</html>