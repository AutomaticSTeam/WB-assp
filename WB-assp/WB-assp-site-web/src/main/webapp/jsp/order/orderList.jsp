<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String rootPath = request.getContextPath();%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>自动建站欢迎页</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<script type="text/javascript">
		       var rootPath = '<%= rootPath%>';
		</script>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<script type="text/javascript" src="<%=rootPath%>/js/json2.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/jsp/common/login/js/welcome.js"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/common.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/welcome.css" />
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
	</head>
	<body>
	<div id="wrapper">
				<jsp:include page="/jsp/common/login/header.jsp"></jsp:include>
			
				<div class="orderMain">
			        <p class="orderTitle">个人订单</p>
			        <table class="tb oderTb">
			        		<tr class="ofirst">
				                <th>订单号</th>
				                <th>产品名称</th>
				                <th>价格</th>
				                <th>创建时间</th>
				                <th>订单状态</th>
				                <th>详情</th>
				            </tr>
			        	<c:forEach items="${orderList}" var="order">
							<tr>
								<td>${order.orderNo}</td>
								<td>${order.templateName}</td>
								<td>${order.orderPrice}</td>
								<td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${order.orderStatus==0?"<font color=red>未支付</font>":"<font color=green>已支付</font>"}</td>
								<td><a href="<%=rootPath%>/wms/order/getOrderByOrderId.do?orderId=${order.orderId}">查看详情</a></td>
							</tr>
						</c:forEach>
			        </table>
			    </div>
			
			    <div class="footer">
			        <p>北京网视通联科技股份有限公司</p>
			        <p>地址：北京市海淀区知春路京仪孵化器D座3层</p>
			        <p>Copyright © 2004-2014 CCHVC云通-CDN全业务平台版权所有 京ICP备14002150号-1</p>
			        <p>京ICP备14002150号-1 京公网安备1101080213237号</p>
			    </div><!--底部-->
			
			</div><!--外部容器-->
	</body>
</html>