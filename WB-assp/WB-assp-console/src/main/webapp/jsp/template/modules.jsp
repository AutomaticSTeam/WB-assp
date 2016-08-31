<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script id="module_footer_tmpl" type="text/html"> 
<div class="footer">
	<div class="links">
		<div class="w">
			<div data-model="imgText" data-index="1">
				<div class="imgText">
                      	{{each templateFooter.templateColumns as column i}}
                               {{if  column.customUrl == null ||  column.customUrl == '' }}
                            			<a >{{column.columnsName}}</a>
                               {{/if}}
                               {{if column.customUrl != null && column.customUrl != '' }}
									<a href="javascript:Site.URLHref ('${column.customUrl}');">{{column.columnsName}}</a>
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
	<div class="w">
		<ul class="menuList">
				{{each templateColumns as templateColumn i}}
				   {{if templateColumn.columnsPid == 0 }}
				           <li  id="column{{templateColumn.columnsId}}">
		                           <a class="menuList-item" href="javascript:Site.NavHref('{{templateColumn.customUrl}}')" title="">
		                             <span>
										<img src="{{templateColumn.columnsIcon }}" alt="" class="icon" />
										<img src="{{templateColumn.columnsIconActive  }}" alt="" class="iconHover hide" />
									</span>
		                            <span>{{templateColumn.columnsName }}	</span>
		                         </a>
		                         {{if templateColumn.subTemplateColumns != null }}
		                            <div class="subMenu">   
		                                {{each templateColumn.subTemplateColumns as tColumn i}}
		                                       <div class="subMenu-item" id="column{{tColumn.columnsId}}"><a href="" title="">{{tColumn.columnsName }}</a></div>
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
<div class="header">
		<div class="w">
			<div data-model="logo" class="logo">
				<div class="logo-box" id="{{templateLogo.templateLogoId}}">
						<img  id="logoImg" src="{{templateLogo.logoImgUrl}}"  alt="logo" title="logo" />
			</div>
		</div>
	</div>
</div>
</script>



<script id="module_titles_tmpl" type="text/html"> 
{{each moudles as value i}}
{{if  moudles.length == 1 || (moudles.length > 1 && i != 0) }}
    <div class="tab-item " data-tab="item{{i}}">
  {{/if}}
{{if  moudles.length > 1 && i == 0 }}
    <div class="tab-item current" data-tab="item{{i}}">
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
	     
     {{/if}}
      {{if  moudle.extendUrl != null && moudle.extendUrl != "" }}
	     
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
			
                    {{article.articleNtro }}
           
			<!--用户自定义内容end-->

		</div>
	</div>
  	 {{/each}}
</div>
</script>

<script id="module_imgtxt_tmpl" type="text/html"> 
<div data-model="imgText" class="imgText-1">
		{{each articles as article i}}
                {{if i < moduleItemLineNum*moduleItemColumnNum}}
				<div class="imgText">
					<div class="imgText-img">
						<img src="{{article.articleAttachmentImg}}" alt="" />
						<span class="imgText-tit"></span>
					</div>
					<div class="imgText-content">
						<a href="">{{article.articleTitle }}</a>
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
				{{if i < moduleItemLineNum*moduleItemColumnNum}}
					<a href=""><img  src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}"/></a>
                {{/if}}
			  {{/each}}
			</div>
		</div>
</div>
</script>



<script id="module_icontxt_tmpl" type="text/html">
<div data-model="imgText" class="imgText-1">
	<div class="imgText">
		<div class="imgText-img">
			<img src="" alt="" />
		</div>
		<div class="imgText-content">
			<div
				style="border-right: 1px dashed #ccc; overflow: hidden; padding: 10px 0; margin-top: 20px; margin-bottom: 20px">
				<a style="float: left" href=""><img src="images/index_01.png"
					alt="" /></a>
				<div style="float: left; width: 90px; margin-left: 30px;">
					<p style="font-size: 17px; color: #5f5f5f;">权威数据</p>
					<p style="color: #a9a9a9; font-size: 14px; padding-top: 5px">教育部10年高考数据</p>
				</div>
			</div>
		</div>
	</div>
</div>												
</script>

<script id="module_imgtxt_list_tmpl" type="text/html"> 

