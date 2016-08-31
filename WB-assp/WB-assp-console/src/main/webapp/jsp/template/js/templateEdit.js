$(function(){
	if(rst != 'null' && rst !=''){
		if(rst == 'success'){
			layer.alert('添加或修改视频成功！', {icon: 1});
			parent.changeLeftMenuCss(4);
		}else{
			layer.alert('添加或修改视频失败！', {icon: 2}); 
		}
		rst = '';
	}
	$("#createTimeD").on("click",function(){
		WdatePicker({
			el: "data1",
			dateFmt: "yyyy-MM-dd"
		});
	});
	ContentsManage.initSettingBtn();
	//加载视频分类信息
	 ContentsManage.drawMediaTypesDataView(3); //下拉选框
	//加载视频分类信息
	 ContentsManage.drawMediaTypesDataView(1); //复选框
	 mediaDataGrid();
	 
	 
	 $('#mediaName').on('blur', function(){
		 mediaDataGrid();
     });
	 
	 $('#mediaSelect').on('change', function(){
		 mediaDataGrid();
     });
	 
	 //批量删除文章
	 $('#batchDelBtn').on('click', function(){
		 if(checkTableCkSelected()){
			 layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
				 var ids = getMediaIds();
				 batchEditMedia({dataStatus : 1 , templateIdsStr : ids }, '删除');
				 //删除数据
				  layer.close(index);
			});
		 }
     });
	 
	 //录取时间修改
	 $('#createTimeBtn').on('click', function(){
		 editPropertys('createTime',"录入时间");
     });
	 
	 //视频来源
	 $('#sourceBtn').on('click', function(){
		 editPropertys('mediaSource',"视频来源");
     });
	 
	 //视频作者
	 $('#authorBtn').on('click', function(){
		 editPropertys('mediaAuthor',"视频作者");
     });
	 
	 $('#setSaveBtn').on('click', function(){
		 if(checkTableCkSelected()){
			 if($("#mediaCkBoxList").find("input[name='mediaTypeId']:checked").length <= 0){
				 layer.alert('请选择您要设置的视频类型', {icon: 2});
			 }else{
				 setMediaRelTypes();
				 clearSelected();
			 }
		 }
     });
	 
	 $('#setCancleBtn').on('click', function(){
		     clearSelected();
     });
	 
	 	//添加视频按钮
		$("#createOrEditVedio").click(function(){
			location.href=rootPath+"/jsp/webconsole/vedio/createOrEditVedio.jsp";
			parent.changeLeftMenuCss(3);
		})
	 
});

var clearSelected = function(){
	 $("#mediaCkBoxList").find("input[name='mediaTypeId']:checked").each(function(){
		    $(this).attr('checked',false);
	 });
	 $("#setInfoContent").removeClass('active');
}

//批量设置视频与类型关联
var setMediaRelTypes = function(){
	 $.ajax({
			type:'post',
			 url:rootPath+'/contents/media/addMediaTypeRels.do',
			data:{
				mediaIds : getMediaIds(),
				mediaTypeIds : getMediaTypeIds()
			},
			dataType:'json',
			async : true,
			success: function(data){ 
				if(data.rst == 0){
					mediaDataGrid();
					layer.alert('关联视频数据成功！', {icon: 1});
				}else{
				layer.alert( '关联视频数据失败', {icon: 2});
				}
			 },
			error:function(data){
				//alert(JSON.stringify(data));
			}
	     });
}

//获取视频类型id字符串
var getMediaTypeIds = function(){
	var mediaTypeIdStr = "";
	 $("#mediaCkBoxList").find("input[name='mediaTypeId']:checked").each(function(){
		 mediaTypeIdStr += $(this).val() + ",";
	 });
	 if(mediaTypeIdStr.length > 0){mediaTypeIdStr = mediaTypeIdStr.substring(0, (mediaTypeIdStr.length -1));}
	 return mediaTypeIdStr;
}

//获取视频id字符串
var getMediaIds = function(){
	var mediaIdStr = "";
	 $("input[name='templateId']:checked").each(function(){
		 mediaIdStr += $(this).val() + ",";
	 });
	 if(mediaIdStr.length > 0){mediaIdStr = mediaIdStr.substring(0, (mediaIdStr.length -1));}
	 return mediaIdStr;
}

