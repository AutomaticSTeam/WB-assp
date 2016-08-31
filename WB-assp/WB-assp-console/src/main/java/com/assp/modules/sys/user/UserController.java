package com.assp.modules.sys.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.assp.common.web.BaseController;
import com.assp.modules.sys.entity.Role;
import com.assp.modules.sys.entity.User;
import com.assp.modules.sys.entity.Ztree;
import com.assp.modules.sys.service.IRoleService;
import com.assp.modules.sys.service.IUserService;

/**
 * 
 * 描述：用户controller
 * 创建人： 魏泽超
 * 创建时间: 2016年7月26日 上午11:05:23
 * @version
 */
@Controller
@RequestMapping("/console/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	/**
	 * 
	 * 描述：用户列表
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月26日 上午11:06:25
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/userList")
	public Map<String, Object> userList(String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 用户数据
		List<User> userList = null;
		// 用户总数
		int count = 0;
		User user = new User();
		user.setUserName(userName);
		if (StringUtils.isNotBlank(userName) && null != userName) {
			userList = userService.queryAll(user);
			count = userService.queryCount(user);
		} else {
			userList = userService.queryAll(null);
			count = userService.queryCount(null);
		}

		result.put("total", count);
		result.put("rows", userList);
		return result;
	}

	/**'
	 * 
	 * 描述：跳转至角色分配界面
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月28日 下午12:05:34
	 * @version
	 */
	@RequestMapping("/toUserRoleZtree.do")
	public String toUserRoleZtree(ModelMap model, @RequestParam(value = "userId", required = false) String userId) {
		model.put("userId", userId);
		return "/user/userRole";
	}

	/**
	 * 
	 * 描述：用户分配角色
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月28日 下午5:13:15
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/userRole.do")
	public Map<String, Object> userRole(@RequestParam(value = "roleIds", required = false) String roleIds, @RequestParam(value = "userId", required = false) String userId, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.addUserRole(roleIds.split(","), userId);
		map.put("flag", flag);
		return map;
	}

	/**
	 * 
	 * 描述：获取角色树
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月28日 下午5:14:05
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/roleZtree.do")
	public void roleZtree(HttpServletResponse response, ModelMap model, @RequestParam(value = "userId", required = false) String userId) {
		Role role = new Role();
		role.setRoleStatus(0);
		// 查询角色
		List<Role> queryAll = roleService.queryAll(role);

		// 树形菜单拼装
		List<Ztree> ztreeList = new ArrayList<Ztree>();
		for (Role role2 : queryAll) {
			Ztree ztree = new Ztree();
			ztree.setId(role2.getRoleId().toString());
			ztree.setName(role2.getRoleName());
			ztreeList.add(ztree);
		}

		// 已经分配的角色
		List<String> userRoleList = userService.getUserRoleByUserId(userId);

		for (Ztree ztree : ztreeList) {
			for (String string : userRoleList) {
				if (ztree.getId().equals(string)) {
					ztree.setChecked(true);
				}
			}
		}

		// 树形数据
		String json = JSONArray.toJSONString(ztreeList, true).replace("pid", "pId");

		// model.put("ztreeJson", json);

		printJSON(response, json);
	}

	/**
	 * 
	 * 描述：添加用户
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午2:39:40
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/addUser.do")
	public Map<String, Object> addUser(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		int status = 0; // 0 失败 1 成功
		// 判断是否有重复用户
		User checkUser = new User();
		checkUser.setUserName(user.getUserName());
		User userRst = userService.queryOne(checkUser);

		if (null == userRst) { // 当前用户不存在
			logger.debug("当前用户不存在 ");
			// 设置密码
			String olPpassword = user.getPassword();
			String password = new SimpleHash("SHA-256", olPpassword).toString();
			password.endsWith(user.getPassword());
			user.setPassword(password);
			// 完善用户信息
			user.setPhone(user.getUserName());
			user.setIsActive(1);
			user.setNickName(user.getUserName());
			user.setRealName(user.getUserName());
			user.setSex(0);
			user.setUserStatus(0);
			user.setDataStatus(0);
			user.setSource(0);

			int userId = userService.insertUser(user);
			if (userId > 0) {
				status = 1;
				map.put("msg", "恭喜您注册成功！");
				map.put("user", user);
			} else {
				map.put("msg", "很抱歉，注册失败！");
			}
		} else {
			map.put("msg", "该用户已存在，请重新输入！");
		}

		map.put("status", status);
		return map;
	}

	/**
	 * 
	 * 描述：删除用户
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:31:03
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/deleteUser.do")
	public Map<String, Object> deleteUser(String[] Ids) {
		int status = 0; // 1为成功
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.deleteUserByIds(Ids);
		if (flag) {
			status = 1;
			map.put("msg", "删除成功");
		}
		map.put("status", status);
		return map;

	}

	/**
	 * 
	 * 描述：去更新用户页面
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:31:10
	 * @version
	 */
	@RequestMapping("/toUpdateUser.do")
	public String toUpdateUser(String userId, ModelMap model) {
		User user = new User();
		user.setUserId(Integer.valueOf(userId));
		User queryOne = userService.queryOne(user);
		if (null != queryOne) {
			model.put("user", queryOne);
		}
		return "/user/updateUser";
	}

	/**
	 * 
	 * 描述：更新用户
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午5:31:18
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/updateUser.do")
	public Map<String, Object> updateUser(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		int status = 0; // 1为成功
		user.setPassword(new SimpleHash("SHA-256", user.getPassword()).toString());
		int i = userService.updateByPrimaryKeySelective(user);
		if (i > 0) {
			status = 1;
			map.put("msg", "更新成功");
		}
		map.put("status", status);
		return map;
	}
}
