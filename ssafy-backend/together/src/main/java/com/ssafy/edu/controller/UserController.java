package com.ssafy.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.dao.UserDaoImpl;

@RestController
public class UserController {

	@Autowired
	private UserDaoImpl userDao;
	
	@GetMapping(path = "/helloWorld")
	public String helloWorld() {
		System.out.println(userDao.findAllUsers());
		return String.format("%s", userDao.findAllUsers());
	}
}