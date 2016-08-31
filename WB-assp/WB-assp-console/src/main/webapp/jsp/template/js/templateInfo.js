$(function(){
	//表单验证
	$(".templateForm").Validform({
		tiptype:3
	});
	
	getAllFrames();
	selectedIndustryType();
	selectedTemplateLogo();
	selectedBanner();
	selectedTemplateFooter();
	selectedColor('');
	selectedColumnType(null,'');
	selectedTemplateColumn(null,'',getUrlParam("templateId"));
	
	//模板管理
	showDatagrid("");
	
	//构建实例-下一步
	$("#gotoStep").click(function(){
		var templateName=$("#templateName").val();
		if(templateName!=""&&templateName!=null){
			$.ajax({
				url:rootPath+"/template/addTemplate.do",
				type:"post",
				datatype:"json",
				data:$("#templateForm").serialize(),
				success:function(data){
					if(data.res==1){
						$("#templateId").val(data.templateId);
						$("#templateCode").val(data.templateCode);
						$("#templateDiv").hide();
						$("#templateDiv2").show();
						$("#step1").removeClass("line");
						$("#step2").addClass("line");
					}else{
						layer.alert("添加失败，请重试。。。");
					}
				},
				error:function(){
					layer.alert("添加失败，请重试。。。");
				}
			});	
		}else{
			layer.alert("请填写完整信息");
		}
	});

	//关联资源-下一步
	$("#resourStep").click(function(){
		$.ajax({
			url:rootPath+"/template/addTemplateResour.do",
			type:"post",
			datatype:"json",
			data:$("#form2").serialize(),
			success:function(data){
				if(data.res==1){
					$("#templateId2").val(data.templateId);
					$("#templateDiv3").show();
					$("#templateDiv").hide();
					$("#templateDiv2").hide();
					$("#step3").addClass("line");
					$("#step1").removeClass("line");
					$("#step2").removeClass("line");
				}else{
					layer.alert("添加失败，请重试。。。");
				}
			},
			error:function(){
				layer.alert("添加失败，请重试。。。");
			}
		});	
		
	});

	//提交
	$("#submitTemplate").click(function(){
		$.ajax({
			url:rootPath+"/template/addTemplateResour.do",
			type:"post",
			datatype:"json",
			data:$("#form3").serialize(),
			success:function(data){
				if(data.res==1){
					/*$("#templateDiv").show();
					$("#templateDiv2").hide();
					$("#templateDiv3").hide();
					$("#step").addClass("line");
					$("#step2").removeClass("line");
					$("#step3").removeClass("line");*/
					window.location.href=rootPath+'/jsp/template/templateWelcome.jsp';
				}else{
					layer.alert("添加失败，请重试。。。");
				}
			},
			error:function(){
				layer.alert("添加失败，请重试。。。");
			}
		});	
	});
	
	//弹出导航添加页面
    $("#columns-add").click(function(){
   	 var rows = $('#template-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			layer.alert("请选择单行数据进行操作");
		}else{
			window.open(rootPath+'/jsp/template/frameAdd.jsp?templateId='+rows[0].templateId+'&templateCode='+rows[0].templateCode,'_blank');
		}
   });
   //填充模块 frame-add
    $("#frame-add").click(function(){
    	var rows = $('#template-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			layer.alert("请选择单行数据进行操作");
		}else{
			//检查模板是否已经关联导航，未关联不能填充模块
			/*$.ajax({
    			url:rootPath+'/template/checkTemplateIsHavCols.do',
    			data:{templateId : rows[0].templateId},
    			dataType:'json',
    			async : true,
    			success:function(data){
					if(data.res==1){
						//window.location.href=rootPath+'/jsp/template/frameAdd.jsp?templateId='+rows[0].templateId+'&templateCode='+rows[0].templateCode;
					}else{
						layer.alert("模板未添加导航，不能填充模块");
					}
				},
				error:function(){
					layer.alert("操作有误，请重试。。。");
				}
    			});*/
			window.open(rootPath+'/jsp/template/frameAdd.jsp?templateId='+rows[0].templateId+'&templateCode='+rows[0].templateCode,'_blank');
		}
   });
    //发布模板
    $("#template-release").click(function(){
      	var rows = $('#template-table').datagrid('getSelections');
      	var rowsStr="";
	  	for (var i = 0; i < rows.length; i++) {
	 	  if(i==rows.length-1){
 			  rowsStr+=rows[i].templateId;
	  	  }else
	  		  rowsStr+=rows[i].templateId+",";
	  	}
	  //提交表单
		  $.ajax({
				url:rootPath+"/template/releaseTemplate.do",
				type:"post",
				datatype:"json",
				data:{templateIds:rowsStr},
				success:function(data){
					if(data.res==1){
						layer.alert("模板发布成功");
						showDatagrid("");
					}else{
						layer.alert("模板发布失败，请重试。。。");
					}
				},
				error:function(){
					layer.alert("模板发布失败，请重试。。。");
				}
			});
     });
    //预览模板 
    $("#template-preview").click(function(){
   	 var rows = $('#template-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			layer.alert("请选择单行数据进行操作");
		}else{
			layer.open({
				  type: 1, 
				  area: '500px',
				  shadeClose:true,
				  content:  '<img width="500px" src="'+rows[0].templateViewUrl+'" >'
				});
		}
   });
    
});

