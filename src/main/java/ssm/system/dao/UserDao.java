package ssm.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm.system.entity.User;

public interface UserDao {
	//根据用户名获取用户
	public User getByUsername(String username);
}
