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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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
	

	public long getId() {
		return id;
	}

	public void setId(long post_id) {
		this.id = post_id;
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
		this.id = post_id;
		this.description = body;
		this.title = title;
	}
	
	public Post() {}
}
