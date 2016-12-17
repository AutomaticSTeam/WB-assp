<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% 
String rootPath = request.getContextPath();
String templateId= request.getParameter("templateId");
String columnsId= request.getParameter("columnsId");
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/baseWeb.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
	</head>
	<style>
		#header .btn-logout{
			float: right;
			margin-right: 100px;
		}
		#header .btn-logout a {
			display: inline-block;
			width: 98px;
			height: 26px;
			border: 1px solid #80d7ff;
			border-radius: 2px;
			line-height: 26px;
			text-align: center;
			color: #fff;
			font-size: 13px;
			text-decoration: none;
		}
	</style>
	<body class="body">
		<div id="main">
			<div class="main-left" style="margin-left: 13%;     background-color: #d9dee4; min-height: 700px;">
				<div class="nav">
					<ul class="menu-list">
					<%-- <shiro:hasPermission name="wms:article:manage"> --%>
						<li>
							<div class="menuList-item active">
								<i class="icon icon-01"></i>
								文章管理
								<span class="arrow"></span>
							</div>
							<div class="menuList-content">
							   <%-- <shiro:hasPermission name="wms:article:add"> --%>
								<div class="menuList-content-item">
									<a href="javascript:;" id="addArticle"data-url="<%=rootPath%>/jsp/webconsole/article/addArticle.jsp">添加文章</a>
								</div>
								<%-- </shiro:hasPermission>
								<shiro:hasPermission name="wms:article:marticle"> --%>
								<div class="menuList-content-item">
									<a href="javascript:;" data-url="<%=rootPath%>/jsp/webconsole/article/articleManage.jsp" class="active">管理文章</a>
								</div>
								<%-- </shiro:hasPermission>
								<shiro:hasPermission name="wms:article:articletype"> --%>
								<div class="menuList-content-item">
									<a href="javascript:;" data-url="<%=rootPath%>/jsp/webconsole/article/articleTypeManage.jsp">管理文章分类</a>
								</div>
								<%-- </shiro:hasPermission> --%>
							</div>
						</li>
						<%-- </shiro:hasPermission>
						
						<shiro:hasPermission name="wms:album:manage"> --%>
							<li>
								<div class="menuList-item">
									<i class="icon icon-02"></i>
									图册管理
									<span class="arrow"></span>
								</div>
								<div class="menuList-content">
								<%-- <shiro:hasPermission name="wms:album:add"> --%>
									<div class="menuList-content-item">
										<a href="javascript:;" id="createOrEditAlbum" data-url="<%=rootPath%>/jsp/webconsole/album/createOrEditAlbum.jsp">创建图册</a>
									</div>
									<%-- </shiro:hasPermission>
									<shiro:hasPermission name="wms:album:malbum"> --%>
									<div class="menuList-content-item">
										<a href="javascript:;" id="albumManage" data-url="<%=rootPath%>/jsp/webconsole/album/albumManage.jsp">管理图册</a>
									</div>
									<%-- </shiro:hasPermission>
									<shiro:hasPermission name="wms:album:malbumtype"> --%>
									<div class="menuList-content-item">
									        <a href="javascript:;" id="albumManage" data-url="<%=rootPath%>/jsp/webconsole/album/albumTypeManage.jsp">管理图册分类</a>
								     </div>
								     <%-- </shiro:hasPermission> --%>
								</div>
							</li>
						<%-- </shiro:hasPermission>
						<shiro:hasPermission name="wms:media:manage"> --%>
						<li>
							<div class="menuList-item">
								<i class="icon icon-04"></i>
								视频管理
								<span class="arrow"></span>
							</div>
							<div class="menuList-content">
							 <%-- <shiro:hasPermission name="wms:media:add"> --%>
								<div class="menuList-content-item">
									<a href="javascript:;" id="createOrEditVedio" data-url="<%=rootPath%>/jsp/webconsole/vedio/createOrEditVedio.jsp">添加视频</a>
								</div>
								<%-- </shiro:hasPermission>
								<shiro:hasPermission name="wms:media:mmedia"> --%>
								<div class="menuList-content-item">
									<a href="javascript:;" id="vedioManage" data-url="<%=rootPath%>/jsp/webconsole/vedio/vedioManage.jsp">管理视频</a>
								</div>
								<%-- </shiro:hasPermission>
								<shiro:hasPermission name="wms:media:mmediatype"> --%>
									<div class="menuList-content-item">
									<a href="javascript:;" data-url="<%=rootPath%>/jsp/webconsole/vedio/vedioTypeManage.jsp">管理视频分类</a>
								</div>
								<%-- </shiro:hasPermission> --%>
							</div>
						</li>
						<%-- </shiro:hasPermission>
						<shiro:hasPermission name="wms:statistics:manage"> --%>
						<%-- <li>
							<div class="menuList-item">
								<i class="icon icon-03"></i>
								网站统计
								<span class="arrow"></span>
							</div>
							<div class="menuList-content">
							<shiro:hasPermission name="wms:statistics:article">
								<div class="menuList-content-item">
									<a href="javascript:;" data-url="文章统计.html">文章统计</a>
								</div>
								</shiro:hasPermission>
							</div>
						</li> --%>
						<%-- </shiro:hasPermission> --%>
						<li>
							<div class="menuList-item">
								<i class="icon icon-03"></i>
								logo管理
								<span class="arrow"></span>
							</div>
							<div class="menuList-content">
								<div class="menuList-content-item">
									<a href="javascript:;" data-url="<%=rootPath%>/jsp/webconsole/logo/logoManage.jsp">管理logo</a>
								</div>
							</div>
						</li>
						<li>
							<div class="menuList-item">
								<i class="icon icon-03"></i>
								页脚管理
								<span class="arrow"></span>
							</div>
							<div class="menuList-content">
								<div class="menuList-content-item">
									<a href="javascript:;" data-url="<%=rootPath%>/jsp/webconsole/footer/footerManage.jsp">管理页脚</a>
								</div>
							</div>
						</li>
					</ul>
				</div><!--nav-->
			</div><!--main-left-->
			<div class="main-right">
				<iframe src="<%=rootPath%>/jsp/webconsole/article/articleManage.jsp" frameborder="0" scrolling="auto" style="width:100%;min-height:836px; background-color: #fff;" id="baseData"></iframe>
			</div>
		</div><!--main-->
		
		<script src="<%=rootPath%>/js/backstage_left.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>