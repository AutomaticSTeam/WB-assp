$(function(){
	
	/*Enter键登录*/
	$(document).keydown(function(event) {
		if (event.keyCode==13){
			loginFun();
		}  
	})
	
	/*点击验证码刷新*/
	$(".text-code-image").click(function(){
		$("#changeVerificationCode").click();
	})
	
	/*背景图切换*/
	var item = $('.loginImage-item');
	item.css({
		'opacity':'0'
	});
	item.first().css({
		'opacity':'1'
	});
	var n=0;
	function run(){
		n >= item.length ? n=0:n;
		item.eq(n).stop().animate({'opacity':'1'},3000).siblings().stop().animate({'opacity':'0'},3000);
		n += 1;
		var timer = setTimeout(run,5000);
	};	
	run();/*背景图切换*/
	
	/*记住密码*/
	/*$('.radio').click(function(){
		$(this).toggleClass('radio-sel');
	});*/
	
	$("#login").click(function(){
		loginFun();
	})
	
	/*登录*/
	
	/*验证码*/
	$("#verificationCode").attr("src",rootPath+'/sys/login/getVerificationCode.do?'+Math.random());
	$("#changeVerificationCode").click(function(){
		$("#verificationCode").attr("src",rootPath+'/sys/login/getVerificationCode.do?'+Math.random());
	})
	/*浏览器校验*/
	/*$.browser.msie = /msie/.test(navigator.userAgent.toLowerCase()); 
	if (!$.support.leadingWhitespace) {
		if ($.browser.msie && 8 < $.browser.version) {
			layer.alert("请更换更高版本ie或者使用其他浏览器。。。");
		} 
	} */
	
});

var loginFun=function(){

	var verifyCode=$("#verifyCode").val();
	var userName=$("#userName").val();
	var password=$("#password").val();
	
	if(verifyCode.length==0){
		layer.alert("请填写验证码");
	}else if(verifyCode.length!=4){
		layer.alert("请四位验证码");
	}else if(userName==""){
		layer.alert("请填写用户名");
	}else if(password==""){
		layer.alert("请填写密码");
	}else{
		$.ajax({
			url:rootPath+"/sys/login/login.do",
			type:"post",
			datatype:"json",
			data:$(".login-form").serialize(),
			success:function(data){
			if(data.status=="0"){
				location.href=rootPath+"/jsp/common/login/welcome.jsp";
			}else{
				layer.msg(data.tip, {
					  time: 500 
					},function(){
						if(data.status==2){
							$("#changeVerificationCode").click();
						}
					});
			}
					
			},
			error:function(){
				
			}
	  })
	}

}