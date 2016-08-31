package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.service.IUserRelTemplateService;
import com.assp.modules.template.entity.UserRelTemplate;
import com.assp.modules.template.mapper.UserRelTemplateMapper;

/**
 * 类简述
 * <p>
 *      颜色业务处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangzhipeng@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年6月27日
 */
@Service
public class UserRelTemplateServiceImpl implements IUserRelTemplateService {

	@Autowired
	private UserRelTemplateMapper  userRelTemplateMapper;

	@Override
	public UserRelTemplate queryOne(UserRelTemplate record) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.selectOne(record);
	}

	@Override
	public List<UserRelTemplate> queryAll(UserRelTemplate record) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.select(record);
	}

	@Override
	public int queryCount(UserRelTemplate record) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.selectCount(record);
	}

	@Override
	public UserRelTemplate queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(UserRelTemplate record) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.insert(record);
	}

	@Override
	public int addSelective(UserRelTemplate record) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.insertSelective(record);
	}

	@Override
	public int delete(UserRelTemplate record) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(UserRelTemplate record) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(UserRelTemplate record) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(UserRelTemplate example) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.selectCountByExample(example);
	}

	@Override
	public List<UserRelTemplate> queryByExample(UserRelTemplate example) {
		// TODO Auto-generated method stub
		return userRelTemplateMapper.selectByExample(example);
	}


	

}
  