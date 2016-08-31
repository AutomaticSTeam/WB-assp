$(function() {
	
	// 产品
	$('.productList').each(function(){
		var $this = $(this),
			$li = $this.find('li'),
			len = $li.length,
			num = Math.round($this.width()/$li.width());
			for (var i=len; i >= 0; i--) {
				if (i >= len-num) {
					$li.eq(i).css({
						'margin-bottom':0
					});
				}
			}
		
		for (var n=0; n < len; n++) {
			if ((n+1)%num == 0) {
				$li.eq(n).after('<div class="clearfix"></div>');
			}
		}
		
		$li.last().css({
			'margin-bottom':0
		});
		
		$this.css({
			'left':'-12px',
			'width':$this.width()+12
		}).wrap('<div style="overflow:hidden"></div>');
	});

	$('.articleList').css({
		'left':'-44px',
		'width':$('.articleList').width()+44
	}).wrap('<div style="overflow:hidden;position: relative"></div>');
	
	//模块之间距离
	function modelDistance(){
		$('.modelGroup').nextAll('.modelGroup').css({
			'margin-top':'12px'
		});
		$('.modelGroup').next('.content').css({
			'margin-top':'12px'
		});
		
		$('.articleList li').each(function(){
			if($(this).width() <= 324){
				$(this).find('.time').hide();
				$(this).find('.tit').css({
					'margin-right':0
				})
			}
		});
		
		$('.tab .subTit:first').css({'border':0});
	};modelDistance();
	
	
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
			
	};imgText();


	// tab切换
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
	tab();

	// 文章列表模块
	function listModel() {
		$('.article-list03 li:first').addClass('article-list03-li');
	};
	listModel();

	// 导航切换
	function nav() {
		var $nav = $('.nav'),
			$ul = $('.nav ul'),
			$li = $ul.find('li'),
			$menuList1Li = $(".menuList-1 li");
			liWidth = $nav.find('.w').width() / $li.length;
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
			$menuList1Li.find("a").css("padding","0 15px");
		}
		//li个数大于7加左右按钮
		if($li.length>7){
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
	nav();
});
(function($) {
	$.fn.slider = function(options) {
		/* 默认参数 */
		var defaults = {
			duration: 1, // 图片切换时间
			delay: 3, // 图片间隔时间
			autoPlay:true, //是否自动播放
			productNum:6, // 设置一张滑动中，产品个数
			width: '100%',
			height: '100px'
		};
		var settings = $.extend(defaults, options || {});
		
		/* 获取轮播图元素 */
		var $wrapper = $(this),
			$ul = $wrapper.find('.slider-pic > ul'),
			$lis = $ul.find(' > li'),
			$firstPic = $lis.first().find('img'),
			$title = $wrapper.find('.slider-title a'),
			$nav = $wrapper.find('.slider-nav a'),
			$navPic = $nav.find('img');
		var li_num = $lis.size(),
			li_height = 0,
			li_width = 0;
		/* 获取产品元素 */
		var $ulNew = $wrapper.find('ul.productList'),
			$lisNew = $ulNew.find('li'),
			lenNew = $lisNew.length;
		
		/*初始化*/
		var init = function() {
			li_width = settings.width ? settings.width : $firstPic.width();
			li_height = settings.height ? settings.height : $firstPic.height();

			defaultContainerCss();
			defaultState();
			addElement();
			setCss();
			operation();
		};
		
		
		/*默认容器宽度和高度*/
		var defaultContainerCss = function() {
			
			if($firstPic.width() > $wrapper.width()){
				li_width = $wrapper.width();
			}
			
			width_height(li_width, li_height);

			// 当设置大小与容器大小相等时，图片的高度随着容器大小等比例缩放
			if ($wrapper.width() == settings.width && !settings.height) {
				width_height(li_width, $firstPic.height());
			}

			// 需要设置宽度和高度的函数
			function width_height(w, h) {
				$wrapper.width(w + 'px').height(h + 'px');
				$lis.width(w + 'px').height(h + 'px');
				$lis.find('a').width(w + 'px').height(h + 'px');
				$ul.parent().width(w + 'px').height(h + 'px');
				$wrapper.width('100%');
				$lis.width('100%');
				$lis.find('a').width('100%');
				$ul.parent().width('100%');
			}

			// 网站上方轮播图宽度小于 class="w" 宽度时，设置容器宽度与 class="w" 相同，高度等比例缩放
			$('.slider-banner').each(function() {
				var $this = $(this);

				if ($this.width() < $('.w').width()) {
					var w = $('.w').width();
					var h = $this.first().height();

					$this.width(w).height(h);
					$this.find('ul li').width(w).height(h);
					$this.find('ul').parent().width(w).height(h);
				}
			});
			
			// 产品模块高度auto
			$('[data-model="product"]').each(function(){
				$(this).width('auto').height('auto');
			});
			
			
		};
		/* 默认状态 */
		var defaultState = function() {
			$lis.css({
				'position': 'absolute',
				'top': '0',
				'left':'0',
				'z-index':'0',
				'opacity':'0'
			});
			$lis.eq(0).addClass('current').css({
				'z-index':'1',
				'opacity':'1'
			})
			$nav.eq(0).addClass('current').siblings().removeClass('current');
		};

		/* 设置默认样式 */
		var setCss = function() {
			
			//设置轮播图标题z轴大于滑动的图片
			$title.parent().css({
				'z-index': (li_num + 2)
			});
			
			//设置轮播图导航z轴大于滑动的图片
			$nav.parent().css({
				'z-index': (li_num + 2)
			});
			
			// 当轮播图导航图片显示时，样式
			if($navPic.css('display') == 'block'){
				$nav.parent().wrap('<div class="slider-nav-box"></div>'); // 添加一个类名为slider-nav-box父级
				$nav.parent().parent().css({
					'position':'absolute',
					'bottom':'0',
					'left': '50%',
					'width': $wrapper.width() - ($wrapper.find('.slider-prev').width()*2) -24,
					'height':$nav.parent().height(),
					'overflow':'hidden'
					
				});
				$nav.parent().parent().css({
					'margin-left': - $nav.parent().parent().width() / 2
				});
				$nav.parent().css({
					'width':$nav.length * ($nav.outerWidth()+6)
				});
			}
			$wrapper.find('.slider-background').css({
				'z-index': (li_num + 1)
			});
			$wrapper.find('.slider-prev, .slider-next').css({
				'z-index': (li_num + 3)
			});
			
			
			//产品列表样式
			var $productListDiv = $wrapper.find('.productList-div'),
				$ulNew01 = $productListDiv.find('.productList');
			var productListDivWidth = parseInt($productListDiv.width())+12;
			
			
			$ulNew01.css({
				'width':productListDivWidth,
				'float':'left'
			});
			
			$ulNew01.eq(0).nextAll('.productList').css({
				'margin-left':'0'
			})
			
			$productListDiv.css({
				'left':'-12px',
				'width':$ulNew01.width()*$ulNew01.length
			});
			$productListDiv.parent().parent().parent().find('.slider-page').remove();
			$productListDiv.parent().parent().find('.slider-background').remove();
			
			$ulNew01.each(function(){
				var $this = $(this),
				$liNew = $this.find('li'),
				lenNew01 = $liNew.length,
				numNew = Math.round($this.width()/$liNew.width());

				if($this.width() >= numNew*$liNew.width()){
					for (var i=lenNew01; i >= 0; i--) {
						if (i >= lenNew01-numNew) { 
							$liNew.eq(i).css({
								'margin-bottom':0
							});
						}
					}
				}
			})
			
			
		};
		/* 添加导航和背景相对的元素 */
		var addElement = function() {
			$('<div class="slider-background"></div>').appendTo($wrapper);
			$('<div class="slider-page"><a href="javascript:;" class="slider-prev"><span class="slider-pageBackground"></span><span class="slider-pageArrow"><</span></a><a href="javascript:;" class="slider-next"><span class="slider-pageBackground"></span><span class="slider-pageArrow">></span></a></div>').appendTo($wrapper);
			for(var n=0;n<$nav.length;n++){
				$nav.eq(n).append(n+1);
			}
			
			if($navPic.css('display') == 'block'){
				$nav.append('<span class="back"></span>');
			}
		
			
			/* 产品列表添加ul，是ul做为滑动区域 */
			
			$ulNew.wrap('<div class="productList-div"></div>');
			$wrapper.find('.productList-div').wrap('<div style="overflow:hidden;position:relative;"></div>');
			var arr = [];
			
			for(var i = 0; i < lenNew; i++){
				arr[i] = $lisNew.eq(i).html();
			}
			$ulNew.html('');
			for(var i = 0; i < lenNew; i++){
				if(i%settings.productNum==0){
					var e='';
					
					for(var d=0;d<settings.productNum;d++){
						e +='<li>'+arr[i+d]+'</li>';
					}
					$ulNew.parent().append('<ul class="productList">'+e+'</ul>');
					$wrapper.find('.productList li').each(function(){
						var $this = $(this);
						if($this.html()== 'undefined'){
							$this.html('')
						}
					});
				}
			}
			
			$ulNew.each(function(){
				var $this = $(this);
				
				if($this.html() == ''){
					$this.remove();
				}
				
			});
			
			/*产品列表添加轮播图导航*/
			var $productListDiv = $wrapper.find('.productList-div'),
				$ulNew01 = $productListDiv.find('.productList');
				
			var navStart = '<div class="slider-nav"><a class="current"></a>';
			
			for (var i= 1; i<$ulNew01.length;i++) {
				navStart +='<a></a>';
			}
			navEnd = navStart+'</div>';
			$productListDiv.parent().parent().append(navEnd);
		};

		var operation = function() {
			
			var navWidth = $nav.outerWidth(true); // 获取每一张图宽度
			var onegroupWidth = $nav.parent().parent().width(); // 获取1组的宽度
			var oneGroupNum = onegroupWidth/navWidth; //　获取1组的个数
			var len = $nav.length; // 实际图片张数
			var groupNum = Math.ceil(len/oneGroupNum);//　获取组数
			
			/*获取产品轮播图元素*/
			var $productListDiv = $wrapper.find('.productList-div'),
				$ulNew01Width = $productListDiv.find('.productList').width(),
				$ulNew01Len = $productListDiv.find('.productList').length;
			var $navNew = $productListDiv.parent().siblings('.slider-nav').find('a');
			/*获取产品轮播图元素*/

			if(settings.autoPlay){
				start();
				//鼠标经过事件
				$wrapper.find('.slider-pic, .slider-nav, .slider-page, .productList-div').hover(function() {
					stop();
				}, function() {
					$wrapper.data('timeid', window.setTimeout(start, settings.delay * 1000));
				});
			}
			
			// 鼠标点击向右滑动箭头
			$wrapper.find('.slider-next').on('click', function() {
				if($navPic.css('display') == 'block'){
					sliderRight01();
				}else{
					sliderRight();
				}
			});

			// 鼠标点击向左滑动箭头
			$wrapper.find('.slider-prev').on('click', function() {
				if($navPic.css('display') == 'block'){
					sliderLeft01()
				}else{
					sliderLeft();
				}
			});
			
		

			// 鼠标点击轮播图导航
			$nav.on('click', function() {
				i = $(this).index() + 1;
				setAnimate(i);
			});
			
			// 鼠标点击产品轮播图导航
			$navNew.on('click', function() {
				w = $(this).index();
				setAnimate02(w);
			});
			
			var d=0;
			function sliderRight01() {
				d+=1;
				d >= (groupNum-1)?d=(len/oneGroupNum)-1:d;
				setAnimate01(d);
			};
			
			
			function sliderLeft01() {
				d-=1;
				d <= 0?d=0:d;
				setAnimate01(d);
			};
			
			
			var w = 0;
			// 向右滑动
			function sliderRight02() {			
				w >= $ulNew01Len-1 ? w = 0 : w += 1;
				setAnimate02(w);
			};
			
			
			var i = 1;
			// 向右滑动
			function sliderRight() {			
				i >= li_num ? i = 1 : i += 1;
				setAnimate(i);
			};

			// 向左滑动
			function sliderLeft() {
				i <= 1 ? i = li_num : i -= 1;
				setAnimate(i);
			};

			// 设置动画
			function setAnimate(i) {
				if (!$ul.is(':animated')) {
					$nav.eq(i - 1).addClass('current').siblings().removeClass('current');
					$lis.eq(i - 1).addClass('current').stop().animate({
						'z-index': '1',
						'opacity': '1'
					},settings.duration*1000).siblings().removeClass('current').stop().animate({
						'z-index': '0',
						'opacity': '0'
					},settings.duration*1000);
					$title.html($lis.eq(i - 1).find('a').attr('title')); 
				}
			};
			
			function setAnimate01(d){
				$nav.parent().css({'left':-d*onegroupWidth});

			}
			
			// 产品动画
			
			function setAnimate02(w) {
				if(!$productListDiv.is(':animated')){
					$productListDiv.addClass('current').css('position','relative').animate({'left':-(w*$ulNew01Width+12)});
					$navNew.eq(w).addClass('current').siblings().removeClass('current');
				}
			};

			//开始轮播
			
			function start() {
				for (var n = 0; n<len; n++) {
					if((n+1)%4 == 0){
						if ($('#focus02 .slider-nav a.current').index() == n) {
								sliderRight01();
						}
					}
				}
				
				if($('#focus02 .slider-nav a.current').index() >= len-1){
					d = 0;
					setAnimate01(0);
				}
				sliderRight();
				sliderRight02();
				$wrapper.data('timeid', window.setTimeout(start, settings.delay * 1000));
			};

			//停止轮播
			var stop = function() {
				window.clearTimeout($wrapper.data('timeid'));
			};
		};

		var imgLoader = new Image();
		imgLoader.onload = function() {
			imgLoader.onload = null;
			init();
		}
		imgLoader.src = $wrapper.find('.slider-pic > ul li:first, ul.productList li:first').find('img').attr('src');
	};
})(jQuery);