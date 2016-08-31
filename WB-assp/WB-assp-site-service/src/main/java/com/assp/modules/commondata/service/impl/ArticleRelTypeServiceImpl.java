package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.ArticleRelType;
import com.assp.modules.commondata.mapper.ArticleRelTypeMapper;
import com.assp.modules.commondata.service.IArticleRelTypeService;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangkang@sxw100.com)
 * @version 1.0
 * @CreateDate 2016-6-2 下午5:25:37 
 */
@Service
public class ArticleRelTypeServiceImpl implements IArticleRelTypeService{
	
	@Autowired
	private ArticleRelTypeMapper articleRelTypeMapper;

	@Override
	public ArticleRelType queryOne(ArticleRelType record) {
		return articleRelTypeMapper.selectOne(record);
	}

	@Override
	public List<ArticleRelType> queryAll(ArticleRelType record) {
		return articleRelTypeMapper.select(record);
	}

	@Override
	public int queryCount(ArticleRelType record) {
		return articleRelTypeMapper.selectCount(record);
	}

	@Override
	public ArticleRelType queryByPrimaryKey(Object key) {
		return articleRelTypeMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(ArticleRelType record) {
		return articleRelTypeMapper.insert(record);
	}

	@Override
	public int addSelective(ArticleRelType record) {
		return articleRelTypeMapper.insertSelective(record);
	}

	@Override
	public int delete(ArticleRelType record) {
		return articleRelTypeMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return articleRelTypeMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(ArticleRelType record) {
		return articleRelTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleRelType record) {
		return articleRelTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int queryCountByExample(ArticleRelType example) {
		return articleRelTypeMapper.selectCountByExample(example);
	}

	@Override
	public List<ArticleRelType> queryByExample(ArticleRelType example) {
		return articleRelTypeMapper.selectByExample(example);
	}

	@Override
	public void bathDelByArticleIds(List<Integer> articleIds) {
		// TODO Auto-generated method stub
		articleRelTypeMapper.bathDelByArticleIds(articleIds);
	}

}
  