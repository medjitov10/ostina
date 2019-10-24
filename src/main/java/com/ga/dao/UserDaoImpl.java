package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Profile;
import com.ga.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUserByUserName(String username) {
		Session session = sessionFactory.getCurrentSession();
		User dbuser;
		try {
			session.beginTransaction();
			dbuser = (User) session.createQuery("from User u where u.username='" + username + "'").getSingleResult();

		} finally {
			session.close();
		}
		return dbuser;
	}

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
			foundUser = (User) session.createQuery("FROM User where username='" + user.getUsername() + "'")
					.getSingleResult();
		} finally {
			session.close();
		}
		return foundUser;
	}

	@Override
	public Profile createProfile(Profile profile, User user) {
		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();
			profile.setUser(user);
			user.setUserProfile(profile);
			session.saveOrUpdate(profile);
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return profile;
	}

	@Override
	public Profile getProfile(User user) {

		return user.getUserProfile();
	}

	@Override
	public Profile updateProfile(Profile profile, User user) {
		Session session = sessionFactory.getCurrentSession();
		Profile existedProfile = user.getUserProfile();
		existedProfile.setMobile(profile.getMobile());
		try {
			session.beginTransaction();
			existedProfile.setUser(user);
			user.setUserProfile(existedProfile);
			session.update(existedProfile);
			session.update(user);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return existedProfile;
	}

	@Override
	public List<Comment> getCommentsByUser(User user) {
		return user.getComments();
	}

}
