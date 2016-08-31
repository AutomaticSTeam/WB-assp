package com.assp.modules.template.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.template.entity.TemplateLogo;
import com.assp.modules.template.mapper.TemplateLogoMapper;
import com.assp.modules.template.service.ITemplateLogoService;
import com.github.abel533.entity.Example;

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
 * @CreateDate 2016年7月4日
 */
@Service
public class TemplateLogoServiceImpl implements ITemplateLogoService {
	
	@Autowired
	private TemplateLogoMapper templateLogoMapper;

	@Override
	public TemplateLogo queryOne(TemplateLogo record) {
		// TODO Auto-generated method stub
		return templateLogoMapper.selectOne(record);
	}

	@Override
	public List<TemplateLogo> queryAll(TemplateLogo record) {
		// TODO Auto-generated method stub
		return templateLogoMapper.select(record);
	}

	@Override
	public int queryCount(TemplateLogo record) {
		// TODO Auto-generated method stub
		return templateLogoMapper.selectCount(record);
	}

	@Override
	public TemplateLogo queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return templateLogoMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateLogo record) {
		// TODO Auto-generated method stub
		return templateLogoMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateLogo record) {
		// TODO Auto-generated method stub
		return templateLogoMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateLogo record) {
		// TODO Auto-generated method stub
		return templateLogoMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return templateLogoMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateLogo record) {
		// TODO Auto-generated method stub
		return templateLogoMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateLogo record) {
		// TODO Auto-generated method stub
		return templateLogoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateLogo example) {
		// TODO Auto-generated method stub
		Example templateLogoExample = new Example(TemplateLogo.class);
		return templateLogoMapper.selectCountByExample(templateLogoExample);
	}

	@Override
	public List<TemplateLogo> queryByExample(TemplateLogo example) {
		// TODO Auto-generated method stub
		Example templateLogoExample = new Example(TemplateLogo.class);
		return templateLogoMapper.selectByExample(templateLogoExample);
	}
	

}
  