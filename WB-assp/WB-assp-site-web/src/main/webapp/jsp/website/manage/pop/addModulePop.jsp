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
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
</head>
<body >
<div class="addModulePop">
	<div class="pop-head pop-tab-title">
		<a data-tab="01"  href="javscript:;" class="active">常规</a>
		<a data-tab="02"  href="javscript:;">常规</a>
	</div><!--pop-head-->
	<div class="pop-body">
		<div class="popTabContent-item popTabContent-01 ">
			<div class="pop-form">
				<div class="popForm-item">
					<label>模块类型：</label>
					<div class="label-con">
						<select class="select" name="modelAddType" id="modelAddType">
								<option value="1">文章</option>
								<option value="6">图册</option>
								<option value="8">视频</option>
						</select>
					</div>
				</div>
			</div><!--pop-list-->
		</div>
		<div class="popTabContent-item popTabContent-02 hide">
			<div class="pop-form">
				<div class="popForm-item">
					<label>版式：</label>
					<div class="label-con">
						<select class="select" name="modelAddModuleType" id="modelAddModuleType">
							<option value="0">单模块</option>
							<option value="1">模块组-标签切换</option>
							<option value="2">模块组-竖排</option>
						</select>
					</div>
				</div>
			</div><!--pop-list-->
		</div>
	</div><!--pop-body-->
</div><!--logoPop-->
<script>
var top = window.parent;
var addModuleManage = {
		id: "addMouduleFun",
		moduleId: null,
		tempdata: null,
		moduleVO: null,
		initialize: function(flg){
			if(flg){
				addModuleManage.tempdata = top.SiteManager.tempData;
				addModuleManage.moduleId = top.SiteManager.tempData.dataUrl;
			}else{
				addModuleManage.moduleId = addModuleManage.tempdata.dataUrl;
			}
			
			addModuleManage.getData();
			// 没有排版，则选择排版，否则直接进入选择分类
			if(addModuleManage.moduleVO.moduleType == void 0){
				$("a[data-tab='02']").show();
				$(".popTabContent-02").show();
				$("a[data-tab='01']").hide();
				$(".popTabContent-01").hide();
			}else{
				$("a[data-tab='01']").show();
				$(".popTabContent-01").show();
				$(".popTabContent-02").hide();
				$("a[data-tab='02']").hide();
			}
			top.SiteManager.handers.addModuleFun = addModuleManage;
		},
		// 保存方法
		save: function(index){
			if(addModuleManage.moduleVO.moduleType == void 0){
				layer.confirm('您确定选择该版式？', {icon: 3, title:'提示'}, function(index){
					var moduleType_ =  $("#modelAddModuleType").val();
					$.ajax({
						type : 'post',
						url : '<%=rootPath%>/site/modifyModuleVO.do',
						data :  {moduleId : addModuleManage.moduleId,moduleType:moduleType_},
						dataType : 'json',
						cache: false,
						async  :  false,
						traditional : true,
						success : function(data){ 
							if(data.code == 1){
								top.Template.reloadModule({moduleId : addModuleManage.moduleId});
								addModuleManage.initialize(false);
							}
							layer.close(index);
						},
						error:function(data){
						}
				     });
					
				});
				
			}else{
				top.SiteManager.tempData.dataType = $("#modelAddType").val();
				top.SiteManager.moduleFun(addModuleManage.tempdata.dataUrl,addModuleManage.tempdata.dataType);
				top.layer.close(index);
			}
		},
		// 同步到页面
		synData: function(name,zhi){
			
		},
		// 获取该module
		getData: function(){
			$.ajax({
				type : 'post',
				url : '<%=rootPath%>/site/loadDynamicModuleDatas.do',
				data :  {moduleId : addModuleManage.moduleId},
				dataType : 'json',
				async  :  false,
				traditional : true,
				success : function(data){ 
					addModuleManage.moduleVO = data.datas.module;
				},
				error:function(data){
				}
		     });
		}
	}

	$(document).ready(function(){
		addModuleManage.initialize(true);
		tab01();
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
</script>
</body>
</html>