//上传图片
function uploadcpImg(classStr,thisValue){
	uploadImg(classStr,thisValue);
}

var uploadImg=function(classStr,thisValue){
	var templateCode = "modules";
	templateCode = $("#templateCode").val();
	layer.open({
		  type: 2, 
		  title: ['添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）', 'font-size:14px;'],
		  content: [rootPath+'/jsp/common/pop/uploadImg.jsp?singleupload=true&&chooseSingle=true&&templateCode='+templateCode, 'no'],
		 // skin: 'demo-class'
		  area: ['640px', '430px'],//定义大小
		  closeBtn:1,//右上角关闭按钮样式
		  shade: 0.3, //遮罩为0表示不显示
		  shadeClose :true,//是否点击遮罩关闭
		  scrollbar:false,//是否允许滚动条出现
		  //按钮与回调
		  btn: ['确认', '取消'],
		  yes: function(index, layero){
			  setImg(index, layero,classStr,thisValue);
			  layer.close(index);
		  	   },
		});
	
}
//设置附图
var setImg =  function(index, layero,classStr,thisValue){
	var body = layer.getChildFrame('body', index);
	var pUrls=body.find("input[id='pictureUrls']").val();
	if(pUrls != "" && pUrls.length > 0){
		pUrl = pUrls.substring(0,(pUrls.length - 1));
		$(thisValue).attr("src",pUrl);
		$("."+classStr).val(pUrl);
		$(thisValue).unbind("click");
	}
};

