<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@ page import="java.lang.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模板选择</title>
<script type="text/javascript">
    var rootPath = '<%= rootPath%>';
</script>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/template.css" />
<%-- <link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" /> --%>
<link rel="stylesheet" href="<%=rootPath%>/css/page.css" />
<link rel="stylesheet" href="<%=rootPath%>/static/page/pager.css" />
<script src="<%=rootPath%>/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>   
<script src="<%=rootPath%>/static/dialog/dialog.js" type="text/javascript" charset="utf-8"></script>
<script src="<%= rootPath%>/static/plugins/jquery.tmpl.min.js" type="text/javascript"></script>
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/static/page/jquery.pager.js" type="text/javascript"></script> 
<script src="<%=rootPath%>/static/ztree/jquery.ztree.core-3.5.min.js" type="text/javascript"></script> 
<script src="js/selectTemplate.js" type="text/javascript" charset="utf-8"></script>
<style>
.nav-color,.nav-industryType{text-decoration: none;}
.page li { background: #EFF3F5;}
</style>
</head>
<script id="template_data_grid" type="text/x-jquery-tmpl"> 
	
 					{{each(i,template) rows}}
						{{if i%3==0}}
						<li class="column-item-ml0">
						{{else}}
						<li>
						{{/if}}
							<a class="column-image">
								{{if template.templateThumbnailUrl}}
								<img src="{{= template.templateThumbnailUrl}}" />
								{{else}}
								<img src="<%=rootPath%>/images/column_image.png" />
								{{/if}}
								<div class="column-search" onclick="$templateManage.toSearch({{= template.templateId}},'{{= template.templateCode}}');"> 
									<img class="search-icon" src="<%=rootPath%>/images/search_icon.png" >
								</div>
							</a>
							<span class="column-title">
								<h1>模板：{{= template.templateName}}</h1>
								<div class="column-handle">
									<a class="column-btn" href="javascript:;" onclick="$templateManage.toTemplate({{= template.templateId}},'{{= template.templateCode}}');">使用</a>
									{{if template.isCollect=='${loginUser.userId}'}}
									<a href="javascript:;"  class="column-collect-already" id="{{= template.templateId}}" title="已收藏"></a>
									{{else}}
									<a href="javascript:;"  class="column-collect" title="收藏" id="{{= template.templateId}}"></a>
									{{/if}}
								</div>
							</span>
						</li>
					{{/each}}	
</script>

<script id="template_industryType_grid" type="text/x-jquery-tmpl"> 
 					{{each(i,ztreebean) rows}}
						<div class="column-nav-item">
								<label class="column-nav-label">
									<a href="javascript:;" id="{{= ztreebean.id}}" class="nav-industryType">{{= ztreebean.name}}&nbsp;&nbsp;>&nbsp;&nbsp;</a>
								</label>
								
								<div class="column-nav-con">
									{{each(i,child) ztreebean.children}}
									<a href="javascript:;" id="{{= child.id}}" class="nav-industryType"><span></span>{{= child.name}}</a>
									{{/each}}&nbsp;&nbsp;
								</div>
								
							</div>
					{{/each}}	
</script>
<body>
<div id="wrapper">
			<div class="header-container">
				
			<jsp:include page="/jsp/template/selectTemplate/header.jsp"></jsp:include> 
			</div><!--头部-->
			
			<ul class="nav tab">
				<li id="industry" class="current tab-item searchType" ><a>行业分类</a></li>
				<li id="guesslike" class="tab-item searchType" ><a>猜你喜欢</a></li>
				<li id="collect" class="tab-item searchType" ><a>我的收藏</a></li>
			</ul><!--导航-->
			
			<div class="main-column">
				<div class="column">
				<form action="" method="post" id="templateForm">
				   <input type="hidden" id="currentPage" name="page" value="1"/>
				   <input type="hidden" name="rows" value="9"/>
				   <input type="hidden" name="showType" id="showType" value="industry"/>
				   <input type="hidden" name="industryTypeId" id="industryTypeId" value=""/>
				   <input type="hidden" name="colorId" id="colorId" value=""/>
				   <input type="hidden" name="isContainsVedio" id="isContainsVedio" value=""/>
			   </form>
					<ul class="column-nav">
						<li data-nav="nav0">
							<a id="nav-industryType1">行业分类</a>
							<span class="triangle"></span>
						</li>
						<li data-nav="nav1">
							<a id="nav-vedio1">是否有视频</a>
							<span class="triangle"></span>
						</li>
						<li data-nav="nav2">
							<a id="nav-color1">颜色分类</a>
							<span class="triangle"></span>
						</li>
					</ul><!--栏目导航-->
					<div class="column-nav-class-container">
						<div class="column-nav-class column-nav-class-w nav0" id="nav-industryType">
						<div class="column-nav-item">         
						<label class="column-nav-label">          
						<a href="javascript:;" id="choose-all-type" class="nav-industryType">选择全部&nbsp;&nbsp;&gt;&nbsp;&nbsp;</a>         
						</label>                  
						<div class="column-nav-con">                    
						</div>                 
						</div>
						</div>
						
						<div class="column-nav-class nav1" id="nav-vedio">
							<a href="javascript:;" id="choose-all-vedio" class="nav-vedio" >选择全部</a>
							<a href="javascript:;" id="1" class="nav-vedio" >是</a>
							<a href="javascript:;" id="0" class="nav-vedio">否</a>
						</div>
						<div class="column-nav-class nav2" id="nav-color">
							<a href="javascript:;" id="choose-all-color" class="nav-color">选择全部</a>
						</div>
					</div><!--导航下拉菜单容器-->
					<div class="breadcrumb">
						<span class="main-class">模板&nbsp;&nbsp;>&nbsp;&nbsp;</span>
						<span class="sub-class">全部</span>
						<span class="video-judge hide"><span class="line"></span>有视频</span>
						<span class="color-class hide"><span class="line"></span>颜色<i class="color-block" style="background:red"></i></span>
					</div><!--面包屑-->
					<ul class="column-content" id="templateDataGrid">
					</ul><!--栏目内容-->
					<div class="page" >
	              	    <div id="pager">
		              	    <ul class="pages">
		              	    </ul>
	              	   </div> 
					</div>
				</div><!--行业分类-->
				
				
			</div><!--主体栏目-->
			
			<div class="footer">
				<p>北京网视通联科技股份有限公司</p>
				<p>地址：北京市海淀区知春路京仪孵化器D座3层</p>
				<p>Copyright © 2004-2014 CCHVC云通-CDN全业务平台版权所有 京ICP备14002150号-1</p>
				<p>京ICP备14002150号-1 京公网安备1101080213237号</p>
			</div><!--底部-->
			
		</div><!--外部容器-->
		
		
		<!--弹窗-->
		<div class="popup">
			<a class="close-btn"></a><!--关闭按扭-->
			<div class="popup-header">
				<h1>模板：<span id="title" class="tcsj">001</span></h1>
				<a href="javascript:;" class="use-btn tcsj" id="">使用</a>
			</div><!--头部标题-->
			<div class="template-container">
				<div class="template">
					<div id="focus01" class="slider">
						<div class="slider-pic">
							<ul>
								<li><img class="tcsj" src="<%=rootPath%>/images/templet_image_01.jpg" id="img"></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<script>
		$(function(){
			//选项卡切换
			$('.column:first').show();
			$('.nav a').click(function(){
				var parentli_ = $(this).parent('li');
				parentli_.addClass('current').siblings().removeClass('current'); 
				//初始化分页
				initPage();
				$("#showType").val(parentli_.attr("id"));
				templateDataGrid();
			});
			
			//选择分类
			$('.column .column-nav-class:nth-child(1)').css('left','0');
			$('.column .column-nav-class:nth-child(2)').css('left','160px');
			$('.column .column-nav-class:nth-child(3)').css('left','320px');
			$('.column-nav a').hover(function(){
				$(this).siblings().removeClass('triangle-down').addClass('triangle-up');
				$('.column-nav-class').eq($('.column-nav a').index(this)).stop().slideDown(300);
			},function(){
				$(this).siblings().removeClass('triangle-up').addClass('triangle-down');
				$('.column-nav-class').eq($('.column-nav a').index(this)).hide();
			});
			
			$('.column-nav-class').hover(function(){
				$('.column-nav a').eq($('.column-nav-class').index(this)).siblings().removeClass('triangle-down').addClass('triangle-up');
				$(this).show();
			},function(){
				$(this).slideUp(200);
				$('.column-nav a').eq($('.column-nav-class').index(this)).siblings().removeClass('triangle-up').addClass('triangle-down');
			});
			
			//收藏
			$('.column-collect').click(function(){
				$(this).toggleClass('column-collect-sel');
			});
			
			//创建遮罩层
			var mask=document.createElement('div');
			mask.className = 'mask'
			$('body').append(mask);
			//隐藏弹窗
			$('.close-btn').click(function(){
				$('.mask').css('visibility','hidden');
				$('.popup').hide();
			});
			
			
			
			
			
			//初始化分类部分
			$templateManage.setIndustryTypeDatas();
			$templateManage.setColorDatas();
			
			//选择分类
			var $navTiem =  $('.column-nav li');
			var $navContent = $('.column-nav-class-container .column-nav-class');
			
			$('.column .column-nav-class:nth-child(1)').css('left','0');
			$('.column .column-nav-class:nth-child(2)').css('left','160px');
			$('.column .column-nav-class:nth-child(3)').css('left','320px');
			
			$navTiem.hover(function(){
				var $this = $(this);
				var navVlaue = $this.attr('data-nav');
				
				$this.find('.triangle').removeClass('triangle-down').addClass('triangle-up');
				$('.column-nav-class-container .'+navVlaue).show();
			},function(){
				var $this = $(this);
				var navVlaue = $this.attr('data-nav');
				
				$this.find('.triangle').addClass('triangle-down').removeClass('triangle-up');
				$('.column-nav-class-container .'+navVlaue).hide();
			});
			
			$navContent.hover(function(){
				var $this = $(this);
				
				$this.parents('.column-nav-class-container').prev('.column-nav').find('li').eq($this.index()).find('.triangle').addClass('triangle-up').removeClass('triangle-down');
				$this.show();
			},function(){
				var $this = $(this);
				
				$this.parents('.column-nav-class-container').prev('.column-nav').find('li').eq($this.index()).find('.triangle').removeClass('triangle-up').addClass('triangle-down');
				$this.hide();
			});
			
			
			//鼠标点击分类
			$templateManage.toBreadcrumb();
			
			//数据 部分
			templateDataGrid();
			$(".use-btn").click(function(){
				$templateManage.toTemplate(this.id);
			});
			
			$("#templateDataGrid").on("click",".column-collect-already",function(){
				$templateManage.delCollectTemplate(this);
			});
			$("#templateDataGrid").on("click",".column-collect",function(){
				$templateManage.collectTemplate(this);
			});
			
			
			
		});
		</script>
</body>
</html>