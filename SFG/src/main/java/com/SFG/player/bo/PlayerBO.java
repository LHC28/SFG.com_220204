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
	
//	player 수정
	public void editPlayer(int id, String name, int number, String position, String birth, String debut, String imagePath) {
		playerDAO.editPlayer(id, name, number, position, birth, debut, imagePath);
	}
	
//	player 삭제
	public void deletePlayer(int id) {
		playerDAO.deletePlayer(id);
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
	public void editBatterStatById(int id, int year, String team, int games, int at_bats, int runs, int hits, int doubles, int triples, int homerun, int runs_batted_in, int bases_on_balls, int strikeouts, int stolen_bases, int hit_by_pitch, int sacrifice_flys) {
		playerDAO.updateBatterStatById(id, year, team, games, at_bats, runs, hits, doubles, triples, homerun, runs_batted_in, bases_on_balls, strikeouts, stolen_bases, hit_by_pitch, sacrifice_flys);
	}
	
//	타자 기록 삭제
	public void deleteBatterStatById(int id) {
		playerDAO.deleteBatterStatById(id);
	}
	
	
	// 투수 관련
	
//	선수 리스트 가져오기(투수)
	public List<Player> getAllPitcherList(){
		return playerDAO.selectPitcherList();
	}
	
	// 투수 연도별 기록 가져오기
	public List<PitcherStat> getPitcherStats(int playerId){
		return playerDAO.selectPitcherStat(playerId);
	}
	
//	투수 기록 추가
	public void addPitcherStatByPlayerId(int playerId, int year, String team, int wins, int losses, double earned_run_average, int games, int game_started, int saves, int hold, double innings_pitched, int hits, int earned_runs, int walks, int strikeouts, double whip) {
		playerDAO.insertPitcherStatByPlayerId(playerId, year, team, wins, losses, earned_run_average, games, game_started, saves, hold, innings_pitched, hits, earned_runs, walks, strikeouts, whip);
	}
	
//	투수 기록 수정
	public void editPitcherStatByPlayerId(int id, int playerId, int year, String team, int wins, int losses, double earned_run_average, int games, int game_started, int saves, int hold, double innings_pitched, int hits, int earned_runs, int walks, int strikeouts, double whip) {
		playerDAO.updatePitcherStatByPlayerId(id, playerId, year, team, wins, losses, earned_run_average, games, game_started, saves, hold, innings_pitched, hits, earned_runs, walks, strikeouts, whip);
	}
	
//	투수 기록 삭제
	public void deletePitcherStatById(int id) {
		playerDAO.deletePitcherStatById(id);
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
		// 이닝수
		double innings_pitched = 0;
//		자책점
		int earned_runs = 0;
//		평균자책점
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
			// 자연수 부분
			int num = (int)innings_pitched+(int)pitcherStats.get(i).getInnings_pitched();
			// 소수 아래 부분
			double demical = (innings_pitched-(int)innings_pitched)+(pitcherStats.get(i).getInnings_pitched()-(int)pitcherStats.get(i).getInnings_pitched());
			innings_pitched=0;
			innings_pitched=num+(int)(demical/0.3)+(demical%0.3);
			//평균자책점
			earned_runs+=pitcherStats.get(i).getEarned_runs();
			
			//삼진
			strikeouts+=pitcherStats.get(i).getStrikeouts();
		}
		// 전체 시즌 기준 평균자책점 (소수 아래 둘째자리까지)
		earned_run_average = Math.round((9*earned_runs)/innings_pitched*100)/100.0;
		
		innings_pitched = Math.round(innings_pitched*10)/10.0;
		
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

//			비율 성적 값 넣기 - 타율, 출루율, 장타율, OPS
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
	
	// 선수 소개 관련
	public List<PlayerIntroduce> getAllPlayerIntroduceList(){
		
		return playerDAO.selectAllPlayerIntroduceList();
	}
	
//	선수 소개 수정
	public void editPlayerIntroduce(int id, int playerId, String title, String content, String imagePath) {
		playerDAO.updatePlayerIntroduce(id, playerId, title, content, imagePath);
	}
	
//	선수 소개 추가
	public void addPlayerIntroduce(int playerId, String title, String content, String imagePath) {
		playerDAO.insertPlayerIntroduce(playerId, title, content, imagePath);
	}
	
//	선수 소개 삭제
	public void deletePlayerIntroduce(int id) {
		playerDAO.deletePlayerIntroduce(id);
	}

}
