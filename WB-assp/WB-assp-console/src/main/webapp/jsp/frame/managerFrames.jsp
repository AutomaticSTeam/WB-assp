<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
   String rootPath = request.getContextPath();
//设置缓存为空   
	response.setHeader("Pragma","No-cache");   
	response.setHeader("Cache-Control","no-cache");   
	response.setDateHeader("Expires",   0); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模块管理</title>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
</script>
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
                    <a href="#">Home</a>
                </li>
                <li class="active"> 框架模板 </li>
            </ul>
        </div> 
        
       <!--body wrapper start-->
        <div class="wrapper">
        <div class="row">
        <div class="col-sm-12">
        <section class="panel">
        <header class="panel-heading">
                                    框架板式管理
            <span class="tools pull-right">
                <a href="javascript:;" class="fa fa-chevron-down"></a>
                <a href="javascript:;" class="fa fa-times"></a>
             </span>
        </header>
        <div class="panel-body">
         
         <form class="form-horizontal" role="form">
            <!--  <fieldset> -->
                 <div class="form-group">
                    <label class="col-sm-2 control-label" for="ds_host">板式名称</label>
                    <div class="col-sm-4">
                       <input class="form-control" name="moduleTmplTitle"  id="moduleTmplTitle"/>
                    </div>
                    <label class="col-sm-2 control-label" for="ds_name">关键字</label>
                    <div class="col-sm-4">
                       <input class="form-control" id="ds_name" />
                    </div>
                 </div>
                 <div class="form-group">
                    <label class="col-sm-2 control-label" for="ds_username">操作人</label>
                    <div class="col-sm-4">
                       <input class="form-control" id="ds_username" />
                    </div>
                    <label class="col-sm-2 control-label" for="ds_password">时间</label>
                    <div class="col-sm-4">
                       <input class="form-control" id="ds_password"  />
                    </div>
                 </div>
             <!--  </fieldset>   -->
         </form>
          
         <div class="qC" style="margin-bottom: 10px;">
           <a class="btn btn-info " type="button" id="module-edit" ><i class="fa fa-edit"></i> 编辑</a>
           <a class="btn btn-info " type="button" id="module-del" ><i class="fa fa-times"></i> 删除</a>
           <a class="btn btn-info " type="button" id="module-preview" ><i class="fa fa-search-plus"></i> 预览</a>
           <a class="btn btn-info " type="button" id="module-joinDate" ><i class="fa fa-plus"></i> 关联数据</a>
         </div>
        <div class="adv-table">
        <table  class="table table-bordered table-striped" id="dynamic-table">
        <thead>
        <tr>
            <th>选择</th>
            <th>名称</th>
            <th>预览图</th>
            <th>描述</th>
            <th>操作人</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody id="moduleDataGrid">
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
</body>
<jsp:include page="/jsp/common/commonJs.jsp" /> 
<script type="text/javascript" src="<%=rootPath%>/jsp/frame/js/managerFrames.js"></script> 
</html>