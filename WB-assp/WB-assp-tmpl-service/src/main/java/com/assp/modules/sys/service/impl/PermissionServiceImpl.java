package com.assp.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.sys.entity.Permission;
import com.assp.modules.sys.mapper.PermissionMapper;
import com.assp.modules.sys.service.IPermissionService;

/**
 * 
 * 描述：权限service实现
 * 创建人： 魏泽超
 * 创建时间: 2016年7月26日 下午4:01:30
 * @version
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public Permission queryOne(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.selectOne(record);
	}

	@Override
	public List<Permission> queryAll(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.select(record);
	}

	@Override
	public int queryCount(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.selectCount(record);
	}

	@Override
	public Permission queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return permissionMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.insert(record);
	}

	@Override
	public int addSelective(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.insertSelective(record);
	}

	@Override
	public int delete(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return permissionMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Permission record) {
		// TODO Auto-generated method stub
		return permissionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Permission example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Permission> queryByExample(Permission example) {
		// TODO Auto-generated method stub
		return null;
	}

}
