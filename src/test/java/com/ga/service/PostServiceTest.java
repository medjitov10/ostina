package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
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
	
	@InjectMocks
	private Post postNull;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
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
		assertNotNull("Test returned null object, expected non-null", postTmp);
		assertEquals(postTmp, post);
	}

	@Test
	public void deletePost_postService_Failed() {
		User user1 = new User();
		user1.setId(4L);
		postNull.setUser(user1);
		when(jwtUtil.getUsernameFromToken(any())).thenReturn("Hristina");
		when(userDao.getUserByUserName(any())).thenReturn(user);
		when(postDao.getPostByPostId(4L)).thenReturn(postNull);
		Post postTmp = postService.deletePost(4L,"123123");
		Assert.assertNull(postTmp);
	}
	
	@Test
	public void listPosts_postService_Success() {
		List<Post> posts = Arrays.asList(post);
		when(postDao.listPosts()).thenReturn(posts);
		List<Post> listPosts = postService.listPosts();
		Assert.assertNotNull(listPosts);
	}
	
	@Test
	public void allPosts_postService_Success() {
		List<Post> posts = Arrays.asList(post);
		when(postDao.listPosts()).thenReturn(posts);
		List<Post> listPosts = postService.allPosts();
		Assert.assertNotNull(listPosts);
	}
	
	@Test
	public void allComments_postService_Success() {
		List<Comment> comments = Arrays.asList(comment);
		when(postDao.allComments(1L)).thenReturn(comments);
		List<Comment> result = postService.allComments(1L);
		Assert.assertNotNull(result);
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

}
