package com.ga.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.UserService;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	UserController userController;
	
	@InjectMocks
	private User user;
	
	@InjectMocks
	private Comment comment;
	
	@InjectMocks
	private Post post;
	
	@Mock
	UserService userService;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	private static String createUserInJson(String username, String password) {
        return "{ \"username\": \"" + username + "\", " +
                "\"password\":\"" + password + "\"}";
    }
	
	
	@Test
	public void signup_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/signup")
			       .contentType(MediaType.APPLICATION_JSON)
			       //.header("Authorization", "some-token")
			       .content(createUserInJson("joe","abc"));//body {username and password}
		List<String> retString = Arrays.asList("123456", "user");
		
		when(userService.signUp(any())).thenReturn(retString);//mocking userService
		
		MvcResult result = mockMvc.perform(requestBuilder)//making a req
	              .andExpect(status().isOk())
	              .andExpect(content().json("{\"username\":\"user\",\"token\":\"123456\"}"))
	              .andReturn();
	    
		
	      System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	public void login_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/login")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(createUserInJson("joe","abc"));
		
		List<String> retString = Arrays.asList("123456", "user");
		
		when(userService.logIn(any())).thenReturn(retString);
		
		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json("{\"username\":\"user\",\"token\":\"123456\"}"))
	              .andReturn();
	    
		
	      System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void list_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .get("/user/list")
			       .accept(MediaType.APPLICATION_JSON);
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(user);
		
		ObjectMapper mapper = new ObjectMapper();
		String listUserMapper = mapper.writeValueAsString(listOfUsers);
		
		when(userService.listUsers()).thenReturn(listOfUsers);
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().string(listUserMapper));
		
	}
	
	@Test
	public void commentsByUser_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .get("/user/comment")
			       .accept(MediaType.APPLICATION_JSON)
			       .header("Authorization", "Bearer " + "token");
		List<Comment> listOfComments = new ArrayList<>();
		listOfComments.add(comment);
		ObjectMapper mapper = new ObjectMapper();
		String listOfCommentsMapper = mapper.writeValueAsString(listOfComments);
		
		when(userService.commentsByUser(any())).thenReturn(listOfComments);
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().string(listOfCommentsMapper));
	}
	
	@Test
	public void postByUser_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .get("/user/post")
			       .accept(MediaType.APPLICATION_JSON)
			       .header("Authorization", "Bearer " + "token");
		List<Post> listOfPosts = new ArrayList<>();
		listOfPosts.add(post);
		ObjectMapper mapper = new ObjectMapper();
		String listOfPostsMapper = mapper.writeValueAsString(listOfPosts);
		
		when(userService.postsByUser(any())).thenReturn(listOfPosts);
		mockMvc.perform(requestBuilder)
		.andExpect(status().isOk())
		.andExpect(content().string(listOfPostsMapper));
		
		
		
	}
	
	@Before
	public void initializer() throws JsonProcessingException {
		user.setUsername("Osman");
		user.setId(1L);
		comment.setText("Hi my name is христина");
		comment.setId(1L);
		post.setTitle("test");
		post.setId(1L);
	}
	


}