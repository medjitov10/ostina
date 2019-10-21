package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ga.dao.UserDao;
import com.ga.entity.User;

public class UserServiceImpl implements UserService {

	@Autowired 
	UserDao userDao;
	
	public List<User> listUsers() {
		
		return userDao.listUsers();
	}

}
