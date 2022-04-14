package com.SFG.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SFG.player.bo.PlayerBO;
import com.SFG.player.model.Player;

@RequestMapping("/player")
@RestController
public class PlayerRestController {

	@Autowired
	private PlayerBO playerBO;
	
	@RequestMapping("/get_all_player")
	public List<Player> getAllPlayer() {
		
		List<Player> playerList = playerBO.getAllPlayerList();
		
		
		return playerList;
	}
	
	@PostMapping("/add_player")
	public Map<String, String> addPlayer(
			@RequestParam("name") String name
			,@RequestParam("number") String number
			,@RequestParam("position") String position
			,@RequestParam("birth") String birth
			,@RequestParam("debut") String debut
			,@RequestParam("imagePath") String imagePath
			) {
		
		Map<String, String> result = new HashMap<>();
		
		playerBO.addPlayer(name, number, position, birth, debut, imagePath);
		
		result.put("result", "success");
		return result;
	}
}