//批量处理视频属性内容
var batchEditMedia = function(data , tipStr){
	 $.ajax({
			type:'post',
			 url:rootPath+'/template/editTemplateProperties.do',
			data:data,
			dataType:'json',
			async : true,
			success: function(data){ 
				if(data.rst == 0){
					mediaDataGrid();
					if(tipStr != null){layer.alert(tipStr + '视频数据成功！', {icon: 1});}
				}else{
					if(tipStr != null){layer.alert(tipStr +  '视频数据失败', {icon: 2});}
				}
			 },
			error:function(data){
				//alert(JSON.stringify(data));
			}
	     });
}

//检查表格中行是否有选中行
var checkTableCkSelected = function(){
	var flag = true;
	 if($("input[name='templateId']:checked").length <= 0){
		 layer.alert('请选择您要操作的数据', {icon: 2});
		 flag = false;
	 }
	 return flag;
}

//编辑属性
var editPropertys = function(optItem,title){
	  $("#data1").val("");
	  $("#data2").val("");
	 if(checkTableCkSelected()){
		 $("#pName").html(title+"：");
		 if(optItem == 'createTime'){
			 $("#data1").show();
			 $("#createTimeD").show();
			 $("#data2").hide();
		 }else{
			 $("#data1").hide();
			 $("#createTimeD").hide();
			 $("#data2").show();
		 }
		 layer.open({
			  type: 1,
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
				  if(optItem == 'createTime'){data = $("#data1").val();}
				  if($.trim(data ) == null  || data == ""){
					  layer.alert('请填写'+title, {icon: 2});
				  }else{
						 var ids = getMediaIds();
						 if(optItem == 'createTime'){
							 batchEditMedia({editTime : data , mediaIdsStr : ids }, '修改' );
						 }else if(optItem == 'mediaAuthor'){
							 batchEditMedia({mediaAuthor : data , mediaIdsStr : ids }, '修改' );
						 }else if(optItem == 'mediaSource'){
							 batchEditMedia({mediaSource : data , mediaIdsStr : ids }, '修改' );
						 }
						  $("#data1").val("");
						  $("#data2").val("");
				  }
			    layer.close(index);
			  },
			  cancel: function(index){ 
				  $("#data1").val("");
				  $("#data2").val("");
				  layer.close(index);
			}
			});
	 }
}


//删除视频关联类型
var delMediaTypeRel  =  function(id){
	 layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
		 $.ajax({
				type:'post',
				 url:rootPath+'/contents/media/delMediaTypeRel.do',
				data:{mediaRelTypeId : id},
				dataType:'json',
				async : true,
				success: function(data){ 
					if(data.rst == 0){
						layer.alert('删除视频分类成功！', {icon: 1});
						$("#r"+id).remove();
					}else{
						layer.alert('删除视频分类失败', {icon: 2});
					}
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     });
		  layer.close(index);
	});
}

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
	batchEditMedia({isTop : value , mediaIdsStr : id } , null);
}

var reView = function(id){
	 layer.confirm('您确定审核通过当前数据', {icon: 3, title:'提示'}, function(index){
		 batchEditMedia({isReview : 1 , mediaIdsStr : id } , "审核");
		 //删除数据
		  layer.close(index);
	});
	
}

/************************************************分页列表开始**************************************************************/
var mediaDataGrid = function(){
	$.ajax({
		type:'post',
		 url:rootPath+'/template/queryTemplateByPage.do',
		data:$("#mediaForm").serialize(),
		dataType:'json',
		async : true,
		success: function(data){ 
			$('#mediaDataGrid').empty();
			var currentPage = data.currentPage;
			var total =data.total;
			var pageCount = data.pages;
			$('#media_data_grid').tmpl(eval(data)).appendTo("#mediaDataGrid");
			ContentsManage.initDataGridContentFun();  //设置表格内js效果
			drawPage(currentPage,pageCount);
		 },
		error:function(data){
			//alert(JSON.stringify(data));
		}
     });
}
var drawPage = function(pagenumber,pageCount){
	var data = {'pagecount':pageCount};
	$("#pager").pager({ pagenumber: pagenumber, pagecount:data.pagecount, buttonClickCallback: pageClickCallback});
}

//回调函数
pageClickCallback = function(pageclickednumber) {
	$("#currentPage").val(pageclickednumber);
	mediaDataGrid();
}
/************************************************分页列表结束**************************************************************/
