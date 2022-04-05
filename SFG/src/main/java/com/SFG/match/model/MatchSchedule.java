package com.SFG.match.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchSchedule {

	private int id;
	private Date yyyymmdd;
	private int day;
	private int homeTeamId;
	private String homeTeamLogo;
	private int awayTeamId;
	private String awayTeamLogo;
	private String stadium;
	private Integer homeScore;
	private Integer awayScore;
	private String result;
	private String time;
	
}
