$(function(){
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
	
	$("#register").click(function(){
		var userName=$("#userName").val();
		var password1=$("#password1").val();
		var password2=$("#password2").val();
		
		if(userName==""){
			layer.alert("用户名/手机号不为空！");
			return;
		} 
		if(userName!=""){
			ValidateUtils.validCellPhoneNumber(userName);
			if(ValidateUtils.validCellPhoneNumber(userName)==false){
				layer.alert("请填写正确的手机号！");
				return;
			}
		}
		if(password1=="" ||password2==""){
			layer.alert("请填写密码！");
			return;
		}
		if(password1!=password2){
			layer.alert("两次密码不一致！");
			return;
		}else{
		if(form1.check()){
				$.ajax({
					url:rootPath+"/sys/register/registerUser.do",
					type:"post",
					datatype:"json",
					data:$(".register-form").serialize(),
					success:function(data){
					   if(data.status==1){
						   $.ajax({
								url:rootPath+"/sys/login/login.do",
								type:"post",
								datatype:"json",
								data:{
									userName:data.user.userName,
									password:data.user.password,
									vistSource:2},
								success:function(data){
								if(data.status=="0"){
									location.href=rootPath+"/jsp/common/login/welcome.jsp";
								}else{
									layer.msg(data.tip, {
										  time: 2000 
										});
								}
										
								},
								error:function(){
									
								}
						  })
					   }else{
						 layer.msg(data.msg);
					   }
					}
			    });
			}else {
				layer.msg("验证未通过！");
			}
		}
		
	});	/*登录*/
	

	$("#verifyCode").attr("disabled",true);
	/*验证码*/
	$("#getSmsInfoBtn").click(function(){
		
		var data = {userName :$("#userName").val()};
		var userName=$("#userName").val();
	//判断手机号   有没有    对不对
		if(userName==""){
			layer.alert("用户名/手机号不为空！");
			return;
		} 
		if(userName!=""){
			ValidateUtils.validCellPhoneNumber(userName);
			if(ValidateUtils.validCellPhoneNumber(userName)==false){
				layer.alert("请填写正确的手机号！");
				return;
			}
		}
		sendSms.sendSms(rootPath + "/sys/register/sendMessage.do",data,'verifyCode');
	})
	
});

