package com.SFG.player.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SFG.player.model.PitcherStat;
import com.SFG.player.model.Player;
import com.SFG.player.model.PlayerIntroduce;

@Repository
public interface PlayerDAO {

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
}