package com.SFG.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SFG.player.bo.PlayerBO;
import com.SFG.player.model.BatterStat;
import com.SFG.player.model.BatterTotalStat;
import com.SFG.player.model.PitcherStat;
import com.SFG.player.model.PitcherTotalStat;
import com.SFG.player.model.Player;
import com.SFG.player.model.PlayerIntroduce;

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
		
		// 이름이 긴 경우 두 줄로 나타날 수 있어 성만 나오도록 한다.
		for(int i=0; i<pitcherList.size(); i++) {
			if(pitcherList.get(i).getName().length()>=16) {
				String[] name = pitcherList.get(i).getName().split(" ");
				pitcherList.get(i).setName(name[1]);
			}
		}
		
		model.addAttribute("pitcherList", pitcherList);
		model.addAttribute("viewName", "player/pitcherView");
		return "template/layout";
	}
	
	//내야수
	@RequestMapping("/in_fielder_view")
	public String inFielderView(Model model) {
		// 내야수 리스트 가져오기
		List<Player> infieldList = playerBO.getInfieldList();
		
		// 이름이 긴 경우 두 줄로 나타날 수 있어 성만 나오도록 한다.
		for(int i=0; i<infieldList.size(); i++) {
			if(infieldList.get(i).getName().length()>=16) {
				String[] name = infieldList.get(i).getName().split(" ");
				infieldList.get(i).setName(name[1]);
			}
		}
		
		model.addAttribute("infieldList", infieldList);
		model.addAttribute("viewName", "player/inFielderView");
		return "template/layout";
	}
	
	//외야수
	@RequestMapping("/out_fielder_view")
	public String outFielderView(Model model) {
		// 외야수 리스트 가져오기
		List<Player> outfieldList = playerBO.getOutfieldList();
		
		// 이름이 긴 경우 두 줄로 나타날 수 있어 성만 나오도록 한다.
				for(int i=0; i<outfieldList.size(); i++) {
					if(outfieldList.get(i).getName().length()>=16) {
						String[] name = outfieldList.get(i).getName().split(" ");
						outfieldList.get(i).setName(name[1]);
					}
				}
		
		model.addAttribute("outfieldList", outfieldList);
		model.addAttribute("viewName", "player/outFielderView");
		return "template/layout";
	}
	
	// 선수 세부 사항
	@RequestMapping("/player_detail_view")
	public String playerDetailView(
			Model model,
			@RequestParam("playerId") int playerId
			) {
		// 선수 정보 가져오기
		Player player = playerBO.getPlayer(playerId);
		// 선수 소개 정보 가져오기
		PlayerIntroduce playerIntroduce = playerBO.getPlayerIntroduce(playerId);
		
		if(player.getPosition().equals("pitcher")) {
			// 투수 통산기록 가져오기
			PitcherTotalStat pitcherTotalStat = playerBO.getpitcherTotalStat(playerId);
			// 투수 연도별 기록 가져오기
			List<PitcherStat> pitcherStats =  playerBO.getPitcherStats(playerId);
			model.addAttribute("pitcherStats", pitcherStats);
			model.addAttribute("pitcherTotalStat", pitcherTotalStat);
		}
		else if(player.getPosition().contains("infielder") || player.getPosition().contains("outfielder")) {
			// 타자 통산 기록 가져오기
			BatterTotalStat batterTotalStat = playerBO.getBatterTotalStat(playerId);
			// 타자 연도별 기록 가져오기
			List<BatterStat> batterStats = playerBO.getBatterStats(playerId);
			
			model.addAttribute("batterTotalStat", batterTotalStat);
			model.addAttribute("batterStats", batterStats);
		}
		
		model.addAttribute("player", player);
		model.addAttribute("playerIntroduce", playerIntroduce);
		model.addAttribute("viewName", "player/playerDetailView");
		return "template/layout";
	}
}
