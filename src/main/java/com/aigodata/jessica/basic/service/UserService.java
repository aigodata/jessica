package com.aigodata.jessica.basic.service;

import java.util.List;

import com.aigodata.jessica.basic.domain.User;

public interface UserService {

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public User add(User user);


	public List<User> getAll();
	
	/**
	 * 更新用户
	 * @param user
	 */
	public int update(User user);

}
