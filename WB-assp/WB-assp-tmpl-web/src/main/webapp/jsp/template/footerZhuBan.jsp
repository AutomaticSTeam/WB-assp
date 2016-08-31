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
						<a href="http://localhost:8080/DL-wms-web/template/loadTempLatePage.do?columnsId=1&templateId=1">首页</a> &gt; <b class="footer-info">主办单位</b>
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
											<!-- 存放内容 -->
				<div class="content-text">
					<p>中国延安精神研究会是顺应时代的需要，由一批革命战争年代在延安工作、战斗过的老同志倡议，经民政部批准，于1990年5月18日成立的群众性学术团体。挂靠中央党校。彭真同志应邀担任名誉会长，并为中国延安精神研究会题写会名，马文瑞同志被选为会长。1994年，根据彭真同志提议，经新闻出版署批准，创办了以“传播优良传统，讴歌中华文明，弘扬延安精神，锤练四有新人”为宗旨的会刊《中华魂》杂志。江泽民同志题写了刊名。现任会长为李铁映同志，常务副会长为令狐安、逄先知、洪虎、邢世忠、有林、倪豪梅、朱佳木、刘京、储波、陈小津、张启华、苏希胜同志，副会长有刘忠德、李树文、杨永茂、张保庆、李捷、沙健孙、李文海、王立平等26人。秘书长由苏希胜同志兼任。
					</p>	
					<p>中国延安精神研究会成立26年来，一批老同志不顾年高体弱，讲政治、讲奉献、讲团结，在中央领导的关心和中央党校的指导下，围绕中央各个时期的中心任务，做了一些有益的工作：先后组织有关方面的专家和学者编辑出版了近百本论著；摄制了《走自己的道路》、《延安情怀》等多部电视片；编辑出版《中华魂》杂志近300期；开办了中华魂网站，网站建立了革命历史人物资料库，收录了革命历史人物2000余人、各类历史资料2亿余字、视频图像1.1万余小时；召开各种纪念会、座谈会、研讨会200余次，还多次应邀组织老同志到学校、机关、企业宣讲党的优良传统。</p>	
					<p>通过以上活动，传播了延安精神，宣传了党的政策，教育了青年，老同志也从中受到教育。党和国家领导人多次对中国延安精神研究会的工作给予了充分肯定，先后作出重要批示，2004年、2010年，中国延安精神研究会被民政部评为“全国先进民间组织”。
					</p>	
					<p>中国延安精神研究会的同志决心不辜负中央领导同志和广大干部群众的期望，再接再厉，为创建社会主义和谐社会做贡献！</p>	
									
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