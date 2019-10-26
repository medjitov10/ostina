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





public class UserDaoTest {
	
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private User user;


    @InjectMocks
    private UserDaoImpl userDao;
    
    @InjectMocks
    private Profile profile;
    
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    Transaction transaction;
    
    @Before
    public void initializeDummyUser() {
    	user.setUsername("hristina");
    	user.setPassword("histina");
    	user.setId(1L);
    }
    @Before
    public void init() {
    	when(sessionFactory.getCurrentSession()).thenReturn(session);
    	when(session.getTransaction()).thenReturn(transaction);
    }
    
    @Mock
    Query<User> query;
    
    @Test
    public void getuserByUserName_UserDao_Success() {
    	when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(user);
    	User userTemp = userDao.getUserByUserName(user.getUsername());
    	assertEquals(user, userTemp);
    	
    }
    @Test
    public void listUsers_UserDao_Success() {
    	when(session.createQuery(anyString())).thenReturn(query);
    	List<User> users = userDao.listUsers();
    	Assert.assertNotNull(users);
    	
    }
    @Test
    public void signUp_UserDao_Success() {
    	User userTemp = userDao.signUp(user);
    	assertEquals(userTemp, user);
    }
    
    @Test
    public void logIn_UserDao_Success() {
    	when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(user);
    	User userTemp = userDao.logIn(user);
    	assertEquals(userTemp, user);
    	
    	
    }
    
    @Test
    public void createProfile_UserDao_Success() {
    	Profile profileTemp = userDao.createProfile(profile, user);
    	Assert.assertNotNull(profileTemp);
    	
    }
    
    @Test
    public void getProfile_UserDao_Success() {
    	Profile profileTemp = userDao.getProfile(user);
    
    	Assert.assertNotNull(profileTemp);    	
    }
    
    @Test
    public void updateProfile_UserDao_Success() {
    	Profile profileTemp = userDao.updateProfile(profile, user);
    	Assert.assertNotNull(profileTemp);
    
    }
    @Test
    public void getCommentsByUser_UserDao_Success() {
    	List <Comment> comments = userDao.getCommentsByUser(user);
    	
    	assertEquals(comments, user.getComments());
    	
    }
    @Test
    public void getPostByUser_UserDao_Success() {
    	List <Post> posts = userDao.getPostsByUser(user);
    	
    	assertEquals(posts, user.getPosts());
    	
    }
    
    @Test
    public void getPostsByUser_UserDao_Success() {
    	List <Post> posts = userDao.getPostsByUser(user);
    	
    	assertEquals(posts, user.getPosts());
    	
    }
    
    @Before
	public void ingrv() throws JsonProcessingException {
		user.setUsername("Osman");
		user.setId(1L);
		user.setUserProfile(profile);
    }
    
	

}
