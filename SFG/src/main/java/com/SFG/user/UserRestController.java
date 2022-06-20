package com.SFG.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
	
	@Autowired
	public JavaMailSender javaMailSender;
	
//	아이디 중복여부 확인
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
	
//	회원가입
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
	
//	로그인 기능
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
	
//	이메일 인증 관련
	@PostMapping("/email_authentication")
	public Map<String, String> emailAuthentication(
			@RequestParam("email") String email
			){
		Map<String, String> result = new HashMap<>();
		
		Random ran = new Random();
		String num = "";
		for(int i=0; i<8; i++) {
			int n = ran.nextInt(10);
			num+=n;
		}
		
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom("zzangth94@naver.com");
		simpleMessage.setTo(email);
		simpleMessage.setSubject("sfg.com 이메일 인증 번호 알림");
		simpleMessage.setText("인증번호는 "+num+"입니다.");
		javaMailSender.send(simpleMessage);
		
		result.put("result", "success");
		// 이메일로 보낸 인증번호 넘기기
		result.put("num", num);
		return result;
	}
	
//	이름과 이메일을 활용한 아이디 찾기
	@PostMapping("/find_loginId")
	public Map<String, String> findLoginId(
			@RequestParam("name") String name
			,@RequestParam("email") String email
			){
		Map<String, String> result = new HashMap<>();
		
		String loginId = userBO.getUserLoginIdByNameAndEmail(name, email);
		
		if(loginId==null) {
			result.put("result", "fail");
		}else {
			result.put("result", "success");
		}
		System.out.println(loginId);
		result.put("loginId", loginId);
		
		return result;
	}
	
	// 이름, 로그인아이디, 이메일을 활용한 비밀번호 재설정
	@PostMapping("/find_password")
	public Map<String, String> findPassword(
			@RequestParam("name") String name
			,@RequestParam("loginId") String loginId
			,@RequestParam("email") String email
			){
		
		Map<String, String> result = new HashMap<>();
		
		User user = userBO.getUserByNameAndLoginIdAndEmail(name, loginId, email);
		
		if(user==null) {
			result.put("result", "fail");
		}else if(user!=null) {
			result.put("result", "success");
			result.put("loginId", user.getLoginId());
		}
		
		return result;
	}
	
	@PostMapping("/change_password")
	public Map<String, String> changePassword(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			){
		Map<String, String> result = new HashMap<>();
		System.out.println(loginId+" "+ password);
		String encryptPassword = null;
		try {
			encryptPassword = SHA256.encrypt(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		userBO.changePasswordByLoginId(loginId, encryptPassword);
		
		result.put("result", "success");
		return result;
	}

}
