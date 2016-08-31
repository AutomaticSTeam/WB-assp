<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>
<body id="d">
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<jsp:include page="/jsp/common/commonJs.jsp" />
<script type="text/javascript" src="<%=rootPath%>/js/module/addModule.js"></script> 
	<form id="moduleTmplForm"   class="moduleTmplForm">
		<div id="addmoduleTmpls" style="margin-top:25px;">
			<div class="main-content" >
				<div class="form">
					<div class="form-item">
					<input type="hidden" id="moduleTmlId"  name="moduleTmlId" value="${reasult.moduleTmlId}"/>
						<label>模块名称：</label>
						<div class="box-input">
							<input class="inputText" name="moduleTmlName" id="moduleTmlName" type="text" value="${reasult.moduleTmlName}" datatype="*1-50"  nullmsg="请填写模块名称！"  errormsg="标题名称不能多于50个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>关键字：&nbsp</label>
						<div class="box-input">
							<input class="inputText" name="moduleTmlKey"  id="moduleTmlKey" type="text" value="${reasult.moduleTmlKey}" datatype="s1-50"  nullmsg="请填写关键字！"  errormsg="" />
						</div>
					</div>
					<div class="form-item form-pic po-a">
						<label>模块附图：</label>
						<div class="box-input">
							<div class="thumb">
							    <input type="hidden" id="moduleTmplAttachmentImg"  name="moduleTmlAttachmentImg" value="${reasult.moduleTmlAttachmentImg}"/>
							    <c:if  test="${reasult.moduleTmlAttachmentImg == null}">
								     <img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px "  id="thumbImgId"/>
									 <span class="del btn dib btn-blue01" id="delImage" style="display:none;">删除</span>
								</c:if>
								<c:if test="${reasult.moduleTmlAttachmentImg != null}">
								    <img src="${reasult.moduleTmlAttachmentImg}"  width="185px" height="115px "  id="thumbImgId"/>
									<span class="del btn dib btn-blue01" id="delImage" >删除</span>
								</c:if>
								
							</div>
						</div>
					</div>
					 <div class="editor-box form-item" style="height:auto;width:100%;'">
					 	<label>模块内容：</label>
						<textarea id="editor" name="moduleTmlContent" style="width:100%;height:150px;">${reasult.moduleTmlContent}</textarea>
					</div>
					<div class="pop-btn">
						<a class="btn dib btn-blue01 cave-btn" id="saveBtn">保存</a>
						<a class="btn dib btn-green01 cancel-btn" id="cancleBtn" href="javascript:window.history.go(-1);">取消</a>
					</div>
				</div><!--form-->
			</div><!--main-content-->
		</div><!--managementmoduleTmpls-->	
	</form>
</body>
</html>