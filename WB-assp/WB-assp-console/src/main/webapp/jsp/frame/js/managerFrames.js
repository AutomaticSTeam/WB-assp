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
			window.location.href=rootPath+"/console/frame/editFrames.do?framesId="+rows[0].framesId;
		}
			
    	
    })
     //删除
     $("#module-del").click(function(){
    	 var rows = $('#module-table').datagrid('getSelections');
  		if(rows.length==0){
  			layer.alert("请选择要操作的数据");
  		}else{
  			var framesIds="";
  			for (var i = 0; i < rows.length; i++) {
  				framesIds+=rows[i].framesId+","
			}
  			framesIds=framesIds.substring(0,framesIds.length-1);
  			layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
	  			$.ajax({
					url:rootPath+"/console/frame/deleteFrames.do",
					type:"post",
					datatype:"json",
					data:{framesIds:framesIds},
					success:function(data){
						if(data.code==1){
							var  moduleTmlName=$('#moduleTmplTitle').val()
							 showDatagrid(moduleTmlName,pageNum);
							 //删除数据
							 layer.alert("操作成功！");
						}else{
							layer.alert("删除失败，请重试。。。");
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
 			/*layer.open({
 				  type: 1, 
 				  area: '500px',
 				  shadeClose:true,
 				  content:  '<img  style="width:500px;height:500px;" '+rows[0].framesAttachmentImg.substring(rows[0].framesAttachmentImg.lastIndexOf("src"),rows[0].framesAttachmentImg.indexOf("</span></span>"))
 				});*/
 			var framesId = rows[0].framesId;
 			window.location.href=rootPath+"/console/frame/framesPreview.do?framesId="+rows[0].framesId;
 			/*$.ajax({
				url:rootPath+"/console/frame/framesPreview.do",
				type:"post",
				datatype:"json",
				data:{framesId:framesId},
				success:function(data){
					if(data.code==1){
						 layer.alert("操作成功！");
					}else{
						layer.alert("操作失败!");
					}
				},
				error:function(){
					layer.alert("操作失败!");
				}
			});*/
 		}
    })
     $("#module-joinDate").click(function(){
    	 
    })
    
    $("#backStep").click(function(){
    	window.location.href=rootPath+'/jsp/frame/managerFrames.jsp';
    });
   
})

var showDatagrid=function(framesName){
	
	$.ajax({
		type:'post',
		 url:rootPath+"/console/frame/ManagerFrames.do",
		//data:$("#templateForm").serialize(),
		dataType:'json',
		async : true,
		success: function(data){ 
			 $data = data.rows;
			 var html = "";
			 $.each($data ,function(i,frame){ 
				 html += '<tr>'
					   +'<td>'
		               +'<input type="checkbox" name="framesId">'
		               +'</td>'
		               +'<td>' + frame.framesName + '</td>'
		               +'<td>' + frame.framesAttachmentImg + '</td>'
		               +'<td>'+frame.framesDesc+'</td>'
		               +'<td>' + frame.operatorName + '</td>'
		               +'<td>' + DateUtils.long2String(frame.createTime, 7) + '</td>'
		               +'</tr>';
			 });
			 $("#moduleDataGrid").html(html);
		 },
		error:function(data){
			//alert(JSON.stringify(data));
		}
     });
	
	
	/*$.ajax({
		url:rootPath+"/console/frame/ManagerFrames.do",
		data:{'framesName': framesName,dataStatus : 0},
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
				    singleSelect:true,
				    pageList: [5,10, 20, 50, 100],
				    showFooter: true,
				    data:data.rows.slice((pageNum-1)*pagesize,pageNum*pagesize),
				    columns: [[
				        { field: 'framesId', checkbox: true },
				        { field: 'framesName', title: '板式名称', width: 100, align: 'center' },
				        { field: 'framesAttachmentImg', title: '预览图', width: 100, align: 'center'},
				        { field: 'framesDesc', title: '板式描述', width: 100, align: 'center'},
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
	});*/
}

function over(thisSpan){
	$(thisSpan).children("span").css('display','inline');
}

function out(thisSpan){
	$(thisSpan).children("span").css('display','none');
}
