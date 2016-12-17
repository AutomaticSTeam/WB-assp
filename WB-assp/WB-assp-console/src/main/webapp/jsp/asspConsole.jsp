<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="keywords" content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
  <meta name="description" content="">
  <link rel="shortcut icon" href="#" type="image/png">
   <% String rootPath = request.getContextPath();%>
	<script type="text/javascript">
	<!--
	       var rootPath = '<%=rootPath%>';
	//-->
	</script>
  <title>易站-全世界最好用的建站系统</title>
  <jsp:include page="/jsp/common/commonCssLink.jsp" />
</head>
<body class="sticky-header">
  <section>
	
    <!-- left side start-->
      <jsp:include page="/jsp/common/commonLeftSide.jsp" />
    <!-- left side end-->
    
    <!-- main content start-->
    <div class="main-content" >

        <!-- header section start-->
             <jsp:include page="/jsp/common/headerSection.jsp" />
        <!-- header section end-->

        <!-- page heading start-->
        <div class="page-heading">
            <h3>
                                                      管理员
            </h3>
            <ul class="breadcrumb">
                <li>
                    <a href="#">控制台</a>
                </li>
                <!-- <li class="active"> My Dashboard </li> -->
            </ul>
        </div> 

        <!--footer section start-->
        <footer>
      
        </footer>
        <!--footer section end-->


    </div>
    <!-- main content end-->
</section>

 <jsp:include page="/jsp/common/commonJs.jsp" />

</body>
</html>



