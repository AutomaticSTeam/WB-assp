<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8">
	<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%= rootPath%>/static/plugins/jquery.tmpl.min.js" type="text/javascript"></script>
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script> 
<script src="<%=rootPath%>/js/common/imageUtil.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/js/json2.js" type="text/javascript"></script> 
</head>
<body >
<div class="focusPop">
	<div class="pop-head pop-tab-title">
		<a data-tab="01" class="active" href="javscript:;">常规</a>
	</div><!--pop-head-->
	<div class="pop-body">
		<div class="popTabContent-item popTabContent-01">
			<div class="pop-form">
				<div class="popForm-item">
					<label>图层样式：</label>
					<div class="label-con">
						<div class="images-css">
							<img data-css="1" id="module_banner_tmpl" class="active imageStyle" src="<%=rootPath%>/images/focus_img02.png" alt="" >
							<img data-css="2" id="module_foucs_pictures_tmpl" src="<%=rootPath%>/images/focus_img01.png" alt="" class="imageStyle">
							<img data-css="3" id="module_foucs_thumbnail_tmpl"  src="<%=rootPath%>/images/focus_img03.png" alt="" class="imageStyle">
						</div>
						<div class="attention-msg">
							注：轮播时显示不超过8张图片（建议图片尺寸为<span id="imageStyle" style="color: red;"></span>，大小不超过1MB。）
						</div>
					</div>
				</div>
				<div class="popForm-item">
					<label>当前图片：</label>
					<div class="label-con">
						<div class="lable-head">
							<div class="lable-title">
								&nbsp;
								<div class="lable-btn">
									<a href="javascript:;" class="btn dib btn-border-gray selectImgBookBtn">选择图册</a>
									<a href="javascript:;" class="btn dib btn-border-gray addImgBtn">添加图片</a>
								</div>
							</div>
							<div class="focus-pic">
								<ul class="popImgList" id="popInfo-body">
									
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="focus-tabContent">
					<div class="popForm-item">
						<label>图片名称：</label>
						<div class="label-con">
							<input class="inputText" type="text" id="pictureName" name="pictureName"/>
						</div>
					</div>
					<div class="popForm-item">
						<label>基本描述：</label>
						<div class="label-con">
							<textarea class="textarea" name="briefDesc" id="briefDesc"></textarea>
						</div>
					</div>
					<div class="popForm-item">
						<label>链接地址：</label>
						<div class="label-con">
							<input class="inputText" type="text" id="hyperlink" name="hyperlink"/>
							<!-- <a href="javacript:;" class="btn dib btn-border-gray">设置链接</a> -->
						</div>
					</div>
					<div class="popForm-item">
						<label>详细描述：</label>
						<div class="label-con">
							<div class="editor-box">
							<textarea class="textarea" id="detailDesc" name="detailDesc" style="width:100%;height:500px;"></textarea>
							</div>
						</div>
					</div>
				</div><!--focus-tabContent-->
			</div><!--pop-form-->
		</div><!--popTabContent-->
	</div><!--pop-body-->
</div><!--focus-body-->
<script id="pop_data_grid" type="text/x-jquery-tmpl"> 
	 {{each(i,picture) rows}}
{{if picture.dataStatus != 1}}
	<li data-tab="{{= i}}" id="li{{= picture.pictureId}}" >
		<div class="img active" id="{{= picture.pictureId}}">
			<img  src="{{= picture.pictureUrl}}" alt="{{= picture.briefDesc}}" title="{{= picture.briefDesc}}" >
			<div class="move-img"><a href="javascript:;" class="move-left" id="{{= picture.pictureId}}"></a><a href="javascript:;" class="move-right"  id="{{= picture.pictureId}}"></a></div>
			<div class="del-img"><a href="javascript:;" class="del"  id="{{= picture.pictureId}}"></a></div>
		</div>
	</li>
{{/if}}
	{{/each}}
</script>
<script>
var ue ;
$(function() {
	selectCss();
	focusManage.initialize();
});

