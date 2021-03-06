package com.ga.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.Profile;
import com.ga.entity.User;

public interface UserService extends UserDetailsService{

	public List<User> listUsers();
	
	public List<String> signUp(User user);

	public List<String> logIn(User user);

	public Profile createProfile(Profile profile, String tokenHeader);
	
	public Profile getProfile(String token);

	public Profile updateProfile(Profile profile, String tokenHeader);
	
	public List<Comment> commentsByUser(String token);


	public List<Post> postsByUser(String tokenHeader);
}
