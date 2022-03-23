package com.SFG.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SFG.common.SHA256;
import com.SFG.user.bo.UserBO;
import com.SFG.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	@PostMapping("/duplicated_id")
	public Map<String, String> duplicatedId(
			@RequestParam("loginId") String loginId
			){
		
		Map<String, String> result = new HashMap<>();
		User user = userBO.selectUser(loginId);	
		if(user==null) {
			// 중복되는 아이디가 없는 경우
			result.put("result", "success");
		}else {
			//중복되는 아이디가 있는 경우
			result.put("result" , "fail");
		}
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email
			){
		
		String encryptPassword = null;
		// SHA256을 활용한 password 해싱
		try {
			encryptPassword = SHA256.encrypt(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// 회원가입
		userBO.addUser(loginId, encryptPassword, name, email);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,HttpServletRequest request
			){
		String encryptPassword = null;
		try {
			encryptPassword = SHA256.encrypt(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		User user =  userBO.selectUserByLoginIdAndPassword(loginId, encryptPassword);
		
		Map<String, String> result = new HashMap<>();
		
		if(user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("loginId", user.getLoginId());
			session.setAttribute("name", user.getName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("imagePath", user.getEmail());
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@RequestMapping("/get_all_user")
	public List<User> getAllUser(){
		
		List<User> userList =  userBO.getUserList();
		
		return userList;
	}
	
//	유저 정보 삭제
	@PostMapping("/delete_user")
	public Map<String, String> deleteUser(
			@RequestParam("id") Object userId
			){
		int id = Integer.parseInt(userId.toString());
		
		// id를 활용하여 user 정보 삭제
		userBO.deleteUserById(id);
		
		Map<String, String> result = new HashMap<>();
		return result;
	}

}
