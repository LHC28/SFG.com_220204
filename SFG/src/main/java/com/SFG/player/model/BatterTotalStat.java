package com.SFG.player.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatterTotalStat {
	// 경기수 안타 홈런 타점 도루 타율
	private int games;
	private int hits;
	private int homerun;
	private int runs_batted_in;
	private int stolen_bases;
	private double batting_average;
	
}
