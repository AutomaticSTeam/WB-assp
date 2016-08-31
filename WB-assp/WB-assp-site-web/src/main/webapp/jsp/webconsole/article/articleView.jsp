<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	String rootPath = request.getContextPath();
	
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>文章编辑</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<script type="text/javascript">
		var articleId = '${article.articleId}';
		    var rootPath = '<%= rootPath%>';
		</script>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		 <script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
		 <script src="<%=rootPath%>/js/common/StringUtils.js" type="text/javascript" charset="utf-8"></script>
		 <script src="<%=rootPath%>/js/dialog.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/jsp/webconsole/article/js/query_article_type.js" type="text/javascript" charset="utf-8"></script>
		<!-- <script src="<%=rootPath%>/jsp/webconsole/article/js/addArticle.js" type="text/javascript" charset="utf-8"></script> -->
		<script src="<%=rootPath%>/jsp/webconsole/article/js/articleView.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/static/Huploadify/Huploadify.css"/>
		<script type="text/javascript" src="<%=rootPath%>/static/Huploadify/jquery.Huploadify.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/plugins/json2.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.config.js"></script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.all.min.js"> </script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
	</head>
	<body id="d">
	<form id="articleForm" action="<%= rootPath%>/contents/article/addArticle.do" method="post" class="registerform">
		<input type="hidden" id="mediaId" name="articleId" value="${article.articleId}"/>
		<div id="addArticles">
			<div class="column">
				<h2 class="column-tit"><span class="tit">添加文章</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
				<div class="form">
					<div class="form-item">
						<label>文章标题：</label>
						<div class="box-input">
							<input class="inputText" name="articleTitle" type="text" value="${article.articleTitle}" datatype="s1-30"  nullmsg="请填写问题名称！"  errormsg="标题名称不能多于30个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>作&emsp;&emsp;者：</label>
						<div class="box-input">
							<input class="inputText" name="articleAuthor" type="text" value="${article.articleAuthor}" datatype="s1-30"  nullmsg="请填写作者名称！"  errormsg="作者名称不能多于30个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>来&emsp;&emsp;源：</label>
						<div class="box-input">
							<input class="inputText" name="articleSource" type="text" value="${article.articleSource}" datatype="s1-50"  nullmsg="请填写文章来源！"  errormsg="文章来源不能多于50个字！" />
						</div>
					</div>
					<!-- <div class="form-item">
						<label>时&emsp;&emsp;间：</label>
						<div class="box-input">
							<select name="createTime" class="select">
								<option value=""></option>
							</select>
						</div>
					</div> -->
					<div class="form-item">
						<label>目标位置：</label>
						<div class="box-input">
							<select name="" class="select">
								<option value=""></option>
							</select>
							<div class="checkbox-list" id="queryArticleCKBoxType">
							</div>
						</div>
					</div>
					<div class="form-item">
						<label>文章简介：</label>
						<div class="box-input">
							<textarea rows="5" cols="4"  name="articleNtro" class="inputText"  datatype="s1-200" errormsg="文章简介不能多于100个字！">${article.articleNtro}</textarea>
						</div>
					</div>
					<div class="form-item">
						<label>文章摘要：</label>
						<div class="box-input">
							<input class="inputText" name="articleBrief" type="text" value="${article.articleBrief}" datatype="s1-100"  nullmsg="请填写文章摘要！"  errormsg="文章摘要不能多于100个字！" />
						</div>
					</div>
					<div class="form-item form-pic po-a">
						<label>文章附图：</label>
						<div class="box-input">
							<a class="btn btn-border-gray dib addImgBtn"  id="addImgBtn" <c:if test="${article.articleAttachmentImg != null}"> style="display:none;" </c:if> >添加图片</a>
							<div class="thumb">
							    <input type="hidden" id="articleAttachmentImg"  name="articleAttachmentImg" value="${article.articleAttachmentImg}"/>
								<c:if test="${article.articleAttachmentImg == null}">
								    <img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px"  id="thumbImgId"/>
								</c:if>
								<c:if test="${article.articleAttachmentImg != null}">
								    <img src="${article.articleAttachmentImg}"  width="185px" height="115px"  id="thumbImgId"/>
								</c:if>
								<!-- <span class="del" id="delImage">删除</span> -->
							</div>
						</div>
					</div>
					 <div class="editor-box">
						<textarea id="editor" name="articleContent" style="width:800px;height:150px;">${article.articleContent}</textarea>
						<input type="hidden" id="articleWordNum" name="articleWordNum" value=" "/>
					</div>
					<div class="pop-btn">
						<a class="btn dib btn-blue01 cave-btn" id="saveBtn">保存</a>
						<a class="btn dib btn-green01 cancel-btn" href="javascript:window.history.go(-1); id="cancleBtn">取消</a>
					</div>
				</div><!--form-->
			</div><!--main-content-->
		</div><!--managementArticles-->	
	</form>
	<!-- <script src="<%=rootPath%>/jsp/webconsole/article/js/backstage_article.js" type="text/javascript" charset="utf-8"></script> -->
	</body>
	 <script>
	var ue = UE.getEditor('editor');
	ue.ready(function() {
	    ue.setDisabled();
	});
	</script> 
</html>