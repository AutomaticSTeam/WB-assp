/**
 * this js  aims to deal vedio   
 * @type
 * @author :songhx@sxw100.com
 */
$(function(){

	//加载视频分类信息
	 ContentsManage.drawMediaTypesDataView(1);
	 
	 if(mediaId != null && mediaId != 'null' && mediaId != '' ){
		 selectedMediaType(mediaId);
	 }
	

	$("input,textarea").each(function(){ 
	     $(this).attr('readonly','readonly'); 
	});
});

//选中关联类型
var selectedMediaType = function(id){
		 $.ajax({
				url:rootPath+'/contents/media/queryMediaTypes.do',
				data:{dataStatus : 0},
				dataType:'json',
				async : true,
				success: function(data){ 
					$data = data.mediaTypes;
					 var resultHtml = "";
					 $.each($data ,function(i,mediaType){
						 resultHtml +='  <span class="checkbox"><input type="checkbox" name="mediaTypeId" id="" value="'+mediaType.mediaTypeId+'">'+mediaType.mediaTypeName+' </span>';
					 });
					$("#mediaCkBoxList").html( resultHtml); //复选框
					 $.ajax({
							type:'post',
							 url:rootPath+'/contents/media/queryMediaTypeRels.do',
							data:{
								mediaId : id ,
							},
							dataType:'json',
							async : true,
							success: function(data){ 
								$data = data.mediaRelTypes;
								$.each($data ,function(i,mediaRelType){
									$("input[name='mediaTypeId']").each(function(){
										 if($(this).val()  == mediaRelType.mediaTypeId){
											 $(this).attr('checked',true);
										 }
									 });
								 });
								 $("#mediaCkBoxList").find("input[type='checkbox']").attr('disabled','disabled'); 
							 },
							error:function(data){
								//alert(JSON.stringify(data));
							}
					     });
				}
		});

}

//设置附图
var setImg =  function(index, layero){
	var body = layer.getChildFrame('body', index);
	var pUrls=body.find("input[id='pictureUrls']").val();
	if(pUrls != "" && pUrls.length > 0){
		pUrls = pUrls.substring(0,(pUrls.length - 1));
		$("#thumbImgId").attr("src",pUrls);
		$("#mediaAttachmentImg").val(pUrls);
		$("#addImgBtn").hide();
	}
	//var pNames=body.find("input[id='pictureNames']").val();
	layer.close(index);
  }

