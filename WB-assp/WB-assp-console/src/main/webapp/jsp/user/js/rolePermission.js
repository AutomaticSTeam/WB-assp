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
	url:rootPath+'/console/role/permissionZtree.do',
	data:{roleId:roleId},
	dataType:'json',
	async : false,
	success: function(data){
		zNodes=eval("("+data+")");  
	}
});
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	$("#checkedNode").bind("click", {type:"checkTrue"}, checkNode);
});

//提交表单
function tjForm(){
	var roleId=$("#roleId").val();
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var permissionIds=new Array();
	if(nodes.length>0){
		for(var i=0;i<nodes.length;i++){
			if(null!=nodes[i].pId){
				permissionIds.push(nodes[i].id);
			}
		}
		//alert("勾选了"+nodes.length+"个节点");
	}else{
		alert("请先选择一项权限后提交!");
		return;
	}
	
	if(nodes.length==1 && nodes[0].pId==null){
		permissionIds.push(nodes[0].id);
	}
	//alert(permissionIds);
	$.ajax({
		url:rootPath+'/console/role/rolePermission.do',
		data:{roleId : roleId,permissionIds:permissionIds.toString()},
		dataType:'json',
		async : false,
		success: function(data){
			 alert("分配成功!");
			 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
             parent.layer.close(index);
		}
	});
}

function checkNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	type = e.data.type,
	nodes = zTree.getSelectedNodes();
	if (type.indexOf("All")<0 && nodes.length == 0) {
		alert("请先选择一个节点");
	}

	if (type == "checkAllTrue") {
		zTree.checkAllNodes(true);
	} else if (type == "checkAllFalse") {
		zTree.checkAllNodes(false);
	} else {
		for (var i=0, l=nodes.length; i<l; i++) {
			if (type == "checkTrue") {
				zTree.checkNode(nodes[i], true, false);
			} else if (type == "checkFalse") {
				zTree.checkNode(nodes[i], false, false);
			} else if (type == "toggle") {
				zTree.checkNode(nodes[i], null, false);
			}else if (type == "checkTruePS") {
				zTree.checkNode(nodes[i], true, true);
			} else if (type == "checkFalsePS") {
				zTree.checkNode(nodes[i], false, true);
			} else if (type == "togglePS") {
				zTree.checkNode(nodes[i], null, true);
			}
		}
	}
}
