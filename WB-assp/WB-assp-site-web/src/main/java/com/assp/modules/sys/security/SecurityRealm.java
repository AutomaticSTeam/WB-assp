package com.assp.modules.sys.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assp.common.constant.SystemConstants;
import com.assp.modules.sys.entity.Permission;
import com.assp.modules.sys.entity.PermissionVo;
import com.assp.modules.sys.entity.Role;
import com.assp.modules.sys.entity.User;
import com.assp.modules.sys.mapper.PermissionMapper;
import com.assp.modules.sys.mapper.RoleMapper;
import com.assp.modules.sys.mapper.UserMapper;
/**
 * Configured Apache Shiro Realm.
 */
@SuppressWarnings("deprecation")
@Component
public class SecurityRealm extends AuthorizingRealm {
	
	private static final Logger logger = Logger. getLogger(SecurityRealm.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	
	public SecurityRealm() {
        setName("securityRealm"); //This name must match the name in the User class's getPrincipals() method
        setCredentialsMatcher(new Sha256CredentialsMatcher());
        setCacheManager(new MemoryConstrainedCacheManager());
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        token.setRememberMe(true);
        User user = new User();
        user.setUserStatus(SystemConstants.USABLE_STATUS);
        user.setDataStatus(SystemConstants.USABLE_STATUS);
        user.setNickName(token.getUsername());
        User ruser = userMapper.selectOne(user);
        if( ruser != null ) {
            return new SimpleAuthenticationInfo(ruser.getUserName(), ruser.getPassword(), getName());
        } else {
            return null;
        }
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	SimpleAuthorizationInfo info = null;
    	if(info == null){
    		info = new SimpleAuthorizationInfo();
	    	String nickName = (String) principals.getPrimaryPrincipal();
	    	User user = new User();
	    	user.setNickName(nickName);
	    	user.setUserStatus(SystemConstants.USABLE_STATUS);
	        user.setDataStatus(SystemConstants.USABLE_STATUS);
	        logger.debug("user info -- nickName = " + nickName);
	    	User returnUser = userMapper.selectOne(user); //根据昵称和状态查找用户
	    	if(returnUser != null){
	    		//获取用户角色
	    		List<Role> roles = roleMapper.selectRolesByUserId(returnUser.getUserId());
	    	  	if(roles != null && roles.size() == 1 && "ROLE_ADMIN".equals(roles.get(0).getRoleCode() )){
		    		logger.info("---- user  id>>>" +returnUser.getUserId().intValue());
		    		List<Permission> permissions = permissionMapper.select(null); //查询所有的权限
		    		for (Permission permission : permissions) {
		    			info.addStringPermission(permission.getPermissionCode());
					}
		    	}else{
		    		//获取用户权限
		    		List<Integer> roleIds = new ArrayList<Integer>();
		    		for(Role r : roles){
		    			roleIds.add(r.getRoleId());
		    			info.addRole(r.getRoleCode().toString());
		    		}
		    		PermissionVo permissionVo = new PermissionVo();
		    		if(roleIds != null && roleIds.size() > 0){
		    			permissionVo.setRoleIds(roleIds);
						List<Permission> permissions = permissionMapper.selectPermissionsByVo(permissionVo );
						for(Permission p : permissions){
							info.addStringPermission(p.getPermissionCode().toString());
						}
		    		}
		    	}
	    	}
    	}
    	return info;
    }
    
    public static void main(String[] args) {
    	System.out.println(new SimpleHash("SHA-256", "8888")
		.toString());
	}
}

