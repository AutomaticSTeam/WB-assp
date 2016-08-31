<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
         <script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		</script>
		<link rel="stylesheet" href="<%=rootPath%>/static/Validform_v5.3.2/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
	</head>
	<body>
			<div class="column">
				<h2 class="column-tit"><span class="tit">站点信息编辑</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
					<div class="pop-body">
						<div class="popTabContent-item popTabContent-01">
							<form id="websiteForm"  method="post" class="websiteForm" action="<%=rootPath%>/contents/website/editWebsiteVO.do">
							<input type="hidden" name="siteId" value="${websiteVO.siteId }"/>
								<div class="pop-form">
									<div class="popForm-item">
										<label>站点名称：</label>
										<div class="label-con">
											<input class="inputText" type="text" name="siteName" nullmsg="站点名称不能为空！" id="siteName" value="${websiteVO.siteName }"/>
										</div>
									</div>
									<div class="popForm-item">
										<label>站点域名：</label>
										<div class="label-con">
											<input class="inputText" type="text" name="siteDomain" nullmsg="站点域名不能为空！" id="siteDomain" value="${websiteVO.siteDomain }"/>
										</div>
									</div>
									<div class="pop-btn">
										<a class="btn dib btn-blue01 cave-btn saveBtn">保存</a>
									</div>
								</div><!--popForm-->
							</form>
						</div><!--popTabContent-->
					</div><!--pop-body-->
			</div><!--main-content-->
		
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=rootPath%>/static/Validform_v5.3.2/Validform_v5.3.2_min.js"></script>
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/jsp/webconsole/website/js/editWebsite.js" type="text/javascript" charset="utf-8"></script>
		<script>
		$(document).ready(function(){
			editWebsite.initialize();
		});
		</script>
	</body>
</html>