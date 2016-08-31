package com.assp.modules.website.service.impl;   

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.common.SyncServerResourcesUtil;
import com.assp.modules.sys.entity.User;
import com.assp.modules.template.entity.Template;
import com.assp.modules.website.entity.UserRelWebsite;
import com.assp.modules.website.entity.Website;
import com.assp.modules.website.entity.WebsiteRelTemplate;
import com.assp.modules.website.mapper.UserRelWebsiteMapper;
import com.assp.modules.website.mapper.WebsiteMapper;
import com.assp.modules.website.mapper.WebsiteRelTemplateMapper;
import com.assp.modules.website.service.IWebsiteService;

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
 * @CreateDate 2016年7月22日 上午11:21:30 
 */
@Service
public class WebsiteServiceImpl  implements IWebsiteService{

	@Autowired
	private WebsiteMapper websiteMapper;
	
	@Autowired
	private UserRelWebsiteMapper userRelWebsiteMapper;
	
	@Autowired
	private WebsiteRelTemplateMapper websiteRelTemplateMapper;
	
	@Override
	public Website queryOne(Website record) {
		return websiteMapper.selectOne(record);
	}

	@Override
	public List<Website> queryAll(Website record) {
		return websiteMapper.select(record);
	}

	@Override
	public int queryCount(Website record) {
		return websiteMapper.selectCount(record);
	}

	@Override
	public Website queryByPrimaryKey(Object key) {
		return websiteMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Website record) {
		return websiteMapper.insert(record);
	}

	@Override
	public int addSelective(Website record) {
		return websiteMapper.insertSelective(record);
	}

	@Override
	public int delete(Website record) {
		return websiteMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return websiteMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Website record) {
		return websiteMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Website record) {
		return websiteMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Website example) {
		return websiteMapper.selectCountByExample(example);
	}

	@Override
	public List<Website> queryByExample(Website example) {
		return null;
	}

	@Override
	public void addWebsite(Website website) {
		websiteMapper.insertWebsite(website);
	}

	@Override
	public Map<String, Object> templateToWebsite(Template template, User user) throws NumberFormatException, Exception {
		Map<String, Object>  map = new HashMap<String, Object>();
		 // 生成站点实例
		   Website  site = new Website();
		   if(StringUtils.isNotBlank(template.getTemplateName())){site.setSiteName(template.getTemplateName());}
		   site.setSiteIp("10.1.1.127");
		   site.setSitePort("8080"); // 默认8080
		   site.setSiteStatus(Integer.valueOf(0));
		   websiteMapper.insertWebsite(site);
		   
		   String siteCode =  String.valueOf(10000+site.getSiteId().intValue());
		   
		   //更新站点siteCode
			  Website  upSite = new Website();
			  upSite.setSiteCode(siteCode);
			  upSite.setSiteDomain("www."+siteCode+".wms.com");
			  upSite.setSiteId(site.getSiteId());
			  websiteMapper.updateByPrimaryKeySelective(upSite);
		   
		   //模板数据迁移到站点
		   Website  siteRecord = new Website();
		   siteRecord.setSiteId(site.getSiteId());
		   siteRecord.setTemplateId(template.getTemplateId());
		   siteRecord.setTemplateCode(template.getTemplateCode());
		   siteRecord.setSiteCode(siteCode);
		   siteRecord.setTmplIpAddress(SyncServerResourcesUtil.getIpAddress("tmplIpAddress"));
		   siteRecord.setSiteIpAddress(SyncServerResourcesUtil.getIpAddress("siteIpAddress"));
		  Integer  siteTemplateId =  websiteMapper.executeSync(siteRecord);
		  
		  //释放乐观锁
		  websiteMapper.batchSyncSQL();
		  
		  int syncRst = SyncServerResourcesUtil.syncTmplResorcesToSite(template.getTemplateCode(), siteCode);
		  //设置目录创建失败的重复执行此时
		  int exeConunt=2;
		  //创建失败则再次尝试创建
		  while(syncRst==1&&exeConunt>0){
			  syncRst = SyncServerResourcesUtil.syncTmplResorcesToSite(template.getTemplateCode(), siteCode);
			  exeConunt--;
		  }
		 
		  
		//用户关联站点
		   UserRelWebsite userRelWebsite  = new UserRelWebsite() ;
		   userRelWebsite.setSiteId(site.getSiteId());
		   userRelWebsite.setUserId(user.getUserId());
		   userRelWebsite.setCreateTime(new Date());
		   userRelWebsite.setRelStatus(Integer.valueOf(0));
		   userRelWebsiteMapper.insertSelective(userRelWebsite );
		   
		   //站点关联模板
		   WebsiteRelTemplate wrt = new WebsiteRelTemplate();
		   wrt.setTemplateId(siteTemplateId);
		   wrt.setSiteId(site.getSiteId());
		   websiteRelTemplateMapper.insertSelective(wrt);
		   
		   map.put("syncRst", syncRst);
		   map.put("siteId", site.getSiteId());
		   map.put("siteTemplateId", siteTemplateId);
		return map;
	}

}
  