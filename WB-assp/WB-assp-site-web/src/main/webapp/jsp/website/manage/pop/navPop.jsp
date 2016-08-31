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
<script type="text/javascript">
		    	var rootPath = '<%= rootPath%>';
</script>
	<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
<script src="<%= rootPath%>/static/plugins/jquery.tmpl.min.js" type="text/javascript"></script>
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
<script src="<%=rootPath%>/js/json2.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/js/dialog.js" type="text/javascript"></script> 
<style>
	.popInfo-name .arrowB{
		width:1px;height:1px;border-top:6px solid #000;
		border-right:4px solid transparent;
		border-left:4px solid transparent;
		border-bottom:4px solid transparent;
		position:absolute;left:3px;top:7px;
	}
	.popInfo-name .arrowR{
		width:1px;height:1px;border-left:6px solid #000;
		border-right:4px solid transparent;
		border-top:4px solid transparent;
		border-bottom:4px solid transparent;
		position:absolute;left:3px;top:7px;
	}
	
	.popInfo-box  .column2{
		margin-left:15px;
		transition: all 0.3s;
	}
	.popInfo-box  .column2 .popInfo-name{
		width: 165px;
	}
	.popInfo-show .show{background-position: -28px -36px;}
</style>
</head>
<body >
<div class="navPop" >
	<div class="pop-head pop-tab-title">
		<a data-tab="01" class="active">常规</a>
		<a data-tab="02">信息</a>
	</div><!--pop-head-->
	<div class="pop-body">
		<div class="popTabContent-item popTabContent-01">
			<a class="btn dib btn-border-red addMenuBtn" id="">添加菜单</a>	
			<div class="popInfo-box">
				<div class="popInfo-head">
					<div class="popInfo-name">
						菜单名称
					</div>
					<div class="popInfo-type">
						菜单类型
					</div>
					<div class="popInfo-show">
						菜单可见
					</div>
					<div class="popInfo-operation">
						操作
					</div>
				</div>
				<ul class="popInfo-body popInfo-ul" id="popInfo-body">
					
				</ul>
				<div class="popInfo-num">共<span id="popInfo-num"></span>个</div>
			</div><!--popInfo-box-->
		</div>
		<div class="popTabContent-item popTabContent-02 hide">
			<textarea id="copyrightInfo" name="copyrightInfo" style="width:100%;height:500px;"></textarea>
		</div>
	</div><!--pop-body-->
