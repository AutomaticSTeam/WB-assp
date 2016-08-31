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
		        <p class="orderTitle">订单详情</p>
		        <table class="orderCloudTb tb">
		            <tr class="ofirst">
		                <th>产品名</th>
		                <th>是否启用</th>
		                <!-- <th>数量</th> -->
		                <th>服务器配置</th>
		                <th>够买时长</th>
		                <th>起止时间</th>
		                <th>价格</th>
		            </tr>
		            <tr>
		                <td>${order.templateName}</td>
		                <td>${order.isUse==0?"<font color=red>否</font>":"<font color=green>是</font>"}</td>
		               <!--  <td>1</td> -->
		                <td class="tl" style="text-align: left;">
		                    <p class="lh"><span>&emsp;&emsp;空间大小：</span><span class="ml10">${order.spaceSize}M</span></p>
		                    <p class="lh"><span>&emsp;&emsp;带宽类型：</span><span class="ml10">${order.dkType}</span></p>
		                    <p class="lh"><span>&emsp;&emsp;带宽运营商：</span><span class="ml10">${order.dkOperators}</span></p>
		                    <p class="lh"><span>&emsp;&emsp;带宽信息：</span><span class="ml10">${order.dkInfo}M</span></p>
		                    <p class="lh"><span>&emsp;&emsp;购买时长：</span><span class="ml10">${order.rentedTime}${order.rentedUnit}</span></p>
		                   <!--  <p class="lh"><span>购买时长</span><span class="ml10">四年</span></p>
		                    <p class="lh"style=" width: 200px;"><span>&emsp;有效期</span><span class="ml10">2016/7/19-2020/7/18</span></p> -->
		                </td>
		                <td>${order.rentedTime}${order.rentedUnit}</td>
		                <td>
		                	<c:if test="${order.isUse==0}">
							</c:if>
							<c:if test="${order.isUse==1}">
								 <p class="lh"><span>起：</span><span class="ml5"><fmt:formatDate value="${order.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
		                    	 <p class="lh"><span>止：</span><span class="ml5">${overTime}</span></p>
							</c:if>
		                </td>
		                <td>${order.orderPrice}</td>
		            </tr>
		
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