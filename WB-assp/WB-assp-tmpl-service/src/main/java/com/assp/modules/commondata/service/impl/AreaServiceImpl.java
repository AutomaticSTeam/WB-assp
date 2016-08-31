package com.assp.modules.commondata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.Area;
import com.assp.modules.commondata.mapper.AreaMapper;
import com.assp.modules.commondata.service.IAreaService;

/**
 * 
 * 描述：区域service 实现
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 上午11:45:18
 * @version
 */
@Service
public class AreaServiceImpl implements IAreaService {
	
	@Autowired
	private AreaMapper areaMapper;

	@Override
	public Area queryOne(Area record) {
		// TODO Auto-generated method stub
		return areaMapper.selectOne(record);
	}

	@Override
	public List<Area> queryAll(Area record) {
		// TODO Auto-generated method stub
		return areaMapper.select(record);
	}

	@Override
	public int queryCount(Area record) {
		// TODO Auto-generated method stub
		return areaMapper.selectCount(record);
	}

	@Override
	public Area queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return areaMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Area record) {
		// TODO Auto-generated method stub
		return areaMapper.insert(record);
	}

	@Override
	public int addSelective(Area record) {
		// TODO Auto-generated method stub
		return areaMapper.insertSelective(record);
	}

	@Override
	public int delete(Area record) {
		// TODO Auto-generated method stub
		return areaMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return areaMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Area record) {
		// TODO Auto-generated method stub
		return areaMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Area record) {
		// TODO Auto-generated method stub
		return areaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Area example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Area> queryByExample(Area example) {
		// TODO Auto-generated method stub
		return null;
	}

}
