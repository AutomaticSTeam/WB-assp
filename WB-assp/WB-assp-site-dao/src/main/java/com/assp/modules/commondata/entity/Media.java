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
 * 视频
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月31日 下午5:40:56
 */
@Table(name = "wms_media")
public class Media implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7232312104157826293L;
	@Id
	private Integer mediaId; // '视频id',
	private String mediaName; // '视频名称',
	private String mediaUrl; // '视频url',
	private String mediaAuthor ; //视频作者
	private Integer mediaSize; // '视频大小单位（K）',
	private String mediaBriefing; // '简介',
	private String mediaSummary; // '视频摘要',
	private String mediaSource; // 视频来源
	private String mediaSuffix; // '文件格式（后缀）',
	private String mediaAttachmentImg;  //视频附图
	private Integer mediaUsage; // '视频用途',
	private Date createTime; // '创建时间',
	private Date updateTime; // 更新时间
	private Integer operatorId; // '操作者id',
	private String operatorName; // '操作者姓名',
	private Integer isReview; //是否审核通过 0 否 1 是
	private Integer isTop; // 是否置顶 0 否 1是
	private Integer dataStatus; // '数据状态 0 正常 1 删除',
	private Integer readNum; // '阅读数量',
	
	@Transient
	private List<MediaRelType> mediaRelTypes; //视频关联类型
	
	@Transient
	private List<Integer> mediaTypes;  //视频分类
	
	@Transient
	private  List<Integer> mediaIds;
	
	@Transient
	private Integer limitNum;
	
	private Integer siteId;//站点id，用作查询用
	
	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public List<Integer> getMediaIds() {
		return mediaIds;
	}

	public void setMediaIds(List<Integer> mediaIds) {
		this.mediaIds = mediaIds;
	}

	public List<Integer> getMediaTypes() {
		return mediaTypes;
	}

	public void setMediaTypes(List<Integer> mediaTypes) {
		this.mediaTypes = mediaTypes;
	}

	public List<MediaRelType> getMediaRelTypes() {
		return mediaRelTypes;
	}

	public void setMediaRelTypes(List<MediaRelType> mediaRelTypes) {
		this.mediaRelTypes = mediaRelTypes;
	}

	public String getMediaAuthor() {
		return mediaAuthor;
	}

	public void setMediaAuthor(String mediaAuthor) {
		this.mediaAuthor = mediaAuthor;
	}

	public String getMediaAttachmentImg() {
		return mediaAttachmentImg;
	}

	public void setMediaAttachmentImg(String mediaAttachmentImg) {
		this.mediaAttachmentImg = StringUtils.trimToNull(mediaAttachmentImg);
	}


	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = StringUtils.trimToNull(mediaName);
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public Integer getMediaSize() {
		return mediaSize;
	}

	public void setMediaSize(Integer mediaSize) {
		this.mediaSize = mediaSize;
	}

	public String getMediaBriefing() {
		return mediaBriefing;
	}

	public void setMediaBriefing(String mediaBriefing) {
		this.mediaBriefing = mediaBriefing;
	}

	public String getMediaSummary() {
		return mediaSummary;
	}

	public void setMediaSummary(String mediaSummary) {
		this.mediaSummary = mediaSummary;
	}

	public String getMediaSource() {
		return mediaSource;
	}

	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}

	public String getMediaSuffix() {
		return mediaSuffix;
	}

	public void setMediaSuffix(String mediaSuffix) {
		this.mediaSuffix = mediaSuffix;
	}

	public Integer getMediaUsage() {
		return mediaUsage;
	}

	public void setMediaUsage(Integer mediaUsage) {
		this.mediaUsage = mediaUsage;
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

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getIsReview() {
		return isReview;
	}

	public void setIsReview(Integer isReview) {
		this.isReview = isReview;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}
	
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

}
