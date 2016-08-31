package com.assp.modules.commondata.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.commondata.entity.Article;

/**
 * 类简述
 * <p>
 *      处理文章接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 上午11:18:16 
 */
public interface IArticleService extends BasicService<Article> {
	/**
	 * 
	* @Title: addArticle 
	* @Description: TODO(添加视频返回ID) 
	*@author (wangkang@sxw100.com)
	* @param @param article    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addArticle(Article article);
	
	/**
	 * 
	* @Title: queryByArticleCodition 
	* @Description: TODO(通过条件查询文章) 
	*@author (wangkang@sxw100.com)
	* @param @param article
	* @param @return    设定文件 
	* @return List<Article>    返回类型 
	* @throws
	 */
	public List<Article> queryByArticleCodition(Article article);
	
	/**
	 * 
	* @Title: bathUpdateArticle 
	* @Description: TODO(批量修改文章内容) 
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

}
  