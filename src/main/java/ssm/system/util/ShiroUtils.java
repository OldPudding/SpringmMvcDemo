package ssm.system.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import ssm.system.entity.User;


/**
 * shiro工具类
 * @author yuqs
 * @since 0.1
 */
public class ShiroUtils {
	/**
	 * 获取当前认证实体的登录名称
	 * @return
	 */
	public static String getUsername() {
		Subject subject = SecurityUtils.getSubject();
		if(subject != null) return (String)subject.getPrincipal();
		throw new RuntimeException("user's name is null.");
	}
	
	public static User getUser() {
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getSession().getAttribute("currentUser");
		return user;
	}
}

