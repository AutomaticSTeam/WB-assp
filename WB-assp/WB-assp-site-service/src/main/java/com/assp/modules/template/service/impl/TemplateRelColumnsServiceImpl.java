package com.assp.modules.template.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.template.entity.TemplateRelColumns;
import com.assp.modules.template.mapper.TemplateRelColumnsMapper;
import com.assp.modules.template.service.ITemplateRelColumnsService;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wzp@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年7月8日 
 */
@Service
public class TemplateRelColumnsServiceImpl implements ITemplateRelColumnsService {
	
	@Autowired
	private TemplateRelColumnsMapper templateRelColumnsMapper;

	@Override
	public TemplateRelColumns queryOne(TemplateRelColumns record) {
		return templateRelColumnsMapper.selectOne(record);
	}

	@Override
	public List<TemplateRelColumns> queryAll(TemplateRelColumns record) {
		return templateRelColumnsMapper.select(record);
	}

	@Override
	public int queryCount(TemplateRelColumns record) {
		return templateRelColumnsMapper.selectCount(record);
	}

	@Override
	public TemplateRelColumns queryByPrimaryKey(Object key) {
		return templateRelColumnsMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateRelColumns record) {
		return templateRelColumnsMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateRelColumns record) {
		return templateRelColumnsMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateRelColumns record) {
		return templateRelColumnsMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return templateRelColumnsMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateRelColumns record) {
		return templateRelColumnsMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateRelColumns record) {
		return templateRelColumnsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateRelColumns example) {
		return templateRelColumnsMapper.selectCountByExample(example);
	}

	@Override
	public List<TemplateRelColumns> queryByExample(TemplateRelColumns example) {
		return templateRelColumnsMapper.selectByExample(example);
	}

	@Override
	public TemplateRelColumns querySortNumMaxObj(Integer templateId) {
		return templateRelColumnsMapper.querySortNumMaxObj(templateId);
	}

}
  