//初始化
/**
 * 魏泽超 2016-7-20 
 */
$(function(){
	
	var $province=$("select[name=province]");
	var $city=$("select[name=city]");
	var $area=$("select[name=area]");
	var $option="";
	
	//初始化 省份数据
	$.ajax({
		type:"post",
		url:rootPath+"/wms/product/getProvince.do",
		dataType: "json",
		success:function(data){
			if(data){
				var $length=data.province.length;
				var $data=data.province;
				for(var i=0;i<$length;i++){
					$option=$option+"<option value="+$data[i].provinceid+">"+$data[i].province+"</option>";
				}
			}
			$province.append($option);
			//租用时长
			setjRange('proTime',1,100,1,[1,10,20,30,40,50,60,70,80,90,100],300,true,true,'rentedTime');
			//空间
			setjRange('proSpace',20,100,1,[20,30,40,50,60,70,80,90,100],300,true,true,'spaceSize');
			//带宽
			setjRange('dkSpace',20,100,1,[20,30,40,50,60,70,80,90,100],300,true,true,'dkInfo');
			//初始化 city
			cityByProvinceId('');
			//初始化详细地址
			setAddress();
			//初始化产品价格
			setPrice();
		}
	});
	
	//根据省份查询city
	$("select[name=province]").change(function(){
		cityByProvinceId($(this).val());
	});
	//根据省份查询city
	var cityByProvinceId=function(value){
		$city.empty();
		$option="";
		$.ajax({
			type:"post",
			url:rootPath+"/wms/product/getCityByProvinceId.do",
			dataType: "json",
			data:{"provinceId":value},
			success:function(data){
				if(''!=data.city){
					var $length=data.city.length;
					var $data=data.city;
					for(var i=0;i<$length;i++){
						$option=$option+"<option value="+$data[i].cityid+">"+$data[i].city+"</option>";
					}
					$city.append($option);
					setAddress();
				}else{
					$city.empty();
				}
				areaByCityId('');
			}
		});
	}
	
	//根据city查询区域
	$("select[name=city]").change(function(){
		areaByCityId($(this).val());
	});
	//根据city查询区域
	var areaByCityId=function(value){
		$area.empty();
		if(''==value){
			value=$city.val();
		}
		if(null!=value){
			$option="";
			$.ajax({
				type:"post",
				url:rootPath+"/wms/product/getAreaByCityId.do",
				dataType: "json",
				data:{"cityId":value},
				success:function(data){
					if(''!=data.area){
						var $length=data.area.length;
						var $data=data.area;
						for(var i=0;i<$length;i++){
							$option=$option+"<option>"+$data[i].area+"</option>";
						}
						$area.append($option);
						setAddress();
					}else{
						$area.find("option").remove();
					}
				}
			});
		}
	}
	
	$("select[name=area]").change(function(){
		//赋值到详细地址
		setAddress();
	});
	
	//设置详细地址
	var setAddress=function(){
		var $userAddress=$("input[name=userAddress]");
		var $provinceText=$province.find("option:selected").text();
		var $cityText=$city.find("option:selected").text();
		var $areaText=$area.find("option:selected").text();
		$userAddress.val($provinceText+$cityText+$areaText);
	}
	
	
	//设置滑条
	//from 滑动范围的最小值，数字，如0
	//to 滑动范围的最大值，数字，如100
	//step 步长值，每次滑动大小
	//scale 滑动条下方的尺度标签，数组类型，如[0,50,100]
	//showLabels 布尔型，是否显示滑动条下方的尺寸标签
	//showScale 布尔型，是否显示滑块上方的数值标签
	//format 数值格式
	//width 滑动条宽度
	//isRange 是否为选取范围
	var setjRange=function(className,from,to,step,scale,width,showLabels,showScale,inputName){
	   $("."+className+"").jRange({
			from: from,
			to: to,
			step: step,
			scale: scale,
			format: '%s',
			width: width,
			showLabels: showLabels,
			showScale: showScale
		});
	   
	   //找到当前滑条
	   $("#"+className+"").find("div[class=clickable-dummy]").attr("data-class",className);
	   
	   /*//当滑动条数值改变,触发事件
	   $("#"+className+"").find("input[name="+className+"]").on('input',function(e){
	       alert('Changed!')
	   });*/
	   
	   //滑动滑条 文本框赋值
	   $("div[data-class="+className+"]").click(function(){
		   var $value=$("."+className+"").val();
		   var $input=$("input[name="+inputName+"]");
		   $input.val($value);
		   setPrice();
	   })
	}
	
	//提交表单
	$("#saveBtn").click(function(){
		var options = {
				url : rootPath + "/wms/order/addOrder.do",
				type : "post",
				dataType : "json",
				// data:{orderNo:orderNo,productField:productField,productPrice:productPrice,paymentType:paymentType,productTime:productTime},
				data : {
					provinceName:$province.find("option:selected").text(),
					cityName : $city.find("option:selected").text()
				},
				success : function(data) {
					if (data.status) {
						alert("下单成功");
						window.location.href=rootPath+"/wms/order/toPayOrder.do?orderId="+data.orderId+"";
					} else {
						alert("下单失败");
					}
				}
			};
		if(form1.check()){
			$("#articleForm").ajaxSubmit(options);
		}
	})
	
	
	//带宽类型 click 事件
	$("input[name=dkType]").click(function(){
		setPrice();
	});
	
	//运营商 change事件
	$("select[name=dkOperators]").change(function(){
		setPrice();
	});
	
	//价格计算
	var setPrice=function(){
		var $totalPrice=0;
		//租用时长
		var $rentedTime=$("input[name=rentedTime]").val();
		if(''!=$rentedTime){
			//时长的单价
			var $rentedPrice=$("input[name=proTimePrice]").val();
			$totalPrice=parseFloat($totalPrice)+parseFloat($rentedTime*$rentedPrice)
		}
		
		//空间的大小
		var $spaceSize=$("input[name=spaceSize]").val();
		if(''!=$spaceSize){
			//空间单价
			var $spacePrice=$("input[name=spacePrice]").val();
			$totalPrice=parseFloat($totalPrice)+parseFloat($spaceSize*$spacePrice)
		}
		
		//带宽类型
		var $dkType=$("input[name=dkType]:checked").val();
		//带宽信息
		var $dkInfo=$("input[name=dkInfo]").val();
		//带宽运营商
		var $dkOperators=$("select[name=dkOperators] option:selected").attr("data-price");
		if(''!=$dkInfo){
			var $dkPrice=parseFloat($dkType*$dkInfo)+parseFloat($dkOperators);
			$totalPrice=parseFloat($totalPrice)+parseFloat($dkPrice)
		}
		$("#price").text($totalPrice);
		$("input[name=orderPrice]").val($totalPrice);
	}
	
});