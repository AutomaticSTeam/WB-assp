package com.assp.modules.website.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (panlinlin@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年6月17日 下午7:22:12
 */
/**
 * @author Administrator
 *
 */
@Table(name="wms_website")
public class Website implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    private Integer siteId; 

    private String siteName;
    
    private String siteDomain;

    private String sitePort;

    private String siteIp;

    private Date createTime;
    
    private String siteCode;
    
    private Date updateTime;

    private Date deadTime;

    private Integer siteStatus;

    @Transient
    private Integer templateId; 
    @Transient
    private String   tmplIpAddress ; //模板资源路径IP
    @Transient
    private String   siteIpAddress ; //站点资源路径IP
    @Transient
    private String   templateCode ; //模板code
    
    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteDomain() {
        return siteDomain;
    }

    public void setSiteDomain(String siteDomain) {
        this.siteDomain = siteDomain == null ? null : siteDomain.trim();
    }

    public String getSitePort() {
        return sitePort;
    }

    public void setSitePort(String sitePort) {
        this.sitePort = sitePort == null ? null : sitePort.trim();
    }

    public String getSiteIp() {
        return siteIp;
    }

    public void setSiteIp(String siteIp) {
        this.siteIp = siteIp == null ? null : siteIp.trim();
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

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }


	public Integer getSiteStatus() {
		return siteStatus;
	}

	public void setSiteStatus(Integer siteStatus) {
		this.siteStatus = siteStatus;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getTmplIpAddress() {
		return tmplIpAddress;
	}

	public void setTmplIpAddress(String tmplIpAddress) {
		this.tmplIpAddress = tmplIpAddress;
	}

	public String getSiteIpAddress() {
		return siteIpAddress;
	}

	public void setSiteIpAddress(String siteIpAddress) {
		this.siteIpAddress = siteIpAddress;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	
	
}