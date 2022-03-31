package com.SFG.match.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchSchedule {

	private int id;
	private Date yyyymmdd;
	private int homeTeamId;
	private int awayTeamId;
	private Integer homeScore;
	private Integer awayScore;
	private String result;
}
