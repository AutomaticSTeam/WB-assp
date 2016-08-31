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
<title>模块管理</title>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<jsp:include page="/jsp/common/commonJs.jsp" /> 
<script type="text/javascript" src="<%=rootPath%>/js/frame/managerFrames.js"></script> 

<div id="managementmoduleTmpls">
			<div class="main-content" style="margin: 0 39px 0; margin-top:25px;" >
				<div class="main-body">
				 <form action="" method="post" id="moduleTmplForm">
					<input type="hidden" id="currentPage" name="page" value="1"/>
				    <input type="hidden" name="rows" value="9"/>
					<div class="search">
						<div class="search-item">
							<label>标题搜索：</label>
							<div class="box-input">
								<input class="inputText w180" name="moduleTmplTitle"  id="moduleTmplTitle" type="text" />
							</div>
						</div>
						<div class="" style="float: right;">
							<div class="btn-item">
								<a href="<%= rootPath%>/jsp/frame/addFrames.jsp;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="module-add">新增</a>
								<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="module-edit">编辑</a>
								<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="module-del">删除</a>
								<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="module-preview">预览</a>
								<!-- <a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn" id="module-joinDate">关联数据</a> -->
							</div>
						</div>
					</div><!--search-->
					</form>
					<div class="table" id="module-table"></div><!--table-->
				</div><!--main-body-->
			</div><!--main-content-->
		</div><!--managementmoduleTmpls-->	
</body>

</html>