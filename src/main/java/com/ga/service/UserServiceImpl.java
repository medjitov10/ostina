package com.ga.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.Profile;
import com.ga.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	@Qualifier("encoder")
	private PasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<User> listUsers() {

		return userDao.listUsers();
	}

	@Override
	public List<String> signUp(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		User savedUser = userDao.signUp(user);
		
		if(savedUser != null) {
			UserDetails userDetails = loadUserByUsername(user.getUsername());
			return Arrays.asList( jwtUtil.generateToken(userDetails),user.getUsername());
			
		}
		return null;
	}

	@Override
	public List<String> logIn(User user) {
		User savedUser = userDao.logIn(user);
		if(user.getId()!= null && savedUser != null) {
			UserDetails userDetails = loadUserByUsername(user.getUsername());
			return Arrays.asList( jwtUtil.generateToken(userDetails),user.getUsername());
		}
		
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.getUserByUserName(username);

		return new org.springframework.security.core.userdetails
				.User(user.getUsername(), 
				bCryptPasswordEncoder.encode(user.getPassword()), getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        
//convert the roles into granted authority list
        return authorities;
    }

	@Override
	public Profile createProfile(Profile profile, String tokenHeader) {
		String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(tokenHeader));
		User user = userDao.getUserByUserName(username);
	
		return userDao.createProfile(profile, user);
	}
	
	
	
	@Override
	public Profile getProfile(String token) {
		String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(token));
		User user = userDao.getUserByUserName(username);
		return userDao.getProfile(user);
	}

	@Override
	public Profile updateProfile(Profile profile, String tokenHeader) {
		String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(tokenHeader));
		User user = userDao.getUserByUserName(username);
		
		return userDao.updateProfile(profile, user);
	}

	@Override
	public List<Comment> commentsByUser(String token) {
		String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(token));
		User user = userDao.getUserByUserName(username);
		return userDao.getCommentsByUser(user);
	}

	@Override
	public List<Post> postsByUser(String tokenHeader) {
		String username = jwtUtil.getUsernameFromToken(jwtUtil.pureToken(tokenHeader));
		User user = userDao.getUserByUserName(username);
		return userDao.getPostsByUser(user);
		
	}


}
