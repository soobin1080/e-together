package com.ssafy.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.dao.UserDaoImpl;
import com.ssafy.edu.model.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDaoImpl userdao;

	@Override
	@Transactional
	public User login(User user) {
		return userdao.login(user);
	}

	@Override
	@Transactional
	public void regi(User user) {
		userdao.regi(user);
	}

	@Override
	@Transactional
	public User findUserByEmail(String email) {
		return userdao.findUserByEmail(email);
	}

	@Override
	@Transactional
	public void updateMyself(User user) {
		userdao.updateMyself(user);
	}

	@Override
	@Transactional
	public User myselfDetail(String email) {
		return userdao.myselfDetail(email);
	}
	
	@Override
	@Transactional
	public void deleteMyself(String email) {
		userdao.deleteMyself(email);
	}

	@Override
	@Transactional
	public List<User> findAllUsers() {
		return userdao.findAllUsers();
	}
	
	@Override
	@Transactional
	public int emailCheck(String email) {
		return userdao.emailCheck(email);
	}
	
	@Override
	@Transactional
	public User logout(User user){
		return userdao.logout(user);
	}
	
	@Override
	@Transactional
	public int pwdCheck(User user) {
		return userdao.pwdCheck(user);
	}
	
	@Override
	@Transactional
	public User findEmail(User user) {
		return userdao.findEmail(user);
	}
	
	@Override
	@Transactional
	public User findUserInfo(User user) {
		return userdao.findUserInfo(user);
	}
	
	@Override
	@Transactional
	public void updatePwd(User user) {
		userdao.updatePwd(user);
	}

	@Override
	public void findPwd(User finduserinfo) {
		userdao.findPwd(finduserinfo);
	}
	
	
}
