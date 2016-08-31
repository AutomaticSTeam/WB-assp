/*更新用户中心*/
$(function(){
	$("#usercenter").click(function(){
		var userId    = $("#userId").val();
		var userName  = $("#userName").val();
		var nickName  = $("#nickName").val();
		var position  = $("#position").val();
		var realName  = $("#realName").val();
		var sex       = $("#sex").val();
		var identifyId= $("#identifyId").val();
		var phone     = $("#phone").val();
		var homeTel   = $("#homeTel").val();
		var fax       = $("#fax").val();
		var email     = $("#email").val();
		var address   = $("#address").val();
		var company   = $("#company").val();
		

		if(nickName==null){
			layer.alert("请填写昵称！");
			return;
		}
		if(realName==null){
			layer.alert("请填写真实姓名！");
			return;
		}
		if(identifyId==null){
			layer.alert("请填写身份证号码！");
			return;
		}
		if(identifyId!=null){
			
			ValidateUtils.validIdNumber(identifyId);
//			alert(ValidateUtils.validIdNumber(identifyId););
			if(ValidateUtils.validIdNumber(identifyId)==false){
				layer.alert("请填写正确身份证号码");
				return;
			}
		}
		if(company==null){
			layer.alert("请填写公司名称！");
			return;
		}
		if(email==null){
			layer.alert("请填写电子邮箱！");
			return;
		}
		if(address==null){
			layer.alert("请填写地址！");
			return;
		}
		else{
			if(updateUserCenterForm.check()){
			$.ajax({
				url:rootPath+"/sys/register/updUserCenter.do",
				type:"post",
				datatype:"json",
				data:$(".usercenter-form").serialize(),
				success:function(data){
					if(data.status==0){
					layer.alert(data.msg);
					}else{
					layer.msg(data.msg);
					}
				}
		  })
			}else {
				layer.alert(信息有误);
			}}
	})
});




/* 修改密码  */
var    modifyPaw = function(){
	layer.open({
		  type: 1,
		  //skin: 'layui-layer-lan',
		  title: ["修改密码", 'font-size:14px;'],
		  area: ['400px', '288px'],
		  closeBtn:1,//右上角关闭按钮样式
		  shade: 0.3, //遮罩为0表示不显示
		  shadeClose :true,//是否点击遮罩关闭
		  scrollbar:false,//是否允许滚动条出现
		  content: $('#modifyPawDiv') ,
		  //按钮与回调
		  btn: ['确认', '取消'],
		  yes: function(index, layero){
			 
				var password = $("#password").val();
				var newPaw= $("#newPaw").val();
				var confirmPaw= $("#confirmPaw").val();
				
				if(password==""){
					layer.alert("请填写原密码！");
					return;
				}
				if(newPaw=="" ||confirmPaw==""){
					layer.alert("请填写新密码！");
					return;
				}
				if(newPaw!=confirmPaw){
					layer.alert("两次新密码不一致！");
					return;
				}else{
					if(modifyPawForm.check()){
							$.ajax({
								url:rootPath+"/sys/register/updataPwd.do",
								type:"post",
								datatype:"json",
								data:$(".modifyPaw-form").serialize(),
//								data:{
//									password : password,
//									newPasswprd : newPaw
//								},
								success:function(data){
									if(data.status==7){
									layer.alert(data.msg);
									}else if(data.status==2){
									layer.alert(data.msg);
									}else{
									layer.msg(data.msg);
									}
								}
						  });
					}else {
						alert(信息有误);
					}
				}
		      layer.close(index);
		  },
		  end: function(index){ 
			  $("#articleTypeName").val("");
			  layer.close(index);
		}
		});
}

$(function(){
	$("#modifyPawBtn").on("click",function(){
		 modifyPaw();
	});	
	
	$("#updPwd").click(function(){
	});	/*登录*/

});

