package com.assp.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.sys.entity.UserFeedback;
import com.assp.modules.sys.mapper.UserFeedbackMapper;
import com.assp.modules.sys.service.IUserFeedbackService;

/**
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * 
  * @Company 动力威视
  * @Copyright
  * @author (wangkang@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年7月1日 上午11:17:11 
  */
@Service
public class UserFeedbackServiceImpl implements IUserFeedbackService{

	@Autowired
	private UserFeedbackMapper userFeedbackMapper;
	@Override
	public UserFeedback queryOne(UserFeedback record) {
		return userFeedbackMapper.selectOne(record);
	}

	@Override
	public List<UserFeedback> queryAll(UserFeedback record) {
		return userFeedbackMapper.select(record);
	}

	@Override
	public int queryCount(UserFeedback record) {
		return userFeedbackMapper.selectCount(record);
	}

	@Override
	public UserFeedback queryByPrimaryKey(Object key) {
		return userFeedbackMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(UserFeedback record) {
		return userFeedbackMapper.insert(record);
	}

	@Override
	public int addSelective(UserFeedback record) {
		return userFeedbackMapper.insertSelective(record);
	}

	@Override
	public int delete(UserFeedback record) {
		return userFeedbackMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return userFeedbackMapper.deleteByExample(key);
	}

	@Override
	public int updateByPrimaryKey(UserFeedback record) {
		return userFeedbackMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(UserFeedback record) {
		return userFeedbackMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(UserFeedback example) {
		return 0;
	}

	@Override
	public List<UserFeedback> queryByExample(UserFeedback example) {
		return null;
	}

}
  