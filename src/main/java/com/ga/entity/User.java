package com.ga.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
	
	@JsonIgnore
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password", nullable = false)
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Post> posts;
	
	@JsonIgnore
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn( name = "user_profile_id")
	private Profile userProfile;

	// Getters and setters
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
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
	

	public Profile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(Profile userProfile) {
		this.userProfile = userProfile;
	}

	public User() {};
}
