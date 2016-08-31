/**
 * 数学处理工具
 * 
 * @type
 * @author :songhx@sxw100.com
 */
var MathUtils = {

	/**
	 * 精确加法
	 * 
	 * @param {}
	 *            operand1 操作数1
	 * @param {}
	 *            operand2 操作数2
	 * @return 和
	 */
	add : function(operand1, operand2) {
		var r1, r2, m;
		try {
			r1 = operand1.toString().split(".")[1].length;
		} catch (e) {
			r1 = 0;
		}
		try {
			r2 = operand2.toString().split(".")[1].length;
		} catch (e) {
			r2 = 0;
		}
		m = Math.pow(10, Math.max(r1, r2));
		return (this.mul(operand1, m) + this.mul(operand2, m)) / m;
	},

	/**
	 * 精确减法
	 * 
	 * @param {}
	 *            operand1 操作数1
	 * @param {}
	 *            operand2 操作数2
	 * @return 差
	 */
	sub : function(operand1, operand2) {
		return this.add(operand1, -operand2);
	},

	/**
	 * 精确乘法
	 * 
	 * @param {}
	 *            operand1 操作数1
	 * @param {}
	 *            operand2 操作数2
	 * @return 积
	 */
	mul : function(operand1, operand2) {
		var m = 0, s1 = operand1+"", s2 = operand2+"";
		try {
			m += s1.split(".")[1].length;
		} catch (e) {
		}
		try {
			m += s2.split(".")[1].length;
		} catch (e) {
		}
		return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
				/ Math.pow(10, m);
	},

	/**
	 * 精确除法
	 * 
	 * @param {}
	 *            operand1 操作数1
	 * @param {}
	 *            operand2 操作数2
	 * @return 商            
	 */
	div : function(operand1, operand2) {
		var t1 = 0, t2 = 0, r1, r2;
		try {
			t1 = operand1.toString().split(".")[1].length;
		} catch (e) {
		}
		try {
			t2 = operand2.toString().split(".")[1].length;
		} catch (e) {
		}
		with (Math) {
			r1 = Number(operand1.toString().replace(".", ""));
			r2 = Number(operand2.toString().replace(".", ""));
			return (r1 / r2) * pow(10, t2 - t1);
		}
	},

	/**
	 * 得到百分比字符串，默认精度为10
	 * 
	 * @param {}
	 *            operand 源数据
	 * @param {}
	 *            scale 精度
	 * @return 百分比字符串
	 */
	getPercentString : function(operand, scale) {
		return this.round(this.mul(oprand, 100), scale) + "%";
	},

	/**
	 * 四舍五入
	 * 
	 * @param {}
	 *            operand 源数据
	 * @param {}
	 *            scale 精度
	 * @return 四舍五入后的数值
	 */
	round : function(operand, scale) {
		return this.div(Math.round(this.mul(operand, Math.pow(10, scale))),
				Math.pow(10, scale));
	},

	/**
	 * 向上取整
	 * 
	 * @param {}
	 *            operand 源数据
	 * @param {}
	 *            scale 精度
	 * @return 向上取整后的数值
	 */
	roundUp : function(operand, scale) {
		return this.div(Math.ceil(this.mul(operand, Math.pow(10, scale))), Math
						.pow(10, scale));
	},

	/**
	 * 向下取整
	 * 
	 * @param {}
	 *            operand 源数据
	 * @param {}
	 *            scale 精度
	 * @return 向下取整后的数值
	 */
	roundDown : function(operand, scale) {
		return this.div(Math.floor(this.mul(operand, Math.pow(10, scale))),
				Math.pow(10, scale));
	},

	/**
	 * 随机整数。随机数集合左闭右开
	 * 
	 * @param {}
	 *            min 最小值
	 * @param {}
	 *            max 最大值+1
	 * @param {}
	 *            skip 随机间隔
	 * @return 范围内的随机数
	 */
	randomInt : function(min, max, skip) {
		if (skip == null || skip == 0) {
			skip = 1;
		}
		return parseInt(Math.random() * (max - min) / step) * step + min;
	},

	/**
	 * 随机浮点数。随机数集合左闭右开
	 * 
	 * @param {}
	 *            min 最小值
	 * @param {}
	 *            max 最大值
	 * @return 范围内的随机数
	 */
	randomDouble : function(min, max) {
		return Math.random() * (max - min) + min;
	},

	/**
	 * 幂运算
	 * 
	 * @param {}
	 *            base 底数
	 * @param {}
	 *            exponent 指数
	 * @return 幂运算
	 */
	power : function(base, exponent) {
		// 如果指数为整数，则进行重复精确乘法
		if (parseInt(exponent) == exponent) {
			// 若指数为整数，则直接返回重复乘积
			if (exponent > 0) {
				var pro = base;
				for (var i = 1; i < exponent; i++) {
					pro = mul(pro, base);
				}
				return pro;
			}
			// 若指数为负数，则在通过重复乘法后，求乘积倒数
			else if (exponent < 0) {
				var pro = base;
				for (var i = -1; i > exponent; i--) {
					pro = mul(pro, base);
				}
				return dic(1, pro);
			}
			// 指数为0，直接返回1
			else {
				return 1;
			}
		}
		// 若指数不为整数，则通过js原有方法实现
		else {
			return Math.pow(base, exponent);
		}
	},

	/**
	 * 开方运算
	 * 
	 * @param {}
	 *            base 底数
	 * @param {}
	 *            exponent 指数
	 * @return 开放结果
	 */
	evolution : function(base, exponent) {
		return this.power(base, this.div(1, exponent));
	},
	
	/**
	 * 格式化价格:两位浮点数
	 * 
	 */
	formatPrice : function(value) {
		if(value==0){
			return "0.00";
		}
		if (value == null || value == "") {
			return "";
		}
		value = value.toString();
		var reg = /^(0|[1-9][0-9]*)\.(\d{2})$/;

		if (value.match(reg) != null) {
			return value;
		}

		if (value.indexOf('.') <= 0) {
			return (value + '.00');
		}

		var p = value.split('.');
		if (p[1] != null && p[1].length == 1) {
			return (value + '0');
		}
		return p[0] + '.' + p[1].substr(0, 2);
	},
	
	formatPriceArea : function(value1,conn,value2){
		return this.formatPrice(value1)+conn+this.formatPrice(value2);
	}
	
};