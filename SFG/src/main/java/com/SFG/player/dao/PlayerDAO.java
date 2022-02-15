package com.SFG.player.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SFG.player.model.Player;

@Repository
public interface PlayerDAO {

	public List<Player> selectCoachList();
}
