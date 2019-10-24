package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;

public interface PostService {
	public Post createPost(String tokenHeader, Post post);

	public List<Post> listPosts();

	public Post deletePost(Long postId, String token);

	public List<Post> allPosts();

	public List<Comment> allComments(Long postId);
}
