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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password", nullable = false)
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Post> posts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@JsonIgnore
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn( name = "user_profile_id")
	private Profile userProfile;

	// Getters and setters
	
	
	public List<Post> getPosts() {
		return posts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long userId) {
		this.id = userId;
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
		this.id = userId;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	

	public Profile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(Profile userProfile) {
		this.userProfile = userProfile;
	}

	public User() {};
}
