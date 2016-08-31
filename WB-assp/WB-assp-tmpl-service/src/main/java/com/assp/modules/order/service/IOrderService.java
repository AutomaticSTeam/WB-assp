package com.assp.modules.order.service;

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.order.entity.Order;
import com.assp.modules.order.entity.OrderProduct;
import com.assp.modules.order.entity.OrderVo;
/**
 * 
 * 描述：order Service 接口
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 下午6:08:30
 * @version
 */
public interface IOrderService extends BasicService<Order>{
	/**
	 * 
	 * 描述：返回主键id 插入
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午6:27:37
	 * @version
	 */
	public int addOrder(Order order);
	
	/**
	 * 
	 * 描述：插入订单-以及订单中间表
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午7:41:27
	 * @version
	 */
	public boolean addOrderProduct(Order order,OrderProduct op);
	
	/**
	 * 
	 * 描述：产品订单列表
	 * 当参数为null或者"" 时，查询所有订单
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月21日 上午10:44:25
	 * @version
	 */
	public List<OrderVo> queryOrderByParam(OrderVo orderVo);
}
