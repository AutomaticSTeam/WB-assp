/**
 * this js  aims to deal vedio   
 * @type
 * @author :songhx@sxw100.com
 */
var top = window.parent;
$(function(){
	//加载视频分类信息,与下面selectedMediaType冲突，注掉
	 //ContentsManage.drawMediaTypesDataView(1);
	 //不管有没id先取视频分类
	 //if(mediaId != null && mediaId != 'null' && mediaId != '' ){
	selectedMediaType(mediaId);
	if(typeof moduleId != "undefined" && moduleId !=null && moduleId !="" && moduleId !=''){
		selectDataColumnId(moduleId);
	}
	
	 //}
	//页面编辑时取消按钮
	$("#cancleBtns").click(function(){
		top.layer.closeAll();
		//top.layer.close(top.SiteManager.tempData.layerIndex);
	});
	
	
	$("#mediaForm").Validform(
			{
				tiptype:3,
				btnSubmit:"#saveBtn",
				btnReset:"#cancleBtn"
			});
	
	//提交页面新增的视频
	$("#mediasForm").Validform(
			{
				tiptype:3,
				btnSubmit:"#saveBtns",
			});
	
	/*var templateCode=$("#templateCode").val();*/
	var siteCode = $("#siteCode").val();
	//文件上传
	var uploader  = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',//官网上默认是gears,html5,flash,silverlight,browserplus
		browse_button : 'chooseMedia', //这个是点击上传的html标签的id,可以a,button等等
		container: document.getElementById('uploadMedia'), //这个是容器的地址，
		max_file_size : '10000mb', //这是文件的最大上传大小
		multi_selection:false, //是否开启多文件上传
		//dragdrop:false, //是否支持拖拽 jquery部件属性
		multiple_queues:false,//是否可以多次上传jquery部件属性
		flash_swf_url : rootPath+'/static/plupload-2.1.9/js/Moxie.swf',
	/*	url : rootPath+'/fileUpLoad/upLoad.do?templateCode='+templateCode+'&&dirFolderName=medias' ,*/ //服务器页面地址，后面跟你想传递的参数
		url : rootPath+'/fileUpLoad/upLoad.do?siteCode='+siteCode+'&&dirFolderName=medias' ,
		filters : [ {title : "视频文件", extensions : "mp4,3gp,avi"} ], //文件过滤
	
	
	init: {
		PostInit: function() {
			document.getElementById('filelist').innerHTML = '';

			document.getElementById('startUpload').onclick = function() {
				$("#startUpload").html("正在上传");
				uploader.start();
				return false;
			};
		},
		//文件添加时，会在容器里显示待上传的文件列表
		FilesAdded:function(up, files) {
			plupload.each(files, function(file) {
				document.getElementById('filelist').innerHTML += "<p style='width:100%'   data-value='0' class='uploadInfo' id='" + file.id + "'>"+file.name+"</p>\n" +
																 "<div class='uploadProgress' max='100' value='0' class='jquery'>\n" +
																 "	<div class='progress-bar'>\n" +
																 "		<span style='width: 0%' id='rogress_speed'>0%</span>\n" +
																 "	</div>\n" +
																 "</div>";
			});
			$("#startUpload").css("display","inline-block");
			$("#chooseMedia").hide();
		},

		//文件上传的进度
		UploadProgress: function(up, file) {
			if(file.percent<100){
				$(".uploadProgress").attr("value",file.percent);
				$(".uploadInfo").attr("data-value",file.percent);
				$("#rogress_speed").css("width",file.percent+"%");
				$("#rogress_speed").html(file.percent+"%");
			};
			
		},

		//文件上传成功后的总计
		UploadComplete:function(up, files){
			
			$(".uploadProgress").attr("value",100);
			$(".uploadInfo").attr("data-value",100);
			$("#rogress_speed").css("width","100%");
			$("#rogress_speed").html("100%");
			
			$("#startUpload").html("上传成功");
			$("#canselUpload").css("display","inline-block");
			document.getElementById('canselUpload').onclick = function() {
				$("#filelist").empty();
				$("#mediaUrl").val("");
				$("#mediaSize").val("");
				$("#mediaSuffix").val("");
				$("#chooseMedia").css("display","inline-block");
				$("#startUpload").html("开始上传");
				$("#startUpload").hide();
				$("#canselUpload").hide();
			}; 
			
		},
		FileUploaded:function(up,file,result){
			var data_json=result.response;
			var data = JSON.parse(data_json).uploadRst[0]; 
			$("#mediaUrl").val(data.fileServerPath);
			$("#mediaSize").val(data.fileSize);
			$("#mediaSuffix").val(data.fileType);
		},
		Error: function(up, err) {
			if(err.code=='-601'){
				alert("上传图片只能添加mp4,3gp,avi格式文件");
			}else {
				alert("上传失败，错误代码："+err.code + "   错误信息: " + err.message+"请重试。。。");
			}
		}
	}
	});
	/*if(templateCode!=""&&templateCode!=null){
		uploader.init();
	}*/
	if(siteCode != "" && siteCode != null){
		uploader.init();
	}
	//添加图片按钮事件
	$("#addImgBtn").click(function(){
		
		layer.open({
			  type: 2, 
			  title: ['添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）', 'font-size:14px;'],
			  content: [rootPath+'/jsp/common/pop/uploadImg.jsp?singleupload=true&&chooseSingle=true', 'no'],
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
	
	//删除图片
	$("#delImage").click(function(){
		$("#thumbImgId").attr("src",rootPath + "/images/thumb.png" );
		$("#mediaAttachmentImg").val("");
		$("#addImgBtn").show();
	});
	
});

//选中关联类型
var selectedMediaType = function(id){
		 $.ajax({
				url:rootPath+'/contents/media/queryMediaTypes.do',
				data:{dataStatus : 0},
				dataType:'json',
				async : true,
				success: function(data){ 
					$data = data.mediaTypes;
					 var resultHtml = "";
					 $.each($data ,function(i,mediaType){
						 resultHtml +='  <span class="checkbox"><input type="checkbox" name="mediaTypeId" id="" value="'+mediaType.mediaTypeId+'">'+mediaType.mediaTypeName+' </span>';
					 });
					$("#mediaCkBoxList").html( resultHtml); //复选框
					if(id != null && id != 'null' && id != '' ){
						$.ajax({
							type:'post',
							 url:rootPath+'/contents/media/queryMediaTypeRels.do',
							data:{
								mediaId : id ,
							},
							dataType:'json',
							async : true,
							success: function(data){ 
								$data = data.mediaRelTypes;
								$.each($data ,function(i,mediaRelType){
									$("input[name='mediaTypeId']").each(function(){
										 if($(this).val()  == mediaRelType.mediaTypeId){
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

}

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
					 resultHtml +='  <span class="checkbox"><input type="checkbox" name="mediaTypeId" id="" value="'+mediaType.mediaTypeId+'">'+mediaType.mediaTypeName+' </span>';
				 });
				$("#mediaCkBoxList").html( resultHtml); //复选框
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
					/*$("input[name='articleTypeId']").each(function(){
 						 if($(this).val()  == index){
 							 $(this).attr('checked',true);
 						 }
 					 });*/
				 }
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
		$("#mediaAttachmentImg").val(pUrls);
		$("#addImgBtn").hide();
	}
	//var pNames=body.find("input[id='pictureNames']").val();
	layer.close(index);
  }

