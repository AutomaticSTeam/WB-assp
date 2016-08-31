package com.assp.modules.commondata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.Province;
import com.assp.modules.commondata.mapper.ProvinceMapper;
import com.assp.modules.commondata.service.IProvinceService;

/**
 * 
 * 描述：省份 service实现
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 上午11:42:08
 * @version
 */
@Service
public class ProvinceServiceImpl implements IProvinceService {
	
	@Autowired
	private ProvinceMapper provinceMapper;

	@Override
	public Province queryOne(Province record) {
		// TODO Auto-generated method stub
		return provinceMapper.selectOne(record);
	}

	@Override
	public List<Province> queryAll(Province record) {
		// TODO Auto-generated method stub
		return provinceMapper.select(record);
	}

	@Override
	public int queryCount(Province record) {
		// TODO Auto-generated method stub
		return provinceMapper.selectCount(record);
	}

	@Override
	public Province queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return provinceMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Province record) {
		// TODO Auto-generated method stub
		return provinceMapper.insert(record);
	}

	@Override
	public int addSelective(Province record) {
		// TODO Auto-generated method stub
		return provinceMapper.insertSelective(record);
	}

	@Override
	public int delete(Province record) {
		// TODO Auto-generated method stub
		return provinceMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return provinceMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Province record) {
		// TODO Auto-generated method stub
		return provinceMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Province record) {
		// TODO Auto-generated method stub
		return provinceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Province example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Province> queryByExample(Province example) {
		// TODO Auto-generated method stub
		return null;
	}

}
