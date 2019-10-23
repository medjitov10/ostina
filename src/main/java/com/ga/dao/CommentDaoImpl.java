package com.ga.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Comment createComment(Comment comment, Post post, User user) {
		Session session = sessionFactory.getCurrentSession();
		List<Comment> comments = new ArrayList<Comment>();
		comments.add(comment);
		try {
			session.beginTransaction();
			comment.setUser(user);
			comment.setPost(post);
			session.save(comment);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return comment;
	}

}
