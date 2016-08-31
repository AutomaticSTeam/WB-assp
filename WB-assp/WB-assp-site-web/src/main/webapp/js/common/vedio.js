var commonVedio = {
		showVedio : function() {
			var mediaUrl=$("#mediaUrl").val();
			if(mediaUrl!=null&&mediaUrl!=""){
				thePlayer = jwplayer("container").setup({//通过js调用播放器并安装到指定容器（container）内
				    flashplayer: rootPath+"/static/jwplayer6.6/jwplayer.flash.swf",//调用播放器
				    file: mediaUrl,//调用视频文件
				    width: "100%",//播放器宽
				    aspectratio:"16:9",//自适应宽高比例，如果设置宽高比，可设置宽度100%,高度不用设置
				    image:"",//视频预览图片
				    controlbar: "over",//控制条位置
				    screencolor :"#c2c2c2"//播放器颜色
				});
			}
		},
};