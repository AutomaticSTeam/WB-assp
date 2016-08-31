/**
 * 
 * 
 */
var ArticleTypeQueryAll ={

		 drawMediaTypesDataView : function(articleType){
	    	   $.ajax({
					url:rootPath+'/contents/media/queryMediaTypes.do',
					data:{dataStatus : 0},
					dataType:'json',
					async : true,
					success: function(data){
						$data =  data.articleTypes;
						if(articleType == 0){
							$("#queryArticleTypeOne").html(ArticleTypeQueryAll.drawMediaTypesOptionView($data));
						}else{
							$("#queryArticleType").html(ArticleTypeQueryAll.drawMediaTypesCkBoxView($data));
						}
//						var html = '';
//						 $.each($data ,function(i,articleType){
//							html +='<span class="checkbox">'
//								 +'<input type="checkbox" name="articleTypeId" value="'+articleType.articleTypeId+'">'+articleType.articleTypeName+'</span>';
//						 });
//						$("#queryArticleType").html(html);
//					},
//					error:function(data){
//						//alert(JSON.stringify(data));
//					}
					}
					});
	       },
	       //下拉列表文章分类视图
	       drawMediaTypesOptionView : function($data){
	    	   var resultHtml = "";
	    	   $.each($data,function(i,articleType){
	    		   resultHtml +='<option value="'+articleType.articleTypeId+'">'+articleType.articleTypeName+'</option>';
	    	   });
	    	   return resultHtml;
	       },
	       //复选框文章分类视图
	       drawMediaTypesCkBoxView :function($data){
	    	   var resultHtml ="";
	    	   $.each($data,function(i,articleType){
	    		   resultHtml +='<span class="checkbox">'
							  +'<input type="checkbox" name="articleTypeId" value="'+articleType.articleTypeId+'">'+articleType.articleTypeName+'</span>';
	    	   });
	    	   return resultHtml;
	       },
};