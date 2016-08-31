package com.assp.modules.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.order.entity.Product;
import com.assp.modules.order.mapper.ProductMapper;
import com.assp.modules.order.service.IProductService;

/**
 * 
 * 描述：产品service
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 下午3:34:10
 * @version
 */
@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public Product queryOne(Product record) {
		// TODO Auto-generated method stub
		return productMapper.selectOne(record);
	}

	@Override
	public List<Product> queryAll(Product record) {
		// TODO Auto-generated method stub
		return productMapper.select(record);
	}

	@Override
	public int queryCount(Product record) {
		// TODO Auto-generated method stub
		return productMapper.selectCount(record);
	}

	@Override
	public Product queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return productMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Product record) {
		// TODO Auto-generated method stub
		return productMapper.insert(record);
	}

	@Override
	public int addSelective(Product record) {
		// TODO Auto-generated method stub
		return productMapper.insertSelective(record);
	}

	@Override
	public int delete(Product record) {
		// TODO Auto-generated method stub
		return productMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return productMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Product record) {
		// TODO Auto-generated method stub
		return productMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Product record) {
		// TODO Auto-generated method stub
		return productMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Product example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> queryByExample(Product example) {
		// TODO Auto-generated method stub
		return null;
	}

}
