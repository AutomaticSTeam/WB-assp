package com.assp.modules.commondata.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 描述：区域表 实体
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 上午11:29:20
 * @version
 */
@Table(name="wms_areas")
public class Area {
	@Id
	private Integer id; //主键id
	
	private String areaid; //区域id
	
	private String area; //区域名称
	
	private String cityid; //城市id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	
	
}
