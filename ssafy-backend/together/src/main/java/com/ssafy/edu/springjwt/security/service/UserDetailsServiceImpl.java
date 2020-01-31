package com.ssafy.edu.springjwt.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.service.IUserService;
import com.ssafy.edu.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	IUserService userservice;

	//
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userservice.findUserByEmail(email);
		System.out.println("loadUserByUsername : " + user);
		if(null == user) {
			throw new UsernameNotFoundException("User Not Found with email: " + email);
		}
		
		return UserDetailsImpl.build(user);
	}

}