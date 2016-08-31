package com.assp.modules.sys.service.impl;   

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.common.constant.SystemConstants;
import com.assp.modules.sys.entity.User;
import com.assp.modules.sys.mapper.UserMapper;
import com.assp.modules.sys.service.IUserService;
import com.assp.modules.website.entity.Website;

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
 * @CreateDate 2016年4月20日 下午2:18:54 
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User queryOne(User record) {
		return userMapper.selectOne(record);
	}

	@Override
	public List<User> queryAll(User record) {
		return userMapper.select(record);
	}

	@Override
	public int queryCount(User record) {
		return userMapper.selectCount(record);
	}

	@Override
	public User queryByPrimaryKey(Object key) {
		return userMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(User record) {
		return userMapper.insert(record);
	}

	@Override
	public int addSelective(User record) {
		return userMapper.insertSelective(record);
	}

	@Override
	public int delete(User record) {
		return userMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return userMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(User example) {
		return 0;
	}

	@Override
	public List<User> queryByExample(User example) {
		return null;
	}

	@Override
	public User userLoginSys(User user) {
		String password = new SimpleHash("SHA-256", user.getPassword()).toString();
		user.setPassword(null);
		user.setIsActive(Integer.valueOf(1));
		user.setUserStatus(SystemConstants.USABLE_STATUS);
		user.setDataStatus(SystemConstants.USABLE_STATUS);
		User returnUser = userMapper.selectOne(user);
		if(returnUser != null  && password.endsWith(returnUser.getPassword())){
			return returnUser;
		}
		return null;
	}

	@Override
	public List<Website> getUserWebSite(Integer userId) {
		return userMapper.getUserWebSite(userId);
	}
	
	
	public static void main(String[] args) {
		System.out.println(new SimpleHash("SHA-256", "123456").toString()); 
	}
	
	

	@Override
	public int getUserNum() {
		return userMapper.getUserNum();
	}

	@Override
	public void updateUserNum(Integer userUum) {
		 userMapper.updateUserNum(userUum);
	}

	@Override
	public void insertUserRole(Integer userid) {
		// TODO Auto-generated method stub
		userMapper.insertUserRole(userid);
		
	}

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		
		int i =userMapper.insertUser(user);
		if(i>0){
			i=user.getUserId();
		}
		return i;
	}

}
  