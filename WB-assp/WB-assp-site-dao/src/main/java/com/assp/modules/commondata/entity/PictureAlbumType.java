package com.assp.modules.commondata.entity;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "wms_picture_album_type")
public class PictureAlbumType {
	@Id
    private Integer albumTypeId;

    private Integer albumTypePid;

    private String albumTypeName;

    private int albumStatus;
    
	private Integer siteId;//站点id，用作查询用
    
    @Transient
    private List<PictureAlbum> PictureAlbums;
    
    public Integer getAlbumTypeId() {
        return albumTypeId;
    }

    public void setAlbumTypeId(Integer albumTypeId) {
        this.albumTypeId = albumTypeId;
    }

    public Integer getAlbumTypePid() {
        return albumTypePid;
    }

    public void setAlbumTypePid(Integer albumTypePid) {
        this.albumTypePid = albumTypePid;
    }

    public String getAlbumTypeName() {
        return albumTypeName;
    }

    public void setAlbumTypeName(String albumTypeName) {
        this.albumTypeName = albumTypeName == null ? null : albumTypeName.trim();
    }

    public int getAlbumStatus() {
        return albumStatus;
    }

    public void setAlbumStatus(int albumStatus) {
        this.albumStatus = albumStatus;
    }

	public List<PictureAlbum> getPictureAlbums() {
		return PictureAlbums;
	}

	public void setPictureAlbums(List<PictureAlbum> pictureAlbums) {
		PictureAlbums = pictureAlbums;
	}
	
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
}