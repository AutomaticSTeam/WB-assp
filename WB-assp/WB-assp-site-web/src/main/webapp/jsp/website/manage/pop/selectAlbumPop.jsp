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
<script src="<%=rootPath%>/js/common/imageUtil.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/js/json2.js" type="text/javascript"></script> 
</head>
<body >
<div class="focusPop popSelectPhoto">
	<div class="pop-head pop-tab-title">
		<a data-tab="01" class="active" href="javscript:;">常规</a>
	</div><!--pop-head-->
	<div class="pop-body">
		<div class="popTabContent-item popTabContent-01">
			<div class="pop-form">
				<div class="popForm-item">
					<label>选择图册：</label>
					<div class="label-con">
						<div class="establishImgBtn-r">
							<a href="javascript:;" id="" class="btn dib btn-border-gray establishImgBookBtn">创建图册</a>
						</div>
						<div class="popInfo-box">
							<div class="popInfo-body" id="popInfo-body">
								
							</div>
						</div><!--popInfo-box-->
					</div>
				</div>
			</div><!--pop-form-->
		</div><!--popTabContent-->
	</div><!--pop-body-->
</div><!--focus-body-->
<form id="tjForm" name="tjForm" method="post">
<div id="albumDiv"  class="oneEditPop" style="display:none">
<input type="hidden" name="albumId" id="albumId" class="inputText"/>
	     	<div class="pop-form m-a">
					<div class="popForm-item">
						<label>图册名称：</label>
						<div class="label-con">
							<input class="inputText" type="text" name="albumName"  id="albumName" />
						</div>
					</div>
				</div>
</div>
</form>
<script id="pop_data_grid" type="text/x-jquery-tmpl"> 
	 {{each(i,pictureAlbum) rows}}
	<div class="popInfo-item">
		<div class="popInfo-name">
			<input type="checkbox" name="albumName" id="{{= pictureAlbum.albumId}}" class="checkitem"/>{{= pictureAlbum.albumName}}
		</div>
		<div class="popInfo-operation">
			<a href="javascript:;" class="editor" id="{{= pictureAlbum.albumId}}">【编辑】</a>
		</div>
	</div>
	{{/each}}
</script>
<script>
$(function() {
	selectAlbumManage.initialize();
});


var top = window.parent;
var parentName = '<%=request.getParameter("parentName")%>';
//初始化选中状态
var tempData = top.SiteManager.handers[parentName].tempData;
var selectAlbumManage = {
		id: "selectAlbum",
		//初始化相关参数，方法
		initialize: function(){
			selectAlbumManage.getDataList();
			var albumIds = tempData.pictureAlbumIds;
			for(var i = 0;i < albumIds.length; i++){
				$(":checkbox[id='"+albumIds[i]+"']").attr("checked",true);
			}
			$(".establishImgBookBtn,.editor").click(function(){
				selectAlbumManage.edit(this.id);
			});
		},
		// 页面缓存数据刷新
		refreshTemp: function(){
		},
		edit: function(id){
			$(".inputText").val('');
			if(id != ""){
				$("#albumId").val(id);
				$.ajax({
					type : 'post',
					url : "<%=rootPath%>/site/getPictureAlbum.do",
					dataType : 'json',
					data: {albumId:id},
					async  :  false,
					traditional : true,
					success : function(data){ 
						//用于扩展
						if(data.code == 1){
							$("#albumName").val(data.datas.pictureAlbum.albumName);
						}else {
							top.layer.alert("没获取到数据，请重试");
						}
					 },
					error:function(data){
						//alert(JSON.stringify(data));
					}
			     }); 
			}
			
			layer.open({  
	            shade: [0.2, '#000', false],  
	            type: 1,  
	            area: ['480px', '300px'],  
	            fix: false, //不固定  
	            maxmin: true,  
	            title: ['编辑图册', false],  
	            content: $("#albumDiv"),
	            scrollbar:false,//是否允许滚动条出现
	            //按钮与回调
				btn: ["保存","取消"],
				yes: function(index, layero){
					selectAlbumManage.save();
				    layer.close(index);
				},
				btn2: function(index, layero){
				},
				cancel: function(index){
					//取消操作
					return true;
				}
				});
		 },
		//获取图片相关数据
		getDataList: function(){
			$.ajax({
				type : 'post',
				url : "<%=rootPath%>/site/getPictureAlbums.do",
				dataType : 'json',
				async  :  false,
				traditional : true,
				success : function(data){ 
					if(data.code == 1){
						$("#pop_data_grid").tmpl({rows:data.datas.pictureAlbums}).appendTo("#popInfo-body"); 
						$("#popInfo-body").find(".checkitem").click(function(){  
						   if(this.checked){
							   $(".checkitem").not(this).attr("checked",false);
							   tempData.pictureAlbumIds = [this.id];
						   }
						});
					}else {
						top.layer.alert("没获取到数据，请重试");
					}
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     }); 
		},
		// 保存
		save: function(){
			if($("#albumName").val() == ""){
				top.layer.alert("请填写图册名称");
				return;
			}
			
			top.layer.confirm('您确定保存？', {icon: 3, title:'提示'}, function(index){
				 //删除数据
				$.ajax({
					type : 'post',
					url : "<%=rootPath%>/site/modifyPictureAlbum.do",
					dataType : 'json',
					data: $("#tjForm").serialize(),
					async  :  false,
					traditional : true,
					success : function(data){ 
						//用于扩展
						if(data.code == 1){
							window.location.reload();
						}else {
							top.layer.alert("没获取到数据，请重试");
						}
					 },
					error:function(data){
					}
			     }); 
				top.layer.close(index);
			});
			
		}
		
	}
</script>
</body>
</html>
