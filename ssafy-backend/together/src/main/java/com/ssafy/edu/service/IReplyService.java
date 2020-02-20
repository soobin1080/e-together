package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.Reply;

public interface IReplyService {

	List<Reply> getAllReply(int review_num);

	void insertReply(Reply reply);

	void deleteReply(int reply_num);

	void updateReply(Reply reply);

}
