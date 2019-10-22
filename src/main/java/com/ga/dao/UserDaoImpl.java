package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> listUsers() {
		List<User> allUsers = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			allUsers = session.createQuery("FROM User").getResultList();

		} finally {
			session.close();
		}
		return allUsers;
	}

	@Override
	public User signUp(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User logIn(User user) {
		Session session = sessionFactory.getCurrentSession();
		User foundUser;
		try {
			session.beginTransaction();
			foundUser = (User) session.createQuery("FROM User where username='" + user.getUsername()
					+ "' and password='" + user.getPassword() + "'").getSingleResult();
		} finally {
			session.close();
		}
		return foundUser;
	}

}
