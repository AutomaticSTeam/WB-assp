/**
 * @author lin
 */
$(function(){
	//表单验证
	$("#moduleForm").Validform({
		tiptype:3
	});
	
	//数据类型改变赋值隐藏域
	$("#dataTargetTable").change(function(){
		$("#contentDataType").val($("#dataTargetTable").find("option:selected").attr("class"));
		getDataByType($("#dataTargetTable").find("option:selected").attr("id"));
	})
	
	//查询全部模板 填充选项
	getModuleTmpl();
	
	//查询父节点 填充选项
	queryPModules(getUrlParam("columnsRelFrameId"),getUrlParam("sort"));
	
	getDataByType(1);
	
	$("#pageShow_yes").click(function(){
		$("#pageSize_item").show();
		
	})
	$("#pageShow_no").click(function(){
		$("#pageSize_item").hide();
		
		
	})
	
	//保存
	$("#saveBtn").click(function(){
		saveModuleForm();
	})
	
	//取消
	$("#cancleBtn").click(function(){
		$("#moduleForm").reset();
	})
	
})

var getModuleTmpl=function(){
	$.ajax({
		url:rootPath+"/console/moduleTmpl/ManagerModuleTmpl.do",
		type:"post",
		datatype:"json",
		success:function(data){
			 data=data.rows;
			if(data.length>0){
				$("#moduleTmpl").empty();
				for (var i = 0; i < data.length; i++) {
					if(i==0){
						$("#thumbImgId").attr("src",data[i].moduleTmlAttachmentImg);
					}
					var optionHtml="<option value='"+data[i].moduleTmlKey+"' id='"+data[i].moduleTmlAttachmentImg+"' class='"+data[i].moduleTmlKey+"'>"+data[i].moduleTmlName+"</option>";
					$("#moduleTmpl").append(optionHtml);
				}
				$("#moduleTmpl").change(function(){
					$("#thumbImgId").attr("src",$("#moduleTmpl").find("option:selected").attr("id"));
				})
				
			}else{
				layer.alert("数据加载失败，请重试！")
			}
		},
		error:function(){
			layer.alert("数据加载失败，请重试！")
		}
	});
}

var queryPModules=function(columnsRelFrameId,sort){
	$.ajax({
		url:rootPath+"/template/queryPModules.do",
		type:"post",
		datatype:"json",
		data:{dataStatu:0,columnsRelFrameId:columnsRelFrameId,sort:sort},
		success:function(data){
			$("#modulePid").empty();
			$("#modulePid").append('"<option value=\"0\">当前为父节点</option>"');
			for (var i = 0; i < data.length; i++) {
				var updateTime = new Date(data[i].updateTime).toLocaleString(); 
				var optionHtml="<option value='"+data[i].moduleId+"'>"+data[i].moduleName+"------------"+updateTime+"</option>";
				$("#modulePid").append(optionHtml);
			}
		},
		error:function(){
			layer.alert("数据加载失败，请重试！")
		}
	});
}


