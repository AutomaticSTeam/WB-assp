/**
 * @author lin
 */
var _ablumId;
$(function() {
	//保存
	$("#save_btn_pic").bind("click",function(){
		saveAblum();
	});
	_ablumId=getUrlParam("ablumId");
	
	//初始化分类
	AlbumTypeQueryAll.drawAlbumTypesDataView(1);
	if(_ablumId!=""&&_ablumId!=null){
		selectedAblumType(_ablumId);
	}
	//取消
	$("#cancel_btn_pic").bind("click",function(){
		if(_ablumId!=""&&_ablumId!=null){
			layer.confirm('您确定放弃保存当前数据？', {icon: 3, title:'提示'}, function(index){
				location.href=rootPath+"/jsp/webconsole/album/albumManage.jsp";
			});
		}else{
			clearPicContent();
			clearAblumContent();
			$("#pic_content").hide();
		}
	});
	
	if(_ablumId!=""&&_ablumId!=null){
		$(".column-tit .tit").html("编辑图册");
		//回显
		$.ajax({
			url:rootPath+"/contents/album/queryAlbumById.do",
			data:{albumId:_ablumId},
			dataType:"json",
			type:"post",
			success:function(data){
				var albumName=data.albumName;
				if(albumName!=""){
					$("#albumName").val(albumName);
					$.ajax({
						url:rootPath+"/contents/album/queryPicByAlbumId.do",
						data:{albumId:_ablumId},
						dataType:"json",
						type:"post",
						success:function(data){
							if(data.length>0){
								handleData(data,"1");
							}
						}
					})
				}
			},
			error:function(){
				
			}
		})
	}
	var templateCode="Consule-album"
	//添加图片按钮事件
	$(".addImgBtn").click(function(){
		
		layer.open({
			  type: 2, 
			  title: ['添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）', 'font-size:14px;'],
			  content: [rootPath+'/jsp/common/pop/uploadImg.jsp?singleupload=false&&chooseSingle=false&&templateCode=10001', 'no'],
			 // skin: 'demo-class'
			  area: ['640px', '430px'],//定义大小
			  closeBtn:1,//右上角关闭按钮样式
			  shade: 0.3, //遮罩为0表示不显示
			  shadeClose :true,//是否点击遮罩关闭
			  scrollbar:false,//是否允许滚动条出现
			  //按钮与回调
			  btn: ['确认', '取消'],
			  yes: function(index, layero){
				  	OK(index, layero);
			  	   }
			});
		
	})
	//实例化uEditor
	var ue = UE.getEditor('editor',{
   	 toolbars: [[
   	             'fullscreen', 'source', '|', 'undo', 'redo', '|',
   	             'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
   	             'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
   	             'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
   	             'directionalityltr', 'directionalityrtl', 'indent', '|',
   	             'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
   	             'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
   	             'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
   	             'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
   	             'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
   	             'print', 'preview', 'searchreplace', 'drafts', 'help'
   	         ]],
   	           autoHeightEnabled: false,//自动增高
   	           lang:"zh-cn",
   	           focus:false ,//初始化时，是否让编辑器获得焦点true或false
   	           initialFrameWidth:"100%", //初始化编辑器宽度,默认1000
   	           initialFrameHeight:300 , //初始化编辑器高度,默认320
   	           readonly : false ,//编辑器初始化结束后,编辑区域是否是只读的，默认是false
   	           autoFloatEnabled:false,//是否固定工具栏位置（默认为true，可能引起页面下拉到底调动）
   	           imagePopup:false    //图片操作的浮层开关，默认打开
   	       });
	
	//表单验证
	$(".albumForm").Validform({
		tiptype:3
		//添加提交按钮会造成按钮点击时自动submit，页面会刷新
	});
	
	//验证ueditor
	ue.addListener("blur", function (type, event) {
		$("#ueditor_Valid").show();
    });
	
	$("#albumName").change(function(){
		var albumName=$("#albumName").val();
		if(albumName!=""){
			$('#save_btn_pic').unbind("click");
			//保存
			$("#save_btn_pic").bind("click",function(){
				saveAblum();
			})
		}else{
			 $('#save_btn_pic').unbind("click");
		}
	})
	
});
//向富文本编辑器添加内容
function setContent(isAppendTo,val) {
	UE.getEditor('editor').setContent(val, isAppendTo);
}

//清空富文本编辑器内容
function clearContent() {
	UE.getEditor('editor').execCommand('cleardoc');

}

//获得富文本编辑器内容
function getContent() {
	return UE.getEditor('editor').getContent();
}

