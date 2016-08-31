/**
 * @author lin
 */
$(function() {
	showDatagrid("");
	//表单验证
	$(".logoForm").Validform({
		tiptype:3
	});
	//取消按钮事件
	$("#ablumCancel").click(function(){
				layer.close(index);
				window.location.reload();//刷新当前页面.
	})
	
	//添加编辑
	$("#logo-edit").click(function(){
		var rows = $('#logo-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			layer.alert("请选择单行数据进行操作");
		}else{
			//location.href=rootPath+"/jsp/webconsole/logo/createOrEditLogo.jsp？templateLogoId="+rows[0].templateLogoId;
			window.location.href=rootPath+"/contents/logo/toLogoOperate.do?templateLogoId="+rows[0].templateLogoId;
			parent.changeLeftMenuCss(2);
		}
	});
	//删除
	$("#logo-del").click(function(){
		var rows = $('#logo-table').datagrid('getSelections');
		if(rows.length==0){
			layer.alert("请选择数据进行操作");
		}else{
	      	var rowsStr="";
		  	for (var i = 0; i < rows.length; i++) {
		 	  if(i==rows.length-1){
	 			  rowsStr+=rows[i].templateLogoId;
		  	  }else
		  		  rowsStr+=rows[i].templateLogoId+",";
		  	}
		  	alert(rowsStr);
		  //提交表单
			  $.ajax({
					url:rootPath+"/contents/logo/delLogo.do",
					type:"post",
					datatype:"json",
					data:{templateLogoIds:rowsStr},
					success:function(data){
						if(data.res==1){
							layer.alert("logo删除成功");
							showDatagrid("");
						}else{
							layer.alert("logo删除失败，请重试。。。");
						}
					},
					error:function(){
						layer.alert("logo删除失败，请重试。。。");
					}
				});
		} 
   });
	//预览logo
    $("#logo-preview").click(function(){
   	 	var rows = $('#logo-table').datagrid('getSelections');
		if(rows.length==0||rows.length>1){
			layer.alert("请选择单行数据进行操作");
		}else{
			layer.open({
				  type: 1, 
				  area: '500px',
				  shadeClose:true,
				  content:  '<img width="500px" src="'+rows[0].logoImgUrl+'" >'
				});
		}
   });
    
   //添加、编辑提交
    $("#submitlogo").click(function(){
	  //提交表单
		  $.ajax({
				url:rootPath+"/contents/logo/createOrEditLogo.do",
				type:"post",
				datatype:"json",
				data:$(".logoForm").serialize(),
				success:function(data){
					if(data.res==1){
						//layer.alert(data.msg);
						window.location.href=rootPath+"/jsp/webconsole/logo/logoManage.jsp";
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

//查询模板类型
var selectTemplateLogoObj = function(templateLogoId){
	  $.ajax({
			url:rootPath+'/template/queryIndustryTypes.do',
			data:{templateLogoId : templateLogoId},
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
}

//上传图片
function uploadcpImg(classStr,thisValue){
	uploadImg(classStr,thisValue);
}

var uploadImg=function(classStr,thisValue){
	var templateCode = "logo";
	layer.open({
		  type: 2, 
		  title: ['添加图片（只能添加jpg、jpeg、gif、png、免费版大小不超过1MB）', 'font-size:14px;'],
		  content: [rootPath+'/jsp/common/pop/uploadImg.jsp?singleupload=true&&chooseSingle=true&&templateCode='+templateCode, 'no'],
		 // skin: 'demo-class'
		  area: ['640px', '430px'],//定义大小
		  closeBtn:1,//右上角关闭按钮样式
		  shade: 0.3, //遮罩为0表示不显示
		  shadeClose :true,//是否点击遮罩关闭
		  scrollbar:false,//是否允许滚动条出现
		  //按钮与回调
		  btn: ['确认', '取消'],
		  yes: function(index, layero){
			  setImg(index, layero,classStr,thisValue);
			  layer.close(index);
		  	   },
		});
	
}
//设置附图
var setImg =  function(index, layero,classStr,thisValue){
	var body = layer.getChildFrame('body', index);
	var pUrls=body.find("input[id='pictureUrls']").val();
	if(pUrls != "" && pUrls.length > 0){
		pUrl = pUrls.substring(0,(pUrls.length - 1));
		$(thisValue).attr("src",pUrl);
		$("."+classStr).val(pUrl);
		$(thisValue).unbind("click");
	}
};

//模板数据列表
var showDatagrid=function(logo){
	$('#logo-table').datagrid({
	    height: 330,
	    width:695,
	    url: rootPath+"/contents/logo/queryLogoList.do",
	    method: 'POST',
	    queryParams: { 'logo': logo },
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
	        { field: 'templateLogoId', checkbox: true },
	        { field: 'logoTitle', title: 'logo名称', width: 80, align: 'center'},
	        { field: 'logoImgUrl', title: 'logo路径', hidden:true},
	        { field: 'fisrtTitle', title: '主标题', width: 80, align: 'center' },
	        { field: 'secondTitle', title: '二级标题', width: 80, align: 'center'},
	        { field: 'logoImgHide', title: 'logo是否可见', width: 50, align: 'center',
	        	formatter : function (value, rec, index) {
	        		if(value==1){
	        			return "可见";
	        		}else{
	        			return "不可见";
	        		}
	            }
	        },
	        { field: 'logoImgHotLine', title: 'logo链接地址', width: 80, align: 'center'}
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

