$(function(){
	if(rst != 'null' && rst !=''){
		if(rst == 'success'){
			layer.alert('添加或修改文章成功！',{icon: 1});
		}else{
			layer.alert('添加或修改文章失败！', {icon: 2});
		}
		rst = '';
	}
	$("#createTimeD1").on("click",function(){
		WdatePicker({
			el: "data3",
			dateFmt: "yyyy-MM-dd"
		});
	});
	
	//失去焦点重新加载查询数据(标题搜索)
	$('#articleTitle').on('blur',function(){
		articleDataGrid();
	});
	//失去焦点重新加载查询数据(分类)
	 $('#articleTypeId').on('change', function(){
		 articleDataGrid();
     }); 
	 //初始化设置
	ArticleTypeQueryAll.initSettingBtn();
	//复选框
	ArticleTypeQueryAll.drawArticleTypesDataView(1);
	//下拉框
	ArticleTypeQueryAll.drawArticleTypesDataView(0);
	//查询数据(分页)
	articleDataGrid();
	
	
	//批量删除文章
	 $('#batchDelBtn').on('click', function(){
		 if(checkTableCkSelected()){
			 layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
				 var ids = getArticleIds();
				 bathEditArticle({dataStatus : 1 , articleIdsStr : ids }, '删除');
				 //删除数据
				  layer.close(index);
			});
		 }
    });
	//录取时间修改
	 $('#createTimeBtn').on('click',function(){
		 editPropertys('createTime',"录取时间");
	 });
	 //文章来源sourceBtn
	 $('#sourceBtn').on('click',function(){
		 editPropertys('articleSource',"文章来源");
	 });
	 //文章作者authorBtn
	 $('#authorBtn').on('click',function(){
		 editPropertys('articleAuthor',"文章作者");
	 });
	 //设置为保存按钮（提示未选择时的弹窗）
	 $('#setSaveBtn').on('click', function(){
		 if(checkTableCkSelected()){
			 if($("#queryArticleCKBoxType").find("input[name='articleTypeId']:checked").length <= 0){
				 layer.alert('请选择您要设置的文章类型', {icon: 2});
			 }else{
				 setArticleRelTypes();
				 clearSelected();
			 }
		 }
     });
	 //设置为取消按钮
	 $('#setCancleBtn').on('click',function(){
		
			 if($("#queryArticleCKBoxType").find("input[name='articleTypeId']:checked").length > 0){
				 layer.alert('确定取消设置文章分类？', {icon: 2});
			 }else{
				 closeBtn:0;
			 }
		 
	 });
	
	 $('#setCancleBtn').on('click', function(){
	     clearSelected();
 });
});
	
	//检查表格中行是否有选中行
	var checkTableCkSelected = function(){
		var flag = true;
		 if($("input[name='articleId']:checked").length <= 0){
			 layer.alert('请选择您要操作的数据', {icon: 2});
			 flag = false;
		 }
		 return flag;
	};
	//批量修改的操作
	//编辑属性
	var editPropertys = function(optItem,title){
		  $("#data3").val("");
		  $("#data2").val("");
		 if(checkTableCkSelected()){
			 $("#pName").html(title+":");
			 if(optItem == 'createTime'){
				 $("#data1").show();
				 $("#data2").hide();
			 }else{
				 $("#data1").hide();
				 $("#data2").show();
			 }
			 layer.open({
				  type: 1,
				//  skin: 'layui-layer-lan',
				  area: ['300px', '200px'],
				  title: [title, 'font-size:14px;'],
				  closeBtn:1,//右上角关闭按钮样式
				  shade: 0.3, //遮罩为0表示不显示
				  shadeClose :true,//是否点击遮罩关闭
				  scrollbar:false,//是否允许滚动条出现
				  content: $('#updateDate') ,
				  //按钮与回调
				  btn: ['确认', '取消'],
				  yes: function(index, layero){
					  var data = $("#data2").val();
					  if(optItem == 'createTime'){data = $("#data3").val();}
					  if($.trim(data ) == null  || data == ""){
						  layer.alert('请填写'+title, {icon: 2});
					  }else{
							 var ids = getArticleIds();
							 if(optItem == 'createTime'){
								 bathEditArticle({editTime : data , articleIdsStr : ids }, '修改' );
							 }else if(optItem == 'articleAuthor'){
								 bathEditArticle({articleAuthor : data , articleIdsStr : ids }, '修改' );
							 }else if(optItem == 'articleSource'){
								 bathEditArticle({articleSource : data , articleIdsStr : ids }, '修改' );
							 }
							  $("#data3").val("");
							  $("#data2").val("");
					  }
				    layer.close(index);
				  },
				  cancel: function(index){ 
					  $("#data3").val("");
					  $("#data2").val("");
					  layer.close(index);
				}
				});
		 }
	};
	//批量修改文章编辑的操作
