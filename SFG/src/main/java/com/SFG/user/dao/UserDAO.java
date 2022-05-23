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
	
//	입력한 이름과 이메일을 활용한 로그인 아이디 가져오기
	public User selectUserLoginIdByNameAndEmail(
			@Param("name") String name
			,@Param("email") String email
			);
	
//	이름, 아이디, 이메일을 활용한 유저 정보 가져오기
	public User selectUserByNameAndLoginIdAndEmail(
			@Param("name") String name
			,@Param("loginId") String loginId
			,@Param("email") String email
			);
	
//	로그인아이디를 활용한 비밀번호 변경
	public void updatePasswordByLoginId(
			@Param("loginId") String loginId
			,@Param("password") String password
			);
}
