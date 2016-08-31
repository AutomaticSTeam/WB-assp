<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@ page import="java.lang.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>延安精神，中华魂，党史人物，优良传统，中国精神</title>
<script type="text/javascript">
    var rootPath = '<%= rootPath%>';
</script>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/footer.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/model.css" />
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>   
<script src="<%=rootPath%>/js/site.js" type="text/javascript"></script>
<script src="<%=rootPath%>/js/common/DateUtils.js" type="text/javascript"></script>      
<script src="<%=rootPath%>/js/site_frame_module.js" type="text/javascript"></script> 
<script src="<%= rootPath%>/static/plugins/jquery.tmpl.min.js" type="text/javascript"></script>
<!-- 建站操作css -->
	<link rel="stylesheet" href="<%=rootPath %>/css/pop.css" />
<!-- 建站操作css -->
<link rel="stylesheet" href="http://111.206.116.91/wms/template/10005/css/self.css" />

</head>
<body>
		
		<!--网站主题内容-->
		<div id="wrapper">
			
			<jsp:include page="/jsp/template/header.jsp"></jsp:include>
			
			
			<div class="main">
				<div class="w" id="DynamicModules">
				         <div class="breadcrumb">
						<a href="http://localhost:8080/DL-wms-web/template/loadTempLatePage.do?columnsId=1&templateId=1">首页</a> &gt; <b class="footer-info">单位介绍</b>
					</div>
					
					<!-- start -->
					<div class="model ">
						
						<!-- 左列start -->
						<div class="model-item ">
							<div class="model-body">
				                             <!--内容页部分start-->
								<div id="content">
							<c:forEach  items="${articles}" var="article"  varStatus="status">
								 <c:if test="${article.articleId == articleId }">
									<div class="content-head">
										<h1>${article.articleTitle}</h1>
										<!-- <div class="content-bar">
											<span class="time"><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${article.createTime}" /></span>
											<span class="source">来源： ${article.articleSource}</span>
										</div>
										 -->
									</div>
									<div class="content-body">
											<!-- 存放内容 -->
				<div class="content-text">
					<p>单位名称：中国延安精神研究会</p>
					<p>地址：北京市西城区西单北大街宏庙胡同9号中宇饭店4层 </p>
					<p>联系电话：010-83084374</p>
					<p>传真：010-63095735</p>
					<p>邮编：100032 </p>
					<p>银行户名：中国延安精神研究会</p>
					<p>开户银行：中国建设银行北京平安大街支行</p>
					<p>帐号：11001029300059261088</p>
					<p>邮箱：WHZ@1921.org.cn</p>
					<!-- <p>地址：北京市西城区裕民路4号9栋（圆山大酒店南侧） 邮编：100029</p> -->
					<br><br>
					<p>单位名称：北京《中华魂》网络信息中心</p>
					<p>地址：北京市西城区裕民路4号9栋（圆山大酒店南侧）</p>
					<p>联系电话：010-84887788</p>
					<p>传真：010-84887788</p>
					<p>邮编：100029</p>
					<p>银行户名：北京《中华魂》网络信息中心</p>
					<p>开户银行：广东发展银行北京东直门支行</p>
					<p>帐号：137111516010015450</p>
					<p>E-mail：MX@1921.org.cn</p>
					<p>微博地址：http://weibo.com/zhonghuahun1921</p>
					<p>微信公众号：zhonghuahunwang</p>
					<br><br>
					<p>单位名称：《中华魂》杂志社</p>
					<p>地址：北京市海淀区阳坊路光耀东方广场S座949室</p>
					<p>电话：010-63095735</p>
					<p>传真：010-63996801</p>
					<p>开户行：《中华魂》杂志社</p>
					<p>开户银行：中国工商银行北京西四支行</p>
					<p>账号：0200002809004640930</p>
					<br><br>
					<p>单位名称：北京弘扬延安精神基金会</p>
					<p>地址：北京市西城区西单北大街宏庙胡同9号中宇饭店4层 </p>
					<p>电话：010-52606407</p>
					<p>邮编：100032</p>
				</div>
									</div>
									 </c:if>
								</c:forEach>
									
								</div>
								<!--内容页部分end-->
							</div>
						</div>
						<!-- 左列end -->
						
						
					</div>
					<!-- 两列布局73分end -->
				</div>
			</div><!--main-->
			
			<jsp:include page="/jsp/template/footer.jsp"></jsp:include>
		</div><!--wrapper-->
		<!--网站主题内容-->
		
		<!--建站操作js-->
		<script src="<%=rootPath%>/js/template_manage.js" type="text/javascript"></script>   
		<!--建站操作js-->
</body>
</html>