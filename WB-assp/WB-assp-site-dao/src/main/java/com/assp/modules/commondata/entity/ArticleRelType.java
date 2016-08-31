package com.assp.modules.commondata.entity;   

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
 * @CreateDate 2016-6-2 下午5:16:43 
 */
@Table(name = "wms_article_rel_type")
public class ArticleRelType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer articleRelTypeId; // '文章关联类型id'
	private Integer articleId; // 文章id
	private Integer articleTypeId; // 文章类型id
	
	@Transient
	private String articleTypeName; // '分类名称',
	
	
	private Integer siteId;//站点id，用作查询用

	public String getArticleTypeName() {
		return articleTypeName;
	}

	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}

	public Integer getArticleRelTypeId() {
		return articleRelTypeId;
	}

	public void setArticleRelTypeId(Integer articleRelTypeId) {
		this.articleRelTypeId = articleRelTypeId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
}
  