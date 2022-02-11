package com.SFG.player;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/player")
@Controller
public class PlayerController {

	@RequestMapping("/coach_view")
	public String coachView(Model model) {
		
		model.addAttribute("viewName", "player/coachView");
		return "template/layout";
	}
	
	@RequestMapping("/pitcher_view")
	public String pitcherView(Model model) {
		
		model.addAttribute("viewName", "playerPitcherView");
		return "template/layout";
	}
}
