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
  <style type="text/css">
    .qC{
      margin: 5 0 10 0;
    }
    .optIcon{
       width: 10px;
       padding: 0 0 0 10;
    }
  </style>
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
                assp
            </h3>
            <ul class="breadcrumb">
                <li>
                    <a href="#">控制台</a>
                </li>
                <li class="active"> 管理模板 </li>
            </ul>
        </div> 
        
       <!--body wrapper start-->
        <div class="wrapper">
        <div class="row">
        <div class="col-sm-12">
        <section class="panel">
        <header class="panel-heading">
                                    模板管理
            <span class="tools pull-right">
                <a href="javascript:;" class="fa fa-chevron-down"></a>
                <a href="javascript:;" class="fa fa-times"></a>
             </span>
        </header>
        <div class="panel-body">
         <div class="qC">
          <!--  <form role="form">
                   <label>模板名称</label>
                   <input type="text" class="form-control" >
                   <label>操作人</label>
                   <input type="text" class="form-control" >
               
               <button type="submit" class="btn btn-primary">Submit</button>
           </form> -->
           <a class="btn btn-info " type="button" href="<%=rootPath%>/jsp/template/addTemplate.jsp"><i class="fa fa-plus"></i>添加模板</a>
         </div>
        <div class="adv-table">
        <table  class="table table-bordered table-striped" id="dynamic-table">
        <thead>
        <tr>
            <th>操作</th>
            <th>模板名称</th>
            <th>模板Code</th>
            <th>状态</th>
            <th>操作人</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody id="templateDataGrid">
        </tbody>
     </table>
	   <div class="row-fluid">
		<div class="span6">
		  <div class="dataTables_paginate paging_bootstrap pagination">
				<ul>
					<li class="prev disabled"><a href="#">← Previous</a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li class="next"><a href="#">Next → </a></li>
				</ul>
			</div>
		</div>
		</div>
		</div>
        </div>
        </section>
        </div>
        </div>
        </div>
        <!--body wrapper end-->




    </div>
    <!-- main content end-->
</section>

  <!--dynamic table-->
 <jsp:include page="/jsp/common/commonJs.jsp" />
 <script type="text/javascript" src="<%=rootPath%>/jsp/template/js/templateConsole.js"></script>
</body>
</html>



