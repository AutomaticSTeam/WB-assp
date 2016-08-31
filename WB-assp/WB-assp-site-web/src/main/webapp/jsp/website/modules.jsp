<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>


<script id="module_footer_tmpl" type="text/html"> 
<div class="footer">
	<div class="links">
		<div class="w">
			<div data-model="imgText" data-index="1">
				<div class="imgText">
                      	{{each templateFooter.templateColumns as column i}}
                               {{if  column.customUrl == null ||  column.customUrl == '' }}
									<a href="javascript:Site.URLHref('nav',{{column.columnsId}},'{{column.customUrl}}');">{{column.columnsName}}</a>
                               {{/if}}
                               {{if column.customUrl != null && column.customUrl != '' }}
									<a href="javascript:Site.URLHref('nav',{{column.columnsId}},'{{column.customUrl}}');">{{column.columnsName}}</a>
                               {{/if}}
                               {{if  i !=  (templateFooter.templateColumns.length - 1) }}
 									<span> | </span>
                               {{/if}}
	                   {{/each}} 
				</div>
			</div>
		</div>
	</div>
	<div class="footer-body" data-model="footer" data-index="2">
		<div class="w">
			<div class="copyright">
				<div class="copyright-body">
					<div id="copyright-content">
					{{#templateFooter.copyrightInfo}}
					</div>
					<p>技术支持：
					        <span {{if  templateFooter.isShowTechnicalSupport == 1}} style="display:none;"   {{/if}}><a href="#">wms建站</a>  | </span> 
					          <span  {{if  templateFooter.isShowMobileEdit == 1}} style="display:none;"   {{/if}} ><a href="#">手机版 </a>  |   </span> 
					          <span {{if  templateFooter.isShowAdminLogin == 1}} style="display:none;"   {{/if}}  ><a >    管理登录</a> </span> 
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
</script>

<script id="module_nav_tmpl" type="text/html"> 
<div data-model="nav" class="nav">
	<div class="navBtn">
					<span class="navBtnPre"><</span>
					<span class="navBtnNext">></span>
	</div>
	<div class="w">
		<ul class="menuList">
				{{each templateColumns as templateColumn i}}
				   {{if templateColumn.columnsPid == 0 }}
				           <li  id="column{{templateColumn.columnsId}}">
		                           <a class="menuList-item" href="javascript:Site.URLHref('nav',{{templateColumn.columnsId}},'{{templateColumn.customUrl}}');" title="">
		                             <span>
										<img src="{{templateColumn.columnsIcon }}" alt="" class="icon" />
										<img src="{{templateColumn.columnsIconActive  }}" alt="" class="iconHover hide" />
									</span>
		                            <span>{{templateColumn.columnsName }}	</span>
		                         </a>
		                         {{if templateColumn.subTemplateColumns != null }}
		                            <div class="subMenu">   
		                                {{each templateColumn.subTemplateColumns as tColumn i}}
		                                       <div class="subMenu-item" id="column{{tColumn.columnsId}}"><a href="javascript:Site.URLHref('nav',{{tColumn.columnsId}},'{{tColumn.customUrl}}');" title="">{{tColumn.columnsName }}</a></div>
		                                {{/each}} 
		                            </div>
		                         {{/if}}
		                      </li>    
				    {{/if}}
				{{/each}} 
		</ul>
	</div>
</div>
</script>

<script id="module_nav01_tmpl" type="text/html"> 
<div data-model="nav" class="nav nav-1">
	<div class="w">
		<ul class="menuList">
				{{each templateColumns as templateColumn i}}
				   {{if templateColumn.columnsPid == 0 }}
				           <li  id="column{{templateColumn.columnsId}}">
		                           <a class="menuList-item" href="javascript:Site.URLHref('nav',{{templateColumn.columnsId}},'{{templateColumn.customUrl}}');" title="">
		                             <span>
										<img src="{{templateColumn.columnsIcon }}" alt="" class="icon" />
										<img src="{{templateColumn.columnsIconActive  }}" alt="" class="iconHover hide" />
									</span>
		                            <span>{{templateColumn.columnsName }}	</span>
		                         </a>
		                         {{if templateColumn.subTemplateColumns != null }}
		                            <div class="subMenu">   
		                                {{each templateColumn.subTemplateColumns as tColumn i}}
		                                       <div class="subMenu-item" id="column{{tColumn.columnsId}}"><a href="javascript:Site.URLHref('nav',{{tColumn.columnsId}},'{{tColumn.customUrl}}');" title="">{{tColumn.columnsName }}</a></div>
		                                {{/each}} 
		                            </div>
		                         {{/if}}
		                      </li>    
				    {{/if}}
				{{/each}} 
		</ul>
	</div>
</div>
</script>

<script id="module_logo_tmpl" type="text/html"> 
		<div data-model="logo" class="logo">
				<div class="logo-box" id="{{templateLogo.templateLogoId}}">
							<a href=""><img  id="logoImg" src="{{templateLogo.logoImgUrl}}"  alt="logo" title="logo" width="{{templateLogo.imgWidth}}" height="{{templateLogo.imgHeight}}"/></a>
			</div>
			<div class="logo-text">
							<p id="fisrtTitle">{{templateLogo.fisrtTitle}}</p>
							<p class="tit2" id="secondTitle">{{templateLogo.secondTitle}}</p>
			</div>
		</div>
</script>

<script id="module_add_tmpl" type="text/html"> 
<div data-model="addModule" class="model-background">
	<span class="add-icon"></span>
		<span class="modelAdd" data-show><i class="add"></i><i>添加模块</i></span>
</div>
</script>
<div id="modelAdd_tip" style="display:none;">
<div class="pop-form" style="margin: 20px 20px;">
<div class="popForm-item">
					<label>模块类型：</label>
					<div class="label-con">
						<select class="select" name="modelAddType" id="modelAddType">
	<option value="1">文章</option>
	<option value="2">图册</option>
	<option value="3">视频</option>
</select>
					</div>
				</div>
</div>
</div>
<div id="modelAddCompositeType_tip" style="display:none;">
<div class="pop-form" style="margin: 20px 20px;">
<div class="popForm-item">
					<label>模块版式：</label>
					<div class="label-con">
						<select class="select" name="modelAddCompositeType" id="modelAddCompositeType">
							<option value="1">单模块</option>
							<option value="2">模块组-标签切换</option>
							<option value="3">模块组-竖排</option>
						</select>
					</div>
				</div>
</div>
</div>

<script id="module_title_tmpl" type="text/html"> 
	<div class="tit">
		<h2>{{moduleName}}</h2>
	</div>
	<div class="subTit" style="display: none;">
		<a href="">子标题</a>
	</div>
</script>

<script id="module_titles_tmpl" type="text/html"> 
{{each moudles as value i}}
{{if  moudles.length == 1 || (moudles.length > 1 && i != 0) }}
    <div class="tab-item " data-tab="item{{i}}" id="tab-item-{{value.moduleId}}">
  {{/if}}
{{if  moudles.length > 1 && i == 0 }}
    <div class="tab-item current" data-tab="item{{i}}" id="tab-item-{{value.moduleId}}">
  {{/if}}
	<div class="tit">
		<h2>{{value.moduleName}}</h2>
	</div>
	<div class="subTit" style="display: none">
		<a href="">子标题</a>
	</div>
</div>
{{/each}} 
{{each moudles as moudle i}}
      {{if  moudle.extendUrl == null || moudle.extendUrl == "" }}
	     <a  class="tab-more item{{i}}">>></a>
     {{/if}}
      {{if  moudle.extendUrl != null && moudle.extendUrl != "" }}
	     <a href="javascript:Site.URLHref ('','','{{moudle.extendUrl}}')" class="tab-more item{{i}}">>></a>
     {{/if}}
{{/each}}
</script>

<script id="module_imgtxt6_tmpl" type="text/html"> 
<div data-model="imgText" class="imgText-6">
{{each articles as article i}}
	<div class="imgText">
		<div class="imgText-img">
			<img src="{{article.articleAttachmentImg }}"  width="230" height="280" alt="" /><span class="imgText-tit">{{article.articleTitle }}</span>
		</div>
		<div class="imgText-content">

			<!--用户自定义内容start-->
			
                    {{#article.articleNtro }}
           
			<!--用户自定义内容end-->

		</div>
	</div>
  	 {{/each}}
</div>
</script>

<script id="module_imgtxt_tmpl" type="text/html"> 
<div data-model="imgText" class="imgText-1">
		{{each articles as article i}}
                {{if i < pageSize}}
				<div class="imgText">
					<div class="imgText-img">
						<img src="{{article.articleAttachmentImg}}" alt="" />
						<span class="imgText-tit"></span>
					</div>
					<div class="imgText-content">
						<a href="javascript:Site.URLHref ('article','{{article.articleId }}','');">{{article.articleTitle }}</a>
					</div>
				</div>
               {{/if}}
		 {{/each}}
		<div class="imgText">
			<div class="imgText-img">
				<img src="" alt="" />
				<span class="imgText-tit"></span>
			</div>
			<div class="imgText-content">
			{{each pictures as picture i}}
				{{if i < pageSize}}
					<a href="javascript:Site.URLHref ('picture','{{picture.pictureId }}','');"><img  src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}"/></a>
                {{/if}}
			  {{/each}}
			</div>
		</div>
</div>
</script>

<script id="module_imgtxt_list_tmpl" type="text/html"> 

<div data-model="list" class="articleList-1 layout-list1">
	<ul class="articleList">
	{{each articles as article i}}
           {{if i < pageSize}}
		<li data-type="article" data-url="{{article.articleId }}">
			<div class="img">
				<img src="{{article.articleAttachmentImg}}" alt="" />
			</div>
			<div class="con">
				<div class="tit">
					<a href="javascript:;" class="btn-content">{{article.articleTitle }}</a>
				</div>
				<div class="txt">{{article.articleNtro}}</div>
				<div class="time">{{article.createTime | dateFormat:7}}</div>
			</div>
		</li>
        {{/if}}
     {{/each}}
	</ul>
</div>
</script>

<script id="module_productinfo_list_tmpl" type="text/html">
<div data-model="product" class="productList-1 layout-list{{=  moduleItemLineNum}}">
	<ul class="productList productListCss-1">
       {{each pictures as picture i}}
         {{if i < pageSize && picture != null}}
		<li data-type="picture" data-url="{{picture.pictureId }}">
			<div class="img">
            	<a href="javascript:;" class="btn-content"><img src="{{picture.pictureUrl}}"  alt="{{picture.briefDesc}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
			</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{picture.pictureName}}</a></div>
			</div>
	</li>
     {{/if}}
      {{/each}}
	{{each medias as media i}}
         {{if i < pageSize}}
		<li data-type="media" data-url="{{media.mediaId }}">
            <div class="img">
                     <a href="javascript:;" class="btn-content"><img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
		    </div>
      <div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{= media.mediaName}}</a></div>
			</div>
	<div class="item" style="display:none;">
		<span class="time"><i class="timeIcon"></i>01:58:00</span>
		 {{if media.readNum < 10000 }}
	    	<span class="num">人气：{{= media.readNum}}</span>
		 {{/if}}
		 {{if media.readNum >= 10000 }}
	    	<span class="num">人气：{{= media.readNum/10000}}万</span>
		 {{/if}}
	</div>
	
	</li>
      {{/if}}
      {{/each}}
    {{each articles as article i}}
      {{if i < pageSize}}
		<li data-type="article" data-url="{{article.articleId }}">
          <div class="img">
            <a href="javascript:;" class="btn-content"><img src="{{article.articleAttachmentImg}}"  alt="{{article.articleTitle}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
         </div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{article.articleTitle }}</a></div>
			</div>
	
	</li>
  {{/if}}
      {{/each}}
	</ul>