function OK(index, layero){
	
	var body = layer.getChildFrame('body', index);
	var pUrls=body.find("input[id='pictureUrls']").val();
	var pNames=body.find("input[id='pictureNames']").val();
	layer.close(index);
	if(pUrls!=""&&pUrls!=null){
		//发送请求存储图片
		$.ajax({  
		    url:rootPath+'/contents/picture/addPicture.do',  
		    data:{  
		    	pUrls : pUrls,
		    	pNames:pNames
		    },  
		    type:'post',  
		    dataType:'json',  
		    success:function(data){
		    	handleData(data,"0");
		    },  
		    error : function() {  
		         layer.alert("图片保存失败，请重试。。。")
		    }  
		});
	}
	
  }
//处理数据
function handleData(data,type){
    if(data.length>0){
    	//编辑
    	if(type=="1"){
    		for (var i = 0; i < data.length; i++) {
    			var picName=data[i].pictureName;
    			var pId=data[i].pictureId;
    			var Postfix=data[i].imgPostfix;
    			var briefDesc=data[i].briefDesc;
    			var detailDesc=data[i].detailDesc;
    			var pictureUrl=data[i].pictureUrl;
    			var pictureAlbumId=data[i].pictureAlbumId;
    			var pictureType=data[i].pictureType;
    			var hyperlink=data[i].hyperlink;
    			if(hyperlink==""||hyperlink==null){
    				hyperlink="";
    			}
    			var picLi="<li data-tab='"+data[i].pictureId+"' class='picture_list'>\n" +
    			"		<div class='img'>\n" +
    			"			<img src='"+data[i].pictureUrl+"' alt='"+picName+"' title='"+picName+"' id='pic_img'>\n" +
    			"			<div class='move-img'><a href='javascript:;' class='move-left' style='display:none;'></a><a href='javascript:;' class='move-right' style='display:none;'></a></div>\n" +
    			"			<div class='del-img' ><a href='javascript:;' style='display:none;'></a></div>\n" +
    			"		</div>\n" +
    			"  		<div class='picInfo'>\n" +
    			"       <form  action='#' method='post' class='picForm'>"+
    			"			<input type='hidden' class='pId' value='"+pId+"'name='pictureId'>\n" +
    			"			<input type='hidden' class='Postfix' value='"+Postfix+"'name='imgPostfix'>\n" +
    			"			<input type='hidden' class='pName' value='"+picName+"'name='pictureName'>\n" +
    			"			<input type='hidden' class='pBriefDesc'name='briefDesc' value='"+briefDesc+"'>\n" +
    			"			<input type='hidden' class='pDetailDesc'name='detailDesc' value='"+detailDesc+"'>\n" +
    			"			<input type='hidden' class='pUrl'name='pictureUrl' value='"+pictureUrl+"'>\n" +
    			"			<input type='hidden' class='pAlbumId'name='pictureAlbumId' value='"+pictureAlbumId+"'>\n" +
    			"			<input type='hidden' class='pOrder'name='pictureOrder'>\n" +
    			"			<input type='hidden' class='pType'name='pictureType'>\n" +
    			"			<input type='hidden' class='pLink'name='hyperlink' value='"+hyperlink+"'>\n" +
    			"       </form>"+
    			"		</div>"
    			"	</li>";
    			$(".popImgList").append(picLi);
    		}
    	//创建
    	}else{
    		for (var i = 0; i < data.length; i++) {
    			var picName=data[i].pictureName;
    			var pId=data[i].pictureId;
    			var Postfix=data[i].imgPostfix;
    			var picLi="<li data-tab='"+data[i].pictureId+"' class='picture_list'>\n" +
    			"		<div class='img'>\n" +
    			"			<img src='"+data[i].pictureUrl+"' alt='"+picName+"' title='"+picName+"' id='pic_img'>\n" +
    			"			<div class='move-img'><a href='javascript:;' class='move-left' style='display:none;'></a><a href='javascript:;' class='move-right' style='display:none;'></a></div>\n" +
    			"			<div class='del-img' ><a href='javascript:;' style='display:none;'></a></div>\n" +
    			"		</div>\n" +
    			"  		<div class='picInfo'>\n" +
    			"       <form  action='#' method='post' class='picForm'>"+
    			"			<input type='hidden' class='pId' value='"+pId+"'name='pictureId'>\n" +
    			"			<input type='hidden' class='Postfix' value='"+Postfix+"'name='imgPostfix'>\n" +
    			"			<input type='hidden' class='pName' value='"+picName+"'name='pictureName'>\n" +
    			"			<input type='hidden' class='pBriefDesc'name='briefDesc'>\n" +
    			"			<input type='hidden' class='pDetailDesc'name='detailDesc'>\n" +
    			"			<input type='hidden' class='pUrl'name='pictureUrl'>\n" +
    			"			<input type='hidden' class='pAlbumId'name='pictureAlbumId'>\n" +
    			"			<input type='hidden' class='pOrder'name='pictureOrder'>\n" +
    			"			<input type='hidden' class='pType'name='pictureType'>\n" +
    			"			<input type='hidden' class='pLink'name='hyperlink'>\n" +
    			"       </form>"+
    			"		</div>"
    			"	</li>";
    			$(".popImgList").append(picLi);
    		}
    		
    	}
    }
    //绑定左右移动按钮事件
    $(".move-left").click(function() {
    	var li=$(this).parent().parent().parent();
		if(li.prev()){
			li.prev().before(li);
		}
	});
    $(".move-right").click(function() {
    	var li=$(this).parent().parent().parent();
		if(li.next()){
			li.next().after(li);
		}
	});
    //绑定删除按钮事件
    $(".del-img a").click(function() {
    	var nowLi=$(this).parent().parent().parent();
    	var nowLiINdex=nowLi.index();
    	var showLiIndex=$("#nowLi").val();
    	//移除图片后更改数据库字段状态变为删除（0）
    	var pId=nowLi.find(".pId").val();
    	var pName=nowLi.find(".pName").val();
    	$.ajax({  
    	    url:rootPath+'/contents/picture/deletePicture.do',  
    	    data:{  
    	    	pId : pId,
    	    	pName:pName
    	    },
    	    type:'post',  
    	    dataType:'json',  
    	    success:function(data){  
    			if(data==1){
    				nowLi.remove();
    				var lis=$(".popImgList li").length;
    				if(lis==0||lis==""){
    					clearPicContent();
    					//隐藏详情页面
    					$("#pic_content").hide();
    				}
    				if(nowLiINdex==showLiIndex){
    					if(nowLiINdex==0){
    						$("#nowLi").val(0);
    						showContent(0);
    						changeCss(0);
    						
    					}else{
    						var _showLiIndex=showLiIndex-1;
    						$("#nowLi").val(_showLiIndex);
    						showContent(_showLiIndex);
    						changeCss(_showLiIndex);
    					}
    	        	}else{
    	        		$("#nowLi").val(showLiIndex);
    	        		changeCss(showLiIndex);
    	        	}
    				
    			}
    		},
    		error:function(){}
    	})
    	
	});
    //绑定点击图片事件
    $(".popImgList li").click(function(e) { 
    	if (($(e.target).is('.move-img')||$(e.target).is('.del-img'))){
    		
    		//或去点击是的当前内容所属的li 
    		var lastLi=$("#nowLi").val();
    		saveContent(lastLi);
    		//跳转点击对象的li并更新表单内容
    		var nextLi=$(this).index();
    		showContent(nextLi);
		}
    	//存储当前值
    	$("#nowLi").val(nextLi);
    	changeCss(nextLi);
	});
    
    //悬浮效果事件
    $(".popImgList li").hover(function(){
		$(this).find("a").show();
	},function(){
		$(this).find("a").hide();
	})
    //显示图片详情页
    $("#pic_content").show();
    //默认显示第一张图片信息
    showContent(0);
    $("#nowLi").val(0);
    changeCss(0);
}
//存储图片对应内容
function  saveContent(liNum){
	
	var lastLi=liNum;
	if(lastLi!=""){
        var lastObjectLi = $(".popImgList li").eq(lastLi);
        //存储当前内容
        var last_pNameObj=lastObjectLi.find(".pName");
        var pUrl=lastObjectLi.find("#pic_img").attr("src");
        var pLink=$("#pUrl").val();
        //从pNameObj的之后节点中筛选出需要的
        last_pNameObj.val($("#pitureName").val());
        last_pNameObj.nextAll(".pBriefDesc").val($("#pBriefDesc").val());
        last_pNameObj.nextAll(".pUrl").val(pUrl);
        last_pNameObj.nextAll(".pDetailDesc").val(getContent());
        last_pNameObj.nextAll(".pLink").val(pLink);
        
	}
}

