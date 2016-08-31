/**图片处理
 * 
 */
var Img = {
		// 计算图片大小
		calcSize:function(width,height,maxwidth,maxheight){
			var size = new Object();
			size.width = width;
			size.height = height;
			if(size.width>maxwidth){
				size.width=maxwidth;
				size.height = (maxwidth*height/width).toFixed(1);
			}
			if(size.height>maxheight){
				size.height = maxheight;
				size.width = (maxheight*width/height).toFixed(1);
			}
			return size;
		},
		// 获取图片名字
		getImageName: function(name){
			if(name == void 0 || name == null){
				return "";
			}
			var lastPot = name.lastIndexOf(".");
			return (lastPot == -1) ? name.trim() : name.substring(0,lastPot).trim();
		},
		// 获取图片名字
		getImageTypeByName: function(name){
			if(name == void 0 || name == null){
				return "";
			}
			var lastPot = name.lastIndexOf(".");
			
			return (lastPot == -1) ? "" : name.substring(lastPot + 1).trim();
		}
}