</div>
</script>

<script id="module_foucs_productinfo_list_tmpl" type="text/html">
<div data-model="product" id="product01" class="productList-1 layout-list{{=  moduleItemLineNum}}">
	<ul class="productList productListCss-1">
       {{each pictures as picture i}}
         {{if i < pageSize && picture != null}}
		<li data-type="picture" data-url="{{picture.pictureId }}">
			<div class="img">
            	<a href="javascript:;" class="btn-content"><img src="{{picture.pictureUrl}}"  alt="{{picture.briefDesc}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
			</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{picture.pictureName}}</a></div>
			</div>
	</li>
     {{/if}}
      {{/each}}
	{{each medias as media i}}
         {{if i < pageSize}}
		<li data-type="media" data-url="{{media.mediaId }}">
            <div class="img">
                     <a href="javascript:;" class="btn-content"><img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
		    </div>
      <div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{= media.mediaName}}</a></div>
			</div>
	<div class="item" style="display:none;">
		<span class="time"><i class="timeIcon"></i>01:58:00</span>
		 {{if media.readNum < 10000 }}
	    	<span class="num">人气：{{= media.readNum}}</span>
		 {{/if}}
		 {{if media.readNum >= 10000 }}
	    	<span class="num">人气：{{= media.readNum/10000}}万</span>
		 {{/if}}
	</div>
	
	</li>
      {{/if}}
      {{/each}}
    {{each articles as article i}}
      {{if i < pageSize}}
		<li data-type="article" data-url="{{article.articleId }}">
          <div class="img">
            <a href="javascript;" class="btn-content"><img src="{{article.articleAttachmentImg}}"  alt="{{article.articleTitle}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
         </div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{article.articleTitle }}</a></div>
			</div>
	
	</li>
  {{/if}}
      {{/each}}
	</ul>
