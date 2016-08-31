<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@ page import="java.lang.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
	String siteId= request.getParameter("siteId");
%>
<!-- 建站操作头部 -->
		<div id="topBarArea" class="topBarArea">
			<div class="topBarArea-fixed">
				<div class="w">
					<ul class="topBarArea-nav fl">
						<li><a href="<%=rootPath%>/jsp/common/login/welcome.jsp">极速建站</a><span>|</span></li>
						<li><a href="<%=rootPath%>/jsp/webconsole/siteConsole.jsp?templateId=${tempalte.templateId}&columnsId=${columnsId}&siteId=${siteId}">网站管理</a><span>|</span></li>
						<li><a href="<%=rootPath%>/wms/product/toProductView.do?templateId=${tempalte.templateId}">购买</a></li>
					</ul>
					<ul class="topBarArea-right fr">
						<!-- <li class="myWeb">我的网址<div class="web"><a href="">http://www.zhonghuahun.org/</a></div></li> -->
				<!-- 		<li class="save"><button class="btn">保存</button></li>
						<li class="cancel"><button class="btn">取消</button></li> -->
						<c:if test="${loginUser != null }">
						<li class="save btn-website-edit" style="display:none;"><button class="btn" onclick="SiteManager.save();">保存</button></li>
						<li class="cancel btn-website-edit" style="display:none;"><button class="btn" onclick="SiteManager.cancel();">取消</button></li>
						<li class="btn-website-process" style="display:none;">正在处理中，请稍后。。。。。。</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div><!-- topBarArea -->
<!-- 建站操作头部 -->
