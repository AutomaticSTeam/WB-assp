/**
 * 字符串处理工具
 * 
 * @type
 * @author :songhx@sxw100.com
 */
var StringUtils = {

	/**
	 * 去除左侧空格
	 * 
	 * @param {}
	 *            str 源字符串
	 * @return 去除左侧空格后字符串
	 */
	ltrim : function(str) {
		var whitespace = new String(" \t\n\r");
		var s = new String(str);

		if (whitespace.indexOf(s.charAt(0)) != -1) {
			var j = 0, i = s.length;
			while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
				j++;
			}
			s = s.substring(j, i);
		}
		return s;
	},

	/**
	 * 去除右侧空格
	 * 
	 * @param {}
	 *            str 源字符串
	 * @return 去除右侧空格后字符串
	 */
	rtrim : function(str) {
		var whitespace = new String(" \t\n\r");
		var s = new String(str);

		if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
			var i = s.length - 1;
			while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {
				i--;
			}
			s = s.substring(0, i + 1);
		}
		return s;
	},

	/**
	 * 去除两侧空格
	 * 
	 * @param {}
	 *            str 源字符串
	 * @return 去除两侧空格后字符串
	 */
	trim : function(str) {
		return this.rtrim(this.ltrim(str));
	},

	/**
	 * 判断字符串是否为空
	 * 
	 * @param {}
	 *            str 源字符串
	 * @return 字符串是否为空
	 */
	isEmpty : function(str) {
		return str == null || str.length == 0;
	},

	/**
	 * 判断字符串是否为数字，包括正/负的整数或浮点数
	 * 
	 * @param {}
	 *            str 源字符串
	 * @return 字符串是否为数字
	 */
	isNum : function(str) {
		var exp1 = new RegExp("^(\\+|-)?\\d+\\.?\\d*$");
		var exp2 = new RegExp("^(\\+|-)?\\d*\\.?\\d+$");
		return exp1.test(str) || exp2.test(str);
	},

	/**
	 * 判断字符串是否为整数
	 * 
	 * @param {}
	 *            str 源字符串
	 * @return 字符串是否为整数
	 */
	isInt : function(str) {
		var exp = new RegExp("^(\\+|-)?\\d+$");
		return exp.test(str);
	},

	/**
	 * 判断字符串是否为自然数。自然数的范围为非负整数。
	 * 
	 * @param {}
	 *            str 源字符串
	 * @return 字符串是否为自然数
	 */
	isNaturalNumber : function(str) {
		var exp = new RegExp("^\\+?\\d+$");
		return exp.test(str);
	},

	/**
	 * 判断字符串是否为布尔值
	 * 
	 * @param {}
	 *            str 源字符串
	 * @return 字符串是否为布尔值
	 */
	isBoolean : function(str) {
		str = str.toLowerCase();
		return "true" == str || "false" == str;
	},

	/**
	 * 判断字符串是否仅由拉丁字母组成
	 * 
	 * @param {}
	 *            str 源字符串
	 * @return 字符串是否仅含有拉丁字母
	 */
	isLetters : function(str) {
		var exp = new RegExp("^[A-z]+$");
		return exp.test(str);
	},

	/**
	 * 判断字符串是否只包括英文字母、数字. by DengGuo
	 * @param value
	 * @returns
	 */
	englishCheckSub: function(value){  
		var exp = new RegExp("^[a-zA-Z0-9]+$");
		return exp.test(value);             
	},                      
	/**
	 * 判断字符串str是否包含某字符串value。当被判断字符串或被包含字符串为null时，方法返回false。空串被所有字符串包含。
	 * 
	 * @param {}
	 *            str 源字符串
	 * @param {}
	 *            value 目标字符串
	 * @return 源字符串中是否含有目标字符串
	 */
	contains : function(str, value) {
		var string = new String(str);
		var index = string.indexOf(value);
		if (index == -1) {
			return false
		} else {
			return true;
		}
	},

	/**
	 * 驼峰式字符串转化为下划线字符串
	 * 
	 * @param {}
	 *            str 驼峰式的源字符串
	 * @return 对应的下划线式字符串
	 */
	camel2Underline : function(str) {
		if (str == null) {
			return null;
		}
		if (str.length == 0) {
			return "";
		}
		var string = new String(str);
		var result = "";
		for (var i = 0; i < string.length; i++) {
			var c = string.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				result += ('_' + c);
			} else {
				result += c;
			}
		}
		return result.toUpperCase();
	},

	/**
	 * 普通字符串转化为下划线字符串。转化过程中并不按照大写字母来作为一个单词的开始，而使用splitIndex来标志各个单词的开始位置。
	 * 
	 * @param {}
	 *            str 源字符串
	 * @param {}
	 *            splitIndexes 切分的索引
	 * @return 对应的下划线式字符串
	 */
	string2Underline : function(str, splitIndexes) {
		if (str == null) {
			return null;
		}
		if (str.length == 0) {
			return "";
		}
		var string = new String(str);
		var result = "";
		for (var i = 0; i < string.length; i++) {
			var split = false;
			for (var j = 0; j < splitIndexes.length; j++) {
				if (splitIndexes[j] == i) {
					split = true;
				}
			}
			if (split) {
				result += ('_' + c);
			} else {
				result += c;
			}
		}
		return result.toUpperCase();
	},

	/**
	 * 下划线字符串转化为驼峰式字符串
	 * 
	 * @param {}
	 *            str 下划线式的源字符串
	 * @return 对应的驼峰式字符串
	 */
	underline2Camel : function(str) {
		if (str == null) {
			return null;
		}
		if (str.length == 0) {
			return "";
		}
		var string = new String(str.toLowerCase());
		var values = string.split("_");
		var result = "";
		for (var i = 0; i < values.length; i++) {
			if (i == 0) {
				result += values[i];
			} else {
				result += values[i].substr(0, 1).toUpperCase()
						+ values[i].substr(1, values[i].length - 1);
			}
		}
		return result;
	},

	/**
	 * 增加formatString功能
	 * 
	 * @author 
	 * 
	 * @example sy.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
	 * 
	 * @returns 格式化后的字符串
	 */
	formatString : function(str) {
		for (var i = 0; i < arguments.length - 1; i++) {
			str = str.replace("{" + i + "}", arguments[i + 1]);
		}
		return str;
	},
	/**按长度从起始位置截取字符
	 * str 截取字符串对象
	 * len 截取长度getStrinLen
	 * */
	getStrInLen:function(str,len){
		return (str == null || str.length <= len) ? str : str.substring(0,len);
	}
	
}

function checkAjaxError(data,rootPath){
	if(data != null){
		if(data == "{\"result\":\"login\"}"){
			top.location.href = rootPath+"/jsp/common/login.jsp";
		}else{
			var msg = data.result;
			if(msg == "login"){
				top.location.href = rootPath+"/jsp/common/login.jsp";
			}else if(msg == "Nologin"){
				window.location.href = rootPath+"/jsp/error/ajaxerror.jsp";
			}
		}
	}
}