</div>
</script>

<script id="module_recommend_list_tmpl" type="text/html"> 
<div data-model="product" class="productList-1 layout-list{{= moduleItemLineNum}}">
	<ul class="productList productListCss-1">
       {{each pictures as picture i}}
         {{if i < pageSize}}
		<li data-type="picture" data-url="{{picture.pictureId }}">
			<div class="img">
            	<a href="javascript:;" class="btn-content" target="_blank"><img src="{{picture.pictureUrl}}"  alt="{{picture.briefDesc}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
			</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{picture.briefDesc}}</a></div>
			</div>
	</li>
     {{/if}}
      {{/each}}
	{{each medias as media i}}
         {{if i < pageSize}}
		<li data-type="media" data-url="{{media.mediaId }}">
             <div class="img">
                  <a href="javascript:;" class="btn-content"><img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
	     	</div>
           <div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{= media.mediaName}}</a></div>
			</div>
	</li>
      {{/if}}
      {{/each}}
    {{each articles as article i}}
      {{if i < pageSize}}
		<li data-type="article" data-url="{{article.articleId }}">
        <div class="img">
            <a href="javascript:;" class="btn-content"><img src="{{article.articleAttachmentImg}}"  alt="{{article.articleTitle}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
       	</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{article.articleTitle }}</a></div>
			</div>
	</li>
  {{/if}}
      {{/each}}
	</ul>
