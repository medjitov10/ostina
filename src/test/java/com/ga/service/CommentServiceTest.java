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
import com.ga.dao.CommentDao;
import com.ga.dao.PostDao;
import com.ga.dao.UserDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.Profile;
import com.ga.entity.User;
import com.ga.service.UserServiceImpl;

public class CommentServiceTest {
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
	private CommentServiceImpl commentService;

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
	public void someProcedue() {
		
	}

	@Before
	public void initializeDummyUser() {
		user.setId(1L);
		user.setUsername("batman");
		post.setId(1L);
		comment.setUser(user);
		comment.setId(1L);
	}

	@Test
	public void createComment_commentService_Success() {
		when(jwtUtil.getUsernameFromToken(any())).thenReturn("Hristina");
		when(userDao.getUserByUserName(user.getUsername())).thenReturn(user);
		when(postDao.getPostByPostId(1L)).thenReturn(post);
		when(commentDao.createComment(comment, post, user)).thenReturn(comment);
		Comment commentTmp = commentService.createComment(comment, 1L, any());
		assertEquals(commentTmp, commentTmp);
	}
	
	@Test
	public void deleteComment_commentService_Success() {
		when(jwtUtil.getUsernameFromToken(any())).thenReturn("Hristina");
		when(userDao.getUserByUserName(anyString())).thenReturn(user);
		when(commentDao.getCommentByCommentId(1L)).thenReturn(comment);
		when(commentDao.deleteComment(user, comment)).thenReturn(comment);
		Comment commentTmp = commentService.deleteComment("1234", 1L);
		assertEquals(commentTmp, commentTmp);
	}
	
	
}
