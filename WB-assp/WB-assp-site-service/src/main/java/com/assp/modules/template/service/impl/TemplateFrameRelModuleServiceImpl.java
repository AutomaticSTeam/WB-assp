package com.assp.modules.template.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.template.entity.TemplateFrameRelModule;
import com.assp.modules.template.mapper.TemplateFrameRelModuleMapper;
import com.assp.modules.template.service.ITemplateFrameRelModuleService;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (zhangtl@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月27日 上午9:44:46 
 */
@Service
public class TemplateFrameRelModuleServiceImpl implements ITemplateFrameRelModuleService {
	
	@Autowired
	private TemplateFrameRelModuleMapper templateFrameRelModuleMapper;

	@Override
	public TemplateFrameRelModule queryOne(TemplateFrameRelModule record) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.selectOne(record);
	}

	@Override
	public List<TemplateFrameRelModule> queryAll(TemplateFrameRelModule record) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.select(record);
	}

	@Override
	public int queryCount(TemplateFrameRelModule record) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.selectCount(record);
	}

	@Override
	public TemplateFrameRelModule queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateFrameRelModule record) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateFrameRelModule record) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateFrameRelModule record) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateFrameRelModule record) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateFrameRelModule record) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateFrameRelModule example) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.selectCountByExample(example);
	}

	@Override
	public List<TemplateFrameRelModule> queryByExample(
			TemplateFrameRelModule example) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.selectByExample(example);
	}

	@Override
	public TemplateFrameRelModule querySortNumMaxObj(Integer frameId) {
		// TODO Auto-generated method stub
		return templateFrameRelModuleMapper.querySortNumMaxObj(frameId);
	}
}
  