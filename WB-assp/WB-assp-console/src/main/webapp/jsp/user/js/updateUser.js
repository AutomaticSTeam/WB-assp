$(function(){
	//表单验证
	$(".moduleTmplForm").Validform({
		tiptype:3
	});

	$("#saveBtn").click(function(){
		var password=$("#password").val();
		var qrpassword=$("#qrpassword").val();
		
		if(password==qrpassword){
			$.ajax({
				url:rootPath+"/console/user/updateUser.do",
				type:"post",
				datatype:"json",
				data:$(".moduleTmplForm").serialize(),
				success:function(data){
					if(data.status==1){
						layer.alert(data.msg);
						window.location.href=rootPath+"/jsp/user/userList.jsp"
					}else{
						layer.alert(data.msg);
					}
				},
				error:function(){
					layer.alert("更新失败，请重试。。。");
				}
			});	
		}else{
			alert("密码和确认密码不一致");
		}
	});		
	
});