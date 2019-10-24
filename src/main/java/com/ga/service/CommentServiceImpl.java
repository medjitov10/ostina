package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.config.JwtUtil;
import com.ga.dao.CommentDao;
import com.ga.dao.PostDao;
import com.ga.dao.UserDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDao userDao;
	@Autowired
	private CommentDao commentDao;

	@Autowired
	private PostDao postDao;

	@Override
	public Comment createComment(Comment comment, Long postId, String headerToken) {
		// TODO Auto-generated method stub
		String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(headerToken));

		User user = userDao.getUserByUserName(username);

		Post post = postDao.getPostByPostId(postId);

		return commentDao.createComment(comment, post, user);
	}

	@Override
	public Comment deleteComment(String tokenHeader, Long commentId) {
		String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(tokenHeader));
		User user = userDao.getUserByUserName(username);
		Comment comment = commentDao.getCommentByCommentId(commentId);
		if (user.getId() == comment.getUser().getId())
		return commentDao.deleteComment(user, comment);
		return null;
	}
}
