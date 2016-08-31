<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath();%>

 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>弹窗框</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>   
		<script src="<%=rootPath%>/js/template_manage.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
	</head>
	<body>
	<form id="ArticleType" method="post">
		<div class="oneEditPop">
			<div class="pop-body">
				<div class="pop-form m-a">
					<div class="popForm-item">
						<label>分类名称：</label>
						<div class="label-con">
							<input class="inputText" type="text" name="articleTypeName"/>
						</div>
					</div>
				</div><!--pop-form-->
			</div>
		</div><!--oneEditPop-->
	</form>
	</body>
</html>