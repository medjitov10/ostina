package com.ga.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.Profile;
import com.ga.entity.User;
import com.ga.service.UserServiceImpl;

public class UserServiceTest {
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Mock
	private JwtUtil jwtUtil;
	
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@InjectMocks
	private User user;
	
	@InjectMocks
	private Profile profile;
	
	@Before
    public void initMocks() {
      MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void listUsers_User_Success() {
		List<User> listOfUsers = new ArrayList<>(); 
		listOfUsers.add(user);
		when(userDao.listUsers()).thenReturn(listOfUsers);
		List<User> listOfUsersTemp = userService.listUsers();
		assertEquals(listOfUsers, listOfUsersTemp);
	}
	
	@Test
	public void signUp_User_Success() {
		String expectedString = "12345";
		
		when(userDao.signUp(user)).thenReturn(user);
		when(userDao.getUserByUserName(user.getUsername())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn(expectedString);
		when(bCryptPasswordEncoder.encode(any())).thenReturn("Hristina");
		
		List<String> actualToken = userService.signUp(user);
		
		Assert.assertNotNull(actualToken);
	}
	
	@Test
	public void logIn_User_Success() {
		String expectedString = "12345";
		
		when(userDao.logIn(user)).thenReturn(user);
		when(userDao.getUserByUserName(user.getUsername())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn(expectedString);
		when(bCryptPasswordEncoder.encode(any())).thenReturn("Hristina");
		
		List<String> actualToken = userService.logIn(user);
		
		Assert.assertNotNull(actualToken);
	}
	
	@Test
	public void createProfile_User_Success() {
		when(jwtUtil.getUsernameFromToken(any())).thenReturn(user.getUsername());
		when(userDao.getUserByUserName(user.getUsername())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn("12345");
		when(bCryptPasswordEncoder.encode(any())).thenReturn("Hristina");
		when(userDao.createProfile(any(), any())).thenReturn(profile);
		Profile profileTemp = userService.createProfile(profile, "12345");
		
		Assert.assertNotNull(profile);
	}
	
	@Test
	public void getProfile_User_Success() {
		when(jwtUtil.getUsernameFromToken(any())).thenReturn(user.getUsername());
		when(userDao.getUserByUserName(user.getUsername())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn("12345");
		when(bCryptPasswordEncoder.encode(any())).thenReturn("Hristina");
		when(userDao.getProfile(user)).thenReturn(profile);
		Profile profileTemp = userService.getProfile("12345");
		assertEquals(profileTemp, profile);
		
		
	}
    
	 @Before
    public void initializeDummyUser() {
        user.setId(1L);
        user.setUsername("batman");
    }
	 
	
}
