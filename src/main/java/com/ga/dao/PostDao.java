package com.ga.dao;



import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

public interface PostDao {
	public Post createPost(User user, Post post);
	
	public List <Post> listPosts();
	
	public Post getPostByPostId(Long id);

	public Post deletePost(Long postId);

	public List<Comment> allComments(Long postId);
}