</div><!--nav-body-->
<script id="nav_data_grid" type="text/x-jquery-tmpl"> 
	 {{each(i,column) rows}}
{{if column.dataStatus == 0}}
<li class="popInfo-group homePage visible" id="item{{= column.columnsId}}">
		<dl class="popInfo-item column1">
							<div class="popInfo-name">
								{{if column.children.length > 0}}
								<div id="arrow{{= column.columnsId}}" class="arrowR"></div>
								{{else}}
								<div id="arrow{{= column.columnsId}}"></div>
								{{/if}}
								{{= column.columnsName}}
								<div class="popInfo-move">
									{{if i != 0}}
									<a href="javascript:;" class="move-item move-top" id="{{= column.columnsId}}"></a>
									{{else}}
									<a href="javascript:;" class="move-item move-top" id="{{= column.columnsId}}" style="display:none;"></a>
									{{/if}}

									{{if i != rows.length - 1}}
									<a href="javascript:;" class="move-item move-bottom" id="{{= column.columnsId}}"></a>
									{{else}}
									<a href="javascript:;" class="move-item move-bottom" id="{{= column.columnsId}}" style="display:none;"></a>
									{{/if}}
									<a href="javascript:;" class="move-item move-left" id="{{= column.columnsId}}" style="display:none;"></a>
									{{if i != 0}}
									<a href="javascript:;" class="move-item move-right" id="{{= column.columnsId}}"></a>
									{{else}}
									<a href="javascript:;" class="move-item move-right" id="{{= column.columnsId}}" style="display:none;"></a>
									{{/if}}
									
								</div>
							</div>
							<div class="popInfo-type">
								{{= column.columnsTypeName}}
							</div>
							<div class="popInfo-show">
							{{if column.columnsHide == 1}}
							<a class="hide editHideBtn" id="{{= column.columnsId}}"></a>
							{{else}}
							<a class="show editShowBtn" id="{{= column.columnsId}}"></a>
							{{/if}}
							</div>
							<div class="popInfo-operation">
							<a class="editor editorNavBtn" id="{{= column.columnsId}}">【编辑】</a>
							{{if column.columnsTypeId != 1}}
							<a class="delete" id="{{= column.columnsId}}">【删除】</a>
							{{/if}}
							</div>
							<div class="popInfo-add">
								<a class="addMenuBtnNew" id="{{= column.columnsId}}"></a>
							</div>
	</dl>
	{{if column.children.length > 0}}
	<ul class="childGroup" style="display:none;">
		{{each(j,childColumn1) column.children}}
		{{if childColumn1.dataStatus == 0}}
       <li class="popInfo-group homePage visible2" id="item{{= childColumn1.columnsId}}">   
		   <dl class="popInfo-item column2"> 
				<div class="popInfo-name">
								<div id="arrow{{= childColumn1.columnsId}}"></div>
								{{= childColumn1.columnsName}}
								<div class="popInfo-move">
									{{if j != 0}}
									<a href="javascript:;" class="move-item move-top" id="{{= childColumn1.columnsId}}"></a>
									{{else}}
									<a href="javascript:;" class="move-item move-top" id="{{= childColumn1.columnsId}}" style="display:none;"></a>
									{{/if}}

									{{if j != rows.length - 1}}
									<a href="javascript:;" class="move-item move-bottom" id="{{= childColumn1.columnsId}}"></a>
									{{else}}
									<a href="javascript:;" class="move-item move-bottom" id="{{= childColumn1.columnsId}}" style="display:none;"></a>
									{{/if}}
									<a href="javascript:;" class="move-item move-left" id="{{= childColumn1.columnsId}}" style="display:none;"></a>
									{{if j != 0}}
									<a href="javascript:;" class="move-item move-right" id="{{= childColumn1.columnsId}}"></a>
									{{else}}
									<a href="javascript:;" class="move-item move-right" id="{{= childColumn1.columnsId}}" style="display:none;"></a>
									{{/if}}
									
								</div>
							</div>
							<div class="popInfo-type">
								{{= childColumn1.columnsTypeName}}
							</div>
							<div class="popInfo-show">
							{{if childColumn1.columnsHide == 1}}
							<a class="hide editHideBtn" id="{{= childColumn1.columnsId}}"></a>
							{{else}}
							<a class="show editShowBtn" id="{{= childColumn1.columnsId}}"></a>
							{{/if}}
							</div>
							<div class="popInfo-operation">
							<a class="editor editorNavBtn" id="{{= childColumn1.columnsId}}">【编辑】</a>
							{{if childColumn1.columnsTypeId != 1}}
							<a class="delete" id="{{= childColumn1.columnsId}}">【删除】</a>
							{{/if}}
							</div>
							<div class="popInfo-add">
								<a class="addMenuBtnNew" id="{{= childColumn1.columnsId}}"></a>
							</div>
		   </dl>
		</li>
		{{/if}}
		{{/each}}
	</ul>       
	{{/if}}
	
</li>
{{/if}}
	{{/each}}
</script>
<script>
	$(function() {
		tab01();
		navManage.initialize();
	});
	
	function tab01 () {
		var tabItem = $('.pop-tab-title a');
			
		tabItem.on('click',function(){
			var $this = $(this);
			var val = $this.attr('data-tab');
			
			$this.addClass('active').siblings().removeClass('active');
			$('.popTabContent-'+val).removeClass('hide').siblings().addClass('hide');
		});
	}
	
	var ue ;
</script>

