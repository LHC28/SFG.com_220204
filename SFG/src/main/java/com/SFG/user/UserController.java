package com.SFG.user;

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
}
