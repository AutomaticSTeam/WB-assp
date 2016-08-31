package com.assp.modules.sys.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.assp.modules.sys.entity.Permission;
import com.assp.modules.sys.entity.PermissionTree;
import com.assp.modules.sys.entity.Role;
import com.assp.modules.sys.service.IPermissionService;

/**
 * 
 * 描述：权限 controller
 * 创建人： 魏泽超
 * 创建时间: 2016年7月26日 下午3:16:44
 * @version
 */
@Controller
@RequestMapping("/console/permission")
public class PermissionController {

	@Autowired
	private IPermissionService perService;

	/**
	 * 
	 * 描述：获取权限列表
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月26日 下午3:19:05
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/permissionList")
	public String permissinoList() {
		// 获取数据
		List<Permission> permissonList = perService.queryAll(null);

		List<PermissionTree> permissionTreeList = new ArrayList<PermissionTree>();
		// 子节点
		List<PermissionTree> children = new ArrayList<PermissionTree>();

		PermissionTree per = new PermissionTree();
		for (Permission permission : permissonList) {
			if (permission.getPermissionPid() == 0) {
				per.set_parentId(permission.getPermissionPid());
				per.setPermissionCode(permission.getPermissionCode());
				per.setPermissionDeleteFlag(permission.getPermissionDeleteFlag());
				per.setPermissionName(permission.getPermissionName());
				per.setPermissionStatus(permission.getPermissionStatus());
				per.setPermissionId(permission.getPermissionId());
				per.setPermissionPid(permission.getPermissionPid());
			} else {
				if (permission.getPermissionPid() == per.getPermissionId()) {
					PermissionTree perChildren = new PermissionTree();
					perChildren.set_parentId(permission.getPermissionPid());
					perChildren.setPermissionCode(permission.getPermissionCode());
					perChildren.setPermissionDeleteFlag(permission.getPermissionDeleteFlag());
					perChildren.setPermissionName(permission.getPermissionName());
					perChildren.setPermissionStatus(permission.getPermissionStatus());
					perChildren.setPermissionId(permission.getPermissionId());
					perChildren.setPermissionPid(permission.getPermissionPid());
					children.add(perChildren);
				}
			}
		}
		per.setChildren(children);
		permissionTreeList.add(per);

		for (PermissionTree pTree : permissionTreeList.get(0).getChildren()) {
			List<PermissionTree> pChildren = new ArrayList<PermissionTree>();
			for (Permission permission : permissonList) {
				if (pTree.getPermissionId() != 1) {
					if (permission.getPermissionPid() == pTree.getPermissionId()) {
						PermissionTree perChildren = new PermissionTree();
						perChildren.set_parentId(permission.getPermissionPid());
						perChildren.setPermissionCode(permission.getPermissionCode());
						perChildren.setPermissionDeleteFlag(permission.getPermissionDeleteFlag());
						perChildren.setPermissionName(permission.getPermissionName());
						perChildren.setPermissionStatus(permission.getPermissionStatus());
						perChildren.setPermissionId(permission.getPermissionId());
						perChildren.setPermissionPid(permission.getPermissionPid());
						pChildren.add(perChildren);
					}
				}
			}
			pTree.setChildren(pChildren);
			if (null != pChildren && pChildren.size() > 0) {
				addTree(pChildren, permissonList);
			}
		}
		String rows = JSON.toJSONString(permissionTreeList, true);

		return rows;
	}

	// 递归方法
	public void addTree(List<PermissionTree> children, List<Permission> permissonList) {
		for (PermissionTree pTree : children) {
			List<PermissionTree> pChildren = new ArrayList<PermissionTree>();
			for (Permission permission : permissonList) {
				if (pTree.getPermissionId() != 1) {
					if (permission.getPermissionPid() == pTree.getPermissionId()) {
						PermissionTree perChildren = new PermissionTree();
						perChildren.set_parentId(permission.getPermissionPid());
						perChildren.setPermissionCode(permission.getPermissionCode());
						perChildren.setPermissionDeleteFlag(permission.getPermissionDeleteFlag());
						perChildren.setPermissionName(permission.getPermissionName());
						perChildren.setPermissionStatus(permission.getPermissionStatus());
						perChildren.setPermissionId(permission.getPermissionId());
						perChildren.setPermissionPid(permission.getPermissionPid());
						pChildren.add(perChildren);
					}
				}
			}
			pTree.setChildren(pChildren);
			if (null != pChildren && pChildren.size() > 0) {
				addTree(pChildren, permissonList);
			}
		}
	}

	/**
	 * 
	 * 描述：去添加权限页面
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:29:36
	 * @version
	 */
	@RequestMapping("/toAddPermission.do")
	public String tuAddPermission(ModelMap model, String permissionId) {
		model.put("permissionId", permissionId);
		return "/user/addPermission";
	}

