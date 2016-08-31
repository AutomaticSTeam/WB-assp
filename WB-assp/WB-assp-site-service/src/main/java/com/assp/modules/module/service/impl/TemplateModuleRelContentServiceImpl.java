package com.assp.modules.module.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.module.entity.TemplateModuleRelContent;
import com.assp.modules.module.mapper.TemplateModuleRelContentMapper;
import com.assp.modules.module.service.ITemplateModuleRelContentService;

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
 * @CreateDate 2016年5月6日 下午5:32:29 
 */
@Service
public class TemplateModuleRelContentServiceImpl implements ITemplateModuleRelContentService {

	@Autowired
	private TemplateModuleRelContentMapper templateModuleRelContentMapper;
	
	@Override
	public TemplateModuleRelContent queryOne(TemplateModuleRelContent record) {
		return templateModuleRelContentMapper.selectOne(record);
	}

	@Override
	public List<TemplateModuleRelContent> queryAll(
			TemplateModuleRelContent record) {
		return templateModuleRelContentMapper.select(record);
	}

	@Override
	public int queryCount(TemplateModuleRelContent record) {
		return templateModuleRelContentMapper.selectCount(record);
	}

	@Override
	public TemplateModuleRelContent queryByPrimaryKey(Object key) {
		return templateModuleRelContentMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateModuleRelContent record) {
		return templateModuleRelContentMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateModuleRelContent record) {
		return templateModuleRelContentMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateModuleRelContent record) {
		return templateModuleRelContentMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return templateModuleRelContentMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateModuleRelContent record) {
		return templateModuleRelContentMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateModuleRelContent record) {
		return templateModuleRelContentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateModuleRelContent example) {
		return templateModuleRelContentMapper.selectCountByExample(example);
	}

	@Override
	public List<TemplateModuleRelContent> queryByExample(
			TemplateModuleRelContent example) {
		return null;
	}

}
  