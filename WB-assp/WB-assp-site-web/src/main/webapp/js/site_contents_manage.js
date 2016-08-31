/**
 *  this js is for CMS function
 */
var ContentsManage = {
		mediaTypes:null,//视频类型
		mediaZtreeNodes:null,//树形ztreeObj集合
	    //初始化设置
	    initSettingBtn :function(){
	        	var base = this;
	        	base.$addContentBtn = $('.addContentBtn');
	        	base.$batchModifyBtn = $('.batchModifyBtn'); // 获取上方“批量修改”按钮Dom
				base.$setBtn = $('.setBtn'); // 获取上方“设置为”按钮”按钮Dom
				
				// 跳到添加文章页面（如需删除，可以删除）
				base.$addContentBtn.on('click', function() {
					$(parent.document).find('iframe').attr('src', $(this).attr('data-url'));
				});
				
				// 鼠标点击“批量修改”按钮
				base.$batchModifyBtn.on('click', function() {
					base.downContentCss(this);
				});

				// 鼠标点击“设置为”按钮
				base.$setBtn.on('click', function() {
					base.downContentCss(this);
				});
	    },
	    //初始化数据表格内js效果
	    initDataGridContentFun : function(){
	    	var base = this;
			base.$table = $('table'); // 获取tableDom
			base.$tablePic = base.$table.find('.pic'); // 获取table缩略图Dom
			
			// 鼠标指针位于缩略图元素上方
			base.$tablePic.on('mouseover', function() {
				ContentsManage.tablePicFun(this);
			});
			
			// checkbox全选
			ContentsManage.checkboxFun();
			
	    },
	    
	 //列表缩略图显示和隐藏
		tablePicFun: function(current) {
			var base = this,
				$this = $(current);

			$this.find('.picImg').show();
			base.$tablePic.last().find('.picImg').css({
				'top': '-60px'
			});
			$this.mouseout(function() {
				$this.find('.picImg').hide();
			});
		},
	    
	    //全选反选效果
		checkboxFun: function() {
			var base = this,
				$checkboxAll = base.$table.find('th input[type="checkbox"]'),
				$checkbox = base.$table.find('td input[type="checkbox"]');

			$checkboxAll.click(function() {
				$checkbox.prop('checked', function(i, val) {
					return !val;
				});
			});
			$checkbox.click(function() {
				$checkboxAll.attr('checked', $checkboxAll.length == base.$table.find('td input[type="checkbox"]:checkbox').length ? true : false);
			});
		},
	   //下拉框样式设置
	    downContentCss: function(current) {
			var $this = $(current);

			$this.parent().addClass('active').siblings().removeClass('active');

			$(document).click(function(e) {
				var target = $(e.target);
				if (target.closest($this.parent()).length == 0) {
					$this.parent().removeClass('active');
				}
			});
		},
	    
		 //行置顶
		setColumnTop : function(id){
			var obj = $(this);
			if(!obj.hasClass("btn-top-selected")){
				obj.addClass("btn-top-selected");
			}else{
				obj.removeClass("btn-top-selected");
			}
		},

		/**
		 *  根据试图类型返回不同的视频类型视图
		 * @param viewType
		 */
       drawMediaTypesDataView : function(viewType){
    	   $.ajax({
				url:rootPath+'/contents/media/queryMediaTypes.do',
				data:{dataStatus : 0},
				dataType:'json',
				async : true,
				success: function(data){ 
					$data = data.mediaTypes;
					//视频类型界面缓存
					ContentsManage.mediaTypes = $data; 
					
					
					if(viewType == 0){//表格
						//$("#mediaGrid").html( ContentsManage.drawMediaTypesTableView($data));
						ContentsManage.mediaZtreeNodes = ContentsManage.mediaTypeToZtree();
						$("#mediaGrid").html(ContentsManage.queryMediaTypeTableViewInTree(ContentsManage.mediaZtreeNodes));
						//创建树
						$customTreeTable.toTree('mediaGrid');
					}else if(viewType == 1){ 
						//此处一坑，编辑页面和列表页面都用此代码块儿
						//$("#mediaCkBoxList").html( ContentsManage.drawMediaTypesCkBoxView($data)); //复选框
						ContentsManage.mediaZtreeNodes = ContentsManage.mediaTypeToZtree();
						var dds = ContentsManage.drawMediaTypesCkBoxViewInTree(ContentsManage.mediaZtreeNodes);
						$("#queryMediaCKBoxType").html(dds);
						
						$("#mediaCkBoxList").html(dds);
					}else{
						$("#mediaSelect").append( ContentsManage.drawMediaTypesSelOptionView($data)); //下拉选框
					}
				}
		});
       },
       //(视频分类)视频树形化
       mediaTypeToZtree : function(){
    	   
    	   //只是当前的查询结果，若存在分页，则只能ajax获取所有的数据
    	   var treeDatas = $(ContentsManage.mediaTypes).map(function(){
    			var ztreeObj = new ZtreeObj(this.mediaTypeId,this.mediaTypePid,this.mediaTypeName);
    			if(ztreeObj.pId == void 0){
    				ztreeObj.pId = 0;
    			}
    			return ztreeObj;
    		});
    	   return $formatToZtree.getTreeNode(treeDatas);
       },
       //(视频分类)视频树形化表格
       queryMediaTypeTableViewInTree : function($data){
    	   var arr_ = $formatToZtree.getAllNodes($data);
    	   var resultHtml ="";
    	   $.each(arr_,function(i,ztreeNode){
    		   resultHtml +='<tr id="'+ztreeNode.id+'" pid="'+ztreeNode.pId+'">'
					+'<td class="tl tit pl40"> ' +ztreeNode.name+ ' </td>'
					+'<td>'
					+'<div class="btn-group">'
				   	+'<button type="button" class="btn btn-sm btn-default dropdown-toggle node-actions" data-toggle="dropdown" >操&nbsp;&nbsp;作<span class="caret"></span></button>'
					+'<ul class="dropdown-menu" style="font-size: 12px; min-width: 80px;">'
					+'<li><a class="btn-editor" href="javascript: editMediaType('+ztreeNode.id+' ,'+ztreeNode.pId+', \'重命名\');">重命名</a></li>'
					+'<li><a class="delBtn"  href="javascript: delMdiaType('+ztreeNode.id+');">删除</a></li>'
					+'</ul></div>'
					+'</td></tr>';
    	   });
    	   return resultHtml;
       },
     //复选框视频树形分类视图
       drawMediaTypesCkBoxViewInTree :function($data){
    	   var arr_ = $formatToZtree.getAllNodes($data);
    	   var resultHtml ="";
    	   $.each(arr_,function(i,ztreeNode){
    		   resultHtml +='<span class="checkbox" title="'+ztreeNode.name+'">'
    			   +'<input type="checkbox" name="mediaTypeId" value="'+ztreeNode.id+'">'+StringUtils.getStrInLen(ztreeNode.name,4)+'</span>';
    	   });
    	   return resultHtml;
       },
       //获取节点名称
       getNameById :function(id){
    	   var $data = ContentsManage.mediaTypes;
    	   for(var i=0;i<$data.length;i++){
    		   if($data[i].mediaTypeId==id)
    			   return $data[i].mediaTypeName;
    	   }
    	   return null;
       },
       //获取节点路径
       setPathByPId :function(pid,pathName,$data){
    	   for(var i=0;i<$data.length;i++){
    		   if($data[i].mediaTypeId==pid){
    			   pathName.push($data[i].mediaTypeName);
    			   ContentsManage.setPathByPId($data[i].mediaTypePid,pathName,$data);
    			   break;
    		   }
    	   }
       },
       //获取节点路径
       getPathByPId :function(pid){
    	   var pathNames = [];
    	   var $data = ContentsManage.mediaTypes;
    	   ContentsManage.setPathByPId(pid,pathNames,$data);
    	   pathNames = pathNames.reverse();
    	   return pathNames.join('_');
       },
       //表格视图
       drawMediaTypesTableView : function($data){
    		var resultHtml = "";
			 $.each($data ,function(i,mediaType){
				 resultHtml +='<tr id="'+mediaType.mediaTypeId+'" pid="'+mediaType.mediaTypePid+'">'
									+'<td class="tl tit pl40"> ' +mediaType.mediaTypeName+ ' </td>'
									+'<td>'
									+'<div class="btn-group">'
									+'<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" style="font-size: 12px; min-width: 80px;">操&nbsp;&nbsp;作<span class="caret"></span></button>'
									+'<ul class="dropdown-menu" style="font-size: 12px; min-width: 80px;">'
									+'<li><a class="btn-editor" href="javascript: editMediaType('+mediaType.mediaTypeId+' ,'+mediaType.mediaTypePid+', \'重命名\');">重命名</a></li>'
									+'<li><a class="delBtn"  href="javascript: delMdiaType('+mediaType.mediaTypeId+' );">删除</a></li>'
									+'</ul></div>'
									+'</td></tr>';
			 });
			 return resultHtml;
       },
       //复选框视图
       drawMediaTypesCkBoxView : function($data){
    	   var resultHtml = "";
			 $.each($data ,function(i,mediaType){
				 resultHtml +='  <span class="checkbox"><input type="checkbox" name="mediaTypeId" id="" value="'+mediaType.mediaTypeId+'">'+mediaType.mediaTypeName+' </span>';
			 });
			 return resultHtml;
       },
       //下拉框视图
       drawMediaTypesSelOptionView : function($data){
    	   var resultHtml = "";
    	   $.each($data ,function(i,mediaType){
    		     resultHtml +='<option value="'+mediaType.mediaTypeId+'">'+mediaType.mediaTypeName+' </option>';
			 });
			 return resultHtml;
       },
       //绘制图标类型
       drawMediaTypesIco : function(){
    	   var resultHtml = "";
    	   $.each($data ,function(i,mediaType){
    		     resultHtml +='<span class="mediasClass">'+mediaType.mediaTypeName+'<span class="del">×</span></span>';
			 });
			 return resultHtml;
       },
       //格式化名称
       formAtName : function(str,len){
    	   if(str == null ||  str == '') return '';
    	   var s = str;
    	   if(str.length > len){
    		   s = str.substring(0,len);
    	   }
    	   return s;
       }
		
};