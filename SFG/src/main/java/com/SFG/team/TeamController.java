package com.SFG.team;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/team")
@Controller
public class TeamController {
	
	// 팀 소개 페이지
	@RequestMapping("/introduce_view")
	public String introduceView(Model model) {
		
		model.addAttribute("viewName", "team/teamIntroduceView");
		return "template/layout";
	}
	
	// 대표 선수 소개 페이지
	@RequestMapping("/represent_player_view")
	public String representPlayerView(
			Model model
			) {
		
		model.addAttribute("viewName", "team/representPlayerView");
		return "template/layout";
	}
}
