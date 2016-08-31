package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.ArticleType;
import com.assp.modules.commondata.mapper.ArticleTypeMapper;
import com.assp.modules.commondata.service.IArticleTypeService;

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
 * @CreateDate 2016年5月6日 上午11:34:45 
 */
@Service
public class ArticleTypeServiceImpl implements IArticleTypeService {
	
	@Autowired
	private ArticleTypeMapper articleTypeMapper;

	@Override
	public ArticleType queryOne(ArticleType record) {
		return articleTypeMapper.selectOne(record);
	}

	@Override
	public List<ArticleType> queryAll(ArticleType record) {
		return articleTypeMapper.select(record);
	}

	@Override
	public int queryCount(ArticleType record) {
		return articleTypeMapper.selectCount(record);
	}

	@Override
	public ArticleType queryByPrimaryKey(Object key) {
		return articleTypeMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(ArticleType record) {
		return articleTypeMapper.insert(record);
	}

	@Override
	public int addSelective(ArticleType record) {
		return articleTypeMapper.insertSelective(record);
	}

	@Override
	public int delete(ArticleType record) {
		return articleTypeMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return articleTypeMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(ArticleType record) {
		return articleTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleType record) {
		return articleTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(ArticleType example) {
		return articleTypeMapper.selectCountByExample(example);
	}

	@Override
	public List<ArticleType> queryByExample(ArticleType example) {
		return null;
	}

}
  