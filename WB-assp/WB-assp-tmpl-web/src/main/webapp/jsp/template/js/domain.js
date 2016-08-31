/**
 * 域名工具
 *
 * @type
 * @author :songhx@sxw100.com
 */

var Domain = {
	  //批量查询域名信息
	 batchQueryDomains : function(domainValues){
			$.ajax({
				url:rootPath+'/website/domain/queryDomains.html',
				data:{domainValues : domainValues},
				dataType:'json',
				async : true,
				success: function(data){ 
					$data = data.domainInfos;
					var resultHtml = "";
					 $.each($data ,function(i,domainInfo){
						 resultHtml +='<li>';
						 resultHtml +='<div class="d-ib domain-cell">';		
						 resultHtml +='<div class="domain">';			
						 resultHtml +='	<span>' + domainInfo.domainAddress + '</span>';				
						 resultHtml +='</div>	';
						 resultHtml +='	</div>		';	
						 resultHtml +='<div class="mod-select-else-fr">';
						 resultHtml +='<div class="d-ib price-cell">' + domainInfo.use + '</div>';	
						 if(domainInfo.returnCode == "210"){
							 resultHtml +='<div class="d-ib action-cell"><a href="/main/whois_query?domain='+domainInfo.domainAddress+'" >注册</a>';
						 }else{
							resultHtml +='<div class="d-ib action-cell"><a href="' + rootPath + '/website/domain/registDomain?domain='+domainInfo.domainAddress+'" target="_blank"></a>'; //查询注册信息
						 }
						 resultHtml +='	</div>';
						 resultHtml +='</div>';
						 resultHtml +='</li>';
					 });
					
					$(".result-row-tip").hide();
					$(".mod-box-bd").show();
					$("#resultList").html(resultHtml);
				 },
				error:function(data){
					//alert(JSON.stringify(data));
				}
		     });
	 }
}

$(function(){
	//监控查询按钮点击事件
	$(".search-btn").on("click",function(){
		var domainValues = "";
		var queryName = $("#queryName").val();
		if(queryName==""){
			alert("请输入查询信息！");
			return;
		}
		if(queryName.length > 50){
			alert("注册的域名应少于50个字符！");
			return;
		}
		var extensions = $("input[type=checkbox][name=extension]:checked");
		if(extensions.length <=0){
			alert("请选择相应的类型！");
			return;
		}
		 $("input[type=checkbox][name=extension]:checked").each(function(){
			 domainValues += queryName + $(this).val() +",";
		 });
		 if(domainValues.length > 1){
			 domainValues = domainValues.substring(0,domainValues.length-1);
		 }
		Domain.batchQueryDomains(domainValues);
	});
});

