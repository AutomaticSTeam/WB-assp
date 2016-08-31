$(function() {
	// 表单验证
	$(".moduleTmplForm").Validform({
		tiptype : 3
	});

	$("#saveBtn").click(function() {
		$.ajax({
			url : rootPath + "/console/permission/updatePermission.do",
			type : "post",
			datatype : "json",
			data : $(".moduleTmplForm").serialize(),
			success : function(data) {
				if (data.status == 1) {
					alert(data.msg);
					window.location.href = rootPath + "/jsp/user/permission.jsp"
				} else {
					alert(data.msg);
				}
			},
			error : function() {
				layer.alert("更新失败，请重试。。。");
			}
		});
	});

});