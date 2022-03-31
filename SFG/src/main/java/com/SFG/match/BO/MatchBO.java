package com.SFG.match.BO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFG.match.dao.MatchDAO;
import com.SFG.match.model.MatchSchedule;

@Service
public class MatchBO {
	
	@Autowired
	private MatchDAO matchDAO;
	
	public List getMatchSchedule(Integer inputMonth) {
		
		String month = null;
		LocalDate firstDate = null;
		LocalDate lastDate = null;
		int year = 2022;
		int firstDay = 0;
		int lastDay = 0;
		int date = 0;
		
		if(inputMonth==null) {
			// 현재 날짜를 가지고 월 가져오기
			LocalDate curDate = LocalDate.now();
			
			// 첫일
			firstDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
			// 말일
			lastDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
			
			// 월 가져오기
			DateTimeFormatter MonthFormatter = DateTimeFormatter.ofPattern("MM");
			// 타입  - String
			month = curDate.format(MonthFormatter);
		}else {
			// get으로 받아온 월
			LocalDate targetDate = LocalDate.of(2022, inputMonth,1);
			// 첫일
			firstDate = targetDate.with(TemporalAdjusters.firstDayOfMonth());
			// 말일
			lastDate = targetDate.with(TemporalAdjusters.lastDayOfMonth());
			
			// 월 가져오기
			DateTimeFormatter MonthFormatter = DateTimeFormatter.ofPattern("MM");
			month = targetDate.format(MonthFormatter);
		}
		
		// 날짜수
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
		firstDay = Integer.parseInt(firstDate.format(dayFormatter));
		lastDay = Integer.parseInt(lastDate.format(dayFormatter));
		date = lastDay-firstDay+1;
		
		// 숫자로 요일 가져오기
		int dayOfWeek = firstDate.getDayOfWeek().getValue();
		
		List<MatchSchedule> matchSchedule = new ArrayList<>();
		
		for(int i=1; i<dayOfWeek; i++) {
			matchSchedule.add(null);
		}
		for(int i=firstDay; i<=lastDay; i++) {
			MatchSchedule matchDay = matchDAO.selectMatchScheduleByMonthAndDay(month, i);
			if(matchDay==null) {
				matchSchedule.add(null);
			}else {
				matchSchedule.add(matchDay);
			}
		}
		
		return matchSchedule;
	}
}
