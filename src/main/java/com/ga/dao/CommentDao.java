package com.ga.dao;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

public interface CommentDao {
	public Comment createComment(Comment comment, Post post, User user);
	public Comment getCommentByCommentId(Long id);
	public Comment deleteComment(User user, Comment comment);
	
}
