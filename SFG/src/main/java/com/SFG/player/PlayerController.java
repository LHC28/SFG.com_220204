package com.SFG.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SFG.player.bo.PlayerBO;
import com.SFG.player.model.Player;

@RequestMapping("/player")
@Controller
public class PlayerController {

	@Autowired
	private PlayerBO playerBO;
	
	// 코칭스태프
	@RequestMapping("/coach_view")
	public String coachView(Model model) {
		
		// 코치리스트 가져오기
		List<Player> coachList =  playerBO.getCoachList();
		
		model.addAttribute("coachList", coachList);
		model.addAttribute("viewName", "player/coachView");
		return "template/layout";
	}
	
	//투수
	@RequestMapping("/pitcher_view")
	public String pitcherView(Model model) {
		//투수리스트 가져오기
		List<Player> pitcherList = playerBO.getPitcherList();
		
		model.addAttribute("pitcherList", pitcherList);
		model.addAttribute("viewName", "player/pitcherView");
		return "template/layout";
	}
	
	//내야수
	@RequestMapping("/in_fielder_view")
	public String inFielderView(Model model) {
		// 내야수 리스트 가져오기
		List<Player> infieldList = playerBO.getInfieldList();
		
		model.addAttribute("infieldList", infieldList);
		model.addAttribute("viewName", "player/inFielderView");
		return "template/layout";
	}
	
	//외야수
	@RequestMapping("/out_fielder_view")
	public String outFielderView(Model model) {
		// 외야수 리스트 가져오기
		List<Player> outfieldList = playerBO.getOutfieldList();
		
		model.addAttribute("outfieldList", outfieldList);
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
