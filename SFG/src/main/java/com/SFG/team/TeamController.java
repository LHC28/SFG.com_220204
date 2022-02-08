package com.SFG.team;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/team")
@Controller
public class TeamController {
	
	@RequestMapping("/introduce_view")
	public String introduceView(Model model) {
		
		model.addAttribute("viewName", "team/teamIntroduceView");
		return "template/layout";
	}
}