function selectCss () {
var imgCss = $('.images-css'),
	img = imgCss.find('img');
	
img.on('click',function(){
	var $this = $(this);
	
	$this.addClass('active').siblings().removeClass('active');
});
}

var top = window.parent;
var focusManage = {
		id: "focusFun",
		// 模板id
		moduleId: null,
		// 添加图片临时id
		currentAddpictureId: -1,
		//模块数据
		module: null,
		//模块数据
		moduleisModified: false,
		//当前临时数据
		tempData: {} ,
		//类别id
		pictureTypeIds: [],
		// 临时存储数据
		picturesData: null,
		//欲返回的操作数据集
		data_: {
			id: "focusFun",
			url: "<%=rootPath%>/site/modifyTempLatefocus.do",
			data: {} 
			},
		size: {
			preWidth: 0,
			preHeight: 0,
			width: 0,
			height: 0
		},
		//初始化相关参数，方法
		initialize: function(){
			focusManage.moduleId = top.SiteManager.tempData.dataUrl;
			//加载详细说明
			ue = UE.getEditor('detailDesc',{
		        autoHeightEnabled: true,//自动增高
		        lang:"zh-cn",
		        focus:false ,//初始化时，是否让编辑器获得焦点true或false
		        initialFrameWidth:'100%', //初始化编辑器宽度,默认1000
		        initialFrameHeight:'100%', //初始化编辑器高度,默认320
		        readonly : false ,//编辑器初始化结束后,编辑区域是否是只读的，默认是false
		        imagePopup:false   ,  //图片操作的浮层开关，默认打开
		        emotionLocalization:false, //是否开启表情本地化，默认关闭。若要开启请确保emotion文件夹下包含官网提供的images表情文件夹
		        wordCount:false
			});
			
			ue.ready(function() {
			  	// 添加内容改变事件
				ue.addListener( 'contentChange', function( editor ) {
					if(focusManage.tempData != null){
						focusManage.tempData.ismodified = true;
						focusManage.tempData['detailDesc'] = ue.getContent();
					}
					
				 });
			});
			
			focusManage.getDataList();
			this.listenPicture();
			
			$(".popImgList").on("click",".active",function(e){
				if (($(e.target).is('.move-img')||$(e.target).is('.del-img'))){
		    		focusManage.addOrUpdateTempData(focusManage.tempData);
					focusManage.edit(this);
				}
			});
			
			$(".popImgList").on("click",".move-left",function() {
				 focusManage.left(this);
			 });
			$(".popImgList").on("click",".move-right",function() {
				 focusManage.right(this);
			 });
			$(".popImgList").on("click",".del-img .del",function() {
				 focusManage.remove(this);
			 });
			$(".imageStyle").click(function() {
				 focusManage.changeImageSylte(this);
			 });
			//随时赋值,并做修改标记
			$(".inputText,.textarea").blur(function(){
				if(this.className.indexOf("inputText") != -1){
					focusManage.tempData[this.name] = $(this).val();
				}else if(this.className.indexOf("textarea") != -1){
					focusManage.tempData[this.name] = $(this).val();
				}
				focusManage.tempData.ismodified = true;
				focusManage.addOrUpdateTempData(focusManage.tempData);
			});
			top.SiteManager.handers.focusFun = focusManage;
			
			//选择图册
			$(".selectImgBookBtn").click(function(){
				focusManage.selectAlbum();
			});
			// 初始化建议尺寸
			var img = top.$("#module"+focusManage.moduleId).find("li[data-type]").eq(0);
			if(img){
				$("#imageStyle").html(img.width() + "*" + img.height());
			}
		},
		// 页面缓存数据刷新
		refreshTemp: function(){
			$("#popInfo-body").empty();
			$("#pop_data_grid").tmpl({rows:focusManage.picturesData}).appendTo("#popInfo-body"); 
			//悬浮效果事件
			$(".popImgList li").find("a").hide();
		    $(".popImgList li").hover(function(){
				$(this).find("a").show();
			},function(){
				$(this).find("a").hide();
			})
			focusManage.changeCss(0);
		    $(".inputText,.textarea").each(function(){
		    	$(this).val('');
		    });
		    if($(".popImgList .img").length > 0){
				focusManage.edit($(".popImgList .img").eq(0)[0]);
		    }
		},
		 changeCss: function(nowLi){
			$(".popImgList .img").addClass("active");
			$(".popImgList .img").eq(nowLi).removeClass("active");
			$(".popImgList .del-img").css("border","2px solid #297cd0");
			$(".popImgList .del-img").eq(nowLi).css("border","2px solid #FF5722");
		 },
		// 改变轮播图样式
		 changeImageSylte: function(img){
			 focusManage.module.moduleTmpl = $(img).attr("id");
			 focusManage.moduleisModified = true;
			 focusManage.interact();
		 },
		 edit: function(img){
			 var nowLi = $(img).parents("li").attr("data-tab");
			 var id = $(img).attr("id");
			 var lidata = focusManage.tempData = focusManage.getColumnById(id);
			 focusManage.changeCss(nowLi);
			 //赋值
			 $(".inputText,.textarea").each(function(){
				 if(lidata[this.name]){
					 if(this.tagName.toLowerCase() == "textarea"){
						 var detailContent = lidata[this.name];
						 this.name == "detailDesc" ?  ue.ready(function() {ue.setContent(detailContent);}) : $(this).val(detailContent);	 
					 }else {
						 $(this).val(lidata[this.name]);
					 }
				 }
			 });
		 },
		 selectAlbum: function(){
			 focusManage.tempData.pictureAlbumIds = focusManage.pictureTypeIds;
			 top.layer.open({
				  type: 2, 
				  title: ['选择图册', 'font-size:14px;'],
				  content: ['<%=rootPath%>/jsp/website/manage/pop/selectAlbumPop.jsp?parentName=focusFun', 'no'],
				  area: ['660px', '530px'],//定义大小
				  closeBtn: 1,//右上角关闭按钮样式
				  shade: 0.3, //遮罩为0表示不显示
				  shadeClose: true,//是否点击遮罩关闭
				  scrollbar: false,//是否允许滚动条出现
				  //按钮与回调
				  btn: ['确认', '取消'],
				  yes: function(index, layero){
					  if(focusManage.tempData.pictureAlbumIds.length == 0){
						  top.layer.alert("请选择图册");
						  return false;
					  }
					  focusManage.pictureTypeIds = focusManage.tempData.pictureAlbumIds;
					  focusManage.getPictureList();
					  top.layer.close(index);
					  return true;
				  }
				});
		 },
		 // 与父级页面交互
		 interact: function (){
			 focusManage.module.pictures = [];
			 if(focusManage.picturesData.length > 0){
			 	focusManage.picturesData.sort(function (a,b){
			 		return a.pictureOrder - b.pictureOrder;
			 	});
			 }
			 for(var i = 0;i < focusManage.picturesData.length; i++){
				 if(focusManage.picturesData[i].dataStatus == void 0 || focusManage.picturesData[i].dataStatus == 0){
					 focusManage.module.pictures.push(focusManage.picturesData[i]);
				 }
			 }
			 top.Template.refreshModule(focusManage.module);
			 focusManage.synData();
		 },
		 // 更新到父级页面临时变更数据上去
		 synData: function (){
			 var arr_ = [];//只存储更新数据
			 for(var i = 0;i < focusManage.picturesData.length; i++){
				 if(focusManage.picturesData[i].ismodified){
					 arr_.push(focusManage.picturesData[i]);
				 }
			 }
			 // 转换为json串
			 if(focusManage.moduleisModified){
				 var obj = {
						 moduleId:focusManage.module.moduleId,
						 moduleTmpl:focusManage.module.moduleTmpl
						 }
				 this.data_.data.module = JSON.stringify(obj);
			 }
			 this.data_.data.datas = JSON.stringify(arr_);
			 this.data_.data.moduleId = focusManage.module.moduleId;
			 this.data_.data.pictureTypeId = this.pictureTypeIds[0];
			 // 放页面保存数据
			 top.SiteManager.addModuleData(this.data_);
		 },
		// 获取数据
	    getColumnById: function(id){
			for(var i = 0;i < focusManage.picturesData.length; i++){
				if(focusManage.picturesData[i].pictureId == id){
					return focusManage.picturesData[i];
				}
			}
		},
		// 添加或更新数据
		addOrUpdateTempData: function(tempData){
			if(!tempData.ismodified){
				return;
			}
			for(var i = 0;i < focusManage.picturesData.length; i++){
				if(focusManage.picturesData[i].pictureId == tempData.pictureId){
					focusManage.picturesData[i] = tempData;
					focusManage.picturesData[i].ismodified = true; 
					focusManage.interact();
					return;
				}
			}
			// 新增加，则计算排序号，以最后一个节点序号加1
			focusManage.picturesData.push(tempData);
			focusManage.interact();
		},
		// 最大排序号
		maxpictureOrder: function(){
			var maxNum = 0;
			for(var i = focusManage.picturesData.length - 1;i >= 0; i--){
				if(focusManage.picturesData[i].pictureOrder > maxNum){
					maxNum = focusManage.picturesData[i].pictureOrder;
				}
			}
				
			return parseInt(maxNum,10) + 1;
		},	
		// 元素数组排序
		sort: function(data){
			$(data).each(function(index){
				if(this.pictureOrder == void 0 || this.pictureOrder == null || (index != 0 && this.pictureOrder == 0)){
					this.pictureOrder = index;
					this.ismodified = true;
				}else{
					this.ismodified = false;
				}
			});
		},
		//获取图片相关数据
		getDataList: function(){
			$.ajax({
				type : 'post',
				url : "<%=rootPath%>/site/getTempLateFocus.do",
				data :  {moduleId : focusManage.moduleId},
				dataType : 'json',
				async  :  false,
				traditional : true,
				success : function(data){ 
					if(data.code == 1){
						focusManage.module = data.datas.module;
						focusManage.picturesData = data.datas.module.pictures;
						$("#"+focusManage.module.moduleTmpl).addClass('active').siblings().removeClass('active');
						$(data.datas.module.templateModuleRelContents).each(function(){
							focusManage.pictureTypeIds.push(this.dataColumnId);
						});
						focusManage.sort(focusManage.picturesData);
						focusManage.refreshTemp();
					}else {
						top.layer.alert("没获取到数据，请重试");
					}
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     }); 
		},
		//获取图片相关数据以图册
		getPictureList: function(){
			$.ajax({
				type : 'post',
				url : "<%=rootPath%>/site/getPictures.do",
				data :  {pictureAlbumId:focusManage.pictureTypeIds[0]},
				dataType : 'json',
				async  :  false,
				traditional : true,
				success : function(data){ 
					if(data.code == 1){
						focusManage.picturesData = data.datas.pictures;
						focusManage.sort(focusManage.picturesData);
						focusManage.refreshTemp();
						focusManage.interact();
					}else {
						top.layer.alert("没获取到数据，请重试");
					}
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     }); 
		},
		// 删除
		remove: function(a){
			top.layer.confirm('您确定删除当前栏目？', {icon: 3, title:'提示'}, function(index){
		 		var pictureData = focusManage.getColumnById(a.id);
		 		pictureData.dataStatus = 1;
		 		pictureData.ismodified = true;
		 		$("#li"+a.id).remove();
		 		focusManage.interact();
		 		top.layer.close(index);
			});
			
		},
		// 向左排序
		left: function(a){
			// 排序算法：往左挪一下，两者交换排序值,如果排序值为空，则置为99
		 	var pictureData = focusManage.getColumnById(a.id);
			// 已经最左面了 
			if($("#li" + a.id).prev() == void 0 || $("#li" + a.id).prev() == null){
				return;
			}
		 	var prevNodeId = $("#li" + a.id).prev().attr("id");
	     	var prevPictureData = focusManage.getColumnById(prevNodeId.replace("li",""));
	     	var tempSort = (prevPictureData.pictureOrder == void 0 || prevPictureData.pictureOrder == null) ? 99 : prevPictureData.pictureOrder;
		    prevPictureData.pictureOrder = (pictureData.pictureOrder == void 0 || pictureData.pictureOrder == null) ? 99 : pictureData.pictureOrder;
		    prevPictureData.ismodified = true;
		    pictureData.pictureOrder = tempSort;
		    pictureData.ismodified = true;
		    $("#" + prevNodeId).insertAfter("#li" + a.id);
			
		    focusManage.interact();
		},
		// 向右排序
		right: function(a){
			// 排序算法：往下挪一下，两者交换排序值,如果排序值为空，则置为99
		 	var pictureData = focusManage.getColumnById(a.id);
		 	// 已经最右面了 
			if($("#li" + a.id).next() == void 0 || $("#li" + a.id).next() == null){
				return;
			}
		 	
		 	var nextNodeId = $("#li" + a.id).next().attr("id");
	     	var nextpictureData = focusManage.getColumnById(nextNodeId.replace("li",""));
	     	var tempSort = (nextpictureData.pictureOrder == void 0 || nextpictureData.pictureOrder == null) ? 99 : nextpictureData.pictureOrder;
	     	nextpictureData.pictureOrder = (pictureData.pictureOrder == void 0 || pictureData.pictureOrder == null) ? 99 : pictureData.pictureOrder;
	     	nextpictureData.ismodified = true;
		    pictureData.pictureOrder = tempSort;
		    pictureData.ismodified = 1;
		    $("#" + nextNodeId).insertBefore("#li" + a.id);
		    
		    focusManage.interact();
		},
		save: function(completeModuleIds){
			
		},
		listenPicture: function(){
			//添加图片按钮事件
			$(".addImgBtn").click(function(){
				
				top.layer.open({
					  type: 2, 
					  title: ['添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）', 'font-size:14px;'],
					  content: ['<%=rootPath%>/jsp/common/pop/uploadImg.jsp?singleupload=false&&chooseSingle=false', 'no'],
					 // skin: 'demo-class'
					  area: ['640px', '430px'],//定义大小
					  closeBtn: 1,//右上角关闭按钮样式
					  shade: 0.3, //遮罩为0表示不显示
					  shadeClose: true,//是否点击遮罩关闭
					  scrollbar: false,//是否允许滚动条出现
					  //按钮与回调
					  btn: ['确认', '取消'],
					  yes: function(index, layero){
						  focusManage.OK(index, layero);
					  }
					});
				
			})
		},
		OK: function (index, layero){
			
			var body = top.layer.getChildFrame('body', index);
			var pUrls=body.find("input[id='pictureUrls']").val();
			var pNames=body.find("input[id='pictureNames']").val();
			top.layer.close(index);
			if(pUrls != "" && pUrls != null){
				 var pUrlArray = pUrls.split(",");
				 var pNameArray = pNames.split(",");
				 var tempPicture;
				 for( var i = 0;i < pUrlArray.length; i++){
					 
					 if(pUrlArray[i] == "")
						 continue;
					 tempPicture = {
							 pictureId: (focusManage.currentAddpictureId--),
							 pictureName: Img.getImageName(pNameArray[i]),
							 pictureUrl: pUrlArray[i],
							 briefDesc: Img.getImageTypeByName(pNameArray[i]),
							 detailDesc: Img.getImageTypeByName(pNameArray[i]),
							 imgPostfix: Img.getImageTypeByName(pNameArray[i]),
							 ismodified: true,
							 pictureOrder: focusManage.maxpictureOrder()
					 };
					 focusManage.addOrUpdateTempData(tempPicture);
				 }
				 focusManage.refreshTemp();
				 focusManage.interact();
		  }
		}
		
	}
</script>
</body>
</html>
