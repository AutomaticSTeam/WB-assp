package com.assp.modules.order.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wms_order_product")
public class OrderProduct {
	@Id
	private Integer orderProductId; // 主键id

	private Integer orderId; // 订单Id

	private Integer productType; // 产品类型

	private Integer tmplId; // 模板id

	private String tmplName; // 模板名称

	private String spaceSize; // 空间大小

	private Integer dkType;// 带宽类型 1：共享 2：独享

	private String dkOperators; // 带宽运营商

	private String dkInfo; // 带宽信息

	private Integer isUse; // 是否启用 0：否 1：是

	private Date checkTime; // 审核时间

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(Integer orderProductId) {
		this.orderProductId = orderProductId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getTmplName() {
		return tmplName;
	}

	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}

	public String getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(String spaceSize) {
		this.spaceSize = spaceSize;
	}

	public Integer getDkType() {
		return dkType;
	}

	public void setDkType(Integer dkType) {
		this.dkType = dkType;
	}

	public String getDkOperators() {
		return dkOperators;
	}

	public void setDkOperators(String dkOperators) {
		this.dkOperators = dkOperators;
	}

	public String getDkInfo() {
		return dkInfo;
	}

	public void setDkInfo(String dkInfo) {
		this.dkInfo = dkInfo;
	}

	public Integer getTmplId() {
		return tmplId;
	}

	public void setTmplId(Integer tmplId) {
		this.tmplId = tmplId;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

}
