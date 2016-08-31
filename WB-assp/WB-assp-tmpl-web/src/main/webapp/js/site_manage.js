
if(typeof ROOTPATH=="undefined"){ROOTPATH=getRootPath();}

var SiteManager = {
	// 模块操作按钮2个“编辑属性和隐藏模块”
	moduleOperationBtn:	'<div class="operation"><div class="operation-box"><a class="btn btn-blue white dib lh1 editBtn editMouduleBtn">编辑属性</a></div></div>',
	// 文章,视频，图册 列表模块每条数据操作按钮2个“编辑和删除”
	contentOperationBtn:'<div class="operation01"><div class="operation-box"><a title="编辑" class="btn-edit">编辑</a><a title="删除" class="btn-del">删除</a><a title="置顶" class="btn-top">置顶</a></div></div>',
	// 获取frame排版
	$frameArray:null,
	// 获取所有模块儿body
	$moduleArray:null,
	// 顶级页保存时 数据（{url:url,data:data}）组
	moduleDatas:[],
	// 临时文件以供编辑框使用
	tempData:{},
	// 弹出句柄
	handers:{},
	// 顶级页面是否被修改，默认false
	isModified: false,
	// 监听是否有保存操作条件发生的线程id
	saveBtnListenerId: null,
	// 监听刷新条件发生的线程id
	refreshBtnListenerId: null,
	// 欲执行module的id数
	prepareModuleIds:[],
	// 已执行module的id数
	completeModuleIds:[],
	// 初始化标记，默认false
	initFlg: false,

	/**
	 * 站点编辑类 初始化方法
	 * */	
	init: function() {
		small();
		this.$moduleArray = $('.model-body'); 
		this.attachEvents();
		
		//监听页面修改状态
		this.listenSaveBtn();
	},
	/**
	 * 站点编辑类 模块初始化方法
	 * */	
	initModule: function(modules) {
		small();
		this.$moduleArray = modules; 
		this.attachEvents();
		
		//监听页面修改状态
		this.listenSaveBtn();
	},
	/**
	 * 添加属性编辑按钮
	 * */	
	attachEvents: function() {
		var base = this;
		
		// 模块儿添加属性按钮
		base.addModuleOperationBtn(base.$moduleArray);
		// 模块儿属性按钮添加事件
		base.$moduleArray.find('.operation .operation-box .editMouduleBtn').on('click', function() {
			base.editMouduleBtn(this); 
		});
		// 扩展按钮
		base.$moduleArray.find('.operation .operation-box .extendMouduleBtn').on('click', function() {
			base.extendMouduleBtn(this); 
		});
		
		
		// 给文章，视频，图册增加属性按钮
		base.addContentOperationDetail();
		
		// 文章，视频，图册属性按钮添加事件（编辑）
		base.$moduleArray.find('.operation01 .operation-box .btn-edit').on('click', function() {
			base.editBtn(this); 
		});
		//文章，视频，图册属性按钮添加事件（删除）
		base.$moduleArray.find('.operation01 .operation-box .btn-del').on('click', function() {
			base.delBtn(this); 
		});
		//文章，视频，图册属性按钮添加事件（置顶）
		base.$moduleArray.find('.operation01 .operation-box .btn-top').on('click', function() {
			base.setTopBtn(this); 
		});
		
		// 添加添加模块点击事件
		base.$moduleArray.find('.modelAdd').on('click', function() {
			base.addMouduleBtn(this);
		});
	},
	/**
	 * 添加模块事件
	 * */
	addMouduleBtn: function(editBtn) {
		var $module = $(".model-body").has(editBtn);
		var id = $module.attr("id").replace("module","");
		SiteManager.tempData.dataUrl = id;
		layer.open({  
            shade: [0.2, '#000', false],  
            type: 1,  
            area: ['300px', '200px'],  
            fix: false, //不固定  
            maxmin: true,  
            title: ['模块类型', false],  
            content: $("#modelAdd_tip"),//base.rootPath + '/jsp/website/manage/logoFun.jsp' //,
            scrollbar:false,//是否允许滚动条出现
			  //按钮与回调
			btn: ['确认', '取消'],
			yes: function(index, layero){
				SiteManager.tempData.dataType = $("#modelAddType").val();
				SiteManager.moduleFun(SiteManager.tempData.dataUrl,SiteManager.tempData.dataType);
				layer.close(index);
			},
			cancel: function(index){
				return true;
			}
			}); 
	},
	/**
	 * 模块编辑事件
	 * */
	editMouduleBtn: function(editBtn) {
		var $module = $("div[data-model]").has(editBtn);
		var dataShow = $module.attr("data-show");
		var dataType = $module.attr("data-type");
		var dataUrl = $module.attr("data-url");
		if(dataShow == void 0){
			return;
		}
		
		//付临时数据
		this.tempData.dataUrl = dataUrl;
		this.tempData.dataType = dataType;
		switch(dataShow){
		case "module_logo_tmpl":
			this.logoFun(dataUrl);
			break;
		case "module_nav_tmpl":
			this.navFun(dataUrl);
			break;
		case "module_footer_tmpl":
			this.footerFun($module);
			break;
		case "module_article_tmpl":
			this.contentArticle(dataUrl);
			break;
		case "module_media_tmpl":
			this.contentMedia(dataUrl);
			break;
		case "module_foucs_thumbnail_tmpl":
		case "module_banner_tmpl":
		case "module_foucs_pictures_tmpl":
			this.focusFun(dataUrl);
			break;
		/*case "module_foucs_productinfo_list_tmpl":
			this.focusPictureFun(dataUrl);
			break;*/
		//moduleFun 这个方法控制页面中 文章 视频 图册 的编辑属性
		default: 
			this.moduleFun(dataUrl,dataType);
		}
		
	},
	
	/**
	 * 文章，视频，图册编辑事件
	 * */
	editBtn: function(editBtn) {
		
		var $li = $(editBtn).parents("li");
		// 对象类型 图片、文章、视频
		var dataType = $li.attr("data-type");
		// 对象id 图片、文章、视频
		var dataId = $li.attr("data-url");
		
		// 跳到相应方法
		switch (dataType) {
		
			case "picture" :
				SiteManager.pictureFun(dataId);
				break;
			case "article" :
				SiteManager.articleFun(dataId);
				break;
			case "media" :
				SiteManager.mediaFun(dataId);
				break;
			
		
		}
		
	},
	/**
	 * 文章，视频，图册删除事件
	 */
	delBtn: function(delBtn) {
				
				var $li = $(delBtn).parents("li");
				// 对象类型 图片、文章、视频
				var dataType = $li.attr("data-type");
				// 对象id 图片、文章、视频
				var dataId = $li.attr("data-url");
				
				// 跳到相应方法
				switch (dataType) {
				
					case "picture" :
						SiteManager.pictureDel(dataId);
						break;
					case "article" :
						SiteManager.articleDle(dataId);
						break;
					case "media" :
						SiteManager.mediaDel(dataId);
						break;
				}
				
	},
	/**
	 * 文章，视频，图册置顶事件
     */
	setTopBtn: function(setTopBtn) {
						
		var $li = $(setTopBtn).parents("li");
		// 对象类型 图片、文章、视频
		var dataType = $li.attr("data-type");
		// 对象id 图片、文章、视频
		var dataId = $li.attr("data-url");
						
		// 跳到相应方法
		switch (dataType) {
						
			case "picture" :
				SiteManager.pictureTop(dataId);
				break;
			case "article" :
				SiteManager.articleTop(dataId);
				break;
			case "media" :
				SiteManager.mediaTop(dataId);
				break;
		}
						
	},
	/**
	 * 模块模块儿扩展编辑事件
	 * */
	extendMouduleBtn: function(editBtn) {
		// 扩展类型
		var dataType = $(editBtn).attr("data-type");
		var $module = $("div[data-model]").has(editBtn);
		var dataShow = $module.attr("data-show");
		var dataUrl = $module.attr("data-url");
		var moduleType = $module.attr("data-type");
		if(dataType == void 0){
			return;
		}
		
		//付临时数据
		this.tempData.dataUrl = dataUrl;
		this.tempData.dataType = moduleType;
		switch(dataType){
			case "addArticle" ://添加文章按钮
				SiteManager.articleAdd();
				break;
			case "addMedia" ://添加文章按钮
				SiteManager.mediaAdd();
				break;
			case "addPictureAlbum" ://添加图册按钮
				SiteManager.pictureAlbumAdd();
				break;
		}
	},
	/**
	 * 细化按钮，给文章，视频，图册增加属性按钮
	 * */
	addContentOperationDetail: function() {
		var base = this;
		// logo
		base.$moduleArray.find('[data-model="logo"]').addClass('operationTop');

		//图文
		base.$moduleArray.find('[data-model="imgText"]').on('mouseover', function() {
			var $this = $(this);

			if ($this.height() < $this.find('.operation-box').height()) {
				$this.find('.operation').addClass('operationTop');
			}
		});
		
		

		//文章管理每条数据
		base.addContentOperationBtn('[data-model="list"]  li');

		base.addContentOperationBtn('[data-model="product"] li');
		
		//网站底部
		base.$moduleArray.find('[data-model="footer"] .operation').height($('[data-model="footer"]').find('.operation').height() - 2);
	},

	/**保存
	 * **/
	save: function(){
		
		for(var i = 0;i < SiteManager.moduleDatas.length; i++){
			//欲执行的数据记录
			SiteManager.prepareModuleIds.push(SiteManager.moduleDatas[i].id);
			try{
				//开始处理数据
				SiteManager.requestJson(SiteManager.moduleDatas[i]);
			}catch(e){
				
			}
		}
		
		//complete之后刷新
		if(SiteManager.prepareModuleIds.length>0){
			$(".btn-website-edit").hide();
			$(".btn-website-process").show();
			SiteManager.refreshBtnListenerId = self.setInterval("SiteManager.refresh()",500);
		}
		
	},
	/**保存之后做刷新
	 * **/
	refresh:function(){
		if(SiteManager.completeModuleIds.length==SiteManager.prepareModuleIds.length){
			window.clearInterval(SiteManager.refreshBtnListenerId);
			window.location.reload();
		}
	},
	/**取消
	 * **/
	cancel: function(){
		
		window.location.reload();
	},
	/**请求
	 * **/
	requestJson: function(moduleData){
		$.ajax({
			type : 'post',
			url : moduleData.url,
			data :  moduleData.data,
			dataType : 'json',
			async  :  true,
			traditional : true,
			success : function(data){ 
				
				SiteManager.afterRequest(moduleData.id,data)
				
			 },
			error:function(data){
				//alert(JSON.stringify(data));
			}
	     });
	},
	/**处理请求callback
	 * **/
	afterRequest: function(id,data){
		//已处理完成的做记录
		SiteManager.completeModuleIds.push(id);
		//do.....
	},
	
	// 初始化btn
	listenSaveBtn: function(){
		
		if(SiteManager.moduleDatas.length > 0){
			$(".btn-website-edit").show();
			if(SiteManager.saveBtnListenerId != null){
				window.clearInterval(SiteManager.saveBtnListenerId);
				SiteManager.saveBtnListenerId = null;
			}
		}else if(SiteManager.saveBtnListenerId == null){
			
			$(".btn-website-edit").hide();
			SiteManager.saveBtnListenerId = self.setInterval("SiteManager.listenSaveBtn()",1000);
			
		}
		
	},
	// 添加模块儿数据
	addModuleData: function(obj){
		var cm_ = SiteManager.hasModuleData(obj);
		//有先删除
		if(cm_ != -1){
			SiteManager.removeModuleData(cm_);
		}
		
		SiteManager.moduleDatas.push(obj);	
	},
	// 判断是否含有
	hasModuleData: function(obj){
		
		for(var i=0;i<SiteManager.moduleDatas.length;i++){
			if(SiteManager.moduleDatas[i].id==obj.id){
				return i;
			}
		}
		
		return -1;
	},
	// 删除
	removeModuleData:function(index){
		
		SiteManager.moduleDatas.splice(index,1);
		
	},
	// 删除欲保存的数据用标价id
	removeModuleDataById: function(id){
		
		for(var i=0;i<SiteManager.moduleDatas.length;i++){
			if(SiteManager.moduleDatas[i].id==id){
				SiteManager.moduleDatas.splice(i,1);
				break;
			}
		}
		
	},
	// ==============================操作==============================
	// 一般module弹出框
	moduleFun: function(dataUrlId,dataTypeId){
		var base = this;
		layer.open({  
            shade: [0.2, '#000', false],  
            type: 2,  
            area: ['800px', '500px'],  
            fix: false, //不固定  
            maxmin: true,  
            title: ['编辑模块', false],  
            content: ROOTPATH + '/site/selectTemplateModule.do?moduleId='+dataUrlId,//base.rootPath + '/jsp/website/manage/logoFun.jsp' //,
            scrollbar:false,//是否允许滚动条出现
			  //按钮与回调
			btn: ['确认', '取消'],
			yes: function(index, layero){
				  if(SiteManager.handers.moduleSubmit){
					  SiteManager.handers.moduleSubmit.save();
				  }
//				  layer.close(index);
			},
			btn2: function(index, layero){
				//取消操作
				SiteManager.removeModuleDataById("moduleFun");
				SiteManager.listenSaveBtn();
			},
			cancel: function(index){
				//取消操作
				SiteManager.removeModuleDataById("moduleFun");
				SiteManager.listenSaveBtn();
				return true;
			}
			}); 
		
   },
  	// logo网站标志弹出框
	logoFun: function(current){
		var base = this;
		layer.open({  
            shade: [0.2, '#000', false],  
            type: 2,  
            area: ['474px', '355px'],  
            fix: false, //不固定  
            maxmin: true,  
            title: ['编辑logo', false],  
            content: ROOTPATH + '/jsp/website/manage/pop/logoPop.jsp',
            scrollbar:false,//是否允许滚动条出现
			  //按钮与回调
			btn: ['确认', '取消'],
			yes: function(index, layero){
				  layer.close(index);
			},
			btn2: function(index, layero){
				//取消操作
				SiteManager.removeModuleDataById("logoFun");
				SiteManager.listenSaveBtn();
			},
			cancel: function(index){
				//取消操作
				SiteManager.removeModuleDataById("logoFun");
				SiteManager.listenSaveBtn();
				return true;
			}
			}); 
		
   },
   // 导航编辑
   navFun: function(current){
	   var base = this;
	   base.tempData.id = "navFun";
	   layer.open({  
		   shade: [0.2, '#000', false],  
		   type: 2,  
		   area: ['660px', '515px'],  
		   fix: false, //不固定  
		   maxmin: true,  
		   title: ['编辑导航', false],  
		   content: ROOTPATH + '/jsp/website/manage/pop/navPop.jsp',
		   scrollbar:false,//是否允许滚动条出现
		   //按钮与回调
		   btn: ['保存', '取消'],
		   yes: function(index, layero){
			   
			   if(SiteManager.handers.navFun){
				   SiteManager.handers.navFun.save();
			   }
			   
			   layer.close(index);
		   },
		   cancel: function(index){
			   return true;
		   }
	   }); 
	   
   },
   // focus图弹出框
   focusFun: function(current){
		var base = this;
		layer.open({  
           shade: [0.2, '#000', false],  
           type: 2,  
           area: ['660px', '515px'],  
           fix: false, //不固定  
           maxmin: true,  
           title: ['编辑focus图', false],  
           content: ROOTPATH + '/jsp/website/manage/pop/focusPop.jsp',
           scrollbar:false,//是否允许滚动条出现
			  //按钮与回调
			btn: ['确认', '取消'],
			yes: function(index, layero){
				
				  if(SiteManager.handers.focusFun){
					  SiteManager.handers.focusFun.synData();
				  }
				  layer.close(index);
			},
			btn2: function(index, layero){
				//取消操作
				SiteManager.removeModuleDataById("focusFun");
				SiteManager.listenSaveBtn();
			},
			cancel: function(index){
				//取消操作
				SiteManager.removeModuleDataById("focusFun");
				SiteManager.listenSaveBtn();
				return true;
			}
			}); 
		
  },
  
	// 页面中图层编辑按钮
	pictureFun: function(current){
		$.ajax({
			url:rootPath+"/site/queryPictureIdFindAlbumId.do",
			data:{pictureId:current},
			dataType:"json",
			type:"post",
			success:function(data){
				var albumId=data.albumId;
				if(albumId!=""){
					//付临时数据
					layer.open({  
			            shade: [0.2, '#000', false],  
			            type: 2,  
			            area: ['1000px', '500px'],  
			            fix: false, //不固定  
			            maxmin: true,  
			            title: ['编辑文章', false],  
			            content: ROOTPATH + '/jsp/website/manage/pop/addPicturePop.jsp?ablumId='+albumId,
			            scrollbar:false,//是否允许滚动条出现
						}); 
				}
			},
			error:function(){
				
			}
		})
		
	},
	//删除
	pictureDel : function(current){
		console.log("图册删除...");
	},
	//置顶
	pictureTop : function(current){
		console.log("图册置顶...");
	},
	//页面中图册添加文章
	pictureAlbumAdd : function(){
		layer.open({  
			   shade: [0.2, '#000', false],  
			   type: 2,  
			   area: ['1000px', '515px'],  
			   fix: false, //不固定  
			   maxmin: true,  
			   title: ['图册添加', false],  
			   content: ROOTPATH + '/jsp/website/manage/pop/addPicturePop.jsp',
			   scrollbar:false,//是否允许滚动条出现
			   //按钮与回调
			   /*btn: ['保存', '取消'],
			   yes: function(index, layero){
				   
				   if(SiteManager.handers.navFun){
					   SiteManager.handers.navFun.save();
				   }
				   
				   layer.close(index);
			   },
			   cancel: function(index){
				   return true;
			   }*/
		   }); 
	},
	// 文章弹出框(编辑)
	articleFun: function(current){
		//console.log("文章...");
		var base = this;
		//付临时数据
		this.tempData.layerIndex = layer.open({  
            shade: [0.2, '#000', false],  
            type: 2,  
            area: ['1000px', '500px'],  
            fix: false, //不固定  
            maxmin: true,  
            title: ['编辑文章', false],  
            content: ROOTPATH + '/site/toArticleEditOrView.do?articleId='+current,
            scrollbar:false,//是否允许滚动条出现
			}); 
	},
			//删除
	articleDle:function(current){
		layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
			 bathEditArticle({dataStatus : 1 , articleIdsStr : current }, '删除');
			 //删除数据
			  layer.close(index);
		});
	},
			//置顶
	articleTop:function(current){
		layer.confirm('您确定置顶当前数据', {icon: 3, title:'提示'}, function(index){
			bathEditArticle({articleTop : 1 , articleIdsStr : current } , '置顶');
			 //确认置顶
			  layer.close(index);
		});
	},
	//新增文章
	articleAdd: function(){
		   layer.open({  
			   shade: [0.2, '#000', false],  
			   type: 2,  
			   area: ['1000px', '515px'],  
			   fix: false, //不固定  
			   maxmin: true,  
			   title: ['文章添加', false],  
			   content: ROOTPATH + '/jsp/website/manage/pop/addArticlePop.jsp',
			   scrollbar:false,//是否允许滚动条出现
			   //按钮与回调
			   /*btn: ['保存', '取消'],
			   yes: function(index, layero){
				   
				   if(SiteManager.handers.navFun){
					   SiteManager.handers.navFun.save();
				   }
				   
				   layer.close(index);
			   },
			   cancel: function(index){
				   return true;
			   }*/
		   }); 
		   
	   },
	// 视频弹出框（编辑）
	mediaFun: function(current){
		var base = this;
		//付临时数据
		this.tempData.layerIndex = layer.open({  
            shade: [0.2, '#000', false],  
            type: 2,  
            area: ['1000px', '500px'],  
            fix: false, //不固定  
            maxmin: true,  
            title: ['编辑视频', false],  
            content: ROOTPATH + '/site/toMediaEditOrView.do?mediaId='+current,
            scrollbar:false,//是否允许滚动条出现
			}); 
	},
	//删除
	mediaDel:function(current){
		layer.confirm('您确定删除当前数据', {icon: 3, title:'提示'}, function(index){
			batchEditMedia({dataStatus : 1 , mediaIdsStr : current }, '删除');
			 //删除数据
			  layer.close(index);
		});
	},
			//置顶
	mediaTop:function(current){
		layer.confirm('您确定置顶当前数据', {icon: 3, title:'提示'}, function(index){
			batchEditMedia({isTop : 1 , mediaIdsStr : current } , '置顶');
			 //确认置顶
			  layer.close(index);
		});
	},
			//页面中新增视频
	mediaAdd:function(){
		 var base = this;
		  var moduleID= this.tempData.dataUrl;
		   layer.open({  
			   shade: [0.2, '#000', false],  
			   type: 2,  
			   area: ['1000px', '515px'],  
			   fix: false, //不固定  
			   maxmin: true,  
			   title: ['文章视频', false],  
			   content: ROOTPATH + '/jsp/website/manage/pop/addMediaPop.jsp',
			   scrollbar:false,//是否允许滚动条出现
			   //按钮与回调
			   /*btn: ['保存', '取消'],
			   yes: function(index, layero){
				   
				   if(SiteManager.handers.navFun){
					   SiteManager.handers.navFun.save();
				   }
				   
				   layer.close(index);
			   },
			   cancel: function(index){
				   return true;
			   }*/
		   }); 
	},
	//添加分类
	
	// footer站脚弹出框
	footerFun: function($current){
		this.tempData.dataIndex = $current.attr("data-index");
		  var base = this;
		   base.tempData.id = "footerFun";
		   layer.open({  
			   shade: [0.2, '#000', false],  
			   type: 2,  
			   area: ['660px', '545px'],  
			   fix: false, //不固定  
			   maxmin: true,  
			   title: ['编辑导航', false],  
			   content: ROOTPATH + '/jsp/website/manage/pop/navPop.jsp',
			   scrollbar:false,//是否允许滚动条出现
			   //按钮与回调
			   btn: ['保存', '取消'],
			   yes: function(index, layero){
				   
				   if(SiteManager.handers.footerFun){
					   SiteManager.handers.footerFun.save();
				   }
				   
				   layer.close(index);
			   },
			   cancel: function(index){
				   return true;
			   }
		   }); 
	},
	// 添加两个操作按钮
	addModuleOperationBtn: function($arr) {
		
		var base = this;
		
		$arr.each( function(){
			
			$moduleBody = $(this);
			
			var $current;
			// logo时紧贴logo放属性按钮
			if($moduleBody.find(".operation").length == 0) {
				if($moduleBody.find('[data-model="logo"]').length > 0){
					$current = $('[data-model="logo"]');
					$current.append(base.moduleOperationBtn);
					SiteManager.addBtnHover($current);
				}else if($moduleBody.find('[data-model="nav"]').length > 0){// 其他则放module上
					$current = $('[data-model="nav"]');
					$current.find(".w").append(base.moduleOperationBtn);
					SiteManager.addBtnHover($current);
				}else{
					$moduleBody.find("div[data-model]").each(function(){
						$(this).append(base.moduleOperationBtn);
						$current = $(this);
						// 鼠标移动模块上显示操作按钮
						SiteManager.addBtnHover($current);
						
						if($current.find(".extendMouduleBtn").length == 0){
							//扩展特殊属性
							var dataType = $current.attr("data-type");
							//给文章模块儿添加添加文章按钮
							if(dataType != void 0 && dataType.indexOf('1 ') != -1){
								$current.find(".editMouduleBtn").after('<a class="btn btn-blue white dib lh1 editBtn extendMouduleBtn" data-type="addArticle">添加文章</a>');
							}else if(dataType != void 0 && dataType.indexOf('3 ') != -1){
								$current.find(".editMouduleBtn").after('<a class="btn btn-blue white dib lh1 editBtn extendMouduleBtn" data-type="addMedia">添加视频</a>');
							}else if(dataType != void 0 && dataType.indexOf('2 ') != -1){
								$current.find(".editMouduleBtn").after('<a class="btn btn-blue white dib lh1 editBtn extendMouduleBtn" data-type="addPictureAlbum">添加图册</a>');
							}
						}
					});
					
					
				}
			}
		});
	},

	// 添加滑动显示按钮事件
	addBtnHover: function($current) {
		if($current != void 0 && $current != null){
			$current.on('mouseover', function() {
				$current.css({
					'position':'relative'
				});
				SiteManager.showEditBox(this); // 显示编辑按钮
				SiteManager.modelDashedWidthHeight(this); // 设置鼠标滑动模块显示的虚线宽度和高度
				SiteManager.btnDisplayPosition(this); // 当模块宽度与窗口宽度相等，操作按钮显示的位置
			});
		}
	},
	// 文章列表每条数据添加两个操作按钮
	addContentOperationBtn: function(id) {
		var base = this,
		$this = base.$moduleArray.find(id);
		if ($this.find('.operation01').length < 1) {
			$this.append(base.contentOperationBtn);
		}

		// 鼠标移动模块上显示操作按钮
		$this.on('mouseover', function() {
			base.showEditBox01(this); // 显示编辑按钮
		});
	},

	// 显示操作按钮
	showEditBox: function(id) {
		// 显示选区框
		$(id).find('.operation').show();
		$(id).css({
			'z-index': 10
		});

		// 隐藏选区框
		$(id).on('mouseout', function() {
			$(id).find('.operation').hide();
			$(id).css({
				'z-index': 1
			});
		});
	},

	// 显示文章列表每条内容操作按钮
	showEditBox01: function(id) {
		var $this = $(id);

		$this.find('.operation01').show(); // 显示选区框
		if($this.index() == 0){
			$this.find(".btn-top").css({"background-position":"-87px 0"})
		}
		$this.css({
			'z-index': 10
		});
		$this.on('mouseout', function() {
			$this.find('.operation01').hide();
			$this.css({
				'z-index': 1
			});
		}); // 隐藏选区框

		var $operation = $this.parents('[data-model="list"]').find('.operation');

		$this.find('.operation01').on('mouseover', function() {
			$operation.find('.operation-box').css('height', 0);
			$operation.css({
				'border-width': 0
			});
		}).on('mouseout', function() {
			$operation.find('.operation-box').removeAttr('style');
			$operation.css({
				'border-width': '2px'
			});
		});
	},

	// 设置鼠标滑动模块显示的虚线宽度和高度
	modelDashedWidthHeight: function(id) {
		var $this = $(id);

		// 设置选区宽高
		$this.find('.operation').css({
			'width': $this.width(),
			'height': $this.outerHeight()
		});
	},

	// 设置弹出层透明背景宽度和高度
	popWidthHeight: function() {
		$('.pop-background').css({
			'width': $(window).width(),
			'height': $(document).height()
		});
		$(window).on('load resize', function() {
			$('.pop-background').css({
				'width': $(window).width(),
				'height': $(document).height()
			});
		});
	},

	// 当模块宽度与窗口宽度相等，操作按钮显示的位置
	btnDisplayPosition: function(id) {
		var $this = $(id);

		if ($(window).width() - $this.width() < 30) {
			$this.find('.operation').addClass('operationTop');
			$this.find('.operation-box').css({
				'width': 'auto',
				'left': '50%',
				'margin-left': -($('.w').width() / 2)
			});
			$this.find('.operation').width($this.find('.operation').width() - 5);
		}
	},
	//返回一个继承C的P
	inherit : function(C, P) {
		
		for (var i in C) 
	　　　　{ 
	　　　　　　if (P[i])//如果so也具有这个成员 
	　　　　　　　　continue; 
	　　　　　　P[i] = C[i]; 
	　　　　} 
		return P;
	},
	/** ************************** 页面中内容页的编辑******************************************/
	
	
	//内容页面中文章
	 contentArticle:function(id){
			layer.open({  
	            shade: [0.2, '#000', false],  
	            type: 2,  
	            area: ['1000px', '500px'],  
	            fix: false, //不固定  
	            maxmin: true,  
	            title: ['编辑文章', false],  
	            content: ROOTPATH + '/site/toArticleEditOrView.do?articleId='+id,
	            scrollbar:false,//是否允许滚动条出现
				}); 
		
	},
	//内容页面中视频
	contentMedia:function(id){
		layer.open({  
            shade: [0.2, '#000', false],  
            type: 2,  
            area: ['1000px', '500px'],  
            fix: false, //不固定  
            maxmin: true,  
            title: ['编辑视频', false],  
            content: ROOTPATH + '/site/toMediaEditOrView.do?mediaId='+id,
            scrollbar:false,//是否允许滚动条出现
			}); 
	}
	
};
//小操作
function small() {
	//头部
	var $myWeb = $('.topBarArea .topBarArea-right .myWeb');

	$('.topBarArea .topBarArea-nav li:last').find('span').remove();
	$myWeb.on('mouseover', function() {
		$(this).find('.web').show();
	});
	$myWeb.on('mouseout', function() {
		$(this).find('.web').hide();
	});
}
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
/**
 * 
 * 批量修改文章的操作(删除 置顶)
 * 
 */
var bathEditArticle = function(data,tipstr){
	$.ajax({
		type:'post',
		url:rootPath+'/site/editUpdateArticle.do',
		data:data,
		dataType:'json',
		async : true,
		success:function(data){
			if(data.rst == 0){
				if(tipstr !=null){
					layer.alert(tipstr + '文章数据保存成功!',{icon:1});
					window.location.reload();
				}
			}else{
				if(tipstr !=null){
					layer.alert(tipstr + '文章数据保存失败!',{icon:2});
				}
			}
		},
		error:function(data){
			//alert(JSON.stringify(data));
		}
	});
};

//批量处理视频属性内容(删除 置顶)
var batchEditMedia = function(data , tipStr){
	 $.ajax({
			type:'post',
			 url:rootPath+'/site/editMediaProperties.do',
			data:data,
			dataType:'json',
			async : true,
			success: function(data){ 
				if(data.rst == 0){
					if(tipStr != null){
						window.location.reload();
					}
				}else{
					if(tipStr != null){
						layer.alert(tipStr +  '视频数据失败', {icon: 2});
						}
				}
			 },
			error:function(data){
				//alert(JSON.stringify(data));
			}
	     });
}
