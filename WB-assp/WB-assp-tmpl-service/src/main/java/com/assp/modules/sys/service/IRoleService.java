package com.assp.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.assp.common.service.BasicService;
import com.assp.modules.sys.entity.Role;

/**
 * 
 * 描述：角色service
 * 创建人： 魏泽超
 * 创建时间: 2016年7月26日 下午3:10:46
 * @version
 */
public interface IRoleService extends BasicService<Role> {

	public boolean addRolePermission(String [] permissionIds,String roleId);

	public int selectRolePermissionByRoleIdAndPermissionId(
			Map<String, Object> map);
	
	 public List<String> getRolePermissionByRoleId(String roleId);
	 
	 public boolean deleteRolePermissionByRoleId(String roleId);
	 
	 /**
	  * 
	  * 描述：批量删除
	  * 创建人： 魏泽超
	  * 创建时间: 2016年7月30日 下午3:38:27
	  * @version
	  */
	 public boolean deleteRoleByRoleIds(String[] ids);

}
