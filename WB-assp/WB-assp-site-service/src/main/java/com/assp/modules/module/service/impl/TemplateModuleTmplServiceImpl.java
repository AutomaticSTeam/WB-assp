package com.assp.modules.module.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.module.entity.TemplateModuleTmpl;
import com.assp.modules.module.mapper.TemplateModuleTmplMapper;
import com.assp.modules.module.service.ITemplateModuleTmplService;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

@Service
public class TemplateModuleTmplServiceImpl implements ITemplateModuleTmplService {

	@Autowired
	private  TemplateModuleTmplMapper templateModuleTmplMapper;
	
	@Override
	public TemplateModuleTmpl queryOne(TemplateModuleTmpl record) {
		return templateModuleTmplMapper.selectOne(record);
	}

	@Override
	public List<TemplateModuleTmpl> queryAll(TemplateModuleTmpl record) {
		return templateModuleTmplMapper.select(record);
	}

	@Override
	public int queryCount(TemplateModuleTmpl record) {
		return templateModuleTmplMapper.selectCount(record);
	}

	@Override
	public TemplateModuleTmpl queryByPrimaryKey(Object key) {
		return templateModuleTmplMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateModuleTmpl record) {
		return templateModuleTmplMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateModuleTmpl record) {
		return templateModuleTmplMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateModuleTmpl record) {
		return templateModuleTmplMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return templateModuleTmplMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateModuleTmpl record) {
		return templateModuleTmplMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateModuleTmpl record) {
		return templateModuleTmplMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateModuleTmpl moduleTmpl) {
		Example moduleTmplExample = new Example(TemplateModuleTmpl.class);
		Criteria criteria =  moduleTmplExample.createCriteria();
		if(moduleTmpl.getModuleTmlName() != null){
			criteria.andLike("moduleTmlName", "%"+moduleTmpl.getModuleTmlName()+"%"); 
		}
		criteria.andEqualTo("dataStatus", Integer.valueOf(0)); 
		moduleTmplExample.setOrderByClause("updateTime DESC");
		return templateModuleTmplMapper.selectCountByExample(moduleTmplExample);
	}

	@Override
	public List<TemplateModuleTmpl> queryByExample(TemplateModuleTmpl moduleTmpl) {
		Example moduleTmplExample = new Example(TemplateModuleTmpl.class);
		Criteria criteria =  moduleTmplExample.createCriteria();
		if(moduleTmpl.getModuleTmlName() != null){
			criteria.andLike("moduleTmlName", "%"+moduleTmpl.getModuleTmlName()+"%"); 
		}
		criteria.andEqualTo("dataStatus", Integer.valueOf(0)); 
		moduleTmplExample.setOrderByClause("updateTime DESC");
		return templateModuleTmplMapper.selectByExample(moduleTmplExample);
	}

	@Override
	public List<TemplateModuleTmpl> vagueQueryByExample(Example moduleTmplExample) {
		return templateModuleTmplMapper.selectByExample(moduleTmplExample);
	}

}
