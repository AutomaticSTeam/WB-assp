package com.assp.modules.order.mapper;

import java.util.List;

import com.assp.modules.order.entity.Order;
import com.assp.modules.order.entity.OrderVo;
import com.github.abel533.mapper.Mapper;

/**
 * 
 * 描述：订单 mapper
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 下午6:06:37
 * @version
 */
public interface OrderMapper extends Mapper<Order>{
	/**
	 * 
	 * 描述：返回主键id 插入
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午6:27:37
	 * @version
	 */
	public int insertOrder(Order order);
	
	/**
	 * 
	 * 描述：产品订单列表
	 * 当参数为null或者"" 时，查询所有订单
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月21日 上午10:44:25
	 * @version
	 */
	public List<OrderVo> selectOrderByParam(OrderVo orderVo);
}
