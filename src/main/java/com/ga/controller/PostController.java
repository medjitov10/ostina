package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Post;

@RestController
@RequestMapping("/post")
public class PostController {
//	@Autowired
//	PostService postService;

	@PostMapping
	public Post createPost(@RequestHeader("Authorization") String tokenHeader, @RequestBody Post post) {
		return null;

	}
}
