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
			,@Param("earned_runs") int earned_runs
			,@Param("walks") int walks
			,@Param("strikeouts") int striketous
			,@Param("whip") double whip
			);
	
	public void insertPlayerIntroduce(
			@Param("playerId") int playerId
			,@Param("title") String title
			,@Param("content") String content
			,@Param("imagePath") String imagePath
			);

//	select
	
	public List<Player> selectAllPlayerList();
	
	public List<Player> selectCoachList();
	
	public List<Player> selectPitcherList();
	
	public List<Player> selectInfieldList();
	
	public List<Player> selectOutfieldList();
	
	public List<PlayerIntroduce> selectAllPlayerIntroduceList();
	
	// ?????? ?????? ????????????
	public Player selectPlayer(int playerId);
	
	// 
	
	// ?????? ?????? ????????????
	public PlayerIntroduce selectPlayerIntroduce(int playerId);
	
	// ?????? ????????? ?????? ????????????
	public List<PitcherStat> selectPitcherStat(int playerId);
	
	// ?????? ????????? ?????? ????????????
	public List<BatterStat> selectBatterStats(int playerId);
	
	// ?????? ?????? ?????? ?????? ????????? ????????????
	public List<BatterStat> selectBatterStatListByPlayerId(int playerId);
	
//	update
	
//	?????? ??? ??????????????? ??????
	public void editPlayer(
			@Param("id") int id
			,@Param("name") String name
			,@Param("number") int number
			,@Param("position") String position
			,@Param("birth") String birth
			,@Param("debut") String debut
			,@Param("imagePath") String imagePath);
	
//	?????? ?????? ??????
	public void updateBatterStatById(
			@Param("id") int id
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
	
//	?????? ?????? ??????
	public void updatePitcherStatByPlayerId(
			@Param("id") int id
			,@Param("playerId") int playerId
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
			,@Param("earned_runs") int earned_runs
			,@Param("walks") int walks
			,@Param("strikeouts") int striketous
			,@Param("whip") double whip
			);
	
//	?????? ?????? ??????
	public void updatePlayerIntroduce(
			@Param("id") int id
			,@Param("playerId") int playerId
			,@Param("title") String title
			,@Param("content") String content
			,@Param("imagePath") String imagePath
			);
	
//	delete
	
//	?????? ??? ??????????????? ??????
	public void deletePlayer(int id);
	
//	?????? ?????? ??????
	public void deleteBatterStatById(int id);
	
//	?????? ?????? ??????
	public void deletePitcherStatById(int id);
	
//	?????? ?????? ??????
	public void deletePlayerIntroduce(int id);
	
}
