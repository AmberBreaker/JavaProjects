package com.blairscott.service;

import com.blairscott.dao.UserDao;
import com.blairscott.po.User;

import java.util.List;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> queryUsers(User user) {
		return userDao.queryUserList("queryUserById", user);
	}

}
