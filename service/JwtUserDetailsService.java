package com.demo.hospital_management.hospitalmanagement.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.hospital_management.hospitalmanagement.dao.UserDao;
import com.demo.hospital_management.hospitalmanagement.model.Users;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users userFromDatabase = userDao.findByUsername(username);
		if(userFromDatabase.getUsername().equals(username)) {
			return new User(userFromDatabase.getUsername(), new BCryptPasswordEncoder().encode(userFromDatabase.getPassword()), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found "+username);
		}
	}

}
