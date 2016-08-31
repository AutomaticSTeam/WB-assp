/**
 * @author lin
 */
$(function() {
	showDatagrid("");
	//表单验证
	$(".footerForm").Validform({
		tiptype:3
	});
	//实例化uEditor
	var ue = UE.getEditor('editor',{
   	 toolbars: [[
   	             'fullscreen', 'source', '|', 'undo', 'redo', '|',
   	             'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
   	             'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
   	             'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
   	             'directionalityltr', 'directionalityrtl', 'indent', '|',
   	             'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
   	             'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
   	             'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
   	             'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
   	             'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
   	             'print', 'preview', 'searchreplace', 'drafts', 'help'
   	         ]],
   	           autoHeightEnabled: false,//自动增高
   	           lang:"zh-cn",
   	           focus:false ,//初始化时，是否让编辑器获得焦点true或false
   	           initialFrameWidth:"100%", //初始化编辑器宽度,默认1000
   	           initialFrameHeight:300 , //初始化编辑器高度,默认320
   	           readonly : false ,//编辑器初始化结束后,编辑区域是否是只读的，默认是false
   	           autoFloatEnabled:false,//是否固定工具栏位置（默认为true，可能引起页面下拉到底调动）
   	           imagePopup:false    //图片操作的浮层开关，默认打开
   	       });
	
	//验证ueditor
	ue.addListener("blur", function (type, event) {
		$("#ueditor_Valid").show();
    });
	
	selectColumnsList($("#templateFooterId").val());
	
	//添加编辑
	$("#footer-edit").click(function(){
		var rows = $('#footer-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			layer.alert("请选择单行数据进行操作");
		}else{
			window.location.href=rootPath+"/contents/footer/toFooterOperate.do?templateFooterId="+rows[0].templateFooterId;
		}
	});
	//删除
	$("#footer-del").click(function(){
		var rows = $('#footer-table').datagrid('getSelections');
		if(rows.length==0){
			layer.alert("请选择数据进行操作");
		}else{
	      	var rowsStr="";
		  	for (var i = 0; i < rows.length; i++) {
		 	  if(i==rows.length-1){
	 			  rowsStr+=rows[i].templateFooterId;
		  	  }else
		  		  rowsStr+=rows[i].templateFooterId+",";
		  	}
		  	alert(rowsStr);
		  //提交表单
			  $.ajax({
					url:rootPath+"/contents/footer/delFooter.do",
					type:"post",
					datatype:"json",
					data:{templateFooterIds:rowsStr},
					success:function(data){
						if(data.res==1){
							layer.alert("Footer删除成功");
							showDatagrid("");
						}else{
							layer.alert("Footer删除失败，请重试。。。");
						}
					},
					error:function(){
						layer.alert("Footer删除失败，请重试。。。");
					}
				});
		} 
   });
    
   //添加、编辑提交
    $("#submitFooter").click(function(){
	  //提交表单
		  $.ajax({
				url:rootPath+"/contents/footer/createOrEditFooter.do",
				type:"post",
				datatype:"json",
				data:$(".footerForm").serialize(),
				success:function(data){
					if(data.res==1){
						//layer.alert(data.msg);
						window.location.href=rootPath+"/jsp/webconsole/footer/footerManage.jsp";
					}else{
						layer.alert(data.msg+"，请重试。。。");
					}
				},
				error:function(){
					layer.alert("操作失败，请重试。。。");
				}
			});
   }); 
});

//查询持有的样式id
/*var selectStyleFuncList = function(templateFooterId){
	  $.ajax({
			url:rootPath+'/contents/footer/queryStyleFuncs.do',
			data:{templateFooterId : templateFooterId},
			dataType:'json',
			async : true,
			success: function(data){
				var industList = data.industList;
				var indust_html = "<option value=''>请选择</option>";
				$.each(industList, function(i, indust) {
					if(hyValue == indust.industryTypeId){
						indust_html += "<option value='" + indust.industryTypeId + "' selected>" + indust.industryTypeName + "</option>";
					}else
						indust_html += "<option value='" + indust.industryTypeId + "'>" + indust.industryTypeName + "</option>";
	            });
				$("#industryTypeId").html(indust_html).attr("disabled", false).css({"display": "", "visibility": ""});
			}
			});
}*/

//查询模板
/*var selectTemplateList = function(hyValue){
	$.ajax({
		url:rootPath+'/template/queryTemplateList.do',
		data:{template : ''},
		dataType:'json',
		async : true,
		success: function(data){
			var rows = data.rows;
			var row_html = "<option value=''>请选择</option>";
			$.each(rows, function(i, row) {
				if(hyValue == row.templateId){
					row_html += "<option value='" + row.templateId + "' selected>" + row.templateName + "</option>";
				}else
					row_html += "<option value='" + row.templateId + "'>" + row.templateName + "</option>";
            });
			$("#templateId").html(row_html).attr("disabled", false).css({"display": "", "visibility": ""});
		}
		});
}*/
//级联查询columns
var selectColumnsList = function(id){
	$.ajax({
		url:rootPath+'/template/queryColumnsList.do',
		dataType:'json',
		async : true,
		success: function(data){
			var rows = data.tcList;
			var row_html = "";
			$.each(rows, function(i, row) {
				row_html +='<span class="checkbox">'
					  +'<input type="checkbox" name="columnsId" value="'+row.columnsId+'">'+row.columnsName+'</span>';
            });
			$("#queryColumnsCKBoxType").html(row_html);
			//若存在id，则取相关分类
			if(id != null && id != "null" && id != ''){
				$.ajax({
		  			type:'post',
		  			 url:rootPath+'/contents/footer/queryFooterRelColumns.do',
		  			data:{
		  				templateFooterId : id ,
		  			},
		  			dataType:'json',
		  			async : true,
		  			success: function(data){ 
		  				$data = data.footerRelColumnsList;
		  				$.each($data ,function(i,footerRelColumns){
		  					$("input[name='columnsId']").each(function(){
		  						 if($(this).val()  == footerRelColumns.columnsId){
		  							 $(this).attr('checked',true);
		  						 }
		  					 });
		  				 });
		  			 },
		  			error:function(data){
		  				//alert(JSON.stringify(data));
		  			}
		  	     });
			 }
		}
		});
}

//模板数据列表
var showDatagrid=function(footer){
	$('#footer-table').datagrid({
	    height: 330,
	    width:695,
	    url: rootPath+"/contents/footer/queryFooterList.do",
	    method: 'POST',
	    queryParams: { 'footer': footer },
	   // idField: '模块模板id',
	    striped: true,
	    fitColumns: true,
	    rownumbers: false,
	    pagination: true,
	    //nowrap: false,
	    loadMsg:"正在加载。。。",
	    //singleSelect:true,
	    pageSize: 10,
	    pageList: [5,10, 20, 50, 100],
	    showFooter: true,
	    columns: [[
	        { field: 'templateFooterId', checkbox: true },
	        { field: 'copyrightInfo', title: '版权信息', width: 100, align: 'center'}
	    ]],
	    onBeforeLoad: function (param) {
	    },
	    onLoadSuccess: function (data) {
	    	
	    },
	    onLoadError: function () {
	        
	    },
	    onClickCell: function (rowIndex, field, value) {
	        
	    }
	});
}

function getUrlParam(name) {  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");  
    var r = window.location.search.substr(1).match(reg);  
    if(r!=null){
 	  return unescape(r[2]);   
    }else{
 	  return null;  
    }  
}

