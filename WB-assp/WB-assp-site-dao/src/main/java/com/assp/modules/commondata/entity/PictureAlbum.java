package com.assp.modules.commondata.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

/**
 * 类简述
 * <p>
 * 系统图册
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月5日 下午5:14:31
 */
@Table(name = "wms_picture_album")
public class PictureAlbum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer albumId; //
	private String albumName; // '图册名称',
	private Integer albumStatus; // '图册状态 0 正常 1 删除',
	private String albumDesc; // '图册描述',
	private Integer operatorId; // '操作人id',
	private String operatorName; // '操作人姓名',
	private Date createTime; // '创建时间',
	private Date updateTime;//'修改时间',
	private Integer isTop;//'置顶'
	@Transient
	private Integer albumTypeId; //图册类型ID
	
	@Transient
	private Integer review_is; // 待审核图片数量,
	
	@Transient
	private ArrayList<PictureAlbumType> pictureAlbumTypes; // 关联的分类集合
	
	@Transient
	private List<Picture> pictures; // 关联的分类集合
	
	@Transient
	private Picture picture; // 图册第一张图片,

	@Transient
	private  List<Integer> albumIds;
	
	private Integer siteId;//站点id，用作查询用
	
	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = StringUtils.trimToNull(albumName);
	}

	public Integer getAlbumStatus() {
		return albumStatus;
	}

	public void setAlbumStatus(Integer albumStatus) {
		this.albumStatus = albumStatus;
	}

	public String getAlbumDesc() {
		return albumDesc;
	}

	public void setAlbumDesc(String albumDesc) {
		this.albumDesc = albumDesc;
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

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getAlbumTypeId() {
		return albumTypeId;
	}

	public void setAlbumTypeId(Integer albumTypeId) {
		this.albumTypeId = albumTypeId;
	}

	public Integer getReview_is() {
		return review_is;
	}

	public void setReview_is(Integer review_is) {
		this.review_is = review_is;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	public List<Integer> getAlbumIds() {
		return albumIds;
	}

	public void setAlbumIds(List<Integer> albumIds) {
		this.albumIds = albumIds;
	}
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
	/**
	 * @return the pictureAlbumTypes
	 */
	public ArrayList<PictureAlbumType> getPictureAlbumTypes() {
		return pictureAlbumTypes;
	}

	/**
	 * @param pictureAlbumTypes the pictureAlbumTypes to set
	 */
	public void setPictureAlbumTypes(ArrayList<PictureAlbumType> pictureAlbumTypes) {
		this.pictureAlbumTypes = pictureAlbumTypes;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	
}
