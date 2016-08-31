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
<title>延安精神，中华魂，党史人物，优良传统，中国精神</title>
<script type="text/javascript">
    var rootPath = '<%= rootPath%>';
</script>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/footer.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/model.css" />
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>   
<script src="<%=rootPath%>/js/site.js" type="text/javascript"></script>
<script src="<%=rootPath%>/js/common/DateUtils.js" type="text/javascript"></script>      
<script src="<%=rootPath%>/js/site_frame_module.js" type="text/javascript"></script> 
<script src="<%= rootPath%>/static/plugins/jquery.tmpl.min.js" type="text/javascript"></script>
<!-- 建站操作css -->
	<link rel="stylesheet" href="<%=rootPath %>/css/pop.css" />
<!-- 建站操作css -->
<link rel="stylesheet" href="http://111.206.116.91/wms/template/10005/css/self.css" />

</head>
<body>
		
		<!--网站主题内容-->
		<div id="wrapper">
			
			<jsp:include page="/jsp/template/header.jsp"></jsp:include>
			
			
			<div class="main">
				<div class="w" id="DynamicModules">
				         <div class="breadcrumb">
						<a href="http://localhost:8080/DL-wms-web/template/loadTempLatePage.do?columnsId=1&templateId=1">首页</a> &gt; <b  class="footer-info">中华魂网简介</b>
					</div>
					
					<!-- start -->
					<div class="model ">
						
						<!-- 左列start -->
						<div class="model-item ">
							<div class="model-body">
				                             <!--内容页部分start-->
								<div id="content">
							<c:forEach  items="${articles}" var="article"  varStatus="status">
								 <c:if test="${article.articleId == articleId }">
									<div class="content-head">
										<h1>${article.articleTitle}</h1>
										<!-- <div class="content-bar">
											<span class="time"><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${article.createTime}" /></span>
											<span class="source">来源： ${article.articleSource}</span>
										</div>
										 -->
									</div>
									<div class="content-body">
										
									<!-- 	<div class="content" style="margin: 0 33px;">
											 ${article.articleContent}
										</div>
								 -->
				<div class="content-text">
					<p>由中共中央党校主管、中国延安精神研究会主办的中华魂网络信息中心，是为落实中国延安精神研究会会长李铁映同志和常务副会长伍绍祖同志关于要充分发挥现代化高科技手段宣传延安精神的重要指示而创建的。</p>
					<p>中心完成了在财政部、工商局、税务局、北京市通信管理局、国家质量监督局、统计局等单位的登记注册手续，并取得了《电信与信息服务业务经营许可证》、《信息网络传播视听节目许可证》等资质证件。</p>
					<p>中心目前包含反映我党我军历史和优良传统的图书、影视片，建党以来的大事记、历次党代会的重要资料和1965年前被授予少将以上军衔的军队高级干部简历，《中华魂》杂志自1994年创刊以来的全部内容，研究党的历史和重大现实问题的论文，国内外重要信息等有价值的史料。</p>
					<p>中心与人民政协网等单位互相作了链接、与《纵横》杂志、《春秋周刊》、《瞭望》杂志等单位合作，增强了中心的社会影响力并丰富了中华魂网站的内容。这些内容还在继续充实，可供广大网民无偿使用。</p>
					<p>中心还将与相关单位开展更深层次的合作。目前已经开始与延安儿女联谊会、新四军浙东分会等单位共建网站，共享数据库，节约了大量人力、物力资源，为兄弟单位节省了资金，共同发展红色的网络事业。</p>
					<p>我们正在进行多方面努力，把我们已有的各领域的研究文章与成果进行宣传，也希望广大延安精神研究会的兄弟单位和我们的会员们踊跃提交研究成果，不断的丰富我们的网站，争取早日把网络信息中心建设成学习、研究和宣传延安精神的现代化高科技平台，为延安精神进企业、进校园、进农村、进社区作出新的更大的贡献。</p>
					
				</div>
									</div>
									 </c:if>
								</c:forEach>
									
								</div>
								<!--内容页部分end-->
				                  
						
								
								
								
							</div>
						</div>
						<!-- 左列end -->
						
						
					</div>
					<!-- 两列布局73分end -->
				</div>
			</div><!--main-->
			
			<jsp:include page="/jsp/template/footer.jsp"></jsp:include>
		</div><!--wrapper-->
		<!--网站主题内容-->
		
		<!--建站操作js-->
		<script src="<%=rootPath%>/js/template_manage.js" type="text/javascript"></script>   
		<!--建站操作js-->
</body>
</html>