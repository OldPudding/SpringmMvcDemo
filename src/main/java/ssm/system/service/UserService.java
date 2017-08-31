package ssm.system.service;

import java.util.List;

import ssm.system.entity.User;

public interface UserService {
	
	public User getByUsername(String username);	
}
