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
  <title>易站-全世界最好用的建站系统-添加模板</title>
  <jsp:include page="/jsp/common/commonCssLink.jsp" />
   <link href="<%=rootPath%>/css/jquery.stepy.css" rel="stylesheet">
    <!--multi-select-->
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/jquery-multi-select/css/multi-select.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/ios-switch/switchery.css" />
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
          <!--body wrapper start-->
        <section class="wrapper">
        <!-- page start-->

        <hr/>
        <!--body wrapper start-->
        <div class="wrapper">
            <div class="row">
                <div class="col-md-12">
                    <div class="square-widget">
                        <div class="widget-container">
                            <div class="stepy-tab">
                            </div>
                            <form id="default" class="form-horizontal">
                                <fieldset title="Initial Info">
                                    <legend>模板基本信息</legend>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">模板名称</label>
                                        <div class="col-md-6 col-sm-6">
                                            <input type="text" placeholder="模板名称" class="form-control" name="templateName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">模板应用平台</label>
                                        <div class="col-md-6 col-sm-6" style="line-height: 35px; height: 25px;">
			                                    <input type="radio" value="1"  name="platformId" checked="checked"> PC
			                                    <input type="radio" value="2"  name="platformId"> 手机
			                                    <input type="radio" value="3"  name="platformId"> 微信
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset title="Contact Info">
                                    <legend>模板关联信息</legend>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">模板所属行业类型</label>
                                        <div class="col-md-6 col-sm-6">
					
					                            <div class="col-md-9">
					                                <select multiple="multiple" class="multi-select" id="industryTypeId"
					                                        name="industryTypeId">
					                                    <optgroup label="NFC EAST">
					                                        <option>Dallas Cowboys</option>
					                                        <option>New York Giants</option>
					                                        <option>Philadelphia Eagles</option>
					                                        <option>Washington Redskins</option>
					                                    </optgroup>
					                                    <optgroup label="NFC NORTH">
					                                        <option>Chicago Bears</option>
					                                        <option>Detroit Lions</option>
					                                        <option>Green Bay Packers</option>
					                                        <option>Minnesota Vikings</option>
					                                    </optgroup>
					                                    <optgroup label="NFC SOUTH">
					                                        <option>Atlanta Falcons</option>
					                                        <option>Carolina Panthers</option>
					                                        <option>New Orleans Saints</option>
					                                        <option>Tampa Bay Buccaneers</option>
					                                    </optgroup>
					                                    <optgroup label="NFC WEST">
					                                        <option>Arizona Cardinals</option>
					                                        <option>St. Louis Rams</option>
					                                        <option>San Francisco 49ers</option>
					                                        <option>Seattle Seahawks</option>
					                                    </optgroup>
					                                </select>
					                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">模板所属色系</label>
                                        <div class="col-md-6 col-sm-6" style="line-height: 30px; height: 25px;">
                                            <input type="hidden" name="colorId" id="colorId">
                                            <span class="label label-default">灰</span>
											<span class="label label-primary">蓝</span>
											<span class="label label-success">绿</span>
											<span class="label label-warning">黄</span>
											<span class="label label-danger">红</span>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset title="Billing Details">
                                    <legend>模板参数设置</legend>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">是否是推荐模板</label>
                                        <div class="col-md-6 col-sm-6">
                                              <div class="slide-toggle">
				                                <div>
				                                    <input type="checkbox" class="js-switch" value="1" name="isRecommended"/>
				                                </div>
				                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">模板大小单位(K)</label>
                                        <div class="col-md-6 col-sm-6">
                                            <input type="text"  name="templateSize" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">模板宽度</label>
                                        <div class="col-md-6 col-sm-6">
                                            <input type="hidden" name="templateStandardWidth" id="colorId">
                                            <span class="label label-default">500</span>
											<span class="label label-primary">780</span>
											<span class="label label-success">1024</span>
											<span class="label label-warning">1240</span>
											<span class="label label-danger">1980</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">模板高度</label>
                                        <div class="col-md-6 col-sm-6">
                                             <input type="text"  name="templateStandardHeight" class="form-control" value="auto">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">是否有视频</label>
                                        <div class="col-md-6 col-sm-6">
                                              <div class="slide-toggle">
				                                <div>
				                                    <input type="checkbox" name="isContainsVedio" value="1" class="js-switch" />
				                                </div>
				                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-2 control-label">模板缩略图</label>
                                        <div class="col-md-6 col-sm-6">
                                            <input type="file" name="templateThumbnailUrl">
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset title="Final Step">
                                    <legend>保存模板信息</legend>
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <p>您可以选择: <a>保存并完善模板信息</a> | <a>保存并管理模板</a></p>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            </section>



    </div>
    <!-- main content end-->
</section>

 <jsp:include page="/jsp/common/commonJs.jsp" />
 <!--multi-select-->
<script type="text/javascript" src="<%=rootPath %>/js/jquery-multi-select/js/jquery.multi-select.js"></script>
<script type="text/javascript" src="<%=rootPath %>/js/jquery-multi-select/js/jquery.quicksearch.js"></script>
<script src="<%=rootPath %>/js/multi-select-init.js"></script>
 <script src="<%=rootPath %>/js/jquery.stepy.js"></script>
 
<!--ios7-->
<script src="<%=rootPath %>/js/ios-switch/switchery.js" ></script>
<script src="<%=rootPath %>/js/ios-switch/ios-init.js" ></script>
 <script>
    /*=====STEPY WIZARD====*/
    $(function() {
        $('#default').stepy({
            backLabel: 'Previous',
            block: true,
            nextLabel: 'Next',
            titleClick: true,
            titleTarget: '.stepy-tab'
        });
    });
    /*=====STEPY WIZARD WITH VALIDATION====*/
    $(function() {
        $('#stepy_form').stepy({
            backLabel: 'Back',
            nextLabel: 'Next',
            errorImage: true,
            block: true,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#top_tabby',
            validate: true
        });
        $('#stepy_form').validate({
            errorPlacement: function(error, element) {
                $('#stepy_form div.stepy-error').append(error);
            },
            rules: {
                'name': 'required',
                'email': 'required'
            },
            messages: {
                'name': {
                    required: 'Name field is required!'
                },
                'email': {
                    required: 'Email field is requerid!'
                }
            }
        });
    });
</script>
</body>
</html>



