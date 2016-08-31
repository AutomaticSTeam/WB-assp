/**
 * @author lin
 */

var chooseSingle=false;
$(function(){
		//加载css
		$("<link>")
	    .attr({ rel: "stylesheet",
	        type: "text/css",
	        href: rootPath+"/css/progressbar.css"
	    })
	    .appendTo("head");
		
		//获取是否使用单文件上传参数
		var singleupload=getUrlParam("singleupload");
		//未传参数默认是false;
		if(singleupload==""||singleupload==null||singleupload=="false"){
			multi_selection=true;
		}
		if(singleupload=="true"){
			multi_selection=false;
		}
		
		//获取是否使用仅选择单文件上传
		chooseSingle=getUrlParam("chooseSingle");
		//未传参数默认是false;
		if(chooseSingle==""||chooseSingle==null||chooseSingle=="false"){
			chooseSingle=false;
		}
		/*var templateCode=$("#templateCode").val();*/
		var siteCode=$("#siteCode").val();
		//文件上传
		var uploader  = new plupload.Uploader({
			runtimes : 'html5,flash,silverlight,html4',  //官网上默认是gears,html5,flash,silverlight,browserplus
			browse_button : 'uploadFile', //这个是点击上传的html标签的id,可以a,button等等
			container: document.getElementById('popContent-head'), //这个是容器的地址，
			max_file_size : '10mb', //这是文件的最大上传大小
			multi_selection:multi_selection, //是否开启多文件上传
			multiple_queues:false,//是否可以多次上传jquery部件属性
			flash_swf_url : rootPath+'/static/plupload-2.1.9/js/Moxie.swf',
			/*url : rootPath+'/fileUpLoad/upLoad.do?templateCode='+templateCode+'&&dirFolderName=images' , *///服务器页面地址，后面跟你想传递的参数
			url : rootPath+'/fileUpLoad/upLoad.do?siteCode='+siteCode+'&&dirFolderName=images' ,
			filters : [ {title : "图片文件", extensions : "jpg,jpeg,gif,png"} ], //文件过滤
			
		
		init: {
			PostInit: function() {
				//document.getElementById('filelist').innerHTML = '';
			},
			//文件添加时，会在容器里显示待上传的文件列表
			FilesAdded:function(up, files) {
				uploader.start();
			}, 

			//文件上传的进度
			UploadProgress: function(up, file) {
				
				//上传进度条显示
				$("<div class='progress' id='progress'>"
						+"<div class='progress-head'>正在上传</div>"
						+"<div class='progress-body'>"
						+"<div class='progress-bar'>"
						+"		<div class='progress-background' style='width:0%'></div>"
						+"	</div>"
						+"	<div class='progress-num'>0%</div>"
						+"</div>"
						+"  <div class='progress-foot'>"
						+"	<div class='progress-txt fl'>文件大小：0KB</div>"
						+"	<div class='progress-cancel fr'><a href='javascript:;'>取消</a></div>"
						+"</div>"
						+"</div>"
				).appendTo('body');
				
				 $(".progress").show();
				 
				//设置文件大小
					if (file) {
				    	  //设置文件大小的显示
				          var fileSize = 0;
				          if (file.size > 1024 * 1024){
				        	  fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
				          }else{
				        	  fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
				          }
				          $(".progress-txt").html("文件大小："+fileSize);
				    }
					
				 //设置上传进度
				if(file.percent<100){
					$(".progress-background").css({
		        		"width":file.percent+"%",
		        	})
		           $(".progress-num").html(file.percent+"%");
				};
			},

			//文件上传成功后的总计
			UploadComplete:function(up, files){
				$(".progress-background").css({
	        		"width":"100%",
	        	})
	           $(".progress-num").html("100%");
				
				$(".progress-head").html("上传成功");
				setTimeout(function () {
					$(".progress").remove();
			    }, 500);
			},
			//接收返回值
			FileUploaded:function(up,file,result){
				var data_json=result.response;
				var data = JSON.parse(data_json); 
				
				var setLiParameter = {
						data:data,
						position:".popImgList"
						}
			setPicture(setLiParameter);
				
			var fileNum=$(".popImgList li").length/2;
	    	if(chooseSingle){
	    		if(fileNum>0){
	  	    	  $(".uploadFile").css("visibility","hidden");
	  			};
			}	
				
			},
			Error: function(up, err) {
				if(err.code=='-601'){
					alert("上传图片只能添加jpg、jpeg、gif、png格式文件");
				}else if(err.code=='-600'){
					alert("文件大小超过限制，免费版不错过1M");
				}else{
					alert("上传失败，错误代码："+err.code + "   错误信息: " + err.message+"请重试。。。");
				}
			}
		}
		});
		
		/*if(templateCode!=""&&templateCode!=null){
			uploader.init();
		}*/
		if(siteCode != "" && siteCode != null){
			uploader.init();
		}
	
})

//在页面放置图片预览
function setPicture(parameter){
	//隐藏提示语
	$(".popContent-body .msg").hide();
	//上传服务器ip
	var data=parameter.data;
		//添加图片
		$("<li class='page-"+new Date().getMilliseconds()+"'>"
				+"<div class='img'>"
					+"<img src='"+data.uploadRst[0].fileServerPath+"' alt='未命名.jpg' title='"+data.uploadRst[0].oldName+"' />"
					+"<div class='dui-img'><p></p></div>"
					+"<input type='hidden' class='pictureUrl' />"
					+"<div class='del-img '><a class='page-"+new Date().getMilliseconds()+"' href='javascript:;' ></a></div>"
					+"</div>"
				+"<div class='tit editor'><div class='p'>"+data.uploadRst[0].oldName+"</div></div>"
		+"</li>").appendTo(parameter.position);
		
		//存储图片url
		var pUrls=$("#pictureUrls").val();
	    $("#pictureUrls").val(pUrls+data.uploadRst[0].fileServerPath+",");
	    //存储当前url 
	     $(".pictureUrl").val(data.uploadRst[0].fileServerPath);
	      
	    //存储图片原名字
        var pName=$("#pictureNames").val(); 
        $("#pictureNames").val(pName+data.uploadRst[0].oldName+",");
		 
        //判断上传文件是否大于文本框承载极限，超出时出现滚动条，调整宽度是页面更和谐
		var fileNum=$(".popImgList li").length/2;
		if(fileNum>6){
			$(".popUpload .upload-img").width("377px");
		}
		
		//动态改变文件信息
		$(".select-img .popContent-head").html("待添加的文件（"+fileNum+"）")
		
		//右下角删除图标功能实现
		$(".del-img a").click(function(){
			
			//页面效果
			var aClass= $(this).attr("class");
			$("."+aClass).remove();
			
			var allUrl=$("#_popImgList li div input[type='hidden']");
			var picUrlNum=allUrl.length;
			var arrayUrl=new  Array(picUrlNum);
			
			allUrl.each(function(index,element){
				arrayUrl[index]=$(this).val();
			})
			var allUrl_String=arrayUrl.join(",");
			$("#pictureUrls").val(allUrl_String);
			
			var fileNum=$(".popImgList li").length/2;
	    	if(fileNum==0){
				$(".uploadFile").css("visibility","");
			}
		})
		
		$("#image_url").val("");
}

/** 
 * 获取当前URL参数值 
 * @param name  参数名称 
 * @return  参数值 
 */  
function getUrlParam(name) {  
       var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");  
       var r = window.location.search.substr(1).match(reg);  
       if(r!=null){
    	  return unescape(r[2]);   
       }else{
    	  return null;  
       }  
} 