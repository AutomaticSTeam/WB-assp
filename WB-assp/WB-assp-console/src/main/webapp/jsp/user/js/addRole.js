$(function(){
	//表单验证
	$(".moduleTmplForm").Validform({
		tiptype:3
	});

	$("#saveBtn").click(function(){
		$.ajax({
			url:rootPath+"/console/role/addRole.do",
			type:"post",
			datatype:"json",
			data:$(".moduleTmplForm").serialize(),
			success:function(data){
				if(data.status==1){
					alert(data.msg);
					window.location.href=rootPath+"/jsp/user/roleList.jsp"
				}else{
					alert(data.msg);
				}
			},
			error:function(){
				layer.alert("添加失败，请重试。。。");
			}
		});	
	});		
	
});