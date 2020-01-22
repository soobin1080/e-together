package com.ssafy.edu.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.User;

@Repository
public class UserDaoImpl {
	String ns = "ssafy.user.";
	
	@Autowired
	private SqlSession sqlSession;

	public List<User> findAllUsers() {
		return sqlSession.selectList(ns + "findAllUsers");
	}
}