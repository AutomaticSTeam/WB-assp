$(function(){
	//表单验证
	$(".moduleTmplForm").Validform({
		tiptype:3
	});

	$("#saveBtn").click(function(){
		var moduleTmlName=$("#moduleTmlName").val();
		var moduleTmlKey=$("#moduleTmlKey").val();
		if(moduleTmlKey!=""&&moduleTmlKey!=null&&moduleTmlName!=""&&moduleTmlName!=null){
			$.ajax({
				url:rootPath+"/console/moduleTmpl/addModuleTmpl.do",
				type:"post",
				datatype:"json",
				data:$(".moduleTmplForm").serialize(),
				success:function(data){
					if(data.code==1){
						window.location.href=rootPath+"/jsp/module/managerModule.jsp"
					}else{
						layer.alert("添加失败，请重试。。。");
					}
				},
				error:function(){
					layer.alert("添加失败，请重试。。。");
				}
			});	
		}else{
			alert("请填写完整信息");
		}
	});		
	
	var moduleTmlId=$("#moduleTmlId").val();
	if(moduleTmlId==""&&moduleTmlId==null){
		//添加图片按钮事件
		$("#thumbImgId").click(function(){
			uploadImg();
		});
	}else{
		$("#module_add_edit_title").html("编辑模块");
		if($("#moduleTmplAttachmentImg").val()==""||$("#moduleTmplAttachmentImg").val()==null){
			$("#thumbImgId").click(function(){
				uploadImg();
			});
		}
	}
	//删除图片
	$("#delImage").click(function(){
		$("#thumbImgId").attr("src",rootPath + "/images/thumb.png" );
		$("#moduleTmplAttachmentImg").val("");
		$("#thumbImgId").click(function(){
			uploadImg();
		});
		$("#delImage").hide();
	});
})

var uploadImg=function(){
		layer.open({
			  type: 2, 
			  title: ['添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）', 'font-size:14px;'],
			  content: [rootPath+'/jsp/common/pop/uploadImg.jsp?singleupload=true&&chooseSingle=true&&templateCode=modules', 'no'],
			 // skin: 'demo-class'
			  area: ['640px', '430px'],//定义大小
			  closeBtn:1,//右上角关闭按钮样式
			  shade: 0.3, //遮罩为0表示不显示
			  shadeClose :true,//是否点击遮罩关闭
			  scrollbar:false,//是否允许滚动条出现
			  //按钮与回调
			  btn: ['确认', '取消'],
			  yes: function(index, layero){
				  setImg(index, layero);
				  layer.close(index);
			  	   },
			});
		
}
//设置附图
var setImg =  function(index, layero){
	var body = layer.getChildFrame('body', index);
	var pUrls=body.find("input[id='pictureUrls']").val();
	if(pUrls != "" && pUrls.length > 0){
		pUrl = pUrls.substring(0,(pUrls.length - 1));
		$("#thumbImgId").attr("src",pUrl);
		$("#moduleTmplAttachmentImg").val(pUrl);
		$("#thumbImgId").unbind("click");
		$("#delImage").show();
	}
  };
  
  
  /** 
   * 获取当前URL参数值 
   * @param name  参数名称 
   * @return  参数值 
   */  
  function getUrlParam(name) {  
         var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");  
         var r = window.location.search.substr(1).match(reg);  
         if(r!=null){
      	  return unescape(r[2]);   
         }else{
      	  return null;  
         }  
           
  } 