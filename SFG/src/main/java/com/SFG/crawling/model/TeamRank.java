package com.SFG.crawling.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamRank {

	private String teamName;
	private int rank;
	private int games;
	private int wins;
	private int loses;
	private double winsRate;
	// 승차
	private double gamesBehind;
}
