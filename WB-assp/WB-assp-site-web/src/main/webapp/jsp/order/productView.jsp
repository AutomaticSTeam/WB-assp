<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String rootPath = request.getContextPath();%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		</script>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		 <script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/dialog.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/common/StringUtils.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/static/Huploadify/Huploadify.css"/>
		<script type="text/javascript" src="<%=rootPath%>/static/Huploadify/jquery.Huploadify.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/plugins/json2.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.config.js"></script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.all.min.js"> </script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<!-- js -->
 		<script type="text/javascript" src="<%=rootPath%>/jsp/order/js/productView.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/jsp/order/js/jquery.range.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/jsp/order/js/jquery.form.js"></script>
 		<!-- js -->
 		<link rel="stylesheet" href="<%=rootPath%>/jsp/order/js/jquery.range.css" type="text/css" media="all" />
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
	</head>
	<body id="d">
	<form id="articleForm" method="post" class="registerform">
		<div id="addArticles">
			<div class="column">
				<h2 class="column-tit"><span class="tit">客户信息</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
				<div class="form">
					<div class="form-item">
						<label>客户名称：</label>
						<div class="box-input">
							<input class="inputText" name="userName" type="text" value="" datatype="*1-50"  nullmsg="请填写客户姓名"  errormsg="客户姓名不能多于20个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>客户地址：</label>
						<div class="box-input">
							<select class="select" name="province">
							</select>
						</div>
						<div class="box-input">
							<select class="select" name="city">
							</select>
						</div>
						<div class="box-input">
							<select class="select" name="area">
							</select>
						</div>
						<label>详细地址：</label>
						<div class="box-input">
							<input class="inputText" name="userAddress" type="text" value="" datatype="*1-50"  nullmsg="请填写详细地址！"  errormsg="详细地址不能多于20个字！" />
						</div>
					</div>
					<div class="form-item">
						<label>联系电话：</label>
						<div class="box-input">
							<input class="inputText" name="userPhone" type="text" value="" datatype="m"  nullmsg="请填写手机号码！"  errormsg="手机号码格式不正确！" />
						</div>
					</div>
					<div class="form-item">
						<label>域名：</label>
						<div class="box-input">
							<input type="hidden" name="userDomainName" value="www.baidu.com" />
							www.baidu.com
						</div>
					</div>
				</div><!--form-->
			</div><!--main-content-->
			<div class="column">
				<h2 class="column-tit"><span class="tit">租用时长</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
				<div class="form">
					<div class="form-item">
						<div class="box-input" id="proTime" style="width:500px">
							<input type="hidden" class="proTime" value="1">
							<input type="text" style="margin-left:330px;width:30px;" name="rentedTime" readonly="readonly" value="1">月
							<input type="hidden" name="rentedUnit" value="月">
							<input type="hidden" name="proTimePrice" value="100">
						</div>
					</div>
				</div><!--form-->
			</div><!--main-content-->
			<c:forEach items="${proList}" var="pro">
				<c:if test="${pro.productType=='1'}">
					<div class="column">
						<h2 class="column-tit"><span class="tit">${pro.productName}</span><span class="line"></span></h2>
					</div><!--column-->
					<div class="main-content">
						<div class="form">
							<div class="form-item">
								<div class="box-input" style="width:500px">
									<input type="hidden" name="tmplName" value="${temp.templateName}" />
									<input type="hidden" name="tmplId" value="${temp.templateId}"  />
									${temp.templateName}
								</div>
							</div>
						</div><!--form-->
					</div><!--main-content-->
				</c:if>
				<c:if test="${pro.productType=='2'}">
					<div class="column">
						<h2 class="column-tit"><span class="tit">${pro.productName}</span><span class="line"></span></h2>
					</div><!--column-->
					<div class="main-content">
						<div class="form">
							<label>系统建议空间大小：${pro.productInitValue}</label>
							<div class="form-item">
								<div class="box-input" id="proSpace" style="width:600px;margin-top:40px;">
									<input type="hidden" class="proSpace" value="20">
									<input type="text" style="margin-left:330px;width:30px;" name="spaceSize" readonly="readonly" value="${pro.productInitValue}">M
									<input type="hidden" name="spacePrice" value="200">
								</div>
							</div>
							<label>系统建议空间大小：${pro.productInitValue}</label>
						</div><!--form-->
					</div><!--main-content-->
				</c:if>
				<c:if test="${pro.productType=='3'}">
					<div class="column">
						<h2 class="column-tit"><span class="tit">${pro.productName}</span><span class="line"></span></h2>
					</div><!--column-->
					<div class="main-content">
						<div class="form">
							<div class="form-item">
								<div class="box-input" style="width:700px">
									<input type="radio" name="dkType" value="1" checked="checked">共享
									<input type="radio" name="dkType" value="2">独享
								</div>
								<div class="box-input" id="dkSpace" style="width:700px;margin-top:30px;margin-bottom:30px;">
									<input type="hidden" class="dkSpace" value="20">
									<input type="text" style="margin-left:330px;width:30px;" name="dkInfo" readonly="readonly" value="${pro.productInitValue}">M
									<input type="hidden" name="dkSpacePrice" value="105">
								</div>
								<div class="box-input" id="dkSpace" style="width:700px">
									运营商：
									<select class="select" name="dkOperators">
									 	<option data-price="111" value="电信/联通双线">电信/联通双线</option>
									 	<option data-price="222" value="联通">联通</option>
									 	<option data-price="333" value="移动">移动</option>
									 	<option data-price="444" value="电信">电信</option>
									 	<option data-price="555" value="其他">其他</option>
									</select>
								</div>
							</div>
						</div><!--form-->
					</div><!--main-content-->
				</c:if>
			</c:forEach>
			<div class="main-content">
				<div class="form">
					<div class="form-item" style="width:300px;">
						<font size="+1">产品价格：<span id="price">300</span>￥</font>
						<input type="hidden" name="orderPrice" value="300" />
					</div>
				</div><!--form-->
			</div><!--main-content-->
			<div class="pop-btn">
				<a class="btn dib btn-blue01 cave-btn" id="saveBtn">保存</a>
				<a class="btn dib btn-green01 cancel-btn" id="cancleBtn" href="javascript:window.history.go(-1);">取消</a>
			</div>
		</div><!--managementArticles-->	
	</form>
	</body>
<script>
	var form1=$("#articleForm").Validform({
		tiptype:3
	});
</script>
</html>