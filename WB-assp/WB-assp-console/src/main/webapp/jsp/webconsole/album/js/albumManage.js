/**
 * @author lin
 */
$(function() {
	
	queryAlbumAll();
	AlbumTypeQueryAll.drawAlbumTypesDataView(0);
	//取消按钮事件
	$("#ablumCancel").click(function(){
				layer.close(index);
				window.location.reload();//刷新当前页面.
	})
	
	//创建相册按钮
	$("#creatAblum").click(function(){
		location.href=rootPath+"/jsp/webconsole/album/createOrEditAlbum.jsp";
		parent.changeLeftMenuCss(2);
	})
	
	/**模糊查询**/
	//失去焦点重新加载查询数据(图层搜索)
	$('#albumName').on('blur',function(){
		queryAlbumAll();
	});
	//失去焦点重新加载查询数据(分类)
	 $('#albumTypeId').on('change', function(){
		 queryAlbumAll();
	 }); 
});

//定义表示确定页面是否有未保存的操作
var operationToSave=false;
//定义数组存储待删除项
var delIdsArray=new Array();

//删除按钮事件
function delAblum(ablumId){
	
	$.ajax({
		url:rootPath+"/contents/album/queryPicByAlbumId.do",
		type:"post",
		datatype:"json",
		data:{albumId:ablumId},
		success:function(data){
			if(data.length==0){
				layer.confirm('当前相册没有图片是否直接删除', {icon: 3, title:'提示'}, function(index){
					delPic(ablumId);
					layer.close(index);
				});
			}else{
				layer.confirm("当前相册有"+data.length+"张图片,是否删除", {icon: 3, title:'提示'}, function(index){
					delPic(ablumId);
					layer.close(index);
				});
			}
		},
		error:function(){
			layer.confirm('查询图片信息失败是否直接删除图册', {icon: 3, title:'提示'}, function(index){
				delPic(ablumId);
				layer.close(index);
			});
		}
  })
	
}


function showAblum(ablumId){
	//想页面加载预览图
	$.ajax({
		url:rootPath+"/contents/album/queryPicByAlbumId.do",
		type:"post",
		datatype:"json",
		data:{albumId:ablumId},
		success:function(data){
			if(data.length==0){
				layer.alert("该相册未添加任何图片")
			}else{
				$(".popImgs").empty();
				for (var i = 0; i < data.length; i++) {
					var picImg="<img  layer-src='"+data[i].pictureUrl+"' src='"+data[i].pictureUrl+"'style='float:left;max-width:235px;margin: 2px;border: 2px solid #eae6e6;'>";
					$(".popImgs").append(picImg);
				};
				layer.open({
					type: 1,
					title: '相册预览',
					content: $(".pic-show"),
					shadeClose:true,
					area: ["500px","500px"]
				});
			}
		},
		error:function(){
			layer.alert("预览失败，请重试。。。");
		}
  })
}
function preview(picId){
	var imageSrc=$(this).attr("class");
	alert(imageSrc);
	
}
function editAblum(ablumId){
	location.href=rootPath+"/jsp/webconsole/album/createOrEditAlbum.jsp?ablumId="+ablumId;
}
//动态删除掉相关分类
function delArticleTypeRel(ablumTypeId,ablumId){
	layer.confirm('确定删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			url:rootPath+"/contents/album/delRelPic.do",
			type:"post",
			datatype:"text",
			data:{albumId:ablumId,ablumTypeId:ablumTypeId},
			success:function(data){
				if(data=='success'){
					$("#r"+ablumTypeId).remove();
				}else{
					layer.alert("操作失败，请重试。。。");
				}
			},
			error:function(){
			}
	  })
		layer.close(index);
	});
}

function examinationAblum(ablumId){
	if(ablumId!=""){
		$.ajax({
			url:rootPath+"/contents/album/queryPicByAlbumIdToReview.do",
			type:"post",
			datatype:"json",
			data:{albumId:ablumId},
			success:function(data){
				if(data.length>0){
					$("#picList li").remove();
			    		for (var i = 0; i < data.length; i++) {
			    			var picName=data[i].pictureName;
			    			var pId=data[i].pictureId;
			    			var Postfix=data[i].imgPostfix;
			    			var briefDesc=data[i].briefDesc;
			    			var detailDesc=data[i].detailDesc;
			    			var pictureUrl=data[i].pictureUrl;
			    			var pictureAlbumId=data[i].pictureAlbumId;
			    			var pictureType=data[i].pictureType;
			    			
			    			var picListDiv= " <li id='"+pId+"'>\n" +
							"    <div class='left_pic'><img src='"+pictureUrl+"'/></div>\n" +
							"    <div class='right_in' id=''><b>"+picName+"</b>\n" +
							"			<br>"+briefDesc+"  \n" +
							"			<br><a name='"+pId+"' onclick='getDetailDesc(this.name)'>点击查看详细描述</a>\n" +
							"	    <input  type='button' name='"+pId+"' class='btn_green' value='审核通过' onclick='examinationForsingle(this.name)'/>\n" +
							"    </div>\n" +
							"    <div class='cb'></div>\n" +
							"</li>";
			    			$("#picList").append(picListDiv);
			    		}
			    		layer.open({
			    			title:"图册审核",
			    			closeBtn:1,
			    			type:1,
			    			content:$("#review"),
			    			area: ['640px', 'auto'],
			    			btn: ['全部通过', '关闭'],
			    			yes: function(index, layero){
			    				allExamination(index, layero);
			    			  	   },
			    			end:function(){
			    				var liNUm=$("#picList li").length;
			    				if(liNUm==0){
			    					$("#"+ablumId+" a").eq(3).html("【已审】");
			    				}
			    			}
			    		})		
				}else{
					layer.alert("没有需要审核的图片")
				}
			}
		})
	}
	
}
//获取相关分类
function getRelType($data,id){
	return $.map($data,function(obj){
		return '<span class="articlesClass" id="r'+obj.albumTypeId+'" >'+obj.albumTypeName+'<span class="del" onclick="delArticleTypeRel('+obj.albumTypeId+','+id+');" >×</span></span>';
	}).join("");
}

