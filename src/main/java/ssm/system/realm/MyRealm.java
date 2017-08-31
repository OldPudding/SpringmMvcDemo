package ssm.system.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import ssm.system.entity.User;
import ssm.system.service.UserService;

public class MyRealm extends AuthorizingRealm {
	
	@Resource
	private UserService userService;
	
	/**
     * 为当前登陆的用户授予角色和权限
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
     * 对前登陆的用户进行身份认证
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
	    // 获取前台的用户名 
		String username = (String) token.getPrincipal(); 
		// 获取前台的密码
        String password = new String((char[])token.getCredentials());
        // 根据用户名从数据库中查询出信息
		User user = userService.getByUsername(username);		
		if(user == null || !password.equals(user.getPassword()) )
		{
		    //账号或者密码输入错误
            throw new UnknownAccountException();
			
		}
        
		//把从数据库中查询出来的信息放到authcInfo中返回给Shiro
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                user.getUserName(), user.getPassword(), "MyRealm");
        return authcInfo;
	}

}
