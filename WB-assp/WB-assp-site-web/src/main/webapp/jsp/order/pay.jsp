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
		<link rel="stylesheet" href="<%=rootPath%>/css/model.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/welcome.css" />
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
	</head>
	<body>
	<div id="wrapper">
				<jsp:include page="/jsp/common/login/header.jsp"></jsp:include>
			
				
		<div class="payMain">
            <div class="pay-item">
                <h3 class="pay-item-tit">
                    <span class="fr">客户信息</span>
                </h3>
                <div class="pay-item-cont">
                    <div class="pay-item-list">
                        <div class="fl pay-item-list-cont">
                            <label >客户名称</label>
                            <span>${order.userName}</span>
                        </div>
                        <div  class="fl pay-item-list-cont">
                            <label class="">联系电话</label>
                            <span>${order.userPhone}</span>
                        </div>

                    </div>
                    <div>
                        <div class="pay-item-list-cont2" >
                            <label class="">详细地址</label>
                            <span>${order.userAddress}</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="pay-item">
                <h3 class="pay-item-tit">
                    <span class="fr">支付方式</span>
                </h3>
                <div class="pay-item-cont02">
                    <div class="payMethod fl">
                        <img src="<%=rootPath%>/images/alipay.png" alt="">
                        <span class="checked"></span>
                    </div>
                    <div class="payMethod fl">
                        <img src="<%=rootPath%>/images/WeChatPay.png" alt="">
                        <span class="checked"></span>
                    </div>
                    <div class="payMethod fl">
                        <img src="<%=rootPath%>/images/JDpay.png" alt="">
                        <span class="checked"></span>
                    </div>
                </div>
            </div>
            <div class="pay-item">
                <h3 class="pay-item-tit">
                    <span class="fr">订单信息</span>
                </h3>
                <div class="pay-item-cont">
                    <div class="pay-item-list">
                        <div class="fl pay-item-list-cont">
                            <label >产品名</label>
                            <span>${order.templateName}</span>
                        </div>
                        <div  class="fl pay-item-list-cont">
                            <label class="">价格</label>
                            <span class="price">${order.orderPrice}</span>
                        </div>

                    </div>
                    <div class="pay-item-list">
                        <div class="pay-item-list-cont2" >
                            <label class="">域名</label>
                            <span>${order.userDomainName}</span>
                        </div>
                    </div>
                    <div>
                        <div class="pay-item-list-cont2" >
                            <label class="fl">服务器配置</label>
                            <ul class="fl configList">
                                <li>
                                    <span class="configName">空间大小：</span>
                                    <span class="configCont">${order.spaceSize}M</span>
                                </li>
                                <li>
                                    <span class="configName">带宽类型：</span>
                                    <span class="configCont">${order.dkType}</span>
                                </li>
                                <li>
                                    <span class="configName">带宽运营商：</span>
                                    <span class="configCont">${order.dkOperators}</span>
                                </li>
                                <li>
                                    <span class="configName">带宽信息：</span>
                                    <span class="configCont">${order.dkInfo}M</span>
                                </li>
                                <li>
                                    <span class="configName">购买时长：</span>
                                    <span class="configCont">${order.rentedTime}${order.rentedUnit}</span>
                                </li>
                                <!-- <li>
                                    <span class="configName">操作系统：</span>
                                    <span class="configCont">Windows Server 2012 R2 数据中心版 64位中文版</span>
                                </li>
                                <li>
                                    <span class="configName">系统盘：</span>
                                    <span class="configCont">40GB 普通云盘</span>
                                </li>
                                <li>
                                    <span class="configName">密码：</span>
                                    <span class="configCont">未设置</span>
                                </li> -->
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <p class="payMoney">
                <span class="fr">应付金额：<i class="money">${order.orderPrice}</i>元</span>
            </p>
            <div class="btnBox">
                <span class="payBtn fr">去支付</span>
            </div>
        </div>
        <form id="payForm" method="post" action="<%=rootPath%>/wms/order/payOrder.do">
        	<input type="hidden" name="orderId"  value="${order.orderId}"  />
        </form>
        
				
							
			    <div class="footer">
			        <p>北京网视通联科技股份有限公司</p>
			        <p>地址：北京市海淀区知春路京仪孵化器D座3层</p>
			        <p>Copyright © 2004-2014 CCHVC云通-CDN全业务平台版权所有 京ICP备14002150号-1</p>
			        <p>京ICP备14002150号-1 京公网安备1101080213237号</p>
			    </div><!--底部-->
			    
			    
			     <div class="mask"></div>
			        <div class="popPay">
			            <p class="payClose fr"></p>
			            <p class="popMoney">
			                <span>
			                    您需要支付：<i>${order.orderPrice}</i>元
			                </span>
			            </p>
			            <p class="payBox">
			                <img src="<%=rootPath%>/images/code.png" alt="" class="code fl">
			                <img src="<%=rootPath%>/images/RichScan.png" alt="" class="fl RichScan">
			            </p>
			        </div>
			    </div>
			
			</div><!--外部容器-->
	</body>
<script>
    var $payMethod =  $(".pay-item-cont02 .payMethod");
    $payMethod.click(function (){
        if(!$(this).hasClass("active")){
            $(this).addClass("active").siblings().removeClass("active");
            $(this).find(".checked").show();
            $(".payBtn").addClass("active");
            $(this).siblings().find(".checked").hide();

        }else{
            $(this).removeClass("active");
            $(this).find(".checked").hide();
            $(".payBtn").removeClass("active");
        }
    })
    //点击支付出现弹框
    $(".payBtn").click(function(){
        /* if($(this).hasClass("active")){
            $(".mask").fadeIn();
            $(".popPay").fadeIn();
        } */
        $("#payForm").submit();
    })
    //
    $(".payClose").click(function (){
        $(".mask").hide();
        $(".popPay").hide();
    })
</script>
</html>