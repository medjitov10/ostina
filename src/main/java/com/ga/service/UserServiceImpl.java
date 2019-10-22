package com.ga.service;

import java.util.ArrayList;
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
	public String signUp(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		User savedUser = userDao.signUp(user);
		
		if(savedUser != null) {
			UserDetails userDetails = loadUserByUsername(user.getUsername());
			return jwtUtil.generateToken(userDetails);
		}
		return null;
	}

	@Override
	public String logIn(User user) {
		User savedUser = userDao.logIn(user);
		if(savedUser != null) {
			UserDetails userDetails = loadUserByUsername(user.getUsername());
			return jwtUtil.generateToken(userDetails);
		}
		
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.getUserByUserName(username);

		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				bCryptPasswordEncoder.encode(user.getPassword()), getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        
//convert the roles into granted authority list
        return authorities;
    }

}
