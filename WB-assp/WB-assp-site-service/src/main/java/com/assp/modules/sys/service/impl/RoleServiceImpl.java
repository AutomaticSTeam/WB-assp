package com.assp.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.sys.entity.Role;
import com.assp.modules.sys.mapper.RoleMapper;
import com.assp.modules.sys.service.IRoleService;


/**
 * 
 * 描述：角色service实现
 * 创建人： 魏泽超
 * 创建时间: 2016年7月26日 下午4:01:44
 * @version
 */
@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role queryOne(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.selectOne(record);
	}

	@Override
	public List<Role> queryAll(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.select(record);
	}

	@Override
	public int queryCount(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.selectCount(record);
	}

	@Override
	public Role queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insert(record);
	}

	@Override
	public int addSelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insertSelective(record);
	}

	@Override
	public int delete(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Role example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Role> queryByExample(Role example) {
		// TODO Auto-generated method stub
		return null;
	}

}
