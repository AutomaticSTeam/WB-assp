<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" type="text/css" href="http://1.202.135.37/wms/template/10004/css/self.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/css/model.css" />
<link href="<%=rootPath%>/css/base.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=rootPath%>/css/base_console.css" />
<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />

<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>  
<script type="text/javascript" src="<%=rootPath%>/js/frame/managerFrames.js"></script> 
<title>模板管理欢迎页</title>
<script type="text/javascript">
   var rootPath = '<%= rootPath%>';
   $(function(){
       var $modelBody = $('.model-body');

       $modelBody.append('<div class="model-background">' +
                       '<span class="add-icon"></span>'+
               '<img style="width:150px;padding-top:15px;" src="../../images/welcome.jpg"/>' +
               '</div>');
       $('.model .model-item').children().first().css({"margin-left":"0","margin-right":"0px"});
       var len = $(".model").length;
       for(var i=1;i<=5;i++){
           $('.model-array-'+i+' .model-item').children().first().css({"margin-left":"0","margin-right":"20px"});
           $('.model-array-'+i+' .model-item').children().last().css({"margin-right":"0","margin-left":"20px"});
       }
       //model滑过事件

       $(".model-body .model-background").hover(function (){

           $(this).find(".add-icon").show();
       },function(){
           $(this).find(".add-icon").hide();
       })
   });
</script>
</head>
<body>
	<div class="w">
	<c:forEach items="${reasult}" var="frame">
		<c:if test="${frame.frameId==1 }">
			<div class="model">
		        <div class="model-item">
		            <div class="model-body">
		            </div>
		        </div>
		    </div>
		</c:if>
		<c:if test="${frame.frameId!=1 }">
			<div class="model model-array-${frame.frameId }">
				<c:forEach  begin="1" end="${frame.frameId}" var="i">
					<div class="model-item fl">
			            <div class="model-body">
			            </div>
			        </div>
				</c:forEach>
			</div>
		</c:if>
	</c:forEach>
	</div>
	<div class="pop-btn">
		<a class="btn dib btn-green01 cancel-btn" id="backStep">返回</a>
	</div>	
</body>
</html>