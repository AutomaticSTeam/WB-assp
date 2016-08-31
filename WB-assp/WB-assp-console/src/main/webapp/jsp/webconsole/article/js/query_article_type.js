/**
 * @author wangkang@sxw100.com
 * function:查找文章分类
 * 	添加文章页面
 * 	管理文章页面的设置、模糊查询的分类
 * 	管理文章分类的展示
 */

var ArticleTypeQueryAll ={
		
		 articleTypes:null,//文章类型
		 articleZtreeNodes:null,//树形ztreeObj集合
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
					ArticleTypeQueryAll.tablePicFun(this);
				});
				
				// checkbox全选
				ArticleTypeQueryAll.checkboxFun();
				
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
		 //根据视图选择类型返回相应的视图
		 drawArticleTypesDataView : function(articleType){
	    	   $.ajax({
					url:rootPath+'/contents/article/queryArticleTypes.do',
					data:{dataStatus : 0},
					dataType:'json',
					async : true,
					success: function(data){
						$data =  data.articleTypes;
						ArticleTypeQueryAll.articleTypes = $data;  //文章类型界面缓存
						
						if(articleType == 0){//下拉列表
							$("#articleTypeId").append(ArticleTypeQueryAll.drawArticleTypesOptionView($data));
						}else if(articleType == 1){//复选框
							//$("#queryArticleCKBoxType").html(ArticleTypeQueryAll.drawArticleTypesCkBoxView($data));
							ArticleTypeQueryAll.articleZtreeNodes = ArticleTypeQueryAll.articleTypeToZtree();
							$("#queryArticleCKBoxType").html(ArticleTypeQueryAll.drawArticleTypesCkBoxViewInTree(ArticleTypeQueryAll.articleZtreeNodes));
						}else{//表格
						//	$("#articleTypeGrid").html(ArticleTypeQueryAll.queryArticleTypeTableView($data));
							ArticleTypeQueryAll.articleZtreeNodes = ArticleTypeQueryAll.articleTypeToZtree();
							$("#articleTypeGrid").html(ArticleTypeQueryAll.queryArticleTypeTableViewInTree(ArticleTypeQueryAll.articleZtreeNodes));
							//创建树
							$customTreeTable.toTree('articleTypeGrid');
						}
					}
					});
	       },
	     //(文章分类)文章树形化
	       articleTypeToZtree : function(){
	    	   
	    	   //只是当前的查询结果，若存在分页，则只能ajax获取所有的数据
	    	   var treeDatas = $(ArticleTypeQueryAll.articleTypes).map(function(){
	    			return new ZtreeObj(this.articleTypeId,this.articleTypePid,this.articleTypeName);
	    		});
	    	   return $formatToZtree.getTreeNode(treeDatas);
	       },
	       //(文章分类)文章类型名称
	       articleTypeNameFormat : function(articleTypeId){
	    	   if(ArticleTypeQueryAll.articleTypes != null){
	    		   var resultHtml ="";
	    		   for(var i =0; i<ArticleTypeQueryAll.articleTypes.length; i++){
	    			   if(ArticleTypeQueryAll.articleTypes[i].articleTypeId == articleTypeId){
	    				   resultHtml = ArticleTypeQueryAll.articleTypes[i].articleTypeName;
	    			   }
	    		   }
	    		   return resultHtml;
	    	   }
	       },
	       //下拉列表文章分类视图
	       drawArticleTypesOptionView : function($data){
	    	   var resultHtml = "";
	    	   $.each($data,function(i,articleType){
	    		   resultHtml +='<option value="'+articleType.articleTypeId+'">'+articleType.articleTypeName+'</option>';
	    	   });
	    	   return resultHtml;
	       },
	       //复选框文章树形分类视图
	       drawArticleTypesCkBoxViewInTree :function($data){
	    	   //console.log(window.location.pathname + "----" + window.location.href );
	    	   var arr_ = $formatToZtree.getAllNodes($data);
	    	   var resultHtml ="";
	    	   $.each(arr_,function(i,ztreeNode){
	    		   resultHtml +='<span class="checkbox" title="'+ztreeNode.name+'">'
	    			   +'<input type="checkbox" name="articleTypeId" value="'+ztreeNode.id+'">'+StringUtils.getStrInLen(ztreeNode.name,4)+'</span>';
	    	   });
	    	   return resultHtml;
	       },
	       //复选框文章分类视图
	       drawArticleTypesCkBoxView :function($data){
	    	   var resultHtml ="";
	    	   $.each($data,function(i,articleType){
	    		   resultHtml +='<span class="checkbox">'
							  +'<input type="checkbox" name="articleTypeId" value="'+articleType.articleTypeId+'">'+articleType.articleTypeName+'</span>';
	    	   });
	    	   return resultHtml;
	       },
	       //(文章分类)文章树形化表格
	       queryArticleTypeTableViewInTree : function($data){
	    	   var arr_ = $formatToZtree.getAllNodes($data);
	    	   var resultHtml ="";
	    	   $.each(arr_,function(i,ztreeNode){
	    		   resultHtml +='<tr id="'+ztreeNode.id+'" pid="'+ztreeNode.pId+'">'
						+'<td class="tl tit pl40"> ' +ztreeNode.name+ ' </td>'
						+'<td>'
						+'<div class="btn-group">'
						+'<button type="button" class="btn btn-sm btn-default dropdown-toggle node-actions" data-toggle="dropdown" >操&nbsp;&nbsp;作<span class="caret"></span></button>'
						+'<ul class="dropdown-menu" style="font-size: 12px; min-width: 80px;">'
						+'<li><a class="btn-editor" href="javascript: editArticleType('+ztreeNode.id+' ,'+ztreeNode.pId+', \'重命名\');">重命名</a></li>'
						+'<li><a class="delBtn"  href="javascript: delArtilceType('+ztreeNode.id+' );">删除</a></li>'
						+'</ul></div>'
						+'</td></tr>';
	    	   });
	    	   return resultHtml;
	       },
	     //获取节点名称
	       getNameById :function(id){
	    	   var $data = ArticleTypeQueryAll.articleTypes;
	    	   for(var i=0;i<$data.length;i++){
	    		   if($data[i].articleTypeId==id)
	    			   return $data[i].articleTypeName;
	    	   }
	    	   return null;
	       },
	       //获取节点路径
	       setPathByPId :function(pid,pathName,$data){
	    	   for(var i=0;i<$data.length;i++){
	    		   if($data[i].articleTypeId==pid){
	    			   pathName.push($data[i].articleTypeName);
	    			   ArticleTypeQueryAll.setPathByPId($data[i].articleTypePid,pathName,$data);
	    			   break;
	    		   }
	    	   }
	       },
	       //获取节点路径
	       getPathByPId :function(pid){
	    	   var pathNames = [];
	    	   var $data = ArticleTypeQueryAll.articleTypes;
	    	   ArticleTypeQueryAll.setPathByPId(pid,pathNames,$data);
	    	   pathNames = pathNames.reverse();
	    	   return pathNames.join('_');
	       },
	       //表格tr/td形式的文章分类视图	
	       queryArticleTypeTableView : function($data){
	    	   var resultHtml ="";
	    	   $.each($data,function(i,articleType){
	    		   resultHtml +='<tr id="'+articleType.articleTypeId+'" pid="'+articleType.articleTypePid+'">'
						+'<td class="tl tit pl40"> ' +articleType.articleTypeName+ ' </td>'
						+'<td>'
						+'<div class="btn-group">'
						+'<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" style="min-width: 80px;">【操&nbsp;&nbsp;作】<span class="caret"></span></button>'
						+'<ul class="dropdown-menu" style="font-size: 12px; min-width: 80px;">'
						+'<li><a class="btn-editor" href="javascript: editArticleType('+articleType.articleTypeId+' ,'+articleType.articleTypePid+', \'重命名\');">重命名</a></li>'
						+'<li><a class="delBtn"  href="javascript: delArtilceType('+articleType.articleTypeId+' );">删除</a></li>'
						+'</ul></div>'
						+'</td></tr>';
	    	   });
	    	   return resultHtml;
	       },
	       //绘制图标类型
	       drawArticleTypesIco : function(){
	    	   var resultHtml = "";
	    	   $.each($data ,function(i,articleType){
	    		     resultHtml +='<span class="articlesClass">'+articleType.articleTypeName+'<span class="del">×</span></span>';
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