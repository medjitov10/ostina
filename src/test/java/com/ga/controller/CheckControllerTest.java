package com.ga.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class CheckControllerTest {
	private MockMvc mockMvc;
	
	@Mock
	private CheckController checkController;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(checkController).build();
	}
	@Test
	public void helloWorld_HelloWorld_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/test")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().string(""));
	}
}
