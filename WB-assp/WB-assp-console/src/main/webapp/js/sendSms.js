/**
 * 发送短信验证码
 * 
 * 使用时请注意界面上对应相应的属性和样式
 * 
 * @type
 * @author :songhx@sxw100.com
 */
 /**
 * 发送验证码
 */
var sendSms={
	count:0,//防止强刷
	timer:null,//计时器
	//发送验证码
	sendSms:function(url,datas,verifyCode){
		if(sendSms.count>0){return;}
		$("#getSmsInfoBtn").attr("disabled",true);
		//清空之前发送短信验证码的提示信息
  		$("#"+verifyCode).val("");
		$.ajax({
			  	type: "post",
			  	dataType:"json",
			  	url:url,
			  	data:datas,
			    async :true,
			  	success: function(rst) {
			  		if(!rst){
			  			$("#smsIcon").removeClass("right_icon1").addClass("error_icon1");
			  			$('#sendSmsResult').text('验证码发送失败！');
			  		} else {
			  			$("#smsIcon").removeClass("error_icon1").addClass("right_icon1");
			  			$('#sendSmsResult').text('验证码已发送！');
			  			sendSms.timeout();
			  			$("#"+verifyCode).attr("disabled",false);//发送验证码后，允许输入验证码
			  		}
			  	}
		});
	},
	//倒计时
	timeout:function(){
		$('#getSmsInfoBtn').html('<b id="waitBtn"> 90</b>秒后重新获取');
		sendSms.timer = self.setInterval(sendSms.addSec,1000);
	},
	
	//添加显示的秒数
	addSec:function(){
		var t = $('#waitBtn').html();
		if(t > 0){
			$('#waitBtn').html(t-1);
			sendSms.count = t;
		}else{
			sendSms.count = 0;
			window.clearInterval(sendSms.timer);
			$("#getSmsInfoBtn").html('<b id="waitBtn"></b>重新获取验证码');
			$("#sendSmsResult").text("");
			$("#smsIcon").removeClass("error_icon1").removeClass("right_icon1");
			$("#getSmsInfoBtn").attr("disabled",false);
		}
	}
};
 