function queryAlbumAll(){
	/*var albumName = $("#albumName").val();
	var albumTypeId = $("#albumTypeId").val();*/
	$.ajax({
		type:"post",
		url:rootPath+"/contents/album/queryAlbumAll.do",
		data:$("#albumForm").serialize(),
		datatype:"json",
		async : true,
		success:function(result){
			result = JSON.parse(result);
			var data = result.rows;
			$("#ablumTable").find("tr:gt(0)").remove();
			for (var i = 0; i < data.length; i++) {
				if(data[i].review_is>0){
					var div="<tr id="+data[i].albumId+" >\n" +
					"	<td class='tl tit pl40'>"+data[i].albumName+"</td>\n" +
					"	<td class='tl tit pl40'>"+getRelType(data[i].pictureAlbumTypes,data[i].albumId)+"</td>\n" +
					"	<td><a class='btn-editor' name="+data[i].albumId+" onclick='editAblum(this.name)'>【编辑】</a>" +
					"		<a class='delBtn' name="+data[i].albumId+" onclick='delAblum(this.name)'>【删除】</a>" +
					"		<a class='showBtn' name="+data[i].albumId+" onclick='showAblum(this.name)'>【图片预览】</a>" ;
					if(canReview == 1){
						 div += "		<a class='examinaBtn' name="+data[i].albumId+" onclick='examinationAblum(this.name)'>【审核】</a>" ;
					}
					div +="	</td>\n" +
					
					
					"</tr>";
					$("#ablumTable").append(div);
				}else{
					var div="<tr id="+data[i].albumId+">\n" +
					"	<td class='tl tit pl40'>"+data[i].albumName+"</td>\n" +
					"	<td class='tl tit pl40'>"+getRelType(data[i].pictureAlbumTypes,data[i].albumId)+"</td>\n" +
					"	<td><a class='btn-editor' name="+data[i].albumId+" onclick='editAblum(this.name)'>【编辑】</a>" +
					"		<a class='delBtn' name="+data[i].albumId+" onclick='delAblum(this.name)'>【删除】</a>" +
					"		<a class='showBtn' name="+data[i].albumId+" onclick='showAblum(this.name)'>【图片预览】</a>" ;
					if(canReview == 1){
						div += "		<a class='examinaBtn' name="+data[i].albumId+" onclick='examinationAblum(this.name)'>【已审】</a>" ;
					}
					div += "	</td>\n" +
					"</tr>";
					$("#ablumTable").append(div);
				}
			}
			
			//添加鼠标虚浮效果
			$("#ablumTable tr").hover(function(){
				$(this).css(
					"background-color","#edf0f5"
				)
			},function(){
				$(this).css(
					"background-color","#fff"
				)
			})
			var currentPage = result.currentPage;
			var total = result.total;
			var pageCount =result.pages;
			//ArticleTypeQueryAll.initDataGridContentFun();//设置表格内js效果
			drawPage(currentPage,pageCount);
		},
		error:function(){
		}
	})
};

var drawPage = function(pagenumber,pageCount){
	var data = {'pagecount':pageCount};
	$("#pager").pager({pagenumber: pagenumber, pagecount: data.pagecount,  buttonClickCallback: pageClickCallback});
}

//回调函数
pageClickCallback = function(pageclickednumber){
	$("#currentPage").val(pageclickednumber);
	queryAlbumAll();
};

function allExamination(){
	var allliNum=$("#picList").children("li").length;
	if(allliNum>0){
		for (var i = 0; i < allliNum; i++) {
			var success=examinationForsingle($("#picList li").eq(i).attr("id"));
		}
	}
}
function examinationForsingle(picId){
	$.ajax({
		url:rootPath+"/contents/picture/review_yesById.do",
		type:"post",
		datatype:"json",
		data:{picId:picId},
		success:function(data){
			if(data>0){
				//移除已操作的li
				$("#picList #"+picId).remove();
			}
		},
		error:function(){
		}
	})
}

function getDetailDesc(picId){
	
	$.ajax({
		url:rootPath+"/contents/picture/queryPicById.do",
		type:"post",
		datatype:"json",
		data:{picId:picId},
		success:function(data){
			var detailDesc=data.detailDesc;
			if(detailDesc==""){
				detailDesc="(详情内容为空)";
			}
			layer.open({
				type:1,
				title:"详情描述",
    			content:detailDesc,
    			area: '400px',
    			btn: ['确定'],
    			yes: function(index, layero){
				  	layer.close(index);
			  	   }
			})
		},
		error:function(){
			layer.alert("获取详情信息失败,请重试。。。");
		}
	})
}

function delPic(ablumId){
	 $.ajax({
			url:rootPath+"/contents/album/deletAlbumById.do",
			type:"post",
			datatype:"json",
			data:{ablumId:ablumId},
			success:function(data){
				layer.alert("删除成功");
				var delAblum=$("#"+ablumId).closest("tr");
				 //删除数据
				delAblum.remove();
			},
			error:function(){
				layer.alert("删除失败，请重试。。。");
			}
	  })
}

