package com.assp.modules.config.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.config.entity.ColumnsType;
import com.assp.modules.config.mapper.ColumnsTypeMapper;
import com.assp.modules.config.service.IColumnsTypeService;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月27日 下午2:36:51 
 */
@Service
public class ColumnsTypeServiceImpl implements IColumnsTypeService {

	@Autowired
	private ColumnsTypeMapper  columnsTypeMapper;

	@Override
	public ColumnsType queryOne(ColumnsType record) {
		return columnsTypeMapper.selectOne(record);
	}

	@Override
	public List<ColumnsType> queryAll(ColumnsType record) {
		return columnsTypeMapper.select(record);
	}

	@Override
	public int queryCount(ColumnsType record) {
		return columnsTypeMapper.selectCount(record);
	}

	@Override
	public ColumnsType queryByPrimaryKey(Object key) {
		return columnsTypeMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(ColumnsType record) {
		return columnsTypeMapper.insert(record);
	}

	@Override
	public int addSelective(ColumnsType record) {
		return columnsTypeMapper.insertSelective(record);
	}

	@Override
	public int delete(ColumnsType record) {
		return columnsTypeMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return columnsTypeMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(ColumnsType record) {
		return columnsTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ColumnsType record) {
		return columnsTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(ColumnsType example) {
		return columnsTypeMapper.selectCountByExample(example);
	}

	@Override
	public List<ColumnsType> queryByExample(ColumnsType example) {
		return columnsTypeMapper.selectByExample(example);
	}
}
  