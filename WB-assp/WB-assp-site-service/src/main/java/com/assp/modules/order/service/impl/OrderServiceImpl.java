package com.assp.modules.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.order.entity.Order;
import com.assp.modules.order.entity.OrderProduct;
import com.assp.modules.order.entity.OrderVo;
import com.assp.modules.order.mapper.OrderMapper;
import com.assp.modules.order.mapper.OrderProductMapper;
import com.assp.modules.order.service.IOrderService;

/**
 * 
 * 描述：订单 service 实现
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 下午6:12:34
 * @version
 */
@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderProductMapper opMapper;

	@Override
	public Order queryOne(Order record) {
		// TODO Auto-generated method stub
		return orderMapper.selectOne(record);
	}

	@Override
	public List<Order> queryAll(Order record) {
		// TODO Auto-generated method stub
		return orderMapper.select(record);
	}

	@Override
	public int queryCount(Order record) {
		// TODO Auto-generated method stub
		return orderMapper.selectCount(record);
	}

	@Override
	public Order queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return orderMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Order record) {
		// TODO Auto-generated method stub
		return orderMapper.insert(record);
	}

	@Override
	public int addSelective(Order record) {
		// TODO Auto-generated method stub
		return orderMapper.insertSelective(record);
	}

	@Override
	public int delete(Order record) {
		// TODO Auto-generated method stub
		return orderMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return orderMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Order record) {
		// TODO Auto-generated method stub
		return orderMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Order record) {
		// TODO Auto-generated method stub
		return orderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Order example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> queryByExample(Order example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		int i = orderMapper.insertOrder(order);
		if(i>0){
			i=order.getOrderId();
		}
		return i;
	}
	
	/**
	 * 
	 * 描述：添加 订单--订单中间表
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午8:10:13
	 * @version
	 */
	public boolean addOrderProduct(Order order,OrderProduct op){
		boolean flag=false;
		int i = addOrder(order);
		op.setOrderId(i);
		op.setIsUse(0);
		int x = opMapper.insert(op);
		if(x>0){
			flag=true;
		}
		return flag;
	}

	/**
	 * 
	 * 描述：产品订单列表
	 * 当参数为null或者"" 时，查询所有订单
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月21日 上午10:44:25
	 * @version
	 */
	public List<OrderVo> queryOrderByParam(OrderVo orderVo) {
		// TODO Auto-generated method stub
		return orderMapper.selectOrderByParam(orderVo);
	}

}