//模板数据列表
var showDatagrid=function(template){
	$('#template-table').datagrid({
	    height: 330,
	    width:695,
	    url: rootPath+"/template/queryTemplateList.do",
	    method: 'POST',
	    queryParams: { 'template': template },
	   // idField: '模块模板id',
	    striped: true,
	    fitColumns: true,
	    rownumbers: false,
	    pagination: true,
	    //nowrap: false,
	    loadMsg:"正在加载。。。",
	    singleSelect:true,
	    pageSize: 10,
	    pageList: [5,10, 20, 50, 100],
	    showFooter: true,
	    columns: [[
	        { field: 'templateId', checkbox: true },
	        { field: 'templateCode', title: '模板编码', hidden:true},
	        { field: 'templateName', title: '模板名称', width: 100, align: 'center' },
	        { field: 'industryTypeId', title: '模板类型', width: 50, align: 'center',hidden:true,
	        	formatter : function (value, rec, index) {
	        		$.ajax({
	        			url:rootPath+'/template/queryIndustryTypes.do',
	        			data:{dataStatus : 0},
	        			dataType:'json',
	        			async : true,
	        			success: function(data){
	        				var industList = data.industList;
	        				$.each(industList, function(i, indust) {
	        					if(value == indust.industryTypeId){
	        						return indust.industryTypeName;
	        					}	
	        	            });
	        			}
	        			});
	            }
	        },
	        { field: 'colorId', title: '模板颜色', width: 50, align: 'center',hidden:true,
	        	formatter : function (value, rec, index) {
	        		$.ajax({
	        			url:rootPath+'/template/queryColor.do',
	        			data:{dataStatus : 0},
	        			dataType:'json',
	        			async : true,
	        			success: function(data){
	        				var colorList = data.colorList;
	        				var color_html = "<option value=''>请选择</option>";
	        				$.each(colorList, function(i, color) {
	        					if(value == color.bannerId){
	        						return color.colorName;
	        					}
	        	            });
	        			}
	        			});
	        		//return retVal;
	            }
	        },
	        { field: 'templateThumbnailUrl', title: '缩略图', hidden:true},
	        { field: 'templateViewUrl', title: '展示图', hidden:true},
	        { field: 'createTime', title: '创建时间', width: 150, align: 'center' ,
	        	formatter : function(value,rec,index){
	        		var unixTimestamp = new Date(value);  
	        	    return unixTimestamp.toLocaleString(); 
          	},sortable:true},
	        { field: 'updateTime', title: '更新时间', width: 150, align: 'center' ,
          		formatter : function(value,rec,index){
          			var unixTimestamp = new Date(value);  
          		    return unixTimestamp.toLocaleString(); 
          	},sortable:true},
          	{ field: 'dataStatus', title: '状态', width: 50, align: 'center',
	        	formatter : function (value, rec, index) {
	        		if(value == '2'){
	        			return '未发布';
	        		}else if(value == '0'){
	        			return '已发布';
	        		}
	        	}
	        },
	        { field: 'operatorName', title: '操作人姓名', hidden:true }
	    ]],
	    onBeforeLoad: function (param) {
	    },
	    onLoadSuccess: function (data) {
	    	
	    },
	    onLoadError: function () {
	        
	    },
	    onClickCell: function (rowIndex, field, value) {
	        
	    }
	});
}

//删除菜单
var deleteColumns = function(){
	var base = $(this);
	base.parents('.form').remove();
}

//查询所有框架
var getAllFrames=function(){
	$.ajax({
		url:rootPath+"/console/frame/ManagerFrames.do",
		data:{'framesName': "",dataStatus : 0},
		dataType:'json',
		async : true,
		success: function(data){
			var framesList = data.rows;
			var frames_html = "<option value=''>请选择</option>";
			$.each(framesList, function(i, frames) {
				frames_html += "<option value='" + frames.framesId + "'>" + frames.framesName + "</option>";
            });
			$("#framesId").html(frames_html).attr("disabled", false).css({"display": "", "visibility": ""});
			
		},
		error:function(){
			layer.alert("数据加载失败，请重试！")
		}
	});
}

//查询模板类型
  var selectedIndustryType = function(hyValue){
	  $.ajax({
			url:rootPath+'/template/queryIndustryTypes.do',
			data:{dataStatus : 0},
			dataType:'json',
			async : true,
			success: function(data){
				var industList = data.industList;
				var indust_html = "<option value=''>请选择</option>";
				$.each(industList, function(i, indust) {
					if(hyValue == indust.industryTypeId){
						indust_html += "<option value='" + indust.industryTypeId + "' selected>" + indust.industryTypeName + "</option>";
					}else
						indust_html += "<option value='" + indust.industryTypeId + "'>" + indust.industryTypeName + "</option>";
	            });
				$("#industryTypeId").html(indust_html).attr("disabled", false).css({"display": "", "visibility": ""});
			}
			});
  }
  
//模板logo
  var selectedTemplateLogo = function(hyValue){
	  $.ajax({
			url:rootPath+'/template/queryTemplateLogo.do',
			dataType:'json',
			async : true,
			success: function(data){
				var logoList = data.logoList;
				var logo_html = "<option value=''>请选择</option>";
				$.each(logoList, function(i, logo) {
					if(hyValue == logo.templateLogoId){
						logo_html += "<option value='" + logo.templateLogoId + "' selected>" + logo.logoTitle + "</option>";
					}else
						logo_html += "<option value='" + logo.templateLogoId + "'>" + logo.logoTitle + "</option>";
	            });
				$("#templateLogoId").html(logo_html).attr("disabled", false).css({"display": "", "visibility": ""});
			}
			});
  }
  
