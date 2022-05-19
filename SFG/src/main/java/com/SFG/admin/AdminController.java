package com.SFG.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SFG.match.model.MatchSchedule;
import com.SFG.player.bo.PlayerBO;
import com.SFG.player.model.Player;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private PlayerBO playerBO;
	
//	admin 메인 페이지
	@RequestMapping("/main")
	public String main(
			Model model
			) {
		
		model.addAttribute("viewName", "admin/adminMain");
		return "template/layout";
	}
	
//	선수 및 코칭스태프 등록
	@RequestMapping("/player_info")
	public String playerInfo(
			Model model
			) {
		
		model.addAttribute("viewName", "admin/playerInfo");
		return "template/layout";
	}
	
//	타자 기록 등록
	@RequestMapping("/batter_info")
	public String batterInfo(
			Model model
			) {
		
		// 셀렉트 박스에 들어갈 타자 명단
		List<Player> batterList = playerBO.getAllBatterList();
				
		model.addAttribute("batterList", batterList);
		model.addAttribute("viewName", "admin/batterInfo");
		return "template/layout";
	}
	
//	투수 기록 등록
	@RequestMapping("/pitcher_info")
	public String pitcherInfo(
			Model model
			) {
		
		// 셀렉트 박스에 들어갈 투수 명단
		List<Player> pitcherList = playerBO.getAllPitcherList();
		
		model.addAttribute("pitcherList", pitcherList);
		model.addAttribute("viewName", "admin/pitcherInfo");
		return "template/layout";
	}
	
//	경기정보 페이지
	@RequestMapping("/game_info")
	public String gameInfo(
			Model model
			) {
		
		LocalDate curDate = LocalDate.now();
		DateTimeFormatter MonthFormatter = DateTimeFormatter.ofPattern("MM");
		int month = Integer.valueOf(curDate.format(MonthFormatter));
		
		model.addAttribute("curMonth", month);
		model.addAttribute("viewName", "admin/gameInfo");
		return "template/layout";
	}
	
//	선수 소개 입력 및 수정 페이지
	@RequestMapping("/player_introduce")
	public String playerIntroduce(
			Model model
			) {
		
		
		model.addAttribute("viewName", "admin/playerIntroduce");
		return "template/layout";
	}
	
//	메인페이지 수정용
//	@RequestMapping("/mainpage_info")
	
//	구단 페이지
//	@RequestMapping("/team_info")
	
	
//	팬페이지
//	@RequestMapping("/fan_info")
}
