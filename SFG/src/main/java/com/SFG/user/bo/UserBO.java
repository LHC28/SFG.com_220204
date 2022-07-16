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
	
//	이름, 로그인아이디, 이메일을 활용하여 유저 정보 가져오기
	public User getUserByNameAndLoginIdAndEmail(String name, String loginId, String email) {
		return userDAO.selectUserByNameAndLoginIdAndEmail(name, loginId, email);
	}
	
//	로그인아이디를 활용한 비밀번호 변경
	public void changePasswordByLoginId(String loginId, String password) {
		userDAO.updatePasswordByLoginId(loginId, password);
	}
	
//	댓글에서 필요한 닉네임 가져오기
	public String getUserName(int userId) {
		User user = userDAO.selectUserByUserId(userId);
		return user.getName();
	}
}
