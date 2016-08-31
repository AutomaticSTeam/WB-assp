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
</head>
<body >
<div class="popMenu" id="popMenu">
	<div class="pop-head pop-tab-title">
		<a data-tab="01" class="active">常规</a>
	</div><!--pop-head-->

	<div class="pop-body">
		<div class="popTabContent-item popTabContent-01">
			<div class="pop-form">
				<div class="popForm-item">
					<label>菜单名称：</label>
					<div class="label-con">
						<input class="inputText" type="text" name="columnsName" id="columnsName"/>
					</div>
				</div>
				<div class="popForm-item">
					<label>菜单类型：</label>
					<div class="label-con">
						<select class="select" name="columnsTypeId" id="columnsTypeId">
							<option value="2">自定义菜单</option>
						</select>
					</div>
				</div>
				<div class="popForm-item" id="parent-level">
					<label>选择上级菜单：</label>
					<div class="label-con">
						<select class="select" name="columnsPid" id="columnsPid">
							<option value="0">--无--</option>
						</select>
					</div>
				</div>
			</div><!--pop-form-->
		</div><!--popTabContent-->
	</div><!--pop-body-->
</div><!--popAddMenu-->
<script>
var topNav = window.parent;
$(document).ready(function(){
	perNavManage.initialize();
});
var perNavManage = {
		id: "perNav",
		// 该导航临时数据集
		columnData: null,
		// 初始化
		initialize: function(){
			perNavManage.columnData = topNav.navManage.tempData;
			var pid = -1;
			var columnsId = -1;
			
			if(perNavManage.columnData.columnsId != void 0){
				$("#columnsName").val(perNavManage.columnData.columnsName);
				if(perNavManage.columnData.columnsTypeId == 1){
					$("#columnsTypeId").empty();
					$("#columnsTypeId").append('<option value="1">系统菜单</option>');
				}
				columnsId = perNavManage.columnData.columnsId;
				pid = perNavManage.columnData.columnsPid;
			}
			
			//初始化菜单
			if(topNav.navManage.id == 'navFun'){
				var columnsData = topNav.navManage.columnsData;
				var $columnsPid = $("#columnsPid");
				if(columnsData != null){
					for(var i = 0;i < columnsData.length; i++){
						if(columnsData[i].dataStatus == 0 && columnsData[i].columnsPid != columnsId && columnsData[i].columnsId != columnsId){
							
							if(pid == columnsData[i].columnsId)
								$columnsPid.append("<option value='"+columnsData[i].columnsId+"' selected>"+columnsData[i].columnsName+"</option>");
							else
								$columnsPid.append("<option value='"+columnsData[i].columnsId+"'>"+columnsData[i].columnsName+"</option>");
						}
					}
				}
			}else{
				$("#parent-level").remove();
			}
			// 添加进父handler
			topNav.navManage.handers.perNav = perNavManage;
		},
		// 保存
		save: function(){
			if($("#columnsName").val() == ""){
				top01.layer.alert("请填写导航名");
				return;
			}
			$(".pop-body").find("select,input:visible").each(function(){
				perNavManage.columnData[this.name] = $(this).val();
			});
			
			//如果是添加 则现在就保存，否则只做页面修改
			if(perNavManage.columnData == null || perNavManage.columnData.columnsId == void 0 ||perNavManage.columnData.columnsId == null ){
				perNavManage.columnData.templateId = topNav.navManage.templateId;
				perNavManage.columnData.footerId = topNav.navManage.footerId;
				perNavManage.columnData.sortNum = topNav.navManage.maxSortNum();
				$.ajax({
					type : 'post',
					url : topNav.ADDNAVURL,
					data :  perNavManage.columnData,
					dataType : 'json',
					async  :  false,
					traditional : true,
					success : function(data){ 
						if(data.code == 1){
							 topNav.navManage.tempData = data.datas.templateColumns ;
							 topNav.navManage.complete();
						}else {
							 topNav.layer.alert("保存失败，请重试");
						}
					 },
					error:function(data){
					}
			     }); 
			}else {
				 topNav.navManage.complete();
			}
		}
};
</script>
</body>
</html>
