package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.User;

public interface IUserService {

	User login(User user);

	void regi(User user);

	User findUserByEmail(String email);

	void updateMyself(User user);

	void deleteMyself(String email);

	List<User> findAllUsers();

	int emailCheck(String email);

	User logout(User user);

	int pwdCheck(User user);

	User findEmail(User user);

	User findUserInfo(User user);

	void updatePwd(User user);

	User myselfDetail(String email);

	void findPwd(User finduserinfo);

}
