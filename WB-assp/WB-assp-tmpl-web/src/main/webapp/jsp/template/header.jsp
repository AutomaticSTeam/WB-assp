<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@ page import="java.lang.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
%>
<link rel="stylesheet" href="http://111.206.116.91/wms/template/${siteTempalte.templateCode}/css/self.css" />
<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
<div class="header">
				<div class="w">
					<div data-model="logo" class="logo" >
						<div class="logo-box" id="${templateVo.templateLogo.templateLogoId }">
							<a href="<%= rootPath%>/1/1/index.html" ><img id="logoImg" src="${templateVo.templateLogo.logoImgUrl }"  alt="logo" title="logo" /></a>
			</div>
		</div>
	</div>
</div>
<!-- 轮播图模块start -->
		<div id="focus01" data-model="focus" class="slider slider-banner slider-1">
			<div class="slider-pic">
				<ul>
					<c:forEach items="${templateVo.bannerPictures}" var="bannerPicture">
						<li>
						      <c:if test="${bannerPicture.hyperlink == null || bannerPicture.hyperlink =='' }">
						             <a title="${bannerPicture.briefDesc }"><img src="${bannerPicture.pictureUrl }" alt="${bannerPicture.briefDesc }"></a>
						      </c:if>
						   <c:if test="${bannerPicture.hyperlink != null && bannerPicture.hyperlink !='' }">
						             <a href="${bannerPicture.hyperlink}" title="${bannerPicture.briefDesc }"><img src="${bannerPicture.pictureUrl }" alt="${bannerPicture.briefDesc }"></a>
						      </c:if>
						 </li>
					</c:forEach>
				</ul>
			</div>
			<div class="slider-title"><a href=""></a></div>
			<div class="slider-nav">
				<c:forEach items="${templateVo.bannerPictures}" var="templateBanner">
					<a><img src="" alt="" /></a>
				</c:forEach>
			</div>
			<script>
			$(function(){
				$('#focus01').slider({
					duration: 1, // 图片切换时间
					delay: 3, // 图片间隔时间
					autoPlay:true,
					width: null,
					height: 319
				});
			});
			</script>
		</div>
			<!-- 轮播图模块end -->
<div data-model="nav" class="nav">
	<div class="w">
		<ul class="menuList">
		  <c:set var="currId" value="1"></c:set>
              <c:forEach items="${templateVo.templateColumns}" var="templateColumn"  varStatus="status">
                   <c:if test="${status.index == 0 }">
                        <c:set var="currId" value="${templateColumn.columnsId}"></c:set>
                     
                   </c:if>
                  <c:if test="${templateColumn.columnsPid == 0 }">
                         <li   <c:if test="${templateColumn.columnsId == columnsId }">class="current" </c:if>>
                                  <a class="menuList-item" href="javascript:Site.NavHref('${templateColumn.customUrl}')" title="">
                             <span>
								<img src="${templateColumn.columnsIcon }" alt="" class="icon" />
								<img src="${templateColumn.columnsIconActive  }" alt="" class="iconHover hide" />
							</span>
                            <span>${templateColumn.columnsName }	</span>
                         </a>
                  </c:if>
                   <c:if test="${templateColumn.columnsId != currId  }">  <div class="subMenu">   <c:set var="currPid" value="${templateColumn.columnsId}"></c:set> </c:if> 
                   <c:forEach items="${templateVo.templateColumns}" var="column">
                           <c:if test="${templateColumn.columnsId == column.columnsPid }">
                              <div class="subMenu-item"><a href="<%= rootPath%>/template/loadTempLatePage.do?columnsId=${column.columnsPid}" title="">${column.columnsName }</a></div>
                           </c:if>
                   </c:forEach>
                  <c:if test="${templateColumn.columnsId != currId  }">  </div>  </c:if> 
              </c:forEach>
		</ul>
	</div>
</div><!--nav-->



