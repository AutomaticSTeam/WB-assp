/**
 * @author: songhx 
 * @version : v 1.1
 * @desc : 
 *     this js is for templateConsole
 */
$(function(){
	TemplateConsole.templateDataGrid();
});

//模板管理类
var TemplateConsole = {
	
	//加载模板数据
	templateDataGrid : function(){
		$.ajax({
			type:'post',
			 url:rootPath+'/template/queryTemplateList.do',
			//data:$("#templateForm").serialize(),
			dataType:'json',
			async : true,
			success: function(data){ 
				 $data = data.rows;
				 var html = "";
				 $.each($data ,function(i,tmpl){ 
					 html += '<tr>'
						   +'<td>'
			               +'<a class="optIcon" title="编辑"><i class="fa fa-edit"></i></a>'
			               +'<a onclick="TemplateConsole.setTemplate('+tmpl.templateId+','+tmpl.templateCode+')" class="optIcon" title="模板地图"><i class="fa fa-sitemap"></i></a>'
			               +'<a class="optIcon" title="预览"><i class="fa fa-search-plus"></i></a>'
			               +'<a class="optIcon" title="删除"><i class="fa fa-times"></i></a>'
			               +'</td>'
			               +'<td>' + tmpl.templateName + '</td>'
			               +'<td>' + tmpl.templateCode + '</td>'
			               +'<td><span class="label label-warning label-mini">已发布</span></td>'
			               +'<td>' + tmpl.operatorName + '</td>'
			               +'<td>' + DateUtils.long2String(tmpl.createTime, 7) + '</td>'
			               +'</tr>';
				 });
				 $("#templateDataGrid").html(html);
			 },
			error:function(data){
				//alert(JSON.stringify(data));
			}
	     });
	},
	
	//设置模板
	setTemplate : function(templateId , templateCode){
		window.open(rootPath+'/jsp/template/setTemplate.jsp?templateId='+templateId+'&templateCode='+templateCode,'_blank');
	}
};