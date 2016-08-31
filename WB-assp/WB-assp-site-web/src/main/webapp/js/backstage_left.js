$(function() {
	
	$("div.menuList-item").eq(0).addClass("active");
	var url = $("a[data-url]").eq(0).attr("data-url");
	$("#iframe01").attr("src",url);
	
	$('.main-right').height($(window).height() - $('#header').height());
	$('.search .search-item:last').css({
		'margin-right': 0
	});

	nav();
	
});

function nav() {
	var $menuList = $('.nav .menuList-item');
	var $menuListA = $('.nav .menuList-content-item a');

	$menuList.each(function() {
		var $this = $(this);

		if ($this.hasClass('active')) {
			navCss(this);
		}
	});

	$menuList.click(function() {
		var $this = $(this);

		if ($this.hasClass('active')) {
			$this.next().stop().slideUp(50);
			$this.removeClass('active');
			$this.find('.arrow').css({
				'transform': 'rotate(0deg)',
				'transition': 'All 0.5s ease'
			});
		} else {
			navCss(this);
		}
	});

	function navCss(current) {
		var $this = $(current);
		
		$this.addClass('active');
		$this.next().stop().slideDown(50);
		$this.find('.arrow').css({
			'transform': 'rotate(90deg)',
			'transition': 'All 0.5s ease'
		});
	};

	// 跳转相关页面（如需删除，可以删除）
	$menuListA.click(function() {
		$menuListA.removeClass('active');
		$(this).addClass('active');
		$(parent.document).find('iframe').attr('src', $(this).attr('data-url'));
	});
	
	
	
};
//ifram页面控制左侧菜单变化
function changeLeftMenuCss(type){
	
	var $menuList = $('.nav .menuList-item');
	var $menuListA = $('.nav .menuList-content-item a');
	if(type!=""){
		$menuListA.removeClass('active');
		switch(type){
		//添加文章
		case 1:
			$("#addArticle").addClass('active');
		  break;
		//创建图册
		case 2:
			$("#createOrEditAlbum").addClass('active');
		  break;
		//添加视频
		case 3:
			$("#createOrEditVedio").addClass('active');
		 break;
		//管理视频
		case 4:
			$("#vedioManage").addClass('active');
		 break;
		//管理图册
		case 5:
			$("#albumManage").addClass('active');
		 break;
		default:
		  
		}
	}
}
