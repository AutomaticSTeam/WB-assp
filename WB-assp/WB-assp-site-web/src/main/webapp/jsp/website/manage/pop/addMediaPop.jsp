<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
        String rootPath = request.getContextPath();
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>视频编辑</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<script type="text/javascript">
		     var rootPath = '<%= rootPath%>';
		     var mediaId = '${media.mediaId}';
		     var top = window.parent;
		     var moduleId= top.SiteManager.tempData.dataUrl;
		</script>
         <script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
         <script src="<%=rootPath%>/js/dialog.js" type="text/javascript" charset="utf-8"></script> 
		<script type="text/javascript" src="<%=rootPath%>/static/plugins/json2.js"></script>
		<script src="<%=rootPath%>/js/common/StringUtils.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/site_contents_manage.js" type="text/javascript" charset="utf-8"></script>
         <script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
		<script src="<%=rootPath%>/jsp/webconsole/vedio/js/createOrEditVedio.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/plupload-2.1.9/js/plupload.full.min.js"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
		<link rel="stylesheet" href="<%=rootPath%>/jsp/webconsole/vedio/css/createOrEditVedio.css" />
		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
	</head>
	<body id="d">
		<div id="addArticles">
			<div class="column">
				<h2 class="column-tit"><span class="tit">编辑视频</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
			<form id="mediasForm" action="<%=rootPath%>/site/addOrEditMedia.do" method="post" class="registerform">
			        <input type="hidden" id="mediaId" name="mediaId" value="${media.mediaId}"/>
			       <div class="form">
					<div class="form-item">
						<label>视频名称：</label>
						<div class="box-input">
							<input class="inputText" name="mediaName" type="text" value="${media.mediaName}" datatype="*1-50"  nullmsg="请填写视频名称！"  errormsg="视频名称不能多于50个字！"  /> 
						</div>
					</div>
					<div class="form-item">
						<label>作&emsp;&emsp;者：</label>
						<div class="box-input">
							<input class="inputText" name="mediaAuthor"  type="text" value="${media.mediaAuthor}"  datatype="s0-30" errormsg="作者不能多于30个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>摘&emsp;&emsp;要：</label>
						<div class="box-input">
							<input class="inputText"  name="mediaSummary" type="text"  value="${media.mediaSummary}" datatype="*0-50" placeholder="摘要请用逗号隔开"  errormsg="摘要不能多于50个字！" />
						</div>
					</div>
						<div class="form-item">
						<label>来&emsp;&emsp;源：</label>
						<div class="box-input">
							<input class="inputText" name="mediaSource"  type="text" value="${media.mediaSource}"  datatype="*0-225" errormsg="来源不能多于225个字！" />
						</div>
					</div>
						<div class="form-item">
						<label>视频简介：</label>
						<div class="box-input pb10">
							<textarea rows="5" cols="4"  name="mediaBriefing" class="inputText"   datatype="*1-200" errormsg="视频简介不能多于200个字！">${media.mediaBriefing}</textarea>
						</div>
					</div>
					<div class="form-item">
						<label>目标位置：</label>
						<div class="box-input">
							<select name="" class="select">
								<option value=""></option>
							</select>
							<div class="checkbox-list" id="mediaCkBoxList">
						   </div>
						</div>
					</div>
						<div class="form-item">
						<label>视频上传：</label>
						<div >
						     <input type="hidden" id="mediaUrl" name="mediaUrl" value="${media.mediaUrl }"/>
						     <input type="hidden" id="mediaSize" name="mediaSize" value="${media.mediaSize }"/>
						     <input type="hidden" id="mediaSuffix" name="mediaSuffix" value="${media.mediaSuffix }"/>
						       <div  id="uploadMedia">
						       		<a class="btn dib btn-blue01 " id="chooseMedia">选择视频</a>
						       		<div  id="uploadMedia_content">
						       		</div>
						       		<div id="filelist">
						       		</div>
						       		<pre id="console"></pre>
						       </div>
						       <a class="btn dib btn-blue01 " style="margin-left: 76px;margin-top: 5px;" id="startUpload">开始上传</a>
						       <a class="btn dib btn-blue01 " id="canselUpload">取消上传</a>
						</div>
					</div>
					<div class="form-item form-pic po-a">
						<label>视频附图：</label>
						<div class="box-input">
							<a class="btn btn-border-gray dib addImgBtn"  id="addImgBtn" <c:if test="${media.mediaAttachmentImg != null}"> style="display:none;" </c:if> >添加图片</a>
							<div class="thumb">
							    <input type="hidden" id="mediaAttachmentImg"  name="mediaAttachmentImg" value="${media.mediaAttachmentImg}"/>
								<c:if  test="${media.mediaAttachmentImg == null}">
								    <img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px"  id="thumbImgId"/>
								</c:if>
								<c:if test="${media.mediaAttachmentImg != null}">
								    <img src="${media.mediaAttachmentImg}"  width="185px" height="115px"  id="thumbImgId"/>
								</c:if>
								<span class="del" id="delImage">删除</span>
							</div>
						</div>
					</div>
					<div class="pop-btn">
						<a class="btn dib btn-blue01 cave-btn" id="saveBtns">保存</a>
						<a class="btn dib btn-green01 cancel-btn" id="cancleBtns">取消</a>
					</div>
				</div><!--form-->
			</form>
			<%-- <input type="hidden" id="templateCode" value="${siteTempalte.templateCode}"> --%>
			<input type="hidden" id="siteCode" value="${website.siteCode}">	
			</div><!--main-content-->
		</div><!--managementArticles-->
		
	</body>
</html>