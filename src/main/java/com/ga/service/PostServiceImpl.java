package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ga.config.JwtUtil;
import com.ga.dao.PostDao;
import com.ga.dao.UserDao;
import com.ga.entity.Post;
import com.ga.entity.User;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private UserDao userDao;
// userdao.getUserByUsername ;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostDao postDao;
	@Override
	public Post createPost(String tokenHeader, Post post) {
		// Get username
		String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(tokenHeader));
		
		User user = userDao.getUserByUserName(username);
		return postDao.createPost(user, post);
	}
	@Override
	public List<Post> listPosts() {
		
		return postDao.listPosts();
	}

}
