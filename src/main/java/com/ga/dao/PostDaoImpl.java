package com.ga.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class PostDaoImpl implements PostDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Post createPost(User user, Post post) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			post.setUser(user);
			session.save(post);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
		return post;
	}

	@Override
	public List<Post> listPosts() {
		List<Post> listPosts = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			listPosts = session.createQuery("FROM Post").getResultList();

		} finally {
			session.close();
		}
		return listPosts;
	}

	@Override
	public Post getPostByPostId(Long id) {

		Session session = sessionFactory.getCurrentSession();
		Post post;
		try {
			session.beginTransaction();
			post = session.get(Post.class, id);
		}	finally {
			session.close();
		}
		return post;
	}

	@Override
	public Post deletePost(Long postId) {
		Post post = null;
	    post = getPostByPostId(postId);
		Session session = sessionFactory.getCurrentSession();
       
        try {
            session.beginTransaction();
            
            session.delete(post);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return post;
	}

	@Override
	public List<Comment> allComments(Long postId) {
		Post post = getPostByPostId(postId);

		return post.getComments();
	}
}
