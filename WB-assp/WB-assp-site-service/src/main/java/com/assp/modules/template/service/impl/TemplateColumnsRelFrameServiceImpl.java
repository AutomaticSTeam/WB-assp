package com.assp.modules.template.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.template.entity.TemplateColumnsRelFrame;
import com.assp.modules.template.mapper.TemplateColumnsRelFrameMapper;
import com.assp.modules.template.service.ITemplateColumnsRelFrameService;

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
 * @CreateDate 2016年4月27日 上午9:44:46 
 */
@Service
public class TemplateColumnsRelFrameServiceImpl implements ITemplateColumnsRelFrameService {
	
	@Autowired
	private TemplateColumnsRelFrameMapper templateColumnsRelFrameMapper;

	@Override
	public TemplateColumnsRelFrame queryOne(TemplateColumnsRelFrame record) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.selectOne(record);
	}

	@Override
	public List<TemplateColumnsRelFrame> queryAll(TemplateColumnsRelFrame record) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.select(record);
	}

	@Override
	public int queryCount(TemplateColumnsRelFrame record) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.selectCount(record);
	}

	@Override
	public TemplateColumnsRelFrame queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateColumnsRelFrame record) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateColumnsRelFrame record) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateColumnsRelFrame record) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateColumnsRelFrame record) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateColumnsRelFrame record) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateColumnsRelFrame example) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.selectCountByExample(example);
	}

	@Override
	public List<TemplateColumnsRelFrame> queryByExample(
			TemplateColumnsRelFrame example) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.selectByExample(example);
	}

	@Override
	public TemplateColumnsRelFrame querySortNumMaxObj(Integer templateId) {
		// TODO Auto-generated method stub
		return templateColumnsRelFrameMapper.querySortNumMaxObj(templateId);
	}
}
  