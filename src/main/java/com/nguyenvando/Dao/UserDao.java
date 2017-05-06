package com.nguyenvando.Dao;

import com.nguyenvando.Entities.User;

public interface UserDao {

	public void addUser(User user);
	
	public User findByUserName(String username);
	
}
