
var editWebsite = {
		// 初始化方法
		initialize: function(){
			$("#websiteForm").Validform({
				tiptype:3,
				btnSubmit:".saveBtn",
				ajaxPost: true,
				beforeSubmit:function(curform){
				},
				callback:function(data){
					$("#Validform_msg").remove();
					if(data.code == 1){
						layer.alert("保存成功");
					}else{
						layer.alert("保存失败，请重试");
					}
				}
			});
		}
};



