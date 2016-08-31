var top = window.parent;
$(function(){
	//保存
	$("#saveBtn").bind("click",function(){
		saveArticle();
	});
	//复选框文章	,已和下文selectedArticleType冲突，则注掉
	//ArticleTypeQueryAll.drawArticleTypesDataView(1);
	//看文章里没下拉列表框，注掉
	//ArticleTypeQueryAll.drawArticleTypesDataView(0);
	ArticleTypeQueryAll.initSettingBtn();
	//验证文章ID,无论有没有id都加载，再在里面判断id
	//if(articleId != null && articleId != "" && articleId != ''){
	selectedArticleType(articleId);
	if(moduleId !=null && moduleId !="" && moduleId !=''){
		selectedAddArticleType(moduleId);
	}
	
	//}
	//表单验证
	$("#articleForm").Validform({
		tiptype:3,
		btnSubmit:"#saveBtn",
		btnReset:"#cancleBtn",
		beforeSubmit:function(curform){
			var articleWordNum =getCount();
			$("#articleWordNum").val(articleWordNum);
			//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话表单将不会提交;	
		}
	});
	//页面中编辑文章提示表单
	$("#articlesForm").Validform({
		tiptype:3,
		btnSubmit:"#saveBtns",
		beforeSubmit:function(curform){
			var articleWordNum =getCount();
			$("#articleWordNum").val(articleWordNum);
			//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话表单将不会提交;	
		}
	});
	$("#cancleBtns").click(function(){
		top.layer.closeAll();
		//top.layer.close(top.SiteManager.tempData.layerIndex);
	});

	//添加图片按钮事件
	$("#addImgBtn").click(function(){
		
		layer.open({
			  type: 2, 
			  title: ['添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）', 'font-size:14px;'],
			  content: [rootPath+'/jsp/common/pop/uploadImg.jsp?singleupload=true&&chooseSingle=true&&templateCode=10001', 'no'],
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
			  	   },
			});
		
	});
	
	//实例化uEditor
	var ue = UE.getEditor('editor',{
   	           autoHeightEnabled: false,//自动增高
 	           lang:"zh-cn",
   	           focus:false ,//初始化时，是否让编辑器获得焦点true或false
   	           initialFrameWidth:'100%', //初始化编辑器宽度,默认1000
   	           initialFrameHeight:'100%', //初始化编辑器高度,默认320
   	           readonly : false ,//编辑器初始化结束后,编辑区域是否是只读的，默认是false
   	           imagePopup:false   ,  //图片操作的浮层开关，默认打开
   	           emotionLocalization:false, //是否开启表情本地化，默认关闭。若要开启请确保emotion文件夹下包含官网提供的images表情文件夹
   	           wordCount:false
	});
	
	//删除图片
	$("#delImage").click(function(){
		$("#thumbImgId").attr("src",rootPath + "/images/thumb.png" );
		$("#articleAttachmentImg").val("");
		$("#addImgBtn").show();
	});
	
});

//获得文章字数
function getCount(){
	return UE.getEditor('editor').getContentLength();
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

//选中关联类型,此处和query_article_type.js中的初始化重复，再此采用下文初始化
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
				//若存在id，则取相关分类
				if(id != null && id != "null" && id != ''){
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
			}
			
			});
  	
  };
  
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
								  +'<input type="checkbox" name="articleTypeId" value="'+articleType.articleTypeId+'">'+articleType.articleTypeName+'</span>';
		    	   });
				$("#queryArticleCKBoxType").html(resultHtml);
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
			}
			
			});
  	
  };

function saveArticle(){
	$.ajax({
		url:rootPath+"/contents/article/addArticle.do",
		type:"post",
		dataType:"json",
		data:$("#articleForm").serialize(),
		success:function(data){
			if(data.reasult == 'success'){
				layer.alert("保存成功。。。",function(){
					location.href=rootPath+"/jsp/webconsole/article/articleManage.jsp";
					parent.changeLeftMenuCss(4);
				});
			}else if(data.reasult == 'fail'){
				alert("保存失败。。。");
			}
		},
		error:function(){
			alert("创建相册失败");
		}
	})
}


