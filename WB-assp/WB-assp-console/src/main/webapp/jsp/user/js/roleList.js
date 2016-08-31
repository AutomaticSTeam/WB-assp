var pageNum = 1;
var pagesize = 10;
$(function(){
	//初始化
	showDatagrid("","");
	
	 $("#search").click(function(){
		 var roleName=$('#roleName').val();
		 var roleCode=$("#roleCode").val();
		 showDatagrid(roleName,roleCode);
	 })
	 
	 //编辑
	$("#module-edit").click(function(){
		var rows = $('#module-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			alert("请选择单行数据进行操作");
		}else{
			window.location.href=rootPath+"/console/role/toUpdateRole.do?roleId="+rows[0].roleId;
		}
			
    	
    })
     //删除
     $("#module-del").click(function(){
    	 var rows = $('#module-table').datagrid('getSelections');
  		if(rows.length==0){
  			layer.alert("请选择要操作的数据");
  		}else{
  			var Ids="";
  			for (var i = 0; i < rows.length; i++) {
  				Ids+=rows[i].roleId+","
			}
  			Ids=Ids.substring(0,Ids.length-1);
  			layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
	  			$.ajax({
					url:rootPath+"/console/role/deleteRole.do",
					type:"post",
					datatype:"json",
					data:{Ids:Ids},
					success:function(data){
						if(data.status==1){
							 //var  moduleTmlName=$('#moduleTmplTitle').val()
							 showDatagrid("","");
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
    });
    
})

var showDatagrid=function(name,code){
	$.ajax({
		url:rootPath+"/console/role/roleList.do",
		data:{'roleName': name,'roleCode':code},
		dataType:'json',
		async : true,
		type:"post",
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
				        { field: 'roleId', checkbox: true },
				        { field: 'roleName', title: '角色名称', width: 100, align: 'center' },
				        { field: 'roleCode', title: '角色编码', width: 100, align: 'center'},
				        { field: 'roleDesc', title: '角色描述', width: 100, align: 'center'},
				        { field: 'roleStatus', title: '用户状态', formatter:roleStatus},
				        { field: 'row' , title: '操作' , formatter: cz}
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

function roleStatus(value){
	if(null!=value){
		if('0'==value){
			return "可用";
		}else if('1'==value){
			return "不可用";
		}
	}
}

function cz(){
	var html="<input type=button value=分配权限 onclick='assignedPer()'>";
	return html;
}

function assignedPer(){
	var rows = $('#module-table').datagrid('getSelected');
	if(null==rows){
		alert("请先选中一行后在分配权限!");
	}else{
		if(rows.roleCode=='ROLE_ADMIN'){
			alert("管理员账号不能操作!");
		}else{
			layer.open({
				  type: 2, 
				  title: ['权限列表', 'font-size:14px;'],
				 // content: [rootPath+'/jsp/user/rolePermission.jsp?roleId='+rows.roleId+'', 'yes'],
				  content: [rootPath+'/console/role/toPermissionZtree.do?roleId='+rows.roleId+'', 'yes'],
				  area: ['640px', '400px'],//定义大小
				  closeBtn:1,//右上角关闭按钮样式
				  shade: 0.3, //遮罩为0表示不显示
				  shadeClose :true,//是否点击遮罩关闭
				  scrollbar:true,//是否允许滚动条出现
				  //按钮与回调
				  //btn: ['', '取消'],
				  /*yes: function(index, layero){
				  	 },*/
			});
		}
	}
}
