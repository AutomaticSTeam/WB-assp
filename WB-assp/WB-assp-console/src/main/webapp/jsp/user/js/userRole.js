//初始化
var setting = {
			check: {
				enable: true,
				nocheckInherit: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
var zNodes;
$.ajax({
	url:rootPath+'/console/user/roleZtree.do',
	data:{userId:userId},
	dataType:'json',
	async : false,
	success: function(data){
		zNodes=eval("("+data+")");  
	}
});
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});

//提交表单
function tjForm(){
	var userId=$("#userId").val();
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var permissionIds=new Array();
	if(nodes.length>0){
		for(var i=0;i<nodes.length;i++){
			permissionIds.push(nodes[i].id);
		}
		//alert("勾选了"+nodes.length+"个节点");
	}else{
		alert("请先选择一项权限后提交!");
		return;
	}
	$.ajax({
		url:rootPath+'/console/user/userRole.do',
		data:{userId : userId,roleIds:permissionIds.toString()},
		dataType:'json',
		async : false,
		success: function(data){
			 alert("分配成功!");
			 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
             parent.layer.close(index);
		}
	});
}