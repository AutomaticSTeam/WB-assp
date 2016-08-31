package com.assp.modules.commondata.mapper;   

import java.util.List;

import com.assp.modules.commondata.entity.Article;
import com.assp.modules.commondata.entity.ArticleVo;
import com.github.abel533.mapper.Mapper;

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
 * @CreateDate 2016年5月6日 上午11:05:06 
 */
public interface ArticleMapper extends Mapper<Article> {

	/**
	 * 
	* @Title: selectArtlclesByVo 
	* @Description: 通过视图条件查找文章 
	*@author (shx@sxw100.com)
	* @param @param articleVo
	* @param @return    设定文件 
	* @return List<Article>    返回类型 
	* @throws
	 */
	
	public List<Article> selectArtlclesByVo(ArticleVo articleVo);
	
	/**
	 * 
	* @Title: insertArticle 
	* @Description: TODO(添加文章返回ID) 
	*@author (wangkang@sxw100.com)
	* @param @param article    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void insertArticle(Article article);
	
	/**
	 * 
	* @Title: selectArticlesByCondition 
	* @Description: TODO(通过条件查找文章) 
	*@author (wangkang@sxw100.com)
	* @param @param article
	* @param @return    设定文件 
	* @return List<Article>    返回类型 
	* @throws
	 */
	public List<Article> selectArticlesByCondition(Article article);
	
	/**
	 * 
	* @Title: bathUpdateArticle 
	* @Description: TODO(批量修改文章) 
	*@author (wangkang@sxw100.com)
	* @param @param article    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void bathUpdateArticle(Article article);

	/**
	 * 
	* @Title: queryAllArticleVyTypeId 
	* @Description: TODO(根据类型查询文章) 
	* @author (panlinlin@sxw100.com)
	* @param @param articleRelTypeId
	* @param @return    设定文件 
	* @return Article    返回类型 
	* @throws
	 */
	public List<Article> queryAllArticleVyTypeId(Integer articleRelTypeId);

	public List<Article> selectArticlesByTypeId(Integer articleTypeId);
}
  