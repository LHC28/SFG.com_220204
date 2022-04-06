package com.SFG.match.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchSchedule {

	private int id;
	// 경기 날짜
	private Date yyyymmdd;
	// 경기 일자
	private int day;
	// 홈팀 id
	private int homeTeamId;
	// 홈팀 이름
	private String homeTeamName;
	// 홈팀 로고
	private String homeTeamLogo;
	// 원정팀 id
	private int awayTeamId;
	// 원정팀 이름
	private String awayTeamName;
	// 원정팀 로고
	private String awayTeamLogo;
	// 경기장
	private String stadium;
	// 홈팀 점수
	private Integer homeScore;
	// 원정팀 점수
	private Integer awayScore;
	// 경기 결과
	private String result;
	// 경기 시간
	private String time;
	
}
