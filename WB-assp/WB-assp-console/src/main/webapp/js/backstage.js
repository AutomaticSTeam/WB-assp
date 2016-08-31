$(function() {
	
	
	$(window).on('load resize', function() {
		loadResize();
		tableWidth();
	});

	function tableWidth() {
		var $tableBody = $('#managementArticles .table .table-body'),
			$table = $('#managementArticles .table table');

		if ($tableBody.width() < 1200) {
			$table.width('1200px');
			$tableBody.css({
				'overflow-x': 'auto',
				'overflow-y': 'hidden'
			});
		} else {
			$table.width('100%');
		}
	}
	tableWidth();

	// 浏览器调整窗口
	function loadResize() {
		if ($(window).width() <= 1024) {
			$('.main-content').css({
				'margin': '0 20px'
			});
		} else {
			$('.main-content').removeAttr('style');
		}
		$('.main-right').height($(window).height() - $('#header').height());
	}
	loadResize();
	
	$('.search .search-item:last').css({'margin-right':0});
});