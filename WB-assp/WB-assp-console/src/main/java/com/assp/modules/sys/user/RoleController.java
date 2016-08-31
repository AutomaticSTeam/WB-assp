package com.assp.modules.sys.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.assp.common.utils.DateUtils;
import com.assp.common.web.BaseController;
import com.assp.modules.sys.entity.Permission;
import com.assp.modules.sys.entity.Role;
import com.assp.modules.sys.entity.Ztree;
import com.assp.modules.sys.service.IPermissionService;
import com.assp.modules.sys.service.IRoleService;

/**
 * 
 * 描述：角色controller
 * 创建人： 魏泽超
 * 创建时间: 2016年7月26日 下午3:02:53
 * @version
 */
@Controller
@RequestMapping("/console/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPermissionService perService;

	/**
	 * 
	 * 描述：获取角色列表
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月26日 下午3:19:25
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/roleList")
	public Map<String, Object> roleList(String roleName, String roleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取数据
		List<Role> roleList = null;
		int count = 0;
		Role role = new Role();
		if (StringUtils.isNotBlank(roleName) && null != roleName) {
			role.setRoleName(roleName);
		}
		if (StringUtils.isNotBlank(roleCode) && null != roleCode) {
			role.setRoleCode(roleCode);
		}
		roleList = roleService.queryAll(role);
		count = roleService.queryCount(role);
		// 获取总数

		map.put("total", count);
		map.put("rows", roleList);
		return map;
	}

	/**'
	 * 
	 * 描述：跳转至分配权限页面
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月28日 下午12:05:34
	 * @version
	 */
	@RequestMapping("/toPermissionZtree.do")
	public String toPermissionZtree(ModelMap model, @RequestParam(value = "roleId", required = false) String roleId) {
		model.put("roleId", roleId);
		return "/user/rolePermission";
	}

	@ResponseBody
	@RequestMapping("/rolePermission.do")
	public Map<String, Object> rolePermission(@RequestParam(value = "permissionIds", required = false) String permissionIds, @RequestParam(value = "roleId", required = false) String roleId, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = roleService.addRolePermission(permissionIds.split(","), roleId);
		map.put("flag", flag);
		return map;
	}

	/**
	 * 
	 * 描述：获取权限树
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月28日 上午10:19:59
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/permissionZtree.do")
	public void permissionZtree(HttpServletResponse response, ModelMap model, @RequestParam(value = "roleId", required = false) String roleId) {
		// 查找树形菜单数据
		Permission per = new Permission();
		per.setPermissionStatus(0);
		// per.setPermissionDeleteFlag(0);
		List<Permission> queryAll = perService.queryAll(per);
		// 树形菜单拼装
		List<Ztree> ztreeList = new ArrayList<Ztree>();
		for (Permission permission : queryAll) {
			if (("0").equals(permission.getPermissionPid())) {
				Ztree ztree = new Ztree();
				ztree.setId(permission.getPermissionId().toString());
				ztree.setPid(String.valueOf(0));
				ztree.setName(permission.getPermissionName());
				ztree.setNocheck(true);
				ztree.setOpen(true);
				ztreeList.add(ztree);
			} else {
				Ztree ztree = new Ztree();
				ztree.setId(permission.getPermissionId().toString());
				ztree.setPid(permission.getPermissionPid().toString());
				ztree.setName(permission.getPermissionName());
				ztree.setOpen(true);
				ztreeList.add(ztree);
			}
		}

		// 已经分配的权限
		List<String> rolePermissionList = roleService.getRolePermissionByRoleId(roleId);
		if (null != rolePermissionList && rolePermissionList.size() > 0) {
			for (Ztree permission : ztreeList) {
				for (String string : rolePermissionList) {
					if (permission.getId().equals(string)) {
						permission.setChecked(true);
					}
				}
			}
		}
		// 树形数据
		String json = JSONArray.toJSONString(ztreeList, true).replace("pid", "pId");

		// model.put("ztreeJson", json);

		printJSON(response, json);
		// model.put("roleId", roleId);
		// return "/user/rolePermission";
	}

	/**
	 * 
	 * 描述：添加角色
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:30:30
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/addRole.do")
	public Map<String, Object> addRole(Role role) {
		int status = 0;// 1为成功
		Map<String, Object> map = new HashMap<String, Object>();

		// 查看角色编码是否已经存在

		Role r = new Role();
		r.setRoleCode(role.getRoleCode());
		Role queryOne = roleService.queryOne(r);
		if (null == queryOne) {
			role.setCreateTime(DateUtils.getCurrentDate());
			role.setRoleStatus(0);
			int i = roleService.add(role);
			if (i > 0) {
				status = 1;
				map.put("msg", "添加成功");
			}
		} else {
			map.put("msg", "编码已经存在");
		}

		map.put("status", status);
		return map;
	}

	/**
	 * 
	 * 描述：去更新角色页面
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:30:35
	 * @version
	 */
	@RequestMapping("/toUpdateRole.do")
	public String toUpdateRole(String roleId, ModelMap model) {
		Role role = new Role();
		role.setRoleId(Integer.valueOf(roleId));
		Role queryOne = roleService.queryOne(role);
		if (null != queryOne) {
			model.put("role", queryOne);
		}

		return "/user/updateRole";
	}

	/**
	 * 
	 * 描述：更新角色
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:30:43
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/updateRole.do")
	public Map<String, Object> updateRole(Role role) {
		int status = 0;// 1为成功
		Map<String, Object> map = new HashMap<String, Object>();

		// 查看角色编码是否已经存在

		Role r = new Role();
		r.setRoleCode(role.getRoleCode());
		Role queryOne = roleService.queryOne(r);
		if (null == queryOne) {
			role.setUpdateTime(DateUtils.getCurrentDate());
			int i = roleService.updateByPrimaryKeySelective(role);
			if (i > 0) {
				status = 1;
				map.put("msg", "更新成功");
			}
		} else {
			if (queryOne.getRoleId().equals(role.getRoleId())) {
				role.setUpdateTime(DateUtils.getCurrentDate());
				int i = roleService.updateByPrimaryKeySelective(role);
				if (i > 0) {
					status = 1;
					map.put("msg", "更新成功");
				}
			} else {
				map.put("msg", "编码已经存在");
			}
		}

		map.put("status", status);
		return map;
	}

	/**
	 * 
	 * 描述：删除角色
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:30:52
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/deleteRole.do")
	public Map<String, Object> deleteRole(String[] Ids) {
		int status = 0;// 1为成功
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = roleService.deleteRoleByRoleIds(Ids);
		if (flag) {
			status = 1;
			map.put("msg", "删除成功");
		}
		map.put("status", status);
		return map;
	}
}
