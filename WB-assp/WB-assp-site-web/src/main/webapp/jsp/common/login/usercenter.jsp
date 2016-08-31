<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String rootPath = request.getContextPath();%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>用户中心</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	      <script type="text/javascript">
		    	var rootPath = '<%= rootPath%>';
		</script>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<script type="text/javascript" src="<%=rootPath%>/static/plugins/json2.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/js/common/ValidateUtils.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/jsp/common/login/js/updusercenter.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/jsp/common/login/js/welcome.js"></script>
 		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/login.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/common.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/welcome.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
     <style type="text/css">
        .list1-search span{display: inline-block; width:66px;}
        .list1-search span:last-child{display: inline-block; width:85px;}
     </style>
	</head>
	<body>
		<jsp:include page="/jsp/common/login/header.jsp"></jsp:include>
			<div class="main">
					<div class="list1">
						<p class="list1-nav">账号信息</p>
						<p class="list1-search">
							<span>用户名:</span>
							 <input type="text" id="phone" value="${usercenterlist.userName}" readonly="readonly" disabled="disabled"/>
						</p>
						<p class="list1-search">
							<span>密码:</span>
							 <input type="password" id="password" value="123456" readonly="readonly" disabled="disabled"/>
						</p>
						<p class="btnPassword " id="modifyPawBtn">修改密码</p>
					</div>
					<form class="usercenter-form" method="post" id="updateUserCenterId">
					<input type="hidden" name="userId" id="userId" value="${usercenterlist.userId}" />
					<div class="list1">
						<p class="list1-nav">账号信息</p>
						<p class="list1-search">
							<span>姓&emsp;名:</span>
							<input type="text" name="realName" id="realName" value="${usercenterlist.realName}" 
							 placeholder="请输入姓名" datatype="s2-4" errormsg="请输入正确姓名！"/>
						</p>
						<p class="list1-search">
							<span>昵&emsp;称:</span>
							<input type="text" name="nickName" id="nickName" value="${usercenterlist.nickName}"
							 placeholder="请输入昵称" datatype="s2-8" errormsg="请输入2-8位昵称！"/>
						</p>
						<p class="list1-search">
							<span>性&emsp;别:</span>
							<input  style="width:20px; height: auto;"  type="radio" name="sex" id="sex" value= "0" ${usercenterlist.sex=='0'?"checked":""}/>男
							<input   style="width:20px; height: auto;" type="radio" name="sex" id="sex" value= "1" ${usercenterlist.sex=='1'?"checked":""}/>女
						</p>
						<p class="list1-search">
							<span>身份证号:</span>
							<input type="text" name="identifyId" id="identifyId" value="${usercenterlist.identifyId}" />
						</p>
						<p class="list1-search">
							<span>手机号:</span>
							  <input type="text" value="${usercenterlist.phone}" readonly="readonly" disabled="disabled"/>
						</p>
						<p class="list1-search">
							<span>公司名:</span>
							<input type="text" name="company" id="company" value="${usercenterlist.company}" 
							placeholder="请输入公司名" datatype="s8-15" errormsg="请输入8-15位公司名！"
							/>
						</p>
						<p class="list1-search">
							<span>电子邮箱:</span>
							<input type="text" name="email" id="email" value="${usercenterlist.email}" 
							placeholder="请输入电子邮箱" datatype="e" errormsg="请输入正确的邮箱格式！"/>
						</p>
						<p class="list1-search">
							<span>地&emsp;址:</span>
							<input type="text" name="address" id="address" value="${usercenterlist.address}" 
							placeholder="请输入地址" datatype="s10-15" errormsg="请输入10-15地址信息！"
							/>
						</p>
						<p class="btnPassword btnclick1" id="usercenter">更新个人资料</p>
					</div>
					</form>
			</div>
			<div class="footer">
				<p>北京网视通联科技股份有限公司</p>
				<p>地址：北京市海淀区知春路京仪孵化器D座3层</p>
				<p>Copyright © 2004-2014 CCHVC云通-CDN全业务平台版权所有 京ICP备14002150号-1</p>
				<p>京ICP备14002150号-1 京公网安备1101080213237号</p>
			</div><!--底部-->
		</div><!--外部容器-->
		<!-- 修改密码框 -->
		<div id="modifyPawDiv" class="oneEditPop" style="display:none">
		   <form id="modifyPawId" method="post" class="modifyPaw-form">
			<div class="pop-form m-a">
					<div class="popForm-item">
						<label>当前密码：</label>
						<div class="label-con">
							<input class="inputText"  type="password" name="password"  id="password" 
							placeholder="" datatype="s6-15" errormsg="请输入6-15位新密码！" nullmsg="新密码不为空"/>
						</div>
					</div>
					<div class="popForm-item">
						<label>&nbsp;&nbsp;&nbsp;新密码：</label>
						<div class="label-con">
						      <input class="inputText"  name="newPaw"  id="newPaw"  type="password" 
						      placeholder="请输入新密码" datatype="s6-15" errormsg="请输入6-15位新密码！" nullmsg="新密码不为空"/>
						</div>
					</div>
						<div class="popForm-item">
						<label>确认密码：</label>
						<div class="label-con">
						      <input class="inputText" name="confirmPaw"  id="confirmPaw"  type="password"
						      placeholder="请再次输入新密码" datatype="s6-15" errormsg="请再次输入6-15位新密码！" nullmsg="新密码不为空"/>
						</div>
					</div>
				</div>
		</form>
		</div>
			<script>
			var modifyPawForm = $("#modifyPawId").Validform({
			 	tiptype:3});
			
			var updateUserCenterForm=$("#updateUserCenterId").Validform({
				tiptype:3
			});
			</script>
	</body>
</html>