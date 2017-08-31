package ssm.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ssm.system.dao.UserDao;
import ssm.system.entity.User;
import ssm.system.service.UserService;
import ssm.system.util.MethodUtils;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDao userDao;//用于注解bean,写于字段上。不需要再额外写setter方法
	
	
	public User getByUsername(String username) {
		return userDao.getByUsername(username);
	}
}
