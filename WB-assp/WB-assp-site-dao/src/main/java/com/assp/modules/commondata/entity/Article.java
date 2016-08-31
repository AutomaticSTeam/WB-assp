package com.assp.modules.commondata.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

/**
 * 类简述
 * <p>
 * 文章类
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月5日 下午6:22:57
 */
@Table(name = "wms_article")
public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer articleId;			 // '文章分类',
	private String articleTitle; 		// '文章标题',
	private String articleAuthor; 		// '文章作者',
	private String articleUrl; 			// '文章资源路径',
	private Integer articleSize; 		// '文章大小（单位k）',
	private String articleBrief; 		// '文章摘要',
	private String articleNtro;			// '文章简介'
	private String articleContent; 		// '文章内容',
	private String articleAttachmentImg;//'文章附图',
	private Integer articleWordNum; 	// '文章字数',
	private Integer operatorId; 		// '操作人id',
	private String operatorName; 		// '操作者姓名',
	private Date createTime;			// '创建时间',
	private Date updateTime; 			// '更新时间',
	private Integer dataStatus; 		// '数据状态 0 正常 1 删除',
	private Integer articleTop;			// 是否置顶，0否，1置顶
	private String articleSource;		// 文章来源
	private Integer articleReview;		// 是否审核 0未审核 1审核

	@Transient
	private Integer articleTypeId;
	@Transient
	private String  articleTypeName;
	@Transient
	private List<ArticleRelType> articleRelTypes; //文章类型关系
	@Transient
	private List<Integer> articleTypes; //文章类型
	@Transient
	private List<Integer> articleIds;
	
	@Transient
	private Integer reviewSort ; // 默认升序
	
	@Transient
	private Integer limitNum;
	
	
	private Integer siteId;//站点id，用作查询用

	public Integer getReviewSort() {
		return reviewSort;
	}

	public void setReviewSort(Integer reviewSort) {
		this.reviewSort = reviewSort;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getArticleTypeName() {
		return articleTypeName;
	}

	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
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
		this.articleTitle =StringUtils.trimToNull(articleTitle);
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
	public String getArticleNtro() {
		return articleNtro;
	}

	public void setArticleNtro(String articleNtro) {
		this.articleNtro = articleNtro;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleAttachmentImg() {
		return articleAttachmentImg;
	}

	public void setArticleAttachmentImg(String articleAttachmentImg) {
		this.articleAttachmentImg = StringUtils.trimToNull(articleAttachmentImg);;
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
	
	public Integer getArticleTop() {
		return articleTop;
	}

	public void setArticleTop(Integer articleTop) {
		this.articleTop = articleTop;
	}

	public String getArticleSource() {
		return articleSource;
	}

	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}

	public Integer getArticleReview() {
		return articleReview;
	}

	public void setArticleReview(Integer articleReview) {
		this.articleReview = articleReview;
	}

	public List<ArticleRelType> getArticleRelTypes() {
		return articleRelTypes;
	}

	public void setArticleRelTypes(List<ArticleRelType> articleRelTypes) {
		this.articleRelTypes = articleRelTypes;
	}

	public List<Integer> getArticleTypes() {
		return articleTypes;
	}

	public void setArticleTypes(List<Integer> articleTypes) {
		this.articleTypes = articleTypes;
	}

	public List<Integer> getArticleIds() {
		return articleIds;
	}

	public void setArticleIds(List<Integer> articleIds) {
		this.articleIds = articleIds;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
}
