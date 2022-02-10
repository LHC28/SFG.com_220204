package com.SFG.user.dao;

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
}
