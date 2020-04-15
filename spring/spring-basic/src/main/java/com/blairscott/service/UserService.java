package com.blairscott.service;

import com.blairscott.po.User;

import java.util.List;

public interface UserService {

	List<User> queryUsers(User user);
}
