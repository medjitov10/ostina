package com.ga.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.ga.entity.Profile;
import com.ga.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class ProfileTest {
	private MockMvc mockMvc;
	private String token = "123456";
	
	@InjectMocks
	private ProfileController profileController;

	@InjectMocks
	private Profile profile;

	@Mock
	private UserService userService;

	@Test
	public void createProfile_ProfileController_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token).content(createProfileInJson());

		when(userService.createProfile(profile, token)).thenReturn(profile);
		ObjectMapper mapper = new ObjectMapper();
		String jsonMapper = mapper.writeValueAsString(profile);
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().string(jsonMapper));
	}
	
	@Test
	public void getProfile_ProfileController_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/profile").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token);

		ObjectMapper mapper = new ObjectMapper();
		String jsonMapper = mapper.writeValueAsString(profile);
		when(userService.getProfile(token)).thenReturn(profile);

		MvcResult result = mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().string(jsonMapper))
			.andReturn();;
	}

	

	@Before
	public void init() throws JsonProcessingException {
		mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
	}

	@Before
	public void initProfile() {
		profile.setId(1L);
		profile.setMobile("123213");
	}

	private static String createProfileInJson() {
		return "{\"id\":1,\"user\":null,\"additionalEmail\":null,\"mobile\":null,\"address\":null}";
	}
}