//模板banner图
  var selectedBanner = function(hyValue){
	  $.ajax({
			url:rootPath+'/template/queryBanner.do',
			data:{dataStatus : 0},
			dataType:'json',
			async : true,
			success: function(data){
				var bannerList = data.bannerList;
				var banner_html = "<option value=''>请选择</option>";
				$.each(bannerList, function(i, banner) {
					if(hyValue == banner.bannerId){
						banner_html += "<option value='" + banner.bannerId + "' selected>" + banner.bannerName + "</option>";
					}else
						banner_html += "<option value='" + banner.bannerId + "'>" + banner.bannerName + "</option>";
	            });
				$("#templateBannerId").html(banner_html).attr("disabled", false).css({"display": "", "visibility": ""});
			}
			});
  }
 
//模板页脚图
  var selectedTemplateFooter = function(hyValue){
	  $.ajax({
			url:rootPath+'/template/queryTemplateFooter.do',
			dataType:'json',
			async : true,
			success: function(data){
				var footerList = data.footerList;
				var footer_html = "<option value=''>请选择</option>";
				$.each(footerList, function(i, footer) {
					if(hyValue == footer.templateFooterId){
						footer_html += "<option value='" + footer.templateFooterId + "' selected>" + footer.templateFooterId + "</option>";
					}else
						footer_html += "<option value='" + footer.templateFooterId + "'>" + footer.templateFooterId + "</option>";
	            });
				$("#templateFooterId").html(footer_html).attr("disabled", false).css({"display": "", "visibility": ""});
			}
			});
  }
  
//模板颜色  
  var selectedColor = function(hyValue){
	  $.ajax({
			url:rootPath+'/template/queryColor.do',
			data:{dataStatus : 0},
			dataType:'json',
			async : true,
			success: function(data){
				var colorList = data.colorList;
				var color_html = "<option value=''>请选择</option>";
				$.each(colorList, function(i, color) {
					if(hyValue == color.bannerId){
						color_html += "<option value='" + color.colorId + "' selected>" + color.colorName + "</option>";
					}else
						color_html += "<option value='" + color.colorId + "'>" + color.colorName + "</option>";
	            });
				$("#colorId").html(color_html).attr("disabled", false).css({"display": "", "visibility": ""});
			}
			});
  }
  
//导航类型
  var selectedColumnType = function(hyValue,num){
	  $.ajax({
			url:rootPath+'/template/queryColumnType.do',
			data:{dataStatus : 0},
			dataType:'json',
			async : true,
			success: function(data){
				var coltypeList = data.coltypeList;
				var coltype_html = "<option value=''>请选择</option>";
				$.each(coltypeList, function(i, coltype) {
					if(hyValue == coltype.columnsTypeId){
						coltype_html += "<option value='" + coltype.columnsTypeId + "' selected>" + coltype.columnsTypeName + "</option>";
					}else
						coltype_html += "<option value='" + coltype.columnsTypeId + "'>" + coltype.columnsTypeName + "</option>";
	            });
				$("#columnsTypeId"+num).html(coltype_html).attr("disabled", false).css({"display": "", "visibility": ""});
			}
			});
  }
  
//所属的父导航
  var selectedTemplateColumn = function(hyValue,num,templateId){
	  $.ajax({
			url:rootPath+'/template/queryTemplateColumn.do',
			data:{dataStatus : 0,templateId:templateId},
			dataType:'json',
			async : true,
			success: function(data){
				var tempcolumnList = data.tempcolumnList;
				var tempcolumn_html = "<option value='0'>当前为父导航</option>";
				$.each(tempcolumnList, function(i, tempcolumn) {
					if(hyValue == tempcolumn.columnsId){
						tempcolumn_html += "<option value='" + tempcolumn.columnsId + "' selected>" + tempcolumn.columnsName + "</option>";
					}else
						tempcolumn_html += "<option value='" + tempcolumn.columnsId + "'>" + tempcolumn.columnsName + "</option>";
	            });
				$("#columnsPid"+num).html(tempcolumn_html).attr("disabled", false).css({"display": "", "visibility": ""});
			}
			});
  } 
  
  function getUrlParam(name) {  
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");  
	    var r = window.location.search.substr(1).match(reg);  
	    if(r!=null){
	 	  return unescape(r[2]);   
	    }else{
	 	  return null;  
	    }  
	}