/**
 * 代码依赖于jquery.ztree.core-3.5.min.js
 * 样式依赖于zTreeStyle.css
 * @author jjy,wangzhipeng
 * @date 2016-06-21 
 */
$(document).ready(function(){
	$('html').click(function(){
		var act=$(document.activeElement);
		var act_name=act.attr('name');
		//去掉前缀
		if(act_name!=undefined&&act_name.indexOf('\.')>=0){
			act_name=act_name.substring(act_name.indexOf('\.')+1);
		}
		//如果元素里面有指定的后缀
		if(act.attr('suffix') != undefined){
			act_name=act_name+'_'+act.attr('suffix');
		}
		//关闭其他不需要显示的下拉树
		$(".div_style").each(function(i,n){
			if($(document.activeElement).parents(".div_style").length==0&&$('#dialog_'+act_name)[0]!=n&&document.activeElement!=n){
				$(n).hide();
			}
		});
	});
});
var $formatToZtree = {
		/**
		 * 给控件加上下拉树效果
		 * @param path
		 * @param sjzd_fl
		 * @param deep 不显示的深度默认为1
		 * @param zd_id 节点id 不赋值时直接取根节点
		 * @param root_path 当前根节点已有路径
		 * @param check_enable 是否可以多选
		 * @param function_name 获取数据的函数 默认是getZd
		 * @param must_leaf 是否必须选择叶子节点
		 */
		selecteTree: function (path,sjzd_fl,deep,zd_id,root_path,check_enable,function_name,must_leaf){
			//设置最大高宽，为了保证网站所有下拉树的一致性，故在此设置尺寸，不采用传递高宽的做法。
			var max_height=350;
			var min_width=300;
			var max_width=$('#main-content').width();
			//获取事件
			var act=$(document.activeElement);
			var act_name=act.attr('name');//每次修改act_name的时候，需要记得修改上面方法中的代码
			//去掉前缀
			if(act_name.indexOf('\.')>=0){
				act_name=act_name.substring(act_name.indexOf('\.')+1);
			}
			
			//如果元素里面有指定的后缀
			if(act.attr('suffix') != undefined){
				act_name=act_name+'_'+act.attr('suffix');
			}

			var div=$('#dialog_'+act_name);
			if(deep == undefined){
				deep=1;
			}
			if(div.length==0){
				var zTreeNodes;
				zTreeNodes=function_name();
				//再判断判断
				var setting = {
					view: {
						selectedMulti: false
					},
					callback:{
						onClick:function(event, treeId, treeNode){
							var name=treeNode.name;
							var temp=treeNode;
							if(must_leaf!=undefined&&must_leaf!=null&&must_leaf){//如果必须选择子节点
								if(treeNode.isParent){//选择了非叶子节点
									alert('必须选择叶子节点');
									return;
								}
							}
							if(treeNode.getParentNode()==null){
								if(root_path==''||root_path==undefined||root_path==null){
									act.val(name);
									act.attr('title',name);
									act.prev().val(treeNode.id);
									div.hide();
								}else{
									act.val(root_path);
									act.attr('title',root_path);
									act.prev().val(treeNode.id);
									div.hide();
								}
							}else{//不给根节点绑定事件
								//深度及深度之内的直接显示name,只有大于深度的，需要加上父级的name
								while(!$formatToZtree.checkItemDeep(temp,deep+1)){
									temp=temp.getParentNode();
									name=temp.name+'_'+name;
								}
								if(root_path!=undefined&&root_path!=''&&msg.root_id!=zd_id){
									name=root_path+'_'+name;
								}
								act.val(name);
								act.attr('title',name);
								act.prev().val(treeNode.id);
								div.hide();
							}
							//触发改变事件
							act.change();
						},
						onExpand:function(event, treeId, treeNode){
							$formatToZtree.resize(act_name,act,div);
						},
						onCollapse:function(event, treeId, treeNode){
							$formatToZtree.resize(act_name,act,div);
						}
					}
				};
				if(check_enable){
					setting.check={enable:true};
				}
				div=$('<div id="dialog_'+act_name+'" class="div_style" tabindex="2" style="min-height:100px;max-with:150px;position:absolute;zIndex=10000;border:1px solid #aaa;overflow:auto;display:block;"></div>');
				$('body').append(div);
				var tree=$('<div id="'+act_name+'" class="ztree" style="background:#fff;"></div>');
				div.append(tree);
				var zTreeObj = $.fn.zTree.init(tree, setting, zTreeNodes);
				if(check_enable){
					var c=$('<div id="ok_'+act_name+'" style="font-size:12px; font-family: Verdana, Arial, Helvetica, AppleGothic;padding:5px;border-top:solid 1px #aaa;background:#fff;text-align:center;cursor:pointer" title="确定">确定</div>');
					c.click(function(){
						var idList=new Array();
						var nameList=new Array();
						$(zTreeObj.getCheckedNodes()).each(function(index, domEle){
							//本机为全选状态并且没有父级，或者父级为半选状态
							if(!domEle.getCheckStatus().half&&(domEle.getParentNode()==null||domEle.getParentNode().getCheckStatus().half)){
								var temp=domEle;
								var name=domEle.name;
								if(domEle.getParentNode()==null){//选择了根节点
									if(root_path==undefined||root_path==null){
										alert('不能选择根节点');
									}else if(root_path==''){
										idList.push(domEle.id);
										nameList.push(name);
									}else{
										idList.push(domEle.id);
										nameList.push(root_path);
									}
								}else{
									idList.push(domEle.id);
									while(!checkItemDeep(temp,deep+1)){
										temp=temp.getParentNode();
										name=temp.name+'_'+name;
									}
									if(root_path!=undefined&&root_path!=''&&msg.root_id!=zd_id){
										name=root_path+'_'+name;
									}
									nameList.push(name);
								}
							}
						});
						act.val(nameList.toString());
						act.attr('title',nameList.toString());
						act.prev().val(idList.toString());
						div.hide();
					});
					div.append(c);
				}
				var d=$('<div id="clear_'+act_name+'" style="font-size:12px; font-family: Verdana, Arial, Helvetica, AppleGothic;padding:5px;border-top:solid 1px #aaa;border-bottom:solid 1px #aaa;background:#fff;text-align:center;cursor:pointer" title="清空">清空</div>');
				d.click(function(){
					act.val('');
					act.attr('title','');
					act.prev().val('');
					div.hide();
					//触发改变事件
					act.change();
				});
				div.append(d);
			}
			//根据控件位置显示在控件的上面或下面。根据显示的位置，对控件高度进行调整
			if(act.offset().top<$('#main-content').height()/2){
				if(act.offset().top+act.height()+4+max_height>$('#main-content').height()){
					div.attr('max_height',$('#main-content').height()-act.offset().top-act.height()-6);//多一像素的边框
				}else{
					div.attr('max_height',max_height);
				}
			}else{
				if(act.offset().top-max_height-2<0){
					div.attr('max_height',act.offset().top-2);
				}else{
					div.attr('max_height',max_height);
				}
			}
			//调整宽度
			if(max_width-2<=min_width){//如果最大的宽度比合适的宽度要小。则取最大宽度并且空间位置为0
				div.width(max_width-2);
				div.css({left:0});
			}else{
				if(act.width()+2>min_width){
					div.width(act.width()+2);
				}else{
					div.width(min_width);
				}
				//调整水平对齐方式
				if(act.offset().left+div.width()+2>$('#main-content').width()){//如果按照做左齐时，右边超过了边框
					if(act.offset().left+act.width()+4<=$('#main-content').width()){//被点击的元素右侧未超出屏幕的时候，与元素右端对齐，否则与屏幕最右端对齐。
						if(act.offset().left+act.width()-div.width()+2<0){//如果与被点击的元素右对齐时，左端小于0则与屏幕最左边对齐。
							div.css({left:0});
						}else{
							div.css({left:(act.offset().left+act.width()-div.width()+2)+'px'});
						}
					}else{
						div.css({left:$('#main-content').width()-div.width()-2+'px'});
					}
				}else{//按照左对齐时，右边没有超过边框
					if(act.offset().left>0){//被点击的元素，最左侧在显示区域的时候，与元素最左端对齐，否则与屏幕最左端对齐。
						div.css({left:act.offset().left+'px'});
					}else{
						div.css({left:0});
					}
				}
			}
			if(div.width()>150)
				div.width(150);
			if(div.attr('max_width')>150)
				div.css({"z-max_width":150});
				
			div.css({"z-index":100000000});
			//展示
			div.show();
			//展示之后再调整，比较准确
			$formatToZtree.resize(act_name,act,div);
		},
		/**
		 * 根据内容，调整下拉树，使之更美观。此函数供dialog.js文件内的函数使用，外部不应该直接调用
		 * @param act_name 根据条件生成的act_name
		 * @param act 触发事件的act
		 * @param div 最外边框的div
		 */
		resize: function (act_name,act,div){
			//根据里面的内容，调整高度
			if($('#'+act_name).outerHeight(true)+$('#clear_'+act_name).outerHeight(true)+$('#ok_'+act_name).outerHeight(true)<div.attr('max_height')){
				div.height($('#'+act_name).outerHeight(true)+$('#clear_'+act_name).outerHeight(true)+$('#ok_'+act_name).outerHeight(true));
			}else{
				div.height(div.attr('max_height'));
			}
			//根据控件位置显示在控件的上面或下面。根据显示的位置，对控件位置进行调整
			if(act.offset().top<$('#main-content').height()/2){
				//因为只需要出去上边的边距，所以求act两个高度的平均数
				div.css({top:(act.offset().top+(act.outerHeight(true)))+'px'});
			}else{
				div.css({top:(act.offset().top-div.outerHeight(true))+'px'});
			}
		},
		/**
		 * 给控件加上下拉树效果2
		 * 用于获取数据节点的一部分
		 * @param path
		 * @param sjzd_fl
		 * @param zd_id 数据节点根目录的id
		 * @param root_path 当前根节点已有路径
		 */
		selecteTree2:function (path,sjzd_fl,zd_id,root_path){
			$formatToZtree.selecteTree(path,sjzd_fl,1,zd_id,root_path,false);
		},
		/**
		 * 给控件加上下拉树效果
		 * @param deep 不显示的深度默认为1
		 * @param check_enable 是否可以多选
		 * @param function_name 获取数据的函数 默认是getZd
		 */
		selecteBean:function (deep,check_enable,function_name){
			$formatToZtree.selecteTree(null,null,deep,null,'',check_enable,function_name);
		},
		/**
		 * 给控件加上下拉树效果
		 * 用于获取数据节点的一部分并且可多选
		 * @param path
		 * @param sjzd_fl
		 * @param zd_id 节点id 不赋值时直接取根节点
		 * @param root_path 当前根节点已有路径
		 */
		selecteCheckBoxTree:function (path,sjzd_fl,zd_id,root_path){
			$formatToZtree.selecteTree(path,sjzd_fl,1,zd_id,root_path,true);
		},
		/**
		 * 给控件加上下拉树效果
		 * @param path
		 * @param sjzd_fl
		 * @param deep 不显示的深度默认为1
		 * @param zd_id 节点id 不赋值时直接取根节点
		 * @param root_path 当前根节点已有路径
		 * @param check_enable 是否可以多选 默认不可以
		 * @param function_name 获取数据的函数 默认是getZd
		 * @param must_leaf 是否必须选择叶子节点
		 */
		selecteLeafTree:function (path,sjzd_fl,deep,zd_id,check_enable){
			$formatToZtree.selecteTree(path,sjzd_fl,deep,zd_id,null,check_enable,null,true);
		},
		/**
		 * 判断节点是否在深度之内
		 * @param treeNode 当前节点
		 * @param deep 深度
		 */
		checkItemDeep:function (treeNode,deep){
			for(var i=0;i<deep;i++){
				if(treeNode.getParentNode()==null){
					return true;
				}else{
					treeNode=treeNode.getParentNode();
				}
			}
			return false;
		},
		/**
		 * 获取格式化的树节点,
		 * $data - 为ZtreeObj的集合
		 * */
		getTreeNode:function ($data){
			var treeNodes = [];
			//根节点遍历
			for(var i=0;i<$data.length;i++){
				if($data[i].pId == void 0 || $data[i].pId == 0){
					
					treeNodes.push($data[i]);
					$formatToZtree.addTreeNode($data[i],$data);
				}
			}
			return treeNodes;
		},
		/**
		 * 获取格式化的树节点,并且排序是否排序
		 * $data - 为ZtreeObj的集合
		 * flg true为排序
		 * */
		getTreeNode:function ($data,flg){
			var treeNodes = [];
			//根节点遍历
			for(var i=0;i<$data.length;i++){
				if($data[i].pId == void 0 || $data[i].pId == 0){
					treeNodes.push($data[i]);
					$formatToZtree.addTreeNode($data[i],$data);
					if(flg){
						$data[i].children.sort(function(a,b){
							return a.sort - b.sort;
						});
					}
						
				}
			}
			
			return flg ? treeNodes.sort(function(a,b){
						return a.sort - b.sort;
					}) : treeNodes;
		},
		/**
		 * 为某节点查找子节点,
		 * ztreeNode - ZtreeObj
		 * $data - 为ZtreeObj的集合
		 * */
		addTreeNode:function (ztreeNode,$data){
			for(var i=0;i<$data.length;i++){
				if($data[i].pId != void 0 && ztreeNode.id==$data[i].pId){
					ztreeNode.addChildren($data[i]);
					$formatToZtree.addTreeNode($data[i],$data);
				}
			}
		},
		/**
		 * 返回有序集合
		 * $data - 为ZtreeObj的集合
		 * */
		getAllNodes:function ($data){
			var arr_ = [];
			$formatToZtree.setAllNodes(arr_,$data);
			return arr_;
		},
		/**
		 * set集合
		 * $data - 为ZtreeObj的集合
		 * */
		setAllNodes:function (arr_,$data){
			for(var i=0;i<$data.length;i++){
				arr_.push($data[i]);
				if($data[i].isParent){
					$formatToZtree.setAllNodes(arr_,$data[i].children);
				}
			}
		}
};

var ZtreeObj = function(id,pId,name){
	//子节点集合
	this.children = [];
	//节点id
	this.id = id;
	//父节点id
	this.pId = pId;
	//节点名称
	this.name = name;
	//是否展开
	this.open = false;
	//是否为父节点
	this.isParent = false;
	//节点编码
	this.code = '';
	//节点顺序号
	this.sort = 0;
	//图标是否有复选框
	this.checked = false;
	
	this.addChildren = function(child){
		this.children.push(child);
		if(!this.isParent)
			this.isParent = true;
	};
}
