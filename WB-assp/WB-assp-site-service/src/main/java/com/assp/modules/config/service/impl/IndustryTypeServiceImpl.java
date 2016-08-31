package com.assp.modules.config.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.config.entity.IndustryType;
import com.assp.modules.config.mapper.IndustryTypeMapper;
import com.assp.modules.config.service.IIndustryTypeService;

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
 * @CreateDate 2016年4月19日 下午6:06:14 
 */
@Service
public class IndustryTypeServiceImpl  implements IIndustryTypeService{
	
	@Autowired
	private IndustryTypeMapper  industryTypeMapper;

	@Override
	public IndustryType queryOne(IndustryType record) {
		return industryTypeMapper.selectOne(record);
	}

	@Override
	public List<IndustryType> queryAll(IndustryType record) {
		return industryTypeMapper.select(record);
	}

	@Override
	public int queryCount(IndustryType record) {
		return industryTypeMapper.selectCount(record);
	}

	@Override
	public IndustryType queryByPrimaryKey(Object key) {
		return industryTypeMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(IndustryType record) {
		return industryTypeMapper.insert(record);
	}

	@Override
	public int addSelective(IndustryType record) {
		return industryTypeMapper.insertSelective(record);
	}

	@Override
	public int delete(IndustryType record) {
		return industryTypeMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return industryTypeMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(IndustryType record) {
		return industryTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(IndustryType record) {
		return industryTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(IndustryType example) {
		return 0;
	}

	@Override
	public List<IndustryType> queryByExample(IndustryType example) {
		return null;
	}

}
  