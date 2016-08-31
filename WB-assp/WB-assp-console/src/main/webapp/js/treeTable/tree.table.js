/**
 * @author wzp
 * function:表格树化
 */

function customTreeTable() {
	this.option = {
			theme : 'vsStyle',
			expandLevel : 1,
			beforeExpand : function($treeTable, id) {
				//判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
				if ($('.' + id, $treeTable).length) {
					return;
				}
			}

	};
	//直接默认
	this.toTree = function (id) {
		$('#'+id).treeTable(this.option);
    }
};
var  $customTreeTable = new customTreeTable();
