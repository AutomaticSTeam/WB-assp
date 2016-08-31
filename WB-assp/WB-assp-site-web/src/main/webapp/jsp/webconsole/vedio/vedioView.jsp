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
		<!--[if IE]>
		<script src=”http://html5shiv.googlecode.com/svn/trunk/html5.js”></script>
		< ![endif]-->
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		     var mediaId = '${media.mediaId}';
		</script>
         <script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
         <script src="<%=rootPath%>/js/dialog.js" type="text/javascript" charset="utf-8"></script>
         <script src="<%=rootPath%>/js/common/StringUtils.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/jsp/webconsole/vedio/js/vedioView.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/site_contents_manage.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
	</head>
	<body id="d">
		<div id="addArticles">
			<div class="column">
				<h2 class="column-tit"><span class="tit">编辑视频</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
			<form id="mediaForm" action="<%=rootPath%>/contents/media/addOrEditMedia.do" method="post" class="registerform">
			        <input type="hidden" id="mediaId" name="mediaId" value="${media.mediaId}"/>
			       <div class="form">
					<div class="form-item">
						<label>视频名称：</label>
						<div class="box-input">
							<input class="inputText" name="mediaName" type="text" value="${media.mediaName}" datatype="s1-50"  nullmsg="请填写视频名称！"  errormsg="视频名称不能多于50个字！"  /> 
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
							<input class="inputText"  name="mediaSummary" type="text"  value="${media.mediaSummary}" datatype="s0-50" placeholder="摘要请用逗号隔开"  errormsg="摘要不能多于50个字！" />
						</div>
					</div>
						<div class="form-item">
						<label>来&emsp;&emsp;源：</label>
						<div class="box-input">
							<input class="inputText" name="mediaSource"  type="text" value="${media.mediaSource}"  datatype="s0-225" errormsg="来源不能多于225个字！" />
						</div>
					</div>
						<div class="form-item">
						<label>视频简介：</label>
						<div class="box-input pb10">
							<textarea rows="5" cols="4"  name="mediaBriefing" class="inputText"   datatype="s1-200" errormsg="视频简介不能多于200个字！">${media.mediaBriefing}</textarea>
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
						<label>视频展示：</label>
						<div >
						     <input type="hidden" id="mediaUrl" name="mediaUrl"/>
						     <input type="hidden" id="mediaSize" name="mediaSize"/>
						     <input type="hidden" id="mediaSuffix" name="mediaSuffix"/>
						     <video src="${media.mediaUrl}" controls="controls">
								您的浏览器不支持 video 标签。
								</video>
						</div>
					</div>
					<div class="form-item form-pic po-a">
						<label>视频附图：</label>
						<div class="box-input">
							<div class="thumb">
							    <input type="hidden" id="mediaAttachmentImg"  name="mediaAttachmentImg" value="${media.mediaAttachmentImg}"/>
								<c:if test="${media.mediaAttachmentImg == null}">
								    <img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px"  id="thumbImgId"/>
								</c:if>
								<c:if test="${media.mediaAttachmentImg != null}">
								    <img src="${media.mediaAttachmentImg}"  width="185px" height="115px"  id="thumbImgId"/>
								</c:if>
							</div>
						</div>
					</div>
				</div><!--form-->
			</form>
				
			</div><!--main-content-->
		</div><!--managementArticles-->
		
	</body>
</html>