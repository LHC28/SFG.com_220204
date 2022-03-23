package com.SFG.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.SFG.user.model.User;

@Repository
public interface UserDAO {

	public void insertUser(
			@Param("loginId") String loginId
			,@Param("password") String password
			,@Param("name") String name
			,@Param("email") String email);
	
	public User selectUser(String loginId);
	
	public User selectUserByLoginIdAndPassword(
			@Param("loginId") String loginId
			,@Param("password") String password);
	
//	유저 리스트 가져오기
	public List<User> selectUserList();
	
	public void deleteUserById(int id);
}
