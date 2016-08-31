package com.assp.modules.commondata.mapper;   

import java.util.List;

import com.assp.modules.commondata.entity.ArticleRelType;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 * 文章类型关系(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangkang@sxw100.com)
 * @version 1.0
 * @CreateDate 2016-6-2 下午5:22:24 
 */
public interface ArticleRelTypeMapper extends Mapper<ArticleRelType>{

	 /**
	  * 
	 * @Title: selectArticleRelTypeByCdt 
	 * @Description: 根据条件查找文章类型
	 *@author (shx@sxw100.com)
	 * @param @param articleRelType
	 * @param @return    设定文件 
	 * @return List<ArticleRelType>    返回类型 
	 * @throws
	  */
	public List<ArticleRelType> selectArticleRelTypeByCdt(ArticleRelType articleRelType);

	/**批量删除关联类别以主键ids
	 * @param articleIds
	 */
	public void bathDelByArticleIds(List<Integer> articleIds);
	
}
  