package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ga.config.JwtUtil;
import com.ga.dao.CommentDao;
import com.ga.dao.PostDao;
import com.ga.dao.UserDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

public class PostServiceTest {
	
	@Mock
	private CommentDao commentDao;
	@Mock
	private PasswordEncoder bCryptPasswordEncoder;

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private UserDao userDao;
	@Mock
	private PostDao postDao;

	@InjectMocks
	private PostServiceImpl postService;
	
	
	
	

	@InjectMocks
	private User user;

	@InjectMocks
	private Comment comment;

	@InjectMocks
	private Post post;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Before
	public void initializeDummyUser() {
		user.setId(1L);
		user.setUsername("batman");
		post.setId(1L);
		post.setUser(user);
		comment.setId(1L);
		post.setUser(user);
	}

	@Test
	public void createPost_postService_Success() {
		when(jwtUtil.getUsernameFromToken(any())).thenReturn("Hristina");
		when(userDao.getUserByUserName(any())).thenReturn(user);
		when(postDao.createPost(user, post)).thenReturn(post);
		
		Post postTmp = postService.createPost(any(),post);
		
		assertEquals(postTmp, post);
	}
	
	@Test
	public void deletePost_postService_Success() {
		when(jwtUtil.getUsernameFromToken(any())).thenReturn("Hristina");
		when(userDao.getUserByUserName(any())).thenReturn(user);
		when(postDao.getPostByPostId(1L)).thenReturn(post);
		when(postDao.deletePost(1L)).thenReturn(post);
		
		Post postTmp = postService.deletePost(1L,"123123");
		
		assertEquals(postTmp, post);
	}

	

}
