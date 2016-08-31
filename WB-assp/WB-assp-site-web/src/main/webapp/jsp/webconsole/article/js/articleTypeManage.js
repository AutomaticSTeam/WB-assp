$(function(){
	//加载表格数据
	//queryArticleTypes();
	ArticleTypeQueryAll.drawArticleTypesDataView(3);
	 //点击添加分类
    $("#addArticleTypeBtn").on("click",function(){
    	   editArticleType(null ,null, "添加文章分类");
    });
    
});

//编辑文章分类
var editArticleType = function(articleTypeId ,articleTypePid,  title){
	//赋值
	if(articleTypeId!=null){
		$("#articleTypeName").val(ArticleTypeQueryAll.getNameById(articleTypeId));
	}
	if(articleTypePid!=null){
		$("#articleTypePid").val(articleTypePid);
		$("#articleTypePid_0").val(ArticleTypeQueryAll.getPathByPId(articleTypePid));
	}
	layer.open({
		  type: 1,
		  //skin: 'layui-layer-lan',
		  title: [title, 'font-size:14px;'],
		  area: ['300px', '250px'],
		  closeBtn:1,//右上角关闭按钮样式
		  shade: 0.3, //遮罩为0表示不显示
		  shadeClose :true,//是否点击遮罩关闭
		  scrollbar:false,//是否允许滚动条出现
		  content: $('#articleTypeDiv') ,
		  //按钮与回调
		  btn: ['确认', '取消'],
		  yes: function(index, layero){
		    var articleTypeName = $("#articleTypeName").val();
		    var articleTypePid = $("#articleTypePid").val();
		    if($.trim(articleTypeName ) == "" ||  articleTypeName == null){
		    	 layer.alert('请填写文章类型名称', {icon: 2});
		    }else if(articleTypeName.length > 20){
		    	 layer.alert('文章类型名称不能多于20个字', {icon: 2});
		    	}else{
		    	$.ajax({
		    		type:'post',
		    		url:rootPath+'/contents/article/queryHasArticleTypes.do',
		    		data:{
		    			articleTypeName:articleTypeName
		    		},
		    		dataType:'json',
		    		async : true,
		    		success: function(data){ 
		    			if(data.rst > 0){
		    				layer.alert("此数据已存在请修改后添加！", {icon: 2});
		    			}else{
		    				layer.close(index);
		    				addOrEditArticleType(articleTypeId,articleTypeName,articleTypePid,title);
		    			}
		    		},
		    		error:function(data){
		    			//alert(JSON.stringify(data));
		    		}
		    	});
		    	//addOrEditArticleType(articleTypeId,articleTypeName,title);
		    	//layer.close(index);
		    }
		  },
		  end: function(index){ 
			  $("#articleTypeName").val("");
			  layer.close(index);
		}
		});
};

//持久化数据
var addOrEditArticleType = function(articleTypeId,articleTypeName,articleTypePid,title){
	articleTypeDataOpt(articleTypeId,articleTypeName,articleTypePid,null,title);
};

//数据操作
var articleTypeDataOpt = function(articleTypeId,articleTypeName,articleTypePid,dataStatus,title){
	$.ajax({
		type:'post',
		 url:rootPath+'/contents/article/addOrEditArticleType.do',
		data:{
			articleTypeId : articleTypeId ,
			articleTypePid : articleTypePid ,
			articleTypeName : articleTypeName,
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
			ArticleTypeQueryAll.drawArticleTypesDataView(3);*/
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
};
//删除文章分类
var delArtilceType = function(articleTypeId){
	$.ajax({
		type:'post',
		url:rootPath+'/contents/article/queryHasArticleTypeRel.do',
		data:{
			articleTypeId:articleTypeId
		},
		dataType:'json',
		async : true,
		success: function(data){ 
			 var tip = "您确定删除当前数据";
			 if(data.rst > 0){
				 tip += ",此分类下关联有文章";
			 }
			 layer.confirm(tip, {icon: 3, title:'提示'}, function(index){
				 articleTypeDataOpt(articleTypeId,null,null,1,"删除文章");
				 //删除数据
				  layer.close(index);
			});
			// ArticleTypeQueryAll.drawArticleTypesDataView(3);
			
		},
		error:function(data){
			//alert(JSON.stringify(data));
		}
	});
};



















