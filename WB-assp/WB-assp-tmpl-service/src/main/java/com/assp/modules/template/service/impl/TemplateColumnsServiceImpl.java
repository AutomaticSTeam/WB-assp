package com.assp.modules.template.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.template.entity.TemplateColumns;
import com.assp.modules.template.mapper.TemplateColumnsMapper;
import com.assp.modules.template.service.ITemplateColumnsService;

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
public class TemplateColumnsServiceImpl implements ITemplateColumnsService {
	
	@Autowired
	private TemplateColumnsMapper templateColumnsMapper;

	@Override
	public TemplateColumns queryOne(TemplateColumns record) {
		return templateColumnsMapper.selectOne(record);
	}

	@Override
	public List<TemplateColumns> queryAll(TemplateColumns record) {
		return templateColumnsMapper.select(record);
	}

	@Override
	public int queryCount(TemplateColumns record) {
		return templateColumnsMapper.selectCount(record);
	}

	@Override
	public TemplateColumns queryByPrimaryKey(Object key) {
		return templateColumnsMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateColumns record) {
		return templateColumnsMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateColumns record) {
		return templateColumnsMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateColumns record) {
		return templateColumnsMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return templateColumnsMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateColumns record) {
		return templateColumnsMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateColumns record) {
		return templateColumnsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateColumns example) {
		return templateColumnsMapper.selectCountByExample(example);
	}

	@Override
	public List<TemplateColumns> queryByExample(TemplateColumns example) {
		return null;
	}

	@Override
	public List<TemplateColumns> queryTemplateColumnsByTemplateId(
			Integer templateId) {
		return templateColumnsMapper.selectTemplateColumnsByTemplateId(templateId);
	}

	@Override
	public List<TemplateColumns> queryTemplateColumnsByTemplateFooterId(
			Integer templateFooterId) {
		return templateColumnsMapper.selectTemplateColumnsByTemplateFooterId(templateFooterId);
	}

	@Override
	public int addGetprimaryKey(TemplateColumns templateColumns) {
		// TODO Auto-generated method stub
		return templateColumnsMapper.addGetprimaryKey(templateColumns);
	}

	@Override
	public List<TemplateColumns> selectTemplateColumnsInFooterByTemplateFooterId(
			Integer dataColumnId) {
		// TODO Auto-generated method stub
		return templateColumnsMapper.selectTemplateColumnsInFooterByTemplateFooterId(dataColumnId);
	}

	@Override
	public int insertList(List<TemplateColumns> list) {
		return templateColumnsMapper.insertList(list);
	}

}
  