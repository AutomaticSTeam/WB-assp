package com.assp.modules.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.order.entity.OrderProduct;
import com.assp.modules.order.mapper.OrderProductMapper;
import com.assp.modules.order.service.IOrderProductService;

/**
 * 
 * 描述：产品 订单 service 实现
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 下午6:12:17
 * @version
 */
@Service
public class OrderProductServiceImpl implements IOrderProductService {
	
	@Autowired
	private OrderProductMapper opMapper;

	@Override
	public OrderProduct queryOne(OrderProduct record) {
		// TODO Auto-generated method stub
		return opMapper.selectOne(record);
	}

	@Override
	public List<OrderProduct> queryAll(OrderProduct record) {
		// TODO Auto-generated method stub
		return opMapper.select(record);
	}

	@Override
	public int queryCount(OrderProduct record) {
		// TODO Auto-generated method stub
		return opMapper.selectCount(record);
	}

	@Override
	public OrderProduct queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return opMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(OrderProduct record) {
		// TODO Auto-generated method stub
		return opMapper.insert(record);
	}

	@Override
	public int addSelective(OrderProduct record) {
		// TODO Auto-generated method stub
		return opMapper.insertSelective(record);
	}

	@Override
	public int delete(OrderProduct record) {
		// TODO Auto-generated method stub
		return opMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return opMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(OrderProduct record) {
		// TODO Auto-generated method stub
		return opMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderProduct record) {
		// TODO Auto-generated method stub
		return opMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(OrderProduct example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderProduct> queryByExample(OrderProduct example) {
		// TODO Auto-generated method stub
		return null;
	}

}
