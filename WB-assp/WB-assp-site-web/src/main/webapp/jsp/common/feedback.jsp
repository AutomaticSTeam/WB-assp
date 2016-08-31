<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String rootPath = request.getContextPath();%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>用回反馈</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		</script>
		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
	</head>
	<body id="d">
	<form id="feedbackForm" action="<%= rootPath%>/sys/user/insertfeedback.do" method="post" class="registerform">
		<input type="hidden" id="feedbackId" name="feedbackId" value=""/>
		<div id="addfeedback">
			<label>您的姓名：</label>
			<input class="inputText" name="feedbackName" type="text" value="" datatype="*1-50"  nullmsg="请输入您的姓名!"  errormsg="不能多于20个字!" />
			<label>企业名称：</label>
			<input class="inputText" name="feedbackBusiness" type="text" value="" datatype="*1-50"  nullmsg="请输入公司名称!"  errormsg="不能多于30个字!" />
			<label>您的电话：</label>
			<input class="inputText" name="feedbackPhone" type="text" value="" datatype="*1-50"  nullmsg="请输入您的电话!"  errormsg="请正确输入您的电话!" />
			<label>邮箱：</label>
			<input class="inputText" name="feedback_email" type="text" value="" datatype="*1-50"  nullmsg="请填写您的Email!"  errormsg="请填写正确的邮箱!" />
			
			<div class="box-input">
			<label>建议：</label>
				<textarea rows="5" cols="4"  name="feedbackSuggest" class="inputText" datatype="*1-1000" errormsg="文章简介不能多于1000个字!"></textarea>
			</div>
		</div><!--managementArticles-->	
		<button class="" id="saveBtn">保存</button>
		<button class="" id="cancleBtn" href="javascript:window.history.go(-1);">取消</button>
	</form>
	</body>
</html>