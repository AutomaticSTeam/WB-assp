package com.assp.modules.commondata.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
 * @CreateDate 2016年6月2日 下午2:27:53
 */
@Table(name = "wms_media_rel_type")
public class MediaRelType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer mediaRelTypeId; // '视频关联类型id',
	private Integer mediaTypeId; // '视频类型id',
	private Integer mediaId; // '视频id',
	
	@Transient
	private String mediaTypeName;// '视频类型名称',
	
	private Integer siteId;//站点id，用作查询用

	public String getMediaTypeName() {
		return mediaTypeName;
	}

	public void setMediaTypeName(String mediaTypeName) {
		this.mediaTypeName = mediaTypeName;
	}

	public Integer getMediaRelTypeId() {
		return mediaRelTypeId;
	}

	public void setMediaRelTypeId(Integer mediaRelTypeId) {
		this.mediaRelTypeId = mediaRelTypeId;
	}

	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
}
