package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class PostDaoTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	@InjectMocks
	private User user;
	
	@InjectMocks
	private PostDaoImpl postDao;
	@InjectMocks
	private Comment comment;
	@InjectMocks
	private Post post;
	
	@Mock
	private SessionFactory sessionFactory;
	@Mock
	Session session;
	@Mock
	Transaction transaction;
    @Mock
    Query<Post> query;
    
    @Test
    public void createPotst_PostDao_Success() {
    	Post postTmp = postDao.createPost(user, post);
    	assertEquals(post, postTmp);
    }
    
    @Test
    public void listPosts_PostDao_Success() {
    	List<Post> listOfPosts = new ArrayList<>();
    	listOfPosts.add(post);
    	when(session.createQuery("FROM Post")).thenReturn(query);
    	when(query.getResultList()).thenReturn(listOfPosts);
    	List<Post> listPostsTmp = postDao.listPosts();
    	assertEquals(listOfPosts, listPostsTmp);
    }
    
    @Test
    public void getPostByPostId_PostDao_Success() {
    	when(session.get(Post.class, 1L)).thenReturn(post);
    	Post postTmp = postDao.getPostByPostId(1L);
    	assertEquals(postTmp, post);
    }
	
    @Test
    public void deletePost_PostDao_Success() {
    	when(session.get(Post.class, 1L)).thenReturn(post);
    	Post postTmp = postDao.deletePost(1L);
    	assertEquals(postTmp, post);
    }
    
    @Test
    public void allComments_PostDao_Success() {
    	when(session.get(Post.class, 1L)).thenReturn(post);
    	assertEquals(postDao.allComments(1L), post.getComments());
    }
    
	@Before
	public void initEntities() {
		user.setId(1L);
		comment.setId(1L);
		post.setId(1L);
		comment.setPost(post);
	}
	
	@Before
    public void init() {
    	when(sessionFactory.getCurrentSession()).thenReturn(session);
    	when(session.getTransaction()).thenReturn(transaction);
    }
	
}
