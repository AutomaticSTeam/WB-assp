$(function(){
	//页面样式
	$('.column-list-view-item:last').css({'border-bottom':'none'});
	
	//获取当前登录用户
	$.ajax({
		url:rootPath+"/sys/login/getUser.do",
		type:"post",
		datatype:"json",
		data:$(".login-form").serialize(),
		success:function(data){
			if(data!=""){
				var userName=data.userName;
				$("#userName").html("欢迎您，"+userName);
				//加载待管理网站
				$.ajax({
					url:rootPath+"/sys/user/getUserWebSite.do",
					type:"post",
					datatype:"json",
					data:data.userId,
					success:function(data){
						if(data.length<1){
							$(".empt-flag").show();
						}else{
							for (var i = 0; i < data.length; i++) {
								var liDiv=	"<li class='column-list-view-item'>\n" +
								"	<h2>网址：<a href='"+data[i].siteDomain+"'>"+data[i].siteDomain+"</a></h2>\n" +
								"	<a class='admin-btn'  id="+data[i].templateId+" onclick='manageSite(this.id)'>管理</a>\n" +
								"</li>"
								$("#siteContent").append(liDiv);
							}
						}
						
					},
					error:function(){
						
					}
				})
			}else{
				location.href=rootPath+"/jsp/common/login/login.jsp";
			}
		},
		error:function(){
			
		}
	})
  
	
	
});
function manageSite(templateId){
	if(templateId!=null&&templateId!=""){
		//查询当前站点的模板与首页id
		$.ajax({
			url:rootPath+"/template/getWebsiteInfoByTemplate.do",
			type:"post",
			datatype:"json",
			data:{templateId:templateId},
			success:function(data){
				var columnsId=data.columnsId;
				if(columnsId!=null){
					location.href = rootPath + "/"+templateId+"/"+columnsId+"/index.html";
				}
			},
			error:function(){
				
			}
		})
	}
}
