package com.SFG.match;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SFG.match.BO.MatchBO;
import com.SFG.match.model.MatchSchedule;

@RequestMapping("/match")
@Controller
public class MatchController {

	@Autowired
	private MatchBO matchBO;
	
	@RequestMapping("/match_result_view")
	public String matchResultView(
			Model model,
			@RequestParam(required=false) Integer inputMonth
			) {
//		일자를 맞춰 가져오도록 할 예정(LocalDate 활용)
		List<MatchSchedule> matchSchedule = matchBO.getMatchSchedule(inputMonth);
		
		model.addAttribute("matchSchedule", matchSchedule);
		model.addAttribute("viewName", "match/resultView");
		return "template/layout";
	}
}