</div>
</script>


<script id="module_product_list_tmpl" type="text/html"> 
<div data-model="product" class="productList-1 layout-list4">
	<ul class="productList productListCss-1">
       {{each pictures as picture i}}
		<li data-type="picture" data-url="{{picture.pictureId}}">
			<div class="img">
            	<a href="javascript:;" class="btn-content"><img src="{{picture.pictureUrl}}"  alt="{{picture.briefDesc}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
			</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{picture.briefDesc}}</a></div>
			</div>
<div class="item" style="display:none">
			<span class="time"><i class="timeIcon"></i>01:58:00</span>
			<span class="num">人气：581万</span>
			</div>
	</li>
      {{/each}}
    {{each medias as media i}}
		<li data-type="media" data-url="{{media.mediaId }}">
		<div class="img">
            <a href="javascript:void(0);" class="btn-content"><img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
		</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{= media.mediaName}}</a></div>
			</div>
<div class="item" style="display:none">
			<span class="time"><i class="timeIcon"></i>01:58:00</span>
			<span class="num">人气：581万</span>
			</div>
	</li>
      {{/each}}
    {{each articles as article i}} 
		<li data-type="article" data-url="{{article.articleId }}">
		<div class="img">
            <a href="javascript:;" class="btn-content"><img src="{{article.articleAttachmentImg }}"  alt="{{article.articleTitle }}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
		</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{article.articleTitle }}</a></div>
			</div>
<div class="item" style="display:none">
			<span class="time"><i class="timeIcon"></i>01:58:00</span>
			<span class="num">人气：581万</span>
			</div>
	</li>
      {{/each}}
	</ul>
</div>
</script>

<script id="module_foucs_pictures_tmpl" type="text/html"> 
<div id="focus02" data-model="focus" class="slider slider-2">
<div class="slider-pic">
	<ul>
       {{each pictures as picture i}}
             <li data-type="picture" >
			        {{if  picture.hyperlink == null ||  picture.hyperlink == ""}}
                            <a href=""  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" /></a>
                 {{/if}}
                  {{if  picture.hyperlink != null && picture.hyperlink != ""}}
                             <a target="_blank" href="{{picture.hyperlink}}"  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
                 {{/if}}
			  </li>
		{{/each}}
	</ul>
</div>
<div class="slider-title"><a href=""></a></div>
<div class="slider-nav">
   {{each pictures as picture i}}
             <a><img src="" alt=""  /></a>
		{{/each}}
</div>
</div>
</script>

