package com.ga.dao;



import com.ga.entity.Post;
import com.ga.entity.User;

public interface PostDao {
	public Post createPost(User user, Post post);
}
