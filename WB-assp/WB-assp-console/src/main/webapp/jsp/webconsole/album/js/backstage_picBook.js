$(function() {
	Backstage.init();
});

Backstage = {
	init: function() {
		var base = this;

		base.$addContentBtn = $('.addContentBtn'); //  获取管理图册上方“添加文章”按钮Dom
		base.$delBtn = $('.delBtn'); // 获取删除文章按钮Dom
		base.$addImgBtn = $('.addImgBtn'); // 获取添加图片按钮Dom
		base.$editArticleClassBtn = $('.btn-editor'); // 获取管理图册编辑按钮Dom

		base.attachEvents();
	},
	attachEvents: function() {
		var base = this;

		// 跳到创建图册页面（如需删除，可以删除）
		base.$addContentBtn.on('click', function() {
			$(parent.document).find('iframe').attr('src', $(this).attr('data-url'));
		});

		// 鼠标点击添加图片按钮
		base.$addImgBtn.on('click', function() {
			base.uploadPop(this);
		});

		// 鼠标点击删除按钮
		base.$delBtn.on('click', function() {
			base.setRemovePop();
			base.removeContent(this, base.popTitle, base.urlValue, base.popIfarmeWidth, base.popIfarmeHeight);
		});

		// 鼠标点击管理图册编辑按钮
		base.$editArticleClassBtn.on('click', function() {
			var tit = $(this).parents('tr').find('.tit').text();
			base.setAlterPop();
			base.articlesClassNewFun(tit, this, '编辑图册', base.urlValue, base.popIfarmeWidth, base.popIfarmeHeight);
		});
	},

	// ============================== 操作 ==============================

	// 设置删除弹出框地址和标题和宽度和高度
	setRemovePop: function() {
		var base = this;

		base.urlValue = rootPath + '/jsp/common/pop/delPop.jsp'; // 设置弹出框地址
		base.popTitle = '文件删除', // 设置弹出框标题
			base.popIfarmeWidth = '280', // 设置ifarme宽度
			base.popIfarmeHeight = '62'; // 设置ifarme高度
	},

	// 设置编辑管理图册弹出框地址和标题和宽度和高度
	setAlterPop: function() {
		var base = this;

		base.urlValue =  rootPath + '/jsp/common/pop/oneEditPop.jsp'; // 设置弹出框地址
		base.popIfarmeWidth = '338', // 设置ifarme宽度
			base.popIfarmeHeight = '82'; // 设置ifarme高度
	},

	// 上传图片操作
	uploadPop: function(current) {
		var base = this,
			$this = $(current),
			urlValue =  rootPath + '/jsp/common/pop/uploadImg.jsp';
		popTitle = '添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）',
			popIfarmeWidth = '640', // 设置ifarme宽度
			popIfarmeHeight = '324'; // 设置ifarme高度

		$this.find('a').addClass('active').parent().siblings('.btnInfoContent-item').find('a').removeClass('active');
		base.iframePop(popTitle, urlValue, popIfarmeWidth, popIfarmeHeight);

		// 鼠标点击弹出框确认按钮
		$('.pop-box .pop-btn .confirm-btn').on('click', function() {
		
			var s=$(window.frames["iframe-id"].document).find("#test").val();
			alert(s);
		});

		// 鼠标点击取消和关闭按钮，删除弹出框
		$('.pop-box').find('.close, .cancel-btn').on('click', function() {
			$(this).parents('.pop-box').remove();
			$('.pop-background').remove();
		});
	},

	// 删除内容
	removeContent: function(current, popTitle, urlValue, popIfarmeWidth, popIfarmeHeight) {
		var base = this;
		var $this = $(current);

		base.iframePop(popTitle, urlValue, popIfarmeWidth, popIfarmeHeight);

		// 鼠标点击弹出框确认按钮后删除内容
		$('.pop-box .pop-btn .confirm-btn').on('click', function() {
			// 删除管理文章分类中选中内容
			$(current).parent().parent().remove();

			//删除内容后，删除弹出框
			$(this).parents('.pop-box').remove();
			$('.pop-background').remove();
		});

		// 鼠标点击取消和关闭按钮，删除弹出框
		$('.pop-box').find('.close, .cancel-btn').on('click', function() {
			$(this).parents('.pop-box').remove();
			$('.pop-background').remove();
		});
	},

	// 管理图册
	articlesClassNewFun: function(tit, current, popTitle, urlValue, popIfarmeWidth, popIfarmeHeight) {

		var base = this,
			$this = $(current);

		base.iframePop(popTitle, urlValue, popIfarmeWidth, popIfarmeHeight);
		$('iframe').on('load', function() {
			$(this).contents().find('.popForm-item .inputText').val(tit);
		});
		$('.pop-box .cancel-btn').hide();
		$('.pop-box .confirm-btn').css({
			'margin-right': 0
		});

		// 鼠标点击弹出框确认按钮
		$('.pop-box .pop-btn .confirm-btn').on('click', function() {

		});

		// 鼠标点击取消和关闭按钮，删除弹出框
		$('.pop-box').find('.close, .cancel-btn').on('click', function() {
			$(this).parents('.pop-box').remove();
			$('.pop-background').remove();
		});
	},
	// ===================== 其他 ========================

	// 添加模块对应弹出框ifarme
	iframePop: function(popTitle, urlValue, w, h) {
		var base = this;

		$('<div class="pop-background" style="position:absolute;top:0;left:0;z-index:110;width:' + $(window).width() + 'px;height:' + $(document).outerHeight() + 'px;"></div>' +
			'<div class="pop-box" style="position:fixed;top:50%;left:50%;z-index:120;width:' + w + 'px;margin-top:' + -(parseInt(h) + 40 + 65) / 2 + 'px;margin-left:' + -w / 2 + 'px;">' +
			'<div class="pop-title"><h2>' + popTitle + '</h2><div class="close"><a></a></div></div><!--pop-title-->' +
			'<div class="pop-iframe" style="width:' + w + 'px;height:' + h + 'px;">' +
			'<iframe id="iframe-id" name="iframe-id" src="' + urlValue + '" frameborder="0" scrolling="auto" style="width:100%;height:100%;"></iframe>' +
			'</div><!--pop-iframe-->' +
			'<div class="pop-btn"><a class="btn dib btn-blue01 confirm-btn">确定</a><a class="btn dib btn-green01 cancel-btn">取消</a></div><!--pop-btn-->' +
			'</div><!--pop-box-->').appendTo('body');
	},
};