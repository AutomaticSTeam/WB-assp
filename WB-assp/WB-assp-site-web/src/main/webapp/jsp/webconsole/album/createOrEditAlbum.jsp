<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
         <script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
         <script src="<%=rootPath%>/js/dialog.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/static/artTemplate/artTemplate.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		</script>
		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
	</head>
	<body>
		<div id="addAtlas">
			<div class="column">
				<h2 class="column-tit"><span class="tit">创建图册</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
				<div class="focusPop" id="popPhoto">
					<div class="pop-body">
						<div class="popTabContent-item popTabContent-01">
							<form id="albumForm"  method="post" class="albumForm">
								<div class="pop-form">
									<div class="popForm-item">
										<label>图册名称：</label>
										<div class="label-con">
											<input class="inputText" type="text" datatype="*1-100"  nullmsg="图册名称不能为空！" id="albumName"/>
											<a class="btn dib btn-border-gray addImgBtn">添加图片</a>
										</div>
									</div>
									<div class="popForm-item">
										<div class="focus-pic">
											<ul class="popImgList" style="min-height: 68px;">
												
											</ul>
										</div>
									</div>
									<div class="focus-tabContent" id="pic_content" style="display:none;">
										<input type="hidden" id="nowLi">
										<div class="popForm-item">
											<label>图片名称：</label>
											<div class="label-con">
												<input class="inputText" type="text"  id="pitureName" datatype="*1-30" nullmsg="图片名称不能为空!" />
											</div>
										</div>
										<div class="popForm-item">
											<label>基本描述：</label>
											<div class="label-con">
												<textarea class="textarea w100p" name="" id="pBriefDesc" datatype="*0-100" errormsg="来源不能多于100个字！" ></textarea>
											</div>
										</div>
										<!-- <div class="popForm-item">
											<label>跳转方式：</label>
											<div class="label-con">
												<div class="skip-radio">
													<span class="radio"><input type="radio" name="skip" id="" />默认</span>
													<span class="radio"><input type="radio" name="skip" id="" />自定义</span>
													<span class="radio"><input type="radio" name="skip" id="" />不跳转</span>
												</div>
											</div>
										</div> -->
										<div class="popForm-item">
											<label>链接地址：</label>
											<div class="label-con">
												<input class="inputText" type="text" id="pUrl" style="width: 100%;"/>
												<!-- <a href="javacript:;" class="btn dib btn-border-gray">设置链接</a> -->
											</div>
										</div>
										<div class="popForm-item">
											<label>类别：</label>
											<div class="label-con">
												<div class="checkbox-list" id="queryAlbumCKBoxType">
												</div>
											</div>
										</div> 
										<div class="popForm-item">
											<label>详细描述：</label>
											<div class="label-con">
												<div class="editor-box">
													<script id="editor" name="pDetailDesc" vtype="text/plain"></script>
													<span class="Validform_checktip Validform_right" id="ueditor_Valid" style="display:none;">通过信息验证！</span>
												</div>
											</div>
										</div>
									</div><!--focus-tabContent-->
									<div class="pop-btn">
										<a class="btn dib btn-blue01 cave-btn" id="save_btn_pic">保存</a>
										<a class="btn dib btn-green01 cancel-btn" id="cancel_btn_pic">取消</a>
									</div>
								</div><!--popForm-->
							</form>
						</div><!--popTabContent-->
					</div><!--pop-body-->
				</div><!--focusPop-->
			</div><!--main-content-->
		</div><!--addAtlas-->
		
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.config.js"></script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.all.js"> </script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/jsp/webconsole/album/js/query_album_type.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/jsp/webconsole/album/js/createOrEditAlbum.js" type="text/javascript" charset="utf-8"></script>
		<script id="picLis" type="text/html">
         {{each picVos as picVo i}}
             <li data-tab="{{i+"--"+picVo.dataStatus}}" id="{{picVo.pictureId}}">
				<div class="img">
					<img src="{{picVo.pictureUrl}}" alt="{{picVo.pictureName+"."+picVo.imgPostfix}}" title="{{picVo.pictureName+"."+picVo.imgPostfix}}">
					<div class="move-img"><a href="javascript:;" class="move-left"></a><a href="javascript:;" class="move-right"></a></div>
					<div class="del-img"><a href="javascript:;"></a></div>
				</div>
			</li>
        {{/each}}
   </script>
	</body>
</html>