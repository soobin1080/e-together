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

	
	public List<Reply> getAllReply(int review_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getAllReply", review_num);
	}

	public void insertReply(Reply reply) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "insertReply", reply);
	}

	public void deleteReply(int reply_num) {
		// TODO Auto-generated method stub
		sqlSession.delete(ns + "deleteReply", reply_num);
	}

	public void updateReply(Reply reply) {
		// TODO Auto-generated method stub
		sqlSession.update(ns + "updateReply", reply);
	}

	

}
