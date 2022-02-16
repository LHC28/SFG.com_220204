package com.SFG.player.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFG.player.dao.PlayerDAO;
import com.SFG.player.model.PitcherStat;
import com.SFG.player.model.PitcherTotalStat;
import com.SFG.player.model.Player;
import com.SFG.player.model.PlayerIntroduce;

@Service
public class PlayerBO {

	@Autowired
	private PlayerDAO playerDAO;
	
	public List<Player> getCoachList(){
		return playerDAO.selectCoachList();
	}
	
	public List<Player> getPitcherList(){
		return playerDAO.selectPitcherList();
	}
	
	public List<Player> getInfieldList(){
		return playerDAO.selectInfieldList();
	}
	
	public List<Player> getOutfieldList(){
		return playerDAO.selectOutfieldList();
	}
	
	//선수 정보 가져오기
	public Player getPlayer(int playerId) {
		return playerDAO.selectPlayer(playerId);
	}
	
	// 선수 소개 가져오기
	public PlayerIntroduce getPlayerIntroduce(int playerId) {
		return playerDAO.selectPlayerIntroduce(playerId);
	}
	
	// 투수 관련
	
	// 투수 연도별 기록 가져오기
	public List<PitcherStat> getPitcherStats(int playerId){
		return playerDAO.selectPitcherStat(playerId);
	}
	
	// 투수 통산 기록 가져오기
	public PitcherTotalStat getpitcherTotalStat(int playerId) {
		// 해당 투수 모든 기록 가져오기
		List<PitcherStat> pitcherStats = playerDAO.selectPitcherStat(playerId);
		// 해당 투수 통산 성적 구하기
		PitcherTotalStat pitcherTotalStat = new PitcherTotalStat();
		int games = 0;
		int wins = 0;
		int losses = 0;
		double innings_pitched = 0;
		double earned_run_average = 0;
		int strikeouts = 0;
		for(int i=0; i<pitcherStats.size(); i++) {
			games+=pitcherStats.get(i).getGames();
			wins+=pitcherStats.get(i).getWins();
			losses+=pitcherStats.get(i).getLosses();
			innings_pitched+=pitcherStats.get(i).getInnings_pitched();
			earned_run_average+=pitcherStats.get(i).getEarned_run_average();
			strikeouts+=pitcherStats.get(i).getStrikeouts();
		}
		pitcherTotalStat.setGames(games);
		pitcherTotalStat.setWins(wins);
		pitcherTotalStat.setLosses(losses);
		pitcherTotalStat.setInnings_pitched(innings_pitched);
		pitcherTotalStat.setEarned_run_average(earned_run_average);
		pitcherTotalStat.setStrikeouts(strikeouts);
		
		return pitcherTotalStat;
	}

}
