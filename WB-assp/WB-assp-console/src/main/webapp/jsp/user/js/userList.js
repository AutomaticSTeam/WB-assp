var pageNum = 1;
var pagesize = 10;
$(function(){
	//初始化
	showDatagrid("",pageNum);
	
	 $('#userName').on('blur',function(){
		var  userName=$('#userName').val()
		 showDatagrid(userName);
	});
	 
	 //编辑
	$("#module-edit").click(function(){
		var rows = $('#module-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			alert("请选择单行数据进行操作");
		}else{
			if('admin'!=rows[0].userName){
				window.location.href=rootPath+"/console/user/toUpdateUser.do?userId="+rows[0].userId;
			}else{
				alert("管理员账号不能操作!");
			}
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
  				if(rows[i].userName!='admin'){
  					Ids+=rows[i].userId+","
  				}else{
  					alert("管理员账号不能操作!");
  					return;
  				}
			}
  			Ids=Ids.substring(0,Ids.length-1);
  			layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
	  			$.ajax({
					url:rootPath+"/console/user/deleteUser.do",
					type:"post",
					datatype:"json",
					data:{Ids:Ids},
					success:function(data){
						if(data.status==1){
							//var  moduleTmlName=$('#moduleTmplTitle').val()
							 showDatagrid("",pageNum);
							 //删除数据
							layer.alert(data.msg);
						}else{
							layer.alert(data.msg);
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

var showDatagrid=function(name){
	$.ajax({
		url:rootPath+"/console/user/userList.do",
		data:{'userName': name},
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
				        { field: 'userId', checkbox: true },
				        { field: 'userName', title: '用户账号', width: 100, align: 'center' },
				        { field: 'nickName', title: '昵称', width: 100, align: 'center'},
				        { field: 'phone', title: '手机号', width: 100, align: 'center'},
				        { field: 'userStatus', title: '用户状态', formatter:userStatus},
				        { field: 'dataStatus', title: '数据状态', formatter:dataStatus},
				        { field: 'source', title: '用户来源', formatter:source},
				        { field: 'uid', title: '操作', formatter:operate}
				    ]],onLoadSuccess:function(data){
				    	if(data.rows.length>0){
				    		for (var i = 0; i < data.rows.length; i++) {
				    			if(data.rows[i].userName=='admin'){
				    				$("input[type=checkbox]")[i+1].remove();
				    			}
			                }
				    	}
				    },onClickRow: function(rowIndex, rowData){
			            //加载完毕后获取所有的checkbox遍历
			            $("input[type='checkbox']").each(function(index, el){
			                //如果当前的复选框不可选，则不让其选中
			                if (el.disabled == true) {
			                	$('#module-table').datagrid('unselectRow', index - 1);
			                }
			            })
			        } 

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

function userStatus(value){
	if(null!=value){
		if('0'==value){
			return "正常";
		}else if('1'==value){
			return "停用";
		}
	}
}

function dataStatus(value){
	if(null!=value){
		if('0'==value){
			return "正常";
		}else if('1'==value){
			return "已删除";
		}
	}
}

function source(value){
	if(null!=value){
		if('0'==value){
			return "dotnet平台";
		}else if('1'==value){
			return "wms主站";
		}
	}
}

function operate(){
	var html="<input type='button' name='handle' onclick='handleClick()' value=分配角色>";
	return html;
}

function handleClick(){
	var rows = $('#module-table').datagrid('getSelected');
	if(null==rows){
		alert("请先选中一行后在分配权限!");
	}else{
		var rows = $('#module-table').datagrid('getSelected');
		if(null==rows){
			alert("请先选中一行后在分配权限!");
		}else{
			if(rows.userName=='admin'){
				alert("管理员账号不能操作!");
			}else{
				layer.open({
					  type: 2, 
					  title: ['角色列表', 'font-size:14px;'],
					 // content: [rootPath+'/jsp/user/rolePermission.jsp?roleId='+rows.roleId+'', 'yes'],
					  content: [rootPath+'/console/user/toUserRoleZtree.do?userId='+rows.userId+'', 'yes'],
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
}