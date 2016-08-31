<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
		<script src="<%=rootPath%>/jsp/webconsole/logo/js/logoManage.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		</script>
		<jsp:include page="/jsp/common/commonCssLink.jsp" />
		<jsp:include page="/jsp/common/commonJs.jsp" /> 
	</head>
	<body>
	<div class="w800 fl " style="height:450px;margin-left:20px;"> 
		<div class="column">
			<h2 class="column-tit"><span class="tit">创建logo</span><span class="line"></span></h2>
		</div><!--column-->
		<div id="logoDiv">
		<form id="logoForm" method="post" class="logoForm">
			<input type="hidden" name="templateLogoId" id="templateLogoId" value="${logo.templateLogoId }" />
			<div class="main-content">
				<div class="form">
					<div class="form-item">
						<label><span style="color: red;">*</span>logo名称：</label>
						<div class="box-input">
							<input class="inputText" id="logoTitle" name="logoTitle" value="${logo.logoTitle }" type="text" datatype="*1-50"  nullmsg="请填写logo名称"  errormsg="logo名称不能多于50个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>logo图片：</label>
						<div class="box-input">
							<input type="hidden" class="logoImgUrl" name="logoImgUrl" value="${logo.logoImgUrl }"/>
							<c:if test="${empty logo }">
								<img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px" onclick="uploadcpImg('logoImgUrl',this)"/>
							</c:if>
							<c:if test="${!empty logo }">
								<img src="${logo.logoImgUrl }"  width="185px" height="115px" onclick="uploadcpImg('logoImgUrl',this)"/>
							</c:if>
						</div>
					</div>
					<div class="form-item">
						<label>logo是否可见：</label>
						<div class="box-input">
							<select name="logoImgHide" class="select">
								<option value="0" ${logo.logoImgHide == 0 ? 'selected':''}>否</option>
								<option value="1" ${logo.logoImgHide == 1 ? 'selected':''}>是</option>
							</select>
						</div>
					</div>
					<div class="form-item">
						<label>主标题：</label>
						<div class="box-input">
							<input class="inputText" type="text" id="fisrtTitle" name="fisrtTitle" value="${logo.fisrtTitle }" ignore="ignore" datatype="*1-100" errormsg="主标题不能多于100字符"/>
						</div>
					</div>
					<div class="form-item">
						<label>二级标题：</label>
						<div class="box-input">
							<input class="inputText" type="text" id="secondTitle" name="secondTitle" value="${logo.secondTitle }" ignore="ignore" datatype="*1-100" errormsg="二级标题不能多于100字符"/>
						</div>
					</div>
					<div class="form-item">
						<label>logo链接地址：</label>
						<div class="box-input">
							<input class="inputText" type="text" id="logoImgHotLine" name="logoImgHotLine" value="${logo.logoImgHotLine }" ignore="ignore" datatype="*1-100" errormsg="链接地址不能多于100字符"/>
						</div>
					</div>
					<div class="form-item">
						<label>图片高：</label>
						<div class="box-input">
							<input class="inputText" type="text" id="imgHeight" name="imgHeight" value="${logo.imgHeight }" ignore="ignore" datatype="*1-50" errormsg="链接地址不能多于50字符"/>
						</div>
					</div>
					<div class="form-item">
						<label>图片宽：</label>
						<div class="box-input">
							<input class="inputText" type="text" id="imgWidth" name="imgWidth" value="${logo.imgWidth }" ignore="ignore" datatype="*1-50" errormsg="链接地址不能多于50字符"/>
						</div>
					</div>
					<div class="pop-btn">
						<a href="<%=rootPath %>/jsp/webconsole/logo/logoManage.jsp" class="btn dib btn-green01 cancel-btn">返回</a>
						<a class="btn dib btn-blue01 cave-btn" id="submitlogo">提交</a>
					</div>
				</div>
			</div><!--main-content-->
		</form>	
		</div><!--logoDiv-->
</div><!-- content end -->		
</body>
</html>