var getDataByType=function(dataType){
			var columns="";
			 switch (parseInt(dataType)) {
			case 0:
				break;
			case 1:
				 columns= [[
					        { field: 'albumId', checkbox: true },
					        { field: 'albumName', title: '名称', width: 100, align: 'center' },
					        
					        { field: 'operatorId', title: '创建人id', hidden:true},
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
					        { field: 'operatorName', title: '操作人姓名', width: 80, align: 'center' }
					    ]];
				break;
			case 2:
				 columns= [[
					        { field: 'albumTypeId', checkbox: true },
					        { field: 'albumTypeName', title: '名称', width: 100, align: 'center' }
					    ]];
				break;
			case 3:
				 columns= [[
					        { field: 'articleId', checkbox: true },
					        { field: 'articleTitle', title: '标题', width: 100, align: 'center' },
					        { field: 'articleAuthor', title: '文章作者', width: 100, align: 'center' },
					        { field: 'articleNtro', title: '文章简介', width: 100, align: 'center' },
					        { field: 'operatorId', title: '创建人id', hidden:true},
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
					        { field: 'operatorName', title: '操作人姓名', width: 80, align: 'center' }
					    ]];
				break;
			case 4:
				columns= [[
					        { field: 'articleTypeId', checkbox: true },
					        { field: 'articleTypeName', title: '名称', width: 100, align: 'center' },
					        { field: 'articleTypeDesc', title: '分类描述', width: 100, align: 'center' },
					        { field: 'operatorId', title: '创建人id', hidden:true},
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
					        { field: 'operatorName', title: '操作人姓名', width: 80, align: 'center' }
					    ]];
				break;
			case 5:
				columns= [[
					        { field: 'mediaId', checkbox: true },
					        { field: 'mediaName', title: '名称', width: 100, align: 'center' },
					        { field: 'mediaAuthor', title: '作者', width: 100, align: 'center' },
					        { field: 'mediaSuffix', title: '格式', width: 100, align: 'center' },
					        { field: 'operatorId', title: '创建人id', hidden:true},
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
					        { field: 'operatorName', title: '操作人姓名', width: 80, align: 'center' }
					    ]];
				break;
			case 6:
				columns= [[
					        { field: 'mediaTypeId', checkbox: true },
					        { field: 'mediaTypeName', title: '名称', width: 100, align: 'center' }
					       ]];
				break;
			case 7:
				columns= [[
					        { field: 'templateLogoId', checkbox: true },
					        { field: 'logoTitle', title: 'logo标题', width: 100, align: 'center' },
					        { field: 'fisrtTitle', title: '主标题', width: 100, align: 'center' },
					        { field: 'secondTitle', title: '二级标题', width: 100, align: 'center' },
					        { field: 'logoImgUrl', title: 'logo图片', width: 100, align: 'center',
					        	formatter : function(value,rec,index){
				          		    return '<img src="'+value+'"  width="185px" height="115px "/>'; 
					        	}
					        }
					       ]];
				break;	
			default:
				break;
			}
			 if(columns!=""){
				 $('#dataTable').datagrid({
					 height: 330,
					 url: rootPath+"/template/queryDataByType.do",
					 method: 'POST',
					 queryParams: { 
						 dataType:dataType,
						 dataStatus:0 },
						 striped: true,
						 fitColumns: true,
						 rownumbers: false,
						 pagination: true,
						 pageSize: 10,
						 loadMsg:"正在加载。。。",
						 singleSelect:true,
						 pageList: [5,10, 20, 50, 100],
						 showFooter: true,
						 columns: columns,
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
	
		
}

var saveModuleForm=function(){
	
	//保存数据表格被选中项的id
	$("#dataColumnId").val($('.datagrid-btable input:checkbox:checked').val());
	
	var moduleName=$("#moduleName").val();
	var moduleItemLineNum=$("#moduleItemLineNum").val();
	var moduleItemColumnNum=$("#moduleItemColumnNum").val();
	var dataColumnId=$("#dataColumnId").val();
	
	if(moduleName!=""&&moduleName!=null
		&&moduleItemLineNum!=""&&moduleItemLineNum!=null
		&&moduleItemColumnNum!=""&&moduleItemColumnNum!=null
		&&dataColumnId!=""&&dataColumnId!=null){
		//保存 moduleForm
		$.ajax({
			url:rootPath+"/template/saveModule.do",
			type:"post",
			datatype:"json",
			data:$("#moduleForm").serialize(),
			success:function(data){
				if(data.code==1){
					$("#moduleId").val(data.Key);
					$.ajax({
						url:rootPath+"/template/saveModuleRelContent.do",
						type:"post",
						datatype:"json",
						data:$("#moduleTmplForm").serialize(),
						success:function(data){
							if(data.code==1){
								layer.alert(data.tip);
							}
						},
						error:function(){
							layer.alert("保存失败，请重试！")
						}
					});
					
				}
			},
			error:function(){
				layer.alert("保存失败，请重试！")
			}
		});
	}else{
		layer.alert("请完整填写表单！")
	}
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