package com.ga.controller;

import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.PostService;
import com.ga.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)

public class PostControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	PostController postController;

	@InjectMocks
	private User user;

	@InjectMocks
	private Comment comment;

	@InjectMocks
	private Post post;

	@Mock
	PostService postService;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
	}

	@Test
	public void listAllPosts_Post_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/post/list").accept(MediaType.APPLICATION_JSON);
		List<Post> listOfPosts = new ArrayList<>();
		listOfPosts.add(post);

		ObjectMapper mapper = new ObjectMapper();
		String listPostMapper = mapper.writeValueAsString(listOfPosts);

		when(postService.listPosts()).thenReturn(listOfPosts);
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(listPostMapper));

	}

	@Test
	public void listAllComments_Post_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/post/1/comment")
					.header("Authorization", "123123")
				.accept(MediaType.APPLICATION_JSON);
		List<Comment> listOfComments = new ArrayList<>();
		listOfComments.add(comment);

		ObjectMapper mapper = new ObjectMapper();
		String listCommentMapper = mapper.writeValueAsString(listOfComments);

		when(postService.allComments(1L)).thenReturn(listOfComments);
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(listCommentMapper));

	}

	@Before
	public void initComment() {
		comment.setId(1L);
		post.setId(1L);
	}

	@Test
	public void createPost_Post_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/post")
				.header("Authorization", "123123")
				.accept(MediaType.APPLICATION_JSON)
				.content(createPostInJson());

		when(postService.createPost(any(), any())).thenReturn(post);
		
		ObjectMapper mapper = new ObjectMapper();
		String listPostMapper = mapper.writeValueAsString(post);
		
		mockMvc.perform(requestBuilder)
			.andExpect(content().string(listPostMapper));
	}

	@Test
	public void deletePost_Post_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/post/1").header("Authorization", "123123")
				.accept(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String postMapper = mapper.writeValueAsString(post);

		when(postService.deletePost(anyLong(), anyString())).thenReturn(post);
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(postMapper));

	}
	private static String createPostInJson() {
		return "{\"id\":1,\"description\":null,\"title\":null,\"user\":null}";
	}

}

//	@DeleteMapping("/{postId}")
//	public Post deletePost(@PathVariable Long postId, @RequestHeader("Authorization") String tokerHeader) {
//		return postService.deletePost(postId, tokerHeader);
//	}
