/**
 * @author wangzhipeng@sxw100.com
 * function:查找图册分类
 * 	添加图册页面
 * 	管理图册页面的设置、模糊查询的分类
 * 	管理图册分类的展示
 */

var AlbumTypeQueryAll ={
		
		 albumTypes:null,//图册类型
		 albumZtreeNodes:null,//树形ztreeObj集合
		 //初始化设置
		  initSettingBtn :function(){
		        	var base = this;
		        	base.$addContentBtn = $('.addContentBtn');
		        	base.$batchModifyBtn = $('.batchModifyBtn'); // 获取上方“批量修改”按钮Dom
					base.$setBtn = $('.setBtn'); // 获取上方“设置为”按钮”按钮Dom
					
					// 跳到添加图册页面（如需删除，可以删除）
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
				// checkbox全选
				AlbumTypeQueryAll.checkboxFun();
				
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
		 //根据视图选择类型返回相应的视图
		 drawAlbumTypesDataView : function(albumType){
	    	   $.ajax({
					url:rootPath+'/contents/album/queryAlbumTypes.do',
					dataType:'json',
					async : false,
					success: function(data){
						$data =  data.albumTypes; 
						//图册类型界面缓存
						AlbumTypeQueryAll.albumTypes = $data; 
						
						if(albumType == 0){//下拉列表
							$("#albumTypeId").append(AlbumTypeQueryAll.drawAlbumTypesOptionView($data));
						}else if(albumType == 1){//复选框
							//$("#queryAlbumCKBoxType").html(AlbumTypeQueryAll.drawAlbumTypesCkBoxView($data));
							AlbumTypeQueryAll.albumZtreeNodes = AlbumTypeQueryAll.albumTypeToZtree();
							$("#queryAlbumCKBoxType").html(AlbumTypeQueryAll.drawAlbumTypesCkBoxViewInTree(AlbumTypeQueryAll.albumZtreeNodes));
						}else{//表格
							//$("#albumTypeGrid").html(AlbumTypeQueryAll.queryAlbumTypeTableView($data));
							AlbumTypeQueryAll.albumZtreeNodes = AlbumTypeQueryAll.albumTypeToZtree();
							$("#albumTypeGrid").html(AlbumTypeQueryAll.queryAlbumTypeTableViewInTree(AlbumTypeQueryAll.albumZtreeNodes));
							//创建树
							$customTreeTable.toTree('albumTypeGrid');
						}
					}
					});
	       },
	       //(图册分类)图册树形化
	       albumTypeToZtree : function(){
	    	   
	    	   //只是当前的查询结果，若存在分页，则只能ajax获取所有的数据
	    	   var treeDatas = $(AlbumTypeQueryAll.albumTypes).map(function(){
	    			return new ZtreeObj(this.albumTypeId,this.albumTypePid,this.albumTypeName);
	    		});
	    	   return $formatToZtree.getTreeNode(treeDatas);
	       },
	       //(图册分类)图册树形化表格
	       queryAlbumTypeTableViewInTree : function($data){
	    	   var arr_ = $formatToZtree.getAllNodes($data);
	    	   var resultHtml ="";
	    	   $.each(arr_,function(i,ztreeNode){
	    		   resultHtml +='<tr id="'+ztreeNode.id+'" pid="'+ztreeNode.pId+'">'
						+'<td class="tl tit pl40"> ' +ztreeNode.name+ ' </td>'
						+'<td>'
						+'<div class="btn-group">'
						+'<button type="button" class="btn btn-sm btn-default dropdown-toggle node-actions" data-toggle="dropdown" >操&nbsp;&nbsp;作<span class="caret"></span></button>'
						+'<ul class="dropdown-menu" style="font-size: 12px; min-width: 80px;">'
						+'<li><a class="btn-editor" href="javascript: editAlbumType('+ztreeNode.id+' ,'+ztreeNode.pId+', \'重命名\');">重命名</a></li>'
						+'<li><a class="delBtn"  href="javascript: delAlbumType('+ztreeNode.id+',0 );">删除</a></li>'
						+'</ul></div>'
						+'</td></tr>';
	    	   });
	    	   return resultHtml;
	       },
	       //(图册分类)图册类型名称
	       albumTypeNameFormat : function(albumTypeId){
	    	   if(AlbumTypeQueryAll.albumTypes != null){
	    		   var resultHtml ="";
	    		   for(var i =0; i<AlbumTypeQueryAll.albumTypes.length; i++){
	    			   if(AlbumTypeQueryAll.albumTypes[i].albumTypeId == albumTypeId){
	    				   resultHtml = AlbumTypeQueryAll.albumTypes[i].albumTypeName;
	    			   }
	    		   }
	    		   return resultHtml;
	    	   }
	       },
	       //下拉列表图册分类视图
	       drawAlbumTypesOptionView : function($data){
	    	   var resultHtml = "";
	    	   $.each($data,function(i,albumType){
	    		   resultHtml +='<option value="'+albumType.albumTypeId+'">'+albumType.albumTypeName+'</option>';
	    	   });
	    	   return resultHtml;
	       },
	       //复选框图册树形分类视图
	       drawAlbumTypesCkBoxViewInTree :function($data){
	    	   var arr_ = $formatToZtree.getAllNodes($data);
	    	   var resultHtml ="";
	    	   $.each(arr_,function(i,ztreeNode){
	    		   resultHtml +='<span class="checkbox">'
	    			   +'<input type="checkbox" name="albumTypeId" value="'+ztreeNode.id+'">'+ztreeNode.name+'</span>';
	    	   });
	    	   return resultHtml;
	       },
	       //复选框图册分类视图
	       drawAlbumTypesCkBoxView :function($data){
	    	   var resultHtml ="";
	    	   $.each($data,function(i,albumType){
	    		   resultHtml +='<span class="checkbox">'
							  +'<input type="checkbox" name="albumTypeId" value="'+albumType.albumTypeId+'">'+albumType.albumTypeName+'</span>';
	    	   });
	    	   return resultHtml;
	       },
	       //获取节点名称
	       getNameById :function(id){
	    	   var $data = AlbumTypeQueryAll.albumTypes;
	    	   for(var i=0;i<$data.length;i++){
	    		   if($data[i].albumTypeId==id)
	    			   return $data[i].albumTypeName;
	    	   }
	    	   return null;
	       },
	       //获取节点路径
	       setPathByPId :function(pid,pathName,$data){
	    	   for(var i=0;i<$data.length;i++){
	    		   if($data[i].albumTypeId==pid){
	    			   pathName.push($data[i].albumTypeName);
	    			   AlbumTypeQueryAll.setPathByPId($data[i].albumTypePid,pathName,$data);
	    			   break;
	    		   }
	    	   }
	       },
	       //获取节点路径
	       getPathByPId :function(pid){
	    	   var pathNames = [];
	    	   var $data = AlbumTypeQueryAll.albumTypes;
	    	   AlbumTypeQueryAll.setPathByPId(pid,pathNames,$data);
	    	   pathNames = pathNames.reverse();
	    	   return pathNames.join('_');
	       },
	       //表格tr/td形式的图册分类视图	
	       queryAlbumTypeTableView : function($data){
	    	   var resultHtml ="";
	    	   $.each($data,function(i,albumType){
	    		   resultHtml +='<tr id="'+albumType.albumTypeId+'" pid="'+albumType.albumTypePid+'">'
						+'<td class="tl tit pl40"> ' +albumType.albumTypeName+ ' </td>'
						+'<td>'
						+'<div class="btn-group">'
						+'<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" style="min-width: 80px;">操&nbsp;&nbsp;作<span class="caret"></span></button>'
						+'<ul class="dropdown-menu" style="font-size: 12px; min-width: 80px;">'
						+'<li><a class="btn-editor" href="javascript: editAlbumType('+albumType.albumTypeId+' ,'+albumType.albumTypePid+', \'重命名\');">重命名</a></li>'
						+'<li><a class="delBtn"  href="javascript: delArtilceType('+albumType.albumTypeId+',0 );">【删除】</a></li>'
						+'</ul></div>'
						+'</td></tr>';
	    	   });
	    	   return resultHtml;
	       },
	       //绘制图标类型
	       drawAlbumTypesIco : function(){
	    	   var resultHtml = "";
	    	   $.each($data ,function(i,albumType){
	    		     resultHtml +='<span class="albumsClass">'+albumType.albumTypeName+'<span class="del">×</span></span>';
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