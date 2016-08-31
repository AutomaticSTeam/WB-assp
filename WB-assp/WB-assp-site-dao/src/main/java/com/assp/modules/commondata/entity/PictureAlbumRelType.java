package com.assp.modules.commondata.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "wms_picture_album_rel_type")
public class PictureAlbumRelType {
	@Id
    private Integer albumRelTypeId;//主键

    private Integer albumTypeId;//分类id

    private Integer albumId;//图册id

	private Integer siteId;//站点id，用作查询用
    
	/**
	 * @return the albumRelTypeId
	 */
	public Integer getAlbumRelTypeId() {
		return albumRelTypeId;
	}

	/**
	 * @param albumRelTypeId the albumRelTypeId to set
	 */
	public void setAlbumRelTypeId(Integer albumRelTypeId) {
		this.albumRelTypeId = albumRelTypeId;
	}

	/**
	 * @return the albumTypeId
	 */
	public Integer getAlbumTypeId() {
		return albumTypeId;
	}

	/**
	 * @param albumTypeId the albumTypeId to set
	 */
	public void setAlbumTypeId(Integer albumTypeId) {
		this.albumTypeId = albumTypeId;
	}

	/**
	 * @return the albumId
	 */
	public Integer getAlbumId() {
		return albumId;
	}

	/**
	 * @param albumId the albumId to set
	 */
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

}