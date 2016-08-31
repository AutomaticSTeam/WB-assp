package com.assp.modules.commondata.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 描述：城市表 实体
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 上午11:28:41
 * @version
 */
@Table(name="wms_cities")
public class City {
	@Id
	private Integer id; //主键id
	
	private String cityid; //城市id
	
	private String city; //城市名称
	
	private String provinceid; //省份id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	
	
}
