$(function(){
	//获取上传参数
   $("#templateId").val(getUrlParam("templateId"));
   $("#templateCode").val(getUrlParam("templateCode"));
   $("#columnsId").val(getUrlParam("columnsId"));
   showDatagrid("");
   
   //表单验证
	$(".columnsForm").Validform({
		tiptype:3
	});
   
   var options = {
			url : rootPath+"/template/queryColumnsList.do",
			type : "post",
			dataType : "json",
			success : function(data){
				if(data.template!=null){
					$("#tempName").text(data.template.templateName);
				}
				var html = "";
				if(data.tcList!=null){
					for (var i = 0; i < data.tcList.length; i++) {
						html += '<li class="frameli" onclick="changeColumns('+data.tcList[i].columnsId+',this)">'+data.tcList[i].columnsName+'</li>';
					}
					$("#addLi").html(html);
				}
			}
	};
	//异步提交
	$("#frameForm").ajaxSubmit(options);
   
	//添加导航弹出框
	$("#columns-add-layer").click(function(){
		layer.open({
		  type: 2, 
		  title: ['添加导航', 'font-size:14px;'],
		  content: [rootPath+'/jsp/template/columnsAdd.jsp?templateId='+$("#templateId").val()+'&templateCode='+$("#templateCode").val(), 'no'],
		  area: ['700px', '530px'],//定义大小
		  closeBtn:1,//右上角关闭按钮样式
		  shade: 0.3, //遮罩为0表示不显示
		  shadeClose :true,//是否点击遮罩关闭
		  scrollbar:true,//是否允许滚动条出现
		  //按钮与回调
		  btn: ['确认', '取消'],
		  yes: function(index, layero){
			  var body = layer.getChildFrame('body', index);
			  var colForm = body.find("form[id='columnsForm']");
			  var isUseFrames = colForm.find("#isUseFrames  option:selected").val();
			  if(isUseFrames=='1'){
				  if(colForm.find("#framesId  option:selected").val()==''){
					  layer.alert("是否选择框架为是时，选择框架为必填项");
					  return false;
				  }
			  }
			  var columnsName=$("#columnsName").val();
			  if(columnsName!=""&&columnsName!=null){
			  //提交表单
			  $.ajax({
					url:rootPath+"/template/addColumns.do",
					type:"post",
					datatype:"json",
					data:colForm.serialize(),
					success:function(data){
						if(data.res==1){
							layer.close(index);
							parent.location.reload();
						}else{
							layer.alert("添加失败，请重试。。。");
						}
					},
					error:function(){
						layer.alert("添加失败，请重试。。。");
					}
				});	
			  }else{
				  layer.alert("请填写完整信息"); 
			  }
		  	  },
		});
	});
	
	//关联frame提交
   $("#frame-submit").click(function(){
	  var rows = $('#frame-table').datagrid('getSelections');
	  if(rows.length==0){
			layer.alert("请选择数据进行操作");
		}else{
		  var rowsStr="";
		  for (var i = 0; i < rows.length; i++) {
			  if(i==rows.length-1){
				  rowsStr+=rows[i].frameId;
			  }else
				  rowsStr+=rows[i].frameId+",";
		  }
		  //提交表单
		  $.ajax({
				url:rootPath+"/template/addColumnsRelFrame.do",
				type:"post",
				datatype:"json",
				data:{frameIds:rowsStr,columnsId:$("#columnsId").val()},
				success:function(data){
					if(data.res==1){
						layer.alert("添加模块成功");
					}else{
						layer.alert("添加失败，请重试。。。");
					}
				},
				error:function(){
					layer.alert("添加失败，请重试。。。");
				}
			});
	  }
   });
   
   //返回
   $("#backStep").click(function(){
	   //window.location.href=rootPath+'/jsp/template/templateWelcome.jsp';
	   window.close();
   });
});

