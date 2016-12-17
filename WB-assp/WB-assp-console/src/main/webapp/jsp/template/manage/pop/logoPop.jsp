<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>

<%
   	String rootPath = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8">
	<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
<%-- <script type="text/javascript" charset="utf-8" src="<%=rootPath%>/static/ueditor1_4_3_3/ueditor.parse.js"> </script>
 --%><script src="<%=rootPath%>/js/common/imageUtil.js" type="text/javascript"></script> 
</head>
<body >
<div class="logoPop">
	<div class="pop-head pop-tab-title">
		<a data-tab="03"   class="active">logo</a>
		<a data-tab="01"  >主标题</a>
		<a data-tab="02"  >副标题</a>
	</div><!--pop-head-->
	<div class="pop-body">
		<div class="popTabContent-item popTabContent-01 hide">
			<textarea id="fisrtTitle" name="fisrtTitle" style="width:100%;height:200px;"></textarea>
			
		</div><!--popTabContent-->
		<div class="popTabContent-item popTabContent-02 hide">
			<textarea id="secondTitle" name="secondTitle" style="width:100%;height:200px;"></textarea>
		</div>
		<div class="popTabContent-item popTabContent-03 ">
			<div class="pop-form">
				<div class="popForm-item">
					<label>LOGO：</label>
					<div class="label-con">
						<a href="javascript:;" class="btn dib btn-border-gray addImgBtn">添加图片</a>
					</div>
				</div>
				<div class="popForm-item">
					<label>图片：</label>
					<div class="label-con">
						<div class="img" id="imgDiv">
						</div>
						<div class="msg">
							注：建议图片尺寸<span id="cc"></span>，大小不超过1MB。
						</div>
					</div>
				</div>
			</div><!--pop-list-->
		</div>
	</div><!--pop-body-->
