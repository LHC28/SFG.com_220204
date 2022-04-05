package com.SFG.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SFG.match.BO.MatchBO;
import com.SFG.match.model.MatchSchedule;

@RequestMapping("/main")
@Controller
public class MainController {

	@Autowired
	private MatchBO matchBO;
	
	@RequestMapping("/entrance")
	public String entrance() {
		return "main/entrance";
	}
	
	@RequestMapping("/main_page")
	public String mainPage(Model model) {
		
		MatchSchedule match = new MatchSchedule();
		
		// 오늘 날짜 가져오기 (월, 일 별개로)
		LocalDate curDate = LocalDate.now();
		DateTimeFormatter MonthFormatter = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
		int month = Integer.valueOf(curDate.format(MonthFormatter));
		int day = Integer.valueOf(curDate.format(dayFormatter));
		// 경기 일정 가져오기
		List<MatchSchedule> matchSchedule = matchBO.getMatchSchedule(month);
		for(int i=day; i<=matchSchedule.size(); i++) {
			if(matchSchedule.get(i).getId()!=0) {
				match = matchSchedule.get(i);
				break;
			}
		}
		if(match.getId()==0) {
			while(true) {
				// 오늘 기준 다음 월로...
				month++;
				// 12월 이후로 넘어가면 반복 중지
				if(month>12) {
					break;
				}
				matchSchedule = matchBO.getMatchSchedule(month);
				for(int i=1; i<=matchSchedule.size(); i++) {
					if(matchSchedule.get(i).getId()!=0) {
						match = matchSchedule.get(i);
						break;
					}
				}
				// 다음 경기가 있으면 중단.
				if(match.getId()!=0) {
					break;
				}
			}
		}
		
		
		model.addAttribute("match", match);
		model.addAttribute("viewName", "include/mainPage");
		return "template/layout";
	}
}
