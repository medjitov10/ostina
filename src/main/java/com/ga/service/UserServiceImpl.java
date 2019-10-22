package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.UserDao;
import com.ga.entity.User;
@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	UserDao userDao;
	@Override
	public List<User> listUsers() {
		
		return userDao.listUsers();
	}
     
	@Override
	public User signUp(User user) {
		return userDao.signUp(user);
	}

	@Override
	public User logIn(User user) {
		return userDao.logIn(user);
	}
	
}
