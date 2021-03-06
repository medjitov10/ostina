package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.Profile;
import com.ga.entity.User;

public interface UserDao {
	public List <User> listUsers();
	
	public User signUp (User user);

	public User logIn(User user);
	
	public User getUserByUserName(String username);

	public Profile createProfile(Profile profile, User user);

	public Profile getProfile(User user);

	public Profile updateProfile(Profile profile, User user);

	public List<Comment> getCommentsByUser(User user);

	public List<Post> getPostsByUser(User user);
}
