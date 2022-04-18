package com.SFG.player.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFG.player.dao.PlayerDAO;
import com.SFG.player.model.BatterStat;
import com.SFG.player.model.BatterTotalStat;
import com.SFG.player.model.PitcherStat;
import com.SFG.player.model.PitcherTotalStat;
import com.SFG.player.model.Player;
import com.SFG.player.model.PlayerIntroduce;

@Service
public class PlayerBO {

	@Autowired
	private PlayerDAO playerDAO;
	
	public void addPlayer(String name, String number, String position, String birth, String debut, String imagePath) {
		playerDAO.insertPlayer(name, number, position, birth, debut, imagePath);
	}
	
	public List<Player> getAllPlayerList(){
		return playerDAO.selectAllPlayerList();
	}
	
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
	
//	선수 리스트 가져오기(타자)
	public List<Player> getAllBatterList(){
		
		List<Player> infielderList = playerDAO.selectInfieldList();
		List<Player> outfielderList = playerDAO.selectOutfieldList();
		
		List<Player> batterList = new ArrayList<>();
		for(int i=0; i<infielderList.size(); i++) {
			batterList.add(infielderList.get(i));
		}
		for(int i=0; i<outfielderList.size(); i++) {
			batterList.add(outfielderList.get(i));
		}
		
		return batterList;
	}
	
	// 선수 소개 가져오기
	public PlayerIntroduce getPlayerIntroduce(int playerId) {
		return playerDAO.selectPlayerIntroduce(playerId);
	}
	
//	타자 선수 기록 가져오기
	public List<BatterStat> getBatterStatListByPlayerId(int playerId){
		return playerDAO.selectBatterStatListByPlayerId(playerId);
	}
	
//	타자 기록 추가
	public void addBatterStatByPlayerId(int playerId, int year, String team, int games, int at_bats, int runs, int hits, int doubles, int triples, int homerun, int runs_batted_in, int bases_on_balls, int strikeouts, int stolen_bases, int hit_by_pitch, int sacrifice_flys) {
		playerDAO.insertBatterStatByPlayerId(playerId, year, team, games, at_bats, runs, hits, doubles, triples, homerun, runs_batted_in, bases_on_balls, strikeouts, stolen_bases, hit_by_pitch, sacrifice_flys);
	}
	
//	타자 기록 수정
	public void editBatterStatByPlayerId(int playerId, int year, String team, int games, int at_bats, int runs, int hits, int doubles, int triples, int homerun, int runs_batted_in, int bases_on_balls, int strikeouts, int stolen_bases, int hit_by_pitch, int sacrifice_flys) {
		playerDAO.updateBatterStatByPlayerId(playerId, year, team, games, at_bats, runs, hits, doubles, triples, homerun, runs_batted_in, bases_on_balls, strikeouts, stolen_bases, hit_by_pitch, sacrifice_flys);
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
			// 경기수
			games+=pitcherStats.get(i).getGames();
			//승
			wins+=pitcherStats.get(i).getWins();
			//패
			losses+=pitcherStats.get(i).getLosses();
			//이닝
			int num = (int)innings_pitched+(int)pitcherStats.get(i).getInnings_pitched();
			double demical = (innings_pitched-(int)innings_pitched)+(pitcherStats.get(i).getInnings_pitched()-(int)pitcherStats.get(i).getInnings_pitched());
			innings_pitched=0;
			innings_pitched=num+(int)(demical/0.3)+(demical%0.3);
			//평균자책점
			earned_run_average+=pitcherStats.get(i).getEarned_run_average();
			//삼진
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

	// 타자 연도별 기록 가져오기
	public List<BatterStat> getBatterStats(int playerId){
		// 비율 스탯 계산하여 넣어줘야 한다.
		List<BatterStat> batterStats = playerDAO.selectBatterStats(playerId);
		
		for(int i=0; i<batterStats.size(); i++) {
			// 타율
			double batting_average = (double)batterStats.get(i).getHits()/(double)batterStats.get(i).getAt_bats();
			// 출루율
			double on_base_percentage = ((double)batterStats.get(i).getHits()+(double)batterStats.get(i).getBases_on_balls()+(double)batterStats.get(i).getHit_by_pitch())/((double)batterStats.get(i).getAt_bats()+(double)batterStats.get(i).getBases_on_balls()+(double)batterStats.get(i).getHit_by_pitch()+(double)batterStats.get(i).getSacrifice_flys());
			// 장타율
			double slugging_percentage = (((double)batterStats.get(i).getHits()-(double)batterStats.get(i).getDoubles()-(double)batterStats.get(i).getTriples()-(double)batterStats.get(i).getHomerun())+(double)batterStats.get(i).getDoubles()*2+(double)batterStats.get(i).getTriples()*3+(double)batterStats.get(i).getHomerun()*4)/(double)batterStats.get(i).getAt_bats();
			// OPS
			double on_base_plus_slugging = on_base_percentage + slugging_percentage; 
			// 소수점 아래 세번째자리까지 남기기
			batting_average = (double)Math.round(batting_average*1000)/1000;
			on_base_percentage = (double)Math.round(on_base_percentage*1000)/1000;
			slugging_percentage = (double)Math.round(slugging_percentage*1000)/1000;
			on_base_plus_slugging = (double)Math.round(on_base_plus_slugging*1000)/1000;
			batterStats.get(i).setBatting_average(batting_average);
			batterStats.get(i).setOn_base_percentage(on_base_percentage);
			batterStats.get(i).setSlugging_percentage(slugging_percentage);
			batterStats.get(i).setOn_base_plus_slugging(on_base_plus_slugging);
		}
		
		return batterStats;
	}
	
	// 타자 통산 기록 가져오기
	// games hits homerun runs_batted_in stolen_bases batting_average
	public BatterTotalStat getBatterTotalStat(int playerId) {
		BatterTotalStat batterTotalStat = new BatterTotalStat();
		//연도별 성적 가져오기
		List<BatterStat> batterStats = playerDAO.selectBatterStats(playerId);
		// 필요한 통산기록만 계산하기
		int games=0; int hits=0; int homerun=0; int runs_batted_in = 0; int stolen_bases=0;
		// 타율에 필요한 값
		int at_bats = 0;
		// 합치기
		for(int i=0; i<batterStats.size(); i++) {
			games+= batterStats.get(i).getGames();
			hits+= batterStats.get(i).getHits();
			homerun+= batterStats.get(i).getHomerun();
			runs_batted_in+= batterStats.get(i).getRuns_batted_in();
			stolen_bases+= batterStats.get(i).getStolen_bases();
			at_bats+= batterStats.get(i).getAt_bats();
			System.out.println(batterStats.get(i).getAt_bats());
		}
		
		double batting_average = (double)hits/(double)at_bats;
		batting_average = (double)Math.round(batting_average*1000)/1000;
		batterTotalStat.setGames(games);
		batterTotalStat.setHits(hits);
		batterTotalStat.setHomerun(homerun);
		batterTotalStat.setRuns_batted_in(runs_batted_in);
		batterTotalStat.setStolen_bases(stolen_bases);
		batterTotalStat.setBatting_average(batting_average);
		
		return batterTotalStat;
	}

}