<script id="module_foucs_thumbnail_tmpl" type="text/html"> 
<div id="focus02" data-model="focus" class="slider slider-3">
<div class="slider-pic">
	<ul>
       {{each pictures as picture i}}
 			{{if  picture != null }}
             <li data-type="picture">
			        {{if  picture.hyperlink == null ||  picture.hyperlink == ""}}
                         <a href=""  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
                 {{/if}}
                  {{if  picture.hyperlink != null && picture.hyperlink != ""}}
                          <a target="_blank" href="{{picture.hyperlink}}"  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
                 {{/if}}
			  </li>
			{{/if}}
		{{/each}}
	</ul>
</div>
<div class="slider-title"><a href=""></a></div>
<div class="slider-nav">
   {{each pictures as picture i}}
             <a><img src="{{picture.pictureUrl}}" alt=""  width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
		{{/each}}
</div>
</div>
</script>

<script id="module_banner_tmpl" type="text/html"> 
<div id="focus01" data-model="focus" class="slider slider-banner slider-1">
<div class="slider-pic">
	<ul>
       {{each pictures as picture i}}
             <li data-type="picture" id="{{picture.pictureId}}">
			        {{if  picture.hyperlink == null ||  picture.hyperlink == ""}}
                         <a href=""  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
                 {{/if}}
                  {{if  picture.hyperlink != null && picture.hyperlink != ""}}
                          <a href="{{picture.hyperlink}}" target="_blank" title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
                 {{/if}}
			  </li>
		{{/each}}
	</ul>
</div>
<div class="slider-title"><a href=""></a></div>
<div class="slider-nav">
   {{each pictures as picture i}}
             <a><img src="{{picture.pictureUrl}}" alt=""  width="{{moduleStyleWidth}}" height="{{moduleStyleHeight}}"/></a>
		{{/each}}
</div>
</div>
</script>

