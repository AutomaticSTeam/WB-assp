<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@ page import="java.lang.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
%>

<div class="footer">
	<div class="links">
		<div class="w">
			<div data-model="imgText">
				<div class="imgText">
				     <c:forEach items="${templateVo.templateFooter.templateColumns}" var="column" varStatus="status">
				        <c:if test="${ column.customUrl == null ||  column.customUrl == '' }">
		                    <a >${column.columnsName }</a>
		                 </c:if>
		                 <c:if test="${column.openType == 0 && column.customUrl != null && column.customUrl != '' }">
		                    <a href="javascript:Site.URLHref ('${column.customUrl}');">${column.columnsName }</a>
		                 </c:if>
		                 <c:if test="${column.openType == 1}">
		                    <a href="${column.customUrl}" title="">${column.columnsName }</a>
		                 </c:if>
		                 <c:if test="${status.index !=  fun:length(templateVo.templateFooter.templateColumns) - 1}">
		                       <span> | </span>
		                 </c:if>
		            </c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- 二维码 -->
	<%-- <div class="code" style=" height: 120px;position: fixed; right: 20px;top: 84px;z-index: 1000;border: 1px solid #ccc;">
		<img src="<%=rootPath%>/images/code.jpg" style="max-width: 120px;height: auto;">
	</div> --%>
	<div class="footer-body" data-model="footer">
		<div class="w">
			<div class="copyright">
				<div class="copyright-body">
					${templateVo.templateFooter.copyrightInfo}
					<p>技术支持：
					          <span <c:if test="${templateVo.templateFooter.isShowTechnicalSupport == 1}"> style="display:none;" </c:if>><a href="#">wms建站</a>  | </span> 
					          <span <c:if test="${templateVo.templateFooter.isShowTechnicalSupport == 1}"> style="display:none;" </c:if>><a href="#">手机版 </a>  |   </span> 
					          <span <c:if test="${templateVo.templateFooter.isShowTechnicalSupport == 1}"> style="display:none;" </c:if>><a href="<%=rootPath%>/jsp/common/login/login.jsp">    管理登录</a> </span> 
					</p>
				</div>
				<%-- <div class="accessAmount">
						您是第<span class="cred" id="userNum">${sessionScope.userNum}</span>位访客
				</div> --%>
			</div>
		</div>
	</div>
</div><!--footer-->