//切换columns
function changeColumns(columnsId,obj){
	$("#frameForm").find(".columnsId").val(columnsId);
	$("#ulDiv").find("ul li").removeClass("colorli");
	$(obj).addClass("colorli");
	Template.initTemplatePage(columnsId);
	$.ajax({
		url:rootPath+'/template/queryFrameByColumnsId.do',
		data:{columnsId : columnsId},
		dataType:'json',
		async : true,
		success: function(data){
			var frameList = data.frameList;
			var html = "";
			$.each(frameList, function(i, frame) {
				html += "<div style='border: 1px solid #F5D9D9;width: 87%;'><span style='font-size:14px;font-weight:bold;padding-left:9%'>" + frame.frameName + "</span><span style='font-size:12px;padding-left:30%;'>"+frame.frameDesc+"</span></div>";
            });
			$("#framep").html(html).css({"display": "", "visibility": ""});
		}
		});
}

//到frame列表页
function queryFrameList(){
	if($("#columnsId").val()==''){
		layer.alert("请选择导航");
	}else{
	layer.open({
		  type: 2, 
		  title: ['框架列表', 'font-size:14px;'],
		  content: [rootPath+'/jsp/template/frameList.jsp?columnsId='+$("#columnsId").val(), 'no'],
		  area: ['640px', '400px'],//定义大小
		  closeBtn:1,//右上角关闭按钮样式
		  shade: 0.3, //遮罩为0表示不显示
		  shadeClose :true,//是否点击遮罩关闭
		  scrollbar:false,//是否允许滚动条出现
		  //按钮与回调
		  //btn: ['', '取消'],
		  /*yes: function(index, layero){
		  	 },*/
		});
	}
}

//到module列表页
function frameRelModule(columnsRelFrameId,sort){
	layer.open({
		  type: 2, 
		  title: ['关联内容', 'font-size:14px;'],
		  content: [rootPath+'/jsp/common/pop/moduleJoinData.jsp?columnsRelFrameId='+columnsRelFrameId+'&sort='+sort, 'no'],
		  area: ['740px', '500px'],//定义大小
		  closeBtn:0,//右上角关闭按钮样式
		  shade: 0.3, //遮罩为0表示不显示
		  shadeClose :true,//是否点击遮罩关闭
		  scrollbar:true,//是否允许滚动条出现
		  //按钮与回调
		  btn: ['确定', '取消'],
		  yes: function(index, layero){
			  	var body = layer.getChildFrame('body', index);
				var moduleId=body.find("input[id='moduleId']").val();
				if(moduleId==""){
					layer.alert("请先提交数据，再关联关系");
				}else{
					$.ajax({
						url:rootPath+"/template/saveFrameRelModule.do",
						type:"post",
						datatype:"json",
						data:{moduleId:moduleId,columnsRelFrameId:columnsRelFrameId,sort:sort},
						success:function(data){
							if(data.res==1){
								layer.close(index);
								parent.location.reload();
							}
						},
						error:function(){
							layer.alert("保存失败，请重试！");
						}
					});
				}
		  	 },
		  	 no: function(index, layero){
		  		var body = layer.getChildFrame('body', index);
				var moduleId=body.find("input[id='moduleId']").val();
				if(moduleId!=""){
					layer.alert("已提交数据，不能进行取消");
				}
		  	 }
		});
}


//模板数据列表
var showDatagrid=function(frame){
	$('#frame-table').datagrid({
	    height: 280,
	    width: 561,
	    url: rootPath+"/template/queryFrameList.do",
	    method: 'POST',
	    queryParams: { 'frame': frame },
	    striped: true,
	    fitColumns: true,
	    rownumbers: false,
	    pagination: true,
	    pageSize: 10,
	    pageList: [5,10, 20, 50, 100],
	    showFooter: true,
	    columns: [[
	        { field: 'frameId', checkbox: true },
	        { field: 'frameName', title: '框架名称', width: 50, align: 'center'},
	        { field: 'frameAttachmentImg', title: '框架图', hidden:true},
	        { field: 'frameDesc', title: '框架描述', width: 80, align: 'center'},
	        { field: 'createTime', title: '创建时间', width: 80, align: 'center' ,
	        	formatter : function(value,rec,index){
	        		var unixTimestamp = new Date(value);  
	        	    return unixTimestamp.toLocaleString(); 
          	},sortable:true},
	        { field: 'updateTime', title: '更新时间', width: 80, align: 'center' ,
          		formatter : function(value,rec,index){
          			var unixTimestamp = new Date(value);  
          		    return unixTimestamp.toLocaleString(); 
          	},sortable:true,hidden:true},
	        { field: 'operatorName', title: '操作人姓名', hidden:true }
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