package com.assp.modules.commondata.entity;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * 描述：省份表 实体
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 上午11:25:22
 * @version
 */
@Table(name="wms_provinces")
public class Province {
	@Id
	private Integer id; //主键id
	
	private String provinceid; //省份id
	
	private String province; //省份名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	
}
