package com.assp.modules.order.service;

import com.assp.common.service.BasicService;
import com.assp.modules.order.entity.OrderProduct;
/**
 * 
 * 描述：订单产品 Service 接口
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 下午6:09:04
 * @version
 */
public interface IOrderProductService extends BasicService<OrderProduct>{
	/**
	 * 
	 * 描述：根据订单id 修改付款时间
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月27日 下午2:57:11
	 * @version
	 */
	public int upCheckTimeByOrderId(OrderProduct op);
}
