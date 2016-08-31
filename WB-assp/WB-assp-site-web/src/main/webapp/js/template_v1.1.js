/**
 * @author :songhx
 * @version 1.1
 * @fun_desc
 *      
 */

if(typeof Template=="undefined"){Template={}}
if(typeof columnsId=="undefined"){columnsId=''}
template.config("escape", false);
var TOP_LEVEL = 1; // 顶级
var SUB_LEVEL = 2; // 子级

/**
 * @author :songhx
 * @class_des    模板解析类
 *  @version 1.1
 */
var  Template = {
		//声明变量
		topModulesArray : new  Array(), // 模板页顶级模块数组
		subModulesArray : new  Array(), // 模板页子级模块数组
		
		
		//初始化操作
		
		/**
		 * @method 初始化模板页的方法
		 * @param  cid   导航id
		 */
		initTemplatePage : function(cid){
			Template.drawFrames({columnsId : cid });
			Site.listenBtn();
		},

		/**
		 * @author wangkang
		 * @method 初始化内容页的方法
		 * @param  dataId     内容  Id
		 * @param  dataType   内容 数据类型
		 */
		initContentPage : function(dataId,dataType){
			Site.listenBtn();
			$.ajax({
				type:"post",
				url:rootPath+'/site/loadContentPage.do',
				data:{ contentDataId: dataId,
					   dataType: dataType
				},
				dataType:'json',
				async :true,
				success: function(data){
					var templxml;
					var frame;
					$data = data.contentData;
					switch(dataType){
						//  TODO...
							case '2':
								templxml = 'module_article_tmpl';
							break;
							case '9':
								templxml = 'module_media_tmpl';
							break;
							case '7':
								templxml = 'module_picture_tmpl';
							break;
						} 
						 var moduleBody = $("#module0");
						 moduleBody.find("#m0_tabContent").html(template(templxml,eval($data)));
						 moduleBody.show();
						 if(typeof SiteManager != "undefined"){
							 SiteManager.initModule($("#module0"));
						 }
						 commonVedio.showVedio();
				}
			});
		},

		/**
		 * @desc
		 *                  绘制页面框架
		 * @param   cid  菜单栏目id
		 * @return   
		 */
		drawFrames : function(data_){
			Template.topModulesArray.length = 0; //清空原有数组数据 
			Template.subModulesArray.length = 0; //清空原有数组数据 
			//绘制框架
			$.ajax({
				type:'post',
				url:QUERY_FRAMES_PATH,
				data:data_,
				dataType:'json',
				async : true,
				success: function(data){ 
					var frames = '';
					 $data = data.frames;
					 if($data!=null&&$data.length > 0){
						 var frameClass;
						 $.each($data ,function(i,frame){
							 frameClass = '';
							 frames += '<div class="main frame" data-url="'+frame.sortNum+'">';
							 if(frame.cssClass != void 0){
								 frameClass = frame.cssClass;
							 }
							 switch(frameClass){
							 	case 'header':
							 		frames += '<div class="header"><div class="w">';
							 		break;
							 	case 'footer':
							 		frames += '<div class="footer"><div>';
							 		break;
							 	case 'w1':
							 		frames += '<div><div>';
							 		break;
							 	default:	
							 		frames +='<div><div class="w">';
							 }
							 
							 /*if(frame.frameId == 1){
								 frames +='<div >';
							 }else{
								 frames +='<div class="main frame" data-url="'+frame.sortNum+'"> <div class="w" id="DynamicModules">';
							 }*/
							 
							 
							 if(frame.subTemplateColumnsRelFrame != null){
								 $.each(frame.subTemplateColumnsRelFrame ,function(i,subframe){
									 frames += Template.drawFrame(subframe,subframe.templateFrameRelModules,SUB_LEVEL);
								 });
							 }else{
								 frames += Template.drawFrame(frame,frame.templateFrameRelModules,TOP_LEVEL);
							 }
							 frames +='</div></div></div>';
						 });
				    }else{// TODO
				    	window.location.href = rootPath + '/jsp/error/404.jsp';
				    	return;
				    }
					$("#wrapper").html(frames); //重绘网站主题内容
					
					var data = null; 
					if(Template.topModulesArray.length  > 0 ){
						
						data = {moduleIds : Template.topModulesArray};
						Template.fillModules(data); //填充模块
					}
					
					if(Template.subModulesArray.length  > 0 ){
						data = {moduleIds : Template.subModulesArray};
						Template.fillModules(data); //填充模块
					}

					if(typeof contentDataId !="undefined" && contentDataId != '' && contentDataId != 'null'){
						Template.initContentPage(contentDataId , dataType);
						
					}
					
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     });
		},
		
		/**
		 * @desc
		 *                  绘制具体版式
		 * @param ft 版式类型   
		 * @param  mid 模板id
		 * @param  level 板式级别   1 top   2  sub
		 * @return 版式和模块框架字符串
		 */
		drawFrame : function(frameVO,mids,level){
			var ft = frameVO.frameId;
			var frame = (ft > 1) ?  '<div class="model model-array-' + ft + ' '+(frameVO.cssClass != void 0 ? frameVO.cssClass : '')+'">' : '<div class="model">';
		    var	mid = 0;
		    for(var i =0 ; i < ft ;  i++){
		    	mid = (mids != null && mids!=undefined && mids!="undefined" && mids[i] != void 0 ) ? mids[i].moduleId : 0;
		    	if(mids != null && mids[i] != void 0 ){
		    		( level == 1)  ?  Template.topModulesArray.push(mids[i].moduleId) :   Template.subModulesArray.push(mids[i].moduleId);
		    	}
				frame +=(ft > 1) ? '<div class="model-item '+(mids != void 0 && mids[i] != void 0 && mids[i].cssClass != void 0?  mids[i].cssClass : 'fr' )+'">' : '<div class="model-item">';
				frame +='<div class="model-body" id="module'+mid+'" style="display:none;" data-show="">'
							   + Template.drawGroup(mid)
			     	           +'</div>'
			                   +'</div>';
			}
		    frame +='</div>';
			return frame;
		},
		/**绘制group
		 * 
		 * */
		drawGroup: function(mid){
			return '<div class="modelGroup" id="modelGroup'+mid+'" data-show="">'
			   +'<div class="tab"  id="m'+mid+'_tab">'
			   +'	</div> '
			   +'	<div class="tabContent" id="m'+mid+'_tabContent">'
			   +'</div>'
			   +'</div>';
		},
		/**
		 * @desc
		 *                 填充多模块
		 * @param data  模块数据参数   
		 */
		fillModules : function(data){
			if(data == null )return;
			$.ajax({
				type : 'post',
				url : QUERY_MODULES_PATH,
				data :  data,
				dataType : 'json',
				async  :  true,
				traditional : true,
				success : function(data){ 
					$data = data.moduleVos;
					 if($data!=null&&$data.length > 0){
						 $.each($data ,function(i,moduleVo){
							 Template.fillModule(moduleVo);
						 });
				    }
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     });
		},
         
		/**
		 * @desc
		 *                 填充具体模块
		 * @param moduleVo   模块数据视图
		 */
		fillModule : function(moduleVo){
			if(moduleVo.moudles.length > 0){
				
				 var tmpModuleId = 0,tmpSytleType = 0;
				 // 记录模块id集合
				 var moduleIds_ = '';
				$.each(moduleVo.moudles ,function(i,moudle){
					moduleIds_ += '#module' + moudle.moduleId + ',';
					//主模块儿信息 初始化样式
					if(i == 0 ){
						//主模块儿id
						tmpModuleId =  moudle.moduleId;
						//模块儿组合样式
						if(moudle.moduleStyleType){
							tmpSytleType = moudle.moduleStyleType;
						}
					}
					//添加组
					if(tmpSytleType != 1){
						
						$("#module" + tmpModuleId).append(Template.drawGroup(moudle.moduleId));
					}
					
					(moudle.showTitile == 0) ? $("#m"+moudle.moduleId+"_tab").hide() : $("#m"+moudle.moduleId+"_tab").show(); //控制模块标题显隐
					
					//添加module-type --模块类型加资源类型
					var moduleType = moudle.moduleType != void 0 ? moudle.moduleType : '0';
					if(moudle.templateModuleRelContents){	
						for(var c = 0;c < moudle.templateModuleRelContents.length; c++){
							moduleType += '/' + moudle.templateModuleRelContents[c].contentDataType;
						}
					}
					
					
					//模块内容box
					if(tmpSytleType == 1){
						 //填充模块标题信息
						if(i == 0){//标签只注入一次
							$("#m"+tmpModuleId+"_tab").append(template('module_titles_tmpl', moduleVo));
						}
						 $("#m"+tmpModuleId+"_tabContent").append(Template.drawTabContentBox(i,moudle.moduleId)); 
						 
						 if(moudle.moduleTmpl != void 0){
							 $("#tab_con_"+moudle.moduleId+"_"+i).append(template(moudle.moduleTmpl, moudle));
						 }
						 $("#module"+tmpModuleId).show();
						 // 添加属性
						 $("#tab_con_"+moudle.moduleId+"_"+i).find("div[data-model]").attr("data-show",moudle.moduleTmpl);
						 $("#tab_con_"+moudle.moduleId+"_"+i).find("div[data-model]").attr("data-url",moudle.moduleId);
						 $("#tab_con_"+moudle.moduleId+"_"+i).find("div[data-model]").attr("data-type",moduleType + "/");
						 // 自身展示效果
						 if(moudle.moduleStyleShow != void 0){
							 $("#tab_con_"+moudle.moduleId+"_"+i).find("div[data-model]").attr("style-show",moudle.moduleStyleShow);
						 }
					}else{
						$("#m"+moudle.moduleId+"_tab").append(template('module_titles_tmpl', {moudles:[moudle]}));
						$("#m"+moudle.moduleId+"_tabContent").append(Template.drawTabContentBox(0,moudle.moduleId));
						 if(moudle.moduleTmpl != void 0){
							 switch(moudle.moduleTmpl){
							 	default:
							 		 $("#tab_con_"+moudle.moduleId+"_0").append(template(moudle.moduleTmpl, moudle));
							 }
							
						 }
						$("#module"+moudle.moduleId).show();
						$("#tab_con_"+moudle.moduleId+"_0").find("div[data-model]").attr("data-show",moudle.moduleTmpl);
					    $("#tab_con_"+moudle.moduleId+"_0").find("div[data-model]").attr("data-url",moudle.moduleId);
					    $("#tab_con_"+moudle.moduleId+"_0").find("div[data-model]").attr("data-type",moduleType + "/");
					 // 自身展示效果
					    if(moudle.moduleStyleShow != void 0){
					    	 $("#tab_con_"+moudle.moduleId+"_0").find("div[data-model]").attr("style-show",moudle.moduleStyleShow);
					    } 
					}
					
						
					if(moudle.moduleTmpl == "module_imgtxt6_tmpl"){
						imgText();
					}
					// 要轮播效果
					if(moudle.moduleStyleShow != void 0 && moudle.moduleStyleShow == "01"){
						
						// 轮播做改造
						if(moudle.moduleTmpl == "module_foucs_thumbnail_tmpl"){
							$('#focus02').slider({
								duration: 1, // 图片切换时间
								delay: 2, // 图片间隔时间
								autoPlay:false,
								width: (moudle.moduleStyleWidth == void 0) ? null : moudle.moduleStyleWidth,
								height: (moudle.moduleStyleHeight == void 0) ? null : moudle.moduleStyleHeight
							});
						}
						if(moudle.moduleTmpl == "module_banner_tmpl"){
							$('#focus01').slider({
								duration: 1, // 图片切换时间
								delay: 3, // 图片间隔时间
								autoPlay:true,
								width: (moudle.moduleStyleWidth == void 0) ? null : moudle.moduleStyleWidth,
								height: (moudle.moduleStyleHeight == void 0) ? null : moudle.moduleStyleHeight
							});
						}
						if(moudle.moduleTmpl == "module_foucs_pictures_tmpl"){
							$('#focus02').slider({
								duration: 1, // 图片切换时间
								delay: 3, // 图片间隔时间
								autoPlay:true,
								width: (moudle.moduleStyleWidth == void 0) ? null : moudle.moduleStyleWidth,
								height: (moudle.moduleStyleHeight == void 0) ? null : moudle.moduleStyleHeight
							});
						}
						 if(moudle.moduleTmpl == "module_foucs_productinfo_list_tmpl"){
								$('#product01').slider({
									duration: 1, // 图片切换时间
									delay: 2, // 图片间隔时间
									productNum:4, // 设置一张滑动中，产品个数
									autoPlay:true,
									width: null,
									height: 282
								});

						 }
						 
						  if(moudle.moduleTmpl == "module_foucs_productinfo_list01_tmpl"){
							  $('#product02').slider({
								  duration: 1, // 图片切换时间
								  delay: 2, // 图片间隔时间
								  productNum:4, // 设置一张滑动中，产品个数
								  autoPlay:true,
								  width: null,
								  height: 282
							  });
							  
						  }
						  if(moudle.moduleTmpl == "module_foucs_productinfo_list02_tmpl"){
							  $('#product03').slider({
								  duration: 1, // 图片切换时间
								  delay: 2, // 图片间隔时间
								  productNum:6, // 设置一张滑动中，产品个数
								  autoPlay:true,
								  width: null,
								  height: 282
							  });
							  
						  }
					}
					
				 });
				 //添加样式或控制
				 nav() ;
				 $("#column"+ columnsId ).addClass("current");
				 tab();
				// 添加编辑按钮兵谏亭
				if(typeof SiteManager != "undefined"){
					SiteManager.initModule($((moduleIds_.substring(0,moduleIds_.length-1))));
				}
				
			}
		},
		
		/**
		 * @desc
		 *   动态刷新具体模块(现在只做单一模块)
		 * @param moduleVo   模块数据视图
		 */
		reloadModule : function(module){
			if(typeof module == "undefined" || module.moduleId == void 0 ){
				return ;
			}
			// 先加载数据
			$.ajax({
				type : 'post',
				url : QUERY_MODULE_PATH,
				data :  module,
				dataType : 'json',
				async  :  true,
				traditional : true,
				success : function(data){ 
					if(data.code == 1){
						var module = data.datas.module;
						// 有子则刷新到叶子节点，否则只刷新自身
						if(module.childTemplateModules){
							$("#modelGroup"+module.moduleId).remove();
							$("#module" + module.moduleId).append(Template.drawGroup(module.moduleId));
							var moudles_ = [module];
							moudles_ = moudles_.concat(module.childTemplateModules);
							Template.fillModule({moudles:moudles_});
						}else{
							Template.refreshModule(module);
						}
					}
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     });
			
		},
		/**
		 * @desc
		 *   动态刷新具体模块(现在只做单一模块)
		 * @param moduleVo   模块数据视图
		 */
		refreshModule : function(module){
			
			
			 var tmpModuleId = 0,tmpSytleType = 0;
			 if(module.parentTemplateModule){
				 tmpModuleId = module.parentTemplateModule.moduleId;
				 tmpSytleType = module.parentTemplateModule.moduleStyleType;
			 }else if(module.moduleStyleType){
				 tmpSytleType = module.moduleStyleType;
			 }
			 
			 // 记录模块id
			 var moduleId_ = '#module' + module.moduleId;
			 
			 //主模块儿信息
			 if(tmpSytleType == 1){//清空组内信息
				 $("[id^='tab_con_"+module.moduleId +"']").empty();
				 $("[id^='tab-item-"+module.moduleId +"']").empty();
				 moduleId_ = "[id^='tab_con_"+module.moduleId +"']";
			 }else{
				$("#modelGroup" + module.moduleId).remove();
				$("#module" + module.moduleId).append(Template.drawGroup(module.moduleId));
			 }
				
			 // 如果本身就是一大模块，则有控制标题权限
			 if(tmpModuleId == 0){
				 (module.showTitile == 0) ? $("#m"+module.moduleId+"_tab").hide() : $("#m"+module.moduleId+"_tab").show(); //控制模块标题显隐
			 }
			
			//添加module-type --模块类型加资源类型
			var moduleType = module.moduleType != void 0 ? module.moduleType : '0';
			if(module.templateModuleRelContents){	
				for(var c = 0;c < module.templateModuleRelContents.length; c++){
					moduleType += '/' + module.templateModuleRelContents[c].contentDataType;
				}
			}	
			//模块内容box
			if(tmpSytleType == 1){
				// 填充模块标题信息
				$("[id^='tab-item-"+module.moduleId +"']").append(template('module_title_tmpl', module));
					 
			    if(module.moduleTmpl != void 0){
					 $("[id^='tab_con_"+module.moduleId +"']").append(template(module.moduleTmpl, module));
				}
			    $("#module"+tmpModuleId).show();
			    // 添加属性
			    var tabGroup = $("[id^='tab_con_"+module.moduleId +"'] div[data-model]");
			    if(tabGroup.length > 0){
			    	tabGroup.attr("data-show",module.moduleTmpl);
					tabGroup.attr("data-url",module.moduleId);
					tabGroup.attr("data-type",moduleType + "/");	 
					 // 自身展示效果
				    if(module.moduleStyleShow != void 0){
				    	 tabGroup.attr("style-show",module.moduleStyleShow);
				    } 
						 
			    }
			}else{
				$("#m"+module.moduleId+"_tab").append(template('module_titles_tmpl', {moudles:[module]}));
				$("#m"+module.moduleId+"_tabContent").append(Template.drawTabContentBox(0,module.moduleId));
				 if(module.moduleTmpl != void 0){
					 switch(module.moduleTmpl){
					 	default:
					 		 $("#tab_con_"+module.moduleId+"_0").append(template(module.moduleTmpl, module));
					 }
					
				 }
				$("#module"+module.moduleId).show();
				// 添加属性
				var tabGroup = $("#tab_con_"+module.moduleId+"_0").find("div[data-model]");
				if(tabGroup.length > 0){
					tabGroup.attr("data-show",module.moduleTmpl);
					tabGroup.attr("data-url",module.moduleId);
					tabGroup.attr("data-type",moduleType + "/");
					 // 自身展示效果
				    if(module.moduleStyleShow != void 0){
				    	 tabGroup.attr("style-show",module.moduleStyleShow);
				    } 
				}
					
			}
				
					
			if(module.moduleTmpl == "module_imgtxt6_tmpl"){
					imgText();
			}
			// 若轮播图，则轮播
			if(module.moduleStyleShow != void 0 && module.moduleStyleShow == "01"){
				// 轮播做改造
				if(module.moduleTmpl == "module_foucs_thumbnail_tmpl"){
					$('#focus02').slider({
						duration: 1, // 图片切换时间
						delay: 2, // 图片间隔时间
						autoPlay:false,
						width: (module.moduleStyleWidth == void 0) ? null : module.moduleStyleWidth,
						height: (module.moduleStyleHeight == void 0) ? null : module.moduleStyleHeight
					});
				}
				if(module.moduleTmpl == "module_banner_tmpl"){
					$('#focus01').slider({
						duration: 1, // 图片切换时间
						delay: 3, // 图片间隔时间
						autoPlay:true,
						width: (module.moduleStyleWidth == void 0) ? null : module.moduleStyleWidth,
						height: (module.moduleStyleHeight == void 0) ? null : module.moduleStyleHeight
					});
				}
				if(module.moduleTmpl == "module_foucs_pictures_tmpl"){
					$('#focus02').slider({
						duration: 1, // 图片切换时间
						delay: 3, // 图片间隔时间
						autoPlay:true,
						width: (module.moduleStyleWidth == void 0) ? null : module.moduleStyleWidth,
						height: (module.moduleStyleHeight == void 0) ? null : module.moduleStyleHeight
					});
				}
			  if(module.moduleTmpl == "module_foucs_productinfo_list_tmpl"){
					$('product01').slider({
						duration: 1, // 图片切换时间
						delay: 2, // 图片间隔时间
						productNum:4, // 设置一张滑动中，产品个数
						autoPlay:true,
						width: null,
						height: 282
					});

			 }
			  if(module.moduleTmpl == "module_foucs_productinfo_list01_tmpl"){
				  $('#product02').slider({
					  duration: 1, // 图片切换时间
					  delay: 2, // 图片间隔时间
					  productNum:4, // 设置一张滑动中，产品个数
					  autoPlay:true,
					  width: null,
					  height: 282
				  });
			  }
				  if(module.moduleTmpl == "module_foucs_productinfo_list02_tmpl"){
					  $('#product03').slider({
						  duration: 1, // 图片切换时间
						  delay: 2, // 图片间隔时间
						  productNum:6, // 设置一张滑动中，产品个数
						  autoPlay:true,
						  width: null,
						  height: 282
					  });
				  
			  }
			}
			//添加样式或控制
			nav() ;
			$("#column"+ columnsId ).addClass("current");
			tab();
			// 添加编辑按钮兵谏亭
			if(typeof SiteManager != "undefined"){
				SiteManager.initModule($(moduleId_));
			}
				
		},
		
		drawTabContentBox : function(itemIndex, mid){
			return '<div class="tabContent-item item'+itemIndex+'" id="tab_con_'+mid+'_'+itemIndex+'"> </div>';
		}
		
};

// 图文
function imgText(){
	var imgText_6 = $('.imgText-6');
		
	imgText_6.find('.imgText-img img').on('load',function(){
		var $this = $(this);
		
		imgText_6PicWidth = $this.width(),
		imgText_6PicHeight = $this.height();
		imgText_6.find('.imgText-content').height(imgText_6PicHeight).css({
			'margin-left': $this.width()+10
		});
	});
		
}


//tab切换
function tab() {
	var $elem = $('.tab');

	$elem.find('.tab-item .tit').append('<span class="line"></span>');

	$elem.each(function() {
		var $this = $(this);
			$tabItem = $this.find('.tab-item'),
			$tabContentItem = $this.next().find('.tabContent-item'),
			$tabMore = $this.find('.tab-more'),
			$current = $this.find('.current'),
			
			Cindex = $current.index();
		$tabItem.css({
			'cursor':'default'
		});

		if ($this.css('display') == 'none') {
			$tabContentItem.find(' > div').css({
				'padding-top': '0'
			})
		}

		if ($tabItem.length > 1) {
			
			$tabItem.css({
				'cursor':'pointer'
			});
			
			if ($tabContentItem.attr('class').replace(/[^0-9]/ig, "") == Cindex) {
				$tabContentItem.eq(Cindex).siblings().addClass('hide');
				$tabMore.eq(Cindex).siblings('.tab-more').addClass('hide');
				$current.siblings('.tab-item').find('.line').addClass('hide');
			}

			$tabItem.click(function() {
				var $base = $(this);

				$base.addClass('current').siblings('.tab-item').removeClass('current');
				$this.find('.tab-item').find('.line').addClass('hide');
				$base.find('.line').removeClass('hide');
				
				$this.next('.tabContent').find(' .tabContent-item').addClass('hide');
				$base.parent().next('.tabContent').find('.' + $base.data('tab')).removeClass('hide');
				$base.parent().find('.' + $base.data('tab')).removeClass('hide').siblings('.tab-more').addClass('hide');
			});
		}
	});
};

var navFlag = false;
// 导航切换
function nav() {
	var $nav = $('.nav'),
		$ul = $('.nav ul'),
		$li = $ul.find('li'),
		liWidth = $nav.find('.w').width() / $li.length;
	if($nav.length == 0 || navFlag)
		return;
	navFlag = true;
	
	var iNow = 0;
	// 设置li宽度
	$li.width(Math.floor(liWidth));
	$li.width($li.width() - ($li.outerWidth() - $li.width()));

	if ($li.outerWidth() * $li.length >= $nav.find('.w').width()) {
		$li.first().width($li.first().width() - 1);
	}
	//li个数小于5
	if($li.length<5){
		$li.width("auto");
		$li.find("a").css("padding","0 30px");
	}
	
	//li个数大于7加左右按钮
	if($(".ulWrap").has($ul).length == 0 && $li.length>7){
		$(".navBtn").show();
		$ul.css({"position":"relative"})
		liWidth = $nav.find('.w').width() / 7;
		$li.width(Math.floor(liWidth));
		$li.width($li.width() - ($li.outerWidth() - $li.width()));
		$ul.width($li.outerWidth()*$li.length);
		$ul.wrap("<div class='ulWrap'></div>");
		$(".ulWrap").css({"overflow-x":"hidden","position":"relative","width":$li.outerWidth()*7+1});
		$(".navBtnNext").on("click",BtnNextFun);
		$(".navBtnPre").on("click",BtnPreFun);
	}
	//点击右按钮
	function BtnNextFun(){
		if($li.length-iNow-1>=7){
			iNow++;
			$ul.animate({"left":-$li.outerWidth()*iNow});
			$(".navBtnPre").removeClass("active");
		}else{
			$(this).addClass("active")
		}
	}
	//点击左按钮
	function BtnPreFun(){
		if(iNow>0){
			iNow--;
			$ul.animate({"left":-$li.outerWidth()*iNow});
			$(".navBtnNext").removeClass("active");
		}else{
			$(this).addClass("active")
		}
	}

	$nav.each(function() {
		$(this).find('.menuList > li').on('mouseover', function() {
			var $this = $(this);
			
			$this.addClass('active').siblings('li').removeClass('active');
			$this.find('.subMenu').show();
			$this.on('mouseout', function() {
				$this.removeClass('active');
				$this.find('.subMenu').hide();
			});
			$this.find('.icon').addClass('hide');
			$this.find('.iconHover').removeClass('hide');
		}).on('mouseout',function(){
			var $this = $(this);
			$this.find('.icon').removeClass('hide');
			$this.find('.iconHover').addClass('hide');
		});
	});
};


template.helper('dateFormat', function (date, format) {
   return DateUtils.long2String(date, 1);
});