</div><!--logoPop-->
<script>
var top = window.parent;
var ue01,ue02;
var logoManage = {
		id: "logoFun",
		data_: {
			id: "logoFun",
			url: "<%=rootPath%>/template/modifyTempLateLogo.do",
			data: {} 
			},
		size: {
			preWidth: 0,
			preHeight: 0,
			width: 0,
			height: 0
		},
		//隐藏显示
		logoDisplayChange: function(obj){
			if(obj.value == 1){
				this.top.$(".logo-box").show();
				$("#logoFunForm #logoImgHide").val(0);
			}else{
				this.top.$(".logo-box").hide();
				$("#logoFunForm #logoImgHide").val(1);
			}
		},
		initialize: function(){
			var imgDiv = $("#imgDiv");
			
			this.data_.data.templateLogoId = top.$(".logo-box").attr("id");
			
			if (imgDiv.find("img").length == 0) {
				this.size.preWidth = top.$("#logoImg").width();
				this.size.preHeight = top.$("#logoImg").height();
				var size = Img.calcSize(this.size.preWidth, this.size.preHeight, 350, 100);
				var imgHtml = "<img src='"+ top.$("#logoImg").attr("src") +"' height='"+ size.height +"' width='"+ size.width +"' >";
				$(imgHtml).appendTo(imgDiv);
				$("#logoFunForm #logoImgUrl").val(top.$("#logoImg").attr("src"));
			}
			$("#cc").html(this.size.preWidth+'px*'+this.size.preHeight+'px');
			this.listenPicture();
			
			//加编辑器
			ue01 = UE.getEditor('fisrtTitle',{
				toolbars: [[
			   	             'bold', 'italic', 'underline',  '|', 'forecolor', 'fontfamily', 'fontsize'
			   	         ]],
		        autoHeightEnabled: true,//自动增高
		        lang:"zh-cn",
		        focus:false ,//初始化时，是否让编辑器获得焦点true或false
		        initialFrameWidth:'100%', //初始化编辑器宽度,默认1000
		        initialFrameHeight:'auto', //初始化编辑器高度,默认320
		        readonly : false ,//编辑器初始化结束后,编辑区域是否是只读的，默认是false
		        imagePopup:false   ,  //图片操作的浮层开关，默认打开
		        emotionLocalization:false, //是否开启表情本地化，默认关闭。若要开启请确保emotion文件夹下包含官网提供的images表情文件夹
		        wordCount:false
			});
			ue02 = UE.getEditor('secondTitle',{
				toolbars: [[
			   	             'bold', 'italic', 'underline',  '|', 'forecolor', 'fontfamily', 'fontsize'
			   	         ]],
		        autoHeightEnabled: true,//自动增高
		        lang:"zh-cn",
		        focus:false ,//初始化时，是否让编辑器获得焦点true或false
		        initialFrameWidth:'100%', //初始化编辑器宽度,默认1000
		        initialFrameHeight:'auto', //初始化编辑器高度,默认320
		        readonly : false ,//编辑器初始化结束后,编辑区域是否是只读的，默认是false
		        imagePopup:false   ,  //图片操作的浮层开关，默认打开
		        emotionLocalization:false, //是否开启表情本地化，默认关闭。若要开启请确保emotion文件夹下包含官网提供的images表情文件夹
		        wordCount:false
			});
			// 设置值和监听
			ue01.ready(function() {
			    // 设置编辑器的内容
			    ue01.setContent(top.$("#fisrtTitle").html());
			  	// 添加内容改变事件
				ue01.addListener( 'contentChange', function( editor ) {
					var content_ = ue01.getContent();
					// 去除p标
					if(content_.indexOf("<p>") == 0){
						content_ = content_.substring(3,content_.length-4);
					}
					/* $("#zhanshi").empty();
					$("#zhanshi").append(ue01.getContent()); */	
					top.$("#fisrtTitle").html(content_);
					logoManage.synData("fisrtTitle",content_);
				});
			});
			ue02.ready(function() {
			    // 设置编辑器的内容
			    ue02.setContent(top.$("#secondTitle").html());
			  	// 添加内容改变事件
				ue02.addListener( 'contentChange', function( editor ) {
					
					var content_ = ue02.getContent();
					// 去除p标签
					if(content_.indexOf("<p>") == 0){
						content_ = content_.substring(3,content_.length-4);
					}
					top.$("#secondTitle").html(content_);
					logoManage.synData("secondTitle",content_);
				});
			});
		},
		
		save: function(completeModuleIds){
			
		},
		listenPicture: function(){
			//添加图片按钮事件
			$(".addImgBtn").click(function(){
				
				top.layer.open({
					  type: 2, 
					  title: ['添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）', 'font-size:14px;'],
					  content: ['<%=rootPath%>/jsp/common/pop/uploadImg.jsp?singleupload=true&&chooseSingle=true', 'no'],
					 // skin: 'demo-class'
					  area: ['640px', '430px'],//定义大小
					  closeBtn: 1,//右上角关闭按钮样式
					  shade: 0.3, //遮罩为0表示不显示
					  shadeClose: true,//是否点击遮罩关闭
					  scrollbar: false,//是否允许滚动条出现
					  //按钮与回调
					  btn: ['确认', '取消'],
					  yes: function(index, layero){
						  logoManage.OK(index, layero);
					  }
					});
				
			})
		},
		synData: function(name,zhi){
			if(name != void 0 && zhi != void 0){
				this.data_.data[name] = zhi;
			}
			top.SiteManager.addModuleData(this.data_);
		},
		OK: function (index, layero){
			
			var body = top.layer.getChildFrame('body', index);
			var pUrls=body.find("input[id='pictureUrls']").val();
			var pNames=body.find("input[id='pictureNames']").val();
			top.layer.close(index);
			if(pUrls != "" && pUrls != null){
				//发送请求存储图片
				 var imgDiv = $("#imgDiv");
				 imgDiv.empty();
				 var pUrl = pUrls.split(",")[0];
				 var imgHtml = "<img src='"+ pUrl +"' >";
				 imgDiv.append(imgHtml);
				 var img = imgDiv.find("img");
				 //shouye
				 var size1 = Img.calcSize(img.width(), img.height(), this.size.preWidth, logoManage.size.preHeight);
				 //bianjiye
				 var size2 = Img.calcSize(img.width(), img.height(), 350, 100);
				 img.width(size2.width);
				 img.height(size2.height);
				 var logoImg =  top.$("#logoImg");
				 logoImg.attr("src",pUrl);
				 logoManage.synData("logoImgUrl",pUrl);
		  }
		}
		
	}

	$(document).ready(function(){
		tab01();
		logoManage.initialize();
	});
	function tab01 () {
	var tabItem = $('.pop-tab-title a');
		
	tabItem.on('click',function(){
		var $this = $(this);
		var val = $this.attr('data-tab');
		
		$this.addClass('active').siblings().removeClass('active');
		$('.popTabContent-'+val).removeClass('hide').siblings().addClass('hide');
	});
}
</script>
</body>
</html>
