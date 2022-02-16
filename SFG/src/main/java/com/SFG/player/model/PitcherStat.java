package com.SFG.player.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PitcherStat {

	private int id;
	private int playerId;
	private int year;
	private String team;
	private int wins;
	private int losses;
	private double earned_run_average;
	private int games;
	private int game_started;
	private int saves;
	private int innings_pitched;
	private int hits;
	private int walks;
	private int strikeouts;
	private double whip;
}
