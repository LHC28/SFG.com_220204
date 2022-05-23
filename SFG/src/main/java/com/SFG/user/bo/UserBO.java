package com.SFG.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFG.user.dao.UserDAO;
import com.SFG.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public void addUser(String loginId, String password, String name, String email) {
		userDAO.insertUser(loginId, password, name, email);
	}
	
	public User selectUser(String loginId) {
		return userDAO.selectUser(loginId);
	}
	
	public User selectUserByLoginIdAndPassword(String loginId, String password) {
		return userDAO.selectUserByLoginIdAndPassword(loginId, password);
	}
	
//	유저리스트 가져오기
	public List<User> getUserList(){
		return userDAO.selectUserList();
	}
	
	public void deleteUserById(int id) {
		userDAO.deleteUserById(id);
	}
	
//	이름과 이메일을 활용하여 로그인 아이디 가져오기
	public String getUserLoginIdByNameAndEmail(String name, String email) {
		User user = userDAO.selectUserLoginIdByNameAndEmail(name, email);
		if(user==null) {
			return null;
		}else {
			return user.getLoginId();
		}
	}
}