var bathEditArticle = function(data,tipstr){
	$.ajax({
		type:'post',
		url:rootPath+'/contents/article/editUpdateArticle.do',
		data:data,
		dataType:'json',
		async : true,
		success:function(data){
			if(data.rst == 0){
				articleDataGrid();
				if(tipstr !=null){
					layer.alert(tipstr + '文章数据保存成功!',{icon:1});
				}
			}else{
				if(tipstr !=null){
					layer.alert(tipstr + '文章数据保存失败!',{icon:2});
				}
			}
		},
		error:function(data){
			//alert(JSON.stringify(data));
		}
	});
	
	
	
};
	
	//批量设置文章与类型关联的操作
	//首先获取文章ID字符串和文章类型ID字符串
	var getArticleIds = function(){
		var articleIdStr = "";
			$("input[name='articleId']:checked").each(function(){
				articleIdStr += $(this).val() + ",";
			});
			if(articleIdStr.length > 0){articleIdStr = articleIdStr.substring(0, (articleIdStr.length -1));}
			return articleIdStr;
	};
	var getArticleTypeIds = function(){
		var articleTypeIdStr = "";
		$("#queryArticleCKBoxType").find("input[name='articleTypeId']:checked").each(function(){
				articleTypeIdStr += $(this).val() + ",";
			});
			if(articleTypeIdStr.length > 0){articleTypeIdStr = articleTypeIdStr.substring(0, (articleTypeIdStr.length -1));}
			return articleTypeIdStr;
	};
	//第二步:将获取到的ID提交到后台进行操作
	var setArticleRelTypes = function(){
		$.ajax({
			type:'post',
			url:rootPath+'/contents/article/setArticleRelTypes.do',
			data:{
				articleIds:getArticleIds(),
				articleTypeIds:	getArticleTypeIds()
			},
			dataType:'json',
			async : true,
			success:function(data){
				if(data.srt == 0){
					articleDataGrid();
					layer.alert('关联文章类型数据保存成功!',{icon:1});
				}else{
					layer.alert('关联文章类型数据失败!',{icon:2});
				}
			},
			error:function(){
				//alert(JSON.stringify(data));
			}
			
		});
	};
	/////??????
	var clearSelected = function(){
		 $("#queryArticleCKBoxType").find("input[name='articleTypeId']:checked").each(function(){
			    $(this).attr('checked',false);
		 });
		 $("#setInfoContent").removeClass('active');
	};
	//删除文章类型关联
var delArticleTypeRel = function(id){
	layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'post',
			url:rootPath+'/contents/article/deleteArticleRelTypes.do',
			data:{articleRelTypeId:id},
			dataType:'json',
			async:true,
			success:function(data){
				if(data.rst == 0){
					layer.alert('删除文章分类成功!',{icon:1});
					$("#r"+id).remove();
				}else{
					layer.alert('删除文章分类失败!',{icon:2});
				}
			},
			error:function(data){
				//alert(JSON.stringify(data));
			}
		});
		layer.close(index);
	});
};	

//置顶操作
var setTop = function(t,id){
	var value = 0;
    if(!$(t).hasClass("btn-top btn-top-selected")){
    	value = 1;
    	//$(t).addClass("btn-top-selected");
    }else{
    	value = 0;
    	//$(t).removeClass("btn-top-selected");
    }
    bathEditArticle({articleTop : value , articleIdsStr : id } , null);
};
var reView = function(id){
	layer.confirm('您确定审核通过当前数据', {icon: 3, title:'提示'}, function(index){
		bathEditArticle({articleReview : 1 , articleIdsStr : id } , "审核");
		 //删除数据
		  layer.close(index);
	});
};
	
	
	/**************************************分页*********************************************/
	var articleDataGrid = function(){
		$.ajax({
			type:'post',
			url:rootPath+'/contents/article/queryArticleByPage.do',
			data:$("#articleForm").serialize(),
			dataType:'json',
			async : true,
			success: function(data){
				$('#articleDataGrid').empty();
				var currentPage = data.currentPage;
				var total = data.total;
				var pageCount =data.pages;
				$('#article_data_grid').tmpl(eval(data)).appendTo("#articleDataGrid");
				ArticleTypeQueryAll.initDataGridContentFun();//设置表格内js效果
				drawPage(currentPage,pageCount);
			},
			error:function(data){
				//alert(JSON.stringify(data));
			}
		});
	};
	
	var drawPage = function(pagenumber,pageCount){
		var data = {'pagecount':pageCount};
		$("#pager").pager({pagenumber: pagenumber, pagecount: data.pagecount,  buttonClickCallback: pageClickCallback});
	}
	
	
	//回调函数
	
	
	pageClickCallback = function(pageclickednumber){
		$("#currentPage").val(pageclickednumber);
		articleDataGrid();
	};
