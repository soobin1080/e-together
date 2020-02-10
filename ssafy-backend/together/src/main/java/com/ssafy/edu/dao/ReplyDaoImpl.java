package com.ssafy.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.Reply;

@Repository
public class ReplyDaoImpl {
	String ns = "ssafy.reply.";

	@Autowired
	private SqlSession sqlSession;

	public List<Reply> getAllReply(String budget_email, String budget_title) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("budget_email", budget_email);
		paramMap.put("budget_title", budget_title);
		return sqlSession.selectList(ns + "getAllReply", paramMap);
	}

	public void insertReply(Reply reply) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "insertReview", reply);
	}

	public void deleteReply(Reply reply) {
		// TODO Auto-generated method stub
		sqlSession.delete(ns + "deleteReview", reply);
	}

	public void updateReply(Reply reply) {
		// TODO Auto-generated method stub
		sqlSession.update(ns + "updateReply", reply);
	}
	
	

}
