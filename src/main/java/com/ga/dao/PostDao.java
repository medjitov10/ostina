package com.ga.dao;



import java.util.List;

import com.ga.entity.Post;
import com.ga.entity.User;

public interface PostDao {
	public Post createPost(User user, Post post);
	
	public List <Post> listPosts();
}
