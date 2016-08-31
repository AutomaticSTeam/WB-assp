$(function(){
        ContentsManage.drawMediaTypesDataView(0);
        
         //点击添加分类
        $("#addMediaTypeBtn").on("click",function(){
        	   editMediaType(null ,null, "添加视频分类");
        });
});

//编辑视频分类
var editMediaType = function(mediaTypeId ,mediaTypePid,  title){
	//赋值
	if(mediaTypeId!=null){
		$("#mediaTypeName").val(ContentsManage.getNameById(mediaTypeId));
	}
	if(mediaTypePid!=null){
		$("#mediaTypePid").val(mediaTypePid);
		$("#mediaTypePid_0").val(ContentsManage.getPathByPId(mediaTypePid));
	}
	layer.open({
		  type: 1,
		  title: [title, 'font-size:14px;'],
		  area: ['300px', '250px'],
		  closeBtn:1,//右上角关闭按钮样式
		  shade: 0.3, //遮罩为0表示不显示
		  shadeClose :true,//是否点击遮罩关闭
		  scrollbar:false,//是否允许滚动条出现
		  content: $('#mediaTypeDiv') ,
		  //按钮与回调
		  btn: ['确认', '取消'],
		  yes: function(index, layero){
		    var mediaTypeName = $("#mediaTypeName").val();
		    var mediaTypePid = $("#mediaTypePid").val();
		    if($.trim(mediaTypeName ) == "" ||  mediaTypeName == null){
		    	 layer.alert('请填写视频类型名称', {icon: 2});
		    }else if(mediaTypeName.length > 30){
		    	 layer.alert('视频类型名称不能多于30个字', {icon: 2});
		    }else{
		    	$.ajax({
		    		type:'post',
		    		 url:rootPath+'/contents/media/queryHasMediaTypes.do',
		    		data:{
		    			mediaTypeName : mediaTypeName,
		    		},
		    		dataType:'json',
		    		async : true,
		    		success: function(data){ 
		    			if(data.rst > 0){
		    				layer.alert("此数据已存在请修改后添加！", {icon: 2});
		    			}else{
		    				layer.close(index);
		    				addOrEditMdiaType(mediaTypeId,mediaTypeName,mediaTypePid,title);
		    			}
		    		},
		    		error:function(data){
		    			//alert(JSON.stringify(data));
		    		}
		         });
		    	
		    	
		    }
		  },
		  end: function(index){ 
			  $("#mediaTypeName").val("");
			  layer.close(index);
		}
		});
}

//持久化数据
var addOrEditMdiaType = function(mediaTypeId,mediaTypeName,mediaTypePid,title){
	mediaTypeDataOpt(mediaTypeId,mediaTypeName,mediaTypePid,null,title);
}

//数据操作
var mediaTypeDataOpt = function(mediaTypeId,mediaTypeName,mediaTypePid,dataStatus,title){
	console.log(mediaTypeId);
	$.ajax({
		type:'post',
		 url:rootPath+'/contents/media/addOrEditMediaType.do',
		data:{
			mediaTypeId : mediaTypeId ,
			mediaTypePid : mediaTypePid ,
			mediaTypeName : mediaTypeName,
			dataStatus : dataStatus
		},
		dataType:'json',
		async : true,
		success: function(data){ 

			/*if(data.reasult == "success"){
				layer.alert(title + '成功！', {icon: 1});
			}else{
				layer.alert(title + '失败！', {icon: 2});
			}
			 ContentsManage.drawMediaTypesDataView(0);*/
			
			if(data.reasult == "success"){
				layer.alert(title + '成功！', {icon: 1},function(index){
					  //do something
					window.location.reload();
					  layer.close(index);
					});
			}else{
				layer.alert(title + '失败！', {icon: 2},function(index){
					  //do something
					window.location.reload();
					  layer.close(index);
					});
			}
		},
		error:function(data){
			//alert(JSON.stringify(data));
		}
     });
}

//删除视频分类
var delMdiaType = function(mediaTypeId){
	 
	$.ajax({
		type:'post',
		 url:rootPath+'/contents/media/queryHasMediaTypeRel.do',
		data:{
			mediaTypeId : mediaTypeId 
		},
		dataType:'json',
		async : true,
		success: function(data){ 
			 var tip = "您确定删除当前数据";
			 if(data.rst > 0){
				 tip += ",此分类下关联有视频";
			 }
			 layer.confirm(tip, {icon: 3, title:'提示'}, function(index){
				 mediaTypeDataOpt(mediaTypeId,null,null,1,"删除视频");
				 //删除数据
				  layer.close(index);
			});
			// ContentsManage.drawMediaTypesDataView(0);
		},
		error:function(data){
			//alert(JSON.stringify(data));
		}
     });

}


