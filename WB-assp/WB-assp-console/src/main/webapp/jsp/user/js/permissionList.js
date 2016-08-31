var pageNum = 1;
var pagesize = 10;
$(function(){
	//初始化
	showDatagrid("");
	
	 $('#moduleTmplTitle').on('blur',function(){
		var  moduleTmlName=$('#moduleTmplTitle').val()
		 showDatagrid(moduleTmlName);
	});



 	//新增
 	$("#module-add").click(function(){
 		var rows = $('#module-table').datagrid('getSelections');
 		if(rows.length==0||rows.length>1){
 			alert("请选择单行数据进行操作");
 		}else{
 			window.location.href=rootPath+"/console/permission/toAddPermission.do?permissionId="+rows[0].permissionId;
 		}
     })
	 
	 //编辑
	$("#module-edit").click(function(){
		var rows = $('#module-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			alert("请选择单行数据进行操作");
		}else{
			window.location.href=rootPath+"/console/permission/toUpdatePermission.do?permissionId="+rows[0].permissionId;
		}
			
    	
    })
     //删除
     $("#module-del").click(function(){
    	 var rows = $('#module-table').datagrid('getSelections');
    	 if(rows.length==0||rows.length>1){
 			alert("请选择单行数据进行操作");
 		 }else{
  			layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
	  			$.ajax({
					url:rootPath+"/console/permission/deletePermission.do",
					type:"post",
					datatype:"json",
					data:{permissionId:rows[0].permissionId},
					success:function(data){
						if(data.status==1){
							 //var  moduleTmlName=$('#moduleTmplTitle').val()
							 showDatagrid("",pageNum);
							 //删除数据
							 alert(data.msg);
						}else{
							alert(data.msg);
						}
						layer.close(index);
					},
					error:function(){
						alert("删除失败，请重试。。。");
					}
				});
  			});
  		}
    });
    
})

var showDatagrid=function(framesName){
	$('#module-table').treegrid({
		url:rootPath+"/console/permission/permissionList.do",
		height: 330,
		rownumbers: true,
		idField:"permissionId",
		treeField:"permissionName",
		//collapseAll:false,
		animate:true,
	    columns: [[
	        { field: 'permissionId',hidden:true},
	        { field: 'permissionName', title: '权限名称', width: 200, align: 'left'},
	        { field: 'permissionCode', title: '权限编码', width: 260, align: 'left' }
	       ,{ field: 'permissionPid', title: '父级ID', width: 100, align: 'center' }
	       //,{ field: 'permissionDesc', title: '权限描述', width: 100, align: 'center' }
	       ,{ field: 'permissionStatus', title: '资源状态', width: 100, align: 'center',formatter:permissionStatus}
	       ,{ field: 'permissionDeleteFlag', title: '是否删除', width: 100, align: 'center',formatter:permissionDeleteFlag}
	    ]]
	  /*  onLoadSuccess: function (row, data)
        {
            $.each(data, function (i, val) { $('#module-table').treegrid('collapseAll', data[i].id)})
        }*/
	});
}

function over(thisSpan){
	$(thisSpan).children("span").css('display','inline');
}

function out(thisSpan){
	$(thisSpan).children("span").css('display','none');
}


function permissionStatus(value){
	if(null!=value){
		if('0'==value){
			return "可用";
		}else if('1'==value){
			return "不可用";
		}
	}
}

function permissionDeleteFlag(value){
	if(null!=value){
		if('0'==value){
			return "未删除";
		}else if('1'==value){
			return "已删除";
		}
	}
}