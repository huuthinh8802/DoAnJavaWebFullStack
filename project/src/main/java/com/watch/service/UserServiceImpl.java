package com.watch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watch.models.User;
import com.watch.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(userName);
	}
	@Override
	public boolean create(User user) {
		// TODO Auto-generated method stub
		try {
			this.userRepository.save(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