	/**
	 * 
	 * 描述：添加权限
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:29:45
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/addPermission.do")
	public Map<String, Object> addPermission(Permission permission) {
		int status = 0; // 1为成功
		Map<String, Object> map = new HashMap<String, Object>();

		// 查看编码是否已经存在
		Permission p = new Permission();
		p.setPermissionCode(permission.getPermissionCode());
		Permission queryOne = perService.queryOne(p);
		if (null == queryOne) {
			permission.setPermissionStatus(0);
			int i = perService.add(permission);
			if (i > 0) {
				status = 1;
				map.put("msg", "添加成功");
			}
		} else {
			map.put("msg", "权限编码已经存在");
		}
		map.put("status", status);
		return map;
	}

	/**
	 * 
	 * 描述：去更新权限页面
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:29:52
	 * @version
	 */
	@RequestMapping("/toUpdatePermission.do")
	public String toUpdatePermission(String permissionId, ModelMap model) {
		Permission permission = new Permission();
		permission.setPermissionId(Integer.valueOf(permissionId));
		Permission queryOne = perService.queryOne(permission);
		if (null != queryOne) {
			model.put("permission", queryOne);
		}
		return "/user/updatePermission";
	}

	
	/**
	 * 
	 * 描述：更新权限
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:30:09
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/updatePermission.do")
	public Map<String, Object> updatePermission(Permission permission) {
		int status = 0; // 1为成功
		Map<String, Object> map = new HashMap<String, Object>();

		// 查看编码是否已经存在
		Permission p = new Permission();
		p.setPermissionCode(permission.getPermissionCode());
		Permission queryOne = perService.queryOne(p);

		if (null == queryOne) {
			int i = perService.updateByPrimaryKeySelective(permission);
			if (i > 0) {
				status = 1;
				map.put("msg", "更新成功");
			}
		} else {
			if (permission.getPermissionId().equals(queryOne.getPermissionId())) {
				int i = perService.updateByPrimaryKeySelective(permission);
				if (i > 0) {
					status = 1;
					map.put("msg", "更新成功");
				}
			} else {
				map.put("msg", "权限编码已经存在");
			}
		}

		map.put("status", status);
		return map;
	}

	
	/**
	 * 
	 * 描述：删除权限
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:30:17
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/deletePermission.do")
	public Map<String, Object> deletePermission(String permissionId) {
		int status = 0; // 1为成功
		//
		Map<String, Object> map = new HashMap<String, Object>();
		Permission permission = new Permission();
		permission.setPermissionPid(Integer.valueOf(permissionId));
		List<Permission> queryAll = perService.queryAll(permission);
		if (null != queryAll && queryAll.size() > 0) {
			map.put("msg", "当前节点存在子节点，不能删除，请先删除子节点!");
		} else {
			Permission p = new Permission();
			p.setPermissionId(Integer.valueOf(permissionId));
			int i = perService.deleteByPrimaryKey(p);
			if (i > 0) {
				status = 1;
				map.put("msg", "删除成功");
			}
		}
		map.put("status", status);
		return map;
	}
}
