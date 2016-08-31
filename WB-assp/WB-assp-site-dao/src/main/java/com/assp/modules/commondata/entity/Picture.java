package com.assp.modules.commondata.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 图片数据
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月5日 下午5:05:00
 */
@Table(name = "wms_picture")
public class Picture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer pictureId; // '图片id',
	private String pictureName; // '图片名称',
	private String pictureUrl; // '图片url',
	private String briefDesc; // '简要描述',
	private String detailDesc; // '详细描述',
	private Integer pictureAlbumId; // '图册ID',
	private String imgPostfix; // '图片后缀',
	private Integer pictureType; // '图片类型',
	private Integer imgSize; // '图片大小(k)',
	private String bannerFormatWidth; // '图片规格宽度',
	private String bannerFormatHeight; // '图片规格高度',
	private Integer useage; // '图片用途',
	private Integer operatorId; // '操作者ID',
	private String operatorName; // '操作人姓名',
	private Date createTime; // '创建时间',
	private Date updateTime; // '更新时间',
	private Integer dataStatus; // '数据状态 0 正常 1 删除',
	private Integer isReview; // '审核状态 0 未审核 1已审核'
	private Integer pictureOrder;// 排序
	private String hyperlink;// 超链接
	
	@Transient
	private Integer limitNum;
	
	@Transient
	private  List<Integer> pictureIds;
	
	private Integer siteId;//站点id，用作查询用
	
	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getBriefDesc() {
		return briefDesc;
	}

	public void setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public Integer getPictureAlbumId() {
		return pictureAlbumId;
	}

	public void setPictureAlbumId(Integer pictureAlbumId) {
		this.pictureAlbumId = pictureAlbumId;
	}

	public String getImgPostfix() {
		return imgPostfix;
	}

	public void setImgPostfix(String imgPostfix) {
		this.imgPostfix = imgPostfix;
	}

	public Integer getPictureType() {
		return pictureType;
	}

	public void setPictureType(Integer pictureType) {
		this.pictureType = pictureType;
	}

	public Integer getImgSize() {
		return imgSize;
	}

	public void setImgSize(Integer imgSize) {
		this.imgSize = imgSize;
	}

	public String getBannerFormatWidth() {
		return bannerFormatWidth;
	}

	public void setBannerFormatWidth(String bannerFormatWidth) {
		this.bannerFormatWidth = bannerFormatWidth;
	}

	public String getBannerFormatHeight() {
		return bannerFormatHeight;
	}

	public void setBannerFormatHeight(String bannerFormatHeight) {
		this.bannerFormatHeight = bannerFormatHeight;
	}

	public Integer getUseage() {
		return useage;
	}

	public void setUseage(Integer useage) {
		this.useage = useage;
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

	public Integer getPictureOrder() {
		return pictureOrder;
	}

	public void setPictureOrder(Integer pictureOrder) {
		this.pictureOrder = pictureOrder;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}


	public List<Integer> getPictureIds() {
		return pictureIds;
	}

	public void setPictureIds(List<Integer> pictureIds) {
		this.pictureIds = pictureIds;
	}

	public Integer getIsReview() {
		return isReview;
	}

	public void setIsReview(Integer isReview) {
		this.isReview = isReview;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
}
