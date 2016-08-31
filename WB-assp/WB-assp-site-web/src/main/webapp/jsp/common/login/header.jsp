<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath();%>


<div class="header-container">
	<div class="header">
		<div class="logo" title="返回主页">
			<a href="<%=rootPath%>/jsp/common/login/welcome.jsp">
				<img src="<%=rootPath%>/images/login_logo.png"/>
			</a>
		</div>
		<div class="top-right-info">
			<%-- <p id="userName">欢迎您，</p>
			<a href="<%=rootPath%>//sys/login/logout.do" style="width:73px;">退出</a> --%>
			<!-- <a href="">返回.net平台</a> -->
			<div class="drop-down">
				${loginUser.userName}
			</div>
			<p>欢迎您，</p>
			<ul class="drop-down-ul" style="display: none">
				<li class="li1"><a href="<%=rootPath%>/wms/order/orderList.do">订单管理</a></li>
				<li class="li2"><a href="<%=rootPath%>/sys/register/userCenter.do">账户设置</a></li>
				<li class="li3"><a href="<%=rootPath%>/sys/login/logout.do">&emsp;退出&emsp;</a></li>
			</ul>
		</div>
	</div>
</div><!--头部-->
	<script>
	
	//用户管理点击弹窗外关闭弹窗
	$(document).click(function(event){
	  if(!$(event.target).hasClass('top-right-info')&&!$(event.target).hasClass('drop-down')){
		  $('.drop-down-ul').hide();
	   }
	})
		$(".drop-down").click(function(){
			if($(".drop-down-ul").css("display")=="none"){
				$(".drop-down-ul").show();
			}else{
				$(".drop-down-ul").hide();
			}
	//		$(".drop-down-ul").css({"display":"block"})
		})
	
		$(function(){
			for (var i=1; i<=3; i++){
				$(".li"+i).bind("click", {index: i}, clickHandler);
			}
			function clickHandler(event) {
				var i= event.data.index;
				var val=$(".li"+i).html();
				/*$(".drop-down").empty();
				 $(".drop-down").html("<p>"+val+"<span class='b'>></span></p>");*/
				$(".drop-down-ul").css({"display":"none"})
			}
		});
	</script>
