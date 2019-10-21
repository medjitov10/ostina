package com.ga.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;

	// Getters and setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	// Default constructor and Overloaded constructor
	public User(Long userId, String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public User() {};
}
