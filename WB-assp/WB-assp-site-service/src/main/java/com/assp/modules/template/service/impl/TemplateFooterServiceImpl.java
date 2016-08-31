package com.assp.modules.template.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.template.entity.TemplateFooter;
import com.assp.modules.template.mapper.TemplateFooterMapper;
import com.assp.modules.template.service.ITemplateFooterService;

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
 * @CreateDate 2016年4月27日 上午10:02:16 
 */
@Service
public class TemplateFooterServiceImpl implements ITemplateFooterService {
	
	@Autowired
	private TemplateFooterMapper templateFooterMapper;

	@Override
	public TemplateFooter queryOne(TemplateFooter record) {
		return templateFooterMapper.selectOne(record);
	}

	@Override
	public List<TemplateFooter> queryAll(TemplateFooter record) {
		return templateFooterMapper.select(record);
	}

	@Override
	public int queryCount(TemplateFooter record) {
		return templateFooterMapper.selectCount(record);
	}

	@Override
	public TemplateFooter queryByPrimaryKey(Object key) {
		return templateFooterMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateFooter record) {
		return templateFooterMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateFooter record) {
		return templateFooterMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateFooter record) {
		return templateFooterMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return templateFooterMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateFooter record) {
		return templateFooterMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateFooter record) {
		return templateFooterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateFooter example) {
		return templateFooterMapper.selectCountByExample(example);
	}

	@Override
	public List<TemplateFooter> queryByExample(TemplateFooter example) {
		return null;
	}

}
  