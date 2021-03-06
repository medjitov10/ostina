package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ga.config.JwtUtil;
import com.ga.dao.PostDao;
import com.ga.dao.UserDao;
import com.ga.entity.Comment;
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
	@Override
	public Post deletePost(Long postId, String token) {
		String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(token));
		User user = userDao.getUserByUserName(username);
		
		Post post = postDao.getPostByPostId(postId);
		
		if (post.getUser().getId() == user.getId())
			return postDao.deletePost(postId);
		return null;
	}
	@Override
	public List<Post> allPosts() {
		return postDao.listPosts();
	}
	
	@Override
	public List<Comment> allComments(Long postId) {
		
		return postDao.allComments(postId);
	}

	
}
