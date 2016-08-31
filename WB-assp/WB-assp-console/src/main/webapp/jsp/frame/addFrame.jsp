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
<script type="text/javascript" src="<%=rootPath%>/js/frame/addFrame.js"></script> 
	<form id="moduleTmplForm"   class="moduleTmplForm">
		<div id="addmoduleTmpls" style="margin-top:25px;">
			<div class="main-content" >
				<div class="form">
					<div class="form-item">
					<input type="hidden" id="frameId"  name="frameId" value="${reasult.frameId}"/>
						<label>板式名称：</label>
						<div class="box-input">
							<input class="inputText" name="frameName" id="frameName" type="text" value="${reasult.frameName}" datatype="*1-50"  nullmsg="请填写模块名称！"  errormsg="标题名称不能多于50个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>板式描述</label>
						<div class="box-input">
							<%-- <input class="inputText" name="frameDescribe"  id="frameDescribe" type="text" value="${reasult.frameDescribe}" datatype="s1-50"/> --%>
							<textarea id="editor" name="frameDesc" style="width:100%;height:150px;">${reasult.frameDesc}</textarea>
						</div>
					</div>
					<div class="form-item form-pic po-a">
						<label>板式附图：</label>
						<div class="box-input">
							<div class="thumb">
							    <input type="hidden" id="frameAttachmentImg"  name="frameAttachmentImg" value="${reasult.frameAttachmentImg}"/>
							    <c:if  test="${reasult.frameAttachmentImg == null}">
								     <img src="<%=rootPath%>/images/thumb.png"  width="185px" height="115px "  id="thumbImgId"/>
									 <span class="del btn dib btn-blue01" id="delImage" style="display:none;">删除</span>
								</c:if>
								<c:if test="${reasult.frameAttachmentImg != null}">
								    <img src="${reasult.frameAttachmentImg}"  width="185px" height="115px "  id="thumbImgId"/>
									<span class="del btn dib btn-blue01" id="delImage" >删除</span>
								</c:if>
								
							</div>
						</div>
					</div>
					<div class="pop-btn" style="margin-top:100px;">
						<a class="btn dib btn-blue01 cave-btn" id="saveBtn">保存</a>
						<a class="btn dib btn-green01 cancel-btn" id="cancleBtn" href="javascript:window.history.go(-1);">取消</a>
					</div>
				</div><!--form-->
			</div><!--main-content-->
		</div><!--managementmoduleTmpls-->	
	</form>
</body>
</html>