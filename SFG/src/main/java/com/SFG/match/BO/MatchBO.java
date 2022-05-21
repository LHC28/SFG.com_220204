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
import com.SFG.team.dao.TeamDAO;
import com.SFG.team.model.Team;

@Service
public class MatchBO {
	
	@Autowired
	private MatchDAO matchDAO;
	
	@Autowired
	private TeamDAO teamDAO;
	
	// 경기일정 출력용
	public List<MatchSchedule> getMatchSchedule(Integer inputMonth) {
		
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
			// 첫일 구하기
			firstDate = targetDate.with(TemporalAdjusters.firstDayOfMonth());
			// 말일 구하기
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
		
		// 첫주 1일 이전의 남는 칸 채우기용(표만들 때 활용하기 위함.)
		for(int i=1; i<dayOfWeek; i++) {
			matchSchedule.add(null);
		}
		for(int i=firstDay; i<=lastDay; i++) {
			
			String Si = String.valueOf(i);
			if(i<10) {
//				DB에서 가져올 때 MM-dd형식으로 찾아오는데 0~9의 경우 00 01의 형식이 아닌 1 2가 되면 가져오지 못하게 된다.
//				따라서 문자로 바꾸어 앞에 0을 붙이는 방식을 활용한다.
				Si = "0"+Si;
			}
			
			MatchSchedule matchDay = matchDAO.selectMatchScheduleByMonthAndDay(month, Si);
			if(matchDay==null) {
				MatchSchedule nullMatchDay = new MatchSchedule();
				nullMatchDay.setDay(i);
				matchSchedule.add(nullMatchDay);
			}else {
				matchDay.setDay(i);
				Team homeTeam = teamDAO.selectTeamByTeamId(matchDay.getHomeTeamId());
				Team awayTeam = teamDAO.selectTeamByTeamId(matchDay.getAwayTeamId());
				matchDay.setHomeTeamLogo(homeTeam.getLogo());
				matchDay.setAwayTeamLogo(awayTeam.getLogo());
				matchDay.setHomeTeamName(homeTeam.getNameKor());
				matchDay.setAwayTeamName(awayTeam.getNameKor());
				matchDay.setStadium(homeTeam.getStadium());
				matchSchedule.add(matchDay);
			}
		}
		
		return matchSchedule;
	}
	
	// 관리자 페이지 경기일정 출력용
	public List<MatchSchedule> getMatchScheduleByMonth(int month) {
		String Smonth = String.valueOf(month);
		if(Smonth.length()==1) {
			Smonth = "0"+Smonth;
		}
		
		return matchDAO.selectMatchScheduleByMonth(Smonth);
	}
	
//	경기 정보 추가
	public void addMatchSchedule(String yyyymmdd, int homeTeamId, int awayTeamId, int homeScore, int awayScore, String result, String time) {
		matchDAO.insertMatchSchedule(yyyymmdd, homeTeamId, awayTeamId, homeScore, awayScore, result, time);
	}
	
//	경기 정보 수정
	public void editMatchSchedule(int rowId, String yyyymmdd, int homeTeamId, int awayTeamId, int homeScore, int awayScore, String result, String time) {
		matchDAO.updateMatchSchedule(rowId, yyyymmdd, homeTeamId, awayTeamId, homeScore, awayScore, result, time);
	}
	
//	경기 정보 삭제
	public void deleteMatchScheduleById(int id) {
		matchDAO.deleteMatchScheduleById(id);
	}
	
}
