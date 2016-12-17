<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
 <% String rootPath = request.getContextPath();%>
	<script type="text/javascript">
	<!--
	       var rootPath = '<%=rootPath%>';
	//-->
	</script>
	
    <!-- left side start-->
    <div class="left-side sticky-left-side">

        <!--logo and iconic logo start-->
        <div class="logo">
            
            <a href="index.html"><img src="<%=rootPath%>/images/logo_icon.png" alt=""> SSP <span style="color:#ff6c60; font-family: 微软雅黑; font-size: 12px;">全世界最好的建站系统</span></a>
        </div>

        <div class="logo-icon text-center">
            <a href="index.html">  <img src="<%=rootPath%>/images/logo_icon.png" alt=""></a>
        </div>
        <!--logo and iconic logo end-->

        <div class="left-side-inner">

            <!--sidebar nav start-->
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li><a href="<%=rootPath%>/jsp/asspConsole.jsp"><i class="fa fa-home"></i> <span>Home</span></a></li>
                <li class="menu-list"><a href=""><i class="fa fa-building-o"></i> <span>管理模板</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="<%=rootPath%>/jsp/template/templateConsole.jsp"><i class="fa fa-laptop"></i>模板管理</a></li>
                        <li><a href="<%=rootPath%>/jsp/frame/managerFrames.jsp"><i class="fa fa-road"></i>框架管理</a></li>
                        <li><a href="<%=rootPath%>/jsp/module/managerModule.jsp"><i class="fa fa-table"></i>模块管理</a></li>
                        <li><a href="<%=rootPath%>/jsp/webconsole/cloudDatas.jsp"><i class="fa fa-cloud"></i>云端数据</a></li>

                    </ul>
                </li>
                <li class="menu-list"><a href=""><i class="fa fa-users"></i> <span>客户管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="blank_page.html">管理客户</a></li>
                        <li><a href="boxed_view.html">会员管理</a></li>
                    </ul>
                </li>
                <li class="menu-list"><a href=""><i class="fa fa-laptop"></i> <span>运营分析</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="blank_page.html">模板统计</a></li>
                        <li><a href="boxed_view.html">用户模板</a></li>
                    </ul>
                </li>
                <li class="menu-list"><a href=""><i class="fa fa-user"></i> <span>用户中心</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="#">账户管理</a></li>
                        <li><a href="#">费用中心</a></li>
                    </ul>
                </li>

                <li><a href="login.html"><i class="fa fa-sign-in"></i> <span>登录</span></a></li>

            </ul>
            <!--sidebar nav end-->

        </div>
    </div>
    <!-- left side end-->