//显示图片对应的内容
function showContent(liNum){
	
	if(typeof(liNum) != "undefined"){
		var nextObjectLi=$(".popImgList li").eq(liNum);
		//填充内容前清空内容
		clearPicContent();
		//填充
		var next_pNameObj=nextObjectLi.find(".pName");
		var next_pLinkObj=nextObjectLi.find(".pLink");
		$("#pitureName").val(next_pNameObj.val());
		$("#pUrl").val($(next_pLinkObj).val());
		$("#pBriefDesc").val(next_pNameObj.nextAll(".pBriefDesc").val());
		setContent(false,next_pNameObj.nextAll(".pDetailDesc").val());
	}else{
		//填充内容前清空内容
		clearPicContent();
	}
} 
function clearPicContent(){
	//填充内容前清空内容
	$("#pitureName").val("");
	$("#pBriefDesc").val("");
	$("#pDetailDesc").val("");
	$("#pUrl").val("");
	clearContent();
}

function updataPic(liIndex){
	var nowLi=$(".popImgList li").eq(liIndex);
	nowLi.find(".pOrder").val(liIndex);
	var PicForm=nowLi.find(".picForm");
	$.ajax({ 
		url:rootPath+"/contents/picture/updatePictureResult.do",
		data:PicForm.serialize(),
		type:"post",
		dataType:"json",
		success:function(data){
			
		},
		error:function(data){
			//alert(JSON.stringify(data));
			layer.alert("更新图片失败");
		}
	})
}


