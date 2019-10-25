package com.ga.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.ga.service.UserService;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	public void signup_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/signup")//req type
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
	
	private static String createUserInJson(String username, String password) {
        return "{ \"username\": \"" + username + "\", " +
                "\"password\":\"" + password + "\"}";
    }

}