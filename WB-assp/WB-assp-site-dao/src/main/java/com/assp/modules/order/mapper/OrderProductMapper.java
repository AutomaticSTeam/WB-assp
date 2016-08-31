package com.assp.modules.order.mapper;

import com.assp.modules.order.entity.OrderProduct;
import com.github.abel533.mapper.Mapper;

/**
 * 
 * 描述：订单产品 mapper
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 下午6:07:11
 * @version
 */
public interface OrderProductMapper extends Mapper<OrderProduct>{
	/**
	 * 
	 * 描述：根据订单id 修改付款时间
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月27日 下午2:57:11
	 * @version
	 */
	public int upCheckTimeByOrderId(OrderProduct op);
}
