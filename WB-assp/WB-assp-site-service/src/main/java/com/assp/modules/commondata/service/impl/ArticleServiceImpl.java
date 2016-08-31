package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.Article;
import com.assp.modules.commondata.entity.ArticleRelType;
import com.assp.modules.commondata.mapper.ArticleMapper;
import com.assp.modules.commondata.mapper.ArticleRelTypeMapper;
import com.assp.modules.commondata.service.IArticleService;

/**
 * 类简述
 * <p>
 *      文章业务处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 上午11:20:08 
 */
@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleMapper  articleMapper;
	@Autowired
	private ArticleRelTypeMapper articleRelTypeMapper;
	
	@Override
	public Article queryOne(Article record) {
		return articleMapper.selectOne(record);
	}

	@Override
	public List<Article> queryAll(Article record) {
		return articleMapper.select(record);
	}

	@Override
	public int queryCount(Article record) {
		return articleMapper.selectCount(record);
	}

	@Override
	public Article queryByPrimaryKey(Object key) {
		return articleMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Article record) {
		return articleMapper.insert(record);
	}

	@Override
	public int addSelective(Article record) {
		return articleMapper.insertSelective(record);
	}

	@Override
	public int delete(Article record) {
		return articleMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return articleMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Article record) {
		return articleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Article record) {
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Article example) {
		return articleMapper.selectCountByExample(example);
	}

	@Override
	public List<Article> queryByExample(Article example) {
		return null;
	}

	@Override
	public void addArticle(Article article) {
		articleMapper.insertArticle(article);
	}

	@Override
	public List<Article> queryByArticleCodition(Article article) {
		List<Article> articles = articleMapper.selectArticlesByCondition(article);
		for (Article article2 : articles) {
			ArticleRelType articleRelType = new ArticleRelType();
			articleRelType.setArticleId(article2.getArticleId());
			List<ArticleRelType> arts = articleRelTypeMapper.selectArticleRelTypeByCdt(articleRelType);
			article2.setArticleRelTypes(arts);
		}
		return articles;
	}

	@Override
	public void bathUpdateArticle(Article article) {
		articleMapper.bathUpdateArticle(article);
	}

	@Override
	public List<Article> queryAllArticleVyTypeId(Integer articleRelTypeId) {
		return articleMapper.queryAllArticleVyTypeId(articleRelTypeId);
	}

	

}
  