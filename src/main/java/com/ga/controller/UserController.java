package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	

}
