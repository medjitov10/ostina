package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Profile;
import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired 
	private UserService userService;
	
	@GetMapping
	public Profile getProfile(@RequestHeader("Authorization") String tokenHeader) {
		return userService.getProfile(tokenHeader);
	}
	
	@PostMapping
	public Profile createProfile(@RequestHeader("Authorization") String tokenHeader, @RequestBody Profile profile) {
		return userService.createProfile(profile, tokenHeader);
	}
	
	@PutMapping
	public Profile updateProfile(@RequestHeader("Authorization") String tokenHeader, @RequestBody Profile profile) {
		return userService.updateProfile(profile, tokenHeader);
	}
}
