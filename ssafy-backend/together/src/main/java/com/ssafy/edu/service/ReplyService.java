package com.ssafy.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.edu.dao.ReplyDaoImpl;
import com.ssafy.edu.model.Reply;

@Service
public class ReplyService implements IReplyService {

	@Autowired
	private ReplyDaoImpl replydao;

	@Override
	public List<Reply> getAllReply(int review_num) {
		// TODO Auto-generated method stub
		return replydao.getAllReply(review_num);
	}
	
	@Override
	public void insertReply(Reply reply) {
		// TODO Auto-generated method stub
		replydao.insertReply(reply);
		
	}

	@Override
	public void deleteReply(Reply reply) {
		// TODO Auto-generated method stub
		replydao.deleteReply(reply);
		
	}


	@Override
	public void updateReply(Reply reply) {
		// TODO Auto-generated method stub
		replydao.updateReply(reply);
	}


	

}
