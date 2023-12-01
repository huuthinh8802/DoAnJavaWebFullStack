package com.watch.service;

import com.watch.models.User;

public interface UserService {
	User findByUserName(String userName);
	boolean create(User user);
}
