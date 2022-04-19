package com.SFG.match;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SFG.match.BO.MatchBO;
import com.SFG.match.model.MatchSchedule;

@RequestMapping("/match")
@RestController
public class MatchRestController {

	@Autowired
	private MatchBO matchBO;
	
	@GetMapping("/get_matchSchedule")
	public List<MatchSchedule> getMatchSchedule(
			@RequestParam("month") int month
			){
		
		List<MatchSchedule> matchList = matchBO.getMatchScheduleByMonth(month);
		
		return matchList;
	}
	
	@PostMapping("/add_matchSchedule")
	public Map<String, String> addMatchSchedule(
			@RequestParam("yyyymmdd") String yyyymmdd
			,@RequestParam("homeTeamId") int homeTeamId
			,@RequestParam("awayTeamId") int awayTeamId
			,@RequestParam("homeScore") int homeScore
			,@RequestParam("awayScore") int awayScore
			,@RequestParam("result") String result
			,@RequestParam("time") String time
			){

		matchBO.addMatchSchedule(yyyymmdd, homeTeamId, awayTeamId, homeScore, awayScore, result, time);
		
		Map<String, String> result1 = new HashMap<>();
		result1.put("result", "success");
		return result1;
	}
	
	@PostMapping("/edit_matchSchedule")
	public Map<String, String> editMatchSchedule(
			@RequestParam("id") int id
			,@RequestParam("yyyymmdd") String yyyymmdd
			,@RequestParam("homeTeamId") int homeTeamId
			,@RequestParam("awayTeamId") int awayTeamId
			,@RequestParam("homeScore") int homeScore
			,@RequestParam("awayScore") int awayScore
			,@RequestParam("result") String result
			,@RequestParam("time") String time
			){
		
		matchBO.editMatchSchedule(id, yyyymmdd, homeTeamId, awayTeamId, homeScore, awayScore, result, time);
		
		Map<String, String> result1 = new HashMap<>();
		result1.put("result", "success");
		return result1;
	}
	
	@PostMapping("/delete_matchSchedule")
	public Map<String, String> deleteMatchSchedule(
			@RequestParam("id") int id
			){
		
		matchBO.deleteMatchScheduleById(id);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
}
