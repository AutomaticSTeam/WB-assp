package com.assp.modules.commondata.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

/**
 * 类简述
 * <p>
 * 视频类型
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月31日 下午5:50:31
 */
@Table(name = "wms_media_type")
public class MediaType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer mediaTypeId;// '视频类型',
	private Integer mediaTypePid;// '视频父类型',
	private String mediaTypeName;// '视频类型名称',
	private Integer dataStatus;// '数据状态 0 正常 1 删除',

	private Integer siteId;//站点id，用作查询用
	
	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public Integer getMediaTypePid() {
		return mediaTypePid;
	}

	public void setMediaTypePid(Integer mediaTypePid) {
		this.mediaTypePid = mediaTypePid;
	}

	public String getMediaTypeName() {
		return mediaTypeName;
	}

	public void setMediaTypeName(String mediaTypeName) {
		this.mediaTypeName = StringUtils.trimToNull(mediaTypeName);
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
}