<script id="module_article_tmpl" type="text/html">
	<div data-model="contentArticle" data-url="{{#articleId}}" data-show="module_article_tmpl" data-type="2" class="productList-1 layout-list">
		<div id="content" class="w">
				<div class="content-head">
					 	<h1>{{#articleTitle }}</h1>
					 	<div class="content-bar">
							<span class="time"><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="{{#createTime}}" /></span>
							<span class="source">来源： {{#articleSource}}</span>
						</div>
				</div>
				<div class="content-body">					
					<div class="content">
				   <p>简介：</p>
						<p>
							{{#articleNtro}}
						</p>
						{{#articleContent}}
					</div>
				</div>
			
		</div>
	</div>		
</script>

<script id="module_media_tmpl" type="text/html">
	<div data-model="contentMedia" data-url="{{#mediaId}}" data-show="module_media_tmpl" data-type="9" class="productList-1 layout-list">
		<div id="content" class="w" >
				<div class="content-head">
					 	<h1>{{#mediaName }}</h1>
					 	<div class="content-bar">
							<span class="time"><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="{{#createTime}}" /></span>
							<span class="source">来源： {{#mediaSource}}</span><br/>
						</div>
				</div>
				<div class="content-body">					
					<div class="content zIndex1">
				   		 <p>简介：</p>
						<p>
							{{#mediaBriefing}}
						</p> 
					<%--  	<video src="${media.mediaUrl}" controls="controls" style="width:100%;">您的浏览器不支持 video 标签。</video> --%>
							<div id="container"></div>
							<input type="hidden" value="{{#mediaUrl}}" id="mediaUrl"> 
					</div>
				</div>
		</div>
	</div>
</script>

<script id="module_picture_tmpl" type="text/html">
	<div data-model="contentPicture" class="productList-1 layout-list" data-type="7">
		<div id="content" >
				<div class="content-head">
					 <h1>{{#pictureName }}</h1>
					<div class="content-bar">
						<span class="time"><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="{{#createTime}}" /></span>
					</div>
				</div>
				<div class="content-body">					
					<div class="content">
				   		<p>简介：</p>
						<p>
							{{#briefDesc}}
						</p>
							<img style="-webkit-user-select: none" src="{{#pictureUrl}}">
					</div>
				</div>
		</div>
	</div>
</script>
<script id="module_foucs_productinfo_list01_tmpl" type="text/html">
<div data-model="product" id="product02" class="productList-1 productList-{{=  moduleItemLineNum}} layout-list{{=  moduleItemColumnNum}}">
	<ul class="productList">
       {{each pictures as picture i}}
         {{if i < pageSize && picture != null}}
			<li>
			<div class="img">
			<a href=""><img src="{{picture.pictureUrl}}" alt=""></a>
			</div>
	<div class="con">
		<div class="back"></div>
		<div class="tit">
			<a>{{picture.pictureName}}</a>
		</div>
		<div class="describe">{{picture.briefDesc}}</div>
		<div class="txt"></div>
	</div>
	<div class="item" style="display: none;">
		<span class="time"><i class="timeIcon"></i>01:58:00</span> <span
			class="num">人气：581万</span>
	</div>
</li>	
     {{/if}}
      {{/each}}
	{{each medias as media i}}
         {{if i < pageSize}}
		<li data-type="media" data-url="{{media.mediaId }}">
            <div class="img">
                     <a href="javascript:;" class="btn-content"><img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" /></a>
		    </div>
      <div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{= media.mediaName}}</a></div>
			</div>
	<div class="item" style="display:none;">
		<span class="time"><i class="timeIcon"></i>01:58:00</span>
		 {{if media.readNum < 10000 }}
	    	<span class="num">人气：{{= media.readNum}}</span>
		 {{/if}}
		 {{if media.readNum >= 10000 }}
	    	<span class="num">人气：{{= media.readNum/10000}}万</span>
		 {{/if}}
	</div>
	
	</li>
      {{/if}}
      {{/each}}
    {{each articles as article i}}
      {{if i < pageSize}}
		<li data-type="article" data-url="{{article.articleId }}">
          <div class="img">
            <a href="javascript;" class="btn-content"><img src="{{article.articleAttachmentImg}}"  alt="{{article.articleTitle}}" /></a>
         </div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{article.articleTitle }}</a></div>
			</div>
	
	</li>
  {{/if}}
      {{/each}}
	</ul>
</div>
</script>
<script id="module_foucs_productinfo_list02_tmpl" type="text/html">
<div data-model="product" id="product03" class="productList-1 productList-{{=  moduleItemLineNum}} layout-list{{=  moduleItemColumnNum}}">
	<ul class="productList">
       {{each pictures as picture i}}
         {{if i < pageSize && picture != null}}
			<li>
			<div class="img">
			<a href=""><img src="{{picture.pictureUrl}}" alt=""></a>
			</div>
	<div class="con">
		<div class="back"></div>
		<div class="tit">
			<a>{{picture.pictureName}}</a>
		</div>
		<div class="describe">{{picture.briefDesc}}</div>
		<div class="txt"></div>
	</div>
	<div class="item" style="display: none;">
		<span class="time"><i class="timeIcon"></i>01:58:00</span> <span
			class="num">人气：581万</span>
	</div>
</li>	
     {{/if}}
      {{/each}}
	{{each medias as media i}}
         {{if i < pageSize}}
		<li data-type="media" data-url="{{media.mediaId }}">
            <div class="img">
                     <a href="javascript:;" class="btn-content"><img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" /></a>
		    </div>
      <div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{= media.mediaName}}</a></div>
			</div>
	<div class="item" style="display:none;">
		<span class="time"><i class="timeIcon"></i>01:58:00</span>
		 {{if media.readNum < 10000 }}
	    	<span class="num">人气：{{= media.readNum}}</span>
		 {{/if}}
		 {{if media.readNum >= 10000 }}
	    	<span class="num">人气：{{= media.readNum/10000}}万</span>
		 {{/if}}
	</div>
	
	</li>
      {{/if}}
      {{/each}}
    {{each articles as article i}}
      {{if i < pageSize}}
		<li data-type="article" data-url="{{article.articleId }}">
          <div class="img">
            <a href="javascript;" class="btn-content"><img src="{{article.articleAttachmentImg}}"  alt="{{article.articleTitle}}" /></a>
         </div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{article.articleTitle }}</a></div>
			</div>
	
	</li>
  {{/if}}
      {{/each}}
	</ul>
</div>
</script>

<script id="module_imgtxtContent_tmpl" type="text/html"> 
<div data-model="imgText" class="imgText-6">
{{each articles as article i}}
  <div class="imgText" data-type="article" data-url="{{article.articleId }}">
    <div class="imgText-img">
    <!--  <img src="{{article.articleAttachmentImg }}"  width="230" height="280" alt="" /><span class="imgText-tit">{{article.articleTitle }}</span>-->
    </div>
    <div class="imgText-content">
      {{#article.articleContent}}
      <!--用户自定义内容start-->
                 <!--   {{#article.articleNtro }} -->
      <!--用户自定义内容end-->
    </div>
  </div>
     {{/each}}
</div>
</script>