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
import com.ga.entity.Profile;
import com.ga.entity.User;
import com.ga.service.CommentService;
import com.ga.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
	private MockMvc mockMvc;
	private String token = "123456";
	@InjectMocks
	private CommentController commentController;
	@InjectMocks
	private Comment comment;
	
	@Mock
	private CommentService commentService;
	
	@Test
	public void createComment_commentController_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/comment/1").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token)
				.content(createCommentInJson());

		when(commentService.createComment(any(), any(),  any())).thenReturn(comment);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonMapper = mapper.writeValueAsString(comment);
		MvcResult result = mockMvc.perform(requestBuilder)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonMapper)).andReturn();
	}
	
	@Test
	public void deleteComment_commentController_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/comment/1").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token)
				.content(createCommentInJson());

		when(commentService.deleteComment(any(), any())).thenReturn(comment);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonMapper = mapper.writeValueAsString(comment);
		mockMvc.perform(requestBuilder)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonMapper)).andReturn();
	}
	
	private static String createCommentInJson() {
		return "{\"id\":1,\"user\":null,\"additionalEmail\":null,\"mobile\":null,\"address\":null}";
	}
	
	@Before
	public void initComment() {
		comment.getId();
	}
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
	}
}
