package com.SFG.player.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.SFG.player.model.BatterStat;
import com.SFG.player.model.PitcherStat;
import com.SFG.player.model.Player;
import com.SFG.player.model.PlayerIntroduce;

@Repository
public interface PlayerDAO {
	
//	insert
	
	public void insertPlayer(
			@Param("name") String name
			,@Param("number") String number
			,@Param("position") String position
			,@Param("birth") String birth
			,@Param("debut") String debut
			,@Param("imagePath") String imagePath);
	
	public void insertBatterStatByPlayerId(
			@Param("playerId") int playerId
			,@Param("year") int year
			,@Param("team") String team
			,@Param("games") int games
			,@Param("at_bats") int at_bats
			,@Param("runs") int runs
			,@Param("hits") int hits
			,@Param("doubles") int doubles
			,@Param("triples") int triples
			,@Param("homerun") int homerun
			,@Param("runs_batted_in") int runs_batted_in
			,@Param("bases_on_balls") int bases_on_balls
			,@Param("strikeouts") int strikeouts
			,@Param("stolen_bases") int stolen_bases
			,@Param("hit_by_pitch") int hit_by_pitch
			,@Param("sacrifice_flys") int sacrifice_flys
			);
	
	public void insertPitcherStatByPlayerId(
			@Param("playerId") int playerId
			,@Param("year") int year
			,@Param("team") String team
			,@Param("wins") int wins
			,@Param("losses") int losses
			,@Param("earned_run_average") double earned_run_average
			,@Param("games") int games
			,@Param("game_started") int game_started
			,@Param("saves") int saves
			,@Param("hold") int hold
			,@Param("innings_pitched") double innings_pitched
			,@Param("hits") int hits
			,@Param("walks") int walks
			,@Param("strikeouts") int striketous
			,@Param("whip") double whip
			);

//	select
	
	public List<Player> selectAllPlayerList();
	
	public List<Player> selectCoachList();
	
	public List<Player> selectPitcherList();
	
	public List<Player> selectInfieldList();
	
	public List<Player> selectOutfieldList();
	
	// 선수 정보 가져오기
	public Player selectPlayer(int playerId);
	
	// 
	
	// 선수 소개 가져오기
	public PlayerIntroduce selectPlayerIntroduce(int playerId);
	
	// 투수 연도별 성적 가져오기
	public List<PitcherStat> selectPitcherStat(int playerId);
	
	// 타자 연도별 성적 가져오기
	public List<BatterStat> selectBatterStats(int playerId);
	
	// 타자 특정 선수 성적 리스트 가져오기
	public List<BatterStat> selectBatterStatListByPlayerId(int playerId);
	
//	update
	
//	타자 기록 수정
	public void updateBatterStatByPlayerId(
			@Param("playerId") int playerId
			,@Param("year") int year
			,@Param("team") String team
			,@Param("games") int games
			,@Param("at_bats") int at_bats
			,@Param("runs") int runs
			,@Param("hits") int hits
			,@Param("doubles") int doubles
			,@Param("triples") int triples
			,@Param("homerun") int homerun
			,@Param("runs_batted_in") int runs_batted_in
			,@Param("bases_on_balls") int bases_on_balls
			,@Param("strikeouts") int strikeouts
			,@Param("stolen_bases") int stolen_bases
			,@Param("hit_by_pitch") int hit_by_pitch
			,@Param("sacrifice_flys") int sacrifice_flys
			);
	
//	투수 기록 수정
	public void updatePitcherStatByPlayerId(
			@Param("playerId") int playerId
			,@Param("year") int year
			,@Param("team") String team
			,@Param("wins") int wins
			,@Param("losses") int losses
			,@Param("earned_run_average") double earned_run_average
			,@Param("games") int games
			,@Param("game_started") int game_started
			,@Param("saves") int saves
			,@Param("hold") int hold
			,@Param("innings_pitched") double innings_pitched
			,@Param("hits") int hits
			,@Param("walks") int walks
			,@Param("strikeouts") int striketous
			,@Param("whip") double whip
			);
	
//	delete
	
//	타자 기록 삭제
	public void deleteBatterStatById(int id);
	
//	투수 기록 삭제
	public void deletePitcherStatById(int id);
	
}
