package com.assp.modules.order.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 描述：产品表
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 下午3:27:22
 * @version
 */
@Table(name="wms_product")
public class Product {

	@Id
	private Integer productId; //主键id
	
	private String productName; //产品名称
	
	private Integer productType; //用于区分订单内的产品
	
	private Integer isDelete; // 是否删除  0：否  1：是
	
	private Integer isUse; //是否启用 0：否 1：是
	
	private Date createTime; //创建时间
	
	private String productInitValue; //产品初始值


	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProductInitValue() {
		return productInitValue;
	}

	public void setProductInitValue(String productInitValue) {
		this.productInitValue = productInitValue;
	}
	
	
}
