package com.ga.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name="Posts")
public class Post {
	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long post_id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String title;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "post", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Comment> comments;
	
//	private HashMap<String, HashMap<String, String>> userDetail;
	
//	public HashMap<String, HashMap<String, String>> getUserDetail() {
//		return userDetail;
//	}

	



	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
//		HashMap<String, HashMap<String,String>> usernameDetail = new HashMap<>();
//		HashMap<String, String> username = new HashMap<>();
//		username.put("username", user.getUsername());
//		usernameDetail.put("user", username);
//		this.userDetail = usernameDetail;
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String body) {
		this.description = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Post(long post_id, String body, String title, User user) {
		this.user = user;
		this.post_id = post_id;
		this.description = body;
		this.title = title;
	}
	
	public Post() {}
}
