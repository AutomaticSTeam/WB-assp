$(function(){
	showDatagrid("");
	
	 var pagger = $('#module-table').datagrid('getPager');
	 
	 $('#moduleTmplTitle').on('blur',function(){
		var  moduleTmlName=$('#moduleTmplTitle').val()
		 showDatagrid(moduleTmlName);
		});
	 $(pagger).pagination({
	 		showPageList:false,
	 		beforePageText: '第',   
	 		afterPageText: '页     共 {pages} 页',   
	 		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
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
	$('#module-table').datagrid({
	    height: 330,
	    url: rootPath+"/console/moduleTmpl/ManagerModuleTmpl.do",
	    method: 'POST',
	    queryParams: { 'moduleTmlName': moduleTmlName },
	   // idField: '模块模板id',
	    striped: true,
	    fitColumns: true,
	    rownumbers: false,
	    pagination: true,
	    //nowrap: false,
	    pageSize: 10,
	    pageList: [5,10, 20, 50, 100],
	    showFooter: true,
	    columns: [[
	        { field: 'moduleTmlId', checkbox: true },
	        { field: 'moduleTmlName', title: '名称', width: 100, align: 'center' },
	        { field: 'moduleTmlKey', title: '关键字', width: 200, align: 'center',
	        	formatter : function (value, rec, index) {
	                if (value.length > 30) {
	                	value = value.substr(0, 29) + "..."
	                };
	                return value;
	            }
	        },
	        { field: 'moduleTmlAttachmentImg', title: '预览图', hidden:true},
	        { field: 'moduleTmlContent', title: '内容', hidden:true},
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