<script>
var top = window.parent;
var NAVSAVEURL = '<%=rootPath%>/site/modifyTempLateNav.do';
var GETNAVURL = '<%=rootPath%>/site/queryTempLateNav.do';
var ADDNAVURL = '<%=rootPath%>/site/addTempLateNav.do';
var navManage = {
		id: "navFun",
		templateId: null,
		footerId: null,
		// 单个临时菜单数据
		tempData: {},
		// 临时弹出层index
		tempLayerIndex: 0,
		// 编辑框
		editContent: null,
		// 编辑框是否被编辑
		editContentIsModified: false,
		// 临时存储数据
		columnsData: null,
		// 弹出句柄
		handers:{},
		// 初始化
		initialize: function(){
			if(top.SiteManager.tempData.id)
				navManage.id = top.SiteManager.tempData.id;
			
			if(navManage.id == 'footerFun'){// 站脚编辑初始化参数
				if(top.SiteManager.tempData.dataIndex == 2){
					$("a[data-tab]").click();
				}
				NAVSAVEURL = "<%=rootPath%>/site/modifyFooterNav.do";
				GETNAVURL = "<%=rootPath%>/site/queryFooterNav.do";
				ADDNAVURL = "<%=rootPath%>/site/addFooterNav.do";
				$(".pop-head").removeClass("hide");
				//加载版权说明
				ue = UE.getEditor('copyrightInfo',{
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
				    // 设置编辑器的内容
				    ue.setContent(top.$("#copyright-content").html());
				  	// 添加内容改变事件
					ue.addListener( 'contentChange', function( editor ) {
						navManage.editContentIsModified = true;
					 });
				});
				
				
			}else {// 导航编辑初始化
				$(".pop-head").addClass("hide");
			}
			
			navManage.getNavList();
			//初始化按钮
			$("#popInfo-body").on("click",".editorNavBtn",function(){
				navManage.edit(this.id);
			});
			$(".addMenuBtn").click(function(){
				navManage.edit(null);
			});
			$("#popInfo-body").on("click",".delete",function(){
				navManage.remove(this.id);
			});
			// 添加到主页句柄
			top.SiteManager.handers[navManage.id] = navManage;
			// 隐藏显示操作
			$("#popInfo-body").on("click",".editHideBtn",function(){
				   var columnData = navManage.getColumnById(this.id);
				   columnData.columnsHide = 0;
				   columnData.ismodified = 1;
				   $(this).removeClass();
				   $(this).addClass("show editShowBtn");
				   layer.alert("已显示");
			 });
			$("#popInfo-body").on("click",".editShowBtn",function(){
				   var columnData = navManage.getColumnById(this.id);
				   columnData.columnsHide = 1;
				   columnData.ismodified = 1;
				   $(this).removeClass();
				   $(this).addClass("hide editHideBtn");
				   layer.alert("已隐藏");
			});      
			// 上下排序操作
			$("#popInfo-body").on("click",".move-top",function(){
				navManage.up(this);
			});        
			$("#popInfo-body").on("click",".move-bottom",function(){
				navManage.down(this);
			});  
			$("#popInfo-body").on("click",".move-left",function(){
				navManage.left(this);
			});  
			$("#popInfo-body").on("click",".move-right",function(){
				navManage.right(this);
			});  
			$("#popInfo-body").on("click",".arrowB,.arrowR",function(){
				navManage.arrowClick(this);
			});  
		},
		
		// 获取数据
		getColumnById: function(id){
			for(var i = 0;i < navManage.columnsData.length; i++){
				if(navManage.columnsData[i].columnsId == id){
					return navManage.columnsData[i];
				}
			}
		},
		// 添加或更新数据
		addOrUpdateTempData: function(tempData){
			for(var i = 0;i < navManage.columnsData.length; i++){
				if(navManage.columnsData[i].columnsId == tempData.columnsId){
					navManage.columnsData[i] = tempData;
					navManage.columnsData[i].ismodified = 1; 
					return;
				}
			}
			// 新增加，则计算排序号，以最后一个节点序号加1
			tempData.ismodified = 1;
			navManage.columnsData.push(tempData);
		},
		// 最大排序号
		maxSortNum: function(){
			var maxNum = 0;
			for(var i = navManage.columnsData.length - 1;i >= 0; i--){
				if(navManage.columnsData[i].sortNum > maxNum){
					maxNum = navManage.columnsData[i].sortNum;
				}
			}
			
			return parseInt(maxNum,10) + 1;
		},
		// 删除操作
		remove: function(id){
			top.layer.confirm('您确定删除当前栏目？', {icon: 3, title:'提示'}, function(index){
				 //删除数据
				 var columnData = navManage.getColumnById(id);
				 columnData.dataStatus = 1;
				 columnData.ismodified = 1;
				 if($("#item"+id).hasClass("visible")){
				 	// 若有孩子节点，则一并删除,此处只涉及二级
				 	var childColumnData,id_;
				 	$("#item"+id).find(".childGroup li").each(function(){
				 		id_ = this.id.replace("item","");
				 		childColumnData = navManage.getColumnById(id_);
				 		childColumnData.dataStatus = 1;
				 		childColumnData.ismodified = 1;
				 	});
				 	$("#item"+id).remove();
				 	
				 }else if($("#item"+id).hasClass("visible2")){//删除的是子节点
					var childGroup = $("#item"+id).parents(".childGroup");
					$("#item"+id).remove();
					navManage.refreshMoves(childGroup);
				 }
				 navManage.refreshMoves($("#popInfo-body"));
				 navManage.countColumnsLength();
				 top.layer.close(index);
				 
			});
		},
		// 编辑操作
		edit: function(id){
			navManage.tempData = {};
			var title_ = '编辑菜单';
			var btn_ = ['确认', '取消'];
			if(id == null){
				title_ = '添加菜单';
				btn_ = ['保存', '取消'];
			}else{
				navManage.tempData = navManage.getColumnById(id);
			}
			navManage.tempLayerIndex = layer.open({  
	            shade: [0.2, '#000', false],  
	            type: 2,  
	            area: ['480px', '300px'],  
	            fix: false, //不固定  
	            maxmin: true,  
	            title: [title_, false],  
	            content: "<%=rootPath%>/jsp/website/manage/pop/editNavPop.jsp",
	            scrollbar:false,//是否允许滚动条出现
	            //按钮与回调
				btn: btn_,
				yes: function(index, layero){
					
					if(navManage.handers.perNav){
						navManage.handers.perNav.save();
				    }
				    layer.close(index);
				},
				btn2: function(index, layero){
					//取消操作
					navManage.tempData = {};
				},
				cancel: function(index){
					//取消操作
					navManage.tempData = {};
					return true;
				}
				}); 
			
		},
		// 计算栏目个数
		countColumnsLength: function(){
			$("#popInfo-num").html($(".popInfo-item").length);
		},
		// 向上排序
		up: function(a){
			// 排序算法：往上挪一下，两者交换排序值,如果排序值为空，则置为99
		 	var columnData = navManage.getColumnById(a.id);
		 	var prevNodeId = $("#item" + a.id).prev().attr("id");
	     	var prevColumnData = navManage.getColumnById(prevNodeId.replace("item",""));
	     	var tempSort = (prevColumnData.sortNum == void 0 || prevColumnData.sortNum == null) ? 99 : prevColumnData.sortNum;
		    prevColumnData.sortNum = (columnData.sortNum == void 0 || columnData.sortNum == null) ? 99 : columnData.sortNum;
		    prevColumnData.ismodified = 1;
		    columnData.sortNum = tempSort;
		    columnData.ismodified = 1;
		    $("#" + prevNodeId).insertAfter("#item" + a.id);
		    // 然后判断up，down还有操作权限
		    navManage.refreshMoves($("#popInfo-body"));
		    navManage.refreshMoves($("#item" + a.id).parents(".childGroup"));
		},
		// 向下降序
		down: function(a){
			// 排序算法：往下挪一下，两者交换排序值,如果排序值为空，则置为99
		 	var columnData = navManage.getColumnById(a.id);
		 	var nextNodeId = $("#item" + a.id).next().attr("id");
	     	var nextColumnData = navManage.getColumnById(nextNodeId.replace("item",""));
	     	var tempSort = (nextColumnData.sortNum == void 0 || nextColumnData.sortNum == null) ? 99 : nextColumnData.sortNum;
	     	nextColumnData.sortNum = (columnData.sortNum == void 0 || columnData.sortNum == null) ? 99 : columnData.sortNum;
	     	nextColumnData.ismodified = 1;
		    columnData.sortNum = tempSort;
		    columnData.ismodified = 1;
		    $("#" + nextNodeId).insertBefore("#item" + a.id);
		    navManage.refreshMoves($("#popInfo-body"));
		    navManage.refreshMoves($("#item" + a.id).parents(".childGroup"));
		},
		// 向右排序
		right: function(a){
			var $thisA = $(a);
			var $thisItem = $thisA.parents(".popInfo-item"),
			$thisGroup  = $thisA.parents(".popInfo-group");
			$thisItem.removeClass("column1").addClass("column2");
			$thisGroup.addClass("visible2");
			// 建立父子关系
			var parentli_id = $thisGroup.prev().attr("id").replace("item","");
			var columnData = navManage.getColumnById(a.id);
			columnData.columnsPid = parentli_id;
			columnData.ismodified = 1;
			// 显现左箭标
			if(!$thisGroup.prev().find("ul").hasClass("childGroup")){
				$thisGroup.prev().append('<ul class="childGroup"></ul>');
			}else{
				if($thisA.parents(".childGroup").find("li").length==1){
					$thisGroup.parents(".childGroup").prev().find('.popInfo-name div:first-child').addClass("arrowB");
				}else{
					
				}
			}
			$thisGroup.removeClass("visible");
			$thisGroup.prev().find(".childGroup").append($thisGroup);
			$thisGroup.parents(".childGroup").prev().find('.popInfo-name div:first-child').addClass("arrowB");
			navManage.refreshMoves($("#popInfo-body"));
			navManage.refreshMoves($thisGroup.parents(".childGroup"));
		},
		// 刷新排序
		refreshMoves: function($group){
			
			if($group == void 0 || $group == null || $group.length == 0 )
				return;
			
			var $lis = $group.children("li");
			var len = $lis.length;
			var id_;
			$lis.each(function(i){
				id_ = this.id.replace("item","");
				// 先隐藏
				$(this).find(".move-item[id='"+id_+"']").hide();
				// 有大哥
				if(i - 1 >= 0){
					$(this).find(".move-top[id='"+id_+"']").show();
				}
				// 有小弟
				if(len - i > 1){
					$(this).find(".move-bottom[id='"+id_+"']").show();
				}
				// 说明在二级
				if($(this).hasClass("visible2")){
					$(this).find(".move-left[id='"+id_+"']").show();
				}else if(i - 1 >= 0 && $(this).find(".childGroup li").length == 0 ){
					$(this).find(".move-right[id='"+id_+"']").show();
				}
			});
		},
		// 向左排序
		left: function(a){
			var $thisA = $(a);
			var $li = $thisA.parents("li.visible2"),
				$thisGroup = $li.parent(),
				$parentli  = $thisGroup.parent();
			// 删除父子关系
			var columnData = navManage.getColumnById(a.id);
			columnData.columnsPid = 0;
			columnData.ismodified = 1;
			$li.removeClass("visible2");
			$li.addClass("visible");
			$li.children("dl.column2").removeClass("column2").addClass("column1");
			$li.insertAfter($parentli);
			$liLen = $thisGroup.find("li").length;
			// 如果没孩子 就删掉group
			if($thisGroup.find("li").length == 0){
				$thisGroup.prev().find('.popInfo-name div:first-child').removeClass("arrowB");
				$thisGroup.remove();
			}
			navManage.refreshMoves($("#popInfo-body"));
			navManage.refreshMoves($thisGroup);
		},
		// 显示图标
		arrowShow: function($thisA,$thisKind1){
			var $thisMove = $thisA.parent(),
			$thisName = $thisA.parents(".popInfo-name"),
			$thisItem = $thisA.parents(".popInfo-item");
			$thisMove.find($thisKind1).css({"visibility":"visible"});
		},
		// 隐藏
		arrowHide: function($thisA,$thisKind1){
			var $thisMove = $thisA.parent(),
			$thisName = $thisA.parents(".popInfo-name"),
			$thisItem = $thisA.parents(".popInfo-item");
			$thisMove.find($thisKind1).hide();
		},
		// 顶级层级图标
		arrowClick: function(obj){
			if($(obj).hasClass("arrowB")){
				$(obj).parents(".popInfo-group").find(".childGroup").hide();
				$(obj).removeClass("arrowB").addClass("arrowR");
			}else if($(obj).hasClass("arrowR")){
				$(obj).parents(".popInfo-group").find(".childGroup").show();
				$(obj).addClass("arrowB").removeClass("arrowR");
			}
		},
		
		save: function(){
			var modifiedData = [];
			for(var i = 0;i < navManage.columnsData.length; i++){
				if(navManage.columnsData[i].ismodified == 1){
					modifiedData.push(navManage.columnsData[i]);
				}
				
			}
			if(modifiedData.length==0 && !navManage.editContentIsModified){
				top.layer.alert("没有修改的数据需要保存");
				return;
			}
			//编辑器内容
			if(navManage.editContentIsModified){
				navManage.editContent = ue.getContent();
			}
			
			$.ajax({
				type : 'post',
				url : NAVSAVEURL,
				data :  {templateId : navManage.templateId,footerId : navManage.footerId,datas : JSON.stringify(modifiedData),copyrightInfo:navManage.editContent},
				dataType : 'json',
				async  :  false,
				traditional : true,
				success : function(data){ 
					top.location.reload();
				 },
				error:function(data){
				}
		     });
		},
		// 添加，编辑保存之后
		complete: function(){
			// 临时缓存数据
			
			navManage.addOrUpdateTempData(navManage.tempData);
			navManage.refreshTemp();
			//清除弹出框临时数据
			tempData = {};
		},
		// 页面缓存数据刷新
		refreshTemp: function(){
			
			if(navManage.id == 'footerFun'){
				// 无父子级关系
				var treeDatas_ = $.map(navManage.columnsData,function(obj){
					var treeNode = top.SiteManager.inherit(new ZtreeObj(0,0,''),obj); 
					treeNode.id = obj.columnsId;
					treeNode.pId = 0;
					treeNode.name = obj.columnsName;
					treeNode.sort = obj.sortNum;
					return treeNode;
				});
				$("#popInfo-body").empty();
				$("#nav_data_grid").tmpl({rows:$formatToZtree.getTreeNode(treeDatas_,true)}).appendTo("#popInfo-body"); 
				//去掉级别树的添加
				$(".move-left,.move-right,.popInfo-add").remove();
			}else{
				var treeDatas_ = $.map(navManage.columnsData,function(obj){
					var treeNode = top.SiteManager.inherit(new ZtreeObj(0,0,''),obj); 
					treeNode.id = obj.columnsId;
					treeNode.pId = obj.columnsPid;
					treeNode.name = obj.columnsName;
					treeNode.sort = obj.sortNum;
					return treeNode;
				});
				$("#popInfo-body").empty();
				$("#nav_data_grid").tmpl({rows:$formatToZtree.getTreeNode(treeDatas_,true)}).appendTo("#popInfo-body"); 
				navManage.countColumnsLength();
				navManage.refreshMoves($("#popInfo-body"));
				$(".childGroup").each(function(){
					navManage.refreshMoves($(this));
				});
			}
		},
		// 元素数组排序
		sort: function(data){
			$(data).each(function(index){
				if(this.sortNum == void 0 || this.sortNum == null || (index != 0 && this.sortNum == 0)){
					this.sortNum = index;
					this.ismodified = 1;
				}
			});
		},
		
		getNavList: function(){
			var moduleId_ = top.SiteManager.tempData.dataUrl;
			$.ajax({
				type : 'post',
				url : GETNAVURL,
				data :  {moduleId : moduleId_},
				dataType : 'json',
				async  :  false,
				traditional : true,
				success : function(data){ 
					navManage.columnsData = data.datas.rows;
					navManage.sort(navManage.columnsData);
					if(navManage.id == "footerFun"){
						navManage.footerId = data.datas.footerId;
					}
					
					navManage.templateId = data.datas.templateId;
					navManage.refreshTemp();
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     }); 
		}
		
	}
</script>
</body>
</html>
