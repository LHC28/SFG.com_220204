package com.SFG.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class MainController {

	@RequestMapping("/entrance")
	public String entrance() {
		return "main/entrance";
	}
	
	@RequestMapping("/main_page")
	public String mainPage(Model model) {
		
		model.addAttribute("viewName", "include/mainPage");
		return "template/layout";
	}
}
