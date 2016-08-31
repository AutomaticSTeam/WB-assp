package com.assp.modules.commondata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.City;
import com.assp.modules.commondata.mapper.CityMapper;
import com.assp.modules.commondata.service.ICityService;
/**
 * 
 * 描述：城市service实现
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 上午11:44:26
 * @version
 */
@Service
public class CityServiceImpl implements ICityService {
	@Autowired
	private CityMapper cityMapper;

	@Override
	public City queryOne(City record) {
		// TODO Auto-generated method stub
		return cityMapper.selectOne(record);
	}

	@Override
	public List<City> queryAll(City record) {
		// TODO Auto-generated method stub
		return cityMapper.select(record);
	}

	@Override
	public int queryCount(City record) {
		// TODO Auto-generated method stub
		return cityMapper.selectCount(record);
	}

	@Override
	public City queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return cityMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(City record) {
		// TODO Auto-generated method stub
		return cityMapper.insert(record);
	}

	@Override
	public int addSelective(City record) {
		// TODO Auto-generated method stub
		return cityMapper.insertSelective(record);
	}

	@Override
	public int delete(City record) {
		// TODO Auto-generated method stub
		return cityMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return cityMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(City record) {
		// TODO Auto-generated method stub
		return cityMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(City record) {
		// TODO Auto-generated method stub
		return cityMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(City example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<City> queryByExample(City example) {
		// TODO Auto-generated method stub
		return null;
	}

}
