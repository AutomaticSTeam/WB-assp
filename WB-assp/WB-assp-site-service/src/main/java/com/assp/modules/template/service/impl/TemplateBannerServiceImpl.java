package com.assp.modules.template.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.template.entity.TemplateBanner;
import com.assp.modules.template.mapper.TemplateBannerMapper;
import com.assp.modules.template.service.ITemplateBannerService;

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
 * @CreateDate 2016年4月27日 上午9:54:40 
 */
@Service
public class TemplateBannerServiceImpl implements ITemplateBannerService {

	@Autowired 
	private TemplateBannerMapper templateBannerMapper;
	
	@Override
	public TemplateBanner queryOne(TemplateBanner record) {
		return templateBannerMapper.selectOne(record);
	}

	@Override
	public List<TemplateBanner> queryAll(TemplateBanner record) {
		return templateBannerMapper.select(record);
	}

	@Override
	public int queryCount(TemplateBanner record) {
		return templateBannerMapper.selectCount(record);
	}

	@Override
	public TemplateBanner queryByPrimaryKey(Object key) {
		return templateBannerMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateBanner record) {
		return templateBannerMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateBanner record) {
		return templateBannerMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateBanner record) {
		return templateBannerMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return templateBannerMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateBanner record) {
		return templateBannerMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateBanner record) {
		return templateBannerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateBanner example) {
		return templateBannerMapper.selectCountByExample(example);
	}

	@Override
	public List<TemplateBanner> queryByExample(TemplateBanner example) {
		return null;
	}

}
  