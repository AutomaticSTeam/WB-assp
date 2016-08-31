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
<title>模板管理欢迎页</title>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<jsp:include page="/jsp/common/commonJs.jsp" />
<jsp:include page="/jsp/common/templateTree.jsp" />
</head>
<body>
<!-- content begin -->
<div class="w800 fl " style="height:450px;margin-left:20px;"> 
		<div class="column">
			<h2 class="column-tit"><span class="tit">构建实例</span><span id="step1" class="line"></span></h2>
			<h2 class="column-tit"><span class="tit">关联资源</span><span id="step2"></span></h2>
			<h2 class="column-tit"><span class="tit">完善实例</span><span id="step3"></span></h2>
		</div><!--column-->
		<div id="templateDiv">
		<form id="templateForm" method="post" class="templateForm">
			<div class="main-content">
				<div class="form">
					<div class="form-item">
						<label><span style="color: red;">*</span>模板名称：</label>
						<div class="box-input">
							<input class="inputText" id="templateName" name="templateName" type="text" datatype="*1-50"  nullmsg="请填写模板名称"  errormsg="模板名称不能多于50个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>模板类型：</label>
						<div class="box-input">
							<select id="industryTypeId" name="industryTypeId" class="select"></select>
						</div>
					</div>
					<!-- <div class="form-item">
						<label>模板应用平台：</label>
						<div class="box-input">
							<select id="platformId" name="platformId" class="select"></select>
						</div>
					</div>
					<div class="form-item">
						<label>模板logo：</label>
						<div class="box-input">
							<select name="templateLogoId" id="templateLogoId" class="select">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="form-item">
						<label>模板banner：</label>
						<div class="box-input">
							<select name="templateBannerId" id="templateBannerId" class="select">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="form-item">
						<label>模板页脚图：</label>
						<div class="box-input">
							<select name="templateFooterId" id="templateFooterId" class="select"></select>
						</div>
					</div> -->
					<div class="form-item">
						<label>是否推荐：</label>
						<div class="box-input">
							<select name="isRecommended" class="select">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</div>
					</div>
					<div class="form-item">
						<label>模板大小：</label>
						<div class="box-input">
							<input class="inputText" type="text" id="templateSize" name="templateSize" ignore="ignore" datatype="n" errormsg="请输入数字"/>
						</div>
					</div>
					<div class="form-item">
						<label>模板宽度：</label>
						<div class="box-input">
							<input class="inputText" type="text" id="templateStandardWidth" name="templateStandardWidth" ignore="ignore" datatype="*1-20" errormsg="模板宽度不能多于20字符"/>
						</div>
					</div>
					<div class="form-item">
						<label>模板高度：</label>
						<div class="box-input">
							<input class="inputText" type="text" id="templateStandardHeight" name="templateStandardHeight" ignore="ignore" datatype="*1-20" errormsg="模板高度不能多于20字符"/>
						</div>
					</div>
					<div class="form-item">
						<label>是否有视频：</label>
						<div class="box-input">
							<select name="isContainsVedio" class="select">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</div>
					</div>
					<div class="form-item">
						<label>模板颜色：</label>
						<div class="box-input">
							<select name="colorId" id="colorId" class="select">
							</select>
						</div>
					</div>
					<div class="pop-btn">
						<a class="btn dib btn-green01 cancel-btn" id="clearInput">重置</a>
						<a class="btn dib btn-blue01 cave-btn" id="gotoStep">下一步</a>
					</div>
				</div>
			</div><!--main-content-->
		</form>	
		</div><!--templateDiv-->
		
		<div id="templateDiv2" style="display: none;">
		<form id="form2" method="post">
			<input type="hidden" name="templateId" id="templateId"/>
			<input type="hidden" name="templateCode" id="templateCode"/>
			<div class="form-item">
				<label>缩略图地址：</label>
				<div class="box-input">
					<input type="hidden" class="templateThumbnailUrl" name="templateThumbnailUrl"/>
					<img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px" onclick="uploadcpImg('templateThumbnailUrl',this)"/>
				</div>
			</div>
			<div class="form-item">
				<label>展示图地址：</label>
				<div class="box-input">
					<input type="hidden" class="templateViewUrl" name="templateViewUrl"/>
					<img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px" onclick="uploadcpImg('templateViewUrl',this)"/>
				</div>
			</div> 
			<div class="pop-btn">
				<a class="btn dib btn-green01 cancel-btn" id="clearInput">重置</a>
				<a class="btn dib btn-blue01 cave-btn" id="resourStep">下一步</a>
			</div>
		</form>	
		</div><!--templateDiv2-->
		
		<div id="templateDiv3" style="display: none;">
		<form id="form3" method="post">
			<input type="hidden" name="templateId" id="templateId2"/>
			<div class="pop-btn">
				<a class="btn dib btn-blue01 cave-btn" id="submitTemplate">提交</a>
			</div>
		</form>	
		</div><!--templateDiv3-->
</div><!-- content end -->
</body>
</html>