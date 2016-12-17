$(function(){
	if(dataTypeId != null && dataTypeId != "" && dataTypeId != ''){
		 var resultHtml ="";
		if(dataTypeId.indexOf("/1/") != -1 || dataTypeId == 1){
			selectedAddArticleType(moduleId);
			resultHtml +='<a class="btn dib btn-border-gray glClassBtn" id="manageArticleType">管理文章分类</a>';
			$("#queryManageType").html(resultHtml);
			$("#manageArticleType").click(function(){
				addManagerArticleType();
				//top.layer.close(top.SiteManager.tempData.layerIndex);
			});
			}else if(dataTypeId.indexOf("/5/") != -1 || dataTypeId == 6){
				selectPictureAlbumType(moduleId);
				resultHtml +='<a class="btn dib btn-border-gray glClassBtn" id="managePictureAlbumType">管理图册分类</a>';
				$("#queryManageType").html(resultHtml);
				$("#managePictureAlbumType").click(function(){
					managePictureAlbumType();
					//top.layer.close(top.SiteManager.tempData.layerIndex);
				});
			}else if(dataTypeId.indexOf("/8/") != -1 || dataTypeId == 8){
				selectDataColumnId(moduleId);
				resultHtml +='<a class="btn dib btn-border-gray glClassBtn" id="manageMediaType">管理视频分类</a>';
				$("#queryManageType").html(resultHtml);
				$("#manageMediaType").click(function(){
					addManagerMediaType();
					//top.layer.close(top.SiteManager.tempData.layerIndex);
				});
			}
	}
	
	
	//分类展示
	//所对应的选择分类
	
});

