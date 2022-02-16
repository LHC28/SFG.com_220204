package com.SFG.player.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PitcherTotalStat {

	private int games;
	private int wins;
	private int losses;
	private double innings_pitched;
	private  double earned_run_average;
	private int strikeouts;
}
