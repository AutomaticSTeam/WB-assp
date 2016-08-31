/**
 * @author lin
 */
var _ablumId;
var siteAblumId;

var siteModuleId = (typeof moduleId != "undefined")?moduleId:"";
var top = window.parent;
//创建图片存储容器
var picList = new Array();
//创建已删除图片容器
var picList_del = new Array();
$(function() {
	
	//初始化页面
	pageFun.initPage();
	
	/**
	 * ***************************绑定事件开始*************************************
	 */
	//保存
	$("#save_btn_pic").bind("click",function(){
		if((typeof _ablumId != "undefined") && _ablumId != null && _ablumId !="" && _ablumId !=''){
				albumManager.saveAblum();
		}
	});
	
	//取消（其他）
	$("#cancel_btn_pics").click(function(){
		top.layer.closeAll();
	});
	/**wangkang 添加**/
	//取消
	$("#cancel_btn_pic").bind("click",function(){
		if(_ablumId!=""&&_ablumId!=null){
			layer.confirm('您确定放弃保存当前数据？', {icon: 3, title:'提示'}, function(index){
				location.href=rootPath+"/jsp/webconsole/album/albumManage.jsp";
			});
		}else{
			picDataManager.clearPicContent();
			picDataManager.clearAblumContent();
			$("#pic_content").hide();
		}
	});
	
	//添加图片按钮事件
	$(".addImgBtn").click(function(){
		
		layer.open({
			  type: 2, 
			  title: ['添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）', 'font-size:14px;'],
			  content: [rootPath+'/jsp/common/pop/uploadImg.jsp?singleupload=false&&chooseSingle=false', 'no'],
			 // skin: 'demo-class'
			  area: ['640px', '430px'],//定义大小
			  closeBtn:1,//右上角关闭按钮样式
			  shade: 0.3, //遮罩为0表示不显示
			  shadeClose :true,//是否点击遮罩关闭
			  scrollbar:false,//是否允许滚动条出现
			  //按钮与回调
			  btn: ['确认', '取消'],
			  yes: function(index, layero){
				  	OK(index, layero);
			  	   }
			});
	})
	
	//图册名称为空时解除绑定的保存事件
	$("#albumName").change(function(){
		var albumName=$("#albumName").val();
		if(albumName!=""){
			$('#save_btn_pic').unbind("click");
			//保存
			$("#save_btn_pic").bind("click",function(){
				if(typeof siteAblumId != "undefined" && siteAblumId != null && siteAblumId !="" && siteAblumId !=''){
					albumManager.saveAblums();
				}else{
					albumManager.saveAblum();
				}
				
			})
		}else{
			 $('#save_btn_pic').unbind("click");
		}
	})
	/**
	 * ***************************绑定事件结束*************************************
	 */
	
	
	/**
	 * 编辑回显
	 */
	if((_ablumId!=""&&_ablumId!=null)||(siteAblumId!=""&&siteAblumId!=null)){
		$(".column-tit .tit").html("编辑图册");
		//回显
		$.ajax({
			url:rootPath+"/contents/album/queryPicsByAlbum.do",
			data:{albumId:(_ablumId!=""&&_ablumId!=null)?_ablumId:siteAblumId},
			dataType:"json",
			type:"post",
			success:function(data){
				var albumName=data.albumName;
				if(albumName!=""){
					$("#albumName").val(albumName);
					picList=data.pictures;
					albumManager.handleData(picList);
				}
			},
			error:function(){
			}
		})
	}
	
	
	
	//实例化uEditor
	var ue=ueManager.initUe('editor');
	
	//表单验证
	$(".albumForm").Validform({
		tiptype:3
		//添加提交按钮会造成按钮点击时自动submit，页面会刷新
	});
	
	//验证ueditor
	ue.addListener("blur", function (type, event) {
		$("#ueditor_Valid").show();
    });
	
	
	
});
//图册操作
var albumManager = {
		//处理数据
		handleData : function(_picList){
		    if(_picList.length>0){
		    	//编辑或创建
	    		var pageData={
	    				picVos : _picList
	    			}
    			var picLi=template('picLis', pageData);
    			$(".popImgList").append(picLi);
    			
    			
		    }
		    //绑定左右移动按钮事件
		    $(".move-left").click(function() {
		    	var li=$(this).parent().parent().parent();
				if(li.prev()){
					//交换存储容器中的位置
					var index=li.index();
					picList=pageFun.swapArrayItem(picList,index-1,index);
					if(index==$("#nowLi").val()){
						$("#nowLi").val(index-1);
					}
					//交换显示位置
					li.prev().before(li);
				}
			});
		    $(".move-right").click(function() {
		    	var li=$(this).parent().parent().parent();
				if(li.next()){
					//交换存储容器中的位置
					var index=li.index();
					picList=pageFun.swapArrayItem(picList,index,index+1);
					if(index==$("#nowLi").val()){
						$("#nowLi").val(index+1);
					}
					//交换显示位置
					li.next().after(li);
				}
			});
		    //绑定删除按钮事件
		    $(".del-img a").click(function() {
		    	var nowLi=$(this).parent().parent().parent();
		    	
		    	var nowLiINdex=nowLi.index();
		    	var showLiIndex=$("#nowLi").val();
		    	//移除存储容器中的实例
		    	var pic_del=picList[nowLiINdex];
		    	pic_del.dataStatus=1;
		    	picList_del.push(pic_del);
		    	picList.splice(nowLiINdex,1);
				nowLi.remove();
				if(picList.length==0){
					picDataManager.clearPicContent();
					//隐藏详情页面
					$("#pic_content").hide();
				}else{
					if(nowLiINdex==showLiIndex){
						if(nowLiINdex==0){
							$("#nowLi").val(0);
							picDataManager.showPicContent(0);
							
						}else{
							var _showLiIndex=showLiIndex-1;
							$("#nowLi").val(_showLiIndex);
							picDataManager.showPicContent(_showLiIndex);
						}
		        	}
				}
				
			})
		    //绑定点击图片事件
		    $(".popImgList li").click(function(e) { 
		    	if (($(e.target).is('.move-img')||$(e.target).is('.del-img'))){
		    		//或去点击是的当前内容所属的li 
		    		var lastLi=$("#nowLi").val();
		    		var nextLi=$(this).index();
		    		if(lastLi!=''&&lastLi != null){
		    			picDataManager.savePicContent(lastLi);
		    		}
		    		//跳转点击对象的li并更新表单内容  
		    		picDataManager.showPicContent(nextLi);
			    	//存储当前值
			    	$("#nowLi").val(nextLi);
			    	$("#pic_content").show();
				}
			});
		    
		    //悬浮效果事件
		    $(".popImgList li").hover(function(){
				$(this).find("a").show();
				$(this).find(".del-img").addClass("blueBorder");
			},function(){
				$(this).find(".del-img").removeClass("blueBorder");
				$(this).find("a").hide();
			});
		   
		},
		//保存图册信息
		saveAblum : function(){
			//获取选中的分类
			var relTypeIDS = '';
			$("input[name='albumTypeId']:checked").each(function(){
				relTypeIDS += ',' + this.value;
			});
			if(relTypeIDS!="")
				relTypeIDS = relTypeIDS.substring(1);
			var albumName=$("#albumName").val();
			if(albumName!=null&&albumName!=""){
				 if(picList.length>0){
					//保存前存储当前li的内容
					picDataManager.savePicContent($("#nowLi").val());
					//判断所有图片名称是否为空
					for (var i = 0; i < picList.length; i++) {
						//创建相册后给个相册id赋值
						var nowLi=$(".popImgList li").eq(i);
						//判断每个图片的名称是否为空
						var _pnameCheck=picList[i].pictureName;
						if(_pnameCheck==""){
							layer.alert("图片名称不能为空，请检查您的内容");
							return;
						}
					}
				 }
				//创建或者编辑相册
				$.ajax({
					url:rootPath+"/contents/album/addOrEditAlbum.do",
					data:{albumName:$("#albumName").val(),
						  albumId:((typeof _ablumId=="undefined")||_ablumId=="")?"":_ablumId,
						  relTypeIDS:relTypeIDS},
					type:"post",
					dataType:"json",
					success:function(data){
						var ablumId=data.albumId;
						if(ablumId!=""){
							if(picList.length>0||picList_del.length>0){
								//调用方法保存图片
								if(picDataManager.updataPics(ablumId)){
									layer.alert("保存成功。。。",function(){
										if(siteModuleId!=null&&siteModuleId!=""){
											var module={
												moduleId:siteModuleId
											}
											parent.Template.reloadModule(module);
											parent.layer.closeAll();
										}else{
											location.href=rootPath+"/jsp/webconsole/album/albumManage.jsp";
											parent.changeLeftMenuCss(5);
										}
									});
								}
							}
						}
					},
					error:function(){
						alert("操作失败");
					}
				})
				}else{
					layer.alert("图册名称不能为空");
				}
		
		},
		
		//选中关联类型
		selectedAblumType : function(id){
			 $.ajax({
					type:'post',
					 url:rootPath+'/contents/album/queryAlbumTypeRels.do',
					data:{
						pictureAlbumId : id 
					},
					dataType:'json',
					async : true,
					success: function(data){ 
						$data = data.datas;
						$($data).each(function(){
							var albumObj = this;
							$("input[name='albumTypeId']").each(function(){
								 if(this.value == albumObj.albumTypeId){
									 $(this).attr('checked',true);
								 }
							 });
						 });
					 },
					error:function(data){
						//alert(JSON.stringify(data));
					}
			     });
		},

		//页面中添加图册时，所携带的moduleIDID
		 selectedAddPictureAlbumType : function(index){
			  $.ajax({
					url:rootPath+'/contents/album/queryAlbumTypes.do',
					data:{dataStatus : 0},
					dataType:'json',
					async : true,
					success: function(data){
						$data =  data.albumTypes; 
						 var resultHtml ="";
				    	   $.each($data,function(i,pictureAlbumType){
				    		   resultHtml +='<span class="checkbox">'
										  +'<input type="checkbox" name="albumTypeId" value="'+pictureAlbumType.albumTypeId+'">'+pictureAlbumType.albumTypeName+'</span>';
				    	   });
						$("#queryAlbumCKBoxType").html(resultHtml);
						//若存在id，则取datacolumnId
						if(index != null && index != "null" && index != ''){
							$.ajax({
					  			type:'post',
					  			 url:rootPath+'/site/selectDateColumnId.do',
					  			data:{
					  				moduleId : index 
					  			},
					  			dataType:'json',
					  			async : true,
					  			success: function(data){ 
					  				$data = data.templateModuleRelContents;
					  				$.each($data ,function(i,templateModuleRelContents){
					  				$("input[name='albumTypeId']").each(function(){
					  						 if($(this).val()  == templateModuleRelContents.dataColumnId){
					  							 $(this).attr('checked',true);
					  						 }
					  					 });
					  				 });
					  			 },
					  			error:function(data){
					  			}
					  	     });
						 }
					}
					
					});
			
		}
}
//图片数据操作
var picDataManager = {
		//存储图片对应内容
		savePicContent : function(liNum){
			if(liNum!=""&&typeof(liNum) != "undefined"){
				liNum = parseInt(liNum);
		        //存储当前内容
		        var picVo=picList[liNum];
		        picVo.pictureName = $("#pitureName").val();
		        picVo.briefDesc = $("#pBriefDesc").val();
		        picVo.detailDesc = ueManager.getContent();
		        picVo.hyperlink = $("#pUrl").val();
			}
		},
		//清除显示内容
		clearPicContent : function (){
			$("#pitureName").val("");
			$("#pBriefDesc").val("");
			$("#pDetailDesc").val("");
			$("#pUrl").val("");
			ueManager.clearContent();
		},
		//显示图片对应的内容
		showPicContent : function (liNum){
			if(typeof(liNum) != "undefined"){
				var nextObjectLi=$(".popImgList li").eq(liNum);
				//填充内容前清空内容
				picDataManager.clearPicContent();
				//填充
				var picVo=picList[liNum];
				$("#pitureName").val(picVo.pictureName);                          
				$("#pUrl").val(picVo.hyperlink);
				$("#pBriefDesc").val(picVo.briefDesc);
				var detailDesc=picList[liNum].detailDesc;
				//防止填充值为null
				ueManager.setContent((detailDesc==""||detailDesc==null)?"":detailDesc);
				//修改显示样式
				$(".popImgList .del-img").css("border","2px solid #c2c2c2");
				$(".popImgList li").eq(liNum).find(".del-img").css("border","2px solid #297cd0");
			}
		},
		//清空图册名与预览图
		clearAblumContent:function (){
			$("#albumName").val("");
			$(".popImgList li").remove();
		},
		//保存图片
		updataPics : function (ablumId){
			if(ablumId!=null&&ablumId!=""){
				//给每个实例插入图册id
				for (var i = 0; i < picList.length; i++) {
					picList[i].pictureAlbumId=ablumId;
					picList[i].pictureOrder=i;
				}
				//存入已删除的图片
				picList=picList.concat(picList_del);
				$.ajax({ 
					url:rootPath+"/contents/picture/updatePictureResult.do?saveClient="+((typeof saveClient!="undefined")? 1 : 0),
					data:{picListJson:JSON.stringify(picList)},
					traditional:true,
					type:"post",
					dataType:"json",
					success:function(data){
						
					},
					error:function(data){
						//alert(JSON.stringify(data));
						layer.alert("更新图片失败");
					}
				})
				return true;
			}else{
				layer.alert("操作失败");
				return false;
			}
		},
		//图片样式操作
		changeCss:function(nowLi){
			console.log(nowLi);
			$(".popImgList .del-img").css("border","2px solid #c2c2c2");
			$(".popImgList .del-img").eq(nowLi).css("border","2px solid #297cd0");
		}
		
}
//ueditor操作
var ueManager={
		ue:null,
		initUe:function(){
			ueManager.ue = UE.getEditor("editor",{
			   	 toolbars: [[
			   	             'fullscreen', 'source', '|', 'undo', 'redo', '|',
			   	             'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
			   	             'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
			   	             'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
			   	             'directionalityltr', 'directionalityrtl', 'indent', '|',
			   	             'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
			   	             'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
			   	             'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
			   	             'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
			   	             'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
			   	             'print', 'preview', 'searchreplace', 'drafts', 'help'
			   	         ]],
			   	           autoHeightEnabled: false,//自动增高
			   	           lang:"zh-cn",
			   	           focus:false ,//初始化时，是否让编辑器获得焦点true或false
			   	           initialFrameWidth:"100%", //初始化编辑器宽度,默认1000
			   	           initialFrameHeight:300 , //初始化编辑器高度,默认320
			   	           readonly : false ,//编辑器初始化结束后,编辑区域是否是只读的，默认是false
			   	           autoFloatEnabled:false,//是否固定工具栏位置（默认为true，可能引起页面下拉到底调动）
			   	           imagePopup:false    //图片操作的浮层开关，默认打开
			   	       });
			return ueManager.ue;
		},
		setContent:function(html){
			ueManager.ue.setContent(html,false);
		},
		clearContent:function(){
			ueManager.ue.execCommand('cleardoc');
		},
		getContent:function(){
			return ueManager.ue.getContent();
		}
}

