package com.assp.modules.template.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.template.entity.TemplateFooterRelColumns;
import com.assp.modules.template.mapper.TemplateFooterRelColumnsMapper;
import com.assp.modules.template.service.ITemplateFooterRelColumnsService;

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
 * @CreateDate 2016年7月11日 
 */
@Service
public class TemplateFooterRelColumnsServiceImpl implements ITemplateFooterRelColumnsService {
	
	@Autowired
	private TemplateFooterRelColumnsMapper templateFooterRelColumnsMapper;

	@Override
	public TemplateFooterRelColumns queryOne(TemplateFooterRelColumns record) {
		return templateFooterRelColumnsMapper.selectOne(record);
	}

	@Override
	public List<TemplateFooterRelColumns> queryAll(TemplateFooterRelColumns record) {
		return templateFooterRelColumnsMapper.select(record);
	}

	@Override
	public int queryCount(TemplateFooterRelColumns record) {
		return templateFooterRelColumnsMapper.selectCount(record);
	}

	@Override
	public TemplateFooterRelColumns queryByPrimaryKey(Object key) {
		return templateFooterRelColumnsMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateFooterRelColumns record) {
		return templateFooterRelColumnsMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateFooterRelColumns record) {
		return templateFooterRelColumnsMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateFooterRelColumns record) {
		return templateFooterRelColumnsMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return templateFooterRelColumnsMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateFooterRelColumns record) {
		return templateFooterRelColumnsMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateFooterRelColumns record) {
		return templateFooterRelColumnsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateFooterRelColumns example) {
		return templateFooterRelColumnsMapper.selectCountByExample(example);
	}

	@Override
	public List<TemplateFooterRelColumns> queryByExample(TemplateFooterRelColumns example) {
		return templateFooterRelColumnsMapper.selectByExample(example);
	}

}
  