//清空图册名与预览图
function clearAblumContent(){
	$("#albumName").val("");
	$(".popImgList li").remove();
}
function saveAblum(){
	
		//获取选中的分类
		var relTypeIDS = '';
		$("input[name='albumTypeId']:checked").each(function(){
			relTypeIDS += ',' + this.value;
		});//
		if(relTypeIDS!="")
			relTypeIDS = relTypeIDS.substring(1);
		var albumName=$("#albumName").val();
		if(albumName!=null&&albumName!=""){
			//检查上传了几张图片，及有几个li标签
			var liNum=$(".popImgList").children("li").length;
			var pnameCheck=0;
			 if(liNum>0){
				//保存前存储当前li的内容
				saveContent($("#nowLi").val());
				//判断所有图片名称是否为空
				for (var i = 0; i < liNum; i++) {
					//创建相册后给个相册id赋值
					var nowLi=$(".popImgList li").eq(i); 
					var _pnameCheck=nowLi.find(".pName").val();
					if(_pnameCheck==""){
						pnameCheck++;
					}
				}
			 }
			 if(pnameCheck>0){
					layer.alert("图片名称不能为空，请检查您的内容");
				}else{
					//检查当前是新建还是编辑
					if(_ablumId==""||_ablumId==null){
						//创建相册
						$.ajax({
							url:rootPath+"/contents/album/addAlbumAndGetprimaryKey.do",
							data:{albumName:$("#albumName").val(),
								relTypeIDS:relTypeIDS},
							type:"post",
							dataType:"json",
							success:function(data){
								var ablumId=data.albumId;
								if(ablumId!=""){
									if(liNum!=0){
											//循环提交图片
											for (var i = 0; i < liNum; i++) {
												//创建相册后给个相册id赋值
												var nowLi=$(".popImgList li").eq(i); 
												nowLi.find(".pAlbumId").val(ablumId);
												//调用方法保存图片
												updataPic(i); 
											}
											//保存完成后清空内容
											clearPicContent();
											clearAblumContent();
											//隐藏详情页面
											$("#pic_content").hide();
									}else{
										clearAblumContent();
									}
								}
								layer.alert("保存成功。。。",function(){
									location.href=rootPath+"/jsp/webconsole/album/albumManage.jsp";
									parent.changeLeftMenuCss(5);
								});
								
							},
							error:function(){
								alert("创建相册失败");
							}
						})
					}else{
						//编辑状态 编辑相册
						$.ajax({
							url:rootPath+"/contents/album/editAlbumById.do",
							data:{albumName:$("#albumName").val(),
								  albumId:_ablumId,
								  relTypeIDS:relTypeIDS},
							type:"post",
							dataType:"json",
							success:function(data){
								var ablumId=data.albumId;
								if(ablumId!=""){
									if(liNum!=0){
										//循环提交图片
										for (var i = 0; i < liNum; i++) {
											//创建相册后给个相册id赋值
											var nowLi=$(".popImgList li").eq(i); 
											nowLi.find(".pAlbumId").val(ablumId);
											//调用方法保存图片
											updataPic(i); 
										}
									}
									layer.alert("修改相册成功",function(index){
										location.href=rootPath+"/jsp/webconsole/album/albumManage.jsp";
									});
								}
							},
							error:function(){
								layer.alert("修改相册失败");
							}
						})
				}
				}
			
		}else{
			layer.alert("图册名称不能为空");
		}
		
}
function changeCss(nowLi){
	$(".popImgList .del-img").css("border","2px solid #297cd0");
	$(".popImgList .del-img").eq(nowLi).css("border","2px solid #FF5722");
}
/** 
 * 获取当前URL参数值 
 * @param name  参数名称 
 * @return  参数值 
 */  
function getUrlParam(name) {  
       var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");  
       var r = window.location.search.substr(1).match(reg);  
       if(r!=null){
    	  return unescape(r[2]);   
       }else{
    	  return null;  
       }  
         
} 

//选中关联类型
var selectedAblumType = function(id){
	 $.ajax({
			type:'post',
			 url:rootPath+'/contents/album/queryAlbumTypeRels.do',
			data:{
				pictureAlbumId : id ,
			},
			dataType:'json',
			async : true,
			success: function(data){ 
				$data = data.datas;
				$($data).each(function(){
					var albumObj = this;
					$("input[name='albumTypeId']").each(function(){
						 if(this.value == albumObj.albumTypeId){
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