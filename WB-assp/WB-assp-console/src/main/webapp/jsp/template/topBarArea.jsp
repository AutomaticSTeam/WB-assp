<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@ page import="java.lang.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
%>
<style>
  .topBarArea-nav li{
    float: left;
    text-indent: 0;
    padding: 0 2px;
    margin: 0!important;
    list-style-image: none!important;
    height: 30px;
    line-height: 30px
  }
  
  .topBarArea-nav li a{
    display: block;
    width: 80px;
    height: 30px;
    font-size: 12px;
    cursor: pointer;
    line-height: 30px;
    color: #f4f4f4;
    text-align: center;
    text-decoration: none;
    background-color: #5397d0;
    margin-top: 8px;
    font-weight: bold;
  }
  
  .panels{
     margin-top: 42px;
  }
  .panel{
     margin-left: 480px; 
  }
</style>
<script type="text/javascript">
   var topBarTools = {
		//设置模板主题
		setTemplateThemes : function(){
			$(".panels").show();
			$(".panel").hide();
			$("#templateThemes").show();
		},
		//设置板式
		setPageFrames : function(){
			$(".panels").show();
			$(".panel").hide();
			$("#pageFrames").show();
		},
		//静态化
		setPageStatic : function(){
			$(".panels").hide();
			$(".panel").hide();
			//$("#pageStatic").show();
		},
        //设置
		templateSetting : function(){
			$(".panels").show();
			$(".panel").hide();
			$("#templateSetting").show();
		}
   };
</script>
<!-- 建站操作头部 -->
<div id="topBarArea" class="topBarArea" >
	<div class="topBarArea-fixed">
		<div class="w">
			<ul class="topBarArea-nav fl">
			    <li><a onclick="topBarTools.setTemplateThemes()">模板主题</a></li>
				<li><a onclick="topBarTools.setPageFrames()" >页面板式</a></li>
				<li><a onclick="topBarTools.setPageStatic()" >页面静态化</a></li>
				<li><a onclick="topBarTools.templateSetting()" >模板设置</a><span> >> </span></li>
				
			</ul>
		    <ul class="topBarArea-right fr">
			<li class="save btn-website-edit" ><button class="btn" onclick="SiteManager.save();">保存</button></li>
			<li class="cancel btn-website-edit" ><button class="btn" onclick="SiteManager.cancel();">取消</button></li>
			<li class="btn-website-process" style="display:none;">正在处理中，请稍后。。。。。。</li>
			<li class="save btn-website-edit" ><button class="btn" >返回首页</button></li>
			</ul>
		</div>
		
	</div>
	<!-- tab panels -->
		<div id="panels" class="panels" style="width:1400px; display: block;" >
		   <div id="templateThemes" class="panel undis">
		      <iframe frameborder="0"  width="100%" height="205px" scrolling="auto" src="<%=rootPath %>/jsp/template/manage/templateThemes.jsp"></iframe>
		   </div>
		   <div id="pageFrames" class="panel undis ">
		      <iframe frameborder="0"  width="100%" height="205px" scrolling="auto" src="<%=rootPath %>/jsp/template/manage/pageFrames.jsp"></iframe>
		   </div>
		  <div id="pageStatic" class="panel undis">
		      <iframe frameborder="0"  width="100%" height="205px" scrolling="auto" src="<%=rootPath %>/jsp/template/manage/templateThemes.jsp"></iframe>
		   </div>
		  <div id="templateSetting" class="panel undis">
		      <iframe frameborder="0"  width="100%" height="205px" scrolling="auto" src="<%=rootPath %>/jsp/template/manage/templateSetting.jsp"></iframe>
		   </div>
		</div>
	
</div><!-- topBarArea -->

<!-- 建站操作头部 -->
