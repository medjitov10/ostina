package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Post;
import com.ga.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostService postService;

	@PostMapping
	public Post createPost(@RequestHeader("Authorization") String tokenHeader, @RequestBody Post post) {
		return postService.createPost(tokenHeader, post);
	}
	//@RequestHeader("Authorization") String tokerHeader,
	@DeleteMapping("/{postId}")
	public Post deletePost(@PathVariable Long postId) {
		return postService.deletePost(postId);
	}
	
	@GetMapping("/list")
	public List<Post> allPosts(){
	return postService.listPosts();
	}
}