<div data-model="list" class="articleList-1 layout-list1">
	<ul class="articleList">
	{{each articles as article i}}
           {{if i < moduleItemLineNum*moduleItemColumnNum}}
		<li data-type="article" data-url="{{article.articleId }}">
			<div class="img">
				<img src="{{article.articleAttachmentImg}}" alt="" />
			</div>
			<div class="con">
				<div class="tit">
					{{article.articleTitle }}
				</div>
				<div class="txt">{{article.articleNtro}}</div>
				<div class="time">{{article.createTime}}</div>
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
         {{if i < moduleItemLineNum*moduleItemColumnNum}}
		<li data-type="picture" data-url="{{picture.pictureId }}">
			<div class="img">
            	<img src="{{picture.pictureUrl}}"  alt="{{picture.briefDesc}}" />
			</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{picture.pictureName}}</a></div>
			</div>
	</li>
     {{/if}}
      {{/each}}
	{{each medias as media i}}
         {{if i < moduleItemLineNum*moduleItemColumnNum}}
		<li data-type="media" data-url="{{media.mediaId }}">
            <div class="img">
                    <img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" />
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
      {{if i < moduleItemLineNum*moduleItemColumnNum}}
		<li data-type="article" data-url="{{article.articleId }}">
          <div class="img">
            <a href=""><img src="{{article.articleAttachmentImg}}"  alt="{{article.articleTitle}}" /></a>
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
         {{if i < moduleItemLineNum*moduleItemColumnNum && picture != null}}
		<li data-type="picture" data-url="{{picture.pictureId }}">
			<div class="img">
            	<img src="{{picture.pictureUrl}}"  alt="{{picture.briefDesc}}" />
			</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{picture.pictureName}}</a></div>
			</div>
	</li>
     {{/if}}
      {{/each}}
	{{each medias as media i}}
         {{if i < moduleItemLineNum*moduleItemColumnNum}}
		<li data-type="media" data-url="{{media.mediaId }}">
            <div class="img">
                     <img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" />
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
      {{if i < moduleItemLineNum*moduleItemColumnNum}}
		<li data-type="article" data-url="{{article.articleId }}">
          <div class="img">
            <img src="{{article.articleAttachmentImg}}"  alt="{{article.articleTitle}}" />
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
         {{if i < moduleItemLineNum*moduleItemColumnNum}}
		<li data-type="picture" data-url="{{picture.pictureId }}">
			<div class="img">
            	<a href="{{picture.hyperlink}}"><img src="{{picture.pictureUrl}}"  alt="{{picture.briefDesc}}" /></a>
			</div>
		<div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{picture.briefDesc}}</a></div>
			</div>
	</li>
     {{/if}}
      {{/each}}
	{{each medias as media i}}
         {{if i < moduleItemLineNum*moduleItemColumnNum}}
		<li data-type="media" data-url="{{media.mediaId }}">
             <div class="img">
                  <img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" />
	     	</div>
           <div class="con">
				<div class="back"></div>
				<div class="tit"><a href="">{{= media.mediaName}}</a></div>
			</div>
	</li>
      {{/if}}
      {{/each}}
    {{each articles as article i}}
      {{if i < moduleItemLineNum*moduleItemColumnNum}}
		<li data-type="article" data-url="{{article.articleId }}">
        <div class="img">
            <a href=""><img src="{{article.articleAttachmentImg}}"  alt="{{article.articleTitle}}" /></a>
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
            	<a href=""><img src="{{picture.pictureUrl}}"  alt="{{picture.briefDesc}}" /></a>
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
            <a href="javascript:void(0);"><img src="{{= media.mediaAttachmentImg}}"  alt="{{= media.mediaName}}" /></a>
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
           <img src="{{article.articleAttachmentImg }}"  alt="{{article.articleTitle }}" />
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
                             <a href="{{picture.hyperlink}}"  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" /></a>
                 {{/if}}
			  </li>
		{{/each}}
	</ul>
</div>
</div>
</script>

<script id="module_foucs_thumbnail_tmpl" type="text/html"> 
<div id="focus02" data-model="focus" class="slider slider-3">
<div class="slider-pic">
	<ul>
       {{each pictures as picture i}}
             <li data-type="picture">
			        {{if  picture.hyperlink == null ||  picture.hyperlink == ""}}
                         <a href=""  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" /></a>
                 {{/if}}
                  {{if  picture.hyperlink != null && picture.hyperlink != ""}}
                          <a href="{{picture.hyperlink}}"  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" /></a>
                 {{/if}}
			  </li>
		{{/each}}
	</ul>
</div>
<div class="slider-title"><a href=""></a></div>
<div class="slider-nav">
   {{each pictures as picture i}}
             <a><img src="{{picture.pictureUrl}}" alt=""  /></a>
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
                         <a href=""  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" /></a>
                 {{/if}}
                  {{if  picture.hyperlink != null && picture.hyperlink != ""}}
                          <a href="{{picture.hyperlink}}"  title="{{picture.briefDesc}}"><img src="{{picture.pictureUrl}}" alt="{{picture.briefDesc}}" /></a>
                 {{/if}}
			  </li>
		{{/each}}
	</ul>
</div>
<div class="slider-title"><a href=""></a></div>
<div class="slider-nav">
   {{each pictures as picture i}}
             <a><img src="{{picture.pictureUrl}}" alt=""  /></a>
		{{/each}}
</div>
</div>
</script>


