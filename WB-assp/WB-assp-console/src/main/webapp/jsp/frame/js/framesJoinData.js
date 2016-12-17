var pageNum = 1;
var pagesize = 10;
/**
 * @author lin
 */
$(function(){
	//表单验证
	$("#moduleForm").Validform({
		tiptype:3
	});
	
	//查询全部框架
	getAllFrames();
	
	//查询全部板式
	getFrame();
	
	//保存
	$("#saveBtn").click(function(){
		saveModuleForm();
	})
	
})

var getAllFrames=function(){
	$.ajax({
		url:rootPath+"/console/frame/ManagerFrames.do",
		data:{'framesName': "",dataStatus : 0},
		dataType:'json',
		async : true,
		success: function(data){
			data=data.rows;
			if(data.length>0){
				$("#moduleTmpl").empty();
				for (var i = 0; i < data.length; i++) {
					if(i==0){
						$("#thumbImgId").attr("src",data[i].framesAttachmentImg.substring(data[0].framesAttachmentImg.lastIndexOf("http://"),data[0].framesAttachmentImg.indexOf("' alt=''")));
					}
					var optionHtml="<option value="+data[i].framesId+" id='"+data[i].framesAttachmentImg.substring(data[0].framesAttachmentImg.lastIndexOf("http://"),data[0].framesAttachmentImg.indexOf("' alt=''"))+"'>"+data[i].framesName+"</option>";
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
var getFramesByType = function(value){
	$.ajax({
		url:rootPath+"/console/frame/getFramesByType.do",
		data:{'framesName': "",'dataStatus' : 0,'framesType':value},
		dataType:'json',
		async : true,
		success: function(data){
			data=data.rows;
			if(data.length>0){
				$("#moduleTmpl").empty();
				for (var i = 0; i < data.length; i++) {
					if(i==0){
						$("#thumbImgId").attr("src",data[i].framesAttachmentImg.substring(data[0].framesAttachmentImg.lastIndexOf("http://"),data[0].framesAttachmentImg.indexOf("' alt=''")));
					}
					var optionHtml="<option value="+data[i].framesId+" id='"+data[i].framesAttachmentImg.substring(data[0].framesAttachmentImg.lastIndexOf("http://"),data[0].framesAttachmentImg.indexOf("' alt=''"))+"'>"+data[i].framesName+"</option>";
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

var getFrame=function(){
	 $.ajax({
			url:rootPath+"/console/frame/ManagerFrame.do",
			data:{'frameName': "",dataStatus : 0},
			dataType:'json',
			async : true,
			success: function(data){
				if(data.rows != null){
					var size = data.rows.length;
					var pageTotal = Math.ceil(size/pagesize);
					if(pageTotal < pageNum){
						pageNum = pageTotal;
					}
					$('#dataTable').datagrid({
						height: 330,
						striped: true,
					    fitColumns: true,
					    rownumbers: true,
					    pagination: true,
					    pageSize: pagesize,
					    pageList: [5,10, 20, 50, 100],
					    showFooter: true,
					    data:data.rows.slice((pageNum-1)*pagesize,pageNum*pagesize),
					    columns: [[
					        { field: 'frameId', checkbox: true },
					        { field: 'frameName', title: '板式名称', width: 100, align: 'center' },
					        { field: 'frameAttachmentImg', title: '预览图', width: 100, align: 'center'},
					        { field: 'frameDesc', title: '板式描述', width: 100, align: 'center'},
					        { field: 'operatorId', title: '创建人id', hidden:true},
					        
					        { field: 'createTime', title: '创建时间', width: 100, align: 'center' ,
					        	formatter : function(value,rec,index){
					        		if(value == null){
					        			return value;
					        		}
					        		var unixTimestamp = new Date(value);  
					        		var month = unixTimestamp.getMonth();
					        		if(month.toLocaleString().length == 1){
					        			month = '0' + month;
					        		}
					        		var date = unixTimestamp.getDate();
					        		if(date.toLocaleString().length == 1){
					        			date = '0' + date;
					        		}
					        		var hours = unixTimestamp.getHours();
					        		if(hours.toLocaleString().length == 1){
					        			hours = '0' + hours;
					        		}
					        		var minutes = unixTimestamp.getMinutes();
					        		if(minutes.toLocaleString().length == 1){
					        			minutes = '0' + minutes;
					        		}
					        		var seconds = unixTimestamp.getSeconds();
					        		if(seconds.toLocaleString().length == 1){
					        			seconds = '0' + seconds;
					        		}
					        		return unixTimestamp.getFullYear()+'-'+month+'-'+date+' '+
					        		hours+':'+minutes+':'+seconds; 
				          	},sortable:true},
					        { field: 'updateTime', title: '更新时间', width: 100, align: 'center' ,
				          		formatter : function(value,rec,index){
				          			if(value == null){
					        			return value;
					        		}
				          			var unixTimestamp = new Date(value);  
				          			var month = unixTimestamp.getMonth();
					        		if(month.toLocaleString().length == 1){
					        			month = '0' + month;
					        		}
					        		var date = unixTimestamp.getDate();
					        		if(date.toLocaleString().length == 1){
					        			date = '0' + date;
					        		}
					        		var hours = unixTimestamp.getHours();
					        		if(hours.toLocaleString().length == 1){
					        			hours = '0' + hours;
					        		}
					        		var minutes = unixTimestamp.getMinutes();
					        		if(minutes.toLocaleString().length == 1){
					        			minutes = '0' + minutes;
					        		}
					        		var seconds = unixTimestamp.getSeconds();
					        		if(seconds.toLocaleString().length == 1){
					        			seconds = '0' + seconds;
					        		}
					        		return unixTimestamp.getFullYear()+'-'+month+'-'+date+' '+
					        		hours+':'+minutes+':'+seconds; 
				          	},sortable:true},
					        { field: 'operatorName', title: '操作人姓名', width: 80, align: 'center' }
					    ]],
					});
					var pager = $("#dataTable").datagrid("getPager"); 
				      pager.pagination({
				    	pageNumber: pageNum,
				    	beforePageText: '第',   
				 		afterPageText: '页     共 {pages} 页',   
				 		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
				        total:data.rows.length, 
				        onSelectPage:function (pageNo, pageSize) { 
				          pagesize = pageSize;
				          pageNum = pageNo;
				          var start = (pageNo - 1) * pageSize; 
				          var end = start + pageSize; 
				          $("#dataTable").datagrid("loadData", data.rows.slice(start, end)); 
				          pager.pagination('refresh', { 
				            total:data.rows.length, 
				            pageNumber:pageNo 
				          }); 
				        } 
				      });
				}
			}
		});
}

var saveModuleForm=function(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0||rows.length>1){
		alert("请选择单行数据进行操作");
	}else{
		//保存数据表格被选中项的id
		var frameId = $('.datagrid-btable input:checkbox:checked').val();
		var framesId = $('#moduleTmpl option:selected').val();
		alert(frameId+"=="+framesId);
		if(frameId!=""&&frameId!=null&&framesId!=""&&framesId!=null){
				//保存 moduleForm
				$.ajax({
					url:rootPath+"/console/frame/saveFramesRefFrame.do",
					type:"post",
					datatype:"json",
					data:{'frameId': frameId,'framesId' : framesId},
					success:function(data){
						if(data.code==1){
							alert(data.tip);
						}else if(data.code==0){
							alert(data.tip);
						}else if(data.code==2){
							alert(data.tip);
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
}

function over(thisSpan){
	$(thisSpan).children("span").css('display','inline');
}

function out(thisSpan){
	$(thisSpan).children("span").css('display','none');
}

