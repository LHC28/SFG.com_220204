package com.SFG.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SFG.player.bo.PlayerBO;
import com.SFG.player.model.BatterStat;
import com.SFG.player.model.Player;

@RequestMapping("/player")
@RestController
public class PlayerRestController {

	@Autowired
	private PlayerBO playerBO;
	
//	모든 선수 data 가져오기
	@RequestMapping("/get_all_player")
	public List<Player> getAllPlayer() {
		
		List<Player> playerList = playerBO.getAllPlayerList();
		
		
		return playerList;
	}
	
//	선수 및 코칭 스태프 추가
	@PostMapping("/add_player")
	public Map<String, String> addPlayer(
			@RequestParam("name") String name
			,@RequestParam("number") String number
			,@RequestParam("position") String position
			,@RequestParam("birth") String birth
			,@RequestParam("debut") String debut
			,@RequestParam("imagePath") String imagePath
			) {
		
		Map<String, String> result = new HashMap<>();
		
		playerBO.addPlayer(name, number, position, birth, debut, imagePath);
		
		result.put("result", "success");
		return result;
	}
	
//	타자 (내야수 + 외야수) data 가져오기
	@RequestMapping("/get_batter_stat")
	public List<BatterStat> getBatterStat(
			@RequestParam(required=false) Integer playerId,
			Model model
			){
		
		
		// 특정 선수 성적 가져오기
		List<BatterStat> batterStatList = new ArrayList<>();
		if(playerId==null) {
			playerId = 3;
			batterStatList = playerBO.getBatterStatListByPlayerId(playerId);
		}else {
			batterStatList = playerBO.getBatterStatListByPlayerId(playerId);
		}
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		
		model.addAttribute("batterStatList", batterStatList);
		
		return batterStatList;
	}
	
//	타자 스탯 data 추가
	@PostMapping("/add_batter_stat")
	public Map<String, String> addBatterStat(
			@RequestParam("playerId") String SplayerId
			,@RequestParam("year") String Syear
			,@RequestParam("team") String team
			,@RequestParam("games") String Sgames
			,@RequestParam("at_bats") String Sat_bats
			,@RequestParam("runs") String Sruns
			,@RequestParam("hits") String Shits
			,@RequestParam("doubles") String Sdoubles
			,@RequestParam("triples") String Striples
			,@RequestParam("homerun") String Shomerun
			,@RequestParam("runs_batted_in") String Sruns_batted_in
			,@RequestParam("bases_on_balls") String Sbases_on_balls
			,@RequestParam("strikeouts") String Sstrikeouts
			,@RequestParam("stolen_bases") String Sstolen_bases
			,@RequestParam("hit_by_pitch") String Shit_by_pitch
			,@RequestParam("sacrifice_flys") String Ssacrifice_flys
			){
		// String으로 받아오지 않으면 에러가 발생
		int playerId = Integer.valueOf(SplayerId);
		int year = Integer.valueOf(Syear);
		int games = Integer.valueOf(Sgames);
		int at_bats = Integer.valueOf(Sat_bats);
		int runs = Integer.valueOf(Sruns);
		int hits = Integer.valueOf(Shits);
		int doubles = Integer.valueOf(Sdoubles);
		int triples = Integer.valueOf(Striples);
		int homerun = Integer.valueOf(Shomerun);
		int runs_batted_in = Integer.valueOf(Sruns_batted_in);
		int bases_on_balls = Integer.valueOf(Sbases_on_balls);
		int strikeouts = Integer.valueOf(Sstrikeouts);
		int stolen_bases = Integer.valueOf(Sstolen_bases);
		int hit_by_pitch = Integer.valueOf(Shit_by_pitch);
		int sacrifice_flys = Integer.valueOf(Ssacrifice_flys);
		playerBO.addBatterStatByPlayerId(playerId, year, team, games, at_bats, runs, hits, doubles, triples, homerun, runs_batted_in, bases_on_balls, strikeouts, stolen_bases, hit_by_pitch, sacrifice_flys);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
//	타자 스탯 수정
	@PostMapping("/edit_batter_stat")
	public Map<String, String> editBatterStat(
			@RequestParam("playerId") String SplayerId
			,@RequestParam("year") String Syear
			,@RequestParam("team") String team
			,@RequestParam("games") String Sgames
			,@RequestParam("at_bats") String Sat_bats
			,@RequestParam("runs") String Sruns
			,@RequestParam("hits") String Shits
			,@RequestParam("doubles") String Sdoubles
			,@RequestParam("triples") String Striples
			,@RequestParam("homerun") String Shomerun
			,@RequestParam("runs_batted_in") String Sruns_batted_in
			,@RequestParam("bases_on_balls") String Sbases_on_balls
			,@RequestParam("strikeouts") String Sstrikeouts
			,@RequestParam("stolen_bases") String Sstolen_bases
			,@RequestParam("hit_by_pitch") String Shit_by_pitch
			,@RequestParam("sacrifice_flys") String Ssacrifice_flys
			){
		
		int playerId = Integer.valueOf(SplayerId);
		int year = Integer.valueOf(Syear);
		int games = Integer.valueOf(Sgames);
		int at_bats = Integer.valueOf(Sat_bats);
		int runs = Integer.valueOf(Sruns);
		int hits = Integer.valueOf(Shits);
		int doubles = Integer.valueOf(Sdoubles);
		int triples = Integer.valueOf(Striples);
		int homerun = Integer.valueOf(Shomerun);
		int runs_batted_in = Integer.valueOf(Sruns_batted_in);
		int bases_on_balls = Integer.valueOf(Sbases_on_balls);
		int strikeouts = Integer.valueOf(Sstrikeouts);
		int stolen_bases = Integer.valueOf(Sstolen_bases);
		int hit_by_pitch = Integer.valueOf(Shit_by_pitch);
		int sacrifice_flys = Integer.valueOf(Ssacrifice_flys);
		
		playerBO.editBatterStatByPlayerId(playerId, year, team, games, at_bats, runs, hits, doubles, triples, homerun, runs_batted_in, bases_on_balls, strikeouts, stolen_bases, hit_by_pitch, sacrifice_flys);
		
		Map<String, String> result = new HashMap<>();
		
		return result;
	}
	
//	타자 기록 삭제
	@PostMapping("/delete_batter_stat")
	public Map<String, String> deleteBatterStat(
			@RequestParam("account") int id
			){
		
		//int id = Integer.parseInt(statId);
		System.out.println(id);
		playerBO.deleteBatterStatById(id);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
}
