<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
     String rootPath = request.getContextPath();
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
			  <%-- <shiro:hasPermission name="wms:media:audit"> --%>
			      <script type="text/javascript">
			                canReview = 1 ; //不可以审核
		      	</script>
			  <%-- </shiro:hasPermission> --%>
		<script src="<%=rootPath%>/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/page/jquery.pager.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/backstage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/dialog.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/js/common/StringUtils.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/static/layer/layer.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/site_contents_manage.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=rootPath%>/jsp/webconsole/vedio/js/vedioManage.js" type="text/javascript" charset="utf-8"></script>
		 <script src="<%=rootPath%>/static/datepicker/My97DatePicker/WdatePicker.js" type="text/javascript"></script> 
		<script src="<%=rootPath%>/js/common/DateUtils.js" type="text/javascript"></script>      
		<script src="<%= rootPath%>/static/plugins/jquery.tmpl.min.js" type="text/javascript"></script>
		<link rel="stylesheet" href="<%=rootPath%>/css/baseWeb.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pop.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/backstage.css" />
		<link rel="stylesheet" href="<%=rootPath%>/css/pageWeb.css" />
	</head>
	<script id="media_data_grid" type="text/x-jquery-tmpl"> 
                    <div class="table-body">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th class="w40"><input type="checkbox" name="" id="" /></th>
									<th class="w120">操作</th>
									<th>视频名称</th>
									<th class="w40">置顶</th>
                                    <th class="w60">审核通过</th>
									<th class="w60">阅读权限</th>
									<th class="w280">视频分类</th>
									<th class="w120">日期</th>
								</tr>
                              {{each(i,media) rows}}
                                  <tr>
									<td><input type="checkbox" name="mediaId" value="{{= media.mediaId}}" /></td>
									<td>
                                        {{if media.isReview == 0 && canReview == 1 }}
                                              <a  class="btn-review" onclick="reView({{= media.mediaId}});"></a>
                                        {{/if}}
										<a href="<%=rootPath%>/contents/media/toMediaEditOrView.do?flag=0&mediaId={{= media.mediaId}}" class="btn-preview"></a>
										<a href="<%=rootPath%>/contents/media/toMediaEditOrView.do?flag=1&mediaId={{= media.mediaId}}" class="btn-edit"></a>
									</td>
									<td class="tl tit"><a title="{{= media.mediaName}}">{{= ContentsManage.formAtName(media.mediaName,20)}}</a>
                                      <span class="pic">
                                             <img src="<%=rootPath%>/images/thumb.png" />
                                      <span class="picImg">
										{{if media.mediaAttachmentImg == null }}
                                             <img src="<%=rootPath%>/images/thumb.png" />
                                        {{/if}}
	                                    {{if media.mediaAttachmentImg != null }}
                                            <img src="{{= media.mediaAttachmentImg}}" alt="" />
                                        {{/if}}
                                      </span>
                                     </span>
                                  </td>
									<td>
                                            {{if media.isTop == 0 }}
                                                  <a class="btn-top" onclick="setTop(this,{{= media.mediaId}});" ></a>
 											 {{/if}}
                                             {{if media.isTop == 1 }}
                                                  <a class="btn-top btn-top-selected" onclick="setTop(this,{{= media.mediaId}});" ></a>
 											 {{/if}}
                                     </td>
                                    <td>
                                       {{if media.isReview == 0 }}
                                              否
                                        {{/if}}
                                        {{if media.isReview == 1 }}
                                             是
                                        {{/if}}
                                     </td>
									<td>默认</td>
									<td class="tl">
                                   {{each(i,mediaRelType) media.mediaRelTypes}}
										<span class="articlesClass" id="r{{= mediaRelType.mediaRelTypeId}}" >{{= mediaRelType.mediaTypeName}}<span class="del" onclick="delMediaTypeRel({{= mediaRelType.mediaRelTypeId}});" >×</span></span>
									{{/each}}	
									</td>
										<td>
											{{if media.createTime != null &&  media.createTime !=""}}
											   {{= DateUtils.date2String(DateUtils.string2Date(media.createTime, 1) ,1)}}
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
				<h2 class="column-tit"><span class="tit">管理视频</span><span class="line"></span></h2>
			</div><!--column-->
			<div class="main-content">
				<div class="main-head">
					<div class="btns">
						<div class="btn-item">
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib addContentBtn" id="createOrEditVedio" >添加视频</a>
						</div>
						<div class="btn-item">
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib batchModifyBtn">推量修改</a>
							<div class="infoContent">
								<div class="infoContent-item">
									<a data-modify="录取时间"  id="createTimeBtn">录取时间</a>
								</div>
								<!-- <div class="infoContent-item">
									<a data-modify="阅读权限">阅读权限</a>
								</div> -->
								<div class="infoContent-item">
									<a data-modify="来源" id="sourceBtn">来源</a>
								</div>
								<div class="infoContent-item">
									<a data-modify="作者" id="authorBtn">作者</a>
								</div>
								<div class="arrow"><span></span></div>
							</div>
						</div>
						<div class="btn-item" id="setInfoContent">
							<a href="javascript:;" class="btn btn-border-gray border-gray-new dib setBtn">设置为</a>
							<div class="infoContent setInfoContent" >
								<div class="checkbox-box" id="mediaCkBoxList">
								</div>
								<div class="pop-btn"><a class="btn dib btn-blue01 confirm-btn" id="setSaveBtn">确定</a>
								<a class="btn dib btn-green01 cancel-btn" id="setCancleBtn" >取消</a></div><!--pop-btn-->
								<div class="arrow"><span></span></div>
							</div>
						</div>
						<div class="btn-item">
							<a  class="btn btn-border-gray border-gray-new dib removeArticleBtn" id="batchDelBtn">推量删除</a>
						</div>
					</div>
				</div><!--main-head-->
				<div class="main-body">
				   <form action="" method="post" id="mediaForm">
				   <input type="hidden" id="currentPage" name="page" value="1"/>
				   <input type="hidden" name="rows" value="9"/>
				       	<div class="search">
							<div class="search-item">
								<label>标题搜索：</label>
								<div class="box-input">
									<input class="inputText w280" name="mediaName"  id="mediaName" type="text" />
								</div>
							</div>
							<div class="search-item">
								<label>分类：</label>
								<div class="box-input">
									<select class="select w180"  id="mediaSelect" name="typeId">
									      <option value="">请选择</option>
									</select>
								</div>
							</div>
						</div><!--search-->
				   </form>
					<div class="table" id="mediaDataGrid">
						
					</div><!--table-->
					<div class="page" >
	              	    <div id="pager">
		              	    <ul class="pages">
		              	    </ul>
	              	   </div> 
					</div>
				</div><!--main-body-->
			</div><!--main-content-->
		</div><!--managementArticles-->
	</body>
	
	<div id="updateDate"  class="oneEditPop undis"  >
	     	<div class="pop-form m-a">
					<div class="popForm-item">
						<label id="pName">作者：</label>
						<div class="label-con">
						<span class="pr">
						      <input class="inputText" type="text"  id="data1" />
						      <a  class="datetime1" id="createTimeD">时间</a>
						</span>
						  
							<input class="inputText" type="text"  id="data2" class="undis" />
						</div>
					</div>
		</div>
	</div>
</html>