//页面中添加文章时，所携带的文章类型ID
var selectedAddArticleType = function(index){
	  $.ajax({
			url:rootPath+'/contents/article/queryArticleTypes.do',
			data:{dataStatus : 0},
			dataType:'json',
			async : true,
			success: function(data){
				$data =  data.articleTypes;
				 var resultHtml ="";
		    	   $.each($data,function(i,articleType){
		    		   resultHtml +='<span class="checkbox">'
								  +'<input type="checkbox" class="checkitem" name="articleTypeId" id ="" value="'+articleType.articleTypeId+'">'+articleType.articleTypeName+'</span>';
		    	   });
				$("#queryCKBoxType").html(resultHtml);
				$("#articleType").html("<span>文章分类：</span>");
				//若存在id，则取datacolumnId
				if(index != null && index != "null" && index != ''){
					$.ajax({
			  			type:'post',
			  			 url:rootPath+'/site/selectDateColumnId.do',
			  			data:{
			  				moduleId : index ,
			  			},
			  			dataType:'json',
			  			async : true,
			  			success: function(data){ 
			  				$data = data.templateModuleRelContents;

			  				$.each($data ,function(i,templateModuleRelContents){
			  				$("input[name='articleTypeId']").each(function(){
			  						 if($(this).val()  == templateModuleRelContents.dataColumnId){
			  							 $(this).attr('checked',true);
			  						 }
			  					 });
			  				 });
			  			 },
			  			error:function(data){
			  				//alert(JSON.stringify(data));
			  			}
			  	     });
					/*$("input[name='articleTypeId']").each(function(){
						 if($(this).val()  == index){
							 $(this).attr('checked',true);
						 }
					 });*/
				 }
				$("#queryCKBoxType").find(".checkitem").click(function(){
					//console.log(this.checked);
				   if(this.checked){
					   $(".checkitem").not(this).attr("checked",false);
				   }
				     
				  }); 
			}
			
			});
	
};
//图册分类
var selectPictureAlbumType = function(index){
	 $.ajax({
			url:rootPath+'/contents/album/queryAlbumTypes.do',
			data:{dataStatus : 0},
			dataType:'json',
			async : true,
			success: function(data){ 
				$data = data.albumTypes;
				 var resultHtml = "";
				 $.each($data ,function(i,PictureAlbumType){
					 resultHtml +='  <span class="checkbox"><input type="checkbox" class="checkitem" name="albumTypeId" id="" value="'+PictureAlbumType.albumTypeId+'">'+PictureAlbumType.albumTypeName+' </span>';
				 });
				$("#queryCKBoxType").html( resultHtml); //复选框
				$("#pictureAlbumType").html("<span>图册分类：</span>");
				//若存在id，则取datacolumnId
				if(index != null && index != "null" && index != ''){
					$.ajax({
			  			type:'post',
			  			 url:rootPath+'/site/selectDateColumnId.do',
			  			data:{
			  				moduleId : index ,
			  			},
			  			dataType:'json',
			  			async : true,
			  			success: function(data){ 
			  				$data = data.templateModuleRelContents;

			  				$.each($data ,function(i,templateModuleRelContents){
			  				$("input[name='albumTypeId']").each(function(){
			  						 if($(this).val()  == templateModuleRelContents.dataColumnId){
			  							 $(this).attr('checked',true);
			  						 }
			  					 });
			  				 });
			  			 },
			  			error:function(data){
			  				//alert(JSON.stringify(data));
			  			}
			  	     });
				 }
				$("#queryCKBoxType").find(".checkitem").click(function(){  
						//console.log(this.checked);
					   if(this.checked){
						   $(".checkitem").not(this).attr("checked",false);
					   }
					     
					  }); 
			}
	});
}
//视频分类
var selectDataColumnId = function(index){
	 $.ajax({
			url:rootPath+'/contents/media/queryMediaTypes.do',
			data:{dataStatus : 0},
			dataType:'json',
			async : true,
			success: function(data){ 
				$data = data.mediaTypes;
				 var resultHtml = "";
				 $.each($data ,function(i,mediaType){
					 resultHtml +='  <span class="checkbox"><input type="checkbox" class="checkitem" name="mediaTypeId" id="" value="'+mediaType.mediaTypeId+'">'+mediaType.mediaTypeName+' </span>';
				 });
				$("#queryCKBoxType").html( resultHtml); //复选框
				$("#mediaType").html("<span>视频分类：</span>");
				//若存在id，则取datacolumnId
				if(index != null && index != "null" && index != ''){
					$.ajax({
			  			type:'post',
			  			 url:rootPath+'/site/selectDateColumnId.do',
			  			data:{
			  				moduleId : index ,
			  			},
			  			dataType:'json',
			  			async : true,
			  			success: function(data){ 
			  				$data = data.templateModuleRelContents;

			  				$.each($data ,function(i,templateModuleRelContents){
			  				$("input[name='mediaTypeId']").each(function(){
			  						 if($(this).val()  == templateModuleRelContents.dataColumnId){
			  							 $(this).attr('checked',true);
			  						 }
			  					 });
			  				 });
			  			 },
			  			error:function(data){
			  				//alert(JSON.stringify(data));
			  			}
			  	     });
				 }
				$("#queryCKBoxType").find(".checkitem").click(function(){  
						//console.log(this.checked);
					   if(this.checked){
						   $(".checkitem").not(this).attr("checked",false);
					   }
					     
					  }); 
			}
	});

}

//添加管理文章分类
var addManagerArticleType = function(){
	 layer.open({  
        shade: [0.2, '#000', false],  
        type: 2,  
        area: ['666px', '333px'],  
        fix: false, //不固定  
        maxmin: true,  
        title: ['编辑文章分类', false],  
        content: rootPath + '/jsp/webconsole/article/articleTypeManage.jsp',
        scrollbar:false,//是否允许滚动条出现
        btn: ['确认', '取消'],
		}); 
}
//添加管理图册分类
var managePictureAlbumType = function(){
	 layer.open({  
	        shade: [0.2, '#000', false],  
	        type: 2,  
	        area: ['666px', '333px'],  
	        fix: false, //不固定  
	        maxmin: true,  
	        title: ['编辑图册分类', false],  
	        content: rootPath + '/jsp/webconsole/album/albumTypeManage.jsp',
	        scrollbar:false,//是否允许滚动条出现
	        btn: ['确认', '取消'],
			});
}

//添加管理视频分类
var addManagerMediaType = function(){
	 layer.open({  
        shade: [0.2, '#000', false],  
        type: 2,  
        area: ['666px', '333px'],  
        fix: false, //不固定  
        maxmin: true,  
        title: ['编辑视频分类', false],  
        content: rootPath + '/jsp/webconsole/vedio/vedioTypeManage.jsp',
        scrollbar:false,//是否允许滚动条出现
        btn: ['确认', '取消'],
		}); 
}	



