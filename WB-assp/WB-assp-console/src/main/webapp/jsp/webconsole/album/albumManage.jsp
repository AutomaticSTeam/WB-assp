<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script> 
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/jsp/webconsole/album/js/albumManage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/jsp/webconsole/album/js/query_album_type.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/page/jquery.pager.js" type="text/javascript"></script>
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		    var canReview = 0 ; //不可以审核
			</script>
		  <%-- <shiro:hasPermission name="wms:album:audit"> --%>
		      <script type="text/javascript">
		                canReview = 1 ; //不可以审核
	      	</script>
	      	<%-- </shiro:hasPermission> --%>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
		<link rel="stylesheet" href="<%=rootPath%>/jsp/webconsole/album/css/albumManage.css" />
	</head>
<body>
		<div id="managementAtlas">
			<div class="column">
				<h2 class="column-tit"><span class="tit">管理图册</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
				<div class="main-head" style="margin-bottom: 80px;">
					<a class="btn btn-border-gray border-gray-new dib addContentBtn" id="creatAblum"  data-url="<%=rootPath%>/jsp/webconsole/album/createOrEditAlbum.jsp">创建图册</a>
					<span class="msg">您可以通过添加“图册展示”模块来展示图册</span>
					<div style="margin-top: 25px;">
					<form action="" method="post" id="albumForm">
					<input type="hidden" id="currentPage" name="page" value="1"/>
				    <input type="hidden" name="rows" value="9"/>
					<div class="search-item">
							<label>图册搜索：</label>
							<div class="box-input">
								<input class="inputText w280" name="albumName"  id="albumName" type="text" />
							</div>
						</div>
						<div class="search-item">
							<label>分类：</label>
							<div class="box-input">
								<select class="select w180" name="albumTypeId" id="albumTypeId">
									<option value="">请选择</option>
								</select>
							</div>
						</div>
						</form>
					</div>
					
				</div><!--main-head-->
				<div class="main-body">
					<div class="table">
						<table border="0" cellspacing="0" cellpadding="0" id="ablumTable">
							<tr>
								<th class="tl pl40">图册名称</th>
								<th class="tl pl40">图册类别</th>
								<th class="w250">操作</th>
							</tr>
						</table>
					</div><!--table-->
					<div class="page">
						<div id="pager">
		              	    <ul class="pages">
		              	    </ul>
	              	    </div>
					</div>
					<div class="pic-show" style="background-color: #eee;">
						<div class="popImgs" >
												
						</div>
					</div><!--pic-show-->
				</div><!--main-body-->
			</div><!--main-content-->
		</div><!--managementArticles-->
		<div id="review" Style="display:none;">
		    <div class="fast_doc">       
		        <div class="fast_box">
		            <ul id="picList">   
		            </ul>        
		        </div>
			</div>
		</div><!--picList-->
		<%-- <script src="<%=rootPath%>/jsp/webconsole/album/js/backstage_picBook.js" type="text/javascript" charset="utf-8"></script>  --%>
	</body>
</html>