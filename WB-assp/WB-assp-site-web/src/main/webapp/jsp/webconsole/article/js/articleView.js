$(function(){

	//加载文章分类信息
	ArticleTypeQueryAll.drawArticleTypesDataView(1);
	 if(articleId != null && articleId != 'null' && articleId != '' ){
		 selectedArticleType(articleId);
	 }
	
	 
	$("input,textarea").each(function(){ 
	     $(this).attr('readonly','readonly'); 
	});
	//实例化uEditor
	var ue = UE.getEditor('editor',{
   	           autoHeightEnabled: false,//自动增高
   	           lang:"zh-cn",
   	           focus:false ,//初始化时，是否让编辑器获得焦点true或false
   	           initialFrameWidth:800, //初始化编辑器宽度,默认1000
   	           initialFrameHeight:160 , //初始化编辑器高度,默认320
   	           readonly : false ,//编辑器初始化结束后,编辑区域是否是只读的，默认是false
   	           imagePopup:false   ,  //图片操作的浮层开关，默认打开
   	           emotionLocalization:false, //是否开启表情本地化，默认关闭。若要开启请确保emotion文件夹下包含官网提供的images表情文件夹
   	       });
	 
});

//选中关联类型
var selectedArticleType = function(id){
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
								  +'<input type="checkbox" name="articleTypeId" value="'+articleType.articleTypeId+'">'+articleType.articleTypeName+'</span>';
		    	   });
				$("#queryArticleCKBoxType").html(resultHtml);
				 $.ajax({
			  			type:'post',
			  			 url:rootPath+'/contents/article/queryArticleTypeRels.do',
			  			data:{
			  				articleId : id ,
			  			},
			  			dataType:'json',
			  			async : true,
			  			success: function(data){ 
			  				$data = data.articleRelTypes;
			  				$.each($data ,function(i,articleRelType){
			  					$("input[name='articleTypeId']").each(function(){
			  						 if($(this).val()  == articleRelType.articleTypeId){
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
			
			});
	
}

//设置附图
var setImg =  function(index, layero){
	var body = layer.getChildFrame('body', index);
	var pUrls=body.find("input[id='pictureUrls']").val();
	if(pUrls != "" && pUrls.length > 0){
		pUrls = pUrls.substring(0,(pUrls.length - 1));
		$("#thumbImgId").attr("src",pUrls);
		$("#articleAttachmentImg").val(pUrls);
		$("#addImgBtn").hide();
	}
	//var pNames=body.find("input[id='pictureNames']").val();
	layer.close(index);
  };