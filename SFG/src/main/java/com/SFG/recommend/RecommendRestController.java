package com.SFG.recommend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SFG.recommend.bo.RecommendBO;
import com.SFG.recommend.model.Recommend;

@RequestMapping("/recommend")
@RestController
public class RecommendRestController {

	@Autowired
	private RecommendBO recommendBO;
	
	@PostMapping("/recommend_click")
	public Map<String, String> recommendClick(
			@RequestParam("userId") int userId
			,@RequestParam("boardId") int boardId
			){
	
		// recommend 확인
		Recommend recommendCheck = recommendBO.getRecommendByUserId(userId, boardId);
		if(recommendCheck==null) {
			recommendBO.addRecommend(userId, boardId);
		}else {
			recommendBO.deleteRecommend(userId, boardId);
		}
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
}
