package com.SFG.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
}
