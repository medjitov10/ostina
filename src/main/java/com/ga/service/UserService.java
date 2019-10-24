package com.ga.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.Profile;
import com.ga.entity.User;

public interface UserService extends UserDetailsService{

	public List<User> listUsers();
	
	public String signUp(User user);

	public String logIn(User user);

	public Profile createProfile(Profile profile, String tokenHeader);
	
	public Profile getProfile(String token);

	public Profile updateProfile(Profile profile, String tokenHeader);
	
	
}
