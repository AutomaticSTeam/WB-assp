$(function() {
	Backstage.init();

	// 浏览器调整窗口
	function loadResize() {
		function resizeD() {
			if ($(window).width() <= 1024) {
				$('.main-content').css({
					'margin': '0 20px'
				});
			} else {
				$('.main-content').removeAttr('style');
			}
		}
		resizeD();

		$(window).on('load resize', function() {
			resizeD()
		});

		$('.main-right').height($(window).height() - $('#header').height());
	}
	loadResize();
});

Backstage = {
	init: function() {
		var base = this;

		base.$addContentBtn = $('.addContentBtn'); //  获取管理文章上方“添加文章”按钮Dom
		base.$batchModifyBtn = $('.batchModifyBtn'); // 获取管理文章上方“批量修改”按钮Dom
		base.$setBtn = $('.setBtn'); // 获取管理文章上方“设置为”按钮”按钮Dom
		base.$removeArticleBtn = $('.removeArticleBtn'); // 获取管理文章上方“删除文章”按钮”按钮Dom
		base.$table = $('table'); // 获取tableDom
		base.$tablePic = base.$table.find('.pic'); // 获取table缩略图Dom
		base.$btnTop = base.$table.find('.btn-top'); // 获取table置顶Dom
		base.$articlesClassRemoveBtn = base.$table.find('.articlesClass .del'); // 获取table置顶Dom
		base.$delBtn = $('.delBtn'); // 获取删除文章按钮Dom
		base.$addImgBtn = $('.addImgBtn'); // 获取添加图片按钮Dom
		base.$addArticleClassBtn = $('.addArticleClassBtn'); // 获取添加文章分类按钮Dom
		base.$editArticleClassBtn = $('.btn-editor'); // 获取添加文章分类按钮Dom

		base.attachEvents();
	},
	attachEvents: function() {
		var base = this;

		// 跳到添加文章页面（如需删除，可以删除）
		base.$addContentBtn.on('click', function() {
			$(parent.document).find('iframe').attr('src', $(this).attr('data-url'));
		});

		// 鼠标点击“批量修改”按钮
		base.$batchModifyBtn.on('click', function() {
			base.downContentCss(this);
		}).each(function() {
			base.setAlterPop();
			base.batchModifyItemFun(this, base.urlValue, base.popIfarmeWidth, base.popIfarmeHeight);
		});

		// 鼠标点击“设置为”按钮
		base.$setBtn.on('click', function() {
			base.downContentCss(this);
		}).each(function() {
			base.setClassFun(this);
		});

		// 鼠标点击“删除文章”按钮
		base.$removeArticleBtn.on('click', function() {
			base.setRemovePop();
			base.batchRemoveArticleFun(this, base.popTitle, base.urlValue, base.popIfarmeWidth, base.popIfarmeHeight);
		});

		// 鼠标指针位于缩略图元素上方
		base.$tablePic.on('mouseover', function() {
			base.tablePicFun(this);
		});

		// 鼠标点击置顶
		base.$btnTop.on('click', function() {
			base.topFun(this);
		});

		// 鼠标点击文章分类
		base.$articlesClassRemoveBtn.on('click', function() {
			base.articlesClassFun(this);
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

		// 鼠标点击添加文章分类按钮
		base.$addArticleClassBtn.on('click', function() {
			base.setAlterPop();
			base.articlesClassNewFun(this, '添加文章分类', base.urlValue, base.popIfarmeWidth, base.popIfarmeHeight);
		});

		// 鼠标点击管理文章分类重命名按钮
		base.$editArticleClassBtn.on('click', function() {
			var tit = $(this).parents('tr').find('.tit').text();
			base.setAlterPop();
			base.articlesClassNewFun(tit, this, '重命名文章分类', base.urlValue, base.popIfarmeWidth, base.popIfarmeHeight);
		});

		// checkbox全选
		base.checkboxFun();
	},

	// ============================== 操作 ==============================

	// 设置删除弹出框地址和标题和宽度和高度
	setRemovePop: function() {
		var base = this;

		base.urlValue =  rootPath + '/jsp/common/pop/delPop.jsp'; // 设置弹出框地址
		base.popTitle = '文件删除', // 设置弹出框标题
			base.popIfarmeWidth = '280', // 设置ifarme宽度
			base.popIfarmeHeight = '62'; // 设置ifarme高度
	},

	// 设置编辑文章分类和批量修改弹出框地址和标题和宽度和高度
	setAlterPop: function() {
		var base = this;

		base.urlValue =  rootPath + '/jsp/common/pop/oneEditPop.jsp'; // 设置弹出框地址
		base.popIfarmeWidth = '338', // 设置ifarme宽度
			base.popIfarmeHeight = '82'; // 设置ifarme高度
	},

	// 删除文章内容
	batchRemoveArticleFun: function(current, popTitle, urlValue, popIfarmeWidth, popIfarmeHeight) {
		var base = this;
		var $this = $(current);

		// 判断是否选中checkbox
		if (base.$table.find('td input[type="checkbox"]:checked').length > 0) {
			// true,执行下面内容
			base.iframePop(popTitle, urlValue, popIfarmeWidth, popIfarmeHeight);
		} else {
			// false,执行下面内容
			alert('请选择需要删除的文章');
			$this.parent().removeClass('active');
		}

		// 鼠标点击弹出框确认按钮后删除内容
		$('.pop-box .pop-btn .confirm-btn').on('click', function() {
			// 删除管理文章列表中选中内容
			base.$table.find('input[type="checkbox"]:checked').parents('td').parent().remove();

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

	// 管理文章显示“批量修改”和“设置为”按钮下拉内容
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

	// 管理文章批量修改项弹出修改框
	batchModifyItemFun: function(current, urlValue, popIfarmeWidth, popIfarmeHeight) {
		var base = this;

		$(current).parent().find('.infoContent-item').click(function() {
			var $this = $(this),
				popTitle = '批量修改' + $this.find('a').attr('data-modify'); // 设置弹出框标题

			// 判断是否选中checkbox
			if (base.$table.find('td input[type="checkbox"]:checked').length > 0) {
				// true，执行下面内容
				base.iframePop(popTitle, urlValue, popIfarmeWidth, popIfarmeHeight);
				$('.pop-box .cancel-btn').hide();
				$('.pop-box .confirm-btn').css({
					'margin-right': 0
				});
				$('iframe').on('load', function() {
					$(this).contents().find('.oneEditPop .popForm-item label').html($this.find('a').attr('data-modify') + '：');
				});
				$this.parents('.btn-item').removeClass('active');

			} else {
				// false，执行下面内容
				alert('请选择需要修改的文章');
				$this.parents('.btn-item').removeClass('active');
			}

			// 鼠标点击弹出框确认按钮
			$('.pop-box .pop-btn .confirm-btn').on('click', function() {

			});

			// 鼠标点击取消和关闭按钮，删除弹出框
			$('.pop-box').find('.close, .cancel-btn').on('click', function() {
				$(this).parents('.pop-box').remove();
				$('.pop-background').remove();
			});
		});
	},

	// 管理文章设置为按钮操作
	setClassFun: function(current) {
		var $this = $(current),
			$confirmBtn = $('.setInfoContent .confirm-btn'),
			$cancelBtn = $('.setInfoContent .cancel-btn');

		//鼠标点击确认按钮
		$confirmBtn.click(function() {
			if ($('.setInfoContent input[type="checkbox"]:checked').length > 0) {
				$this.parent().removeClass('active');
			} else {
				alert('请选中分类');
			}
		});

		//鼠标点击取消按钮
		$cancelBtn.click(function() {
			$this.parent().removeClass('active');
		});
	},

	// 管理文章是否全选
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

	// 管理文章列表缩略图显示和隐藏
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

	// 管理文章置顶
	topFun: function(current) {
		if ($(current).hasClass('btn-top-selected')) {
			$(current).removeClass('btn-top-selected');
		} else {
			$(current).addClass('btn-top-selected');
		}
	},

	// 管理文章删除文章分类
	articlesClassFun: function(current) {
		$(current).parent().remove();
	},

	// 上传图片操作
	uploadPop: function(current) {
		var base = this,
			$this = $(current),
			urlValue = rootPath + '/jsp/common/pop/uploadImg.jsp';
		popTitle = '添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）',
			popIfarmeWidth = '640', // 设置ifarme宽度
			popIfarmeHeight = '324'; // 设置ifarme高度

		$this.find('a').addClass('active').parent().siblings('.btnInfoContent-item').find('a').removeClass('active');
		base.iframePop(popTitle, urlValue, popIfarmeWidth, popIfarmeHeight);

		// 鼠标点击弹出框确认按钮
		$('.pop-box .pop-btn .confirm-btn').on('click', function() {

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

	// 管理文章分类
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
			'<iframe src="' + urlValue + '" frameborder="0" scrolling="auto" style="width:100%;height:100%;"></iframe>' +
			'</div><!--pop-iframe-->' +
			'<div class="pop-btn"><a class="btn dib btn-blue01 confirm-btn">确定</a><a class="btn dib btn-green01 cancel-btn">取消</a></div><!--pop-btn-->' +
			'</div><!--pop-box-->').appendTo('body');
	},
};