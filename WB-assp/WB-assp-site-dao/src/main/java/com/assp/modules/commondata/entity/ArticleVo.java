package com.assp.modules.commondata.entity;   

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 类简述
 * <p>
 *    文章视图类
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月19日 上午11:20:21 
 */
public class ArticleVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer articleId; // '文章分类',
	private String articleTitle; // '文章标题',
	private String articleAuthor; // '文章作者',
	private String articleUrl; // '文章资源路径',
	private Integer articleSize; // '文章大小（单位k）',
	private String articleBrief; // '文章简介',
	private String articleContent; // '文章内容',
	private Integer articleWordNum; // '文章字数',
	private Integer operatorId; // '操作人id',
	private String operatorName; // '操作者姓名',
	private Date createTime; // '创建时间',
	private Date updateTime; // '更新时间',
	private Integer dataStatus; // '数据状态 0 正常 1 删除',
	private Integer articleReview;		// 是否审核 0未审核 1审核
	
	
	private Integer articleTypeId;
	
	private List<Object> articleTypeIds;
	
	

	public Integer getArticleReview() {
		return articleReview;
	}

	public void setArticleReview(Integer articleReview) {
		this.articleReview = articleReview;
	}

	public List<Object> getArticleTypeIds() {
		return articleTypeIds;
	}

	public void setArticleTypeIds(List<Object> articleTypeIds) {
		this.articleTypeIds = articleTypeIds;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public Integer getArticleSize() {
		return articleSize;
	}

	public void setArticleSize(Integer articleSize) {
		this.articleSize = articleSize;
	}

	public String getArticleBrief() {
		return articleBrief;
	}

	public void setArticleBrief(String articleBrief) {
		this.articleBrief = articleBrief;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Integer getArticleWordNum() {
		return articleWordNum;
	}

	public void setArticleWordNum(Integer articleWordNum) {
		this.articleWordNum = articleWordNum;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	
	
}
  