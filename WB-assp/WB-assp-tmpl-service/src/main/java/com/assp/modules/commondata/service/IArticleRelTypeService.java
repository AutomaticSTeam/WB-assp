package com.assp.modules.commondata.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.commondata.entity.ArticleRelType;

/**
 * 类简述
 * <p>
 * 处理文章类型关系接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangkang@sxw100.com)
 * @version 1.0
 * @CreateDate 2016-6-2 下午5:27:33 
 */
public interface IArticleRelTypeService extends BasicService<ArticleRelType>{

	void bathDelByArticleIds(List<Integer> articleIds);

}
  