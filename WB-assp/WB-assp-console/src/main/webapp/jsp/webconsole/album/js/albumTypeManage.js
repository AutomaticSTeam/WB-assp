$(function(){
	//加载表格数据
	//queryAlbumTypes();
	AlbumTypeQueryAll.drawAlbumTypesDataView(3);
	 //点击添加分类
    $("#addAlbumTypeBtn").on("click",function(){
    	   editAlbumType(null ,null, "添加图册分类");
    });
    
});

//编辑图册分类
var editAlbumType = function(albumTypeId ,albumTypePid,  title){
	//赋值
	if(albumTypeId!=null){
		$("#albumTypeName").val(AlbumTypeQueryAll.getNameById(albumTypeId));
	}
	if(albumTypePid!=null){
		$("#albumTypePid").val(albumTypePid);
		$("#albumTypePid_0").val(AlbumTypeQueryAll.getPathByPId(albumTypePid));
	}
	layer.open({
		  type: 1,
		  //skin: 'layui-layer-lan',
		  title: [title, 'font-size:14px;'],
		  area: ['300px', '230px'],
		  closeBtn:1,//右上角关闭按钮样式
		  shade: 0.3, //遮罩为0表示不显示
		  shadeClose :true,//是否点击遮罩关闭
		  scrollbar:false,//是否允许滚动条出现
		  content: $('#albumTypeDiv') ,
		  //按钮与回调
		  btn: ['确认', '取消'],
		  yes: function(index, layero){
		    var albumTypeName = $("#albumTypeName").val();
		    var albumTypePid = $("#albumTypePid").val();
		    if($.trim(albumTypeName ) == "" ||  albumTypeName == null){
		    	 layer.alert('请填写图册类型名称', {icon: 2});
		    }else if(albumTypeName.length > 20){
		    	 layer.alert('图册类型名称不能多于20个字', {icon: 2});
		    	}else{
		    	$.ajax({
		    		type:'post',
		    		url:rootPath+'/contents/album/queryHasAlbumTypes.do',
		    		data:{
		    			albumTypeName:albumTypeName
		    		},
		    		dataType:'json',
		    		async : true,
		    		success: function(data){ 
		    			if(data.rst > 0){
		    				layer.alert("此数据已存在请修改后添加！", {icon: 2});
		    			}else{
		    				layer.close(index);
		    				addOrEditAlbumType(albumTypeId,albumTypeName,albumTypePid,title);
		    			}
		    		},
		    		error:function(data){
		    			//alert(JSON.stringify(data));
		    		}
		    	});
		    	//addOrEditAlbumType(albumTypeId,albumTypeName,title);
		    	//layer.close(index);
		    }
		  },
		  end: function(index){ 
			  $("#albumTypeName").val("");
			  layer.close(index);
		}
		});
};

//持久化数据
var addOrEditAlbumType = function(albumTypeId,albumTypeName,albumTypePid,title){
	albumTypeDataOpt(albumTypeId,albumTypeName,albumTypePid,null,title);
};

//数据操作
var albumTypeDataOpt = function(albumTypeId,albumTypeName,albumTypePid,dataStatus,title){
	$.ajax({
		type:'post',
		 url:rootPath+'/contents/album/addOrEditAlbumType.do',
		data:{
			albumTypeId : albumTypeId ,
			albumTypePid : albumTypePid ,
			albumTypeName : albumTypeName,
			dataStatus : dataStatus
		},
		dataType:'json',
		async : true,
		success: function(data){ 
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
			
			//AlbumTypeQueryAll.drawAlbumTypesDataView(3);
		},
		error:function(data){
			//alert(JSON.stringify(data));
		}
     });
};
//删除图册分类
var delAlbumType = function(albumTypeId,del){
		layer.confirm("确定删除该数据?", {icon: 3, title:'提示'}, function(index){
			$delArtilceType(albumTypeId,0);
			layer.close(index);
		});
};
//删除图册分类
var $delArtilceType = function(albumTypeId,del){
	$.ajax({
		type:'get',
		url:rootPath+'/contents/album/delAlbumType.do',
		data:{
			albumTypeId:albumTypeId,
			delStatus:del
		},
		dataType:'json',
		async : true,
		success: function(data){ 
			if(data.result=='success'){
				layer.alert('删除成功！', {icon: 1},function(index){
					  //do something
					  window.location.reload();
					  layer.close(index);
					});
				//AlbumTypeQueryAll.drawAlbumTypesDataView(3);
			}else if(data.result=='tip'){
				layer.confirm(data.message, {icon: 3, title:'提示'}, function(index){
					//删除数据
					$delArtilceType(albumTypeId,1);
					
					layer.close(index);
				});
			}else{
				layer.alert(data.message, {icon: 1});
			}
			
		},
		error:function(data){
			//alert(JSON.stringify(data));
		}
	});
	
};



















