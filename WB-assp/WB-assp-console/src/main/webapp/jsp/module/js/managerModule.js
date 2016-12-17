$(function(){
	showDatagrid("");
	
	 
	 $('#moduleTmplTitle').on('blur',function(){
		var  moduleTmlName=$('#moduleTmplTitle').val()
		 //showDatagrid(moduleTmlName);
		});
	 
	 //编辑
	$("#module-edit").click(function(){
		var rows = $('#module-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			alert("请选择单行数据进行操作");
		}else{
			window.location.href=rootPath+"/console/moduleTmpl/editModuleTmpl.do?moduleTmlId="+rows[0].moduleTmlId;
		}
			
    	
    })
     //删除
     $("#module-del").click(function(){
    	 var rows = $('#module-table').datagrid('getSelections');
  		if(rows.length==0){
  			layer.alert("请选择要操作的数据");
  		}else{
  			var moduleTmlIds="";
  			for (var i = 0; i < rows.length; i++) {
  				moduleTmlIds+=rows[i].moduleTmlId+","
			}
  			moduleTmlIds=moduleTmlIds.substring(0,moduleTmlIds.length-1);
  			$.ajax({
				url:rootPath+"/console/moduleTmpl/deletModuleTmpl.do",
				type:"post",
				datatype:"json",
				data:{moduleTmlIds:moduleTmlIds},
				success:function(data){
					if(data.code==1){
						layer.alert(data.tip);
						//刷新表格
						$('.l-btn-plain').click();
						
					}else{
						layer.alert("删除失败，请重试。。。");
					}
				},
				error:function(){
					layer.alert("删除失败，请重试。。。");
				}
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
 				  content:  '<img width="500px" src="'+rows[0].moduleTmlAttachmentImg+'" >'
 				});
 		}
    })
     $("#module-joinDate").click(function(){
    	 
    })
    
   
})

var showDatagrid=function(moduleTmlName){
	
	
	$.ajax({
		type:'post',
		 url:rootPath+"/console/moduleTmpl/ManagerModuleTmpl.do",
		//data:$("#templateForm").serialize(),
		dataType:'json',
		async : true,
		success: function(data){ 
			 $data = data.rows;
			 var html = "";
			 $.each($data ,function(i,module){ 
				 html += '<tr>'
					   +'<td>'
		               +'<input type="checkbox" name="moduleTmlId">'
		               +'</td>'
		               +'<td>' + module.moduleTmlName + '</td>'
		               +'<td>' + module.moduleTmlKey + '</td>'
		               +'<td><span class="label label-warning label-mini">'+module.moduleTmlAttachmentImg+'</span></td>'
		               +'<td>' + module.moduleTmlContent + '</td>'
		               +'<td>' + module.operatorName + '</td>'
		               +'<td>' + DateUtils.long2String(module.createTime, 7) + '</td>'
		               +'</tr>';
			 });
			 $("#moduleDataGrid").html(html);
		 },
		error:function(data){
			//alert(JSON.stringify(data));
		}
     });
	
}