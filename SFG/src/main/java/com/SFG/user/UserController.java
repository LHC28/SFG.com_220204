package com.SFG.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		
		model.addAttribute("viewName", "user/signInView");
		return "template/layout";
	}
	
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		
		model.addAttribute("viewName", "user/signUpView");
		return "template/layout";
	}
	
	@RequestMapping("/sign_out")
	public String signOut(
			HttpServletRequest request
			) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginId");
		session.removeAttribute("name");
		session.removeAttribute("email");
		session.removeAttribute("imagePath");
		session.removeAttribute("userId");
		
		return "redirect:/main/main_page";
	}
	
	@RequestMapping("/find_loginId")
	public String findLoginId(
			Model model
			) {
		
		model.addAttribute("viewName", "user/findLoginIdView");
		return "template/layout";
	}
	
	@RequestMapping("/find_password")
	public String findPassword(
			Model model
			) {
		
		model.addAttribute("viewName", "user/findPasswordView");
		return "template/layout";
	}
	
}
