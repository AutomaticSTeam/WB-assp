// 小操作
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

var Web = {
	rootPath: getRootPath(),	
	init: function() {
		var base = this;

		base.parent = parent.document; // 回到iframe的父级
		base.body = parent.document.body; // 回到iframe的父级找到元素body
		base.$parent = $(base.parent);
		base.$logo = $('[data-model="logo"]'); // 获取logo网站标志模块Dom
		base.$nav = $('[data-model="nav"]');// 获取nav导航模块Dom
		base.$focus = $('[data-model="focus"]');// 获取focus轮播图模块Dom
		base.$imgText = $('[data-model="imgText"]');// 获取imgText图文模块Dom
		base.$list= $('[data-model="list"]');// 获取list文章列表模块Dom
		base.$product= $('[data-model="product"]');// 获取paroduct产品列表模块Dom
		base.$footer = $('[data-model="footer"]');// 获取footer网站底部模块Dom
		base.$popTitle = '编辑属性', // 设置ifarme标题

		// 模块操作按钮2个“编辑属性和隐藏模块”
		base.operationBtn2 = '<div class="operation"><div class="operation-box"><a class="btn btn-blue white dib lh1 editBtn">编辑属性</a><!--<a class="btn btn-blue white dib lh1 btn-hide">隐藏模块</a>--></div></div>';

		// 文章列表模块每条数据操作按钮2个“编辑和删除”
		base.listOperationBtn2 = '<div class="operation01"><div class="operation-box"><a title="编辑" class="btn-edit">编辑</a><a title="删除" class="btn-del">删除</a></div></div>';

		base.attachEvents();
	},
	attachEvents: function() {
		var base = this;
		
		
		//logo网站标志编辑属性按钮
		base.operationBtn2New(base.$logo);
		base.$logo.find('.operation .operation-box .editBtn').on('click', function() {
			base.logoFun(this); // 显示弹出框
		});
		
		//nav网站导航编辑属性按钮
		base.operationBtn2New(base.$nav);
		base.$nav.find('.operation .operation-box .editBtn').on('click', function() {
			base.navFun(this); // 显示弹出框
		});
		
		//focus网站轮播图编辑属性按钮
		base.operationBtn2New(base.$focus);
		base.$focus.find('.operation .operation-box .editBtn').on('click', function() {
			base.focusFun(this); // 显示弹出框
		});
		
		//imgText网站图文内容编辑属性按钮
		base.operationBtn2New(base.$imgText);
		base.$imgText.find('.operation .operation-box .editBtn').on('click', function() {
			base.imgTextFun(this); // 显示弹出框
		});
		
		//list网站文章列表编辑属性按钮
		base.operationBtn2New(base.$list);
		base.$list.find('.operation .operation-box .editBtn').on('click', function() {
			base.listFun(this); // 显示弹出框
		});
		
		//product网站产品列表编辑属性按钮
		base.operationBtn2New(base.$product);
		base.$product.find('.operation .operation-box .editBtn').on('click', function() {
			base.productFun(this); // 显示弹出框
		});
		
		//footer网站底部编辑属性按钮
		base.operationBtn2New(base.$footer);
		base.$footer.find('.operation .operation-box .editBtn').on('click', function() {
			base.footerFun(this); // 显示弹出框
		});
		
		base.other();
	
	},

	other: function() {
		var base = this;

		// logo
		$('[data-model="logo"]').addClass('operationTop');

		//图文
		$('[data-model="imgText"]').on('mouseover', function() {
			var $this = $(this);

			if ($this.height() < $this.find('.operation-box').height()) {
				$this.find('.operation').addClass('operationTop');
			}
		});

		//文章管理
		if ($('[data-model="list"] .operation .btn-list').length < 1) {
			$('[data-model="list"] .operation .btn-edit').after('<a href="javascript:;" class="btn btn-blue white dib lh1 btn-list">添加文章</a>'); // 添加文章按钮
		}

		//文章管理每条数据
		base.listOperationBtn2New('[data-model="list"] li');

		//网站底部
		$('[data-model="footer"]').find('.operation').height($('[data-model="footer"]').find('.operation').height() - 2);
	},

	// ==============================操作==============================
	
	// logo网站标志弹出框
	logoFun: function(current){
		var base = this,$this = $(current);
		layer.open({  
            shade: [0.2, '#000', false],  
            type: 1,  
            area: ['474px', '260px'],  
            fix: false, //不固定  
            maxmin: true,  
            title: ['编辑logo', false],  
            content: $("#logoFun"),//base.rootPath + '/jsp/website/manage/logoFun.jsp' //,
            scrollbar:false,//是否允许滚动条出现
			  //按钮与回调
			btn: ['确认', '取消'],
			yes: function(index, layero){
				  $logoManage.setModified();
				  layer.close(index);
			 }, success: function(a, c) {
				 $logoManage.initialize();
             }
			}); 
		
   },
	
	// nav网站导航弹出框
	navFun: function(current){
		var base = this,$this = $(current);
		layer.open({  
            shade: [0.2, '#000', false],  
            type: 1,  
            area: ['474px', '260px'],  
            fix: false, //不固定  
            maxmin: true,  
            title: ['编辑导航', false],  
            content: $("#navFun"),
            scrollbar:false,//是否允许滚动条出现
			  //按钮与回调
			btn: ['确认', '取消'],
			yes: function(index, layero){
				console.log(navManage);
				  navManage.setModified();
				  layer.close(index);
			 }, success: function(a, c) {
				 navManage.initialize();
             }
		}); 
	},
	
	// focus网站轮播图弹出框
	focusFun: function(current){
		var base = this,
			$this = $(current),
			urlValue = '../../../../pop/focusPop.html', // ifarme地址
			popIfarmeWidth = '660', // 设置ifarme宽度
			popIfarmeHeight = '420'; // 设置ifarme高度
			id = 'formModel-3';

		base.iframePop(base.$popTitle, urlValue, popIfarmeWidth, popIfarmeHeight, id, 'focusPop'); // 添加logo网站标志操作弹出框
		
		// 鼠标点击确定按钮
		$('#' + id).find('.confirm-btn').on('click', function() {

		});
		
		// 鼠标点击取消和关闭按钮
		$('#' + id).find('.cancel-btn, .close').on('click', function() {
			$('#' + id).remove();
			$('#background-' + id).remove();
		});
	},
	
	// imgText网站图文弹出框
	imgTextFun: function(current){
		var base = this,
			$this = $(current),
			urlValue = '../../../../pop/imgTextPop.html', // ifarme地址
			popIfarmeWidth = '750', // 设置ifarme宽度
			popIfarmeHeight = '420'; // 设置ifarme高度
			id = 'formModel-4';

		base.iframePop(base.$popTitle, urlValue, popIfarmeWidth, popIfarmeHeight, id, 'imgTextPop'); // 添加logo网站标志操作弹出框
		
		// 鼠标点击确定按钮
		$('#' + id).find('.confirm-btn').on('click', function() {
			var $model = $this.parent().parent().parent(),
				$iframe = $(this).parent().siblings('.pop-iframe'),
				$title = $iframe.find('.content-title').val(), // 获取模块标题
				$layout = $iframe.find('.imagesText-css .active').data('css'), // 获取模块样式
				$content = $iframe.find('.editor-box textarea').val(), // 获取模块内容
				$img = $iframe.find('.imagesText-uploadImg'),
				$imgSrc = $img.find('.imagesText-img img').attr('src'), // 获取模块内容左侧图片src
				$imgWidth = $img.find('.imagesText-img .img-width').val(),// 获取模块内容左侧图片宽度
				$imgHeight = $img.find('.imagesText-img .img-height').val(),// 获取模块内容左侧图片高度
				$imgAlt = $img.find('.imagesText-img .img-alt').val();// 获取模块内容左侧图片描述
				
			$model.find('.imgText-content').html($content); // 改变当前内容（编辑器）
			
			// 改变当前图片内容（单独上传）
			$model.parents('.imgText').find('.imgText-img').attr({ 
				src:$imgSrc,
				width:$imgWidth,
				height:$imgHeight,
				alt:$imgAlt
			});
			
			// 改变模块标题
			var tj = $model.find('.content .modelList-content').attr('class').replace(/[^0-9]/ig, "") == $model.find('.title .modelList-tit').data('tab').replace(/[^0-9]/ig, "");
			if(tj){
				$model.siblings('.title').find('.tit').html($title);				
			}
				
			base.closePop(id);// 关闭弹出框
		});
		
		// 鼠标点击取消和关闭按钮
		$('#' + id).find('.cancel-btn, .close').on('click', function() {
			base.closePop(id);// 关闭弹出框
		});
	},
	
	// list网站文章列表弹出框
	listFun: function(current){
		var base = this,
			$this = $(current),
			urlValue = '../../../../pop/listPop.html', // ifarme地址
			popIfarmeWidth = '646', // 设置ifarme宽度
			popIfarmeHeight = '420'; // 设置ifarme高度
			id = 'formModel-4';

		base.iframePop(base.$popTitle, urlValue, popIfarmeWidth, popIfarmeHeight, id, 'listPop'); // 添加logo网站标志操作弹出框
		
		// 鼠标点击确定按钮
		$('#' + id).find('.confirm-btn').on('click', function() {

		});
		
		// 鼠标点击取消和关闭按钮
		$('#' + id).find('.cancel-btn, .close').on('click', function() {
			$('#' + id).remove();
			$('#background-' + id).remove();
		});
	},
	
	// product网站产品列表弹出框
	productFun: function(current){
		var base = this,
			$this = $(current),
			urlValue = '../../../../pop/productPop.html', // ifarme地址
			popIfarmeWidth = '660', // 设置ifarme宽度
			popIfarmeHeight = '420'; // 设置ifarme高度
			id = 'formModel-4';

		base.iframePop(base.$popTitle, urlValue, popIfarmeWidth, popIfarmeHeight, id, 'productPop'); // 添加logo网站标志操作弹出框
		
		// 鼠标点击确定按钮
		$('#' + id).find('.confirm-btn').on('click', function() {

		});
		
		// 鼠标点击取消和关闭按钮
		$('#' + id).find('.cancel-btn, .close').on('click', function() {
			$('#' + id).remove();
			$('#background-' + id).remove();
		});
	},
	
	// footer网站产品列表弹出框
	footerFun: function(current){
		var base = this,
			$this = $(current),
			urlValue = '../../../../pop/navPop.html', // ifarme地址
			popIfarmeWidth = '640', // 设置ifarme宽度
			popIfarmeHeight = '420'; // 设置ifarme高度
			id = 'formModel-4';

		base.iframePop(base.$popTitle, urlValue, popIfarmeWidth, popIfarmeHeight, id, 'navPop'); // 添加logo网站标志操作弹出框
		
		// 鼠标点击确定按钮
		$('#' + id).find('.confirm-btn').on('click', function() {

		});
		
		// 鼠标点击取消和关闭按钮
		$('#' + id).find('.cancel-btn, .close').on('click', function() {
			$('#' + id).remove();
			$('#background-' + id).remove();
		});
	},
	
	
	// 图文操作
	imgTextAtta: function(id, thisValueId) {
		var base = this,
			$this = $(id),
			$obtainContent = $('#' + thisValueId).find('iframe').contents(); // 获取iframe内的节点;

		// 获取单个图片内容
		$this.find('.imgText-img img').attr({
			'src':$obtainContent.find('.img-pic').attr('src'),
			'alt':$obtainContent.find('.img-alt').val(),
			'title':$obtainContent.find('.img-alt').val(),
			'width':$obtainContent.find('.img-width').val(),
			'height':$obtainContent.find('.img-height').val()
		});
		
		// 获取编辑器内容
		$this.find('.imgText-content').html($obtainContent.find('.editor-box textarea').val());
		
		base.common($this, thisValueId, $obtainContent); 
	},
	
	// 获取显示样式和标题
	common: function($this, thisValueId, $obtainContent){
		$this.find('.imgText').attr('class','imgText'+' imgText'+$obtainContent.find('.imagesText-css .active').attr('data-css')); // 获取显示样式
		
		//获取标题
		if($this.attr('class').replace(/[^0-9]/ig, "") == $this.parents('.model-body').find('.modelList-tit').attr('data-tab').replace(/[^0-9]/ig, "")){
			$this.parents('.model-body').find('.modelList-tit').attr('data-tab','item'+$this.attr('class').replace(/[^0-9]/ig, "")).find('.tit').text($obtainContent.find('.content-title').val());
		}
	},

	// ==============================pop弹出层==============================

	// 添加上传图片pop
	addUploadPop: function() {
		var base = this,

			urlValue = '../../../../pop/uploadImg.html',
			idValueNew = 'popUpload-' + base.$parent.find('.pop-box').attr('id').replace(/[^0-9]/ig, ""),
			popTitle = '添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）',
			popIfarmeWidth = '640', // 设置ifarme宽度
			popIfarmeHeight = '324'; // 设置ifarme高度

		// 添加上传图片操作弹出框
		base.iframePop(popTitle, urlValue, popIfarmeWidth, popIfarmeHeight, idValueNew); // 添加footer网站底部操作弹出框
		base.$parent.find('#' + idValueNew).prev('.pop-background').remove();
		base.delPop(idValueNew); // 鼠标点击关闭和取消按钮删除弹出层
	},
	
	// 添加管理文章分类pop
	glClass: function(){
		var base = this,

			urlValue = '../../../../pop/manageArticleClassPop.html',
			idValueNew = 'glClass-' + base.$parent.find('.pop-box').attr('id').replace(/[^0-9]/ig, ""),
			popTitle = '管理文章分类',
			popIfarmeWidth = '474', // 设置ifarme宽度
			popIfarmeHeight = '248'; // 设置ifarme高度

		// 添加上传图片操作弹出框
		base.iframePop(popTitle, urlValue, popIfarmeWidth, popIfarmeHeight, idValueNew); // 添加footer网站底部操作弹出框
		base.$parent.find('#' + idValueNew).prev('.pop-background').remove();
		base.delPop(idValueNew); // 鼠标点击关闭和取消按钮删除弹出层
	},
	
	// 编辑管理文章分类名称
	editArticleClass: function(popTitle){
		var base = this,

			urlValue = '../../../../pop/editClass.html',
			idValueNew = 'editArticleClass-' + base.$parent.find('.pop-box').attr('id').replace(/[^0-9]/ig, ""),
			popTitle = popTitle,
			popIfarmeWidth = '338', // 设置ifarme宽度
			popIfarmeHeight = '82'; // 设置ifarme高度

		// 添加上传图片操作弹出框
		base.iframePop(popTitle, urlValue, popIfarmeWidth, popIfarmeHeight, idValueNew); // 添加footer网站底部操作弹出框
		base.$parent.find('#' + idValueNew).prev('.pop-background').remove();
		base.$parent.find('#' + idValueNew).find('.cancel-btn').remove();
		base.$parent.find('#' + idValueNew).find('.confirm-btn').css('margin-right',0);
		base.delPop(idValueNew); // 鼠标点击关闭和取消按钮删除弹出层
	},

	// ==============================其他==============================

	// 添加两个操作按钮
	operationBtn2New: function(id) {
		var base = this,
			$this = id;

		if ($this.find('.operation').length < 1) {
			$this.append(base.operationBtn2);
		}

		// 鼠标移动模块上显示操作按钮
		$this.on('mouseover', function() {
			$this.css({
				'position':'relative'
			});
			base.showEditBox(this); // 显示编辑按钮
			base.modelDashedWidthHeight(this); // 设置鼠标滑动模块显示的虚线宽度和高度
			base.btnDisplayPosition(this); // 当模块宽度与窗口宽度相等，操作按钮显示的位置
		});
	},

	// 文章列表每条数据添加两个操作按钮
	listOperationBtn2New: function(id) {
		var base = this,
			$this = $(id);

		if ($this.find('.operation01').length < 1) {
			$this.append(base.listOperationBtn2);
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

	// 删除弹出层
	delPop: function(idValueNew) {
		var base = this;

		base.$parent.find('#' + idValueNew).find('.close, .cancel-btn').on('click', function() {
			base.$parent.find('#' + idValueNew).remove();
			base.$parent.find('#background-' + idValueNew).remove();
		});
	},

	// 当模块宽度与窗口宽度相等，操作按钮显示的位置
	btnDisplayPosition: function(id) {
		var $this = $(id);

		if ($this.width() == $(window).width()) {
			$this.find('.operation').addClass('operationTop');
			$this.find('.operation-box').css({
				'width': 'auto',
				'left': '50%',
				'margin-left': -($('.w').width() / 2)
			});
			$this.find('.operation').width($this.find('.operation').width() - 5);
		}
	},
	
	closePop: function(id){
		$('#' + id).remove();
		$('#background-' + id).remove();
	},

	// 添加模块对应弹出框ifarme
	iframePop: function(popTitle, urlValue, w, h, popID, popContentID) {
		var base = this,
			id = popID;
		
		$('<div class="pop-background" id="background-' + id + '"></div><div class="pop-box" id="' + id + '"><div class="pop-title"><h2>' + popTitle + '</h2><div class="close"><a></a></div></div><!--pop-title--><div class="pop-iframe"></div><!--pop-iframe--><div class="pop-btn"><a class="btn dib btn-blue01 confirm-btn">确定</a><a class="btn dib btn-green01 cancel-btn">取消</a></div><!--pop-btn--></div>').appendTo(base.body);
//		<iframe src="' + urlValue + '" frameborder="0" scrolling="auto" style="width:100%;height:100%;"></iframe>

		$('#'+ id).find('.pop-iframe').load(urlValue, function(responseText, textStatus, XMLHttpRequest) {  
            //所有状态成功，执行此函数，显示数据  //textStatus四种状态 success、error、notmodified、timeout  
            if (textStatus == "error") {  
                var msg = "错误: ";  
                alert(msg + XMLHttpRequest.status + " " + XMLHttpRequest.statusText);  
            }  
            else if (textStatus == "timeout") {  
                var msg = "操时: ";  
                alert(msg + XMLHttpRequest.status + " " + XMLHttpRequest.statusText);  
            } else if(textStatus == "success"){
            	$('.removeElement').remove();
            }
        });
		
		base.popCenter( id, w, h); // 让弹出层居中 (必须：一定要在pop-background的下面)
	},

	// 让弹出层居中
	popCenter: function(idValueNew, w, h) {
		var base = this;

		base.$parent.find('#' + idValueNew).find('.pop-iframe').css({
			'width': +w + 'px',
			'height': +h + 'px'
		});
		base.$parent.find('#' + idValueNew).css({
			'width': +w + 'px',
			'top': '50%',
			'left': '50%',
			'margin-top': -base.$parent.find('#' + idValueNew).height() / 2,
			'margin-left': -base.$parent.find('#' + idValueNew).width() / 2
		});
		base.popWidthHeight(); // 设置弹出层透明背景宽度和高度
	}
};
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


var websiteManage = {
		modules:[],//
		initBtnId:null,//
		prepareModuleIds:[],//欲执行module的id数
		completeModuleIds:[],//已执行module的id数
		//添加模块儿定义
		addModule:function(obj){
			var cm_ = websiteManage.hasModule(obj);
			//有先删除
			if(cm_!=-1){
				websiteManage.removeModule(cm_);
			}
			websiteManage.modules.push(obj);	
		},
		//判断是否含有
		hasModule:function(obj){
			for(var i=0;i<websiteManage.modules.length;i++){
				if(websiteManage.modules[i].id==obj.id)
					return i;
			}
			return -1;
		},
		//删除
		removeModule:function(index){
			websiteManage.modules.splice(index,1);
		},
		//取消
		cancel:function(){
			window.location.reload();
		},
		//初始化btn
		initBtn:function(){
			if(websiteManage.modules.length>0){
				$(".btn-website-edit").show();
				if(websiteManage.initBtnId!=null)
					window.clearInterval(websiteManage.initBtnId);
			}
			
		},
		//refresh
		refresh:function(){
			if(websiteManage.completeModuleIds.length==websiteManage.prepareModuleIds.length){
				window.location.reload();
			}
		},
		//终极保存
		save:function(){
			
			for(var i=0;i<websiteManage.modules.length;i++){
				try{
					if(websiteManage.modules[i].isModified){
						websiteManage.modules[i].save(websiteManage.completeModuleIds);
						websiteManage.prepareModuleIds.push(websiteManage.modules[i].id);
					}
				}catch(e){
					alert(e);
				}
			}
			//complete之后...
			if(websiteManage.prepareModuleIds.length>0){
				window.clearInterval(websiteManage.initBtnId);
				$(".btn-website-edit").hide();
				$(".btn-website-process").show();
				self.setInterval("websiteManage.refresh()",500);
			}
		
		},
		inherit : function(C, P) {
			
			for (var i in C) 
		　　　　{ 
		　　　　　　if (P[i])//如果so也具有这个成员 
		　　　　　　　　continue; 
		　　　　　　P[i] = C[i]; 
		　　　　} 
			return P;
		}
};
websiteManage.initBtnId = self.setInterval("websiteManage.initBtn()",1000);
var module_ = {
		id:null,
		top:window,
		//是否被修改
		isModified:false,
		//保存操作完之后要把执行模块儿id放到已执行队列，以确保所有模块儿都保存完之后才做后续操作
		save:function(completeModuleIds){
			console.log(this.id+' save...');
		},
		//初始化
		initialize:function(){
			this.toTarget();
			console.log(this.id+' initialize...');
		},
		
		toTarget:function(){
			top.websiteManage.addModule(this);
			
		},
		//保存时，判断是否被修改，只有被修改的才保存
		setModified:function(){
			module_.isModified = true;
		}
};
