/**站点编辑
 * 
 * */
var QUERY_FRAMES_PATH  = rootPath+'/site/queryPageFrame.do'; // 查询框架链接
var QUERY_MODULES_PATH  = rootPath+'/site/loadDynamicWebsiteDatas.do'; // 查询框架链接
var QUERY_MODULE_PATH  = rootPath+'/site/loadDynamicModuleDatas.do'; // 查询框架链接
var QUERY_ROOT_PATH = rootPath+'/site/'; 
var Site = {
		/**url跳转入口
		 * 
		 * */
		URLHref: function(type,id,url){
			if(url != 'null' && url != null && url != ""){ // 导航不为空跳转
				
				Site.href(rootPath + url,"_blank");
				
			}else{
				switch(type){
					case 'nav':
						window.location.href = QUERY_ROOT_PATH + 'c/' + id + '.html';
						break;
					case 'article':
						Site.href(QUERY_ROOT_PATH + 'loadContentPageId.do?articleId=' + id ,"_blank");
						break;
					case 'picture':
						Site.href(QUERY_ROOT_PATH + 'loadContentPage.do?mediaId=' + id ,"_blank");
						break;
					case 'media':
						Site.href(QUERY_ROOT_PATH + 'loadContentPage.do?pictureId=' + id ,"_blank");
						break;
				}
			}
		},
		href: function(url,target){
			var a_ = document.getElementById("zdtj"); 
			if(a_ == void 0){
				a_ = document.createElement("a");
				var body = document.getElementsByTagName("body");
				body[0].appendChild(a_);
			}
		    a_.setAttribute("href",url);
		    a_.setAttribute("id","zdtj");
		    a_.setAttribute("target",target);
		    a_.click();
		},
		/**做监听
		 * */
		listenBtn: function(module){
			// 内容按钮
			$(document).on("click",".btn-content",function(){
				var li_ = $("li").has(this);
				Site.URLHref(li_.attr("data-type"),li_.attr("data-url"),li_.attr("data-extendurl"));
			});
		},
		NavHref: function(url){
			if(url != 'null' && url != null && url != ""){ // 导航不为空跳转
				location.href =  rootPath + url;
			}else{
				layer.alert('很抱歉，敬请期待...', {icon: 5});
			}
		}
}