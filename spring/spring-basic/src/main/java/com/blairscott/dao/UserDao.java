package com.blairscott.dao;

import com.blairscott.po.User;

import java.util.List;

public interface UserDao {

    public List<User> queryUserList(String sqlId, Object param);

}
