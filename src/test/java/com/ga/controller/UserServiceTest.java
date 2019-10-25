package com.ga.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.service.UserServiceImpl;

public class UserServiceTest {
	
	@Mock
	private UserDao userDao;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@InjectMocks
	private User user;
	@Before
    public void initMocks() {
      MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void listUsers_User_Success() {
		List<User> listOfUsers = new ArrayList<>(); 
		Arrays.asList(user);
		when(userDao.listUsers()).thenReturn(listOfUsers);
		List<User> listOfUsersTemp = userService.listUsers();
		assertEquals(listOfUsers, listOfUsersTemp);
	}
    
	 @Before
    public void initializeDummyUser() {
        user.setId(1L);
        user.setUsername("batman");
    }
	
}
