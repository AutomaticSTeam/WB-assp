var pageNum = 1;
var pagesize = 10;
$(function(){
	showDatagrid("",pageNum);
	
	 $('#moduleTmplTitle').on('blur',function(){
		var  moduleTmlName=$('#moduleTmplTitle').val()
		 showDatagrid(moduleTmlName);
	});
	 
	 //编辑
	$("#module-edit").click(function(){
		var rows = $('#module-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			alert("请选择单行数据进行操作");
		}else{
			window.location.href=rootPath+"/console/frame/editFrame.do?frameId="+rows[0].frameId;
		}
			
    })
     //删除
     $("#module-del").click(function(){
    	 var rows = $('#module-table').datagrid('getSelections');
  		if(rows.length==0){
  			layer.alert("请选择要操作的数据");
  		}else{
  			var frameIds="";
  			for (var i = 0; i < rows.length; i++) {
  				frameIds+=rows[i].frameId+","
			}
  			frameIds=frameIds.substring(0,frameIds.length-1);
  			layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
				 $.ajax({
					 url:rootPath+"/console/frame/deleteFrame.do",
					 type:"post",
					 datatype:"json",
					 data:{frameIds:frameIds},
					 success:function(data){
						 if(data.code==1){
							 var  moduleTmlName=$('#moduleTmplTitle').val()
							 showDatagrid(moduleTmlName,pageNum);
							 //删除数据
							 layer.alert("操作成功！");
						 }else{
						 }
					 },
					 error:function(){
						 layer.alert("删除失败，请重试。。。");
					 }
				 });
			});
  		}
    })
    //预览
     $("#module-preview").click(function(){
    	 var rows = $('#module-table').datagrid('getSelections');
 		if(rows.length==0||rows.length>1){
 			layer.alert("请选择单行数据进行操作");
 		}else{
 			layer.open({
 				  type: 1, 
 				  area: '500px',
 				  shadeClose:true,
 				  content: '<img  style="width:500px;height:500px;" '+rows[0].frameAttachmentImg.substring(rows[0].frameAttachmentImg.lastIndexOf("src"),rows[0].frameAttachmentImg.indexOf("</span></span>"))
 				});
 		}
    })
     $("#module-joinDate").click(function(){
    	 $.ajax({
    			url:rootPath+"/console/frame/ManagerFrame.do",
    			data:{dataStatus : 0},
    			dataType:'json',
    			async : true,
    			success: function(data){
    				if(data.rows != null){
    					$('#module-table').datagrid({
    						height: 330,
    						striped: true,
    					    fitColumns: true,
    					    rownumbers: true,
    					    pagination: true,
    					    pageSize: 10,
    					    pageList: [5,10, 20, 50, 100],
    					    showFooter: true,
    					    data:data.rows.slice(0,10),
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
    					var pager = $("#module-table").datagrid("getPager"); 
    				      pager.pagination({ 
    				        total:data.rows.length, 
    				        onSelectPage:function (pageNo, pageSize) { 
    				          var start = (pageNo - 1) * pageSize; 
    				          var end = start + pageSize; 
    				          $("#module-table").datagrid("loadData", data.rows.slice(start, end)); 
    				          pager.pagination('refresh', { 
    				            total:data.rows.length, 
    				            pageNumber:pageNo 
    				          }); 
    				        } 
    				      });
    				}
    			}
    		});
    })
    
   
})

var showDatagrid=function(frameName,pageNums){
	$.ajax({
		url:rootPath+"/console/frame/ManagerFrame.do",
		data:{'frameName': frameName,dataStatus : 0},
		dataType:'json',
		async : true,
		success: function(data){
			if(data.rows != null){
				var size = data.rows.length;
				var pageTotal = Math.ceil(size/pagesize);
				if(pageTotal < pageNum){
					pageNum = pageTotal;
				}
				$('#module-table').datagrid({
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
				var pager = $("#module-table").datagrid("getPager"); 
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
			          $("#module-table").datagrid("loadData", data.rows.slice(start, end)); 
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

function over(thisSpan){
	$(thisSpan).children("span").css('display','inline');
}

function out(thisSpan){
	$(thisSpan).children("span").css('display','none');
}
