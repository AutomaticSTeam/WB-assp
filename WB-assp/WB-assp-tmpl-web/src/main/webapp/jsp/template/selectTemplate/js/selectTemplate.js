var $templateManage = {
		/**
		 *去模板编辑页
		 */
		toTemplate: function (templateid,templateCode){
			layer.confirm("确定要用该模板？", {icon: 3, title:'提示'}, function(index){
				 layer.close(index);
				 window.location.href=localhostPaht()+'/DL-wms-site-web/site/templateToSite.do?templateId='+templateid+"&templateCode="+templateCode;
			});
		},
		/**
		 *查询点击
		 */
		navClick: function (obj){
			//初始化分页
			initPage();
			var className = obj.attr('class');
			if(className.indexOf('nav-industryType')!=-1){
				$("#industryTypeId").val(obj.attr('id'));
			}else if(className.indexOf('nav-color')!=-1){
				$("#colorId").val(obj.attr('id'));
			}else if(className.indexOf('nav-vedio')!=-1){
				$("#isContainsVedio").val(obj.attr('id'));
			}
			templateDataGrid();
		},
		/**
		 面包屑
		 */
		toBreadcrumb: function (){
			
			var $breadcrumb = $('.breadcrumb'),
				$mainClass = $breadcrumb.find('.main-class'),
				$subClass = $breadcrumb.find('.sub-class'),
				$videoJudge = $breadcrumb.find('.video-judge'),
				$colorClass = $breadcrumb.find('.color-class');
			
			$(document).on("click",".column-nav-class .column-nav-label a",function(){
				var $this = $(this);
				$this.parents('.column-nav-class').hide();
				
				
				var type_index=$this.index();
				var type_index_chiled=$this.parent().next('.column-nav-con').find('a').index();
				if(type_index==0&&type_index_chiled!=0){
					$('#nav-industryType').hide();
					$(".main-class").empty();
					$(".main-class").append("模板&nbsp;&nbsp;>&nbsp;&nbsp;");
					$(".sub-class").empty();
					$(".sub-class").append("全部");
					$("#industryTypeId").val('');
					templateDataGrid();
					$mainClass.html("模板  >  ");
					$subClass.html("全部");
				}else{
					$mainClass.html($this.html());
					var $subz = $this.parent().next('.column-nav-con').find('a').eq(0);
					$subClass.html($subz.html());
					$templateManage.navClick($subz);
				}
				
			});
			
			$(document).on("click",".column-nav-class .column-nav-con a",function(){
				var $this = $(this);
				$this.parents('.column-nav-class').hide();
				
				$mainClass.html($this.parent().prev('.column-nav-label').find('a').eq(0).html());
				$subClass.html($this.html());
				$templateManage.navClick($this);
			});
			
			$(document).on("click",".nav1 a",function(){
				var $this = $(this);
				var index=$this.index();
				$this.parents('.column-nav-class').hide();
				if(index==0){
					var content=$(".breadcrumb span").eq(3);
					if(content){
						$('#nav-vedio').hide();
						$(".video-judge").hide();
						$("#isContainsVedio").val('');
						templateDataGrid();
						$(".breadcrumb span").eq(3).html("");
					}
				}else{
				$videoJudge.removeClass("hide");
				$videoJudge.html('<span class="line"></span>'+$this.html()).css('display','block');
				$templateManage.navClick($this);
				}
			});
			
			$(document).on("click",".nav2 a",function(){
				var $this = $(this);
				var index=$this.index();
				$this.parents('.column-nav-class').hide();
				if(index==0){
					var content=$(".breadcrumb span").eq(3);
					if(content){
						$('#nav-color').hide();
						$(".color-class").addClass("hide");
						$("#colorId").val('');
						templateDataGrid();
						$(".breadcrumb span").eq(4).html("");
					}
				}else{
				var backgroundColor = '';
				$colorClass.removeClass("hide");
				$colorClass.html('<span class="line"></span>'+$this.html()+'<i class="color-block" style="background:'+backgroundColor+'"></i>').css('display','block');
				$templateManage.navClick($this);
				}
			});
		},
		/**
		 *展示图
		 */
		toSearch: function (templateid,templateCode){
			window.location.href = getRootPath()+'/selectTemplate/toSearch.do?templateId=' + templateid + '&templateCode='+templateCode;
		},
		/**
		 *获取企业分类数据
		 */
		setIndustryTypeDatas: function (){
			$.ajax({
				type:'get',
				url:getRootPath()+'/selectTemplate/queryIndustryTypes.do',
				dataType:'json',
				success: function(data){ 
					//先树形化
					var $data = $templateManage.toZtree(data.datas);
					var obj = new Object();
					obj.rows = $data;
					$('#template_industryType_grid').tmpl(eval(obj)).appendTo("#nav-industryType");
					/*$($data).each(function(){
						$("#nav-industryType").append('<a href="javascript:;" id="'+this.id+'" class="nav-industryType">'+this.name+'</a>');
					});*/
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     });
		},
		/**
		 *获取颜色分类数据
		 */
		setColorDatas: function (){
			$.ajax({
				type:'get',
				url:getRootPath()+'/selectTemplate/queryColors.do',
				dataType:'json',
				success: function(data){ 
					//先树形化
					$(data.datas).each(function(){
						$("#nav-color").append('<a href="javascript:;" id="'+this.colorId+'" class="nav-color">'+this.colorName+'</a>');
					});
				},
				error:function(data){
					//alert(JSON.stringify(data));
				}
			});
		},
		/**
		 *收藏模板
		 */
		collectTemplate: function (obj){
			$.ajax({
				type:'post',
				url:getRootPath()+'/selectTemplate/collectTemplate.do',
				data:{templateId:obj.id},
				dataType:'json',
				success: function(data){ 
					if(data.result==1||data.result==2){
						layer.alert('已收藏', {icon: 1});
						//做收藏之后操作...
						$(obj).attr("class","column-collect-already");
						$(obj).attr("title","已收藏");
					}else{
						layer.alert(data.message, {icon: 2});
					}
				},
				error:function(data){
					//alert(JSON.stringify(data));
				}
			});
		},
		/**
		 *收藏模板
		 */
		delCollectTemplate: function (obj){
			$.ajax({
				type:'post',
				url:getRootPath()+'/selectTemplate/delCollectTemplate.do',
				data:{templateId:obj.id},
				dataType:'json',
				success: function(data){ 
				if(data.result==1||data.result==2){
					layer.alert('已移除收藏', {icon: 2});
					//做移除收藏之后操作...
					if($("#showType").val()=='collect'){
						templateDataGrid();
					}else{
						$(obj).attr("class","column-collect");
						$(obj).attr("title","收藏");
					}
				}else{
					layer.alert(data.message, {icon: 2});
				}
			},
			error:function(data){
				//alert(JSON.stringify(data));
			}
			});
		},
		/**
		 *数据树形化
		 */
		toZtree : function(datas){
	    	   
	    	   //只是当前的查询结果，若存在分页，则只能ajax获取所有的数据
	    	   var treeDatas = $.map(datas,function(obj){
	    			return new ZtreeObj(obj.industryTypeId,obj.industryTypePid,obj.industryTypeName);
	    		});
	    	   return $formatToZtree.getTreeNode(treeDatas);
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
function localhostPaht(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    return(localhostPaht);
}

/************************************************分页列表开始**************************************************************/
var templateDataGrid = function(){
	$.ajax({
		type:'post',
		 url:getRootPath()+'/selectTemplate/queryTemplatesByPage.do',
		data:$("#templateForm").serialize(),
		dataType:'json',
		async : true,
		success: function(data){ 
			$('#templateDataGrid').empty();
			var currentPage = data.currentPage;
			var total =data.total;
			var pageCount = data.pages;
			$('#template_data_grid').tmpl(eval(data)).appendTo("#templateDataGrid");
			drawPage(currentPage,pageCount);
			//图片遮罩
			$('.column-image').hover(function(){
				$(this).children('.column-search').fadeIn();
			},function(){
				$('.column-search').hide();
			});
		 },
		error:function(data){
			//alert(JSON.stringify(data));
		}
     });
}
var drawPage = function(pagenumber,pageCount){
	var data = {'pagecount':pageCount};
	$("#pager").pager({ pagenumber: pagenumber, pagecount:data.pagecount, buttonClickCallback: pageClickCallback});
}
var initPage = function(){
	$("#currentPage").val(1);
	$("#rows").val(9);
}

//回调函数
pageClickCallback = function(pageclickednumber) {
	$("#currentPage").val(pageclickednumber);
	templateDataGrid();
}
/************************************************分页列表结束**************************************************************/
