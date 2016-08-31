<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String rootPath = request.getContextPath();
	String reasult = null;
	if(request.getAttribute("reasult") != null ){ reasult = (String)request.getAttribute("reasult"); }
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>网站后台</title>
		<script type="text/javascript">
		    var rootPath = '<%= rootPath%>';
		    var rst =  '<%= reasult%>';
		    var canReview = 0 ; //不可以审核
		</script>
		  <shiro:hasPermission name="wms:article:audit">
		      <script type="text/javascript">
		                canReview = 1 ; //不可以审核
	      	</script>
		  </shiro:hasPermission>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/page/jquery.pager.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/dialog.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/common/StringUtils.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script>
		<script src="<%=rootPath%>/jsp/webconsole/article/js/query_article_type.js" type="text/javascript" charset="utf-8"></script>
		<!-- <script src="<%=rootPath%>/js/site_contents_manage.js" type="text/javascript" charset="utf-8"></script> -->
		<script src="<%=rootPath%>/jsp/webconsole/article/js/articleManage.js" type="text/javascript" charset="utf-8"></script>
		 <script src="<%=rootPath%>/static/datepicker/My97DatePicker/WdatePicker.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/common/DateUtils.js" type="text/javascript"></script>      
		<script src="<%= rootPath%>/static/plugins/jquery.tmpl.min.js" type="text/javascript"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/base.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/page.css" />
	</head>
		<script id="article_data_grid" type="text/x-jquery-tmpl"> 
                    <div class="table-body">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th class="w40"><input type="checkbox" name="" id="" /></th>
									<th class="w120">操作</th>
									<th>文章名称</th>
									<th class="w40">置顶</th>
									<th class="w60">审核通过</th>
									<th class="w60">阅读权限</th>
									<th class="w280">文章分类</th>
									<th class="w120">日期</th>
								</tr>
                              {{each(i,article) rows}}
                                  <tr>
									<td><input type="checkbox" name="articleId" value="{{= article.articleId}}" /></td>
									<td>
										 {{if article.articleReview == 0 && canReview == 1}}
                                              <a  class="btn-review" onclick="reView({{= article.articleId}});"></a>
                                       	 {{/if}}
										<a href="<%=rootPath%>/contents/article/toArticleEditOrView.do?flag=0&articleId={{= article.articleId}}" class="btn-preview"></a>
										<a href="<%=rootPath%>/contents/article/toArticleEditOrView.do?flag=1&articleId={{= article.articleId}}" class="btn-edit"></a>
									</td>
									<td class="tl tit"><a title="{{= article.articleTitle}}">{{= ArticleTypeQueryAll.formAtName(article.articleTitle,20)}}</a>
                                      <span class="pic">
                                             <img src="<%=rootPath%>/images/thumb.png" />
                                      <span class="picImg">
                                      
											 {{if article.articleAttachmentImg == null }}
                                                 <img src="<%=rootPath%>/images/thumb.png" />
 											 {{/if}}
 											 {{if article.articleAttachmentImg != null }}
                                                  <img src="{{= article.articleAttachmentImg}}" alt="" />
 											 {{/if}}
                                      </span>
                                     </span>
                                	  </td>
									<td>
                                            {{if article.articleTop == 0 }}
                                                  <a class="btn-top" onclick="setTop(this,{{= article.articleId}});" ></a>
 											 {{/if}}
                                             {{if article.articleTop == 1 }}
                                                  <a class="btn-top btn-top-selected" onclick="setTop(this,{{= article.articleId}});"></a>
 											 {{/if}}
                                     </td>
									<td>
                                       {{if article.articleReview == 0 }}
                                           		   否
                                        {{/if}}
                                        {{if article.articleReview == 1 }}
                                            	 是
                                        {{/if}}
                                     </td>
									<td>默认</td>
									<td class="tl">
                                  		 {{each(i,articleRelType) article.articleRelTypes}}
											<span class="articlesClass" id="r{{= articleRelType.articleRelTypeId}}" >{{= articleRelType.articleTypeName}}<span class="del" onclick="delArticleTypeRel({{= articleRelType.articleRelTypeId}});" >×</span></span>
										{{/each}}
									</td>
									<td>
                                        {{if article.createTime != null && article.createTime != "" }}
                                         {{= DateUtils.date2String(DateUtils.string2Date(article.createTime, 1) ,1)}}
 										{{/if}}
                                    </td>
								</tr>
                              {{/each}}
							</table>
						</div>
   	 </script>
	<body>
		<div id="managementArticles">
			<div class="column">
				<h2 class="column-tit"><span class="tit">管理文章</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
				<div class="main-head">
					<div class="btns">
					<shiro:hasPermission name="wms:article:addarticle">
						<div class="btn-item">
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib addContentBtn"  data-url="<%=rootPath%>/jsp/webconsole/article/addArticle.jsp">添加文章</a>
						</div>
						</shiro:hasPermission>
						<shiro:hasPermission name="wms:article:batchmodify">
						<div class="btn-item">
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn">批量修改</a>
							<div class="infoContent">
								<div class="infoContent-item">
									<a data-modify="录取时间"  id="createTimeBtn">录取时间</a>
								</div>
								<!-- <div class="infoContent-item">
									<a data-modify="阅读权限">阅读权限</a>
								</div>
								 -->
								<div class="infoContent-item">
									<a data-modify="来源" id="sourceBtn">来源</a>
								</div>
								<div class="infoContent-item">
									<a data-modify="作者" id="authorBtn">作者</a>
								</div>
								<!-- <div class="infoContent-item">
									<a data-modify="网址" id="urlBtn">网址</a>
								</div> -->
								<div class="arrow"><span></span></div>
							</div>
						</div>
						</shiro:hasPermission>
						<shiro:hasPermission name="wms:article:settype">
						<div class="btn-item" id="setInfoContent">
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib setBtn">设置为</a>
							<div class="infoContent setInfoContent">
								<div class="checkbox-box" id="queryArticleCKBoxType">
								</div>
								<div class="pop-btn"><a class="btn dib btn-blue01 confirm-btn" id="setSaveBtn">确定</a>
								<a class="btn dib btn-green01 cancel-btn" id="setCancleBtn">取消</a></div><!--pop-btn-->
								<div class="arrow"><span></span></div>
							</div>
						</div>
						</shiro:hasPermission>
						<shiro:hasPermission name="wms:article:delete">
						<div class="btn-item">
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib removeArticleBtn" id="batchDelBtn">删除文章</a>
						</div>
						</shiro:hasPermission>
					</div>
				</div><!--main-head-->
				<div class="main-body">
				 <form action="" method="post" id="articleForm">
					<input type="hidden" id="currentPage" name="page" value="1"/>
				    <input type="hidden" name="rows" value="9"/>
					<div class="search">
						<div class="search-item">
							<label>标题搜索：</label>
							<div class="box-input">
								<input class="inputText w280" name="articleTitle"  id="articleTitle" type="text" />
							</div>
						</div>
						<div class="search-item">
							<label>分类：</label>
							<div class="box-input">
								<select class="select w180" name="typeId" id="articleTypeId">
									<option value="">请选择</option>
								</select>
							</div>
						</div>
					</div><!--search-->
					</form>
					<div class="table" id="articleDataGrid">
					</div><!--table-->
					<div class="page">
						<div id="pager">
		              	    <ul class="pages">
		              	    </ul>
	              	    </div>
					</div><!--page-->
				</div><!--main-body-->
			</div><!--main-content-->
		</div><!--managementArticles-->
		<!-- <script src="<%=rootPath%>/jsp/webconsole/article/js/backstage_article.js" type="text/javascript" charset="utf-8"></script> -->
	</body>
	
	<div id="updateDate" class="oneEditPop undis">
	     	<div class="pop-form m-a">
					<div class="popForm-item">
						<label id="pName">作者：</label>
						<div class="label-con">
						<span class="pr" id="data1">
						      <input class="inputText" type="text" id="data3"/>
						      <a  class="datetime1" id="createTimeD1">时间</a>
						</span>
							<input class="inputText" type="text"  id="data2" class="undis" />
						</div>
					</div>
			</div>
	</div>
</html>