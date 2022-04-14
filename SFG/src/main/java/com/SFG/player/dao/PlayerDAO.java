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

//	select
	
	public List<Player> selectAllPlayerList();
	
	public List<Player> selectCoachList();
	
	public List<Player> selectPitcherList();
	
	public List<Player> selectInfieldList();
	
	public List<Player> selectOutfieldList();
	
	// 선수 정보 가져오기
	public Player selectPlayer(int playerId);
	
	// 선수 소개 가져오기
	public PlayerIntroduce selectPlayerIntroduce(int playerId);
	
	// 투수 연도별 성적 가져오기
	public List<PitcherStat> selectPitcherStat(int playerId);
	
	// 타자 연도별 성적 가져오기
	public List<BatterStat> selectBatterStats(int playerId);
	
	
}
