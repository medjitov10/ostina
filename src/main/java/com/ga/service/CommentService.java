package com.ga.service;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

public interface CommentService {
	public Comment createComment(Comment comment, String postId, String token);
}
