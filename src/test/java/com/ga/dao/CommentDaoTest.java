package com.ga.dao;

import org.junit.Rule;

import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.Profile;
import com.ga.entity.User;

public class CommentDaoTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	@InjectMocks
	private User user;
	@InjectMocks
	private Post post;
	
	@InjectMocks
	private Comment comment;

	@InjectMocks
	private CommentDaoImpl commentDao;

	@Mock
	private SessionFactory sessionFactory;

	@Mock
	Session session;

	@Mock
	Transaction transaction;
	
    
    @Mock
    Query<User> query;
    
    @Test
    public void createComment_commentDao_Success() {
    	Comment commentTemp = commentDao.createComment(comment, post, user);
    	assertEquals(comment, commentTemp);
    }
    
    @Test
    public void getCommentByCommentId_commentDao_Success() {
    	when(session.get(Comment.class, 1L)).thenReturn(comment);
    	Comment commentTemp = commentDao.getCommentByCommentId(1L);
    	assertEquals(comment, commentTemp);
    }
    
    @Test
    public void deleteComment_commentDao_Success() {
    	Comment commentTemp = commentDao.deleteComment(user, comment);
    	assertEquals(comment, commentTemp);
    }
    
    @Before
    public void init() {
    	when(sessionFactory.getCurrentSession()).thenReturn(session);
    	when(session.getTransaction()).thenReturn(transaction);
    }
}