var pageFun = {
	//初始化页面参数
	initPage:function(){
		_ablumId=pageFun.getUrlParam("ablumId");
		siteAblumId=pageFun.getUrlParam("siteAblumId");
		
		if(typeof siteAblumId != "undefined" && siteAblumId != null && siteAblumId !="" && siteAblumId !=''){
			_ablumId=pageFun.getUrlParam("siteAblumId");
		}
		
		//初始化分类
		AlbumTypeQueryAll.drawAlbumTypesDataView(1);
		if(_ablumId!=""&&_ablumId!=null){
			albumManager.selectedAblumType(_ablumId);
		}
		
		/**wangkang 添加**/
		//页面中添加图册时 分类
		if(typeof moduleId != "undefined" && moduleId != null && moduleId !="" && moduleId !=''){
			albumManager.selectedAddPictureAlbumType(moduleId);
			}
		//页面中编辑时分类
		if(typeof siteModuleId != "undefined" && siteModuleId != null && siteModuleId !="" && siteModuleId !=''){
			albumManager.selectedAddPictureAlbumType(siteModuleId);
			}
	},
	getUrlParam : function (name) {  
       var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");  
       var r = window.location.search.substr(1).match(reg);  
       if(r!=null){
    	  return unescape(r[2]);   
       }else{
    	  return null;  
       }  
	},
	swapArrayItem : function(attr,index1,index2){
		//获取待移动元素
		var vo_index=attr[index1];
		//从数组中删除待移动元素
		attr.splice(index1,1);
		//将带移动元素插入指定位置
		attr.splice(index2,0,vo_index);
		return attr;
	}
	
}

function OK(index, layero){
	var body = layer.getChildFrame('body', index);
	var pUrls=body.find("input[id='pictureUrls']").val();
	var pNames=body.find("input[id='pictureNames']").val();
	layer.close(index);
	if(pUrls!=""&&pUrls!=null&&pNames!=""&&pNames!=null){
		pUrls=pUrls.substring(0,pUrls.length-1);
		pNames=pNames.substring(0,pNames.length-1);
		
		//发送后台整理封装数据
		$.ajax({  
		    url:rootPath+'/contents/picture/handlePicture.do',  
		    data:{  
		    	pUrls : pUrls,
		    	pNames:pNames
		    },  
		    type:'post',  
		    dataType:'json',  
		    success:function(data){
		    	if(data.length > 0){
		    		//将新内容合并进存储容器
		    		$.merge(picList,data);
		    		//将新内容显示在页面上
		    		albumManager.handleData(data);
		    	}
		    },  
		    error : function() {  
		         layer.alert("图片保存失败，请重试。。。")
		    }  
		});
	}
  }
  