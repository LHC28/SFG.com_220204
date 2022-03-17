package com.SFG.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

//	admin 메인 페이지
	@RequestMapping("/main")
	public String main(
			Model model
			) {
		
		model.addAttribute("viewName", "admin/adminMain");
		return "template/layout";
	}
	
//	회원정보
//	@RequestMapping("/user_info")
	
//	메인페이지 수정용
//	@RequestMapping("/mainpage_info")
	
//	구단 페이지
//	@RequestMapping("/team_info")
	
//	경기정보 페이지
//	@RequestMapping("game_info")
	
//	팬페이지
//	@RequestMapping("/fan_info")
}
