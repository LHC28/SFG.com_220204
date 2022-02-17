package com.SFG.player.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatterStat {

	private int id;
	private int playerId;
	private int year;
	private String team;
	private int games;
	private int at_bats;
	private int runs;
	private int hits;
	private int doubles;
	private int triples;
	private int homerun;
	private int runs_batted_in;
	private int bases_on_balls;
	private int strikeouts;
	private int stolen_bases;
	private int hit_by_pitch;
	private int sacrifice_flys;
	// 직접 구하는 비율 스탯 
	private double batting_average;
	private double on_base_percentage;
	private double slugging_percentage;
	private double on_base_plus_slugging;
	
	private Date createdAt;
	private Date updatedAt;
}
