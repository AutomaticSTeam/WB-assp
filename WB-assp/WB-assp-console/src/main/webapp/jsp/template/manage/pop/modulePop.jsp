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
<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		    var top = window.parent;
		   	var moduleId= top.SiteManager.tempData.dataUrl;
		    var dataTypeId= top.SiteManager.tempData.dataType;
		    console.log(dataTypeId);
		    var moduleSubmit = {
		    		save: function(){
		    			mediasForm.submit();
		    		}
		    }
		    top.SiteManager.handers.moduleSubmit = moduleSubmit;
</script>
<meta charset="utf-8">
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/common/imageUtil.js" type="text/javascript"></script> 
		 <script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/dialog.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/common/StringUtils.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/common/DateUtils.js" type="text/javascript"></script> 
		 <script src="<%=rootPath%>/static/datepicker/My97DatePicker/WdatePicker.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
	<!--  	<script src="<%=rootPath%>/jsp/webconsole/article/js/query_article_type.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/jsp/webconsole/article/js/addArticle.js" type="text/javascript" charset="utf-8"></script>-->
		<script src="<%=rootPath%>/jsp/website/manage/pop/modulePop.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/static/Huploadify/Huploadify.css"/>
		<script type="text/javascript" src="<%=rootPath%>/static/Huploadify/jquery.Huploadify.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/plugins/json2.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.config.js"></script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.all.min.js"> </script>
 		<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
 		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
 		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />	
		<script type="text/javascript">
		    	var rootPath = '<%= rootPath%>';
		</script>
</head>
<body >
<form id="mediasForm" action="<%=rootPath%>/site/addTemplateModuleVo.do" method="post" class="registerform">
<div class="listPop">
	<div class="pop-head pop-tab-title">
		<a data-tab="01" class="active">常规</a>
	</div><!--pop-head-->
	<div class="pop-body">
		<div class="popTabContent-item popTabContent-01">
			<div class="pop-form m-a">
				<div class="popForm-item">
					<label>模块标题：</label>
					<div class="label-con">
						<input class="inputText" name="moduleName" type="text" value="${templateModule.moduleName}" datatype="*1-20"  nullmsg="请填写模块名称！"  errormsg="标题名称不能多于20个字！" />
						<input type="hidden" id="moduleId" name="moduleId" value="${templateModule.moduleId}"/>
					</div>
				</div>
				 <div class="popForm-item">
					<!-- <label>模块样式：</label>
					<div class="label-con">
						<div class="images-css">
							<img data-css="1" class="active" src="../../../../../images/list_img01.png" alt="" />
							<img data-css="2" src="../../../../../images/list_img03.png" alt="" />
							<img data-css="3" src="../../../../../images/list_img02.png" alt="" />
						</div>
					</div> 
				</div>
				<div class="popForm-item">
					<label>模块内容：</label>
					<div class="listClass-box">
						 <div class="listClass-msg">
							<!-- 显示的文章为：分类为 <span class="listClass-num"><span class="id_00">“今日关注”</span></span> 的文章 -->
						</div>
						<div class="listClass-head">
							<div class="listClass-head-title">
								<div class="fl" id="articleType"></div>
								<div class="fl" id="pictureAlbumType"></div>
								<div class="fl" id="mediaType"></div>
								 <div class="listClass-radio">
									<span class="radio"><!-- <input type="radio" name="class" id="" />全部 --></span>
									<span class="radio"><!-- <input type="radio" name="class" id="" />指定 --></span> 
								</div>
							</div>
							<div class="listClass-btn" id="queryManageType"></div>
							<div class="listClass-checkbox" id="queryCKBoxType"></div>
						</div>
						
					</div><!--listClass-box-->
				</div>
			</div>
		</div><!--popTabContent-->
	</div><!--pop-body-->
</form>
</body>
</html>
