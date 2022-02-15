package com.SFG.player.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFG.player.dao.PlayerDAO;
import com.SFG.player.model.Player;

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

}
