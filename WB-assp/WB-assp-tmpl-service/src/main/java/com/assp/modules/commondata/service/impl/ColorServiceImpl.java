package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.Color;
import com.assp.modules.commondata.mapper.ColorMapper;
import com.assp.modules.commondata.service.IColorService;

/**
 * 类简述
 * <p>
 *      颜色业务处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangzhipeng@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年6月27日
 */
@Service
public class ColorServiceImpl implements IColorService {

	@Autowired
	private ColorMapper  colorMapper;

	@Override
	public Color queryOne(Color record) {
		// TODO Auto-generated method stub
		return colorMapper.selectOne(record);
	}

	@Override
	public List<Color> queryAll(Color record) {
		// TODO Auto-generated method stub
		return colorMapper.select(record);
	}

	@Override
	public int queryCount(Color record) {
		// TODO Auto-generated method stub
		return colorMapper.selectCount(record);
	}

	@Override
	public Color queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return colorMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Color record) {
		// TODO Auto-generated method stub
		return colorMapper.insert(record);
	}

	@Override
	public int addSelective(Color record) {
		// TODO Auto-generated method stub
		return colorMapper.insertSelective(record);
	}

	@Override
	public int delete(Color record) {
		// TODO Auto-generated method stub
		return colorMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return colorMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Color record) {
		// TODO Auto-generated method stub
		return colorMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Color record) {
		// TODO Auto-generated method stub
		return colorMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Color example) {
		// TODO Auto-generated method stub
		return colorMapper.selectCountByExample(example);
	}

	@Override
	public List<Color> queryByExample(Color example) {
		// TODO Auto-generated method stub
		return colorMapper.selectByExample(example);
	}

	

}
  