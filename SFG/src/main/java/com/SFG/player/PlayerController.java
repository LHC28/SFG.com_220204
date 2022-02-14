package com.SFG.player;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/player")
@Controller
public class PlayerController {

	// 코칭스태프
	@RequestMapping("/coach_view")
	public String coachView(Model model) {
		
		model.addAttribute("viewName", "player/coachView");
		return "template/layout";
	}
	
	//투수
	@RequestMapping("/pitcher_view")
	public String pitcherView(Model model) {
		
		model.addAttribute("viewName", "player/pitcherView");
		return "template/layout";
	}
	
	//내야수
	@RequestMapping("/in_fielder_view")
	public String inFielderView(Model model) {
		
		model.addAttribute("viewName", "player/inFielderView");
		return "template/layout";
	}
	
	//외야수
	@RequestMapping("/out_fielder_view")
	public String outFielderView(Model model) {
		
		model.addAttribute("viewName", "player/outFielderView");
		return "template/layout";
	}
	
	// 선수 세부 사항
	@RequestMapping("/player_detail_view")
	public String playerDetailView(Model model) {
		
		model.addAttribute("viewName", "player/playerDetailView");
		return "template/layout";
	}
}
