package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Comment;
import com.ga.entity.JwtResponse;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired 
	UserService userService;
	
	@GetMapping("/list")
	
	public List<User> listUser() {
		return userService.listUsers();
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
    	return ResponseEntity.ok(new JwtResponse(userService.signUp(user)));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		return ResponseEntity.ok(new JwtResponse(userService.logIn(user)));
	}
	
	@GetMapping("/comment")
	public List<Comment> commentsByUser(@RequestHeader("Authorization") String tokenHeader) {
		return userService.commentsByUser(tokenHeader);
	}
	
	@GetMapping("/post")
	public List<Post> postsByUser(@RequestHeader("Authorization") String tokenHeader) {
		return userService.postsByUser(tokenHeader);
	}
}
